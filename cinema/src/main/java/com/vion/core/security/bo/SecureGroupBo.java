/**
 * 文件名：SecureRoleBo.java  
 *  
 * 版本信息：  
 * 日期：2014年10月27日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.security.bo;



/**
 * <b>功能描述</b> <br>
 * 权限账户组BO
 * @author YUJB
 * @date 2014年10月27日 上午10:52:56
 */
public class SecureGroupBo {
	
	private String id;
	
    private String name;
    
    private String system;

	/**
	 * 获得id 
	 * @return  id id
	 */
	public String getId() {
		return id;
	}

	/** 
	 * 设置id 
	 * @param id id 
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获得name 
	 * @return  name name
	 */
	public String getName() {
		return name;
	}

	/** 
	 * 设置name 
	 * @param name name 
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获得system 
	 * @return  system system
	 */
	public String getSystem() {
		return system;
	}

	/** 
	 * 设置system 
	 * @param system system 
	 */
	public void setSystem(String system) {
		this.system = system;
	}

    
	
    
}
