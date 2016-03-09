/**
 * 文件名：DataResourceFormBo.java  
 *  
 * 版本信息：  
 * 日期：2014年8月12日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.security.meta;

import java.util.ArrayList;
import java.util.List;

import com.vion.core.security.RecordFilterRuleResolver;
import com.vion.core.security.rule.FilterRule;
import com.vion.core.security.rule.RecordFilterRule;


/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2014年8月12日 下午5:15:01
 */
public class DataResourceFormBo {
	
	private String moudle;
	
	private String name;
	
	private List<DataResourceNode> nodes;
	
	private String system;
	
	
	
	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMoudle() {
		return moudle;
	}

	public void setMoudle(String moudle) {
		this.moudle = moudle;
	}

	
	public List<DataResourceNode> getNodes() {
		return nodes;
	}

	public void setNodes(List<DataResourceNode> nodes) {
		this.nodes = nodes;
	}
	
	
	public void addNode(DataResourceNode node){
		if(nodes == null){
			nodes = new ArrayList<DataResourceNode>();
		}
		nodes.add(node);
		
	}

	public List<FilterRule> convertToFilterRule(){
		
		List<FilterRule> filterRules = new ArrayList<FilterRule>();
		
		if (nodes != null) {
			for (DataResourceNode node : nodes) {
				filterRules.add(getOneGroupFilter(node));
			}
		}
		return filterRules;
	}
	
	
	private RecordFilterRule getOneGroupFilter(DataResourceNode rule){
		
		RecordFilterRuleResolver resolver = new RecordFilterRuleResolver(moudle);
		
		return resolver.resolveDataResource(rule);
	}
	

	

}
