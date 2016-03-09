/**
 * 文件名： MapLocation.java
 *  
 * 版本信息：  
 * 日期：2015年4月10日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.ganjx.cinema.basic.map;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <b>功能描述</b> <br>
 * 
 * @author GANJX
 * @date 2015年4月10日 下午2:43:03
 */
/**
 * @author Administrator
 *
 */
public class MapLocation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 说明：经度值
	 */
	private BigDecimal lng;
	
	/**
	 * 说明：纬度值
	 */
	private BigDecimal lat;

	public BigDecimal getLng() {
		return lng;
	}

	public void setLng(BigDecimal lng) {
		this.lng = lng;
	}

	public BigDecimal getLat() {
		return lat;
	}

	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}

	@Override
	public String toString() {
		return "MapLocation [lng=" + lng + ", lat=" + lat + "]";
	}
	
	
}
