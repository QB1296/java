/**
 * 文件名：MoudleModel.java  
 *  
 * 版本信息：  
 * 日期：2014年8月12日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.security.meta.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.digester.annotations.rules.ObjectCreate;
import org.apache.commons.digester.annotations.rules.SetNext;
import org.apache.commons.digester.annotations.rules.SetProperty;
import org.springframework.util.Assert;


/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2014年8月12日 上午11:45:06
 */
@ObjectCreate(pattern="premission-mapping/moudle")
public class MoudleModel {

	@SetProperty(attributeName="name",pattern="premission-mapping/moudle")
	private String name;
	
	@SetProperty(attributeName="code",pattern="premission-mapping/moudle")
	private String code;
	
	private List<EntityModel> entityModels;
	
	
	private List<RowRule> rowRules;
	

	public MoudleModel() {
		super();
		entityModels = new ArrayList<EntityModel>();
		EntityModel entityModel = new EntityModel();
		entityModel.setCode(EntityModel.ASSIST_TABLE_ALIAS);
		entityModel.setTable(EntityModel.ASSIST_TABLE);
		entityModels.add(entityModel);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<EntityModel> getSubEntityModels() {
		return entityModels;
	}
	
	public List<EntityModel> getEntityModels() {
		return entityModels;
	}

	public void setEntityModels(List<EntityModel> entityModels) {
		this.entityModels = entityModels;
	}
	

	
	
	public List<RowRule> getRowRules() {
		return rowRules;
	}

	public void setRowRules(List<RowRule> rowRules) {
		this.rowRules = rowRules;
	}

	
	@SetNext
	public void addPointCutModel(EntityModel entityModel) {
		if (entityModels == null) {
			entityModels = new ArrayList<EntityModel>();
		}
		entityModels.add(entityModel);
	}
	
	
	@SetNext
	public void addRowRuleModel(RowRule rowRule) {
		if (rowRules == null) {
			rowRules = new ArrayList<RowRule>();
		}
		rowRules.add(rowRule);
	}
	
	
	/**
	 * 得到表名通过code
	 * @param code
	 * @return
	 */
	public String getTableByCode(String code){
		Assert.notNull(code);
		if (entityModels != null) {
			for (EntityModel entityModel : entityModels) {
				if(code.equals(entityModel.getCode())){
					return entityModel.getTable();
				}			
			}
		}
		return null;
	}
	
	/**
	 * 得到表名列表通过code列表
	 * @param codes
	 * @return
	 */
	public String[] getTablesByCodes(String[] codes){
		if (codes == null) {
			return null;
		}
		String[] tables = new String[codes.length];
		for (int i = 0; i < codes.length; i++) {
			tables[i] = getTableByCode(codes[i]);
		}
		return tables;
	}
	
	
	
}
