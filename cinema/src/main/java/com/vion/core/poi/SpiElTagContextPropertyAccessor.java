/**
 * 文件名：SpiElTagContextPropertyAccessor.java  
 *  
 * 版本信息：  
 * 日期：2015年1月22日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.poi;

import org.springframework.expression.AccessException;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.PropertyAccessor;
import org.springframework.expression.TypedValue;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2015年1月22日 下午2:39:57
 */
public class SpiElTagContextPropertyAccessor implements PropertyAccessor{

	/* (non-Javadoc)
	 * @see org.springframework.expression.PropertyAccessor#getSpecificTargetClasses()*/
	@Override
	public Class<?>[] getSpecificTargetClasses() {
		return new Class[]{SpiElTagContext.class};
	}

	/* (non-Javadoc)
	 * @see org.springframework.expression.PropertyAccessor#canRead(org.springframework.expression.EvaluationContext, java.lang.Object, java.lang.String)*/
	@Override
	public boolean canRead(EvaluationContext context, Object target, String name)
			throws AccessException {
		return ((SpiElTagContext)target).getValue(name) != null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.expression.PropertyAccessor#read(org.springframework.expression.EvaluationContext, java.lang.Object, java.lang.String)*/
	@Override
	public TypedValue read(EvaluationContext context, Object target, String name)
			throws AccessException {
		return new TypedValue(((SpiElTagContext) target).getValue(name));
	}

	/* (non-Javadoc)
	 * @see org.springframework.expression.PropertyAccessor#canWrite(org.springframework.expression.EvaluationContext, java.lang.Object, java.lang.String)*/
	@Override
	public boolean canWrite(EvaluationContext context, Object target,
			String name) throws AccessException {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.springframework.expression.PropertyAccessor#write(org.springframework.expression.EvaluationContext, java.lang.Object, java.lang.String, java.lang.Object)*/
	@Override
	public void write(EvaluationContext context, Object target, String name,
			Object newValue) throws AccessException {
		
	}
	
}
