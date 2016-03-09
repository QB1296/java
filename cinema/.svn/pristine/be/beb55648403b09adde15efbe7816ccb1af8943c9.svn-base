/**
 * 文件名： OriginalBo.java
 *  
 * 版本信息：  
 * 日期：2015年4月7日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.ganjx.cinema.excel;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Row;

import com.ganjx.cinema.util.StringUtil;
import com.vion.core.poi.PoiBaseBo;
import com.vion.core.poi.PoiColumn;
import com.vion.core.poi.PoiField;
import com.vion.core.poi.PoiRow;
import com.vion.core.poi.PoiSheet;

/**
 * <b>功能描述</b> <br>
 * 
 * @author GANJX
 * @date 2015年4月7日 下午5:36:26
 */
@PoiSheet(value=0)
@PoiRow(start=1)
@PoiColumn(start=1, end = 12)
public class OriginalBo extends PoiBaseBo{
	
	/**
	 * 省份
	 */
	@PoiField(column=1)
	private String sprovince;
	/**
	 * 院线
	 */
	@PoiField(column=2)
	private String scinemasName;
	/**
	 * 影院标识
	 */
	@PoiField(column=3)
	private String cinemaId;
	/**
	 * 影院名称
	 */
	@PoiField(column=4)
	private String cinemaName;
	/**
	 * 电影标识
	 */
	@PoiField(column=5)
	private String movieId;
	/**
	 * 电影名称
	 */
	@PoiField(column=6)
	private String movieName;
	/**
	 * 发行版本
	 */
	@PoiField(column=7)
	private String release;
	/**
	 * 影厅标识
	 */
	@PoiField(column=8)
	private String rawHallId;
	/**
	 * 放映日期
	 */
	@PoiField(column=9)
	private Date ddate;
	/**
	 * 放映时间
	 */
	@PoiField(column=10)
	private Date dtime;
	/**
	 * 总人数
	 */
	@PoiField(column=11)
	private Long lnum;
	/**
	 * 总票房
	 */
	@PoiField(column=12)
	private BigDecimal fboxoffice;
	
	public OriginalBo() {
		// TODO Auto-generated constructor stub
	}
	
	public OriginalBo(Row row) {
		// TODO Auto-generated constructor stub
		super(row);
	}
	
	public OriginalBo(String sprovince, String scinemasName, String cinemaId,
			String cinemaName, String movieId, String movieName,
			String release, String rawHallId, Date ddate, Date dtime,
			Long lnum, BigDecimal fboxoffice) {
		super();
		this.sprovince = sprovince;
		this.scinemasName = scinemasName;
		this.cinemaId = cinemaId;
		this.cinemaName = cinemaName;
		this.movieId = movieId;
		this.movieName = movieName;
		this.release = release;
		this.rawHallId = rawHallId;
		this.ddate = ddate;
		this.dtime = dtime;
		this.lnum = lnum;
		this.fboxoffice = fboxoffice;
	}

	public String getSprovince() {
		return sprovince;
	}
	public void setSprovince(String sprovince) {
		this.sprovince = sprovince;
	}
	public String getScinemasName() {
		return scinemasName;
	}
	public void setScinemasName(String scinemasName) {
		this.scinemasName = scinemasName;
	}
	public String getCinemaId() {
		return cinemaId;
	}
	public void setCinemaId(String cinemaId) {
		this.cinemaId = cinemaId;
	}
	public String getCinemaName() {
		return cinemaName;
	}
	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getRelease() {
		return release;
	}
	public void setRelease(String release) {
		this.release = release;
	}
	public Date getDdate() {
		return ddate;
	}
	public void setDdate(Date ddate) {
		this.ddate = ddate;
	}
	public Date getDtime() {
		return dtime;
	}
	public void setDtime(Date dtime) {
		this.dtime = dtime;
	}
	public Long getLnum() {
		return lnum;
	}
	public void setLnum(Long lnum) {
		this.lnum = lnum;
	}
	public BigDecimal getFboxoffice() {
		return fboxoffice;
	}
	public void setFboxoffice(BigDecimal fboxoffice) {
		this.fboxoffice = fboxoffice;
	}

	public String getRawHallId() {
		if(StringUtils.isNotBlank(this.rawHallId)){
			this.setRawHallId(StringUtil.prefixFillZero(rawHallId, 16));
		}
		return rawHallId;
	}

	public void setRawHallId(String rawHallId) {
		this.rawHallId = rawHallId;
	}

	@Override
	public String toString() {
		return "OriginalBo [sprovince=" + sprovince + ", scinemasName="
				+ scinemasName + ", cinemaId=" + cinemaId + ", cinemaName="
				+ cinemaName + ", movieId=" + movieId + ", movieName="
				+ movieName + ", release=" + release + ", rawHallId="
				+ rawHallId + ", ddate=" + ddate + ", dtime=" + dtime
				+ ", lnum=" + lnum + ", fboxoffice=" + fboxoffice + "]";
	}
	
	
}
