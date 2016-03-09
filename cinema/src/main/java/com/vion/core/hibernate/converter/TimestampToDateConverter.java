/**
 * 文件名： TimestampToDateConvert.java
 *  
 * 版本信息：  
 * 日期：2015年4月16日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.vion.core.hibernate.converter;

import java.sql.Timestamp;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

/**
 * <b>功能描述</b> <br>
 * 
 * @author GANJX
 * @date 2015年4月16日 上午11:01:22
 */
public class TimestampToDateConverter implements  Converter<Timestamp,Date>{

	/**
	 * 
	 */
	Logger logger = LoggerFactory.getLogger(TimestampToDateConverter.class);
	
	/* (non-Javadoc)
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public Date convert(Timestamp source) {
		// TODO Auto-generated method stub
		return source;
	}

}
