/**
 * 文件名：SpiderException.java  
 *  
 * 版本信息：  
 * 日期：2013-6-27  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.exception;

/**
 * <b>功能描述</b> <br>
 * 运行时异常
 * @author YUJB
 * @date 2014-6-13 下午04:23:04
 */
public class SpiderRuntimeException extends RuntimeException{

	/**   */
	private static final long serialVersionUID = 1L;

	
	/**
	 * 可格式化的异常信息   message="【%s】抛出了异常" objects="这个方法"<br>
	 * 最终将格式化为 ： 【这个方法】抛出了异常
	 * @sess {@link String#format(String, Object...)}
	 * @param message 异常信息
	 * @param objects message format对应的参数信息
	 */
	public SpiderRuntimeException(String message, Object...objects) {
		this(String.format(message, objects));
	}
	
	/**
	 * 
	 */
	public SpiderRuntimeException(String message,Throwable cause, Object...objects) {
		this(String.format(message, objects),cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public SpiderRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public SpiderRuntimeException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public SpiderRuntimeException(Throwable cause) {
		super(cause);
	}
	
	
	

}
