/**
 * 文件名： Client.java
 *  
 * 版本信息：  
 * 日期：2015年9月1日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.ganjx.redis;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.Transaction;


/**
 * <b>功能描述</b> <br>
 * 
 * @author GANJX
 * @date 2015年9月1日 下午12:52:57
 */
public class RedisClient {
	
	Logger logger = LoggerFactory.getLogger(RedisClient.class);
	
	String serverIp = "192.168.9.7";
	
	/**
	 * 普通同步方式
	 */
	@Test
	public void test1Normal() {
	    Jedis jedis = new Jedis(serverIp);
	    long start = System.currentTimeMillis();
	    for (int i = 0; i < 100000; i++) {
	        String result = jedis.set("n" + i, "n" + i);
	    }
	    long end = System.currentTimeMillis();
	    System.out.println("Simple SET: " + ((end - start)/1000.0) + " seconds");
	    jedis.disconnect();
	}
	
	/**
	 * 事务方式
	 */
	@Test
	public void test2Trans() {
	    Jedis jedis = new Jedis(serverIp);
	    long start = System.currentTimeMillis();
	    Transaction tx = jedis.multi();
	    for (int i = 0; i < 100000; i++) {
	        tx.set("t" + i, "t" + i);
	    }
	    List<Object> results = tx.exec();
	    long end = System.currentTimeMillis();
	    System.out.println("Transaction SET: " + ((end - start)/1000.0) + " seconds");
	    jedis.disconnect();
	}
	
	/**
	 * 管道(Pipelining)
	 */
	@Test
	public void test3Pipelined() {
	    Jedis jedis = new Jedis(serverIp);
	    Pipeline pipeline = jedis.pipelined();
	    long start = System.currentTimeMillis();
	    for (int i = 0; i < 100000; i++) {
	        pipeline.set("p" + i, "p" + i);
	    }
	    List<Object> results = pipeline.syncAndReturnAll();
	    long end = System.currentTimeMillis();
	    System.out.println("Pipelined SET: " + ((end - start)/1000.0) + " seconds");
	    jedis.disconnect();
	}
	
	/**
	 * 管道中调用事务
	 */
	@Test
	public void test4combPipelineTrans() {
		Jedis jedis = new Jedis(serverIp); 
	    long start = System.currentTimeMillis();
	    Pipeline pipeline = jedis.pipelined();
	    pipeline.multi();
	    for (int i = 0; i < 100000; i++) {
	        pipeline.set("" + i, "" + i);
	    }
	    pipeline.exec();
	    List<Object> results = pipeline.syncAndReturnAll();
	    long end = System.currentTimeMillis();
	    System.out.println("Pipelined transaction: " + ((end - start)/1000.0) + " seconds");
	    jedis.disconnect();
	}
	
	/**
	 * 分布式直连同步调用
	 */
	@Test
	public void test5shardNormal() {
	    List<JedisShardInfo> shards = Arrays.asList(
	            new JedisShardInfo(serverIp,6379),
	            new JedisShardInfo(serverIp,6380));

	    ShardedJedis sharding = new ShardedJedis(shards);

	    long start = System.currentTimeMillis();
	    for (int i = 0; i < 100000; i++) {
	        String result = sharding.set("sn" + i, "n" + i);
	    }
	    long end = System.currentTimeMillis();
	    System.out.println("Simple@Sharing SET: " + ((end - start)/1000.0) + " seconds");

	    sharding.disconnect();
	}
	
	/**
	 * 分布式直连异步调用
	 */
	@Test
	public void test6shardpipelined() {
	    List<JedisShardInfo> shards = Arrays.asList(
	            new JedisShardInfo(serverIp,6379),
	            new JedisShardInfo(serverIp,6380));

	    ShardedJedis sharding = new ShardedJedis(shards);

	    ShardedJedisPipeline pipeline = sharding.pipelined();
	    long start = System.currentTimeMillis();
	    for (int i = 0; i < 100000; i++) {
	        pipeline.set("sp" + i, "p" + i);
	    }
	    List<Object> results = pipeline.syncAndReturnAll();
	    long end = System.currentTimeMillis();
	    System.out.println("Pipelined@Sharing SET: " + ((end - start)/1000.0) + " seconds");

	    sharding.disconnect();
	}
	
	/**
	 * 分布式连接池同步调用
	 */
	@Test
	public void test7shardSimplePool() {
	    List<JedisShardInfo> shards = Arrays.asList(
	            new JedisShardInfo(serverIp,6379),
	            new JedisShardInfo(serverIp,6380));

	    ShardedJedisPool pool = new ShardedJedisPool(new JedisPoolConfig(), shards);

	    ShardedJedis one = pool.getResource();

	    long start = System.currentTimeMillis();
	    for (int i = 0; i < 100000; i++) {
	        String result = one.set("spn" + i, "n" + i);
	    }
	    long end = System.currentTimeMillis();
	    pool.returnResource(one);
	    System.out.println("Simple@Pool SET: " + ((end - start)/1000.0) + " seconds");

	    pool.destroy();
	}
	
	/**
	 * 分布式连接池异步调用
	 */
	@Test
	public void test8shardPipelinedPool() {
	    List<JedisShardInfo> shards = Arrays.asList(
	            new JedisShardInfo(serverIp,6379),
	            new JedisShardInfo(serverIp,6380));

	    ShardedJedisPool pool = new ShardedJedisPool(new JedisPoolConfig(), shards);

	    ShardedJedis one = pool.getResource();

	    ShardedJedisPipeline pipeline = one.pipelined();

	    long start = System.currentTimeMillis();
	    for (int i = 0; i < 100000; i++) {
	        pipeline.set("sppn" + i, "n" + i);
	    }
	    List<Object> results = pipeline.syncAndReturnAll();
	    long end = System.currentTimeMillis();
	    pool.returnResource(one);
	    System.out.println("Pipelined@Pool SET: " + ((end - start)/1000.0) + " seconds");
	    pool.destroy();
	}
}
