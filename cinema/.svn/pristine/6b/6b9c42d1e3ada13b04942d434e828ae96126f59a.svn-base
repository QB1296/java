/**
 * 文件名：TrafficSubject.java  
 *  
 * 版本信息：  
 * 日期：2014年8月11日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.security.meta;

import java.util.List;

import com.vion.core.security.rule.FilterRule;

/**
 * <b>功能描述</b> <br>
 * 交通平台权限主体类
 * @author YUJB
 * @date 2014年8月11日 下午4:14:39
 */
public class SecureSubject implements Subject<SecureFuncModels,List<FilterRule>>{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<FilterRule> rules;
	
	private SecureFuncModels funcModels;
	
	
	public SecureSubject() {
		// TODO Auto-generated constructor stub
	}
	
	public SecureSubject(List<FilterRule> rules, SecureFuncModels funcModels) {
		super();
		this.rules = rules;
		this.funcModels = funcModels;
	}

	@Override
	public boolean isPermitted(String permissionId) {
		return false;
	}

	@Override
	public SecureFuncModels getFuncPermissions() {
		return funcModels;
	}


	@Override
	public List<FilterRule> getDataPermissions() {
		return rules;
	}
	


}
