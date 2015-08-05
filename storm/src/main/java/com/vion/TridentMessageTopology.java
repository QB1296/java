/**
 * 文件名： TridentMessageTopology.java
 *  
 * 版本信息：  
 * 日期：2015年7月31日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.vion;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import storm.kafka.BrokerHosts;
import storm.kafka.StringScheme;
import storm.kafka.ZkHosts;
import storm.kafka.trident.TransactionalTridentKafkaSpout;
import storm.kafka.trident.TridentKafkaConfig;
import storm.trident.Stream;
import storm.trident.TridentTopology;
import storm.trident.operation.BaseFunction;
import storm.trident.operation.Filter;
import storm.trident.operation.Function;
import storm.trident.operation.TridentCollector;
import storm.trident.operation.TridentOperationContext;
import storm.trident.operation.builtin.Count;
import storm.trident.operation.builtin.Sum;
import storm.trident.testing.MemoryMapState;
import storm.trident.tuple.TridentTuple;
import backtype.storm.generated.StormTopology;
import backtype.storm.spout.SchemeAsMultiScheme;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

import com.css.webim.message.Message;
import com.css.webim.rmi.ChatServiceRemote;
import com.css.webim.rmi.RMIException;
import com.css.webim.rmi.RMIUtils;
import com.jdbc.trident.state.JdbcState;
import com.jdbc.trident.state.JdbcStateFactory;
import com.jdbc.trident.state.JdbcUpdater;

/**
 * <b>功能描述</b> <br>
 * 
 * @author GANJX
 * @date 2015年7月31日 下午5:25:13
 */
public class TridentMessageTopology extends AbstractTopology implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public static class Split extends BaseFunction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void execute(TridentTuple tuple, TridentCollector collector) {
			String sentence = tuple.getString(0);
			for (String word : sentence.split(" ")) {
				collector.emit(new Values(word));
			}
		}
	}
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		 new TridentMessageTopology().execute(args);
	}
	
	
	@Override
	public StormTopology getTopology() {
		// TODO Auto-generated method stub
		BrokerHosts brokerHosts =  new ZkHosts("192.168.9.7:2181");
		TridentKafkaConfig kafkaConfig = new TridentKafkaConfig(brokerHosts, "topic4");
		
		kafkaConfig.scheme = new SchemeAsMultiScheme(new StringScheme());
		TransactionalTridentKafkaSpout spout = new TransactionalTridentKafkaSpout(kafkaConfig);
		
		
		TridentTopology topology = new TridentTopology();

        JdbcState.Options options = new JdbcState.Options()
                .withConnectionPrvoider(connectionProvider)
                .withMapper(this.jdbcMapper)
                .withTableName(TABLE_NAME);

        JdbcStateFactory jdbcStateFactory = new JdbcStateFactory(options);

        Stream stream = topology.newStream("userSpout", spout).each(new Function(){

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@SuppressWarnings("rawtypes")
			@Override
			public void prepare(Map conf, TridentOperationContext context) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void cleanup() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void execute(TridentTuple tuple, TridentCollector collector) {
				// TODO Auto-generated method stub
				collector.emit(new Values(Calendar.getInstance().getTime().getTime()));
			}
        	
        }, new Fields("create_date"));
        stream.partitionPersist(jdbcStateFactory, new Fields("str","create_date"),  new JdbcUpdater(), new Fields());
        
        stream.each(new Function() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			Logger logger = LoggerFactory.getLogger(getClass());
			@Override
			public void prepare(Map conf, TridentOperationContext context) {
				
			}
			
			@Override
			public void cleanup() {
				
			}
			
			@Override
			public void execute(TridentTuple tuple, TridentCollector collector) {
				logger.debug("{}",tuple);
				collector.emit(new Values(1));
			}
		}, new Fields("oneCount")).each(new Function() {
			
			@Override
			public void prepare(Map conf, TridentOperationContext context) {
				
			}
			
			@Override
			public void cleanup() {
				
			}
			
			@Override
			public void execute(TridentTuple tuple, TridentCollector collector) {
				Calendar calendar = Calendar.getInstance();
				int second = calendar.get(Calendar.SECOND);
				int secondSeg =  ((int)Math.floor(second/5))*5;
				calendar.set(Calendar.SECOND, secondSeg);
				collector.emit(new Values(calendar.getTime()));
			}
		}, new Fields("time"))
		.groupBy(new Fields("time"))
		.aggregate(new Fields("time"), new Count(), new Fields("timeCount"))
		.each(new Fields("time","timeCount"), new CustomDebug())
		.persistentAggregate(new MemoryMapState.Factory(),new Fields("timeCount"),
					new Sum(), new Fields("sum")).newValuesStream().each(new Fields("sum"), new Filter() {
						
						/**
						 * 
						 */
						private static final long serialVersionUID = 1L;
						
						private Logger logger = LoggerFactory.getLogger(getClass());

						@Override
						public void prepare(Map conf, TridentOperationContext context) {
							
						}
						
						@Override
						public void cleanup() {
							
						}
						
						@Override
						public boolean isKeep(TridentTuple tuple) {
							logger.warn("{}",tuple);
							Long count = tuple.getLongByField("sum");
							logger.warn("{}",count);
							Message message = new Message();
							message.setChannel("/service/privatechat");
							message.setReceiverIds(Arrays.asList("2"));
							message.setSenderId("1");
							message.setContent("{\"x\":" + count + ",\"y\":" + new Date().getTime()+ "}");
							message.setSendDate(new Date());
							try {
								ChatServiceRemote remote = RMIUtils.client.subscript("rmi://192.168.9.253:1111/chatservice", ChatServiceRemote.class);
								remote.sendMessageToSpaticOneIgnoreOffline(message);
							} catch (RMIException | RemoteException e) {
								e.printStackTrace();
							}
							return false;
						}
					});
        return topology.build();
	}

}
