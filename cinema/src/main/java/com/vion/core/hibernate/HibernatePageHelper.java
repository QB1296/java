/**
 * 文件名：HibernatePageHelper.java  
 *  
 * 版本信息：  
 * 日期：2014年6月13日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */

package com.vion.core.hibernate;

import java.io.StringReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;
import net.sf.jsqlparser.statement.select.SelectExpressionItem;
import net.sf.jsqlparser.statement.select.SelectVisitor;
import net.sf.jsqlparser.statement.select.Union;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.engine.spi.NamedQueryDefinition;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.hql.internal.ast.QueryTranslatorImpl;

/**
 * <b>功能描述</b> <br>
 * Hibernate分页帮着类,可以根据HQL、SQL得到记录总数
 * @author YUJB
 * @date 2014年6月13日 下午4:37:42
 */
public class HibernatePageHelper {
	

	/**
	 * 得到查询总数的SQL
	 * @param isNamed 是否是命名查询
	 * @param originalHql 原始的HQL
	 * @param sessionFactory sessionFactory
	 * @return
	 */
	private static String getCountSql(Boolean isNamed, String originalHql,
			org.hibernate.SessionFactory sessionFactory) {
		if (isNamed) {
			NamedQueryDefinition namedQuery = ((SessionFactoryImplementor) sessionFactory)
					.getNamedQuery(originalHql);
			originalHql = namedQuery.getQueryString();
		}
		QueryTranslatorImpl queryTranslator = new QueryTranslatorImpl(
				originalHql, originalHql, Collections.EMPTY_MAP,
				(SessionFactoryImplementor) sessionFactory);
		queryTranslator.compile(Collections.EMPTY_MAP, false);
		 
		return getCountSQL(queryTranslator.getSQLString());
	}
	
	
	
	private static String getCountSQL(String sql){
		CCJSqlParserManager parserManager = new CCJSqlParserManager();
		Select select = null;
		try {
			Statement statement = parserManager.parse(new StringReader(sql));
			if (statement instanceof Select) {
				select = (Select) parserManager.parse(new StringReader(sql));
				SelectBody body = select.getSelectBody();
				body.accept(new SelectCountVisitorImpl());
				String countSql = select.toString();
				if(select.getSelectBody() != null && select.getSelectBody() instanceof PlainSelect){
					PlainSelect plainSelect = (PlainSelect) select.getSelectBody();
					if(plainSelect.getGroupByColumnReferences() != null && !plainSelect.getGroupByColumnReferences().isEmpty()){
						countSql = "select count(*) from (" + countSql	+ ") tmp_count_t";
					}
				}
				return countSql;
			}else {
				return sql;
			}
		} catch (JSQLParserException e) {
			throw new RuntimeException(e);
		}
	}
	
	private static void  retypePS(PlainSelect ps){
		SelectExpressionItem selectExpressionItem = new SelectExpressionItem();
		Function function = new Function();
		function.setName("count");
		ExpressionList expressionList = new ExpressionList();
		expressionList.setExpressions(Arrays.asList(new LongValue("1")));
		function.setParameters(expressionList);
		selectExpressionItem.setExpression(function);
		ps.setSelectItems(Arrays.asList(function));
		ps.setOrderByElements(null);
	}
	
	
	public static class SelectCountVisitorImpl implements SelectVisitor {
		

	    public SelectCountVisitorImpl() {
			super();
		}

	    @Override
		public void visit(PlainSelect ps) {
			retypePS(ps);
	    }

	    @Override
		public void visit(Union un) {
			List<?> plainSelects = un.getPlainSelects();
			for (Object object : plainSelects) {
				if (object instanceof PlainSelect) {
					((PlainSelect) object).accept(this);
				} else {
					((Union) object).accept(this);
				}
			}

		}


	}
	
