/**
 * 文件名： CinemaBo.java
 *  
 * 版本信息：  
 * 日期：2015年10月14日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.ganjx.cinema.excel;

import org.apache.poi.ss.usermodel.Row;

import com.vion.core.poi.PoiBaseBo;
import com.vion.core.poi.PoiColumn;
import com.vion.core.poi.PoiField;
import com.vion.core.poi.PoiRow;
import com.vion.core.poi.PoiSheet;

/**
 * <b>功能描述</b> <br>
 * 
 * @author GANJX
 * @date 2015年10月14日 下午2:21:13
 */
@PoiSheet(value=0)
@PoiRow(start=1)
@PoiColumn(start=0, end = 3)
public class CinemaBo extends PoiBaseBo{

	/**
	 * 省份
	 */
	@PoiField(column=0)
	private String sprovince;
	
	
	@PoiField(column=1)
	private String cinemaCode;
	
	@PoiField(column=2)
	private String cinemaName;
	
	@PoiField(column=3)
	private String theatreChain;

	public CinemaBo() {
		// TODO Auto-generated constructor stub
	}
	public CinemaBo(Row row){
		super(row);
	}
	
	public String getSprovince() {
		return sprovince;
	}

	public void setSprovince(String sprovince) {
		this.sprovince = sprovince;
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

	public String getTheatreChain() {
		return theatreChain;
	}

	public void setTheatreChain(String theatreChain) {
		this.theatreChain = theatreChain;
	}

	@Override
	public String toString() {
		return "CinemaBo [sprovince=" + sprovince + ", cinemaCode="
				+ cinemaCode + ", cinemaName=" + cinemaName + ", theatreChain="
				+ theatreChain + "]";
	}
	
	
}
