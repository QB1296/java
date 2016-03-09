/**
 * 文件名： CinemaDetailBo.java
 *  
 * 版本信息：  
 * 日期：2015年10月15日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.ganjx.cinema.excel;

import java.math.BigDecimal;

import org.apache.poi.ss.usermodel.Row;

import com.vion.core.poi.PoiColumn;
import com.vion.core.poi.PoiField;
import com.vion.core.poi.PoiRow;
import com.vion.core.poi.PoiSheet;
import com.vion.core.poi.PoiBaseBo;
/**
 * <b>功能描述</b> <br>
 * 
 * @author GANJX
 * @date 2015年10月15日 下午2:14:59
 */
@PoiSheet(value=0)
@PoiRow(start=1)
@PoiColumn(start=0, end = 7)
public class CinemaDetailBo extends PoiBaseBo{

	@PoiField(column=0)
	private String id;
	@PoiField(column=1)
	private String cinemaCode;
	@PoiField(column=2)
	private String cinemaName;
	@PoiField(column=3)
	private BigDecimal cinemaLng;
	@PoiField(column=4)
	private BigDecimal cinemaLat;
	@PoiField(column=5)
	private String address;
	@PoiField(column=6)
	private String aliasName;
	@PoiField(column=7)
	private String theatreChain;

	
	public CinemaDetailBo() {
		// TODO Auto-generated constructor stub
	}
	public CinemaDetailBo(Row row) {
		// TODO Auto-generated constructor stub
		super(row);
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCinemaCode() {
		return cinemaCode;
	}
	public void setCinemaCode(String cinemaCode) {
		this.cinemaCode = cinemaCode;
	}
	public String getCinemaName() {
		return cinemaName;
	}
	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}
	public BigDecimal getCinemaLng() {
		return cinemaLng;
	}
	public void setCinemaLng(BigDecimal cinemaLng) {
		this.cinemaLng = cinemaLng;
	}
	public BigDecimal getCinemaLat() {
		return cinemaLat;
	}
	public void setCinemaLat(BigDecimal cinemaLat) {
		this.cinemaLat = cinemaLat;
	}
	public String getTheatreChain() {
		return theatreChain;
	}
	public void setTheatreChain(String theatreChain) {
		this.theatreChain = theatreChain;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAliasName() {
		return aliasName;
	}
	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}
	
	@Override
	public String toString() {
		return "CinemaDetailBo [id=" + id + ", cinemaCode=" + cinemaCode
				+ ", cinemaName=" + cinemaName + ", cinemaLng=" + cinemaLng
				+ ", cinemaLat=" + cinemaLat + ", theatreChain=" + theatreChain
				+ ", address=" + address + ", aliasName=" + aliasName + "]";
	}

	
}
