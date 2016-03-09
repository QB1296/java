/**
 * 文件名：ColumnValueModel.java  
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

import com.vion.core.exception.NoCriteriaWritingException;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2014年8月12日 下午12:50:50
 */
@ObjectCreate(pattern="premission-mapping/moudle/rowRule")
public class RowRule {
	
	@SetProperty(attributeName="key",pattern="premission-mapping/moudle/rowRule")
	private String name;
	
	@SetProperty(attributeName="value",pattern="premission-mapping/moudle/rowRule")
	private String entity;
	
	@SetProperty(pattern="premission-mapping/moudle/rowRule")
	private String code;
	
	@SetProperty(pattern="premission-mapping/moudle/rowRule")
	private String operate;
	
	@SetProperty(attributeName="pointCut",pattern="premission-mapping/moudle/rowRule")
	private String pointCut;
	
	@SetProperty(attributeName="excludePointCut",pattern="premission-mapping/moudle/rowRule")
	private String excludePointCut;
	
	@SetProperty(attributeName="joinFilter",pattern="premission-mapping/moudle/rowRule")
	private String joinFilter = "false";
	
	private List<RowRuleValue> rowRuleValues;
	
	private List<RowRuleJoin> rowRuleJoins = new ArrayList<RowRuleJoin>();
	
	private RowRuleSQL sql;
	
	private RowRuleValueConfigModel valueConfigModel;
	
	private RowRuleAssistTable rowRuleAssistTable;
	
	private RowRuleCacheFilter cacheFilter;
	
	
	@SetNext
	public void addCacheFilter(RowRuleCacheFilter cacheFilter) {
		this.cacheFilter = cacheFilter;
	}
	
	
	public boolean isCacheFilter(){
		return cacheFilter != null;
	}
	
	public boolean isAffectSQLFilter(){
		if (cacheFilter == null) {
			throw new NoCriteriaWritingException("首先调用isCacheFilter 返回false不能调用isAffectSQLFilter");
		}
		return Boolean.valueOf(cacheFilter.getAffectSQLFilter());
	}
	
	/**
	 * {@link #sql}		
	 * @param sql the sql to set
	 */
	@SetNext
	public void addAssistTable(RowRuleAssistTable rowRuleAssistTable) {
		this.rowRuleAssistTable = rowRuleAssistTable;
	}
	
	/**
	 * {@link #sql}	
	 * @param sql the sql to set
	 */
	@SetNext
	public void addSql(RowRuleSQL sql) {
		this.sql = sql;
	}
	
	@SetNext
	public void addValueConfigModel(RowRuleValueConfigModel valueConfigModel){
		this.valueConfigModel = valueConfigModel;
	}
	
	/**
	 * {@link #valueConfigModel}
	 * @return the valueConfigModel
	 */
	public RowRuleValueConfigModel getValueConfigModel() {
		return valueConfigModel;
	}
	
	
	
	/**
	 * {@link #sql}
	 * @return the sql
	 */
	public RowRuleSQL getSql() {
		return sql;
	}


	/**
	 * {@link #rowRuleAssistTable}
	 * @return the rowRuleAssistTable
	 */
	public RowRuleAssistTable getRowRuleAssistTable() {
		return rowRuleAssistTable;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}
	
	
	public List<RowRuleValue> getRowRuleValues() {
		return rowRuleValues;
	}

	public void setRowRuleValues(List<RowRuleValue> rowRuleValues) {
		this.rowRuleValues = rowRuleValues;
	}
	
	
	/**
	 * {@link #rowRuleJoins}
	 * @return the rowRuleJoins
	 */
	public List<RowRuleJoin> getRowRuleJoins() {
		return rowRuleJoins;
	}

	/**
	 * {@link #rowRuleJoins}	
	 * @param rowRuleJoins the rowRuleJoins to set
	 */
	public void setRowRuleJoins(List<RowRuleJoin> rowRuleJoins) {
		this.rowRuleJoins = rowRuleJoins;
	}

	@SetNext
	public void addRowRuleJoin(RowRuleJoin rowRuleJoin) {
		if (rowRuleJoins == null) {
			rowRuleJoins = new ArrayList<RowRuleJoin>();
		}
		rowRuleJoins.add(rowRuleJoin);
	}
	

	@SetNext
	public void addRowRuleValue(RowRuleValue rowRuleValue) {
		if (rowRuleValues == null) {
			rowRuleValues = new ArrayList<RowRuleValue>();
		}
		rowRuleValues.add(rowRuleValue);
	}

	/**
	 * @return
	 */
	public String[] getJoinList() {
		String[] ret = new String[rowRuleJoins.size()];
		for (int i = 0; i < rowRuleJoins.size(); i++) {
			ret[i] = rowRuleJoins.get(i).getName();
		}
		return ret;
	}
	
	
	public boolean isAssistTable(){
		return rowRuleAssistTable != null; 
	}

	/**
	 * {@link #pointCut}
	 * @return the pointCut
	 */
	public String getPointCut() {
		return pointCut;
	}

	/**
	 * {@link #pointCut}	
	 * @param pointCut the pointCut to set
	 */
	public void setPointCut(String pointCut) {
		this.pointCut = pointCut;
	}

	/**
	 * {@link #excludePointCut}
	 * @return the excludePointCut
	 */
	public String getExcludePointCut() {
		return excludePointCut;
	}
	
	
	/**
	 * @param excludePointCut the excludePointCut to set
	 */
	public void setExcludePointCut(String excludePointCut) {
		this.excludePointCut = excludePointCut;
	}
	
	
	/**
	 * 获得joinFilter 
	 * @return  joinFilter joinFilter
	 */
	public String getJoinFilter() {
		return joinFilter;
	}
	
	/** 
	 * 设置joinFilter 
	 * @param joinFilter joinFilter 
	 */
	public void setJoinFilter(String joinFilter) {
		this.joinFilter = joinFilter;
	}
}
