/**
 * 文件名：Field.java  
 *  
 * 版本信息：  
 * 日期：2013-6-5  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.dao.finder;

import java.io.Serializable;

/**
 * <b>功能描述</b> <br>
 * @author YUJB
 * @date 2014-6-5 下午03:20:05
 */
public class Field implements Serializable{

	/**   */
	private static final long serialVersionUID = 1L;
	
	
	private String property;
	
	private Integer operator;
	
	public static Field all;
	
	private String alias;
	
	
	public static Field all(){
		if (all == null) {
			all = new Field.AllField(); 
		}
		return all;
	}
	
	public boolean isSelectAllFields(){
		return (this instanceof AllField);
	}
	
	/**
	 * 
	 */
	public Field() {
		super();
	}
	
	/**
	 * @param property
	 */
	public Field(String property) {
		super();
		this.property = property;
	}
	

	public Field(String property, Integer operator) {
		super();
		this.property = property;
		this.operator = operator;
	}


	/**
	 */
	public static final int OP_PROPERTY = 0;
	
	/**
	 */
	public static final int OP_COUNT = 1;
	
	/**
	 */
	public static final int OP_COUNT_DISTINCT = 2;
	
	/**
	 */
	public static final int OP_MAX = 3;
	
	/**
	 */
	public static final int OP_MIN = 4;
	
	/**
	 */
	public static final int OP_SUM = 5;
	
	/**
	 */
	public static final int OP_AVG = 6;

	
	/**
	 * @return the property
	 */
	public String getProperty() {
		return property;
	}

	/**
	 * @param property the property to set
	 */
	public Field setProperty(String property) {
		this.property = property;
		return this;
	}

	/**
	 * @return the operator
	 */
	public Integer getOperator() {
		return operator;
	}

	/**
	 * @param operator the operator to set
	 */
	public Field setOperator(int operator) {
		this.operator = operator;
		return this;
	}
	
	public Field alias(String alias){
		this.alias = alias;
		return this;
	}
	
	
	/**
	 * {@link #alias}
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}




	private static class AllField extends Field{

		/**   */
		private static final long serialVersionUID = 1L;

		
		
	}
	
	

}
