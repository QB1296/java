/**
 * 文件名：PagedResolver.java  
 *  
 * 版本信息：  
 * 日期：2014年6月16日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.spring;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

import com.vion.core.annotation.PagedValue;
import com.vion.core.dao.page.PagedQuery;

/**
 * <b>功能描述</b> <br>
 * pagedQuery解析器,在controller中可以直接使用<code>@PagedValue PagedQuery pagedQuery</code>得到pagedQuery对象
 * @author YUJB
 * @date 2014年6月16日 上午9:25:30
 */
public class PagedQueryResolver implements WebArgumentResolver{
	
	private String pageNumberName = "pageNumber";
	
	private String pageSizeName = "pageSize";
	
	private int defaultPageSize = 10;
	
	private int defaultPageNumber = 1;
	
	private transient Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public Object resolveArgument(MethodParameter parameter,
			NativeWebRequest webRequest) throws Exception {
		Class<?> parameterType = parameter.getParameterType();
		PagedValue pageInfo = parameter.getParameterAnnotation(PagedValue.class);
		if (pageInfo == null && ! parameterType.isAssignableFrom(PagedQuery.class)) {
			return WebArgumentResolver.UNRESOLVED;
		}
		String pageNumber = webRequest.getParameter(pageNumberName);
		String pageSize = webRequest.getParameter(pageSizeName);
		if (pageNumber == null || pageSize == null) {
			logger.info("请求【 " + webRequest.getNativeRequest(HttpServletRequest.class).getRequestURI() + "】 pageNumber或pageSize为空!");
			return convertPageConvert(defaultPageNumber,defaultPageSize);
		}
		return convertPageConvert(Integer.valueOf(pageNumber),Integer.valueOf(pageSize));
	}
	
	
	protected PagedQuery convertPageConvert(int pageNumber,int pageSize){
		PagedQuery pq = new PagedQuery();
		pq.setPageNumber(pageNumber);
		pq.setPageSize(pageSize);
		return pq;
	}

	public String getPageNumberName() {
		return pageNumberName;
	}

	public void setPageNumberName(String pageNumberName) {
		this.pageNumberName = pageNumberName;
	}

	public String getPageSizeName() {
		return pageSizeName;
	}

	public void setPageSizeName(String pageSizeName) {
		this.pageSizeName = pageSizeName;
	}


	public int getDefaultPageSize() {
		return defaultPageSize;
	}


	public void setDefaultPageSize(int defaultPageSize) {
		this.defaultPageSize = defaultPageSize;
	}


	public int getDefaultPageNumber() {
		return defaultPageNumber;
	}


	public void setDefaultPageNumber(int defaultPageNumber) {
		this.defaultPageNumber = defaultPageNumber;
	}
	
	
	
	

}
