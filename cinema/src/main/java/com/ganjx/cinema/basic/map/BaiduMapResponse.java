/**
 * 文件名： MapResponse.java
 *  
 * 版本信息：  
 * 日期：2015年4月13日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.ganjx.cinema.basic.map;

import java.io.Serializable;

/**
 * <b>地图请求结果返回</b> <br>
 * 
 * @author GANJX
 * @date 2015年4月13日 下午2:13:59
 */
/**
 * @author Administrator
 *
 */
public class BaiduMapResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 正常返回结果
	 */
	public final static Integer STATUS_SUCCESS = 0;
	/**
	 * 说明：返回结果状态值， 成功返回0，其他值请查看下方返回码状态表。
	 */
	private Integer status;
	/**
	 * 返回结果集，包含坐标等信息
	 */
	private MapResult result;
	/**
	 * 
	 */
	private String message;
	
	/**
	 * 
	 */
	private Object results;
	/**
	 * 
	 */
	private String msg;
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public MapResult getResult() {
		return result;
	}
	public void setResult(MapResult result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getResults() {
		return results;
	}
	public void setResults(Object results) {
		this.results = results;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	@Override
	public String toString() {
		return "BaiduMapResponse [status=" + status + ", result=" + result
				+ ", message=" + message + ", results=" + results + ", msg="
				+ msg + "]";
	}
	
	
}
