/**
 * 文件名：BasicDaoImpl.java  
 *  
 * 版本信息：  
 * 日期：2015年1月15日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.basic;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import com.vion.core.basic.annotaion.BasicColumnLocation;
import com.vion.core.basic.annotaion.BasicColumnLocations;
import com.vion.core.basic.annotaion.BasicDynmicAble;
import com.vion.core.basic.annotaion.BasicJoin;
import com.vion.core.basic.annotaion.BasicJoins;
import com.vion.core.basic.result.BasicAble;
import com.vion.core.dao.FinderResult;
import com.vion.core.dao.IGeneralDAO;
import com.vion.core.dao.ResultType;
import com.vion.core.dao.finder.Join;
import com.vion.core.dao.page.IPage;
import com.vion.core.dao.page.IpagedQuery;
import com.vion.core.dao.page.PagedResult;
import com.vion.core.domain.entity.IEntity;
import com.vion.core.exception.SpiderRuntimeException;
import com.vion.core.util.Classes;
import com.vion.core.util.GenericUtils;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2015年1月15日 上午10:01:44
 */
@Component
public class BasicDaoImpl<F extends IBasicFinder> implements BasicDao<F>{

	@Autowired
	protected IGeneralDAO generalDAO;
	
	
	private Map<Class<?>, Class<?>> genericMapper(Class<?> clazz){
		Map<Class<?>, Class<?>> genericMapper = new HashMap<Class<?>, Class<?>>();
		Type[] genericInterfaces = clazz.getGenericInterfaces();
		for (Type type : genericInterfaces) {
			if (type instanceof ParameterizedTypeImpl) {
				ParameterizedTypeImpl pt = ((ParameterizedTypeImpl)type);
				Class<?> genericClzz = (Class<?>) pt.getActualTypeArguments()[0];
				genericMapper.put(pt.getRawType(), genericClzz);
			}
		}
		return genericMapper;
	}
	
	private List<BasicColumnLocation> getAllBasicColumnLocations(Method method){
		List<BasicColumnLocation> rets = new ArrayList<BasicColumnLocation>();
		BasicColumnLocation columnLocation = method.getAnnotation(BasicColumnLocation.class);
		if (columnLocation != null) {
			rets.add(columnLocation);
		}
		BasicColumnLocations locations = method.getAnnotation(BasicColumnLocations.class);
		if (locations != null) {
			BasicColumnLocation[] values = locations.value();
			if (values != null) {
				rets.addAll(Arrays.asList(values));
			}
		}
		return rets;
	}
	
	@SuppressWarnings("unchecked")
	private <T> T getProxy(final Object obj,final Class<T> clazz){
		final Map<Class<?>, Class<?>> genericMapper = genericMapper(clazz);
		final ProxyFactory factory = new ProxyFactory();
		
		if(clazz.isInterface()){
			Method[] methods = clazz.getMethods();
			for (Method method : methods) {
				BasicDynmicAble dynmicAble = method.getAnnotation(BasicDynmicAble.class);
				if (dynmicAble != null) {
					Class<?>[] value = dynmicAble.value();
					factory.setInterfaces(value);
				}
			}
			factory.addInterface(clazz);
		}else {
			factory.setTargetClass(clazz);
			factory.setProxyTargetClass(true);
			factory.setInterfaces(clazz.getInterfaces());
		}
		factory.addAdvice(new MethodInterceptor() {
			
			@Override
			public Object invoke(MethodInvocation mi) throws Throwable {
				Object returnObj = null;
				boolean hasMethod = false;
				/*如果有ColumnLocation注解,标识column获取的位置*/
				List<BasicColumnLocation> locations = getAllBasicColumnLocations(mi.getMethod());
				for (BasicColumnLocation columnLocation : locations) {
					if (columnLocation.entity().isAssignableFrom(obj.getClass())) {
						StandardEvaluationContext context = new StandardEvaluationContext(obj);
						ExpressionParser parser = new SpelExpressionParser();
						Expression exp = parser.parseExpression(columnLocation.value());
						try {
							returnObj = exp.getValue(context);
						} catch (Exception e) {
							return null;
						}
						hasMethod = true;
						break;
					}
				}
				/*如果有没有找到合适的ColumnLocation注解直接取对应的column*/
				if (hasMethod == false) {
					Method method = Classes.getMethodByName(obj.getClass(), mi.getMethod().getName());
					if(method != null){
						returnObj = method.invoke(obj);
						hasMethod = true;
					}
				}
				/*都没有找到*/
				if (hasMethod) {
					if (returnObj == null) {
						return null;
					}
					if(mi.getMethod().getReturnType().isAssignableFrom(List.class)){
						List<Object> result = new ArrayList<Object>();
						List<Object> returnObjs = new ArrayList<Object>((Collection<?>)returnObj);
						Class<?> genericClazz = GenericUtils.getFirstGenericMethodReturnType(mi.getMethod());
						if (genericClazz == null) {
							genericClazz = genericMapper.get(mi.getMethod().getDeclaringClass());
						}
						if (genericClazz != null && returnObjs != null) {
							for (Object object : returnObjs) {
								Object proxy = getProxy(object,genericClazz);
								result.add(proxy);
							}
						}
						return result;
					}
					if (!mi.getMethod().getReturnType().isAssignableFrom(returnObj.getClass())) {
						Type type = mi.getMethod().getGenericReturnType();
						Class<?> clazz = null;
						if(type instanceof Class<?>){
							clazz = (Class<?>)type;
						}else {
							clazz = genericMapper.get(mi.getMethod().getDeclaringClass());
						}
						return getProxy(returnObj,clazz);
					}
					return returnObj;
				}else {
					try {
						return mi.proceed();
					} catch (Exception e) {
						return null;
					}
				}
				
			}
		});
		T proxy = (T)factory.getProxy();
		return proxy;
	}

