/**
 * 文件名：HibernateEntityUtils.java  
 *  
 * 版本信息：  
 * 日期：2015年2月28日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.AbstractEntityPersister;

import com.vion.core.SystemContext;
import com.vion.core.exception.NoCriteriaWritingException;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2015年2月28日 下午4:37:04
 */
public class HibernateEntityUtils {
	
	public static String getTable(Class<?> entityClazz){
		AbstractEntityPersister persister = getAbstractEntityPersister(entityClazz);
		return persister.getTableName();
	}
	
	
	public static String getField(Class<?> entityClazz,String filedName){
		AbstractEntityPersister persister = getAbstractEntityPersister(entityClazz);
		String[] id = persister.getIdentifierColumnNames();
		if (id[0].equals(filedName)) {
			return persister.getIdentifierPropertyName();
		}
		int length = persister.getPropertyNames().length;
		for (int j = 0; j < length; j++) {
			String[] propertyNames = persister.getPropertyColumnNames(j);
			if (propertyNames.length == 1) {
				if(filedName.equals(propertyNames[0])){
					return persister.getPropertyNames()[j];
				}
			}
		}
		return null;
	}
	
	
	public static String[] getColumn(Class<?> entityClazz,String columnName){
		AbstractEntityPersister persister = getAbstractEntityPersister(entityClazz);
		return persister.getPropertyColumnNames(columnName);
	}
	
	
	public static AbstractEntityPersister getAbstractEntityPersister(Class<?> entityClazz){
		ClassMetadata hibernateMetadata = getSessionFactory().getClassMetadata(entityClazz);
		if (hibernateMetadata == null){
			throw new NoCriteriaWritingException(entityClazz + "没有被hibernate扫描");
		}
		if (hibernateMetadata instanceof AbstractEntityPersister){
			AbstractEntityPersister persister = (AbstractEntityPersister) hibernateMetadata;
			return persister;
		}
		return null;
	}
	
	
	private static SessionFactory getSessionFactory(){
		return  SystemContext.getApplicationContext().getBean(SessionFactory.class);
	}
}
