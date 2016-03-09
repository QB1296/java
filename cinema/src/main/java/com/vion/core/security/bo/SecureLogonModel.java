/**
 * 文件名：SecureLogonModel.java  
 *  
 * 版本信息：  
 * 日期：2014年10月27日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.security.bo;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vion.core.security.meta.SecureFuncModels.FuncModel;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2014年10月27日 下午5:20:16
 */
public class SecureLogonModel {
	
	private boolean success;
	
	private String description;
	
	private List<FuncModel> funcModels;
	
	private String sessionId;
	
	private String sessionToken = "jsessionId";
	
	private Map<String, Object> data = new HashMap<String, Object>();
	
	public void putBizData(String key,Object value){
		data.put(key, value);
	}
	
	/**
	 * 获得data 
	 * @return  data data
	 */
	public Map<String, Object> getData() {
		return Collections.unmodifiableMap(data);
	}
	
	/** 
	 * 设置data 
	 * @param data data 
	 */
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
	
	public String getSessionToken() {
		return sessionToken;
	}

	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<FuncModel> getFuncModels() {
		return funcModels;
	}

	public void setFuncModels(List<FuncModel> funcModels) {
		this.funcModels = funcModels;
	}

	
	
	
	
	
}
