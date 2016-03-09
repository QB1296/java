/**
 * 文件名：DefaultSecureSessionFactory.java  
 *  
 * 版本信息：  
 * 日期：2014年10月30日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.security.session;

import java.io.Serializable;

/**
 * <b>功能描述</b> <br>
 * 默认的session工厂,生产{@link DefaultSeucreSession}
 * @author YUJB
 * @date 2014年10月30日 下午1:30:28
 */
public class DefaultSecureSessionFactory implements SecureSessionFactory{
	
	private SecureSessionIdGenerator sessionIdGenerator;
	
	@Override
	public SecureSession createSession() {
		if (sessionIdGenerator == null) {
			sessionIdGenerator = new UUIDSecureSessionIdGenerator();
		}
		Serializable generateId = sessionIdGenerator.generateId();
		DefaultSeucreSession session = new DefaultSeucreSession();
		session.setId(generateId);
		return session;
	}

	public SecureSessionIdGenerator getSessionIdGenerator() {
		return sessionIdGenerator;
	}

	public void setSessionIdGenerator(SecureSessionIdGenerator sessionIdGenerator) {
		this.sessionIdGenerator = sessionIdGenerator;
	}
	
	

}
