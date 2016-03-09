/**
 * 文件名：SecureSessionListener.java  
 *  
 * 版本信息：  
 * 日期：2014年10月30日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.security.session;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2014年10月30日 下午4:54:29
 */
public interface SecureSessionListener {

	/**
	 * Session 监听Session创建
	 * @param secureSession
	 */
	void onCreate(SecureSession secureSession);
	
	/**
	 * Session 监听Session销毁
	 * @param secureSession
	 */
	void onExpire(SecureSession secureSession);
	
	/**
	 * Session 监听Session attribute改变销毁
	 * @param secureSession
	 */
	void onChange(SecureSession secureSession);
	
	/**
	 * Session 监听Session touch事件
	 * @param secureSession
	 */
	void onTouch(SecureSession secureSession);
}
