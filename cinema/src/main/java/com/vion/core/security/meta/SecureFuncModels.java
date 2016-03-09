/**
 * 文件名：SecureFuncModels.java  
 *  
 * 版本信息：  
 * 日期：2014年10月28日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.security.meta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2014年10月28日 上午9:38:21
 */
public class SecureFuncModels implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<FuncModel> funcModels;
	
	@JsonIgnore
	private static String MENU_TYPE = "MENU";
	
	
	public List<FuncModel> getFuncModels() {
		return funcModels;
	}

	
	public void  addFuncModel(FuncModel funcModel) {
		if (funcModels == null) {
			funcModels  = new ArrayList<FuncModel>();
		}
		funcModels.add(funcModel);
	}

	public void setFuncModels(List<FuncModel> funcModels) {
		this.funcModels = funcModels;
	}
	
	@JsonIgnore
	public List<FuncModel> getNoMenuFuncModles(){
		SecureFuncModels secureFuncModels = new SecureFuncModels();
		if (funcModels != null) {
			for (FuncModel funcModel : funcModels) {
				if (!funcModel.getType().equals(MENU_TYPE)) {
					secureFuncModels.addFuncModel(funcModel);
				}
			}
		}
		return secureFuncModels.getFuncModels();
	}
	
	

	public class FuncModel{
		private String id;
		
		private String code;
		
		private String type;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}
	}
	
	
	
	
}
