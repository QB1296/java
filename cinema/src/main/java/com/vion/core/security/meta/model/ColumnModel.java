/**
 * 文件名：ColumnModel.java  
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
 * @date 2014年8月12日 上午11:55:18
 */
@ObjectCreate(pattern="premission-mapping/moudle/entitys/entity/column")
public class ColumnModel {
	
	@SetProperty(attributeName="name",pattern="premission-mapping/moudle/entitys/entity/column")
	private String name;
	
	@SetProperty(attributeName="code",pattern="premission-mapping/moudle/entitys/entity/column")
	private String code;
	
	@SetProperty(attributeName="operate",pattern="premission-mapping/moudle/entitys/entity/column")
	private String operate;
	
	private ColumnValueConfigModel valueConfigModel;
	
	@SetNext
	public void addValueConfigModel(ColumnValueConfigModel valueConfigModel){
		this.valueConfigModel = valueConfigModel;
	}
	
	/**
	 * {@link #valueConfigModel}
	 * @return the valueConfigModel
	 */
	public ColumnValueConfigModel getValueConfigModel() {
		return valueConfigModel;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}


	
	
	
	
}
