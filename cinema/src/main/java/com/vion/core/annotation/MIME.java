package com.vion.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <b>功能描述</b> <br>
 * MIME类型,结合{@link Produces}使用 ,作用于spring　MVC.
 * @author YUJB
 * @date 2014年5月13日 下午1:00:56
 */
@Target({ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MIME {
	
	
	/** all MIME   */
	public static String ALL = "*"+ "/" + "*";
	
	/** application/x-www-form-urlencoded  */
	public static String APPLICATION_FORM_URLENCODED = "application"+ "/" + "x-www-form-urlencoded";
	
	/** application/json  */
	public static String APPLICATION_JSON = "application"+ "/" + "json";
	
	/** application/octet-stream  */
	public static String APPLICATION_OCTET_STREAM = "application"+ "/" + "octet-stream";
	
	/** application/xml  */
	public static String APPLICATION_XML = "application"+ "/" + "xml";
	
	/** image/gif  */
	public static String IMAGE_GIF = "image"+ "/" +  "gif";
	
	/** image/jpeg  */
	public static String IMAGE_JPEG = "image"+ "/" +  "jpeg";
	
	/** image/png  */
	public static String IMAGE_PNG = "image"+ "/" +  "png";
	
	/** multipart/form-data  */
	public static String MULTIPART_FORM_DATA = "multipart"+ "/" + "form-data";
	
	/** text/html  */
	public static String TEXT_HTML = "text"+ "/" + "html";
	
	/** text/plain  */
	public static String TEXT_PLAIN = "text"+ "/" + "plain";
	
	/** text/xml */
	public static String TEXT_XML = "text"+ "/" + "xml";
	
}
