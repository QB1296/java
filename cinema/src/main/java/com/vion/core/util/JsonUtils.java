/**
 * 文件名：JsonUtils.java  
 *  
 * 版本信息：  
 * 日期：2015年3月31日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.util;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * <b>json相关方法</b> <br>
 * 
 * 
 * @author tramp
 * @date 2015年3月31日 下午3:42:27
 */
public class JsonUtils {

	private static final Logger logger = (Logger) LoggerFactory.getLogger(JsonUtils.class);
	
	private static ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * 将对象序列化为JSON字符串
	 * 
	 * @param object
	 * @return JSON字符串
	 */
	public static String serialize(Object object) {
		Writer write = new StringWriter();
		try {
			objectMapper.writeValue(write, object);
		}
		catch (JsonGenerationException e) {
			logger.error("JsonGenerationException when serialize object to json", e);
		}
		catch (JsonMappingException e) {
			logger.error("JsonMappingException when serialize object to json", e);
		}
		catch (IOException e) {
			logger.error("IOException when serialize object to json", e);
		}
		return write.toString();
	}

	/**
	 * 将JSON字符串反序列化为对象
	 * 
	 * @param object
	 * @return JSON字符串
	 */
	@SuppressWarnings("unchecked")
	public static <T> T deserialize(String json, Class<T> clazz) {
		Object object = null;
		try {
			object = objectMapper.readValue(json, TypeFactory.rawClass(clazz));
		}
		catch (JsonParseException e) {
			logger.error("JsonParseException when serialize object to json", e);
		}
		catch (JsonMappingException e) {
			logger.error("JsonMappingException when serialize object to json", e);
		}
		catch (IOException e) {
			logger.error("IOException when serialize object to json", e);
		}
		return (T) object;
	}

	/**
	 * 将JSON字符串反序列化为对象
	 * 
	 * @param object
	 * @return JSON字符串
	 */
	@SuppressWarnings("unchecked")
	public static <T> T deserialize(String json, TypeReference<T> typeRef) {
		try {
			return (T) objectMapper.readValue(json.getBytes(), typeRef);
		}
		catch (JsonParseException e) {
			logger.error("JsonParseException when deserialize json", e);
		}
		catch (JsonMappingException e) {
			logger.error("JsonMappingException when deserialize json", e);
		}
		catch (IOException e) {
			logger.error("IOException when deserialize json", e);
		}
		return null;
	}
}
	

