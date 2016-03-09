/**
 * 文件名：MoudleModel.java  
 *  
 * 版本信息：  
 * 日期：2014年8月12日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.security.meta.model;

import org.apache.commons.digester.annotations.rules.ObjectCreate;
import org.apache.commons.digester.annotations.rules.SetProperty;


/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2014年8月12日 上午11:45:06
 */
@ObjectCreate(pattern="premission-mapping/pointcuts/pointcut")
public class PointCutModel {

	@SetProperty(attributeName="value",pattern="premission-mapping/pointcuts/pointcut")
	private String value;
	

	@SetProperty(attributeName="code",pattern="premission-mapping/pointcuts/pointcut")
	private String code;

	/**
	 * {@link #value}
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * {@link #value}	
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * {@link #code}
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * {@link #code}	
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	
}
