/**
 * 文件名： MtimeTest.java
 *  
 * 版本信息：  
 * 日期：2015年10月26日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
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

import com.ganjx.cinema.service.MtimeService;

/**
 * <b>功能描述</b> <br>
 * 
 * @author GANJX
 * @date 2015年10月26日 下午5:05:36
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/applicationContext.xml",
		"classpath:config/secure-Context.xml" })
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=false)
@Transactional(readOnly=false)
@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
public class MtimeTest {

	Logger logger = LoggerFactory.getLogger(MtimeTest.class);
	
	@Autowired
	MtimeService mtimeService;
	
	@Test
	public void readFromCinemaAndAddress() throws Exception {
		mtimeService.readFromCinemaAndAddress();
	}
	@Test
	public void testName() throws Exception {
		Document doc;
        try {
        	Connection connect = Jsoup.connect("http://theater.mtime.com/China_Beijing/");
        	connect.timeout(60*1000);
//        	connect.cookie("__utma", "105366342.1075811790.1445852275.1445911733.1445914112.3");
//        	connect.cookie("__utmb", "105366342.2.10.1445914112");
//        	connect.cookie("__utmc", "105366342");
//        	connect.cookie("__utmt_~1", "1");
//        	connect.cookie("__utmt", "1");
//        	connect.cookie("__utmz", "105366342.1445911733.2.2.utmcsr=baidu|utmccn=(organic)|utmcmd=organic");
//        	connect.cookie("_userCode_", "201510261737532202");
//        	connect.cookie("_userIdentity_", "201510261737537499");
//        	connect.cookie("DefaultCity-CookieKey", "290");
//        	connect.cookie("DefaultDistrict-CookieKey", "0");
//        	connect.cookie("Hm_lpvt_6dd1e3b818c756974fb222f0eae5512e", "1445914111");
//        	connect.cookie("Hm_lvt_6dd1e3b818c756974fb222f0eae5512e", "1445852275,1445911732");
            doc = connect.get();
            System.err.println(doc.html());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	@Test
	public void readToAddress() throws Exception {
		mtimeService.readToAddress();
	}
	
	@Test
	public void readCity() throws Exception {
		mtimeService.readCity();
	}
}
