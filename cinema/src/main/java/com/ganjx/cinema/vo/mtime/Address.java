package com.ganjx.cinema.vo.mtime;

import java.io.Serializable;
import java.util.List;

import com.ganjx.cinema.vo.mtime.list.MList;

public class Address implements Serializable{	private static final long serialVersionUID = 463362866L;	private List<MList> list;	private long totalcount;	public Address() {}
	public Address(List<MList> list, long totalcount){
		super();		this.setList(list);		this.setTotalcount(totalcount);
	}
	public String toString() {
		return "Address [list = " + getList() + ", totalcount = " + getTotalcount() + "]";	}

	public List<MList> getList() {
		return list;
	}

	public void setList(List<MList> list) {
		this.list = list;
	}

	public long getTotalcount() {
		return totalcount;
	}

	public void setTotalcount(long totalcount) {
		this.totalcount = totalcount;
	}
}