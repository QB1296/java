/**
 * 文件名：BasicGroupBy.java  
 *  
 * 版本信息：  
 * 日期：2015年1月9日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.security.dao;

import com.vion.core.dao.finder.IFilter;
import com.vion.core.dao.finder.IGroup;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2015年1月9日 下午3:50:10
 */
public class BasicSecureGroup implements IGroup{
	
	/** 排序字段名称  */
	protected BasicSecureField[] fields;
	
	private BasicSecureFilter having;
	
	private String[] properties;
	
	
	public static BasicSecureGroup me(){
		return new BasicSecureGroup();
	}
	
	
	public BasicSecureGroup by(BasicSecureField... fields){
		this.fields = fields;
		return this;
	}
	
	public BasicSecureGroup having(BasicSecureFilter having){
		this.having = having;
		return this;
	}
	
	/**
	 * {@link #fields}
	 * @return the fields
	 */
	BasicSecureField[] getFields() {
		return fields;
	}
	
	void setProperties(String[] properties) {
		this.properties = properties;
	}

	/* (non-Javadoc)
	 * @see com.vion.core.dao.finder.IGroupBy#getPropertys()*/
	@Override
	public String[] getProperties() {
		return properties;
	}

	/* (non-Javadoc)
	 * @see com.vion.core.dao.finder.IGroupBy#getHaving()*/
	@Override
	public IFilter getHaving() {
		return having;
	}


	
	
}
