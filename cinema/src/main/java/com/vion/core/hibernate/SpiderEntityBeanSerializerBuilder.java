/**
 * 文件名：SpiderEntityBeanSerializerBuilder.java  
 *  
 * 版本信息：  
 * 日期：2015年3月12日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.hibernate;

import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.introspect.BasicBeanDescription;
import org.codehaus.jackson.map.ser.BeanSerializerBuilder;
import org.codehaus.jackson.map.ser.std.BeanSerializerBase;

/**
 * <b>功能描述</b> <br>
 * SpiderEntity序列化Bulider
 * @author YUJB
 * @date 2015年3月12日 下午1:04:00
 */
public class SpiderEntityBeanSerializerBuilder extends BeanSerializerBuilder{

	/**
	 * @param beanDesc
	 */
	public SpiderEntityBeanSerializerBuilder(BasicBeanDescription beanDesc) {
		super(beanDesc);
	}

	public SpiderEntityBeanSerializerBuilder(BeanSerializerBuilder src) {
		super(src);
	}
	
	/* (non-Javadoc)
	 * @see org.codehaus.jackson.map.ser.BeanSerializerBuilder#build()*/
	@Override
	public JsonSerializer<?> build() {
		JsonSerializer<?> build = super.build();
		return new SpiderEntityBeanSerializer((BeanSerializerBase)build);
	}
	
	
	
}
