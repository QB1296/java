/**
 * 文件名： SecurityManager.java
 *  
 * 版本信息：  
 * 日期：2015年3月26日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.ganjx.cinema.basic.security;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <b>功能描述</b> <br>
 * 
 * @author GANJX
 * @date 2015年3月26日 下午5:45:10
 */
@Inherited
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = { ElementType.METHOD,ElementType.TYPE })
public @interface Security {
	
	/**
	 * @return
	 */
	public boolean filter() default true;
}
