/**
 * 文件名： CinemaDetailService.java
 *  
 * 版本信息：  
 * 日期：2015年10月15日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.ganjx.cinema.excel;

import java.io.InputStream;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ganjx.cinema.basic.map.BaiduMapResponse;
import com.ganjx.cinema.basic.map.BaiduMapService;
import com.ganjx.cinema.basic.map.MapLocation;
import com.ganjx.cinema.entity.Cinema;
import com.ganjx.cinema.service.CinemaService;
import com.vion.core.dao.IGeneralDAO;
import com.vion.core.poi.BatchProcessor;
import com.vion.core.poi.ExcelImports;

/**
 * <b>功能描述</b> <br>
 * 
 * @author GANJX
 * @date 2015年10月15日 下午2:19:47
 */
@Service
public class CinemaDetailService {
	Logger logger = LoggerFactory.getLogger(CinemaDetailService.class);

	@Autowired
	IGeneralDAO generalDAO;
	@Autowired
	BaiduMapService baiduMapService;
	@Autowired
	CinemaService cinemaService;

	public void importExcel(InputStream inputStream) {
		ExcelImports xls = ExcelImports.XLS();
		int batchSize = 6000;
		xls.setBatchSize(batchSize);
		final CustomExcelImportsService excelImportsService = (CustomExcelImportsService) xls
				.getExcelImportsService();
		xls.importExcel(inputStream, CinemaDetailBo.class,
				new BatchProcessor<CinemaDetailBo>() {

					@Override
					public void processor(List<CinemaDetailBo> data)
							throws Exception {
						// TODO Auto-generated method stub
						try {
							Integer count = 0;
							for (CinemaDetailBo cinemaBo : data) {
								if (StringUtils.isNotBlank(cinemaBo
										.getAddress())
										&& cinemaBo.getCinemaLat() == null
										&& cinemaBo.getCinemaLng() == null ) {
									///////////////////////
									Cinema findByCinemaCode = cinemaService.findByCinemaCode(cinemaBo.getCinemaCode());
									if(findByCinemaCode.getCinemaLat() == null && !cinemaBo.getAddress().equals("无")){
										BaiduMapResponse locationByAddress = baiduMapService.getLocationByAddress(cinemaBo.getAddress());
										if(locationByAddress !=null && locationByAddress.getResult() != null){
											Integer confidence = locationByAddress.getResult().getConfidence();
											MapLocation location = locationByAddress.getResult().getLocation();
											logger.info("{},{}",confidence,location);
											if(confidence>=60){
												cinemaService.update(location, cinemaBo);
											}
										}
										else if(StringUtils.isBlank(findByCinemaCode.getAddress())){
											findByCinemaCode.setAddress(cinemaBo.getAddress());
										}
										count++;	
									}
								}
							}
							logger.info("{}", count);
							logger.info("{}", data.size());
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
