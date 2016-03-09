/**
 * 文件名：ServletContextResourceLoader.java  
 *  
 * 版本信息：  
 * 日期：2015年1月29日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.spring;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

import com.vion.core.SystemContext;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2015年1月29日 下午12:47:42
 */
public class CompositeResourceLoader extends DefaultResourceLoader{
	
	public static final String SERVLET_URL_PREFIX = "context:";

	/* (non-Javadoc)
	 * @see org.springframework.core.io.DefaultResourceLoader#getResource(java.lang.String)*/
	@Override
	public Resource getResource(String location) {
		Assert.notNull(location, "Location must not be null");
		if (location.startsWith(SERVLET_URL_PREFIX)) {
			location = location.substring(SERVLET_URL_PREFIX.length());
			org.springframework.web.context.support.ServletContextResourceLoader servletContextResourceLoader = new org.springframework.web.context.support.ServletContextResourceLoader(SystemContext.getHttpSession().getServletContext());
			return servletContextResourceLoader.getResource(location);
		}else {
			return super.getResource(location);
		}
	}
	
	
	
}
