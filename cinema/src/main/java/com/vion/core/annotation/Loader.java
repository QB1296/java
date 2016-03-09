/**
 * 文件名：ExtPOloader.java  
 *  
 * 版本信息：  
 * 日期：2013-6-26  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2013-6-26 上午09:41:41
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Loader {
	
	String value();
	
	String[] params();
	
	String name() default "";
}
