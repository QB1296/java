/**
 * 文件名：FlatedTreeNode.java  
 *  
 * 版本信息：  
 * 日期：2014年12月10日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.domain.vo;

import java.util.Map;

/**
 * <b>功能描述</b> <br>
 * 平铺的树形结构节点,每一个节点与父亲节点关联通过PId关联直接父节点的id
 * <li>{@link #id}为节点的唯一标识
 * <li>{@link #text}为节点显示名称
 * <li>{@link #data}为业务数据 
 * <li>{@link #pId}关联父节点的唯一标识
 * @author YUJB
 * @date 2014年12月10日 下午2:24:23
 */
public class FlatedTreeNode implements ValueObject{

	/**   */
	private static final long serialVersionUID = 1L;

	/** 节点标识字段  */
	private String id;
	
	/** 节点显示名称  */
	private String text;
	
	/** 业务数据  */
	private Map<String, Object> data;
	
	/** 父节点  */
	private String pId;

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

	/**
	 * {@link #pId}
	 * @return the pId
	 */
	public String getpId() {
		return pId;
	}

	/**
	 * {@link #pId}	
	 * @param pId the pId to set
	 */
	public void setpId(String pId) {
		this.pId = pId;
	}
	
	/**
	 * {@link #pId}
	 * @return the pId
	 */
	public String getPId() {
		return pId;
	}

	/**
	 * {@link #pId}	
	 * @param pId the pId to set
	 */
	public void setPId(String pId) {
		this.pId = pId;
	}
	
	
	
}
