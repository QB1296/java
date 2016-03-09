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
 * 权限账户BO
 * @author YUJB
 * @date 2014年10月27日 上午10:52:56
 */
public class SecureAccountBo {
	
	private String identifier;
	
	

	private String loginName;
	
	private String password;
	
    private String name;
    
    private String system;
    
    /** 错误登陆次数  */
    private long loginCount;
    
    /** 是否可用  */
    private String isValid;

    /** 
	 * 获取identifier 
	 * @return identifier identifier 
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**  
	 * 设置identifier  
	 * @param identifier identifier  
	 */
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	/**
	 * 获得loginName 
	 * @return  loginName loginName
	 */
	public String getLoginName() {
		return loginName;
	}

	/** 
	 * 设置loginName 
	 * @param loginName loginName 
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * 获得password 
	 * @return  password password
	 */
	public String getPassword() {
		return password;
	}

	/** 
	 * 设置password 
	 * @param password password 
	 */
	public void setPassword(String password) {
		this.password = password;
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

	/**
	 * 获得loginCount 
	 * @return  loginCount loginCount
	 */
	public long getLoginCount() {
		return loginCount;
	}

	/** 
	 * 设置loginCount 
	 * @param loginCount loginCount 
	 */
	public void setLoginCount(long loginCount) {
		this.loginCount = loginCount;
	}

	/**
	 * 获得isValid 
	 * @return  isValid isValid
	 */
	public String getIsValid() {
		return isValid;
	}

	/** 
	 * 设置isValid 
	 * @param isValid isValid 
	 */
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
    
    
	
    
}
