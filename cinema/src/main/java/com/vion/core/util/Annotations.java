package com.vion.core.util;

import java.lang.annotation.Annotation;

import org.springframework.util.ClassUtils;


/**
 * <b>功能描述</b> <br>
 * 注解操作工具类
 * @author YUJB
 * @date 2014-6-16 下午3:18:38
 */
public abstract class Annotations extends org.springframework.core.annotation.AnnotationUtils{
	
	
	
	/**
	 * 在注解候选列表中找到第一个匹配的注解返回
	 * @param annotations 注解候选列表
	 * @param annotationType 注解类型
	 * @return 匹配annotationType注解,如果没有找到返回<code>null</code>
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Annotation> T getAnnotation(Annotation[] annotations,Class<T> annotationType){
		for (Annotation annotation : annotations) {
			if(ClassUtils.isAssignable(annotationType, annotation.getClass())){
				return (T)annotation;
			}
		}
		return null;
	}
	
	
}
