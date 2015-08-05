/**
 * 文件名：sdfsfd.java  
 *  
 * 版本信息：  
 * 日期：2015年7月24日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */

package com.vion;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2015年7月24日 上午11:38:47
 */
/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.File;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import storm.kafka.Broker;
import storm.kafka.BrokerHosts;
import storm.kafka.StaticHosts;
import storm.kafka.StringScheme;
import storm.kafka.ZkHosts;
import storm.kafka.trident.GlobalPartitionInformation;
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
import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.LocalDRPC;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.StormTopology;
import backtype.storm.spout.SchemeAsMultiScheme;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

import com.css.webim.message.Message;
import com.css.webim.rmi.ChatServiceRemote;
import com.css.webim.rmi.RMIException;
import com.css.webim.rmi.RMIUtils;

public class TridentWordCount {
	
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

	public static StormTopology buildTopology(LocalDRPC drpc) {
//		String brokerZkStr = "192.168.9.7:2181";
//		String brokerZkPath = "/brokers";
//		ZkHosts zkHosts = new ZkHosts(brokerZkStr, brokerZkPath);
//		GlobalPartitionInformation globalPartitionInformation = new GlobalPartitionInformation();
//		globalPartitionInformation.addPartition(0, new Broker("192.168.9.7", 2181));
//		StaticHosts staticHosts = new StaticHosts(globalPartitionInformation); 
		BrokerHosts brokerHosts =  new ZkHosts("192.168.9.7:2181");
		TridentKafkaConfig kafkaConfig = new TridentKafkaConfig(brokerHosts, "topic4");
		
		kafkaConfig.scheme = new SchemeAsMultiScheme(new StringScheme());
		TransactionalTridentKafkaSpout spout = new TransactionalTridentKafkaSpout(kafkaConfig);
		

		TridentTopology topology = new TridentTopology();
		Stream newStream = topology.newStream("spout1", spout);
				//each 入库,DB ,ES
		newStream.partitionPersist(new DbFactory(),new Fields(StringScheme.STRING_SCHEME_KEY), new CustomStateUpdater());
		Stream each = newStream.each(new Function() {
					
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
				}, new Fields("oneCount"));
		
		each.each(new Function() {
					
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
//				.persistentAggregate(new MemoryMapState.Factory(),new Fields("time"), new Count(), new Fields("timeCount"))
//				.newValuesStream().each(new Fields("time","timeCount"), new Debug())
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

	public static void main(String[] args) throws Exception {
		
		Config conf = new Config();
		conf.setMaxSpoutPending(20);	
		if (args.length == 0) {
			LocalDRPC drpc = new LocalDRPC();
			LocalCluster cluster = new LocalCluster();
			cluster.submitTopology("wordCounter", conf, buildTopology(drpc));
		} else {
			conf.setNumWorkers(3);
			StormSubmitter.submitTopologyWithProgressBar(args[0], conf,
					buildTopology(null));
		}
	}
	
	
}
