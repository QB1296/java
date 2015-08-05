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

import backtype.storm.generated.StormTopology;
import backtype.storm.tuple.Fields;

import com.google.common.collect.Lists;
import com.jdbc.common.Column;
import com.jdbc.mapper.SimpleJdbcLookupMapper;
import com.jdbc.test.spout.UserSpout;
import com.jdbc.trident.state.JdbcQuery;
import com.jdbc.trident.state.JdbcState;
import com.jdbc.trident.state.JdbcStateFactory;
import com.jdbc.trident.state.JdbcUpdater;

import storm.trident.Stream;
import storm.trident.TridentState;
import storm.trident.TridentTopology;

import java.sql.Types;

public class UserPersistanceTridentTopology extends AbstractUserTopology {

    public static void main(String[] args) throws Exception {
        new UserPersistanceTridentTopology().execute(args);
    }

    @SuppressWarnings("rawtypes")
	@Override
    public StormTopology getTopology() {
        TridentTopology topology = new TridentTopology();

        JdbcState.Options options = new JdbcState.Options()
                .withConnectionPrvoider(connectionProvider)
                .withMapper(this.jdbcMapper)
                .withJdbcLookupMapper(new SimpleJdbcLookupMapper(new Fields("dept_name"), Lists.newArrayList(new Column("user_id", Types.INTEGER))))
                .withTableName(TABLE_NAME)
                .withSelectQuery(SELECT_QUERY);

        JdbcStateFactory jdbcStateFactory = new JdbcStateFactory(options);

        Stream stream = topology.newStream("userSpout", new UserSpout());
        TridentState state = topology.newStaticState(jdbcStateFactory);
        stream = stream.stateQuery(state, new Fields("user_id","user_name","create_date"), new JdbcQuery(), new Fields("dept_name"));
        stream.partitionPersist(jdbcStateFactory, new Fields("user_id","user_name","dept_name","create_date"),  new JdbcUpdater(), new Fields());
        return topology.build();
    }
}
