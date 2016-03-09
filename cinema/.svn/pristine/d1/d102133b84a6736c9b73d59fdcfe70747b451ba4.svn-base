/**
 * 文件名：SpiderSecureInitializeCollectionEvent.java  
 *  
 * 版本信息：  
 * 日期：2015年2月28日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.collection.spi.PersistentCollection;
import org.hibernate.event.spi.InitializeCollectionEvent;
import org.hibernate.event.spi.InitializeCollectionEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vion.core.security.SecureCacheSQLExpressionFilter;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2015年2月28日 上午10:01:34
 */
public class SpiderSecureInitializeCollectionEventListener implements InitializeCollectionEventListener{

	/**   */
	private static final long serialVersionUID = 1L;
	
	/** 日志  */
	protected transient static Logger logger = LoggerFactory.getLogger(SpiderSecureInitializeCollectionEventListener.class);
	

	/* (non-Javadoc)
	 * @see org.hibernate.event.spi.InitializeCollectionEventListener#onInitializeCollection(org.hibernate.event.spi.InitializeCollectionEvent)*/
	@Override
	public void onInitializeCollection(InitializeCollectionEvent event)
			throws HibernateException {
		PersistentCollection collection = event.getCollection();
		SecureCacheSQLExpressionFilter filter = new SecureCacheSQLExpressionFilter();
		filter.onFilterCollection(collection);
	}
	

}
