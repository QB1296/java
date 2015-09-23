/**
 * 文件名： Tcinema.java
 *  
 * 版本信息：  
 * 日期：2015年9月23日 
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
 * @date 2015年9月23日 上午11:11:04
 */
@Entity
@Table(name = "t_cinema")
public class Tcinema implements IEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String cinemaId;
	
	private String cinemaName;


	@Id
	@Column(name = "sid", unique = true, nullable = false)
	public String getCinemaId() {
		return cinemaId;
	}

	public void setCinemaId(String cinemaId) {
		this.cinemaId = cinemaId;
	}

	@Column(name = "sname", length = 64)
	public String getCinemaName() {
		return cinemaName;
	}

	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}

	@Override
	public String toString() {
		return "Tcinema [cinemaId=" + cinemaId + ", cinemaName=" + cinemaName
				+ "]";
	}
	
	
}
