/**
 * 文件名：Sort.java  
 *  
 * 版本信息：  
 * 日期：2013-6-5  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */

package com.vion.core.security.dao;

import com.vion.core.dao.finder.Sort;
import com.vion.core.exception.NoSupportedOperationException;


/**
 * <b>功能描述</b> <br>
 * 排序
 * @author YUJB
 * @date 2014-6-5 下午01:08:33
 */
public class BasicSecureSort extends Sort{
	
	private BasicSecureField basicField;

	/**
	 * @param property
	 */
	public BasicSecureSort(BasicSecureField field) {
		super(field.getProperty());
		this.basicField = field;
	}
	
	

	public BasicSecureSort(BasicSecureField field, Order order) {
		super(field.getProperty(), order);
		this.basicField = field;
	}
	
	public static BasicSecureSort asc(BasicSecureField field) {
		return new BasicSecureSort(field);
	}

	public static BasicSecureSort desc(BasicSecureField field) {
		return new BasicSecureSort(field, Order.desc);
	}

	
	public static Sort asc(String property) {
		throw new NoSupportedOperationException("不支持 请使用asc(BasicSecureField field)方法");
	}

	public static Sort desc(String property) {
		throw new NoSupportedOperationException("不支持 请使用desc(BasicSecureField field)方法");
	}


	/**
	 * {@link #basicField}
	 * @return the basicField
	 */
	public BasicSecureField getBasicField() {
		return basicField;
	}

	/**
	 * {@link #basicField}	
	 * @param basicField the basicField to set
	 */
	public void setBasicField(BasicSecureField basicField) {
		this.basicField = basicField;
	}

	
	
	

}
