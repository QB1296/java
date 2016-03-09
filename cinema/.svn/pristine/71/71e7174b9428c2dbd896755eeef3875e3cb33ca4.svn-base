/**
 * 文件名：asdf.java  
 *  
 * 版本信息：  
 * 日期：2013-6-5  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.dao.page;

import java.io.Serializable;



/**
 * <b>功能描述</b> <br>
 * 分页信息
 * @author YUJB
 * @date 2014-6-14 上午11:29:54
 */
public class PageInfo implements IPage, Serializable {

    private static final long serialVersionUID = 8848523495013555357L;
    
    /**
     * 改变这个，当每页大小超过 MAX_FETCH_SIZE 时，这个将是默认的 fetchSize
     */
    public static int DEFAULT_PAGE_SIZE = 20;

    /**
     * ResultSet 最大的 fetch size
     */
    public static int MAX_FETCH_SIZE = 200;


    private Integer pageNumber;
    private Integer pageSize;
    private Integer pageCount;
    private Integer recordCount;

    public PageInfo() {
        pageNumber = 1;
        pageSize = DEFAULT_PAGE_SIZE;
    }

    public PageInfo resetPageCount() {
        pageCount = -1;
        return this;
    }

    public Integer getPageCount() {
        if (pageCount < 0)
            pageCount = (int) Math.ceil((double) recordCount / pageSize);
        return pageCount;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getRecordCount() {
        return recordCount;
    }

    @Override
    public PageInfo setPageNumber(Integer pn) {
    	if (1 > pn){
    	}
        pageNumber = pn;
        return this;
    }

    @Override
    public PageInfo setPageSize(Integer pageSize) {
        this.pageSize = (pageSize > 0 ? pageSize : DEFAULT_PAGE_SIZE);
        return resetPageCount();
    }

    @Override
    public PageInfo setRecordCount(Integer recordCount) {
        this.recordCount = recordCount > 0 ? recordCount : 0;
        this.pageCount = (int) Math.ceil((double) recordCount / pageSize);
        return this;
    }

    public Integer getOffset() {
        return pageSize * (pageNumber - 1);
    }

    @Override
    public String toString() {
        return String.format(    "size: %d, total: %d, page: %d/%d",
                                pageSize,
                                recordCount,
                                pageNumber,
                                this.getPageCount());
    }

    public boolean isFirst() {
        return pageNumber == 1;
    }

    public boolean isLast() {
        if (pageCount == 0)
            return true;
        return pageNumber == pageCount;
    }


}