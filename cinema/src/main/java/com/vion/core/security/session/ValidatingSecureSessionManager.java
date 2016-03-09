/**
 * 文件名：ValidatingSecureSessionManager.java  
 *  
 * 版本信息：  
 * 日期：2014年10月30日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.security.session;


/**
 * <b>功能描述</b> <br>
 * 需要验证的session管理器
 * @author YUJB
 * @date 2014年10月30日 下午2:56:36
 */
public interface ValidatingSecureSessionManager extends SecureSessionManager{
	
	 void validateSessions();

}
