/**
 * 文件名：IPage.java  
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
public interface IPage extends IpagedQuery{
    /**
     * 一共有多少页
     */
	Integer getPageCount();

    /**
     * 整个查询，一共有多少条记录
     */
	Integer getRecordCount();

    /**
     * 设置整个查询一共有多少条记录
     */
    IPage setRecordCount(Integer recordCount);

    /**
     * 当前页之前，还应该有多少条记录
     */
    Integer getOffset();

    /**
     * @return 是否是第一页
     */
    boolean isFirst();

    /**
     * @return 是否是最后一页
     */
    boolean isLast();

}
