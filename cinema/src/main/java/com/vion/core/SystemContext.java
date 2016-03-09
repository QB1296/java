/**
 * 文件名：TrafficSystemContext.java  
 *  
 * 版本信息：  
 * 日期：2014年8月8日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * <b>功能描述</b> <br>
 * 系统上下文信息
 * @author YUJB
 * @date 2014年8月8日 下午3:43:20
 */
public class SystemContext{
	
	
	/**
	 * 得到spring 应用上下文信息
	 * @return
	 */
	public static ApplicationContext getApplicationContext() {
//		return ContextLoader.getCurrentWebApplicationContext();
		return (ApplicationContext)ResourcesHolder.get("ac");
	}

	
	/**
	 * 得到httpSession信息
	 * @return
	 */
	public static HttpSession getHttpSession(){
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	    return attr.getRequest().getSession(true);
	}
	
	
	
	/**
	 * 得到HttpRequest信息
	 * @return
	 */
	public static HttpServletRequest getHttpRequest(){
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	    return attr.getRequest();
	}
	

}
