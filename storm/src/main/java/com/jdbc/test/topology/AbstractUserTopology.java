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
package com.jdbc.test.topology;

import java.sql.Types;
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

public abstract class AbstractUserTopology {
    private static final List<String> setupSqls = Lists.newArrayList(
            "drop table if exists user",
            "drop table if exists department",
            "drop table if exists user_department",
            "create table if not exists user (user_id integer, user_name varchar(100), dept_name varchar(100), create_date datetime)",
            "create table if not exists department (dept_id integer, dept_name varchar(100))",
            "create table if not exists user_department (user_id integer, dept_id integer)",
            "insert into department values (1, 'R&D')",
            "insert into department values (2, 'Finance')",
            "insert into department values (3, 'HR')",
            "insert into department values (4, 'Sales')",
            "insert into user_department values (1, 1)",
            "insert into user_department values (2, 2)",
            "insert into user_department values (3, 3)",
            "insert into user_department values (4, 4)"
    );
    protected UserSpout userSpout;
    protected JdbcMapper jdbcMapper;
    protected JdbcLookupMapper jdbcLookupMapper;
    protected ConnectionProvider connectionProvider;

    protected static final String TABLE_NAME = "user";
    protected static final String JDBC_CONF = "jdbc.conf";
    protected static final String SELECT_QUERY = "select dept_name from department, user_department where department.dept_id = user_department.dept_id" +
            " and user_department.user_id = ?";

    @SuppressWarnings("rawtypes")
	public void execute(String[] args) throws Exception {
//        if (args.length != 4 && args.length != 5) {
//            System.out.println("Usage: " + this.getClass().getSimpleName() + " <dataSourceClassName> <dataSource.url> "
//                    + "<user> <password> [topology name]");
//            System.exit(-1);
//        }
        Map<String,Object> map = Maps.newHashMap();
        map.put("dataSourceClassName", "com.mysql.jdbc.jdbc2.optional.MysqlDataSource");//com.mysql.jdbc.jdbc2.optional.MysqlDataSource
        map.put("dataSource.url", "jdbc:mysql://192.168.9.7/test");//jdbc:mysql://localhost/test
        map.put("dataSource.user", "root");//root

//        if(args.length == 4) {
            map.put("dataSource.password", "root");//password
//        }

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
        Fields outputFields = new Fields("user_id", "user_name", "dept_name", "create_date");
        List<Column> queryParamColumns = Lists.newArrayList(new Column("user_id", Types.INTEGER));
        this.jdbcLookupMapper = new SimpleJdbcLookupMapper(outputFields, queryParamColumns);
        this.connectionProvider = new HikariCPConnectionProvider(map);
//        if (args.length == 4) {
            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("test", config, getTopology());
            Thread.sleep(30000);
            cluster.killTopology("test");
            cluster.shutdown();
            System.exit(0);
//        } else {
//            StormSubmitter.submitTopology(args[4], config, getTopology());
//        }
    }

    public abstract StormTopology getTopology();

}
