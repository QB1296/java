/**
 * 文件名：PermissionLoader.java  
 *  
 * 版本信息：  
 * 日期：2014年8月12日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.annotations.DigesterLoader;
import org.apache.commons.digester.annotations.DigesterLoaderBuilder;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.vion.core.exception.ConfigurationException;
import com.vion.core.security.meta.model.SecureMetaModel;
import com.vion.core.spring.CompositeResourceLoader;


/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2014年8月12日 下午1:13:17
 */
public class SecureMetaLoader implements InitializingBean{
	
	private String configLocation = "classpath:config/premission-Config.xml";
	
	@Override
	public void afterPropertiesSet() throws Exception {
		SecureMetaModel permisssionModel = loaderConfig();
		SecureMetaModelHolder.setSecureMetaModel(permisssionModel);
	}
	
	public SecureMetaModel loaderConfig(){
		return loaderConfig(configLocation);
	}
	
	public SecureMetaModel loaderConfig(String configLocation){
		ResourceLoader loader = new CompositeResourceLoader();
		Resource resource = loader.getResource(configLocation);
		try {
			File file = resource.getFile();
			SecureMetaModel permisssionModel = getPojoFromXMLByDigesterAnnotation(SecureMetaModel.class,file);
			return permisssionModel;
		} catch (IOException e) {
			throw new ConfigurationException(e);
		}
	}

	@SuppressWarnings("unchecked")
	private <T> T getPojoFromXMLByDigesterAnnotation(Class<T> clazz,File file){
		try {
			FileInputStream fis = new FileInputStream(file);
			
			InputSource inputSource = new InputSource(fis);
			DigesterLoader digesterLoader =
				new DigesterLoaderBuilder().useDefaultAnnotationRuleProviderFactory().useDefaultDigesterLoaderHandlerFactory();
			Digester digester = digesterLoader.createDigester(clazz);
			return (T)digester.parse(inputSource);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getConfigLocation() {
		return configLocation;
	}

	public void setConfigLocation(String configLocation) {
		this.configLocation = configLocation;
	}
	
	
	
}
