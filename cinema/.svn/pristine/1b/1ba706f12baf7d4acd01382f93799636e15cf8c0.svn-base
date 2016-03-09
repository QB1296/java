/**
 * 文件名：a.java  
 *  
 * 版本信息：  
 * 日期：2015年3月10日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.basic;

import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.introspect.BasicBeanDescription;
import org.codehaus.jackson.map.ser.BeanPropertyWriter;
import org.codehaus.jackson.map.ser.BeanSerializerBuilder;
import org.codehaus.jackson.map.ser.BeanSerializerFactory;
import org.codehaus.jackson.type.JavaType;
import org.springframework.util.ClassUtils;

import com.vion.core.hibernate.SpiderEntityBeanSerializerBuilder;

/**
 * <b>功能描述</b> <br>
 * 自定义代理Bean序列化工厂,只序列化代理属性,并处理了SpiderEntity空代理对象问题
 * @author YUJB
 * @date 2015年3月10日 上午9:30:35
 */
public  class ProxyAwareSerializerFactory  extends BeanSerializerFactory  {
	
	 /**
	 * @param config
	 */
	public ProxyAwareSerializerFactory(Config config) {
		super(config);
	}
	
	public ProxyAwareSerializerFactory() {
		super(null);
	}

	private List<String> cglibProperties = Arrays.asList("proxyTargetClass","preFiltered","exposeProxy","advisors","proxyClass","invocationHandler","proxiedInterfaces","targetClass","targetSource","frozen");
	
	/* (non-Javadoc)
	 * @see org.codehaus.jackson.map.ser.BeanSerializerFactory#createSerializer(org.codehaus.jackson.map.SerializationConfig, org.codehaus.jackson.type.JavaType, org.codehaus.jackson.map.BeanProperty)*/
	@Override
	public JsonSerializer<Object> createSerializer(SerializationConfig arg0,
			JavaType arg1, BeanProperty arg2) throws JsonMappingException {
		return super.createSerializer(arg0, arg1, arg2);
	}
	
	protected BeanSerializerBuilder constructBeanSerializerBuilder(BasicBeanDescription beanDesc) {
	    return new SpiderEntityBeanSerializerBuilder(beanDesc);
	}
	
	/* (non-Javadoc)
	 * @see org.codehaus.jackson.map.ser.BeanSerializerFactory#filterBeanProperties(org.codehaus.jackson.map.SerializationConfig, org.codehaus.jackson.map.introspect.BasicBeanDescription, java.util.List)*/
	@Override
	protected List<BeanPropertyWriter> filterBeanProperties(
			SerializationConfig config, BasicBeanDescription beanDesc,
			List<BeanPropertyWriter> props) {
		 props = super.filterBeanProperties(config, beanDesc, props);

	     filterInstrumentedBeanProperties(beanDesc, props);

	     return props;
	}
	
	private void filterInstrumentedBeanProperties(
			BasicBeanDescription beanDesc, List<BeanPropertyWriter> props) {
		if (!ClassUtils.isCglibProxyClass(beanDesc.getBeanClass()) && !Proxy.isProxyClass(beanDesc.getBeanClass())) {
			return;
		}
		for (Iterator<BeanPropertyWriter> iter = props.iterator(); iter
				.hasNext();) {
			if (cglibProperties.contains(iter.next().getName())) {
				iter.remove();
			}
		}
	}
	
	  
}
