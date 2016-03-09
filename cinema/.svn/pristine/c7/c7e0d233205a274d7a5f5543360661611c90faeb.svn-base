/**
 * 文件名：ResultTransformers.java  
 *  
 * 版本信息：  
 * 日期：2013-6-6  
 * Copyright(c)  http://www.vion.com <br>
 * 版权所有  
 */

package com.vion.core.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.PropertyNotFoundException;
import org.hibernate.property.ChainedPropertyAccessor;
import org.hibernate.property.PropertyAccessor;
import org.hibernate.property.PropertyAccessorFactory;
import org.hibernate.property.Setter;
import org.hibernate.transform.ResultTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;

import com.vion.core.SystemContext;
import com.vion.core.domain.entity.IEntity;
import com.vion.core.util.Strings;

/**
 * <b>功能描述</b> <br>
 * 
 * @author YUJB
 * @date 2014-6-13 上午11:10:34
 */
public abstract class ResultTransformers {
	
	public static ResultTransformer aliasToBean(Class<?> target) {
		return aliasToBean(target, null);
	}

	public static ResultTransformer aliasToBean(Class<?> target,
			String[] aliases) {
		return new AliasToBeanResultTransformer(target, aliases);
	}

	public static ResultTransformer aliasToEntity(Class<?> target,
			String[] aliases) {
		return new EntityResultTransformer(target, aliases);
	}

	public static ResultTransformer aliasToMap(String[] keys) {
		return new MapResultTransformer(keys);
	}

	@SuppressWarnings("unchecked")
	public static class MapResultTransformer extends BasicTransformerAdapter {

		Logger logger = LoggerFactory.getLogger(this.getClass());

		private static final long serialVersionUID = 1L;

		private String[] keys;

		public MapResultTransformer(String[] keys) {
			this.keys = keys;
		}

		@SuppressWarnings("rawtypes")
		public Object transformTuple(Object[] tuple, String[] aliases) {
			Map map = new HashMap(tuple.length);

			if (tuple != null && tuple.length > 0) {
				if (IEntity.class.isAssignableFrom(tuple[0].getClass())) {
					logger.info("hibernate 查询结果为entity类不能转换成Map");
				}
			}

			if (keys == null) {
				for (int i = 0; i < tuple.length; i++) {
					String alias = aliases[i];
					if (alias != null) {
						map.put(alias, tuple[i]);
					}
				}
				return map;
			}
			for (int i = 0; i < this.keys.length; i++) {
				String key = this.keys[i];
				if (key != null) {
					map.put(key, tuple[i]);
				}
			}

			return map;
		}

	}

	/**
	 * <b>功能描述</b> <br>
	 * Alias到Entity的result转换,其中Alias可手动指定。
	 * @author YUJB
	 * @date 20134-6-16 下午04:44:34
	 */
	public static class EntityResultTransformer extends BasicTransformerAdapter {

		Logger logger = LoggerFactory.getLogger(this.getClass());

		private static final long serialVersionUID = 1L;

		private String[] keys;

		private final Class<?> resultClass;

		public EntityResultTransformer(Class<?> resultClass, String[] keys) {
			this.keys = keys;
			this.resultClass = resultClass;
		}

