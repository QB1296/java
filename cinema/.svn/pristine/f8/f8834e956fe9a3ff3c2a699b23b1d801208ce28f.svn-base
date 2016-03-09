/**
 * 文件名：RootEntityModel.java  
 *  
 * 版本信息：  
 * 日期：2014年8月12日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.security.meta.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.digester.annotations.rules.ObjectCreate;
import org.apache.commons.digester.annotations.rules.SetNext;
import org.apache.commons.digester.annotations.rules.SetProperty;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2014年8月12日 上午11:49:08
 */
@ObjectCreate(pattern="premission-mapping/moudle/root-entity")
public class RootEntityModel {

	@SetProperty(attributeName="name",pattern="premission-mapping/moudle/root-entity")
	private String name;
	
	@SetProperty(attributeName="table",pattern="premission-mapping/moudle/root-entity")
	private String table;
	
	private List<EntityModel> subEntityModels;
	
	
	public List<EntityModel> getSubEntityModels() {
		if (subEntityModels == null) {
			return null;
		}
		return Collections.unmodifiableList(subEntityModels);
	}
	
	public void setSubEntityModels(List<EntityModel> subEntityModels) {
		this.subEntityModels = subEntityModels;
	}
	
	@SetNext
	public void addSubEntityModel(EntityModel subEntityModel) {
		if (subEntityModels == null) {
			subEntityModels  = new ArrayList<EntityModel>();
		}
		
		subEntityModels.add(subEntityModel);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}
	
	
}
