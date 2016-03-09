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
import org.apache.commons.digester.annotations.rules.SetProperty;


/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2014年8月12日 上午11:54:27
 */
@ObjectCreate(pattern="premission-mapping/moudle/rowRule/assistTable")
public class RowRuleAssistTable{
	
	@SetProperty(attributeName="code",pattern="premission-mapping/moudle/rowRule/assistTable")
	private String code;
	
	@SetProperty(attributeName="resourceColumn",pattern="premission-mapping/moudle/rowRule/assistTable")
	private String resourceColumn;


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

	/**
	 * {@link #resourceColumn}
	 * @return the resourceColumn
	 */
	public String getResourceColumn() {
		return resourceColumn;
	}

	/**
	 * {@link #resourceColumn}	
	 * @param resourceColumn the resourceColumn to set
	 */
	public void setResourceColumn(String resourceColumn) {
		this.resourceColumn = resourceColumn;
	}
	
	
	
}
