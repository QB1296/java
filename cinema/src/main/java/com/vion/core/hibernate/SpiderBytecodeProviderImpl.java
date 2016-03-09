/**
 * 文件名：SpiderBytecodeProviderImpl.java  
 *  
 * 版本信息：  
 * 日期：2015年2月10日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.hibernate;

import org.hibernate.bytecode.internal.javassist.BytecodeProviderImpl;
import org.hibernate.bytecode.spi.ProxyFactoryFactory;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2015年2月10日 下午7:55:01
 */
public class SpiderBytecodeProviderImpl extends BytecodeProviderImpl{

	/* (non-Javadoc)
	 * @see org.hibernate.bytecode.internal.javassist.BytecodeProviderImpl#getProxyFactoryFactory()*/
	@Override
	public ProxyFactoryFactory getProxyFactoryFactory() {
		return new SpiderProxyFactoryFactoryImpl();
	}
	
	
}
