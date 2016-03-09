/**
 * 文件名：SecureContextUtil.java  
 *  
 * 版本信息：  
 * 日期：2014年10月23日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.security;

import com.vion.core.SystemContext;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2014年10月23日 下午3:49:21
 */
public class SecureContextUtil {
	
	private static SecureContext secureContext;
	
	public static synchronized SecureContext getSecureContext(){
		if (secureContext == null) {
			secureContext = SystemContext.getApplicationContext().getBean(SecureContext.class);
		}
		return secureContext;
	}
}
