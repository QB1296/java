/**
 * 文件名： CustomSecureSubjectHolder.java
 *  
 * 版本信息：  
 * 日期：2015年3月27日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.ganjx.cinema.basic.security;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.vion.core.security.SecureSubjectHolder;
import com.vion.core.security.meta.SecureSubject;

/**
 * <b>功能描述</b> <br>
 * 
 * @author GANJX
 * @date 2015年3月27日 上午9:48:21
 */
public class CustomSecureSubjectHolder implements SecureSubjectHolder,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired(required=false)
	HttpServletRequest request;
	
	@Override
	public SecureSubject getSecureSubject(Object ticket) {
		// TODO Auto-generated method stub
//		return (SecureSubject) request.getSession().getAttribute(SessionUtils.SUBJECT_KEY);
		return null;
	}

	@Override
	public void setSecureSubject(SecureSubject subject, Object ticket) {
		// TODO Auto-generated method stub
//		HttpSession session = request.getSession();
//		session.setAttribute(SessionUtils.SUBJECT_KEY, subject);
	}

}
