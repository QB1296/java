/**
 * 文件名： MapResult.java
 *  
 * 版本信息：  
 * 日期：2015年4月10日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.ganjx.cinema.basic.map;

import java.io.Serializable;

/**
 * <b>功能描述</b> <br>
 * 
 * @author GANJX
 * @date 2015年4月10日 下午2:41:34
 */
public class MapResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 说明：经纬度坐标
	 */
	private MapLocation location;

	/**
	 * 说明：位置的附加信息，是否精确查找。1为精确查找，0为不精确。
	 */
	private Integer precise;
	
	/**
	 * 说明：可信度
	 */
	private Integer confidence;
	
	/**
	 * 说明：地址类型
	 */
	private String level;

	public MapLocation getLocation() {
		return location;
	}

	public void setLocation(MapLocation location) {
		this.location = location;
	}

	public Integer getPrecise() {
		return precise;
	}

	public void setPrecise(Integer precise) {
		this.precise = precise;
	}

	public Integer getConfidence() {
		return confidence;
	}

	public void setConfidence(Integer confidence) {
		this.confidence = confidence;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "MapResult [location=" + location + ", precise=" + precise
				+ ", confidence=" + confidence + ", level=" + level + "]";
	}
	
	
}
