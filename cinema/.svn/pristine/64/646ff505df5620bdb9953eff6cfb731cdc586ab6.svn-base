/**
 * 文件名： StringToDateConvert.java
 *  
 * 版本信息：  
 * 日期：2015年4月16日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.vion.core.hibernate.converter;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

/**
 * <b>功能描述</b> <br>
 * 
 * @author GANJX
 * @date 2015年4月16日 上午10:49:19
 */
public class StringToDateConverter implements  Converter<String,Date>{

	Logger logger = LoggerFactory.getLogger(StringToDateConverter.class);
	
	/* (non-Javadoc)
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public Date convert(String source) {
		// TODO Auto-generated method stub
		try {
			String[] format = new String[]{
					"yyyy-MM-dd HH:mm:ss",
					DateFormatUtils.ISO_DATE_FORMAT.getPattern(),
					DateFormatUtils.ISO_TIME_NO_T_FORMAT.getPattern(),
			};
			return DateUtils.parseDate(source, format);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
