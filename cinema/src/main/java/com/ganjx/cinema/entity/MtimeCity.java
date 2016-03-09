/**
 * 文件名： MtimeCity.java
 *  
 * 版本信息：  
 * 日期：2015年10月27日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.ganjx.cinema.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.vion.core.domain.entity.IEntity;

/**
 * <b>功能描述</b> <br>
 * 
 * @author GANJX
 * @date 2015年10月27日 上午9:29:38
 */
@Table(name="mtime_city")
@Entity
public class MtimeCity implements IEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	
	public MtimeCity() {
		// TODO Auto-generated constructor stub
	}
	
	public MtimeCity(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
