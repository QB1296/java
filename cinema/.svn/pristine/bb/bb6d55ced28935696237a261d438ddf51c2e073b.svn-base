/**
 * 文件名：PageResults.java  
 *  
 * 版本信息：  
 * 日期：2014年6月13日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.dao.page;

import java.util.Collections;
import java.util.List;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2014-6-14 下午3:32:41
 */
public class PagedResult <T> {
	
	List<T> results;
	
	IPage page;

	public List<T> getResults() {
		return results;
	}

	public void setResults(List<T> results) {
		this.results = results;
	}

	public IPage getPage() {
		return page;
	}

	public void setPage(IPage page) {
		this.page = page;
	}
	
	
	/**
	 * 返回空的分页查询结果
	 * @param clazz
	 * @return
	 */
	public static <K>  PagedResult<K> NullResult(Class<K> clazz,PagedQuery pagedQuery){
		PagedResult<K> pagedResult = new PagedResult<K>();
		PageInfo pageInfo = new PageInfo();
		pageInfo.setRecordCount(0);
		pageInfo.setPageSize(pagedQuery.getPageSize());
		pageInfo.setPageNumber(0);
		
		pagedResult.setResults(Collections.<K> emptyList());
		pagedResult.setPage(pageInfo);
		
		return pagedResult;
	}
	
	
	public static <K>  PagedResult<K> NullResult(Class<K> clazz){
		PagedResult<K> pagedResult = new PagedResult<K>();
		PageInfo pageInfo = new PageInfo();
		pageInfo.setRecordCount(0);
		pageInfo.setPageSize(10);
		pageInfo.setPageNumber(0);
		
		pagedResult.setResults(Collections.<K> emptyList());
		pagedResult.setPage(pageInfo);
		
		return pagedResult;
	}
	
	public <K> PagedResult<K> convert(Class<K> clazz,List<K> results){
		PagedResult<K> ret = new PagedResult<K>();
		ret.setResults(results);
		ret.setPage(this.getPage());
		
		return ret;
	}
}
