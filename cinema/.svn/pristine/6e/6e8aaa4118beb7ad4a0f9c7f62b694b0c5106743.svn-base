/**
 * 文件名： CityLevelService.java
 *  
 * 版本信息：  
 * 日期：2015年11月6日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.ganjx.cinema.service;

import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ganjx.cinema.entity.CityCode;
import com.google.common.collect.Lists;
import com.vion.core.dao.IGeneralDAO;

/**
 * <b>功能描述</b> <br>
 * 
 * @author GANJX
 * @date 2015年11月6日 下午4:02:57
 */
@Service
@Transactional
public class CityLevelService {

	@Autowired
	IGeneralDAO generalDAO;
	
	public void one(){
		Document doc;
		try {
			Connection connect = Jsoup.connect("http://baike.baidu.com/link?url=uNgi3J4Lww1XNvU8AN6QJdXyu36hVc1UWX8W0f5x3Y_TXO-lMmKWFWIiMw_20Uf9Ec9h74ZOanN3H4cy7wdYfK");
			connect.timeout(60*1000);
	        doc = connect.get();
	        Elements select = doc.select(".lemma-summary .para a");
	        for (Element element : select) {
	        	changeCityCodeLevel("一线",element.text());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void one2(){
		List<String> cityList = Lists.newArrayList("成都","杭州","南京","武汉","天津","西安","重庆","青岛","沈阳","长沙","大连","厦门","无锡","福州","济南");
		for (String string : cityList) {
			changeCityCodeLevel("一线",string);
		}
	}
	
	/**
	 * 
	 */
	public void two() {
		Document doc;
		try {
			Connection connect = Jsoup.connect("http://baike.baidu.com/link?url=0S-rPquN7UYx4mZIcGTnY-AluakB0OE1aHqxMCs_sIySPDjxrgKaACIwrhQrGXSWau3NSuytRZsdpWxmS05uea");
			connect.timeout(60*1000);
	        doc = connect.get();
	        Elements select = doc.select(".para");
	        Element element2 = select.get(1);
	        String text = element2.text();
	        String[] split = text.substring(0, text.length()-1).split("、");
	        for (String string : split) {
	        	changeCityCodeLevel("二线",string);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void three(){
		Document doc;
		try {
			Connection connect = Jsoup.connect("http://baike.baidu.com/link?url=goWGCeeADlNoxtOSxSqpQ6eDMkzCFk_U8OtyTZF4TXS1Zjqtdkycsq_gcVwwfeH-1Zo_1yu1gkBKVFVpm8eZna");
			connect.timeout(60*1000);
	        doc = connect.get();
	        Elements select = doc.select(".para");
	        Element element2 = select.get(1);
	        String text = element2.text();
	        String[] split = text.substring(0, text.length()-1).split("、");
	        for (String string : split) {
//	        	System.err.println(string);
	        	changeCityCodeLevel("三线",string);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void four(){
		Document doc;
		try {
			Connection connect = Jsoup.connect("http://baike.baidu.com/link?url=1BYQ2g4vDgnWMEvTDiNPjpZGGLvMHLamVd5BLHXH1gAkmsT8yDZbMQI9hzLO9E_kAzqYjrNFlH8gwJxNhAMU1K");
			connect.timeout(60*1000);
	        doc = connect.get();
	        Elements select = doc.select(".para");
	        Element element2 = select.get(2);
	        String text = element2.text();	        
	        String[] split = text.substring(0, text.length()-1).split("、");
	        for (String string : split) {
//	        	System.err.println(string);
	        	changeCityCodeLevel("四线",string);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void five(){
		Document doc;
		try {
			Connection connect = Jsoup.connect("http://baike.baidu.com/link?url=xaG9rTQDzcuVyMo_nnIIRO6tK3q1M8i82Kj9ZAqw2vTOGGZzGCiTB4kKX80MPkz_BCG1Pnmv78YzQvk-VjmIdK");
			connect.timeout(60*1000);
	        doc = connect.get();
	        Elements select = doc.select(".para");
	        Element element2 = select.get(1);
	        String text = element2.text();	        
	        String[] split = text.substring(0, text.length()-1).split("、");
	        for (String string : split) {
//	        	System.err.println(string);
	        	changeCityCodeLevel("五线",string);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * @param levelName
	 * @param cityName
	 */
	public void changeCityCodeLevel(String levelName,String cityName){
		 List<CityCode> result = generalDAO.findByJPQL("from CityCode t where t.name=?",new Object[]{cityName}).result(CityCode.class);
    	 if(result !=null){
    		 for (CityCode cityCode : result) {
				cityCode.setLevel(levelName);
			}
    	 }
	}
}
