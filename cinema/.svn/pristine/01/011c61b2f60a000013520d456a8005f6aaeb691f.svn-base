/**
 * 文件名：SpiderResourceHolder.java  
 *  
 * 版本信息：  
 * 日期：2013-6-20  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * <b>功能描述</b> <br>
 * 线程绑定的资源holder类,使用key,value的方式维护资源,通过key值索引当前线程关联的资源<br>
 * {@link #bind(Object, Object)}将key,value绑定到当前线程<br>
 * {@link #get(Object)},{@link #get(Object, Class)}通过key值得到资源
 * @author YUJB
 * @date 2014-6-24 下午04:18:51
 */
public class ResourcesHolder {
	
	/** 日志  */
	private transient static Logger logger = LoggerFactory.getLogger(ResourcesHolder.class);
	
	/**  线程local 初始维护空Map。当没有资源时,不会抛出空{@link NullPointerException}异常 */
	private static final ThreadLocal<Map<Object, Object>> resources =
		new ThreadLocal<Map<Object, Object>>(){

			/* (non-Javadoc)
			 * @see java.lang.ThreadLocal#initialValue()*/
			@Override
			protected Map<Object, Object> initialValue() {
				return new HashMap<Object, Object>();
			}
		
	};
	
	
	/**
	 * 更具资源key值得到资源
	 * @param resourceKey 资源key,此值有{@link #bind(Object, Object)}时指定
	 * @return 资源,如果没有返回null
	 */
	public static Object get(Object resourceKey){
		Map<Object, Object> map = resources.get();
		Object resource = map.get(resourceKey);
		logger.debug( "线程{}中通过参数key:{} 得到资源value:{}",new String[]{Thread.currentThread().getName(),resourceKey.toString(),String.valueOf(resource)});
		return resource;
	}
	
	
	/**
	 * @param <T>
	 * @param resourceKey
	 * @param clazz  返回结果类型限制
	 * @return 资源,如果没有返回null
	 * @see #get(Object)
	 */
	@SuppressWarnings("unchecked")
	public static <T> T get(Object resourceKey,Class<T> clazz){
		Object object = get(resourceKey);
		if (object == null) {
			return null;
		}
		return (T)object;
	}
	
	
	/**
	 * 绑定资源 
	 * @param resourceKey 资源key
	 * @param resourceValue 资源
	 */
	public static void bind(Object resourceKey,Object resourceValue){
		Map<Object, Object> map = resources.get();
		map.put(resourceKey, resourceValue);
		logger.debug( "线程{}中绑定参数key:{} , value:{}",new String[]{Thread.currentThread().getName(),resourceKey.toString(),resourceValue.toString()});
	}
	
	
	/**
	 * 将资源从当前线程中移除
	 * @param resourceKey 资源key
	 */
	public static void unbind(Object resourceKey){
		Map<Object, Object> map = resources.get();
		map.remove(resourceKey);
		logger.debug( "线程{}中移除参数key:{}",new String[]{Thread.currentThread().getName(),resourceKey.toString()});
	}
	
}
