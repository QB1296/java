/**
 * 文件名：DefaultFuncNodeModel.java  
 *  
 * 版本信息：  
 * 日期：2014年12月16日  
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
 * @date 2014年12月16日 下午1:25:23
 */
@ObjectCreate(pattern="premission-mapping/defaultDataFunc/nodes/node")
public class DefaultFuncNodeModel {
	
	@SetProperty(attributeName="column",pattern="premission-mapping/defaultDataFunc/nodes/node")
	private String column;
	
	@SetProperty(attributeName="operate",pattern="premission-mapping/defaultDataFunc/nodes/node")
	private String operate;
	
	@SetProperty(attributeName="value",pattern="premission-mapping/defaultDataFunc/nodes/node")
	private String value;

	/**
	 * {@link #column}
	 * @return the column
	 */
	public String getColumn() {
		return column;
	}

	/**
	 * {@link #column}	
	 * @param column the column to set
	 */
	public void setColumn(String column) {
		this.column = column;
	}

	/**
	 * {@link #operate}
	 * @return the operate
	 */
	public String getOperate() {
		return operate;
	}

	/**
	 * {@link #operate}	
	 * @param operate the operate to set
	 */
	public void setOperate(String operate) {
		this.operate = operate;
	}

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
	
	
	
}
