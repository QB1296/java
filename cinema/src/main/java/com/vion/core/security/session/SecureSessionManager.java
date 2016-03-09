/**
 * 文件名：SecureSessionManager.java  
 *  
 * 版本信息：  
 * 日期：2014年10月30日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.security.session;

import java.io.Serializable;

/**
 * <b>功能描述</b> <br>
 * session管理器
 * @author YUJB
 * @date 2014年10月30日 上午10:50:00
 */
public interface SecureSessionManager {
	
	/**
	 * 得到session
	 * @param sessionId
	 * @return
	 */
	SecureSession getSession(Serializable sessionId);

	/**
	 * 创建session
	 * @return
	 */
	SecureSession createSession();
	
	/**
	 * touchSession 此方法调用后 session 失效其实时间更新到当前时间 
	 * @param sessionId
	 */
	void touchSession(Serializable sessionId);
}
