/**
 * 文件名：HibernateQueryHelper.java  
 *  
 * 版本信息：  
 * 日期：2014年7月21日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.hibernate;



import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.vion.core.exception.NoCriteriaWritingException;
import com.vion.core.util.Strings;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2014年7月21日 下午1:30:11
 */
public class HibernateQueryHelper {
	
	/**
	 * 得到HQL query,处理?占位符不能处理 param value 为集合的问题
	 * @return
	 */
	public  static Query getHQLQuery(Session session,String hql,String[] parameterNames,Object[] parameterValues){
		String rettypeQL = rettypeQL(hql,parameterNames,parameterValues);
		Query query = session.createQuery(rettypeQL);
		return query;
	}
	
	/**
	 * 得到SQL query,处理?占位符不能处理 param value 为集合的问题
	 * @return
	 */
	public  static SQLQuery getSQLQuery(Session session,String hql,String[] parameterNames,Object[] parameterValues){
		String rettypeQL = rettypeQL(hql,parameterNames,parameterValues);
		SQLQuery query = session.createSQLQuery(rettypeQL);
		return query;
	}
	
	
	private static int getNamedParamSize(String ql){
		int count = 0;
		if(!Strings.isEmpty(ql)){
			ql = ql.replace("(", " ");
			ql = ql.replace(")", " ");
			String[] split = ql.split(" ");
			List<String> tempList = new ArrayList<String>();
			for (String one : split) {
				if (one.startsWith(":")) {
					if (!tempList.contains(one)) {
						tempList.add(one);
						count ++;
					}
				}
			}
		}
		
		return count;
	}
	
	
	
	private static void  validretypeQL(String ql,String[] parameterNames,Object[] parameterValues){
		int namedParamSize = ql.split("\\:").length;
		if(ql.indexOf(":")== -1 && namedParamSize == 1){
			namedParamSize = 0;
		}else if (ql.lastIndexOf(":") != ql.length()-1) {
			namedParamSize --;
		}
		int namedParamSize2 = getNamedParamSize(ql);
		int paramSize = ql.split("\\?").length;
		if(ql.indexOf("?")== -1 && paramSize == 1){
			paramSize = 0;
		}else if (ql.lastIndexOf("?") != ql.length()-1) {
			paramSize --;
		}
		
		int parameterValuesSize = 0;
		if (parameterValues != null) {
			parameterValuesSize = parameterValues.length;
		}
		int paramNamesSize = 0;
		if (parameterNames != null) {
			paramNamesSize = parameterNames.length;
		}
		
		if (parameterNames != null && paramSize > 0) {
			throw new IllegalArgumentException("[" + ql + "]" + ":name 和  ? 两种方式不能混用！");
		}
		
		if (paramSize > 0 && paramSize != parameterValuesSize) {
			throw new IllegalArgumentException("[" + ql + "]" + "查询参数个数和给定的参数个数值不符,[?]含有" + "[" + paramSize + "]个,[参数值]含有" + "[" + parameterValuesSize + "]个");
		}
		
		if (namedParamSize > 0) {
			if ((namedParamSize != paramNamesSize && namedParamSize2 != paramNamesSize) && namedParamSize != parameterValuesSize) {
				throw new IllegalArgumentException("[" + ql + "]" + "查询参数个数和给定的参数个数值不符,"
						+ "[:]含有" + "[" + namedParamSize + "]个,"
						+ "[:]去除相同含有" + "[" + namedParamSize2 + "]个,"
						+ "[参数]含有" + "[" + paramNamesSize + "]个,"
						+ "[参数值]含有" + "[" + parameterValuesSize + "]个");
			}
			
		}
		
		
	}
	
	
	public  static String rettypeQL(String ql,String[] parameterNames,Object[] parameterValues){
		validretypeQL(ql,parameterNames,parameterValues);
		Integer index = null;
		//保证named之后的参数都为named 不能出现？
		boolean isNamedAfter = false;
		/*如果不是named查询*/
		if (parameterNames == null && parameterValues != null) {
			for (int i = 0; i < parameterValues.length; i++) {
				if (parameterValues[i] instanceof Collection || isNamedAfter) {
					if (isNamedAfter) {
						index = ql.indexOf("?", index + 1);
					}else {
						isNamedAfter = true;
						for (int j = 0; j <= i; j++) {
							if (index == null) {
								index = ql.indexOf("?");
							}else {
								index = ql.indexOf("?", index + 1);
							}
						}
					}
					String beforeHQL = ql.substring(0, index);
					String middleHQL = ":vionListParam" + i;
					String endHQL = ql.substring(index + 1, ql.length());
					ql = beforeHQL + middleHQL + endHQL;
				}
				
			}
		}
		return ql;
	}
	
	
	
	public static void setParams(Query query,String[] parameterNames,Object[] parameterValues){
		if (parameterValues!= null) {
			boolean isNamedAfter = false;
			for (int i = 0; i < parameterValues.length; i++) {
				if (parameterNames ==null) {
					String paramName = "vionListParam" + i;
					if (parameterValues[i] instanceof Collection) {
						isNamedAfter = true;
						query.setParameterList(paramName, (Collection<?>)parameterValues[i]);
					}else if(isNamedAfter){
						query.setParameter(paramName, parameterValues[i]);
					}else {
						if (parameterValues[i] instanceof Date) {
							query.setTimestamp(i, (Date)parameterValues[i]);
						}else if(parameterValues[i] instanceof Integer){
							query.setInteger(i, Integer.valueOf(parameterValues[i].toString()));
						}else if(parameterValues[i] instanceof String){
							query.setString(i, parameterValues[i].toString());
						}else if(parameterValues[i] instanceof Long){
							query.setLong(i, Long.valueOf(parameterValues[i].toString()));
						}else {
							query.setParameter(i, parameterValues[i]);
						}
					}
				}else {
					try {
						if (parameterValues[i] instanceof Collection) {
							query.setParameterList(parameterNames[i], (Collection<?>)parameterValues[i]);
						}else {
							query.setParameter(parameterNames[i], parameterValues[i]);
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						throw new NoCriteriaWritingException("query参数名称和参数值个数不匹配");
					}
				}
			}
		}
	}
	
}
