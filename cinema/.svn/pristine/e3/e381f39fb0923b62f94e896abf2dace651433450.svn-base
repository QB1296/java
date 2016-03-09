/**
 * 文件名： MzCity.java
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
 * @date 2015年10月20日 上午10:05:07
 */
@Table(name="mz_city")
@Entity
public class MzCity implements IEntity{
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;

	private Integer cityid;
	
	private String name;
	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Integer getCityid() {
		return cityid;
	}

	public void setCityid(Integer cityid) {
		this.cityid = cityid;
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
		return "MzCity [cityid=" + cityid + ", name=" + name + "]";
	}
	
	
}
