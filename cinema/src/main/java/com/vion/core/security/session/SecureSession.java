/**
 * 文件名：SecureSession.java  
 *  
 * 版本信息：  
 * 日期：2014年10月30日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.security.session;

import java.io.Serializable;
import java.util.Date;

/**
 * <b>功能描述</b> <br>
 * session对象
 * @author YUJB
 * @date 2014年10月30日 上午10:46:52
 */
public interface SecureSession {

	/**
	 * 得到session ID
	 * @return
	 */
	public Serializable getId();
	
	/**
	 * 得到session创建时间
	 * @return
	 */
	public Date getStartTimestamp();
	
	/**
	 * 得到最后的访问时间
	 * @return
	 */
	public Date getLastAccessTime();
	
	/**
	 * 初始session的失效时间从头开始计数
	 */
	public void touch();
	
	/**
	 * 得到超时时间
	 * @return
	 */
	public long getTimeout();

	/**
	 * 设置超时时间
	 * @param timeout
	 */
	public void setTimeout(long timeout);
	
	/**
	 * 得到session中的属性
	 * @param key
	 * @return
	 */
	Object getAttribute(Object key);
	
    /**
     * 设置session属性
     * @param key
     * @param value
     */
    void setAttribute(Object key, Object value);
    
    /**
     * 删除属性
     * @param key
     * @return
     */
    Object removeAttribute(Object key);
    
}
