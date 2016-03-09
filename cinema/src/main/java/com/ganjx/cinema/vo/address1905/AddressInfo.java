package com.ganjx.cinema.vo.address1905;

import java.io.Serializable;
import java.util.List;

import com.ganjx.cinema.vo.address1905.arealist.Arealist;
import com.ganjx.cinema.vo.address1905.info.Info;

public class AddressInfo implements Serializable{	private static final long serialVersionUID = 1420638790L;	private List<Arealist> arealist;	private List<Info> info;	private long code;	private long totle;	private String message;
	public List<Arealist> getArealist() {		return this.arealist;	}
	public void setArealist(List<Arealist> arealist) {		this.arealist = arealist;	}
	public List<Info> getInfo() {		return this.info;	}
	public void setInfo(List<Info> info) {		this.info = info;	}
	public long getCode() {		return this.code;	}
	public void setCode(long code) {		this.code = code;	}
	public long getTotle() {		return this.totle;	}
	public void setTotle(long totle) {		this.totle = totle;	}
	public String getMessage() {		return this.message;	}
	public void setMessage(String message) {		this.message = message;	}
	public AddressInfo() {}
	public AddressInfo(List<Arealist> arealist, List<Info> info, long code, long totle, String message){
		super();		this.arealist = arealist;		this.info = info;		this.code = code;		this.totle = totle;		this.message = message;
	}
	public String toString() {
		return "AddressInfo [arealist = " + arealist + ", info = " + info + ", code = " + code + ", totle = " + totle + ", message = " + message + "]";	}
}