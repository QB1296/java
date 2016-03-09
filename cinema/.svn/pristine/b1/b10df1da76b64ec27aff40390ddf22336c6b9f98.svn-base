/**
 * 文件名：SecureSubjectHolder.java  
 *  
 * 版本信息：  
 * 日期：2014年10月23日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.security;

import com.vion.core.security.meta.SecureSubject;

/**
 * <b>功能描述</b> <br>
 * 权限主体holder类
 * @author YUJB
 * @date 2014年10月23日 下午1:14:54
 */
public interface SecureSubjectHolder {

	/**
	 * 通过票据得到用户主题
	 * @param ticket 票据
	 * @return 
	 */
	public SecureSubject getSecureSubject(Object ticket);
	
	/**
	 * 设置用户主题
	 * @param subject 用户主题
	 * @param ticket 票据
	 */
	public void setSecureSubject(SecureSubject subject,Object ticket);
	
}
