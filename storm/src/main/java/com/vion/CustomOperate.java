/**
 * 文件名： CustomOperate.java
 *  
 * 版本信息：  
 * 日期：2015年7月30日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.vion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import storm.trident.operation.CombinerAggregator;
import storm.trident.tuple.TridentTuple;

/**
 * <b>功能描述</b> <br>
 * 
 * @author GANJX
 * @date 2015年7月30日 下午4:06:23
 */
public class CustomOperate implements CombinerAggregator<Object>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Logger logger = LoggerFactory.getLogger(CustomOperate.class);
	
	TridentTuple tuple = null;
	
	@Override
	public Object init(TridentTuple tuple) {
		// TODO Auto-generated method stub
		this.tuple = tuple;
		return tuple;
	}

	@Override
	public Object combine(Object val1, Object val2) {
		// TODO Auto-generated method stub
		logger.info("{},{}",val1,val2);
		return null;
	}

	@Override
	public Object zero() {
		// TODO Auto-generated method stub
		logger.info("zero");
		return null;
	}

}
