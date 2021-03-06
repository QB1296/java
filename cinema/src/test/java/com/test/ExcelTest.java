/**
 * 文件名： ExcelTest.java
 *  
 * 版本信息：  
 * 日期：2015年9月23日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.ganjx.cinema.basic.map.BaiduMapResponse;
import com.ganjx.cinema.basic.map.BaiduMapService;
import com.ganjx.cinema.excel.CinemaDetailService;
import com.ganjx.cinema.excel.CinemaExcelService;
import com.ganjx.cinema.excel.PoiService;
import com.vion.core.ResourcesHolder;

/**
 * <b>功能描述</b> <br>
 * 
 * @author GANJX
 * @date 2015年9月23日 上午10:51:07
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/applicationContext.xml",
		"classpath:config/secure-Context.xml" })
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=false)
@Transactional(readOnly=false)
@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
public class ExcelTest {

	Logger logger = LoggerFactory.getLogger(ExcelTest.class);

	@Autowired
	private ApplicationContext applicationContext;
	@Autowired
	PoiService poiService;
	@Autowired
	CinemaExcelService cinemaService;
	@Autowired
	CinemaDetailService cinemaDetailService;
	@Autowired
	BaiduMapService baiduMapService;
	
	@Test
	public void readCinemaDetail() throws Exception {
		ResourcesHolder.bind("ac", applicationContext);
		File excel = new File("E:\\平台\\8-cinema\\1-文档\\没地址cinema.xls");
		cinemaDetailService.importExcel(new FileInputStream(excel));
	}
	
	@Test
	public void testGetAddress() throws Exception {
		BaiduMapResponse locationByAddress = baiduMapService.getLocationByAddress("北京市朝阳区朝阳北路长楹天街购物中心东区5F-09");
		logger.info("{}",locationByAddress);
		BaiduMapResponse locationByAddress1 = baiduMapService.getLocationByAddress("北京市朝阳区朝阳北路长楹天街购物中心");
		logger.info("{}",locationByAddress1);
	}
	
	@Test
	public void readExcel() throws Exception {
		ResourcesHolder.bind("ac", applicationContext);
		File excel = new File("E:\\平台\\8-cinema\\3-影院信息\\影院信息.xlsx");
		cinemaService.importExcel(new FileInputStream(excel));
	}
	
	
	@Test
	public void testName() throws Exception {
		long t = System.currentTimeMillis();
		ResourcesHolder.bind("ac", applicationContext);

		File excel = new File("E:\\平台\\1-院线平台\\5-客户数据\\新建文件夹");
		Collection<File> listFiles = FileUtils.listFiles(excel, new String[]{"xls"}, true);
		for (File file : listFiles) {
			System.err.println(file);
			InputStream inputStream = new FileInputStream(file);
			poiService.importExcel(inputStream);
		}
		System.out.println(System.currentTimeMillis() - t);
	}
}
