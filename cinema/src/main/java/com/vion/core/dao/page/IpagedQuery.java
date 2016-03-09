/**
 * 文件名：IpagedQuery.java  
 *  
 * 版本信息：  
 * 日期：2013-6-5  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.dao.page;

/**
 * <b>功能描述</b> <br>
 * 分页信息接口
 * @author YUJB
 * @date 2014-6-14 上午11:28:58
 */
public interface IpagedQuery {

    /**
     * 当前是第几页， 从 1 开始
     */
	Integer getPageNumber();

    /**
     * 设置页码
     */
    IpagedQuery setPageNumber(Integer pageNumber);

    /**
     * 一页可以有多少条记录
     */
    Integer getPageSize();

    /**
     * 设置一页可以有多少条记录
     */
    IpagedQuery setPageSize(Integer pageSize);

}
