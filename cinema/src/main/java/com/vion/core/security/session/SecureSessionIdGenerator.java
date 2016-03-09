/**
 * 文件名：SecureSessionIdGenerator.java  
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
 * sessionId生成器
 * @author YUJB
 * @date 2014年10月30日 上午11:47:29
 */
public interface SecureSessionIdGenerator {

	Serializable generateId();
}
