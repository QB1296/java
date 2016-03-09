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
@ObjectCreate(pattern="premission-mapping/moudle/rowRule/cacheFilter")
public class RowRuleCacheFilter{
	
	/** 宿主  */
	@SetProperty(attributeName="affectSQLFilter",pattern="premission-mapping/moudle/rowRule/cacheFilter")
	private String affectSQLFilter;


	/**
	 * {@link #宿主}	
	 * @param parasitifer the 宿主 to set
	 */
	public void setAffectSQLFilter(String parasitifer) {
		this.affectSQLFilter = parasitifer;
	}
	
	
	/**
	 * 获得affectSQLFilter 
	 * @return  affectSQLFilter affectSQLFilter
	 */
	public String getAffectSQLFilter() {
		return affectSQLFilter;
	}
	
}
