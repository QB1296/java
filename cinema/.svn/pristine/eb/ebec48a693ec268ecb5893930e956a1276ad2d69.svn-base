/**
 * 文件名：DefaultFuncModel.java  
 *  
 * 版本信息：  
 * 日期：2014年12月16日  
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
import org.springframework.beans.BeanUtils;

import com.vion.core.security.meta.DataResourceFormBo;
import com.vion.core.security.meta.DataResourceNode;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2014年12月16日 下午1:23:11
 */
@ObjectCreate(pattern="premission-mapping/defaultDataFunc")
public class DefaultFuncModel {
	
	@SetProperty(attributeName="name",pattern="premission-mapping/defaultDataFunc")
	private String name;
	
	@SetProperty(attributeName="moudle",pattern="premission-mapping/defaultDataFunc")
	private String moudle;
	
	@SetProperty(attributeName="id",pattern="premission-mapping/defaultDataFunc")
	private String id;
	
	@SetProperty(attributeName="default",pattern="premission-mapping/defaultDataFunc")
	private String isDefault = "false";
	
	private List<DefaultFuncNodeModel> nodes;
	
	
	public DataResourceFormBo convertFormBo(){
		DataResourceFormBo dataResourceFormBo = new DataResourceFormBo();
		BeanUtils.copyProperties(this, dataResourceFormBo);
		List<DataResourceNode> nodes = new ArrayList<DataResourceNode>();
		if (this.getNodes() != null) {
			for (DefaultFuncNodeModel node : this.getNodes()) {
				DataResourceNode bo = new DataResourceNode();
				BeanUtils.copyProperties(node, bo);
				nodes.add(bo);
			}
		}
		dataResourceFormBo.setNodes(nodes);
		return dataResourceFormBo;
	}
	
	
	@SetNext
	public void addDefaultFuncNodeModel(DefaultFuncNodeModel funcNodeModel){
		if (nodes == null) {
			nodes = new ArrayList<DefaultFuncNodeModel>();
		}
		nodes.add(funcNodeModel);
	}
	

	/**
	 * {@link #name}
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * {@link #name}	
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * {@link #moudle}
	 * @return the moudle
	 */
	public String getMoudle() {
		return moudle;
	}

	/**
	 * {@link #moudle}	
	 * @param moudle the moudle to set
	 */
	public void setMoudle(String moudle) {
		this.moudle = moudle;
	}


	/**
	 * {@link #id}
	 * @return the id
	 */
	public String getId() {
		return id;
	}


	/**
	 * {@link #id}	
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}


	/**
	 * {@link #nodes}
	 * @return the nodes
	 */
	public List<DefaultFuncNodeModel> getNodes() {
		if (nodes == null) {
			return null;
		}
		return Collections.unmodifiableList(nodes);
	}

	/**
	 * {@link #nodes}	
	 * @param nodes the nodes to set
	 */
	public void setNodes(List<DefaultFuncNodeModel> nodes) {
		this.nodes = nodes;
	}


	/**
	 * {@link #isDefault}
	 * @return the isDefault
	 */
	public String getIsDefault() {
		return isDefault;
	}


	/**
	 * {@link #isDefault}	
	 * @param isDefault the isDefault to set
	 */
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	
	
	
	
	
}
