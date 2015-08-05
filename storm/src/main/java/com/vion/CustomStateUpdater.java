/**
 * 文件名： CustomStateUpdater.java
 *  
 * 版本信息：  
 * 日期：2015年7月30日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.vion;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import storm.trident.operation.TridentCollector;
import storm.trident.operation.TridentOperationContext;
import storm.trident.state.State;
import storm.trident.state.StateUpdater;
import storm.trident.tuple.TridentTuple;

/**
 * <b>功能描述</b> <br>
 * 
 * @author GANJX
 * @date 2015年7月30日 下午5:32:18
 */
public class CustomStateUpdater implements StateUpdater<DbMapState>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(CustomStateUpdater.class);
	

	@Override
	public void prepare(Map conf, TridentOperationContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cleanup() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void updateState(DbMapState state, List<TridentTuple> tuples,
			TridentCollector collector) {
		// TODO Auto-generated method stub
		logger.info("{}",tuples);
		state.multiPut(tuples, tuples);
	}

	

}
