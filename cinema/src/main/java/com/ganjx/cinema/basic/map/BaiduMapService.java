/**
 * 文件名： MapService.java
 *  
 * 版本信息：  
 * 日期：2015年4月13日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.ganjx.cinema.basic.map;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.ganjx.cinema.util.HttpUtils;

/**
 * <b>地图服务入口</b> <br>
 * 
 * @author GANJX
 * @date 2015年4月13日 上午10:40:08
 */
@Component
public class BaiduMapService implements IMapService{

	Logger logger = LoggerFactory.getLogger(BaiduMapService.class);
	
	
	/* (non-Javadoc)
	 * @see com.vion.pc.basic.map.IMapService#getLocationByAddress(java.lang.String)
	 */
	public BaiduMapResponse getLocationByAddress(String address){
		Assert.notNull(address);
		try {
			BaiduMapResponse obj = null;
			String string = HttpUtils.get(withUrlAndParam(address), null);
			if(string != null){
				obj = jsonToJava(string,BaiduMapResponse.class);
			}
			return obj;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}
	
	
	/**
	 * @param address
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String withUrlAndParam(String address) throws UnsupportedEncodingException{
		BaiduRequest mapRequest = new BaiduRequest();
		BaiduRequestParam params = new BaiduRequestParam();
		String encode = URLEncoder.encode(address,"UTF-8");
		params.setAddress(encode);
		return mapRequest.getUrl() +"?"+ params.toString();
	}
	
	/**
	 * @param json
	 * @param clazz
	 * @return
	 */
	public <T> T jsonToJava(String json,Class<T> clazz){
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(json, clazz);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("JSON转化对象异常：",e);
		}
		return null;
	}
}
