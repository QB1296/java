/**
 * 文件名：SubEntityModel.java  
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
 * @date 2014年8月12日 上午11:54:27
 */
@ObjectCreate(pattern="premission-mapping/moudle/entitys/entity")
public class EntityModel {
	
	@SetProperty(attributeName="code",pattern="premission-mapping/moudle/entitys/entity")
	private String code;
	
	@SetProperty(attributeName="table",pattern="premission-mapping/moudle/entitys/entity")
	private String table;
	
	private List<ColumnModel> columnModels;
	
	public static String ASSIST_TABLE = "VTP_ROLE_ASSIST";
	public static String ASSIST_TABLE_ALIAS = "vion_ass";


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public List<ColumnModel> getColumnModels() {
		if(columnModels == null){
			return null;
		}
		return Collections.unmodifiableList(columnModels);
	}
	
	public void setColumnModels(List<ColumnModel> columnModels) {
		this.columnModels = columnModels;
	}

	@SetNext
	public void addColumnModel(ColumnModel columnModel) {
		if (columnModels == null) {
			columnModels = new ArrayList<ColumnModel>();
		}
		columnModels.add(columnModel);
	}
	
	
}
