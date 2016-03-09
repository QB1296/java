/**
 * 文件名： KDMCCinemaBo.java
 *  
 * 版本信息：  
 * 日期：2015年11月23日 
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
 * @date 2015年11月23日 上午11:30:01
 */
@PoiSheet(value=0)
@PoiRow(start=1)
@PoiColumn(start=0, end = 2)
public class KDMCCinemaBo extends PoiBaseBo{

	@PoiField(column=0)
	private String theatreChain;
	@PoiField(column=1)
	private String cinemaName;
	@PoiField(column=2)
	private String cinemaCode;
	public KDMCCinemaBo() {
		// TODO Auto-generated constructor stub
	}
	public KDMCCinemaBo(Row row) {
		// TODO Auto-generated constructor stub
		super(row);
	}
	public String getTheatreChain() {
		return theatreChain;
	}
	public void setTheatreChain(String theatreChain) {
		this.theatreChain = theatreChain;
	}
	public String getCinemaName() {
		return cinemaName;
	}
	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}
	public String getCinemaCode() {
		return cinemaCode;
	}
	public void setCinemaCode(String cinemaCode) {
		this.cinemaCode = cinemaCode;
	}
	@Override
	public String toString() {
		return "KDMCCinemaBo [theatreChain=" + theatreChain + ", cinemaName="
				+ cinemaName + ", cinemaCode=" + cinemaCode + "]";
	}
	
	
}
