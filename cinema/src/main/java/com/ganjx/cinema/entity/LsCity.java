/**
 * 文件名： LsCity.java
 *  
 * 版本信息：  
 * 日期：2015年10月20日 
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
 * @date 2015年10月20日 下午12:54:48
 */
@Table(name="ls_city")
@Entity
public class LsCity implements IEntity{
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;

	private String href;
	
	private String name;
	
	public LsCity() {
		// TODO Auto-generated constructor stub
	}
	
	public LsCity(String href, String name) {
		super();
		this.href = href;
		this.name = name;
	}

	@Column(name = "name", length = 20)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "LsCity [href=" + href + ", name=" + name + "]";
	}

	@Id
	@Column(name = "href", unique = true, nullable = false)
	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}
	
}
