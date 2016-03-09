/**
 * 文件名：HibernateFinderResult.java  
 *  
 * 版本信息：  
 * 日期：2013-6-5  
 * Copyright(c)  http://www.vion.com <br>
 * 版权所有  
 */

package com.vion.core.hibernate;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;

import com.vion.core.dao.JPAFinderProcessor;
import com.vion.core.dao.ResultType;
import com.vion.core.dao.finder.Field;
import com.vion.core.dao.finder.IFinder;
import com.vion.core.dao.page.IpagedQuery;
import com.vion.core.dao.page.OriginPagedResult;
import com.vion.core.dao.page.PageInfo;
import com.vion.core.dao.page.PagedResult;
import com.vion.core.domain.ExtLoaderResolver;
import com.vion.core.util.Strings;

/**
 * <b>功能描述</b> <br>
 * hibernate实现的FinderResult
 * 
 * @author YUJB
 * @date 2014-6-13 下午02:24:06
 */
public class HibernateFinderResult extends JPAFinderProcessor {

	/** session */
	private Session session;

	private boolean isUseQueryCache = false;
	
	private String cacheRegion;
	
	/**
	 * @param session unitOfWork
	 * @param finder 查询信息
	 */
	public HibernateFinderResult(Session session, IFinder finder) {
		super(finder);
		this.session = session;
	}
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vion.core.dao.FinderProcessor#finderList(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> result(Class<T> clazz) {

		Query query = getQuery();
		setParams(query, getParamValues());
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vion.core.dao.FinderProcessor#finderUnique(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	public <T> T uniqueResult(Class<T> clazz) {

		Query query = getQuery();
		setParams(query, getParamValues());
		setResultTransformerByClazzType(query, clazz);

		T ret = (T) query.uniqueResult();
		ExtLoaderResolver.me().setterValues(ret);
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vion.core.dao.FinderProcessor#originResult()
	 */
	public Object originResult() {
		return result(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vion.core.dao.FinderProcessor#finderMap()
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> finderMap() {
		Query query = getQuery();

		List<Field> fields = finder.getFields();
		String[] keys = new String[fields.size()];
		for (int i = 0; i < fields.size(); i++) {
			keys[i] = fields.get(i).getProperty();
		}

		query.setResultTransformer(ResultTransformers.aliasToMap(keys));

		return query.list();
	}

	/**
	 * query中设置参数
	 * @param query
	 * @param params
	 */
	private void setParams(Query query, List<Object> params) {
		if (params != null) {
			int i = 1;
			for (Object o : params) {
				query.setParameter(PREFIX_PARAM + Integer.toString(i++), o);
			}
		}
	}
	
	private Query getQuery(){
		Query query = session.createQuery(generateQL());
		if (isUseQueryCache) {
			query.setCacheable(true);
		}
		if (!Strings.isEmpty(cacheRegion)) {
			query.setCacheRegion(cacheRegion);
		}
		return query;
	}

	/**
	 * 设置query的{@link org.hibernate.Query#setResultTransformer(org.hibernate.transform.ResultTransformer)}
	 * @param query
	 * @param clazz
	 */
	private void setResultTransformerByClazzType(Query query, Class<?> clazz) {
		if (clazz != null) {
			ResultType resultType = ResultType.getSupportResultType(clazz);
			String[] propertyNames = getSelectColumns()
					.toArray(new String[] {});

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
		String hql = generateQL();
		Query query = session.createQuery(hql);
		final int nCount = HibernatePageHelper.getCountHQL(session, hql,getParamValues().toArray());  
		query.setMaxResults(pageQuery.getPageSize());
		query.setFirstResult(pageQuery.getPageSize()*(pageQuery.getPageNumber()-1));
		setParams(query, getParamValues());
		setResultTransformerByClazzType(query, clazz);
		
		PagedResult<T> pagedResult = new PagedResult<T>();
		PageInfo pageInfo = new PageInfo();
		pageInfo.setRecordCount(nCount);
		pageInfo.setPageSize(pageQuery.getPageSize());
		pageInfo.setPageNumber(pageQuery.getPageNumber());
		pagedResult.setPage(pageInfo);
		pagedResult.setResults(query.list());

		return pagedResult;
	}


	@Override
	public OriginPagedResult originResult(IpagedQuery pageQuery) {
		String hql = generateQL();
		Query query = session.createQuery(hql);
		final int nCount = HibernatePageHelper.getCountHQL(session, hql,getParamValues().toArray());  
		query.setMaxResults(pageQuery.getPageSize());
		query.setFirstResult(pageQuery.getPageSize() * pageQuery.getPageNumber()-1);
		setParams(query, getParamValues());
		
		OriginPagedResult pagedResult = new OriginPagedResult();
		PageInfo pageInfo = new PageInfo();
		pageInfo.setRecordCount(nCount);
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
