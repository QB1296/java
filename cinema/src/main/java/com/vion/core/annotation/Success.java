package com.vion.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <b>功能描述</b> <br>
 * 成功标识作用于SpringMVC controller 方法。如果未出现异常,框架将{@link SuccessedReplyModel}已JSON方式返回.
 * @author YUJB
 * @date 2014年6月11日 上午11:41:15
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Success {
	String Describe() default "";
}
