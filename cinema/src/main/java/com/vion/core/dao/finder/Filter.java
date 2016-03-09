/**
 * 文件名：Filter.java  
 *  
 * 版本信息：  
 * 日期：2013-6-5  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.dao.finder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2014-6-5 下午01:17:53
 */
public class Filter extends IFilter{
	
	/**
	 * @param property
	 * @param value
	 * @param opEqual
	 */
	public Filter(String property, Object value, int operator) {
		this.property = property;
		this.value = value;
		this.operator = operator;
	}

	public static Filter equal(String property, Object value) {
		return new Filter(property, value, OP_EQUAL);
	}
	
	public static Filter equal(Field field, Object value) {
		return new Filter(field.getProperty(), value, OP_EQUAL);
	}

	/**
	 * 
	 */
	public static Filter lessThan(String property, Object value) {
		return new Filter(property, value, OP_LESS_THAN);
	}

	/**
	 * 
	 */
	public static Filter greaterThan(String property, Object value) {
		return new Filter(property, value, OP_GREATER_THAN);
	}

	/**
	 * 
	 */
	public static Filter lessOrEqual(String property, Object value) {
		return new Filter(property, value, OP_LESS_OR_EQUAL);
	}

	/**
	 * 
	 */
	public static Filter greaterOrEqual(String property, Object value) {
		return new Filter(property, value, OP_GREATER_OR_EQUAL);
	}

	/**
	 * 
	 */
	public static Filter in(String property, Collection<?> value) {
		return new Filter(property, value, OP_IN);
	}

	/**
	 */
	public static Filter in(String property, Object... value) {
		return new Filter(property, value, OP_IN);
	}

	/**
	 */
	public static Filter notIn(String property, Collection<?> value) {
		return new Filter(property, value, OP_NOT_IN);
	}

	/**
	 */
	public static Filter notIn(String property, Object... value) {
		return new Filter(property, value, OP_NOT_IN);
	}

	/**
	 */
	public static Filter like(String property, String value) {
		return new Filter(property, value, OP_LIKE);
	}

	/**
	 */
	public static Filter ilike(String property, String value) {
		return new Filter(property, value, OP_ILIKE);
	}

	/**
	 */
	public static Filter notEqual(String property, Object value) {
		return new Filter(property, value, OP_NOT_EQUAL);
	}

	/**
	 */
	public static Filter isNull(String property) {
		return new Filter(property, true, OP_NULL);
	}

	/**
	 */
	public static Filter isNotNull(String property) {
		return new Filter(property, true, OP_NOT_NULL);
	}

	/**
	 */
	public static Filter isEmpty(String property) {
		return new Filter(property, true, OP_EMPTY);
	}

	/**
	 */
	public static Filter isNotEmpty(String property) {
		return new Filter(property, true, OP_NOT_EMPTY);
	}

	/**
	 */
	public static Filter and(Filter... filters) {
		Filter filter = new Filter("AND", null, OP_AND);
		for (Filter f : filters) {
			filter.add(f);
		}
		return filter;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void add(Filter filter) {
		if (value == null || !(value instanceof List)) {
			value = new ArrayList();
		}
		((List) value).add(filter);
	}
	
	
}
