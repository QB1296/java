/**
 * 文件名： TransactionalTopology.java
 *  
 * 版本信息：  
 * 日期：2015年7月31日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.mysql;

import java.util.Map;

import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.transactional.ITransactionalSpout;

/**
 * <b>功能描述</b> <br>
 * 
 * @author GANJX
 * @date 2015年7月31日 下午2:59:27
 */
public class TransactionalTopology {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	}
	
	static class TransactionalSpout implements ITransactionalSpout<String>{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void declareOutputFields(OutputFieldsDeclarer declarer) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Map<String, Object> getComponentConfiguration() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public backtype.storm.transactional.ITransactionalSpout.Coordinator<String> getCoordinator(
				Map conf, TopologyContext context) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public backtype.storm.transactional.ITransactionalSpout.Emitter<String> getEmitter(
				Map conf, TopologyContext context) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
}
