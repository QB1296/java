/**
 * 文件名：IGroupBy.java  
 *  
 * 版本信息：  
 * 日期：2015年1月9日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.dao.finder;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2015年1月9日 下午3:51:03
 */
public interface IGroup {


	public IFilter getHaving();


	/**
	 * @return
	 */
	public String[] getProperties();
}
