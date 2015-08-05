/**
 * 文件名： AbstractTopology.java
 *  
 * 版本信息：  
 * 日期：2015年7月31日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.vion;

import java.util.List;
import java.util.Map;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.generated.StormTopology;
import backtype.storm.tuple.Fields;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jdbc.common.Column;
import com.jdbc.common.ConnectionProvider;
import com.jdbc.common.HikariCPConnectionProvider;
import com.jdbc.common.JdbcClient;
import com.jdbc.mapper.JdbcLookupMapper;
import com.jdbc.mapper.JdbcMapper;
import com.jdbc.mapper.SimpleJdbcLookupMapper;
import com.jdbc.mapper.SimpleJdbcMapper;
import com.jdbc.test.spout.UserSpout;

/**
 * <b>功能描述</b> <br>
 * 
 * @author GANJX
 * @date 2015年7月31日 下午5:20:57
 */
public abstract class AbstractTopology {
	private static final List<String> setupSqls = Lists.newArrayList(
            "drop table if exists message",
            "create table if not exists message (str varchar(100), create_date datetime)"
    );
    protected UserSpout userSpout;
    protected JdbcMapper jdbcMapper;
    protected JdbcLookupMapper jdbcLookupMapper;
    protected ConnectionProvider connectionProvider;

    protected static final String TABLE_NAME = "message";
    protected static final String JDBC_CONF = "jdbc.conf";

    @SuppressWarnings("rawtypes")
	public void execute(String[] args) throws Exception {
        Map<String,Object> map = Maps.newHashMap();
        map.put("dataSourceClassName", "com.mysql.jdbc.jdbc2.optional.MysqlDataSource");//com.mysql.jdbc.jdbc2.optional.MysqlDataSource
        map.put("dataSource.url", "jdbc:mysql://192.168.9.7/test");//jdbc:mysql://localhost/test
        map.put("dataSource.user", "root");//root
        map.put("dataSource.password", "root");//password

        Config config = new Config();
        config.put(JDBC_CONF, map);

        ConnectionProvider connectionProvider = new HikariCPConnectionProvider(map);
        connectionProvider.prepare();
        int queryTimeoutSecs = 60;
        JdbcClient jdbcClient = new JdbcClient(connectionProvider, queryTimeoutSecs);
        for (String sql : setupSqls) {
            jdbcClient.executeSql(sql);
        }

        this.userSpout = new UserSpout();
        this.jdbcMapper = new SimpleJdbcMapper(TABLE_NAME, connectionProvider);
        connectionProvider.cleanup();
        Fields outputFields = new Fields("str", "create_date");
        List<Column> queryParamColumns = Lists.newArrayList();
        this.jdbcLookupMapper = new SimpleJdbcLookupMapper(outputFields, queryParamColumns);
        this.connectionProvider = new HikariCPConnectionProvider(map);
        
		LocalCluster cluster = new LocalCluster();
		cluster.submitTopology("test", config, getTopology());
//		Thread.sleep(30000);
//		cluster.killTopology("test");
//		cluster.shutdown();
//		System.exit(0);
    }

    public abstract StormTopology getTopology();
}
