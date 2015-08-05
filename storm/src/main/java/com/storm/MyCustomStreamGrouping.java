/**
 * 文件名： CustomStreamGrouping.java
 *  
 * 版本信息：  
 * 日期：2015年7月24日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.storm;

import java.util.List;

import backtype.storm.generated.GlobalStreamId;
import backtype.storm.grouping.CustomStreamGrouping;
import backtype.storm.task.WorkerTopologyContext;

/**
 * <b>自定义流分组</b> <br>
 * 
 * @author GANJX
 * @date 2015年7月24日 下午4:59:54
 */
public class MyCustomStreamGrouping implements CustomStreamGrouping{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void prepare(WorkerTopologyContext context, GlobalStreamId stream,
			List<Integer> targetTasks) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Integer> chooseTasks(int taskId, List<Object> values) {
		// TODO Auto-generated method stub
		return null;
	}

}