		public Object transformTuple(Object[] tuple, String[] aliases) {
			if (keys == null) {
				keys = aliases;
			}
			if (tuple != null && tuple.length > 0) {
				if (!(tuple[0] instanceof IEntity)) {
					AliasToBeanResultTransformer beanResultTransformer = new AliasToBeanResultTransformer(
							resultClass);
					return beanResultTransformer.transformTuple(tuple, keys);
				}
				return tuple[0];
			}
			return tuple;
		}

	}

	
	/**
	 * <b>功能描述</b> <br>
	 * Alias到Vo Bean的result转换,其中Alias可手动指定
	 * @author YUJB
	 * @date 2014-6-7 下午04:43:25
	 */
	public static class AliasToBeanResultTransformer extends
			BasicTransformerAdapter {

		/**   */
		private static final long serialVersionUID = 1L;
		private final Class<?> resultClass;
		private boolean isInitialized;
		private String[] aliases;
		private Setter[] setters;
		FormattingConversionServiceFactoryBean conversionService = SystemContext.getApplicationContext().getBean(FormattingConversionServiceFactoryBean.class);

		public AliasToBeanResultTransformer(Class<?> resultClass) {
			if (resultClass == null) {
				throw new IllegalArgumentException("resultClass cannot be null");
			}
			isInitialized = false;
			this.resultClass = resultClass;
		}

		public AliasToBeanResultTransformer(Class<?> resultClass,
				String[] aliases) {
			this(resultClass);
			this.aliases = aliases;
		}

		public boolean isTransformedValueATupleElement(String[] aliases,
				int tupleLength) {
			return false;
		}

		public Object transformTuple(Object[] tuple, String[] aliases) {
			Object result = null;
			if (this.aliases == null) {
				this.aliases = aliases;
			}
			/*如果返回的是实体类型*/
			if (tuple != null && tuple.length > 0) {
				if (tuple[0] instanceof IEntity) {
					try {
						result = resultClass.newInstance();
						BeanUtils.copyProperties(tuple[0], result);
						return result;
					} catch (Exception e) {
						throw new HibernateException(
								"Could not instantiate resultclass: "
										+ resultClass.getName());
					} 
				}
			}
			try {
				if (!isInitialized) {
					initialize(this.aliases);
				}
				result = resultClass.newInstance();

				for (int i = 0; i < aliases.length; i++) {
					if (setters[i] != null) {
						Object convert = conversionService.getObject().convert(tuple[i], setters[i].getMethod().getParameterTypes()[0]);
						setters[i].set(result, convert, null);
					}
				}
			} catch (InstantiationException e) {
				throw new HibernateException(
						"Could not instantiate resultclass: "
								+ resultClass.getName());
			} catch (IllegalAccessException e) {
				throw new HibernateException(
						"Could not instantiate resultclass: "
								+ resultClass.getName());
			}

			return result;
		}

		private void initialize(String[] aliases) {
			PropertyAccessor propertyAccessor = new ChainedPropertyAccessor(
					new PropertyAccessor[] {
							PropertyAccessorFactory.getPropertyAccessor(
									resultClass, null),
							PropertyAccessorFactory
									.getPropertyAccessor("field") });
			setters = new Setter[aliases.length];
			for (int i = 0; i < aliases.length; i++) {
				String alias = aliases[i];
				try {
					Integer aliasInt = Integer.valueOf(alias);
					if (aliasInt.equals(i)) {
						throw new RuntimeException(
								"field [" + i + "] 在JPQL 中指定别名! 例如：select t.name as name!");
					}
				} catch (NumberFormatException e1) {// nothing to do}
					if (alias != null) {
						aliases[i] = alias;
						if (!alias.equals("ROWNUM_")) {
							try {
								setters[i] = propertyAccessor.getSetter(
										resultClass, alias);
							} catch (PropertyNotFoundException e) {
								/* 处理驼峰规则 */
								StringBuffer sb = new StringBuffer();
								String[] split = alias.split("_");
								for (int j =0;j<split.length;j++) {
									String one = split[j];
									if(j == 0){
										sb.append(one.toLowerCase());
									}
									else{
										sb.append(Strings.capitalize(one.toLowerCase()));
									}
								}
								setters[i] = propertyAccessor.getSetter(resultClass, sb.toString());
							}
						}
					}
				}
				isInitialized = true;
			}
		}
	}
	
	
	
	static class BasicTransformerAdapter implements ResultTransformer{

		/**   */
		private static final long serialVersionUID = 1L;
		

		/**
		 * 
		 */
		public BasicTransformerAdapter() {
			super();
		}

		/* (non-Javadoc)
		 * @see org.hibernate.transform.ResultTransformer#transformList(java.util.List)*/
		@SuppressWarnings("rawtypes")
		public List transformList(List arg0) {
			return arg0;
		}

		/* (non-Javadoc)
		 * @see org.hibernate.transform.ResultTransformer#transformTuple(java.lang.Object[], java.lang.String[])*/
		public Object transformTuple(Object[] arg0, String[] arg1) {
			return arg0;
		}
		
	}
	
	
	
}
