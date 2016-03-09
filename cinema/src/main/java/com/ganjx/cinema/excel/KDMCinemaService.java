/**
 * 文件名： KDMCinemaService.java
 *  
 * 版本信息：  
 * 日期：2015年11月23日 
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

import com.ganjx.cinema.basic.map.BaiduMapService;
import com.ganjx.cinema.entity.Cinema;
import com.ganjx.cinema.service.CinemaService;
import com.vion.core.dao.IGeneralDAO;
import com.vion.core.poi.BatchProcessor;
import com.vion.core.poi.ExcelImports;

/**
 * <b>功能描述</b> <br>
 * 
 * @author GANJX
 * @date 2015年11月23日 上午11:25:45
 */
@Service
public class KDMCinemaService {

	Logger logger = LoggerFactory.getLogger(CinemaDetailService.class);

	@Autowired
	IGeneralDAO generalDAO;
	@Autowired
	BaiduMapService baiduMapService;
	@Autowired
	CinemaService cinemaService;
	
	public void importExcel(InputStream inputStream) {
		ExcelImports xls = ExcelImports.XLSX();
		int batchSize = 6000;
		xls.setBatchSize(batchSize);
		final CustomExcelImportsService excelImportsService = (CustomExcelImportsService) xls
				.getExcelImportsService();
		xls.importExcel(inputStream, KDMCCinemaBo.class,
				new BatchProcessor<KDMCCinemaBo>() {

					@Override
					public void processor(List<KDMCCinemaBo> data)
							throws Exception {
						// TODO Auto-generated method stub
						try {
							for (KDMCCinemaBo cinemaBo : data) {
								if(cinemaBo.getCinemaCode().length() <8)
								{
									continue;
								}								
								Cinema findByCinemaCode = cinemaService.findByCinemaCode(cinemaBo.getCinemaCode());
								if(findByCinemaCode == null){
									Integer findMaxId = cinemaService.findMaxId();
									Cinema cinema = new Cinema();
									cinema.setId(findMaxId +1);
									cinema.setCinemaName(cinemaBo.getCinemaName());
									cinema.setCinemaCode(cinemaBo.getCinemaCode());
									cinema.setTheatreChain(cinemaBo.getTheatreChain());
									cinema.setConfidence(10);
									generalDAO.save(cinema);
									logger.info("{}",cinemaBo);
								}
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} finally {
							excelImportsService.getCountDownLatch().countDown();
						}
					}
				});

		try {
			excelImportsService.getCountDownLatch().await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
