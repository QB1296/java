/**
 * 文件名： LashowTest.java
 *  
 * 版本信息：  
 * 日期：2015年10月20日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.test;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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

import com.ganjx.cinema.entity.LsCity;
import com.ganjx.cinema.service.LashouService;
import com.vion.core.dao.IGeneralDAO;

/**
 * <b>功能描述</b> <br>
 * 
 * @author GANJX
 * @date 2015年10月20日 下午12:49:16
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/applicationContext.xml",
		"classpath:config/secure-Context.xml" })
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=false)
@Transactional(readOnly=false)
@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
public class LashouTest {
	
	Logger logger = LoggerFactory.getLogger(LashouTest.class);
	
	@Autowired
	IGeneralDAO generalDAO;
	@Autowired
	LashouService lashouService;
	
	@Test
	public void readFromCinemaAndBaidu() throws Exception {
		lashouService.readFromCinemaAndBaidu();
	}
	
	@Test
	public void readFromCinemaAndAddress() throws Exception {
		lashouService.readFromCinemaAndAddress();
	}
	
	
	@Test
	public void readByCity() {
		lashouService.readByCity();
	}
	
	@Test
	public void readCity() throws Exception {
		Document doc;
        try {
        	System.setProperty("http.proxyHost", "127.0.0.1");
        	System.setProperty("http.proxyPort", "60643");
        	Connection connect = Jsoup.connect("http://beijing.lashou.com/changecity");
        	connect.timeout(60*1000);
            doc = connect.get();
            Elements select = doc.select(".citys-list a");
            for (Element element : select) {
            	LsCity lsCity = new LsCity(element.attr("href"),element.text());
            	generalDAO.save(lsCity);
			}
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
}
