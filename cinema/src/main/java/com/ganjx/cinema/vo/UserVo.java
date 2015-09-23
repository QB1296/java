/**
 * 文件名： UserVO.java
 *  
 * 版本信息：  
 * 日期：2015年9月15日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.ganjx.cinema.vo;

import java.util.Date;

import com.vion.core.domain.vo.ValueObject;

/**
 * <b>功能描述</b> <br>
 * 
 * @author GANJX
 * @date 2015年9月15日 上午10:45:36
 */
public class UserVo implements ValueObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String susername;
	private String spwhash;
	private String sorgId;
	private Integer lisAdmin;
	private String semail;
	private String srealname;
	private String sex;
	private String sphone;
	private Integer stheme;
	private Long luserId;
	private Date ddate;
	private Date ddateUpdate;
	private String slastIp;
	private Date dlastTime;
	private String scookies;
	private String spageId;
	private String sdefPage;
	
	public UserVo() {
		// TODO Auto-generated constructor stub
	}
	public UserVo(Long id) {
		// TODO Auto-generated constructor stub
		this.id = id;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSusername() {
		return susername;
	}
	public void setSusername(String susername) {
		this.susername = susername;
	}
	public String getSpwhash() {
		return spwhash;
	}
	public void setSpwhash(String spwhash) {
		this.spwhash = spwhash;
	}
	public String getSorgId() {
		return sorgId;
	}
	public void setSorgId(String sorgId) {
		this.sorgId = sorgId;
	}
	public Integer getLisAdmin() {
		return lisAdmin;
	}
	public void setLisAdmin(Integer lisAdmin) {
		this.lisAdmin = lisAdmin;
	}
	public String getSemail() {
		return semail;
	}
	public void setSemail(String semail) {
		this.semail = semail;
	}
	public String getSrealname() {
		return srealname;
	}
	public void setSrealname(String srealname) {
		this.srealname = srealname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getSphone() {
		return sphone;
	}
	public void setSphone(String sphone) {
		this.sphone = sphone;
	}
	public Integer getStheme() {
		return stheme;
	}
	public void setStheme(Integer stheme) {
		this.stheme = stheme;
	}
	public Long getLuserId() {
		return luserId;
	}
	public void setLuserId(Long luserId) {
		this.luserId = luserId;
	}
	public Date getDdate() {
		return ddate;
	}
	public void setDdate(Date ddate) {
		this.ddate = ddate;
	}
	public Date getDdateUpdate() {
		return ddateUpdate;
	}
	public void setDdateUpdate(Date ddateUpdate) {
		this.ddateUpdate = ddateUpdate;
	}
	public String getSlastIp() {
		return slastIp;
	}
	public void setSlastIp(String slastIp) {
		this.slastIp = slastIp;
	}
	public Date getDlastTime() {
		return dlastTime;
	}
	public void setDlastTime(Date dlastTime) {
		this.dlastTime = dlastTime;
	}
	public String getScookies() {
		return scookies;
	}
	public void setScookies(String scookies) {
		this.scookies = scookies;
	}
	public String getSpageId() {
		return spageId;
	}
	public void setSpageId(String spageId) {
		this.spageId = spageId;
	}
	public String getSdefPage() {
		return sdefPage;
	}
	public void setSdefPage(String sdefPage) {
		this.sdefPage = sdefPage;
	}
	
	
	
}
