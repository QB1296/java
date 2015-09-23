/**
 * 文件名： PoiService.java
 *  
 * 版本信息：  
 * 日期：2015年3月27日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.ganjx.cinema.excel;

import java.io.InputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ganjx.cinema.entity.Tcinema;
import com.ganjx.cinema.service.TcinemaService;
import com.vion.core.dao.IGeneralDAO;
import com.vion.core.poi.BatchProcessor;
import com.vion.core.poi.ExcelImports;

/**
 * <b>功能描述</b> <br>
 * 
 * @author GANJX
 * @date 2015年3月27日 下午2:53:08
 */
@Service
public class PoiService {

	Logger logger = LoggerFactory.getLogger(PoiService.class);
	
	@Autowired
	IGeneralDAO generalDAO;
	@Autowired
	TcinemaService tcinemaService;
	/**
	 * @param inputStream
	 * @param saveCinema
	 * @param saveBoxoffice
	 */
	public void importExcel(InputStream inputStream){
		ExcelImports xls = ExcelImports.XLS();
		int batchSize = 10000;
		xls.setBatchSize(batchSize);		
		final CustomExcelImportsService excelImportsService = (CustomExcelImportsService) xls.getExcelImportsService();
		xls.importExcel(inputStream, OriginalBo.class, new BatchProcessor<OriginalBo>(){
			
			@Override
			public void processor(List<OriginalBo> data) throws Exception {
				// TODO Auto-generated method stub
				try {
					logger.info("{}",data.size());
					for (OriginalBo originalBo : data) {
						Tcinema tcinema = new Tcinema();
						BeanUtils.copyProperties(originalBo, tcinema);
						try {
							tcinemaService.save(tcinema);
						} catch (Throwable e) {
							// TODO: handle exception
							e.printStackTrace();
						}
						
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
