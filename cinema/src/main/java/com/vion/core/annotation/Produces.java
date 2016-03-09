package com.vion.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



/**
 * <b>功能描述</b> <br>
 * 一般结合{@link MIME}使用。例如<code>@Produces(Value=MIME.APPLICATION_JSON)</code>
 * 将以JSON形式返回结果
 * @author YUJB
 * @date 2014年5月13日 下午1:03:02
 */
@Target({ElementType.METHOD,ElementType.LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Produces {
	
	String value();
	
}
