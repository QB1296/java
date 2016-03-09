/**
 * 文件名：BasicSecureFilter.java  
 *  
 * 版本信息：  
 * 日期：2015年3月6日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.security.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.vion.core.dao.finder.Field;
import com.vion.core.dao.finder.IFilter;
import com.vion.core.exception.NoCriteriaWritingException;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2015年3月6日 下午2:24:35
 */
public class BasicSecureFilter extends IFilter{

	private BasicSecureField basicField;
	
	/**
	 * @param property
	 * @param value
	 * @param opEqual
	 */
	public BasicSecureFilter(String property, Object value, int operator) {
		this.property = property;
		this.value = value;
		this.operator = operator;
	}
	
	/**
	 * @param property
	 * @param value
	 * @param opEqual
	 */
	public BasicSecureFilter(BasicSecureField field, Object value, int operator) {
		this.property = field.getProperty();
		this.basicField = field;
		this.value = value;
		this.operator = operator;
		if (field.getOperator() != null) {
			this.fieldOperator = field.getOperator();
			if (Field.OP_COUNT == fieldOperator) {
				if (!(this.value instanceof Long)) {
					throw new NoCriteriaWritingException("count操作value必须是Long类型");
				}
			}
		}
	}

	
	public static BasicSecureFilter equal(BasicSecureField field, Object value) {
		return new BasicSecureFilter(field, value, OP_EQUAL);
	}

	/**
	 * 
	 */
	public static BasicSecureFilter lessThan(BasicSecureField field, Object value) {
		return new BasicSecureFilter(field, value, OP_LESS_THAN);
	}

	/**
	 * 
	 */
	public static BasicSecureFilter greaterThan(BasicSecureField property, Object value) {
		return new BasicSecureFilter(property, value, OP_GREATER_THAN);
	}

	/**
	 * 
	 */
	public static BasicSecureFilter lessOrEqual(BasicSecureField property, Object value) {
		return new BasicSecureFilter(property, value, OP_LESS_OR_EQUAL);
	}

	/**
	 * 
	 */
	public static BasicSecureFilter greaterOrEqual(BasicSecureField property, Object value) {
		return new BasicSecureFilter(property, value, OP_GREATER_OR_EQUAL);
	}

	/**
	 * 
	 */
	public static BasicSecureFilter in(BasicSecureField property, Collection<?> value) {
		return new BasicSecureFilter(property, value, OP_IN);
	}

	/**
	 */
	public static BasicSecureFilter in(BasicSecureField property, Object... value) {
		return new BasicSecureFilter(property, value, OP_IN);
	}

	/**
	 */
	public static BasicSecureFilter notIn(BasicSecureField property, Collection<?> value) {
		return new BasicSecureFilter(property, value, OP_NOT_IN);
	}

	/**
	 */
	public static BasicSecureFilter notIn(BasicSecureField property, Object... value) {
		return new BasicSecureFilter(property, value, OP_NOT_IN);
	}

	/**
	 */
	public static BasicSecureFilter like(BasicSecureField property, String value) {
		return new BasicSecureFilter(property, value, OP_LIKE);
	}

	/**
	 */
	public static BasicSecureFilter ilike(BasicSecureField property, String value) {
		return new BasicSecureFilter(property, value, OP_ILIKE);
	}

	/**
	 */
	public static BasicSecureFilter notEqual(BasicSecureField property, Object value) {
		return new BasicSecureFilter(property, value, OP_NOT_EQUAL);
	}

	/**
	 */
	public static BasicSecureFilter isNull(BasicSecureField property) {
		return new BasicSecureFilter(property, true, OP_NULL);
	}

	/**
	 */
	public static BasicSecureFilter isNotNull(BasicSecureField property) {
		return new BasicSecureFilter(property, true, OP_NOT_NULL);
	}

	/**
	 */
	public static BasicSecureFilter isEmpty(String property) {
		return new BasicSecureFilter(property, true, OP_EMPTY);
	}

	/**
	 */
	public static BasicSecureFilter isNotEmpty(String property) {
		return new BasicSecureFilter(property, true, OP_NOT_EMPTY);
	}

	/**
	 */
	public static BasicSecureFilter and(BasicSecureFilter... filters) {
		BasicSecureFilter filter = new BasicSecureFilter("AND", null, OP_AND);
		for (BasicSecureFilter f : filters) {
			filter.add(f);
		}
		return filter;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void add(BasicSecureFilter filter) {
		if (value == null || !(value instanceof List)) {
			value = new ArrayList();
		}
		((List) value).add(filter);
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
	
	
	/**
	 * {@link #property}	
	 * @param property the property to set
	 */
	public void setProperty(String property) {
		this.property = property;
	}
}
