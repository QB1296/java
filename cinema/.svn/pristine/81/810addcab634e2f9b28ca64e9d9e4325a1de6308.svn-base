/**
 * 文件名：Classes.java  
 *  
 * 版本信息：  
 * 日期：2013-5-30  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



import javax.persistence.Entity;

import com.vion.core.domain.entity.IEntity;
import com.vion.core.exception.NoCriteriaWritingException;



/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2013-5-30 下午04:23:11
 */
public abstract class Classes {
	
	public static final char INNER_CLASS_SEPARATOR = '$';

	public static final String CGLIB_CLASS_SEPARATOR = "$$";
	
	
	/**
	 * 通过类名称得到Class 对象 
	 * @param className 类名称
	 * @return Class 对象
	 */
	public static Class<?> forName(String className){
		try {
			return Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	/**
	 * JVM中是否存在类
	 * @param className 类名称
	 * @return true:存在；false:不存在 
	 */
	public static boolean isExist(String className) {
		try {
			forName(className);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	
	/**
	 * 得到类加载器,首先去当前类的加载器如果不存在,调用ClassLoader.getSystemClassLoader
	 * @return 类加载器
	 */
	public static ClassLoader getClassLoader(){
		ClassLoader classLoader = Classes.class.getClassLoader();
		if (classLoader == null) {
			classLoader = ClassLoader.getSystemClassLoader();
		}
		return classLoader;
	}
	
	
	/**
	 * 加载类
	 * @param className
	 * @return
	 * @throws ClassNotFoundException
	 */
	public static Class<?> loadClass(String className) throws ClassNotFoundException {
        try {
        	return Thread.currentThread().getContextClassLoader().loadClass(className);
        }
        catch (ClassNotFoundException e) {
            return Class.forName(className);
        }
    }
	
	/**
	 * 通过方法名称得到方法,由于存在重载的情况,返回第一个有效的方法,如为私有方法,将方法修改为可访问状态。
	 * @param calzz 在此类中查找方法
	 * @param methodName 待查找的方法名称
	 * @return 方法,如果没有找到则返回<code>null</code>
	 */
	public static Method getMethodByName(Class<?> clazz,String methodName) {
		return Classes.getMethodByName(clazz, methodName,new Class[0]);
    }
	
	
	/**
	 * 通过方法名称得到方法,由于存在重载的情况,返回第一个有效的方法,如为私有方法,将方法修改为可访问状态。
	 * @param calzz 在此类中查找方法
	 * @param methodName 待查找的方法名称
	 * @return 方法,如果没有找到则返回<code>null</code>
	 */
	public static Method getMethodByName(Class<?> clazz,String methodName,Class<?>... paramTypes) {
		if (clazz == null) {
			return null;
		}
		Method[] declaredMethods = clazz.getDeclaredMethods();
		Method retMethod = null;
		for (Method method : declaredMethods) {
			if(method.getName().equals(methodName) && (paramTypes == null || Arrays.equals(paramTypes, new Class[0]) || Arrays.equals(method.getParameterTypes(), paramTypes))){
				if (!method.isAccessible()) {
					method.setAccessible(true);
				}
				retMethod =  method;
				break;
			}
		}
		if (retMethod == null) {
			retMethod = getMethodByName(clazz.getSuperclass(), methodName);
		}
		return retMethod;
    }
	
	
	/**
	 * 通过field名称得到 Field对象
	 * @param clazz 类
	 * @param fieldName 成员变量名称
	 * @return Filed对象
	 */
	public static Field getField(Class<?> clazz, String fieldName){
		try {
			return clazz.getField(fieldName);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
		
	}
	
	/**
	 * 获取类及所有的深度父类（不包括Object）的字段 包括(public,private,protected)
	 * @param clazz
	 * @return
	 */
	public static Field[] getAllFields(Class<?> clazz){
		List<Field> allFields = new ArrayList<>();
		getAllFieldsImpl(clazz,allFields);
		return allFields.toArray(new Field[]{});
	}
	
	/**
	 * @param clazz
	 * @param allFields
	 */
	private static void getAllFieldsImpl(Class<?> clazz,List<Field> allFields){
		String className = clazz.getName();
		//如果当前类是Object，直接返回
		if(className.equals(Object.class.getName())){
			return;
		}
		Field[] fields = clazz.getDeclaredFields();
		for(Field f:fields){
			allFields.add(f);
		}
		getAllFieldsImpl(clazz.getSuperclass(), allFields);
	}
	
	/**
	 * 通过参数和指定的Class对象 实例化Class
	 * @param clazz 类
	 * @param objs 构造方法参数
	 * @return 指定类的实例
	 */
	@SuppressWarnings("unchecked")
	public static <T> T constructorNewInstance(Class<T> clazz,Object... objs){
		Class<?>[] classes = new Class<?>[objs.length];
		for (int i = 0; i < objs.length; i++) {
			classes[i] = objs[i].getClass();
		}
		try {
			if (classes.length == 0) {
				clazz.newInstance();
			}
			Constructor<T> constructor = null;
			Constructor<?>[] constructors = clazz.getConstructors();
			for (Constructor<?> one : constructors) {
				Class<?>[] parameterTypes = one.getParameterTypes();
				boolean isThisConstructor = true;
				if(parameterTypes.length == classes.length){
					for (int i = 0; i < parameterTypes.length; i++) {
						if(!parameterTypes[i].isAssignableFrom(classes[i])){
							isThisConstructor = false;
						}
					}
				}else {
					isThisConstructor = false;
				}
				if (isThisConstructor) {
					constructor = (Constructor<T>) one; 
					break;
				}
			}
			return constructor.newInstance(objs);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}
	
	
	/**
	 * 类是否为Cglib代理
	 * @param clazz 类
	 * @return true:是Cglib代理类；false:不是Cglib代理
	 */
	public static boolean isCglibProxyClass(Class<?> clazz) {
		return (clazz != null && isCglibProxyClassName(clazz.getName()));
	}

	
	/**
	 * 类是否为Cglib代理
	 * @param className 类名称
	 * @return true:是Cglib代理类；false:不是Cglib代理
	 */
	public static boolean isCglibProxyClassName(String className) {
		return (className != null && className.contains(CGLIB_CLASS_SEPARATOR));
	}
	
	
	/**
	 * 类是否是实体类,必须包含Entity注解实现{@link IEntity}接口
	 * @param clazz 类
	 * @return true:是实体类；false：不是实体类
	 */
	public static boolean isEntityClass(Class<?> clazz) {
		
		return (clazz.getAnnotation(Entity.class) != null && IEntity.class.isAssignableFrom(clazz));
	}


	/**
	 * @param injectType
	 * @return
	 */
	public static Class<?> getGenericType(Class<?> injectType) {
		if (injectType.isArray()) {
			return injectType.getComponentType();
		}
		return injectType;
	}
	
	
    
    /**
     * 根据field名称得到field的值,直接掉用toString()方法返回字符串
     * @param obj 对象
     * @param fieldName 成员变量名称
     * @return field的值
     */
    public static String getStringFieldValue(Object obj,String fieldName){
		return getFieldValue(obj,fieldName).toString();
	}
	
    /**
     * 根据field名称得到field的值
     * @param obj 对象
     * @param fieldName 成员变量名称
     * @return field的值
     */
    public static Object getFieldValue(Object obj,String fieldName){
		try {
			Method method = obj.getClass().getMethod("get" + Strings.capitalize(fieldName));
			return method.invoke(obj);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			throw new NoCriteriaWritingException(fieldName + "在类中不存在");
		}
	}
    

}
