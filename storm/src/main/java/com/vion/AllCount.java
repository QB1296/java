/**
 * 文件名：AllCount.java  
 *  
 * 版本信息：  
 * 日期：2015年7月27日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion;

import storm.trident.operation.builtin.Count;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2015年7月27日 下午4:50:43
 */
public class AllCount extends Count{

	/**   */
	private static final long serialVersionUID = 1L;
	

	/* (non-Javadoc)
	 * @see storm.trident.operation.builtin.Count#combine(java.lang.Long, java.lang.Long)*/
	@Override
	public Long combine(Long val1, Long val2) {
		Long combine = super.combine(val1, val2);
		return combine;
	}
	
	
	

}
