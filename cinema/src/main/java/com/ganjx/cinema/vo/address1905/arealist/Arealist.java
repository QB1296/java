package com.ganjx.cinema.vo.address1905.arealist;

import java.io.Serializable;

public class Arealist implements Serializable{	private static final long serialVersionUID = 1335622434L;	private String name;	private Integer cityid;
	public String getName() {		return this.name;	}
	public void setName(String name) {		this.name = name;	}
	public Integer getCityid() {		return this.cityid;	}
	public void setCityid(Integer cityid) {		this.cityid = cityid;	}
	public Arealist() {}
	public Arealist(String name, Integer cityid){
		super();		this.name = name;		this.cityid = cityid;
	}
	public String toString() {
		return "Arealist [name = " + name + ", cityid = " + cityid + "]";	}
}