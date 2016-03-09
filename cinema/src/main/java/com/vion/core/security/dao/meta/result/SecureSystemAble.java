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
 * @date 2015年3月6日 下午3:32:40
 */
public interface SecureSystemAble extends BasicAble{

	/**
	 * 获得id 
	 * @return  id id
	 */
	public String getId();
	
	/**
	 * 获得name 
	 * @return  name name
	 */
	public String getName();
	
	
	/**
	 * @return
	 */
	public String getCode();
	
	
	/**
	 * @return
	 */
	public String getUrl();
    
    
}
