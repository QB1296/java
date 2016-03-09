/**
 * 文件名：ExtLoaderResolver.java  
 *  
 * 版本信息：  
 * 日期：2014年12月23日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */

package com.vion.core.domain;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import com.vion.core.SystemContext;
import com.vion.core.annotation.ExtPOloader;
import com.vion.core.annotation.Loader;
import com.vion.core.annotation.Loaders;
import com.vion.core.exception.NoCriteriaWritingException;
import com.vion.core.util.BeanUtils;
import com.vion.core.util.Classes;
import com.vion.core.util.Strings;

/**
 * <b>功能描述</b> <br>
 * 对注解@ExtPOloader Loader的支持。数据访问层操作时返回valueObject对象时自动处理额外的数据loader
 * <pre>
 * @Loaders({ @Loader(name = "testLoader", value = "BasicBizDeviceDao!getMonitorSitesByDevicenum", params = { "id" }) })
 *	public class TestBO {
 *	
 *	private ....;
 *
 *	private String testEntityName;
 *
 *	@ExtPOloader(loaderName = "testLoader", spel = "[0].monitorsitename")
 *	public String getTestEntityName() {
 *		return testEntityName;  
 * }
 * </pre>
 * @author YUJB
 * @date 2014年12月23日 上午11:38:02
 */
public class ExtLoaderResolver {

	/** 日志 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public static ExtLoaderResolver me(){
		return new ExtLoaderResolver();
	}
	
	
	/**
	 * 是否支持extLoader(是否半酣Loder或loaders注解)
	 * @param vo
	 * @return true:支持；false:不支持
	 */
	public boolean isExtLoader(Object vo){
		Class<? extends Object> clazz = vo.getClass();
		Loader loader = clazz.getAnnotation(Loader.class);
		Loaders loaders = clazz.getAnnotation(Loaders.class);
		if (loader == null && loaders == null) {
			return false;
		}
		return true;
	}
	
	/**
	 * 处理object,根据@Loader和@ExtPOloader注解注入数据
	 * @param vo 任意的对象
	 */
	public void  setterValues(Object vo) {
		if (vo == null) {
			return ;
		}
		Class<? extends Object> voClass = vo.getClass();

		Map<String, Object> loaderResultMapping = getLoaderResultMapping(voClass,vo);
		for (Field field : voClass.getDeclaredFields()) {
			String fieldName = field.getName();
			Method getterMethod = BeanUtils.getGetterMethod(voClass,
					fieldName);
			if (getterMethod != null) {
				ExtPOloader extPOloader = getterMethod
						.getAnnotation(ExtPOloader.class);
				/* 如果是外部加载的field */
				if (extPOloader != null) {

					Object setterVlaue = null;

					Loader loaderAnnotation = extPOloader.loader();
					String loaderName = extPOloader.loaderName();
					if (!Strings.isEmpty(loaderName)) {

						if (!loaderResultMapping.containsKey(loaderName)) {
							throw new NoCriteriaWritingException(
									loaderAnnotation,
									"不知道的%s,请在class中增加注解%s并声明name为%s",
									loaderName, Loader.class, loaderName);
						}
						setterVlaue = loaderResultMapping.get(loaderName);
					} else {
						setterVlaue = invokeLoader(loaderAnnotation,vo);
					}
					BeanUtils.setterValue(
							vo,
							fieldName,
							retypeReturnValue(setterVlaue,
									extPOloader.spel()));

				} 
			}
		}
	}

	/**
	 * @param voClass
	 * @return
	 */
	private Map<String, Object> getLoaderResultMapping(
			Class<? extends Object> voClass,Object vo) {
		Map<String, Object> loaderResultMapping = new HashMap<String, Object>();
		Loader loader = voClass.getAnnotation(Loader.class);
		Loaders loaders = voClass.getAnnotation(Loaders.class);
		List<Loader> assembleLoaders = new ArrayList<Loader>();
		if (loaders != null) {
			assembleLoaders.addAll(Arrays.asList(loaders.value()));
		}
		if (loader != null) {
			assembleLoaders.add(loader);
		}

		for (Loader oneLoader : assembleLoaders) {
			String loaderName = oneLoader.name();
			Object loaderReturn = invokeLoader(oneLoader,vo);
			loaderResultMapping.put(loaderName, loaderReturn);

		}
		return loaderResultMapping;
	}

