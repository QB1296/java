/**
 * 文件名： HttpTest.java
 *  
 * 版本信息：  
 * 日期：2015年10月14日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.test;

import static org.junit.Assert.*;

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
import com.ganjx.cinema.service.DivisonCodeService;
import com.ganjx.cinema.service.MaizuoService;
import com.ganjx.cinema.service.TCinemaAddressService;

/**
 * <b>功能描述</b> <br>
 * 
 * @author GANJX
 * @date 2015年10月14日 上午10:39:37
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/applicationContext.xml",
		"classpath:config/secure-Context.xml" })
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=false)
@Transactional(readOnly=false)
@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
public class HttpTest {

	org.slf4j.Logger logger = LoggerFactory.getLogger(HttpTest.class);
	
	@Autowired
	BaiduMapService baiduMapService;
	@Autowired
	DivisonCodeService divisonCodeService;
	@Autowired
	TCinemaAddressService tCinemaAddressService;
	@Autowired
	MaizuoService maizuoService;
	
	@Test
	public void readFromMaizuo() throws Exception {
		maizuoService.readFromMaizuo();
	}
	
	@Test
	public void readMzCity() throws Exception {
		maizuoService.readCity();
	}
	@Test
	public void readCinemaFromDb() throws Exception{
		tCinemaAddressService.readCinemaFromDb();
	}
	
	@Test
	public void readCinema() throws Exception {
		tCinemaAddressService.readCinema();
	}
	
	@Test
	public void readCinemaAddress() throws Exception {
		tCinemaAddressService.readCinemaAddress();
	}
	@Test
	public void readFromCity(){
		tCinemaAddressService.readFromCity();
	}
	
	@Test
	public void readCity(){
		tCinemaAddressService.readCity();
	}
	
	@Test
	public void readFromUrl() throws Exception {
		tCinemaAddressService.readFromUrl();
	}
	
	@Test
	public void readDivisonCode() throws Exception {
		divisonCodeService.readFromUrl();
	}
	
	@Test
	public void getLocationByAddress() throws Exception {
		BaiduMapResponse locationByAddress = baiduMapService.getLocationByAddress("河北省衡水市为民街与育才路交口东南瑞泰广场4楼");
		logger.info("{}",locationByAddress);
	}
}
