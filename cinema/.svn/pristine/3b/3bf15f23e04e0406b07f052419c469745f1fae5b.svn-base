/**
 * 文件名：BussinessException.java  
 *  
 * 版本信息：  
 * 日期：2014-6-11  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.exception;

import java.text.MessageFormat;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.vion.core.spring.PropertiesAccessor;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2014-6-13 上午08:57:24
 */
public class SpiderBussinessException extends Exception{

	/**   */
	private static final long serialVersionUID = 1L;
	
	private String exceptionCode;
	
	private String exceptionDes;
	
	private PropertiesAccessor propertiesAccessor;

	public SpiderBussinessException(Throwable cause,String exceptionCode,Object... arguments) {
		super(exceptionCode, cause);
		initProperties(exceptionCode,arguments);
	}

	public SpiderBussinessException(String exceptionCode,Object... arguments) {
		super(exceptionCode);
		initProperties(exceptionCode,arguments);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()*/
	@Override
	public String getMessage() {
		return "错误码：" + "[" + exceptionCode + "]" + "错误信息："+ "["  + exceptionDes+ "]";
	}


	private void initProperties(String exceptionCode,Object... arguments){
		this.exceptionCode = exceptionCode;
		if (propertiesAccessor  == null) {
			WebApplicationContext applicationContext = ContextLoader.getCurrentWebApplicationContext();
			propertiesAccessor = applicationContext.getBean(PropertiesAccessor.class);
		}
		String property = propertiesAccessor.get(exceptionCode);
		String format = MessageFormat.format(property, arguments);
		exceptionDes = format;
	}
	
	public String getExceptionCode() {
		return exceptionCode;
	}

	public String getExceptionDes() {
		return exceptionDes;
	}
	

}
