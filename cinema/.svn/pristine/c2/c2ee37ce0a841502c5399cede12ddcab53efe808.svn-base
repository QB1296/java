/**
 * 文件名：SessionSecureSubjectHolder.java  
 *  
 * 版本信息：  
 * 日期：2014年10月23日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.security;

import java.util.HashMap;
import java.util.Map;

import com.vion.core.security.meta.SecureSubject;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2014年10月23日 下午1:25:27
 */
public class PersistenceSecureSubjectHolder implements SecureSubjectHolder{
	
	private static Map<Object, SecureSubject> subjectHolderMapper = new HashMap<Object, SecureSubject>();
	
	@Override
	public SecureSubject getSecureSubject(Object ticket) {
		return subjectHolderMapper.get(ticket);
	}
	

	@Override
	public void setSecureSubject(SecureSubject subject,
			Object ticket) {
		subjectHolderMapper.put(ticket, subject);
		
	}

}