	/**
	 * @param classLoaderAnnotation
	 * @return
	 */
	private Object invokeLoader(Loader loaderAnnotation,Object vo) {
		String value = loaderAnnotation.value();

		/* 验证loader value书写正确性 */
		String[] split = value.split("!");
		if (split.length != 2) {
			throw new NoCriteriaWritingException(loaderAnnotation,
					" value值必须是beanName!method的方式");
		}

		Object loader = getLoader(split[0]);

		Method method = getLoaderInvokeMethod(loader.getClass(), split[1]);

		Object[] realParams = getLoaderInvokeMethodParams(loaderAnnotation
				.params(),vo);

		Object setterVlaue = getLoaderInvokeReturn(method, loader, realParams,
				"");

		return setterVlaue;
	}


	/**
	 * @param method
	 * @param loader
	 * @param realParams
	 * @param useField
	 * @return
	 */
	private Object getLoaderInvokeReturn(Method method, Object loader,
			Object[] realParams, String useField) {

		Object setterVlaue = null;
		/* 执行 */
		try {
			setterVlaue = method.invoke(loader, realParams);
		} catch (Exception e) {
			e.printStackTrace();
			throw new NoSuchBeanDefinitionException("class:" + loader.getClass()
					+ " method:" + method + " params:" + realParams + "不能执行");
		}

		return retypeReturnValue(setterVlaue, useField);
	}

	private Object retypeReturnValue(Object returnValue, String useField) {
		if (returnValue == null) {
			return null;
		}
		if (!Strings.isEmpty(useField)) {
			StandardEvaluationContext context = new StandardEvaluationContext(returnValue);
			ExpressionParser parser = new SpelExpressionParser();
			Expression exp = parser.parseExpression(useField);
			Object fieldValue;
			try {
				fieldValue = exp.getValue(context);
				returnValue = fieldValue;
			} catch (EvaluationException e) {
				logger.warn("【" + useField  + "】 错误【" +  e.getMessage() + "】");
				returnValue = null;
			}
		}
		return returnValue;
	}

	/**
	 * @param params
	 * @return
	 */
	private Object[] getLoaderInvokeMethodParams(String[] params,Object vo) {
		Object[] realParams = new Object[params.length];

		/* 得到真实的方法调用参数正确性 @id 等转化成实际的值 */
		for (int i = 0; i < params.length; i++) {
			String oneParam = params[i];
			StandardEvaluationContext context = new StandardEvaluationContext(vo);
			ExpressionParser parser = new SpelExpressionParser();
			Expression exp = parser.parseExpression(oneParam);
			Object realParam = exp.getValue(context);
			realParams[i] = realParam;
		}

		return realParams;
	}

	/**
	 * @param class1
	 * @param string
	 * @return
	 */
	private Method getLoaderInvokeMethod(Class<? extends Object> clazz,
			String loaderMethod) {
		Method method = Classes.getMethodByName(clazz, loaderMethod);

		/* 验证loader bean method正确性 */
		if (method == null) {
			throw new NoCriteriaWritingException(loaderMethod + " 在类"
					+ clazz.getName() + "中不存在");
		}

		return method;
	}

	/**
	 * @param loaderBeanName
	 * @return
	 */
	private Object getLoader(String loaderBeanName) {
		Object loader = SystemContext.getApplicationContext().getBean(
				loaderBeanName);

		/* 验证loader bean Name正确性 */
		if (loader == null) {
			throw new NoSuchBeanDefinitionException(loaderBeanName);
		}

		return loader;
	}

}
