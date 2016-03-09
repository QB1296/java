package com.ganjx.cinema.vo.maizuo;

import java.io.Serializable;
import java.util.List;

import com.ganjx.cinema.vo.maizuo.data.Data;

public class MzAddress implements Serializable{	private static final long serialVersionUID = 218160612L;	private List<Data> data;	private String msg;	private long status;
	public List<Data> getData() {		return this.data;	}
	public void setData(List<Data> data) {		this.data = data;	}
	public String getMsg() {		return this.msg;	}
	public void setMsg(String msg) {		this.msg = msg;	}
	public long getStatus() {		return this.status;	}
	public void setStatus(long status) {		this.status = status;	}
	public MzAddress() {}
	public MzAddress(List<Data> data, String msg, long status){
		super();		this.data = data;		this.msg = msg;		this.status = status;
	}
	public String toString() {
		return "MzAddress [data = " + data + ", msg = " + msg + ", status = " + status + "]";	}
}