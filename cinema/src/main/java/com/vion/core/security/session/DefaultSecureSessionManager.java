/**
 * 文件名：DefaultSecureSessionManager.java  
 *  
 * 版本信息：  
 * 日期：2014年10月30日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.security.session;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2014年10月30日 下午2:31:35
 */
public class DefaultSecureSessionManager implements ValidatingSecureSessionManager,InitializingBean{
	
	private static Logger logger = LoggerFactory.getLogger(SecureCompositeTouchSessionFilter.class);
	
	/** session仓库  */
	private SecureSessionRepository secureSessionRepository;
	
	/** session监听器  */
	private SecureSessionListener[] secureSessionListeners;
	
	/** session超时校验器  */
	private SecureSessionValidator secureSessionValidator;
	
	/** 单位为分钟,默认为30分钟  */
	private Long timeout = 30L;
	

	@Override
	public SecureSession getSession(Serializable sessionId) {
		SecureSession session = secureSessionRepository.getSession(sessionId);
		return session;
	}

	@Override
	public SecureSession createSession() {
		secureSessionValidator.start();
		SecureSession session = secureSessionRepository.createSession();
		SecureCurrentSessionIdHolder.setSessoinId(String.valueOf(session.getId()));
		session.setTimeout(timeout);
		SecureSessionProxy proxy = new SecureSessionProxy(session);
		proxy.setSecureSessionManager(this);
		logger.info("创建新的session[{}]",new String[]{String.valueOf(proxy.getId())});
		return proxy;
	}
	
	
	@Override
	public void touchSession(Serializable sessionId) {
		SecureSession session = getSession(false);
		if (session != null) {
			session.touch();
		}
	}
	

	@Override
	public void validateSessions() {

        List<SecureSession> activeSessions = secureSessionRepository.getSessions();

        if (activeSessions != null && !activeSessions.isEmpty()) {
            for (SecureSession session : activeSessions) {
            	if (session instanceof ValidatingSecureSession) {
					boolean valid = ((ValidatingSecureSession)session).isValid();
					if (!valid) {
						if (secureSessionListeners != null) {
							for (SecureSessionListener listener: secureSessionListeners) {
								listener.onExpire(session);
							}
						}
						secureSessionRepository.remove(session.getId());
						logger.info("session失效[{}]",new String[]{String.valueOf(session.getId())});
					}
				}
            }
        }

	}
	
	public SecureSessionRepository getSecureSessionRepository() {
		return secureSessionRepository;
	}
	
	public void setSecureSessionRepository(
			SecureSessionRepository secureSessionRepository) {
		this.secureSessionRepository = secureSessionRepository;
	}

	public SecureSessionListener[] getSecureSessionListeners() {
		return secureSessionListeners;
	}

	public void setSecureSessionListeners(
			SecureSessionListener[] secureSessionListeners) {
		this.secureSessionListeners = secureSessionListeners;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		secureSessionValidator = new SecureSessionValidator(this);
		
	}

	public SecureSessionValidator getSecureSessionValidator() {
		return secureSessionValidator;
	}

	public void setSecureSessionValidator(
			SecureSessionValidator secureSessionValidator) {
		this.secureSessionValidator = secureSessionValidator;
	}

	public Long getTimeout() {
		return timeout;
	}

	public void setTimeout(Long timeout) {
		this.timeout = timeout;
	}

	

	
	
}
