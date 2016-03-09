/**
 * 文件名：Sort.java  
 *  
 * 版本信息：  
 * 日期：2013-6-5  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */

package com.vion.core.dao.finder;


/**
 * <b>功能描述</b> <br>
 * 排序
 * @author YUJB
 * @date 2014-6-5 下午01:08:33
 */
public class Sort {

	/** 排序字段名称  */
	protected String property;
	
	/** 升序,降序  */
	protected Order order;
	
	public static enum Order{
		desc,asc
	}
	
	/**
	 * @param property
	 */
	public Sort(String property) {
		this.property =property;
		this.order = Order.asc;
	}

	/**
	 * @param property2
	 * @param order
	 */
	public Sort(String property, Order order) {
		this.property = property;
		this.order = order;
	}

	public static Sort asc(String property) {
		return new Sort(property);
	}

	public static Sort desc(String property) {
		return new Sort(property, Order.desc);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public boolean isDesc() {
		return order.equals(Order.desc);
	}
	
	

}
