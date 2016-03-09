package com.vion.core.poi;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <b>功能描述</b> <br>
 * 标识行返回
 * @author YUJB
 * @date 2014年5月13日 下午1:03:02
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PoiRow {
	
	int start();
	int end() default -1;
}
