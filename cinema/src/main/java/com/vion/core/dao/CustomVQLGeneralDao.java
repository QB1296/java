/**
 * 文件名：CustomVQLGeneralDao.java  
 *  
 * 版本信息：  
 * 日期：2015年4月16日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.dao;


/**
 * <b>功能描述</b> <br>
 * 自定义查询语言
 * @author YUJB
 * @date 2015年4月16日 下午1:54:28
 */
public  interface CustomVQLGeneralDao {
	
	public boolean isSupportQLType(String qlType);

	public FinderResult findByVQL(String vql);
	
}