	/**
	 * 基于HQL语句重新设置参数值,name HQL 转化 SQL时 :name 转化成了 ？,调整参数值的位置
	 * @param originalHql
	 * @param sessionFactory
	 * @param parameterNames
	 * @param parameterValues
	 * @return
	 */
	private static Object[] retypeParameterValues(String originalHql,org.hibernate.SessionFactory sessionFactory,String[] parameterNames, Object[] parameterValues){
		
		Object[] retValues = processEnumParamValues(parameterValues);
		
		Map<Integer, Object> map = new HashMap<Integer,Object>();
		
		QueryTranslatorImpl queryTranslator = new QueryTranslatorImpl(
				originalHql, originalHql, Collections.EMPTY_MAP,
				(SessionFactoryImplementor) sessionFactory);
		queryTranslator.compile(Collections.EMPTY_MAP, false);
		
		if(parameterNames != null){
			for (int i = 0; i < parameterNames.length; i++) {
				String paramName = parameterNames[i];
				int[] indexs = queryTranslator.getNamedParameterLocs(paramName);
				Object value = retValues[i];
				for (int j = 0; j < indexs.length; j++) {
					int index = indexs[j];
					map.put(index, value);
				}
			}
		}else {
			return retValues;
		}
		retValues = new Object[map.size()];
		for (Integer index : map.keySet()) {
			retValues[index] = map.get(index);
		}
		
		return retValues;
	}
	
	/**
	 * count(*)总数时过滤enum类型
	 * @param parameterValues
	 * @return
	 */
	private static Object[] processEnumParamValues(Object[] parameterValues){
		if (parameterValues == null) {
			return parameterValues;
		}
		Object[] retValues = new Object[parameterValues.length];
		for (int i = 0; i < retValues.length; i++) {
			if(parameterValues[i] instanceof Enum){
				try {
					retValues[i] = parameterValues[i].getClass().getMethod("getName").invoke(parameterValues[i]);
				} catch (Exception e) {
					retValues[i] = parameterValues[i];
				}
			}else {
				retValues[i] = parameterValues[i];
			}
		}
		return retValues;
	}
	

	/**
	 * 根据HQL得到记录总数
	 * @param session
	 * @param hql
	 * @return
	 */
	public static int getCountHQL(Session session, String hql,
			String[] parameterNames, Object[] parameterValues) {
		String countSql = getCountSql(false, hql, session.getSessionFactory());
		Object[] retVlaues = retypeParameterValues(hql,session.getSessionFactory(),parameterNames,parameterValues);
		SQLQuery sqlQuery = HibernateQueryHelper.getSQLQuery(session, countSql, null, retVlaues);
		HibernateQueryHelper.setParams(sqlQuery, null, retVlaues);
		return ((Number) sqlQuery.uniqueResult()).intValue();
	}
	
	/**
	 * 根据HQL得到记录总数
	 * @param session
	 * @param hql
	 * @return
	 */
	public static int getCountHQL(Session session, String hql,Object[] parameterValues) {
		String countSql = getCountSql(false, hql, session.getSessionFactory());
		SQLQuery createSQLQuery = session.createSQLQuery(countSql);
		HibernateQueryHelper.setParams(createSQLQuery, null, parameterValues);
		return ((Number) createSQLQuery.uniqueResult()).intValue();
	}
	

	/**
	 * 根据HQL名称得到记录总数
	 * @param session
	 * @param hql
	 * @return
	 */
	public static int getCountNamedHQL(Session session, String hql,
			String[] parameterNames, Object[] parameterValues) {
		String countSql = getCountSql(true, hql, session.getSessionFactory());
		SQLQuery createSQLQuery = session.createSQLQuery(countSql);
		int size = parameterNames.length;
		for (int i = 0; i < size; i++) {
			createSQLQuery.setParameter(i, parameterValues[i]);
		}
		return ((Number) createSQLQuery.uniqueResult()).intValue();
	}
	
	/**
	 * 根据SQL语句得到记录总数
	 * @param session
	 * @param sql
	 * @return
	 */
	public static int getCountSQL(Session session, String sql,Object[] parameterValues) {
		String countSql = "select count(*) from (" + sql
		+ ") tmp_count_t";
		SQLQuery createSQLQuery = HibernateQueryHelper.getSQLQuery(session, countSql, null, parameterValues);
		HibernateQueryHelper.setParams(createSQLQuery, null, parameterValues);
		return ((Number) createSQLQuery.uniqueResult()).intValue();
	}

	
}
