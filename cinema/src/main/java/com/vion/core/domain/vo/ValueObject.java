/**
 * 文件名：ValueObject.java  
 *  
 * 版本信息：  
 * 日期：2014-6-5  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.domain.vo;

import java.io.Serializable;

/**
 * <b>功能描述</b> <br>
 * 值对象,标识接口。正常所有的数据传输对象都应该实现此接口，目前数据访问层可支持直接返回valueObject对象
 * @author YUJB
 * @date 2014-6-5 上午10:31:18
 */
public interface ValueObject extends Serializable{
	
}