/**
 * 文件名： CinemaService.java
 *  
 * 版本信息：  
 * 日期：2015年10月14日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.ganjx.cinema.excel;

import java.io.InputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ganjx.cinema.service.CinemaService;
import com.vion.core.dao.IGeneralDAO;
import com.vion.core.poi.BatchProcessor;
import com.vion.core.poi.ExcelImports;

/**
 * <b>功能描述</b> <br>
 * 
 * @author GANJX
 * @date 2015年10月14日 下午2:20:48
 */
@Service
public class CinemaExcelService {
	
	Logger logger = LoggerFactory.getLogger(PoiService.class);
	
	@Autowired
	IGeneralDAO generalDAO;
	@Autowired
	CinemaService cinemaService;
	/**
	 * @param inputStream
	 * @param saveCinema
	 * @param saveBoxoffice
	 */
	public void importExcel(InputStream inputStream){
		ExcelImports xls = ExcelImports.XLSX();
		int batchSize = 100;
		xls.setBatchSize(batchSize);		
		final CustomExcelImportsService excelImportsService = (CustomExcelImportsService) xls.getExcelImportsService();
		xls.importExcel(inputStream, CinemaBo.class, new BatchProcessor<CinemaBo>(){
			
			@Override
			public void processor(List<CinemaBo> data) throws Exception {
				// TODO Auto-generated method stub
				try {
					logger.info("{}",data.size());
					for (CinemaBo cinemaBo : data) {
						logger.info("{}",cinemaBo);
						cinemaService.update(cinemaBo);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally{
					excelImportsService.getCountDownLatch().countDown();
				}
			}});
		
		
		
		try {
			excelImportsService.getCountDownLatch().await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	
	
}
