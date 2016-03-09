/**
 * 文件名：SecureCurrentSessionIdHolder.java  
 *  
 * 版本信息：  
 * 日期：2014年11月3日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.security.session;

import com.vion.core.ResourcesHolder;

/**
 * <b>功能描述</b> <br>
 * 保存当前的sessionId
 * @see SecureAbstractTouchSessionFilter
 * @author YUJB
 * @date 2014年11月3日 下午2:01:30
 */
public class SecureCurrentSessionIdHolder {
	
	private static String SESSION_ID = "SECURE_SESSION_ID";
	
	/**
	 * 得到当前的sessionId
	 * @return
	 */
	public static String getSessionId(){
		return ResourcesHolder.get(SESSION_ID,String.class);
	}
	
	/**
	 * sessionId为null时 unbind sessionID
	 * @param sessionId
	 */
	public static void setSessoinId(String sessionId){
		if (sessionId == null) {
			ResourcesHolder.unbind(SESSION_ID);
		}else {
			ResourcesHolder.bind(SESSION_ID, sessionId);
		}
	}
}
