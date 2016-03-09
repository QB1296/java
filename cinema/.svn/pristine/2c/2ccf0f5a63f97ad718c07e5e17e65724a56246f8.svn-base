/**
 * 文件名：BasicJoin.java  
 *  
 * 版本信息：  
 * 日期：2010年2月4日  
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

import com.vion.core.dao.finder.IJoin.JoinType;
import com.vion.core.domain.entity.IEntity;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2010年2月4日 下午6:52:29
 */
@Target(value={ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface BasicJoin {
	public Class<? extends IEntity> entity();
	public String column();
	public JoinType joinType();
}

