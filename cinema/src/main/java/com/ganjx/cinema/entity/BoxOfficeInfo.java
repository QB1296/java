/**
 * 文件名： BoxOffice.java
 *  
 * 版本信息：  
 * 日期：2015年11月6日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.ganjx.cinema.entity;

import java.math.BigDecimal;

import com.vion.core.domain.entity.IEntity;

/**
 * <b>功能描述</b> <br>
 * 
 * @author GANJX
 * @date 2015年11月6日 下午3:05:39
 */
public class BoxOfficeInfo implements IEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer curRanking;
	
	private Integer hisRanking;
	
	private String cinemaName;
	
	private BigDecimal boxOffice;
	
	private Long peopleCount;
	
	private Long screenCount;
	
	private Integer year;
	
	public BoxOfficeInfo() {
		// TODO Auto-generated constructor stub
	}

	public Integer getCurRanking() {
		return curRanking;
	}

	public void setCurRanking(Integer curRanking) {
		this.curRanking = curRanking;
	}

	public Integer getHisRanking() {
		return hisRanking;
	}

	public void setHisRanking(Integer hisRanking) {
		this.hisRanking = hisRanking;
	}

	public String getCinemaName() {
		return cinemaName;
	}

	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}

	public BigDecimal getBoxOffice() {
		return boxOffice;
	}

	public void setBoxOffice(BigDecimal boxOffice) {
		this.boxOffice = boxOffice;
	}

	public Long getPeopleCount() {
		return peopleCount;
	}

	public void setPeopleCount(Long peopleCount) {
		this.peopleCount = peopleCount;
	}

	public Long getScreenCount() {
		return screenCount;
	}

	public void setScreenCount(Long screenCount) {
		this.screenCount = screenCount;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "BoxOfficeInfo [curRanking=" + curRanking + ", hisRanking="
				+ hisRanking + ", cinemaName=" + cinemaName + ", boxOffice="
				+ boxOffice + ", peopleCount=" + peopleCount + ", screenCount="
				+ screenCount + ", year=" + year + "]";
	}
	
	
}
