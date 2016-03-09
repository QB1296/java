/**
 * 文件名：BeanMap.java  
 *  
 * 版本信息：  
 * 日期：2014年12月10日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.vion.core.basic.annotaion.BasicColumn;



/**
 * <b>功能描述</b> <br>
 * Bean工具类
 * @author YUJB
 * @date 2014年12月10日 下午4:46:17
 */
public class BeanUtils{
	
	
	/**
	 * map转化为Bo Bean。如果返回Bo实现了成员变量{@link BasicColumn}注解,map将BasicColumn的Value值最为Key值
	 * @param map 待转化map数据
	 * @param clazz Bo类型class
	 * @return 转化后的Bean
	 */
	@SuppressWarnings("rawtypes")
	public static <T> T  convert2Bo(Map map,Class<T> clazz) {
		T result = null;
		try {
			result = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e1) {
			e1.printStackTrace();
		}
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo( clazz );
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            if ( propertyDescriptors != null ) {
                for ( int i = 0; i < propertyDescriptors.length; i++ ) {
                    PropertyDescriptor propertyDescriptor = propertyDescriptors[i];
                    if ( propertyDescriptor != null ) {
                    	String name = propertyDescriptor.getName();
                        Method writeMethod = propertyDescriptor.getWriteMethod();
                        if ( writeMethod != null ) {
                        	Field field = clazz.getDeclaredField(name);
                        	if (field != null) {
								BasicColumn basicColumn = field.getAnnotation(BasicColumn.class);
								if (basicColumn != null) {
									name = basicColumn.value();
								}
							}
                        	try {
                        		Object object = map.get(name);
                        		if (object != null) {
                        			writeMethod.invoke(result,object);
								}
							} catch (Exception e) {
							}
                        }
                    }
                }
            }
        }
        catch ( Exception e ) {
          e.printStackTrace();  
        } 
        return result;
    }

	/**
	 * Bean转化成Map,将忽略IgnoreFieldNames指定的Field。默认忽略getClass方法
	 * @param bean 待转化的Bean
	 * @param ignoreFieldNames 忽略字段
	 * @return 转化后的Map
	 */
	public static Map<String, Object> convert2Map(Object bean,String... ignoreFieldNames) {
		Map<String, Object> map = new HashMap<String, Object>();
        if(bean == null){ return map; };
        Class<?>  beanClass = bean.getClass();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo( beanClass );
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            if ( propertyDescriptors != null ) {
                for ( int i = 0; i < propertyDescriptors.length; i++ ) {
                    PropertyDescriptor propertyDescriptor = propertyDescriptors[i];
                    if ( propertyDescriptor != null ) {
                       putBlock:{ 
	                    	String name = propertyDescriptor.getName();
	                        if (ignoreFieldNames != null) {
								for (String ignoreField : ignoreFieldNames) {
									if(ignoreField.equals(name)){
										break putBlock;
									}
								}
							}
	                        if( name.equals("class")){
	                        	break putBlock;
	                        }
	                        Method readMethod = propertyDescriptor.getReadMethod();
	                        if ( readMethod != null ) {
	                        	try {
									Object invoke = readMethod.invoke(bean);
									map.put(name, invoke);
								} catch (Exception e) {
								}
	                        }
                    	}
                    }
                }
            }
        }
        catch ( Exception e ) {
          e.printStackTrace();  
        } 
        return map;
    }
	
	/**
	 * 得到Clazz的setter方法
	 * @param fieldName 文件名称 
	 * @Param clazz 类
	 * @return 如果没有setter方法返回null
	 */
	public static Method getSetterMethod(Class<?> clazz,String fieldName) {
		
		Method method = null;
		while (method == null && clazz != null) {
			method = Classes.getMethodByName(clazz, "set" + Strings.capitalize(fieldName));
			if(method != null && method.getParameterTypes().length == 1){
				return method;
			}else {
				clazz = clazz.getSuperclass();
			}
		}
		//TODO：log 没有setter方法
		return null;
	}
	
	
	/**
	 * 得到Clazz的getter方法
	 * @param fieldName 文件名称 
	 * @Param clazz 类
	 * @return 如果没有getter方法返回null
	 */
	public static Method getGetterMethod(Class<?> clazz,String fieldName) {
		
		Method method = null;
		while (method == null && clazz != null) {
			method = Classes.getMethodByName(clazz, "get" + Strings.capitalize(fieldName));
			if(method != null && method.getParameterTypes().length == 0){
				return method;
			}else {
				clazz = clazz.getSuperclass();
			}
		}
		//TODO：log 没有setter方法
		return null;
	}
	

	/**
	 * 根据field名称在指定对象中设置值
	 * @param obj 对象
	 * @param fieldName 成员变量名称
	 * @param value 成员变量值
	 */
	public static void setterValue(Object obj,String fieldName,Object value){
		
		Method method = getSetterMethod(obj.getClass(), fieldName);
		if (method != null) {
			try {
				method.invoke(obj, value);
			} catch (Exception e) {
				throw new RuntimeException(e);
			} 
		}
	}
	
	/**
	 * 根据field名称在指定对象中查找值
	 * @param obj 对象
	 * @param fieldName field名称
	 * @return filedName field名称的值 如果filedName在对象中不存在返回null
	 */
	public static Object getFieldValue(Object obj,String fieldName) {
		
		try {
			Field field = obj.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			return field.get(obj);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
