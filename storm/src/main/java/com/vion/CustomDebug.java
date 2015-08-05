/**
 * 文件名： CustomDebug.java
 *  
 * 版本信息：  
 * 日期：2015年7月30日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.vion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import storm.trident.operation.builtin.Debug;
import storm.trident.tuple.TridentTuple;

/**
 * <b>功能描述</b> <br>
 * 
 * @author GANJX
 * @date 2015年7月30日 下午2:00:57
 */
public class CustomDebug extends Debug{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger= LoggerFactory.getLogger(CustomDebug.class);
	
	@Override
	public boolean isKeep(TridentTuple tuple) {
		// TODO Auto-generated method stub
		logger.debug("{}",tuple);
		return true;
	}

}
