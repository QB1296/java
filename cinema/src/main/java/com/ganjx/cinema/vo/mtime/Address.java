package com.ganjx.cinema.vo.mtime;

import java.io.Serializable;
import java.util.List;

import com.ganjx.cinema.vo.mtime.list.MList;

public class Address implements Serializable{

		super();
	}

		return "Address [list = " + getList() + ", totalcount = " + getTotalcount() + "]";

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