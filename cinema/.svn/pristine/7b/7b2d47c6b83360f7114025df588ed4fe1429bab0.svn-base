/**
 * 文件名：HibernateHQLFinderResult.java  
 *  
 * 版本信息：  
 * 日期：2013-6-7  
 * Copyright(c)  http://www.vion.com <br>
 * 版权所有  
 */	
 
package com.vion.core.hibernate;

import java.util.List;

import org.hibernate.Query;
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
 *
 * @author YUJB
 * @date 2014-6-13 下午04:00:11
 */
public class HibernateHQLFinderResult implements FinderResult{
	
	private Session session;
	
	private String hql;
	
	private Object[] parameterValues;
	
	private String[] parameterNames;
	
	private boolean isNamed = false;
	
	private boolean isUseQueryCache = false;
	
	private String cacheRegion;
	

	public HibernateHQLFinderResult(Session session,String hql) {
		this(session, hql, false);
	}
	
	
	public HibernateHQLFinderResult(Session session,String hql, boolean isNamed) {
		this.session = session;
		this.hql = hql;
		this.isNamed = isNamed;
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
	public void setParameterNames(String[] parameterNames) {
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
		Query  query = null;
		if (isNamed) {
			query = getNamedQuery();
		}else {
			query = getHQLQuery();
		}
		setParams(query);
		setResultTransformerByClazzType(query, clazz);
		List<T> list = query.list();
		if (list.size() > 0) {
			boolean extLoader = ExtLoaderResolver.me().isExtLoader(list.get(0));
			if (extLoader) {
				for (T object : list) {
					ExtLoaderResolver.me().setterValues(object);
				}
			}
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.vion.core.dao.FinderResult#uniqueResult(java.lang.Class)*/
	@SuppressWarnings("unchecked")
	public <T> T uniqueResult(Class<T> clazz) {
		Query  query = null;
		if (isNamed) {
			query = getNamedQuery();
		}else {
			query = getHQLQuery();
		}
		setParams(query);
		setResultTransformerByClazzType(query, clazz);
		T ret = (T) query.uniqueResult();
		ExtLoaderResolver.me().setterValues(ret);
		return ret;
	}
	
	
	/**
	 * 得到HQL query,处理?占位符不能处理 param value 为集合的问题
	 * @return
	 */
	private Query getHQLQuery(){
		Query query = HibernateQueryHelper.getHQLQuery(session, hql, parameterNames, parameterValues);
		if (isUseQueryCache) {
			query.setCacheable(true);
		}
		if (!Strings.isEmpty(cacheRegion)) {
			query.setCacheRegion(cacheRegion);
		}
		return query;
	}
	
	
	
	private Query getNamedQuery(){
		Query query = session.getNamedQuery(hql);
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
	
	/**
	 * 设置query的{@link org.hibernate.Query#setResultTransformer(org.hibernate.transform.ResultTransformer)}
	 * @param query
	 * @param clazz
	 */
	private void setResultTransformerByClazzType(Query query, Class<?> clazz) {
		if (clazz != null) {
			ResultType resultType = ResultType.getSupportResultType(clazz);
			String[] propertyNames = null;

			if (ResultType.Map.name().equals(resultType.name())) {
				query.setResultTransformer(ResultTransformers
						.aliasToMap(propertyNames));
			}
			if (ResultType.POJO.name().equals(resultType.name())) {
				query.setResultTransformer(ResultTransformers
						.aliasToBean(clazz,propertyNames));
			}
			if (ResultType.Entity.name().equals(resultType.name())) {
				query.setResultTransformer(ResultTransformers.aliasToEntity(clazz,propertyNames));
			}
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public <T> PagedResult<T> result(Class<T> clazz, IpagedQuery pageQuery) {
		Query  query = null;
		int count = 0;
		if (isNamed) {
			query = session.getNamedQuery(hql);
			count = HibernatePageHelper.getCountNamedHQL(session, hql,parameterNames,parameterValues);
		}else {
			query = getHQLQuery();
			count = HibernatePageHelper.getCountHQL(session, hql,parameterNames,parameterValues);
		}
		query.setMaxResults(pageQuery.getPageSize());
		query.setFirstResult(pageQuery.getPageSize()*(pageQuery.getPageNumber()-1));
		
		setParams(query);
		setResultTransformerByClazzType(query, clazz);
		
		PagedResult<T> pagedResult = new PagedResult<T>();
		PageInfo pageInfo = new PageInfo();
		pageInfo.setRecordCount(count);
		pageInfo.setPageSize(pageQuery.getPageSize());
		pageInfo.setPageNumber(pageQuery.getPageNumber());
		pagedResult.setPage(pageInfo);
		pagedResult.setResults(query.list());
		
		return pagedResult;
	}

	@Override
	public OriginPagedResult originResult(IpagedQuery pageQuery) {
		Query  query = null;
		int count = 0;
		if (isNamed) {
			query = session.getNamedQuery(hql);
			count = HibernatePageHelper.getCountNamedHQL(session, hql,parameterNames,parameterValues);
		}else {
			query = getHQLQuery();
			count = HibernatePageHelper.getCountHQL(session, hql,parameterNames,parameterValues);
		}
		query.setMaxResults(pageQuery.getPageSize());
		query.setFirstResult(pageQuery.getPageSize()*(pageQuery.getPageNumber()-1));
		setParams(query);
		
		OriginPagedResult pagedResult = new OriginPagedResult();
		PageInfo pageInfo = new PageInfo();
		pageInfo.setRecordCount(count);
		pageInfo.setPageSize(pageQuery.getPageSize());
		pageInfo.setPageNumber(pageQuery.getPageNumber());
		pagedResult.setPage(pageInfo);
		pagedResult.setResults(query.list());
		
		return pagedResult;
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
