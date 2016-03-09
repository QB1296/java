/**
 * 文件名： DivisonCodeService.java
 *  
 * 版本信息：  
 * 日期：2015年10月15日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.ganjx.cinema.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ganjx.cinema.entity.DivisonCode;
import com.vion.core.dao.IGeneralDAO;

/**
 * <b>功能描述</b> <br>
 * 
 * @author GANJX
 * @date 2015年10月15日 上午9:58:00
 */
@Service
@Transactional
public class DivisonCodeService {

	Logger logger = LoggerFactory.getLogger(DivisonCodeService.class);
	
	@Autowired
	IGeneralDAO generalDAO;
	
	Map<Integer, DivisonCode> divisonMap = new HashMap<Integer, DivisonCode>();
	 
	public void readFromUrl(){
		generalDAO.executeHQL("delete from DivisonCode");
		Document doc;
        try {
            doc = Jsoup.connect("http://www.stats.gov.cn/tjsj/tjbz/xzqhdm/201504/t20150415_712722.html").get();
            Elements ListDiv = doc.getElementsByAttributeValue("class","MsoNormal");
           
            DivisonCode province = null;
            DivisonCode city = null;
            for (Element element :ListDiv) {
            	Elements children = element.children();
            	ListIterator<Element> listIterator = children.listIterator();
            	boolean first = true;   
            	DivisonCode divisonCode = new DivisonCode();
            	while (listIterator.hasNext()) {
					Element next = listIterator.next();
					String text = next.text();
					if(first){
						divisonCode.setId(Integer.valueOf(StringUtils.strip(text).substring(0,6)));
						first=false;
					}
					else{
						divisonCode.setName(StringUtils.strip(next.text()));
					}
					
					String[] split = text.split("　");
					switch (split.length) {
						case 1:break;
						case 2://省份
							province = divisonCode;
							divisonMap.put(divisonCode.getId(), divisonCode);
							break;
						case 3:
							//市区
							city = divisonCode;
							divisonCode.setPid(province.getId());
							province.getChildren().add(divisonCode);
							break;
						case 4:
							divisonCode.setPid(city.getId());
							appendChildren(province,city,divisonCode);
							break;
					}
				}
            	
            	logger.info("{}",divisonCode);
            	generalDAO.save(divisonCode);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }	
	}
	
	public void appendChildren(DivisonCode province,DivisonCode city,DivisonCode county){
		DivisonCode divisonCode = divisonMap.get(province.getId());
		List<DivisonCode> children = divisonCode.getChildren();
		for (DivisonCode divisonCode2 : children) {
			if(divisonCode2.getId() == city.getId()){
				city.getChildren().add(county);
			}
		}
	}
	
}
