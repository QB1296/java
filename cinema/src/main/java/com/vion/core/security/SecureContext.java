/**
 * 文件名：SecureContext.java  
 *  
 * 版本信息：  
 * 日期：2014年10月23日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.security;

import com.vion.core.SystemContext;
import com.vion.core.model.ReplyModel;
import com.vion.core.security.meta.SecureSubject;
import com.vion.core.security.session.SecureCurrentSessionIdHolder;
import com.vion.core.security.session.SecureSession;
import com.vion.core.security.session.SecureSessionManager;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2014年10月23日 下午3:49:44
 */
public class SecureContext {
	
	
	private SQLRuleFilterProcessor sqlRuleFilterProcessor;

	
	public SecureSubject getSecureSubject(Object ticket) {
		return sqlRuleFilterProcessor.getSecureSubjectHolder().getSecureSubject(ticket);
	}

	
	public SecureSession getSecureSession(String ticket){
		SecureSessionManager bean = SystemContext.getApplicationContext().getBean(SecureSessionManager.class);
		return bean.getSession(ticket);
	}
	
	public SecureSession getSecureSession(){
		SecureSessionManager bean = SystemContext.getApplicationContext().getBean(SecureSessionManager.class);
		String sessionId = SecureCurrentSessionIdHolder.getSessionId();
		if (sessionId == null) {
			return bean.createSession();
		}
		return bean.getSession(sessionId);
	}
	
	public String processSql(String sql, Object ticket) {
		return sqlRuleFilterProcessor.process(sql,ticket);
	}

	
	public String addAccount(String Identifier, String accountName, String system) {
		return null;
	}

	
	public ReplyModel authentication(String accountName, String pwd,String system) {
		return null;
	}


	public SQLRuleFilterProcessor getSqlRuleFilterProcessor() {
		return sqlRuleFilterProcessor;
	}


	public void setSqlRuleFilterProcessor(
			SQLRuleFilterProcessor sqlRuleFilterProcessor) {
		this.sqlRuleFilterProcessor = sqlRuleFilterProcessor;
	}
	
	

}
