/**
 * 文件名：UserIdSQLProcessor.java  
 *  
 * 版本信息：  
 * 日期：2014年12月26日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.security;

import org.springframework.stereotype.Component;

import com.vion.core.security.session.SecureSession;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2014年12月26日 下午1:21:31
 */
@Component
public class UserSQLProcessor extends AbstractValueProcessor{
	
	private static final String CURRENT_USER = "#{currentUser}";


	/* (non-Javadoc)
	 * @see com.vion.core.security.AbstractValueProcessor#getRealField(java.lang.Object)*/
	@Override
	public String getRealField(Object value) {
		SecureSession secureSession = SecureContextUtil.getSecureContext().getSecureSession();
		return  "'" + secureSession.getAttribute("currentUser") + "'";
	}

	/* (non-Javadoc)
	 * @see com.vion.core.security.AbstractValueProcessor#canProcess(java.lang.String)*/
	@Override
	public boolean canProcess(String placeholder) {
		return placeholder.equals(CURRENT_USER);
	}

}
