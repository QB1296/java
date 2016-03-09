/**
 * 文件名：ValueConfigModel.java  
 *  
 * 版本信息：  
 * 日期：2014年12月18日  
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
 * @date 2014年12月18日 下午1:59:59
 */
@ObjectCreate(pattern="premission-mapping/moudle/rowRule/valueConfig")
public class RowRuleValueConfigModel {

	@SetProperty(attributeName="type",pattern="premission-mapping/moudle/rowRule/valueConfig")
	private String type;
	
	@SetProperty(attributeName="show",pattern="premission-mapping/moudle/rowRule/valueConfig")
	private String show;
	
	@SetProperty(attributeName="dataUrl",pattern="premission-mapping/moudle/rowRule/valueConfig")
	private String dataUrl;
	
	@SetProperty(attributeName="valueRule",pattern="premission-mapping/moudle/rowRule/valueConfig")
	private String valueRule;
	
	@SetProperty(attributeName="valueProcessor",pattern="premission-mapping/moudle/rowRule/valueConfig")
	private String valueProcessor;

	/**
	 * {@link #type}
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * {@link #type}	
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * {@link #show}
	 * @return the show
	 */
	public String getShow() {
		return show;
	}

	/**
	 * {@link #show}	
	 * @param show the show to set
	 */
	public void setShow(String show) {
		this.show = show;
	}

	/**
	 * {@link #dataUrl}
	 * @return the dataUrl
	 */
	public String getDataUrl() {
		return dataUrl;
	}

	/**
	 * {@link #dataUrl}	
	 * @param dataUrl the dataUrl to set
	 */
	public void setDataUrl(String dataUrl) {
		this.dataUrl = dataUrl;
	}

	/**
	 * {@link #valueRule}
	 * @return the valueRule
	 */
	public String getValueRule() {
		return valueRule;
	}

	/**
	 * {@link #valueRule}	
	 * @param valueRule the valueRule to set
	 */
	public void setValueRule(String valueRule) {
		this.valueRule = valueRule;
	}

	/**
	 * {@link #valueProcessor}
	 * @return the valueProcessor
	 */
	public String getValueProcessor() {
		return valueProcessor;
	}

	/**
	 * {@link #valueProcessor}	
	 * @param valueProcessor the valueProcessor to set
	 */
	public void setValueProcessor(String valueProcessor) {
		this.valueProcessor = valueProcessor;
	}
	
	
	
}
