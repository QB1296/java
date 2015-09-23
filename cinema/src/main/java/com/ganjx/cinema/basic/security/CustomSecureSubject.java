/**
 * 文件名： CustomSecureSubject.java
 *  
 * 版本信息：  
 * 日期：2015年3月27日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.ganjx.cinema.basic.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ganjx.cinema.entity.TFunc;
import com.ganjx.cinema.entity.TFuncAdmin;
import com.ganjx.cinema.vo.UserVo;
import com.vion.core.security.meta.SecureFuncModels;
import com.vion.core.security.meta.SecureSubject;
import com.vion.core.security.rule.FilterRule;

/**
 * <b>功能描述</b> <br>
 * 
 * @author GANJX
 * @date 2015年3月27日 下午2:46:47
 */
public class CustomSecureSubject extends SecureSubject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<TFunc> funcs;
	private List<TFuncAdmin> funcAdmins;	
	private List<String> groups;	
	private UserVo user;	
	private Map<String, Object> bizMap;
	
	public CustomSecureSubject(){
		
	}
	
	public CustomSecureSubject(List<FilterRule> rules,
			SecureFuncModels funcModels) {
		super(rules, funcModels);
		// TODO Auto-generated constructor stub
	}

	public List<TFunc> getFuncs() {
		return funcs;
	}

	public void setFuncs(List<TFunc> funcs) {
		this.funcs = funcs;
	}

	public List<TFuncAdmin> getFuncAdmins() {
		return funcAdmins;
	}

	public void setFuncAdmins(List<TFuncAdmin> funcAdmins) {
		this.funcAdmins = funcAdmins;
	}

	public List<String> getGroups() {
		if(groups == null){
			groups = new ArrayList<String>();
		}
		return groups;
	}

	public void setGroups(List<String> groups) {
		this.groups = groups;
	}

	public UserVo getUser() {
		return user;
	}

	public void setUser(UserVo user) {
		this.user = user;
	}

	public Map<String, Object> getBizMap() {
		return bizMap;
	}

	public void setBizMap(Map<String, Object> bizMap) {
		this.bizMap = bizMap;
	}
	
	public void put(String key,Object object) {
		if(this.bizMap == null){
			this.bizMap = new HashMap<String, Object>();
		}
		bizMap.put(key, object);
	}
	
}
