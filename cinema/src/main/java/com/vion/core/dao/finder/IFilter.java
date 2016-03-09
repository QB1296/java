/**
 * 文件名：Filter.java  
 *  
 * 版本信息：  
 * 日期：2013-6-5  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.dao.finder;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2014-6-5 下午12:05:29
 */
public abstract class IFilter {
	
	public static final int OP_EQUAL = 0;
	public static final int OP_NOT_EQUAL = 1;
	public static final int OP_LESS_THAN = 2;
	public static final int OP_GREATER_THAN = 3;
	public static final int OP_LESS_OR_EQUAL = 4;
	public static final int OP_GREATER_OR_EQUAL = 5;
	public static final int OP_LIKE = 6;
	public static final int OP_ILIKE = 7;
	public static final int OP_IN = 8;
	public static final int OP_NOT_IN = 9;
	public static final int OP_NULL = 10;
	public static final int OP_NOT_NULL = 11;
	public static final int OP_EMPTY = 12;
	public static final int OP_NOT_EMPTY = 13;
	public static final int OP_AND = 100;
	public static final int OP_OR = 101;
	public static final int OP_NOT = 102;
	public static final int OP_SOME = 200;
	public static final int OP_ALL = 201;
	public static final int OP_NONE = 202;
	public static final int OP_CUSTOM = 999;
	public static final int OP_START_WITH_CONNECT_BY = 203;
	
	protected String property;
	
	protected int  operator;
	
	protected Object value;
	
	protected Integer fieldOperator;
	
	/**
	 * {@link #fieldOperator}
	 * @return the fieldOperator
	 */
	public Integer getFieldOperator() {
		return fieldOperator;
	}
	
	
	/**
	 * @return the property
	 */
	public String getProperty() {
		return property;
	}
	
	/**
	 * @return the operator
	 */
	public int getOperator() {
		return operator;
	}
	
	
	/**
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}

	
}
