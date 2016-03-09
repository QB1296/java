/**
 * 文件名： KdmCinema.java
 *  
 * 版本信息：  
 * 日期：2015年11月30日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.ganjx.cinema.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.vion.core.domain.entity.IEntity;

/**
 * <b>功能描述</b> <br>
 * 
 * @author GANJX
 * @date 2015年11月30日 下午6:06:42
 */
@Entity
@Table(name = "kdm_cinema")
@DynamicUpdate
public class KdmCinema implements IEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String cinemaCode;
	private String cinemaName;
	private String theatreChain;
	private String screenCode;
	private String serverVendors;
	private String serverModels;
	private Integer lstatus;
	private String nscreenCode;
	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "cinema_code")
	public String getCinemaCode() {
		return cinemaCode;
	}
	public void setCinemaCode(String cinemaCode) {
		this.cinemaCode = cinemaCode;
	}
	@Column(name = "cinema_name")
	public String getCinemaName() {
		return cinemaName;
	}
	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}
	@Column(name = "theatre_chain", length = 50)
	public String getTheatreChain() {
		return theatreChain;
	}
	public void setTheatreChain(String theatreChain) {
		this.theatreChain = theatreChain;
	}
	@Column(name = "screen_code")
	public String getScreenCode() {
		return screenCode;
	}
	public void setScreenCode(String screenCode) {
		this.screenCode = screenCode;
	}
	@Column(name = "server_vendors")
	public String getServerVendors() {
		return serverVendors;
	}
	public void setServerVendors(String serverVendors) {
		this.serverVendors = serverVendors;
	}
	@Column(name = "server_models")
	public String getServerModels() {
		return serverModels;
	}
	public void setServerModels(String serverModels) {
		this.serverModels = serverModels;
	}
	@Override
	public String toString() {
		return "KdmCinema [id=" + id + ", cinemaCode=" + cinemaCode
				+ ", cinemaName=" + cinemaName + ", theatreChain="
				+ theatreChain + ", screenCode=" + screenCode
				+ ", serverVendors=" + serverVendors + ", serverModels="
				+ serverModels + ", lstatus=" + lstatus + ", nscreenCode="
				+ nscreenCode + "]";
	}
	@Column(name = "lstatus")
	public Integer getLstatus() {
		return lstatus;
	}
	public void setLstatus(Integer lstatus) {
		this.lstatus = lstatus;
	}
	@Column(name = "nscreen_code")
	public String getNscreenCode() {
		return nscreenCode;
	}
	public void setNscreenCode(String nscreenCode) {
		this.nscreenCode = nscreenCode;
	}
	
	
}
