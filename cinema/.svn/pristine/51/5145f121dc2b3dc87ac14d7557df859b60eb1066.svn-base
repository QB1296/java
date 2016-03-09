/**
 * 文件名：SpiderEntityBeanSerializer.java  
 *  
 * 版本信息：  
 * 日期：2015年3月12日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.hibernate;

import java.io.IOException;
import java.lang.reflect.Method;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.std.BeanSerializerBase;

import com.vion.core.util.Classes;

/**
 * <b>功能描述</b> <br>
 * SpiderEntity序列化,处理空代理问题
 * @author YUJB
 * @date 2015年3月12日 下午12:50:33
 */
public class SpiderEntityBeanSerializer extends BeanSerializerBase{
	
	
	private BeanSerializerBase beanSerializer;
	
	public SpiderEntityBeanSerializer(BeanSerializerBase beanSerializer) {
		super(beanSerializer);
		this.beanSerializer = beanSerializer;
	}

	/* (non-Javadoc)
	 * @see org.codehaus.jackson.map.ser.std.BeanSerializerBase#serialize(java.lang.Object, org.codehaus.jackson.JsonGenerator, org.codehaus.jackson.map.SerializerProvider)*/
	@Override
	public void serialize(Object bean, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonGenerationException {
		boolean nullLazyProxy = false;
		Method method = Classes.getMethodByName(bean.getClass(), "isNullLazyProxy");
		if (method != null) {
			try {
				Object invoke = method.invoke(bean);
				if (invoke != null) {
					nullLazyProxy = Boolean.valueOf(invoke.toString());
				}
			} catch (Exception e) {} 
		}
		if(!nullLazyProxy){
			beanSerializer.serialize(bean, jgen, provider);
		}else {
			jgen.writeNull();
		}
		
	}

}