	/* (non-Javadoc)
	 * @see com.vion.core.basic.BasicBizDao#find(com.vion.core.dao.finder.Finder, java.lang.Class)*/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@Transactional(readOnly = true)
	public <T> List<T> find(F finder, Class<T> clazz) {
		if (BasicAble.class.isAssignableFrom(clazz) && finder.isAutoFetch()) {
			Class<?>[] interfaces = clazz.getInterfaces();
			for (Class<?> interClazz : interfaces) {
				List<BasicJoin> joins = new ArrayList<BasicJoin>();
				BasicJoins basicJoins = interClazz.getAnnotation(BasicJoins.class);
				if (basicJoins != null) {
					joins.addAll(Arrays.asList(basicJoins.value()));
				}
				BasicJoin basicJoin = interClazz.getAnnotation(BasicJoin.class);
				if (basicJoin != null) {
					joins.add(basicJoin);
				}
				for (BasicJoin joinAnno : joins) {
					if (joinAnno.entity().isAssignableFrom(finder.getRootClass())) {
						Join join = new Join(joinAnno.joinType(), joinAnno.column(), true);
						finder.join(join);
					}
				}
			}
		}
		FinderResult fr = generalDAO.find(finder);
		List<T> ret  = new ArrayList<T>();
		if (BasicAble.class.isAssignableFrom(clazz)) {
			List<? extends IEntity> result = fr.result(finder.getRootClass());
			for (final IEntity iEntity : result) {
				T proxy = getProxy(iEntity,clazz);
				ret.add(proxy);
			}
		}
		
		if (ResultType.Map.isSupport(clazz)) {
			ret = (List<T>) fr.result(Map.class);
		}
		
		if (ResultType.POJO.isSupport(clazz)) {
			List<Map> result = (List<Map>) fr.result(Map.class);
			for (Map map : result) {
				T t = com.vion.core.util.BeanUtils.convert2Bo(map, clazz);
				ret.add(t);
			}
		}
		if (ResultType.Primitive.isSupport(clazz)) {
			ret = fr.result(clazz);
		}
		return ret;
	}

	/* (non-Javadoc)
	 * @see com.vion.core.basic.BasicBizDao#find(com.vion.core.dao.finder.Finder, java.lang.Class, com.vion.core.dao.page.PagedQuery)*/
	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> PagedResult<T> find(F finder, Class<T> clazz,
			IpagedQuery pagedQuery) {
		IPage page = null;
		FinderResult fr = generalDAO.find(finder);
		List<T> ret  = new ArrayList<T>();
		if (BasicAble.class.isAssignableFrom(clazz)) {
			PagedResult<? extends IEntity> result = fr.result(finder.getRootClass(),pagedQuery);
			page = result.getPage();
			for (final IEntity iEntity : result.getResults()) {
				T proxy = getProxy(iEntity,clazz);
				ret.add(proxy);
			}
		}
		if (ResultType.Map.isSupport(clazz)) {
			return (PagedResult<T>) fr.result(Map.class,pagedQuery);
		}
		
		if (ResultType.POJO.isSupport(clazz)) {
			PagedResult<Map> result = fr.result(Map.class,pagedQuery);
			page = result.getPage();
			for (Map map : result.getResults()) {
				T t = com.vion.core.util.BeanUtils.convert2Bo(map, clazz);
				ret.add(t);
			}
		}
		if (ResultType.Primitive.isSupport(clazz)) {
			ret = fr.result(clazz);
		}
		PagedResult pagedResult = new PagedResult();
		pagedResult.setPage(page);
		pagedResult.setResults(ret);
		return pagedResult;
	}

	/* (non-Javadoc)
	 * @see com.vion.core.basic.BasicBizDao#findUniqued(com.vion.core.dao.finder.Finder, java.lang.Class)*/
	@Override
	@Transactional(readOnly = true)
	public <T> T findUniqued(F finder, Class<T> clazz) {
		List<T> rets = find(finder, clazz);
		if (rets != null && rets.size() == 1) {
			return rets.get(0);
		}
		throw new SpiderRuntimeException("不存在或存在多个结果");
	}
}
