/**
 * 文件名： DbFactory.java
 *  
 * 版本信息：  
 * 日期：2015年7月30日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.vion;

import java.util.Map;
import java.util.UUID;

import storm.trident.state.State;
import storm.trident.state.StateFactory;
import backtype.storm.task.IMetricsContext;

/**
 * <b>功能描述</b> <br>
 * 
 * @author GANJX
 * @date 2015年7月30日 下午3:10:43
 */
public class DbFactory implements StateFactory{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 String _id;

     public DbFactory() {
         _id = UUID.randomUUID().toString();
     }
     
	@Override
	public State makeState(Map conf, IMetricsContext metrics,
			int partitionIndex, int numPartitions) {
		// TODO Auto-generated method stub
		return new DbMapState(_id + partitionIndex);
	}

}
