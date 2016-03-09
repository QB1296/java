/**
 * 文件名：DaoResultTypeMismatchException.java  
 *  
 * 版本信息：  
 * 日期：2013-6-6  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.dao;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2014-6-14 上午09:25:48
 */
public class DaoResultTypeMismatchException extends RuntimeException{

	/**   */
	private static final long serialVersionUID = 1L;
	

	/**
	 * @param clazz
	 */
	public DaoResultTypeMismatchException(Class<?> clazz) {
		this("不支持" + clazz.toString() + "类型的返回值");
	}
	
	/**
	 * @param clazz
	 */
	public DaoResultTypeMismatchException(Class<?> clazz,String supportClassDesp) {
		this("不支持" + clazz.toString() + "类型的返回值。" + "支持类型" + supportClassDesp);
	}
	

	/**
	 * 
	 */
	public DaoResultTypeMismatchException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DaoResultTypeMismatchException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public DaoResultTypeMismatchException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public DaoResultTypeMismatchException(Throwable cause) {
		super(cause);
	}
	
	
	

}
