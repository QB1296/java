/**
 * 文件名：FilterRule.java  
 *  
 * 版本信息：  
 * 日期：2014年8月12日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.security.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vion.core.security.FragmentSQL;


/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2014年8月12日 下午2:02:59
 */
public class ColumnFilterRule implements FilterRule{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ColumnFilterRuleGroup columnFilterRuleGroup;
	
	private String column;
	
	private String entity;
	
	
	public ColumnFilterRuleGroup getColumnFilterRuleGroup() {
		return columnFilterRuleGroup;
	}

	public void setColumnFilterRuleGroup(ColumnFilterRuleGroup columnFilterRuleGroup) {
		this.columnFilterRuleGroup = columnFilterRuleGroup;
	}


	public String getColumn() {
		return column;
	}


	public void setColumn(String column) {
		this.column = column;
	}


	public String getEntity() {
		return entity;
	}


	public void setEntity(String entity) {
		this.entity = entity;
	}
	
	
	public Map<String, List<String>> getAllHideColumn(){
		Map<String, List<String>> retMap = new HashMap<String, List<String>>();
		if(this.getColumnFilterRuleGroup() != null){
			ColumnFilterRuleGroup group = this.getColumnFilterRuleGroup();
			List<ColumnFilterRule> rules = group.getRules();
			for (ColumnFilterRule columnFilterRule : rules) {
				String column = columnFilterRule.getColumn();
				String entity = columnFilterRule.getEntity();
				List<String> columns = new ArrayList<String>();
				if (retMap.containsKey(entity)) {
					columns = retMap.get(entity);
				}
				columns.add(column);
				retMap.put(entity, columns);
				
			}
		}else {
			String column = this.getColumn();
			String entity = this.getEntity();
			List<String> columns = new ArrayList<String>();
			if (retMap.containsKey(entity)) {
				columns = retMap.get(entity);
			}
			columns.add(column);
			retMap.put(entity, columns);
		}
		return retMap;
	}
	
	@Override
	public boolean isSupport(FragmentSQL fragmentSQL) {
		if (entity != null && entity.equals(fragmentSQL.getRootFrom())) {
			return true;
		}
		
		List<String> allEntitys = fragmentSQL.getAllEntitys();
		List<String> filterEntitys = new ArrayList<String>();
		
		String rootEntity = columnFilterRuleGroup.getRootEntity();
		List<ColumnFilterRule> rules = columnFilterRuleGroup.getRules();
		if (rules != null) {
			for (ColumnFilterRule columnFilterRule : rules) {
				filterEntitys.add(columnFilterRule.getEntity());
			}
		}
		
		if (fragmentSQL.getRootFrom().equals(rootEntity) && allEntitys.containsAll(filterEntitys)) {
			return true;
		}
		
		return false;
	}


	public class ColumnFilterRuleGroup{
		private String rootEntity;
		
		private List<ColumnFilterRule> rules;

		public String getRootEntity() {
			return rootEntity;
		}

		public void setRootEntity(String rootEntity) {
			this.rootEntity = rootEntity;
		}

		public List<ColumnFilterRule> getRules() {
			return rules;
		}

		public void setRules(List<ColumnFilterRule> rules) {
			this.rules = rules;
		}
		
		
	}


	
	

}
