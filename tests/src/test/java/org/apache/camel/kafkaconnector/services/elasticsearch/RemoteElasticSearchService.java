/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.camel.kafkaconnector.services.elasticsearch;

import org.apache.camel.kafkaconnector.TestCommon;
import org.apache.camel.kafkaconnector.clients.elasticsearch.ElasticSearchClient;

public class RemoteElasticSearchService implements ElasticSearchService {
    private static final int ELASTIC_SEARCH_PORT = 9200;

    private int getPort() {
        String strPort = System.getProperty("elasticsearch.port");

        if (strPort != null) {
            return Integer.parseInt(strPort);
        }

        return ELASTIC_SEARCH_PORT;
    }

    private String getHost() {
        return System.getProperty("elasticsearch.host");
    }

    @Override
    public String getHttpHostAddress() {
        return getHost() + ":" + getPort();
    }

    @Override
    public void initialize() {
        // NO-OP
    }

    @Override
    public ElasticSearchClient getClient() {
        return new ElasticSearchClient(getHost(), getPort(), TestCommon.DEFAULT_ELASTICSEARCH_INDEX);
    }
}
