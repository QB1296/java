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
 * 数据权限BO
 * @author YUJB
 * @date 2014年10月27日 上午10:52:56
 */
public class SecureDataBo {
	
	private String id;
	
    private String name;
    
    private String system;
    
    private String moudleCode;
    
    private String moudleName;
    
    
	/**
	 * 获得moudleCode 
	 * @return  moudleCode moudleCode
	 */
	public String getMoudleCode() {
		return moudleCode;
	}

	/** 
	 * 设置moudleCode 
	 * @param moudleCode moudleCode 
	 */
	public void setMoudleCode(String moudleCode) {
		this.moudleCode = moudleCode;
	}

	/**
	 * 获得moudleName 
	 * @return  moudleName moudleName
	 */
	public String getMoudleName() {
		return moudleName;
	}

	/** 
	 * 设置moudleName 
	 * @param moudleName moudleName 
	 */
	public void setMoudleName(String moudleName) {
		this.moudleName = moudleName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

    
}
