/**
 * 文件名：SecureCacheSQLExpressionFilter.java  
 *  
 * 版本信息：  
 * 日期：2015年3月4日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.security;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.vion.core.ResourcesHolder;
import com.vion.core.SystemContext;
import com.vion.core.security.session.SecureCurrentSessionIdHolder;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2015年3月4日 下午2:55:47
 */
public class SecureCacheSQLExpressionFilter {
	
	/** 日志  */
	protected transient static Logger logger = LoggerFactory.getLogger(ResourcesHolder.class);
	
	
	
	/* (non-Javadoc)
	 * @see org.hibernate.event.spi.InitializeCollectionEventListener#onInitializeCollection(org.hibernate.event.spi.InitializeCollectionEvent)*/
	public void onFilterCollection(Object bag)
			throws HibernateException {
		ApplicationContext ac = SystemContext.getApplicationContext();
		if (ac ==  null) {
			return;
		}
		SQLRuleFilterProcessor processor = ac.getBean(SQLRuleFilterProcessor.class);
		String id = SecureCurrentSessionIdHolder.getSessionId();
		filterCollection(bag,processor,id);
		
	}
	
	
	private void filterCollection(Object sources,SQLRuleFilterProcessor processor,String id){
		if (id != null) {
			processor.cacheFilter(sources, id);
		}
	}
}
