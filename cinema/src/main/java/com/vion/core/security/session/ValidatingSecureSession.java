/**
 * 文件名：ValidatingSecureSession.java  
 *  
 * 版本信息：  
 * 日期：2014年10月30日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.security.session;


/**
 * <b>功能描述</b> <br>
 * 需要验证的session
 * @author YUJB
 * @date 2014年10月30日 下午12:54:04
 */
public interface ValidatingSecureSession extends SecureSession {

	  boolean isValid();
}
