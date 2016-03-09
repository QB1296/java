/**
 * 文件名： NCinemaTest.java
 *  
 * 版本信息：  
 * 日期：2015年11月23日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;

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

import com.ganjx.cinema.basic.map.BaiduMapService;
import com.ganjx.cinema.excel.CinemaDetailService;
import com.ganjx.cinema.excel.CinemaExcelService;
import com.ganjx.cinema.excel.KDMCinemaService;
import com.ganjx.cinema.excel.PoiService;
import com.vion.core.ResourcesHolder;

/**
 * <b>功能描述</b> <br>
 * 
 * @author GANJX
 * @date 2015年11月23日 上午11:23:05
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/applicationContext.xml",
		"classpath:config/secure-Context.xml" })
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=false)
@Transactional(readOnly=false)
@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
public class NCinemaTest {

	Logger logger = LoggerFactory.getLogger(ExcelTest.class);

	@Autowired
	private ApplicationContext applicationContext;
	@Autowired
	PoiService poiService;
	@Autowired
	KDMCinemaService kdmCinemaService;
	
	
	@Test
	public void readCinemaDetail() throws Exception {
		ResourcesHolder.bind("ac", applicationContext);
		File excel = new File("E:\\平台\\8-cinema\\1-文档\\KDM信息-20150908科研所.xlsx");
		kdmCinemaService.importExcel(new FileInputStream(excel));
	}
}
