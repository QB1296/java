/**
 * 文件名：SubEntityModel.java  
 *  
 * 版本信息：  
 * 日期：2014年8月12日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.security.meta.model;

import org.apache.commons.digester.annotations.rules.ObjectCreate;
import org.apache.commons.digester.annotations.rules.SetNext;
import org.apache.commons.digester.annotations.rules.SetProperty;


/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2014年8月12日 上午11:54:27
 */
@ObjectCreate(pattern="premission-mapping/moudle/rowRule/values/value")
public class RowRuleValue {
	
	@SetProperty(attributeName="key",pattern="premission-mapping/moudle/rowRule/values/value")
	private String key;
	
	@SetProperty(attributeName="value",pattern="premission-mapping/moudle/rowRule/values/value")
	private String value;
	
	private SQL sql;
	
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	

	public SQL getSql() {
		return sql;
	}

	public void setSql(SQL sql) {
		this.sql = sql;
	}

	@SetNext
	public void addSQL(SQL sql) {
		this.sql = sql;
	}

}
