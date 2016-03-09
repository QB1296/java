/**
 * 文件名：HibernateSQLFinderResult.java  
 *  
 * 版本信息：  
 * 日期：2013-6-7  
 * Copyright(c)  http://www.vion.com <br>
 * 版权所有  
 */	
 
package com.vion.core.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.vion.core.dao.FinderResult;
import com.vion.core.dao.ResultType;
import com.vion.core.dao.page.IpagedQuery;
import com.vion.core.dao.page.OriginPagedResult;
import com.vion.core.dao.page.PageInfo;
import com.vion.core.dao.page.PagedResult;
import com.vion.core.domain.ExtLoaderResolver;
import com.vion.core.util.Strings;

/**
 * <b>功能描述</b> <br>
 * hibernate SQL查询结果
 * @author YUJB
 * @date 2014-6-13 下午12:47:59
 */
public class HibernateSQLFinderResult implements FinderResult{
	
	private Session session;
	
	private String sql;
	
	private Object[] parameterValues;
	
	private String[] parameterNames;
	
	private boolean isUseQueryCache = false;
	
	private String cacheRegion;
	

	public HibernateSQLFinderResult(Session session,String sql) {
		this.session = session;
		this.sql = sql;
	}
	
	
	/**
	 * @param parameterValues the parameterValues to set
	 */
	public void setParameterValues(Object... parameterValues) {
		this.parameterValues = parameterValues;
	}
	
	
	/**
	 * @param parameterNames the parameterNames to set
	 */
	public void setParameterNames(String... parameterNames) {
		this.parameterNames = parameterNames;
	}
	
	/* (non-Javadoc)
	 * @see com.vion.core.dao.FinderResult#originResult()*/
	public Object originResult() {
		return result(null);
	}

	/* (non-Javadoc)
	 * @see com.vion.core.dao.FinderResult#result(java.lang.Class)*/
	@SuppressWarnings("unchecked")
	public <T> List<T> result(Class<T> clazz) {
		SQLQuery sqlQuery = getSQLQuery();
		setParams(sqlQuery);
		setResultTransformerByClazzType(sqlQuery,clazz);
		
		List<T> list = sqlQuery.list();
		for (T object : list) {
			ExtLoaderResolver.me().setterValues(object);
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.vion.core.dao.FinderResult#uniqueResult(java.lang.Class)*/
	@SuppressWarnings("unchecked")
	public <T> T uniqueResult(Class<T> clazz) {
		SQLQuery sqlQuery = getSQLQuery();
		setParams(sqlQuery);
		setResultTransformerByClazzType(sqlQuery,clazz);
		
		T ret = (T) sqlQuery.uniqueResult();
		ExtLoaderResolver.me().setterValues(ret);
		return ret;
	}
	
	

	/**
	 * 设置query的{@link org.hibernate.Query#setResultTransformer(org.hibernate.transform.ResultTransformer)}
	 * @param query
	 * @param clazz
	 */
	private void setResultTransformerByClazzType(SQLQuery query, Class<?> clazz) {
		if (clazz != null) {
			ResultType resultType = ResultType.getSupportResultType(clazz);
			if (ResultType.Map.name().equals(resultType.name())) {
				query.setResultTransformer(ResultTransformers.aliasToMap(null));
			}
			if (ResultType.POJO.name().equals(resultType.name())) {
				query.setResultTransformer(ResultTransformers.aliasToBean(clazz));
			}
			if (ResultType.Entity.name().equals(resultType.name())) {
				query.addEntity(clazz);
			}
		}
	}



	@SuppressWarnings("unchecked")
	@Override
	public <T> PagedResult<T> result(Class<T> clazz, IpagedQuery pageQuery) {
		SQLQuery sqlQuery = getSQLQuery();
		setParams(sqlQuery);
		setResultTransformerByClazzType(sqlQuery,clazz);
		
		int count = HibernatePageHelper.getCountSQL(session, sql,parameterValues);
		
		sqlQuery.setMaxResults(pageQuery.getPageSize());
		sqlQuery.setFirstResult(pageQuery.getPageSize()*(pageQuery.getPageNumber()-1));
		
		PagedResult<T> pagedResult = new PagedResult<T>();
		PageInfo pageInfo = new PageInfo();
		pageInfo.setRecordCount(count);
		pageInfo.setPageSize(pageQuery.getPageSize());
		pageInfo.setPageNumber(pageQuery.getPageNumber());
		pagedResult.setPage(pageInfo);
		pagedResult.setResults(sqlQuery.list());
		
		return pagedResult;
	}


	@Override
	public OriginPagedResult originResult(IpagedQuery pageQuery) {
		SQLQuery sqlQuery = getSQLQuery();
		setParams(sqlQuery);
		
		int count = HibernatePageHelper.getCountSQL(session, sql,parameterValues);
		
		sqlQuery.setMaxResults(pageQuery.getPageSize());
		sqlQuery.setFirstResult(pageQuery.getPageSize()*(pageQuery.getPageNumber()-1));
		
		OriginPagedResult pagedResult = new OriginPagedResult();
		PageInfo pageInfo = new PageInfo();
		pageInfo.setRecordCount(count);
		pageInfo.setPageSize(pageQuery.getPageSize());
		pageInfo.setPageNumber(pageQuery.getPageNumber());
		pagedResult.setPage(pageInfo);
		pagedResult.setResults(sqlQuery.list());
		
		return pagedResult;
	}
	
	/**
	 * 得到SQL query,处理?占位符不能处理 param value 为集合的问题
	 * @return
	 */
	private SQLQuery getSQLQuery(){
		SQLQuery query = HibernateQueryHelper.getSQLQuery(session, sql, parameterNames, parameterValues);
		if (isUseQueryCache) {
			query.setCacheable(true);
		}
		if (!Strings.isEmpty(cacheRegion)) {
			query.setCacheRegion(cacheRegion);
		}
		return query;
	}
	
	private void setParams(Query query){
		HibernateQueryHelper.setParams(query, parameterNames, parameterValues);
	}
	
	/* (non-Javadoc)
	 * @see com.vion.core.dao.FinderResult#useQueryCache()*/
	@Override
	public void useQueryCache() {
		isUseQueryCache = true;
	}


	/* (non-Javadoc)
	 * @see com.vion.core.dao.FinderResult#setCacheRegion(java.lang.String)*/
	@Override
	public void setCacheRegion(String cacheRegion) {
		this.cacheRegion = cacheRegion;
	}

}
