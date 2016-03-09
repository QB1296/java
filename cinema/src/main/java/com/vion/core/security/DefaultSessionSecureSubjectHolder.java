/**
 * 文件名：SessionSecureSubjectHolder.java  
 *  
 * 版本信息：  
 * 日期：2014年10月23日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.security;

import org.springframework.beans.factory.annotation.Autowired;

import com.vion.core.security.meta.SecureSubject;
import com.vion.core.security.session.SecureSession;
import com.vion.core.security.session.SecureSessionManager;

/**
 * <b>功能描述</b> <br>
 * SecureSubject Holder类,通过{@link #getSecureSubject(Object)}得到用户主题
 * @author YUJB
 * @date 2014年10月23日 下午1:25:27
 */
public class DefaultSessionSecureSubjectHolder implements SecureSubjectHolder{
	
	private final static String SUBJECT_KEY = "SUBJECT_KEY"; 
	
	@Autowired
	private SecureSessionManager secureSessionManager;

	@Override
	public SecureSubject getSecureSubject(Object ticket) {
		if (ticket == null || "null".equals(ticket)) {
			return null;
		}
		SecureSession session = secureSessionManager.getSession(ticket.toString());
		if (session != null) {
			Object obj = session.getAttribute(SUBJECT_KEY);
			if(obj != null){
				return (SecureSubject)obj;
			}
			
		}
		return null;
	}

	@Override
	public void setSecureSubject(SecureSubject subject,
			Object ticket) {
		SecureSession session = secureSessionManager.getSession(ticket.toString());
		if (session != null) {
			session.setAttribute(SUBJECT_KEY,subject);
		}
		
	}

}
