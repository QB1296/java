/**
 * 文件名：NoCriteriaWritingException.java  
 *  
 * 版本信息：  
 * 日期：2014-6-13  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.exception;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * <b>功能描述</b> <br>
 * 没有正确写异常,异常多用于开发阶段,对一些写法不规范的代码抛出
 * @author YUJB
 * @date 2013-6-27 下午04:10:02
 */
public class NoCriteriaWritingException extends SpiderRuntimeException{

	/**   */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 类写法不正确
	 * @param clazz 类
	 * @param message 异常信息
	 */
	public NoCriteriaWritingException(Class<?> clazz,String message) {
		super(clazz + " 类写法不标准 ," + message);
	}
	
	
	/**
	 * 注解写法不正确
	 * @param annotation 注解
	 * @param message 异常信息
	 */
	public NoCriteriaWritingException(Annotation annotation,String message) {
		super(annotation + " 注解写法不标准 ," + message);
	}
	
	
	/**
	 * 方法写法不正确
	 * @param Method 方法
	 * @param message 异常信息
	 */
	public NoCriteriaWritingException(Method method,String message) {
		super(method + " 方法写法不标准," + message);
	}
	
	
	/**
	 * {@link #NoCriteriaWritingException(Class, String)}
	 * @see SpiderRuntimeException#TrafficRuntimeException(String, Object...)
	 * @param clazz
	 * @param message
	 * @param objects
	 */
	public NoCriteriaWritingException(Class<?> clazz,String message,Object... objects) {
		super(clazz + " 类写法不标准 ," + message,objects);
	}
	
	
	/**
	 * {@link #NoCriteriaWritingException(Class, String)}
	 * @see SpiderRuntimeException#TrafficRuntimeException(String, Object...)
	 * @param annotation
	 * @param message
	 * @param objects
	 */
	public NoCriteriaWritingException(Annotation annotation,String message,Object... objects) {
		super(annotation + " 注解写法不标准 ," + message,objects);
	}
	
	
	/**
	 * {@link #NoCriteriaWritingException(Class, String)}
	 * @see SpiderRuntimeException#TrafficRuntimeException(String, Object...)
	 * @param method
	 * @param message
	 * @param objects
	 */
	public NoCriteriaWritingException(Method method,String message,Object... objects) {
		super(method + " 方法写法不标准 ," + message,objects);
	}
	
	/**
	 * @param message
	 * @param objects
	 */
	public NoCriteriaWritingException(String message, Object... objects) {
		super(message, objects);
	}


	/**
	 * @param message
	 * @param cause
	 * @param objects
	 */
	public NoCriteriaWritingException(String message, Throwable cause,
			Object... objects) {
		super(message, cause, objects);
	}


	/**
	 * @param message
	 * @param cause
	 */
	public NoCriteriaWritingException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public NoCriteriaWritingException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public NoCriteriaWritingException(Throwable cause) {
		super(cause);
	}
	
	

}
