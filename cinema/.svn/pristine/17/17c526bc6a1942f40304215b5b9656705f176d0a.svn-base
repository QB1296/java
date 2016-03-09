/**
 * 文件名：DataTreeQueryResult.java  
 *  
 * 版本信息：  
 * 日期：2014年10月29日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.domain.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * <b>功能描述</b> <br>
 * 嵌套的树形结构节点,每一个节点与父亲节点关联通过childrens关联直接子节点
 * <li>{@link #id}为节点的唯一标识
 * <li>{@link #text}为节点显示名称
 * <li>{@link #data}为业务数据 
 * <li>{@link #childrens}关联直接子节点
 * @author YUJB
 * @date 2014年12月10日 下午2:24:23
 */
public class NestedTreeNode implements ValueObject{
	
	
	/**   */
	private static final long serialVersionUID = 1L;

	/** 节点标识字段  */
	private String id;
	
	/** 节点显示名称  */
	private String text;
	
	/** 业务数据  */
	private Map<String, Object> data;
	
	/** 直接子节点  */
	private List<NestedTreeNode> children = new ArrayList<NestedTreeNode>();
	

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
	 * {@link #text}
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * {@link #text}	
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * {@link #data}
	 * @return the data
	 */
	public Map<String, Object> getData() {
		return data;
	}

	/**
	 * {@link #data}	
	 * @param data the data to set
	 */
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
	public void addData(String key,Object value){
		if (this.data == null) {
			this.data = new HashMap<String,Object>();
		}
		this.data.put(key, value);
	}

	/**
	 * {@link #childrens}
	 * @return the childrens
	 */
	public List<NestedTreeNode> getChildren() {
		return children;
	}

	/**
	 * {@link #childrens}	
	 * @param childrens the childrens to set
	 */
	public void setChildren(List<NestedTreeNode> children) {
		this.children = children;
	}
	
	
	public void addChild(NestedTreeNode child){
		children.add(child);
	}
	
	
	public NestedTreeNode deepCopy(){
		NestedTreeNode nestedTreeNode = new NestedTreeNode();
		nestedTreeNode.setChildren(this.getChildren());
		nestedTreeNode.setData(cloneMap(this.getData()));
		nestedTreeNode.setId(this.getId());
		nestedTreeNode.setText(this.getText());
		return nestedTreeNode;
	}
	
	
	public Map<String, Object> cloneMap(Map<String, Object> orginedMap) {
		Map<String, Object> target = new HashMap<String, Object>();
		for (Iterator<String> keyIt = orginedMap.keySet().iterator(); keyIt.hasNext();) {
			String key = keyIt.next();
			target.put(key, orginedMap.get(key));
		}
		return target;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NestedTreeNode other = (NestedTreeNode) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
}
