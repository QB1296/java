/**
 * 文件名： WordService.java
 *  
 * 版本信息：  
 * 日期：2015年11月6日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.ganjx.cinema.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ganjx.cinema.entity.CityCode;
import com.vion.core.dao.IGeneralDAO;

/**
 * <b>功能描述</b> <br>
 * 
 * @author GANJX
 * @date 2015年11月6日 下午12:49:29
 */
@Service
@Transactional
public class WordService {

	@Autowired
	IGeneralDAO generalDAO;
	
	public void readWord(InputStream inputStream) throws IOException {
	      HWPFDocument doc = new HWPFDocument(inputStream);  
	      Range range = doc.getRange();
	      readList(range);
	}
	
	private void readList(Range range) {  
	      int num = range.numParagraphs();  
	      Paragraph para;  
	      String level = "";
	      for (int i=0; i<num; i++) {  
	         para = range.getParagraph(i); 
	         String strip = StringUtils.strip(para.text());
	         if(StringUtils.isNotBlank(strip)){
	        	 if(strip.indexOf("线") == 1){
	        		 level = StringUtils.left(strip, 2);
	        	 }
	        	 else{
	        		 String left = StringUtils.left(strip, strip.indexOf("("));
	        		 List<CityCode> result = generalDAO.findByJPQL("from CityCode t where t.name=?",new Object[]{left}).result(CityCode.class);
		        	 if(result !=null){
		        		 for (CityCode cityCode : result) {
		        			 if(StringUtils.isBlank(cityCode.getLevel())){
		        				 System.err.println(level +"--"+cityCode.getName());
		        				 cityCode.setLevel(level);
		        			 }
						}
		        	 }
	        	 }
	         }
	      }  
	   }  
}
