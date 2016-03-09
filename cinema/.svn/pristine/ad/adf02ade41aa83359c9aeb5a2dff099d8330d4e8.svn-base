/**
 * 文件名： BaiduUrl.java
 *  
 * 版本信息：  
 * 日期：2015年4月13日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.ganjx.cinema.basic.map;


/**
 * <b>百度提供的地图URL</b> <br>
 * 
 * @author GANJX
 * @date 2015年4月13日 下午1:40:02
 */
public class BaiduRequest{

	/**
	 * 域名
	 */
	private String domain = "api.map.baidu.com";
	/**
	 * 服务名
	 */
	private String service = "geocoder";
	/**
	 * 版本号
	 */
	private String version = "v2";
	
	/**
	 * 
	 */
	public BaiduRequest() {
		// TODO Auto-generated constructor stub
	}
	
	public String getUrl() {
		// TODO Auto-generated method stub
		StringBuffer buf = new StringBuffer();
		buf.append("http://");
		buf.append(domain);
		buf.append("/");
		buf.append(service);
		buf.append("/");
		buf.append(version);
		buf.append("/");
		return buf.toString();
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
}
