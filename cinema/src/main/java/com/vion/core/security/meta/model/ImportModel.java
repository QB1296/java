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
@ObjectCreate(pattern="premission-mapping/import")
public class ImportModel {

	@SetProperty(attributeName="url",pattern="premission-mapping/import")
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
