/**
 * 文件名：BasicDynmicAble.java  
 *  
 * 版本信息：  
 * 日期：2015年1月8日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.basic.annotaion;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2015年1月8日 上午11:08:23
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface BasicDynmicAble {
	
	public Class<?>[] value();
}
