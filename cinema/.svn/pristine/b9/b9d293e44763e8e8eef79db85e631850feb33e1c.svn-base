/**
 * 文件名： DbTest.java
 *  
 * 版本信息：  
 * 日期：2015年10月13日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.ganjx.cinema.service.CinemaNameService;

/**
 * <b>功能描述</b> <br>
 * 
 * @author GANJX
 * @date 2015年10月13日 下午5:41:19
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/applicationContext.xml",
		"classpath:config/secure-Context.xml" })
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=false)
@Transactional(readOnly=false)
@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
public class DbTest {

	Logger logger = LoggerFactory.getLogger(DbTest.class);
	
	@Autowired
	CinemaNameService cinemaNameService;
	
	@Test
	public void readDb() throws Exception {
		cinemaNameService.readCinema();
	}
}
