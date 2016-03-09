/**
 * 文件名：SpiderLocalSessionFactoryBean.java  
 *  
 * 版本信息：  
 * 日期：2015年2月10日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.spring;

import org.hibernate.SessionFactory;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.event.spi.InitializeCollectionEventListener;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;

import com.vion.core.util.Classes;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2015年2月10日 下午7:34:46
 */
public class SpiderLocalSessionFactoryBean extends LocalSessionFactoryBean{
	
	private static Class<? extends InitializeCollectionEventListener> clazz;
	
	static {
		ClassLoader cl = SpiderLocalSessionFactoryBean.class.getClassLoader();
		try {
			@SuppressWarnings("unchecked")
			Class<? extends InitializeCollectionEventListener> clazz = (Class<? extends InitializeCollectionEventListener>) cl.loadClass("com.vion.core.hibernate.SpiderSecureInitializeCollectionEventListener");
			SpiderLocalSessionFactoryBean.clazz = clazz;
		}
		catch (ClassNotFoundException ex) {
			clazz = null;
		}
	}

	/* (non-Javadoc)
	 * @see org.springframework.orm.hibernate4.LocalSessionFactoryBean#buildSessionFactory(org.springframework.orm.hibernate4.LocalSessionFactoryBuilder)*/
	@Override
	protected SessionFactory buildSessionFactory(LocalSessionFactoryBuilder sfb) {
		sfb.setEntityNotFoundDelegate(new SpiderEntityNotFoundDelegate());
		SessionFactory sessionFactory = super.buildSessionFactory(sfb);
	    EventListenerRegistry registry = ((SessionFactoryImpl) sessionFactory).getServiceRegistry().getService(
		        EventListenerRegistry.class);
	    if (clazz != null) {
	    	registry.getEventListenerGroup(EventType.INIT_COLLECTION).appendListener(Classes.constructorNewInstance(clazz));
		}
		return sessionFactory;
	}
	
	
	
}
