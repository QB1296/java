/**
 * 文件名： KafkaTest.java
 *  
 * 版本信息：  
 * 日期：2015年7月30日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.vion;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

/**
 * <b>功能描述</b> <br>
 * 
 * @author GANJX
 * @date 2015年7月30日 下午2:05:15
 */
public class KafkaTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long events = 5L/* Long.MAX_VALUE */;
		Properties props = new Properties();
		props.put("metadata.broker.list", "192.168.9.7:9092");
		props.put("serializer.class", "kafka.serializer.StringEncoder");
		props.put("request.required.acks", "1");

		ProducerConfig config = new ProducerConfig(props);

		Producer<String, String> producer = new Producer<String, String>(config);

		for (long nEvents = 0; nEvents < events; nEvents++) {
			KeyedMessage<String, String> data = new KeyedMessage<String, String>(
					"topic4", "ip", "msg");
			producer.send(data);
		}
		producer.close();
	}
}
