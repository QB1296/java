package com.ganjx.cinema.vo.address1905.info;

import java.io.Serializable;

public class Cinemafreefilm implements Serializable{	private static final long serialVersionUID = 189971819L;	private String message;	private long code;
	public String getMessage() {		return this.message;	}
	public void setMessage(String message) {		this.message = message;	}
	public long getCode() {		return this.code;	}
	public void setCode(long code) {		this.code = code;	}
	public Cinemafreefilm() {}
	public Cinemafreefilm(String message, long code){
		super();		this.message = message;		this.code = code;
	}
	public String toString() {
		return "Cinemafreefilm [message = " + message + ", code = " + code + "]";	}
}