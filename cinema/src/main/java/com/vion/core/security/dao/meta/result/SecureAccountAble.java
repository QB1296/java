/**
 * 文件名：AccountAble.java  
 *  
 * 版本信息：  
 * 日期：2015年1月9日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.security.dao.meta.result;

import com.vion.core.basic.result.BasicAble;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2015年1月9日 上午10:11:51
 */
public interface SecureAccountAble extends BasicAble{

	/**
	 * {@link #identify}
	 * @return the identify
	 */
	public String getIdentify();

	/**
	 * {@link #loginName}
	 * @return the loginName
	 */
	public String getLoginName();

	/**
	 * {@link #password}
	 * @return the password
	 */
	public String getPassword();

	/**
	 * {@link #name}
	 * @return the name
	 */
	public String getName();

	/**
	 * {@link #system}
	 * @return the system
	 */
	public String getSystem();

	/**
	 * {@link #loginCount}
	 * @return the loginCount
	 */
	public long getLoginCount();

	/**
	 * {@link #isValid}
	 * @return the isValid
	 */
	public String getIsValid();
    
    
}
