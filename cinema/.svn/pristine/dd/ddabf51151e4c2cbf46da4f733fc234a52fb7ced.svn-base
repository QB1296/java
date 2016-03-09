/**
 * 文件名： CityLevelTest.java
 *  
 * 版本信息：  
 * 日期：2015年11月6日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.ganjx.cinema.service.CityLevelService;

/**
 * <b>功能描述</b> <br>
 * 
 * @author GANJX
 * @date 2015年11月6日 下午4:01:22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/applicationContext.xml",
		"classpath:config/secure-Context.xml" })
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=false)
@Transactional(readOnly=false)
@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
public class CityLevelTest {

	@Autowired
	CityLevelService cityLevelService;
	
	@Test
	public void all() throws Exception {
		one();
		two();
		three();
		four();
		five();
		one2();
	}
	
	@Test
	public void one() throws Exception {
		cityLevelService.one();
	}
	
	@Test
	public void one2() throws Exception {
		cityLevelService.one2();
	}
	
	@Test
	public void two() throws Exception {
		cityLevelService.two();
	}
	
	@Test
	public void three() throws Exception {
		cityLevelService.three();
	}
	
	@Test
	public void four() throws Exception {
		cityLevelService.four();
	}
	
	@Test
	public void five() throws Exception {
		cityLevelService.five();
	}
}
