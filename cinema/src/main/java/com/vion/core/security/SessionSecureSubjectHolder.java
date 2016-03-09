/**
 * 文件名：SessionSecureSubjectHolder.java  
 *  
 * 版本信息：  
 * 日期：2014年10月23日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.security;

import javax.servlet.http.HttpSession;

import com.vion.core.security.meta.SecureSubject;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2014年10月23日 下午1:25:27
 */
public class SessionSecureSubjectHolder implements SecureSubjectHolder{
	
	private final static String SUBJECT_KEY = "SUBJECT_KEY"; 

	@Override
	public SecureSubject getSecureSubject(Object ticket) {
		if (ticket instanceof HttpSession) {
			HttpSession session = (HttpSession)ticket;
			return (SecureSubject) session.getAttribute(SUBJECT_KEY);
		}else {
			throw new  RuntimeException("当前subjectHolder为【" + this + "】:" + ticket + "必须传入httpSession");
		}
	}

	@Override
	public void setSecureSubject(SecureSubject subject,
			Object ticket) {
		if (ticket instanceof HttpSession) {
			HttpSession session = (HttpSession)ticket;
			session.setAttribute(SUBJECT_KEY, subject);
		}else {
			throw new  RuntimeException(ticket + "必须传入httpSession");
		}
		
		
	}

}
