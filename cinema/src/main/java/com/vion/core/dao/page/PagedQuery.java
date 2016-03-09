/**
 * 文件名：PagedQuery.java  
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
public class PagedQuery implements IpagedQuery{
	
	/** 第几页 从1开始  */
	private Integer pageNumber;
	
	/** 每页条数  */
	private Integer pageSize;

	@Override
	public Integer getPageNumber() {
		return pageNumber;
	}

	@Override
	public IpagedQuery setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
		return this;
	}

	@Override
	public Integer getPageSize() {
		return pageSize;
	}

	@Override
	public IpagedQuery setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
		return this;
	}
	
	public boolean isNull(){
		if (pageSize == null && pageNumber == null) {
			return true;
		}
		return false;
	}
	
	/**
	 * 初始化缺省参数
	 */
	public void initDefalutValue(){
		pageSize = 10;
		pageNumber = 1;
	}


}
