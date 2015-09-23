/**
 * 文件名： CustomEntityInterceptor.java
 *  
 * 版本信息：  
 * 日期：2015年3月27日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.ganjx.cinema.basic.security;

import java.lang.reflect.Method;

import org.hibernate.EmptyInterceptor;

import com.ganjx.cinema.dao.GenericBoDao;
import com.ganjx.cinema.util.SubjectUtils;
import com.vion.core.SystemContext;
import com.vion.core.security.SQLRuleFilterProcessor;
import com.vion.core.util.Classes;

/**
 * <b>功能描述</b> <br>
 * 
 * @author GANJX
 * @date 2015年3月27日 上午11:47:52
 */
public class CustomEntityInterceptor extends EmptyInterceptor{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see org.hibernate.EmptyInterceptor#onPrepareStatement(java.lang.String)
	 */
	@Override
	public String onPrepareStatement(String sql) {
		// TODO Auto-generated method stub
		if(SystemContext.getApplicationContext() == null){
			return sql;
		}
		
		SQLRuleFilterProcessor processor = SystemContext.getApplicationContext().getBean(SQLRuleFilterProcessor.class);
		CustomSecureSubject currentSubject = SubjectUtils.getCurrentSubject();
		if (currentSubject != null && isAccess()) {
			return processor.process(sql,null);
		}else {
			return sql;
		}
	}
	
	/**
	 * @return
	 */
	public boolean isAccess(){
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		for (StackTraceElement stackTraceElement : stackTrace) {
			String className = stackTraceElement.getClassName();
			if(!className.startsWith(GenericBoDao.class.getPackage().getName()))
				continue;
			String methodName = stackTraceElement.getMethodName();
			Class<?> clazz = null;
			try {
				clazz = Class.forName(className);
				Method method = Classes.getMethodByName(clazz, methodName);
				//方法为null     类true
				//方法为true     类为true
				if(method != null && clazz != null){
					Security annotation = method.getAnnotation(Security.class);
					Security clazzSec = clazz.getAnnotation(Security.class);
					if(annotation == null && clazzSec != null && clazzSec.filter()){
						return true;
					}
					if(annotation != null && annotation.filter()){
						return true;
					}
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		return false;
	}
	
}
