/**
 * 文件名： CustomHibernateGeneralDAO.java
 *  
 * 版本信息：  
 * 日期：2015年9月23日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.ganjx.cinema.basic;

import java.io.Serializable;

import org.hibernate.engine.spi.EntityKey;
import org.hibernate.engine.spi.PersistenceContext;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.persister.entity.EntityPersister;

import com.vion.core.domain.entity.IEntity;
import com.vion.core.hibernate.HibernateGeneralDAO;

/**
 * <b>功能描述</b> <br>
 * 
 * @author GANJX
 * @date 2015年9月23日 下午1:07:14
 */
public class CustomHibernateGeneralDAO extends HibernateGeneralDAO{

	/* (non-Javadoc)
	 * @see com.vion.core.hibernate.HibernateGeneralDAO#getCacheEntity(java.lang.Class, java.io.Serializable)
	 */
	@Override
	public <T extends IEntity> Object getCacheEntity(Class<T> clazz,
			Serializable identifier) {
		SessionImplementor sessionImpl = (SessionImplementor) getSession();
		EntityPersister entityPersister = sessionImpl.getFactory().getEntityPersister(clazz.getName());
		PersistenceContext persistenceContext = sessionImpl.getPersistenceContext();
		EntityKey entityKey = new EntityKey(identifier, entityPersister);
		return persistenceContext.getEntity(entityKey);
	}
}
