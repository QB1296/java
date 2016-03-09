package com.ganjx.cinema.vo.address1905.info;

import java.io.Serializable;

public class Cinemavoucher implements Serializable{	private static final long serialVersionUID = 189971819L;	private String message;	private long code;
	public String getMessage() {		return this.message;	}
	public void setMessage(String message) {		this.message = message;	}
	public long getCode() {		return this.code;	}
	public void setCode(long code) {		this.code = code;	}
	public Cinemavoucher() {}
	public Cinemavoucher(String message, long code){
		super();		this.message = message;		this.code = code;
	}
	public String toString() {
		return "Cinemavoucher [message = " + message + ", code = " + code + "]";	}
}