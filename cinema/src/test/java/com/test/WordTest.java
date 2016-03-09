/**
 * 文件名： WordTest.java
 *  
 * 版本信息：  
 * 日期：2015年11月6日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.ganjx.cinema.service.WordService;

/**
 * <b>功能描述</b> <br>
 * 
 * @author GANJX
 * @date 2015年11月6日 下午12:49:19
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/applicationContext.xml",
		"classpath:config/secure-Context.xml" })
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=false)
@Transactional(readOnly=false)
@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
public class WordTest {

	@Autowired
	WordService wordService;
	
	@Test
	public void testName() throws Exception {
		InputStream inputStream = new FileInputStream(new File("E:\\平台\\8-cinema\\1-文档\\2015年中国最新城市等级划分出炉.doc"));
		wordService.readWord(inputStream);
	}
	
	@Test
	public void testName1() throws Exception {
		System.err.println(" ".equals(" "));
	}
}
