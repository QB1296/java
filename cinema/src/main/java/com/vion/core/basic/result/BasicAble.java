/**
 * 文件名：BasicAble.java  
 *  
 * 版本信息：  
 * 日期：2015年1月4日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.basic.result;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2015年1月4日 下午2:21:41
 */
public interface BasicAble {
	
	@JsonIgnore
	public Boolean isNullLazyProxy();
}
