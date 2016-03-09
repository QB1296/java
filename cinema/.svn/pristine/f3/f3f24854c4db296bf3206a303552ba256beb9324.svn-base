/**
 * 文件名： MapTest.java
 *  
 * 版本信息：  
 * 日期：2015年10月12日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.ganjx.cinema.basic.map.BaiduMapResponse;
import com.ganjx.cinema.basic.map.BaiduMapService;
import com.ganjx.cinema.basic.map.MapLocation;

/**
 * <b>功能描述</b> <br>
 * 
 * @author GANJX
 * @date 2015年10月12日 下午6:29:04
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/applicationContext.xml",
		"classpath:config/secure-Context.xml" })
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=false)
@Transactional(readOnly=false)
@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
public class MapTest {

	org.slf4j.Logger logger = LoggerFactory.getLogger(MapTest.class);
	
	@Autowired
	BaiduMapService baiduMapService;
	
	@Test
	public void testName() throws Exception {
//		BaiduMapResponse locationByAddress = baiduMapService.getLocationByAddress("北京耀莱影城王府井店");
		BaiduMapResponse locationByAddress = baiduMapService.getLocationByAddress("耀莱影城（王府井店）");
		logger.warn("{}",locationByAddress);
	}
	
}
