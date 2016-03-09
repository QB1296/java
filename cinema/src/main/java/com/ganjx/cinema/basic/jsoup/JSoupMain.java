/**
 * 文件名： JSoupMain.java
 *  
 * 版本信息：  
 * 日期：2015年10月8日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.ganjx.cinema.basic.jsoup;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.ListIterator;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.print.resources.serviceui;

import com.ganjx.cinema.entity.BoxOfficeInfo;
import com.ganjx.cinema.entity.TCinemaAddress;

/**
 * <b>功能描述</b> <br>
 * 
 * @author GANJX
 * @date 2015年10月8日 下午3:30:09
 */
public class JSoupMain {

	static Logger logger = LoggerFactory.getLogger(JSoupMain.class);
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Document doc;
        try {
//        	System.setProperty("http.proxyHost", "127.0.0.1");
//        	System.setProperty("http.proxyPort", "60203");
        	Connection connect = Jsoup.connect("http://58921.com/alltime/2004?page=0&1=");
        	connect.cookie("DIDA642a4585eb3d6e32fdaa37b44468fb6c", "3sqkm2c0tbd311pd546v9hqkn6");
        	connect.cookie("HMACCOUNT", "BA695C8EE37D8116");
        	connect.cookie("Hm_lpvt_e71d0b417f75981e161a94970becbb1b", "1446793337");
        	connect.cookie("Hm_lvt_e71d0b417f75981e161a94970becbb1b", "1446792978");
        	connect.cookie("remember", "MTEzNTI2LjIxNjM0Mi4xMDI4MTYuMTA3MTAwLjExMTM4NC4yMDc3NzQuMTE5OTUyLjExMTM4NC4xMDI4MTYuMA==");
        	connect.cookie("time", "MTEzNTI2LjIxNjM0Mi4xMDI4MTYuMTA3MTAwLjExMTM4NC4yMDc3NzQuMTE5OTUyLjExMTM4NC4xMDQ5NTguMTExMzg0LjExMTM4NC4xMTU2NjguMTE3ODEwLjEyMjA5NC4xMDkyNDIuMTA5MjQyLjExOTk1Mi4xMTEzODQuMA==");
        	connect.timeout(60*1000);
            doc = connect.get();
            Elements select = doc.select(".table-responsive table tr");
            for (Element element : select) {
				Elements select2 = element.select("td");
				if(!select2.isEmpty()){
					BoxOfficeInfo boxOfficeInfo= new BoxOfficeInfo();
					boxOfficeInfo.setCurRanking(Integer.valueOf(select2.get(0).text()));
					boxOfficeInfo.setHisRanking(Integer.valueOf(select2.get(1).text()));
					boxOfficeInfo.setCinemaName(select2.get(2).text());
					String text = select2.get(3).html();
					logger.info("boxoffice:{}",text);
//					boxOfficeInfo.setBoxOffice(new BigDecimal());
					String peopleCount = select2.get(4).text();
					boxOfficeInfo.setPeopleCount(peopleCount.equals("--")?null:Long.valueOf(peopleCount));
					String screenCount = select2.get(5).text();
					boxOfficeInfo.setScreenCount(screenCount.equals("--")?null:Long.valueOf(screenCount));
					boxOfficeInfo.setYear(Integer.valueOf(select2.get(6).text()));
					System.out.println(boxOfficeInfo);
				}
				logger.info("-------------------------------------");
			}
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		 
	}
}
