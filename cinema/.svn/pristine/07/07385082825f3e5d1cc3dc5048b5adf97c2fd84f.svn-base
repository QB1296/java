/**
 * 文件名：SecureSessionProxy.java  
 *  
 * 版本信息：  
 * 日期：2014年11月3日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.security.session;

import java.io.Serializable;
import java.util.Date;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2014年11月3日 上午10:35:15
 */
public class SecureSessionProxy implements SecureSession{
	
	private SecureSession secureSession;
	
	private DefaultSecureSessionManager secureSessionManager;

	public SecureSessionProxy(SecureSession secureSession) {
		super();
		this.secureSession = secureSession;
	}

	@Override
	public Serializable getId() {
		return secureSession.getId();
	}

	@Override
	public Date getStartTimestamp() {
		return secureSession.getStartTimestamp();
	}

	@Override
	public Date getLastAccessTime() {
		return secureSession.getLastAccessTime();
	}

	@Override
	public void touch() {
		secureSession.touch();
		updateSession(secureSession);
	}

	@Override
	public long getTimeout() {
		return secureSession.getTimeout();
	}

	@Override
	public void setTimeout(long timeout) {
		secureSession.setTimeout(timeout);
		updateSession(secureSession);
	}

	@Override
	public Object getAttribute(Object key) {
		return secureSession.getAttribute(key);
	}

	@Override
	public void setAttribute(Object key, Object value) {
		secureSession.setAttribute(key, value);
		updateSession(secureSession);
	}

	@Override
	public Object removeAttribute(Object key) {
		secureSession.removeAttribute(key);
		updateSession(secureSession);
		return this;
	}

	
	private void updateSession(SecureSession session){
		SecureSessionRepository repository = secureSessionManager.getSecureSessionRepository();
		repository.updateSession(session);
	}
	

	public DefaultSecureSessionManager getSecureSessionManager() {
		return secureSessionManager;
	}

	public void setSecureSessionManager(
			DefaultSecureSessionManager secureSessionManager) {
		this.secureSessionManager = secureSessionManager;
	}



}
