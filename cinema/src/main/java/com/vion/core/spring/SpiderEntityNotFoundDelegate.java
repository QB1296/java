/**
 * 文件名：SpiderEntityNotFoundDelegate.java  
 *  
 * 版本信息：  
 * 日期：2015年2月10日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.spring;

import java.io.Serializable;

import org.hibernate.proxy.EntityNotFoundDelegate;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2015年2月10日 下午7:35:57
 */
public class SpiderEntityNotFoundDelegate implements EntityNotFoundDelegate{

	/* (non-Javadoc)
	 * @see org.hibernate.proxy.EntityNotFoundDelegate#handleEntityNotFound(java.lang.String, java.io.Serializable)*/
	@Override
	public void handleEntityNotFound(String entityName, Serializable id) {
		
	}

}
