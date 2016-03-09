/**
 * 文件名：GenericUtils.java  
 *  
 * 版本信息：  
 * 日期：2012-12-18  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.util;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;

import com.vion.core.exception.NoCriteriaWritingException;

/**
 * <b>功能描述</b> <br>
 * 泛型工具类
 * @author YUJB
 * @date 2014年6月16日 上午10:48:39
 */
public abstract class GenericUtils {
	
	
	/**
	 * 得到泛型参数类型,泛型中存在泛型参数的概念.JVM使用桥接的方式处理。具体查找JVM资料<br>
	 * 如果List&lt;String&gt;将返回null<br> 
	 * MyList extends List<String>  getTypeParam(MyList.class,0) 将返回String Class 
	 * @param klass Class对象
	 * @param index 类型索引
	 * @return Class 对象 如果没有找到返回null,如果index越界 抛出{@link NoCriteriaWritingException}
	 */
	@SuppressWarnings("unchecked")
    public static <T> Class<T> getTypeParam(Class<?> klass, int index) {
        Type[] types = getTypeParams(klass);
        if (types == null) {
			return null;
		}
        if (index >= 0 && index < types.length) {
            Type t = types[index];
            Class<T> clazz = (Class<T>)getTypeClass(t);
            return clazz;
        }
        throw new NoCriteriaWritingException("参数越界[" + index + "],数组总长度[" + types.length + "]");
    }
	
    /**
     * 获取一个类的泛型参数数组，如果这个类没有泛型参数，返回 null
     * @param clazz
     * @return 泛型参数列表
     */
    public static Type[] getTypeParams(Class<?> clazz) {
        if (clazz == null || "java.lang.Object".equals(clazz.getName())){
        	return null;
        }
            
        /*父类查找*/
        Type superclass = clazz.getGenericSuperclass();
        if (null != superclass && superclass instanceof ParameterizedType)
            return ((ParameterizedType) superclass).getActualTypeArguments();

        /*接口查找*/
        Type[] interfaces = clazz.getGenericInterfaces();
        for (Type inf : interfaces) {
            if (inf instanceof ParameterizedType) {
                return ((ParameterizedType) inf).getActualTypeArguments();
            }
        }
        return getTypeParams(clazz.getSuperclass());
    }
    
    
    /**
     * 根据Type得到Class 对象
     * @param type Type对象
     * @return Class对象
     */
    public static Class<?> getTypeClass(Type type) {
        Class<?> clazz = null;
        if (type instanceof Class<?>) {
            clazz = (Class<?>) type;
        } else if (type instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) type;
            clazz = (Class<?>) pt.getRawType();
        } else if (type instanceof GenericArrayType) {
            GenericArrayType gat = (GenericArrayType) type;
            Class<?> typeClass = getTypeClass(gat.getGenericComponentType());
            return Array.newInstance(typeClass, 0).getClass();
        } else if (type instanceof TypeVariable<?>) {
            TypeVariable<?> tv = (TypeVariable<?>) type;
            Type[] ts = tv.getBounds();
            if (ts != null && ts.length > 0){
            	return getTypeClass(ts[0]);
            }
        } else if (type instanceof WildcardType) {
            WildcardType wt = (WildcardType) type;
            Type[] t_low = wt.getLowerBounds();
            if (t_low.length > 0)
                return getTypeClass(t_low[0]);
            Type[] t_up = wt.getUpperBounds(); 
            return getTypeClass(t_up[0]);
        }
        return clazz;
    }

    
    /**
     * 得到方法的第一个泛型类型
     * @param method 方法
     * @return 泛型类型 如果存在类型擦出或有多个泛型、不存在泛型 返回空
     */
    public static Class<?> getFirstGenericMethodReturnType(Method method){
    	Type type = method.getGenericReturnType();
		if (type instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType)type;
			Type[] types = parameterizedType.getActualTypeArguments();
			if (types.length == 1) {
				if (types[0] instanceof Class<?>) {
					return (Class<?>)types[0];
				}else {
					return null;
				}
			}
			else {
				return null;
			}
		}
		return null;
    }
   
    private GenericUtils() {
       
    }
}
