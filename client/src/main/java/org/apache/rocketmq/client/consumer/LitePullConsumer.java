/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.rocketmq.client.consumer;

import java.util.Collection;
import java.util.List;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;

public interface LitePullConsumer {

    /**
     * Start the consumer
     */
    void start() throws MQClientException;

    /**
     * Shutdown the consumer
     */
    void shutdown();

    /**
     * Subscribe some topic with subExpression
     *
     * @param subExpression subscription expression.it only support or operation such as "tag1 || tag2 || tag3" <br> if
     *                      null or * expression,meaning subscribe all
     */
    void subscribe(final String topic, final String subExpression) throws MQClientException;

    /**
     * Subscribe some topic with selector.
     *
     * @param selector message selector({@link MessageSelector}), can be null.
     */
    void subscribe(final String topic, final MessageSelector selector) throws MQClientException;

    /**
     * Unsubscribe consumption some topic
     *
     * @param topic message topic
     */
    void unsubscribe(final String topic);

    void assign(Collection<MessageQueue> messageQueues);

    List<MessageExt> poll();

    List<MessageExt> poll(long timeout);

    void seek(MessageQueue messageQueue, long offset) throws MQClientException;

    void pause(Collection<MessageQueue> messageQueues);

    boolean isAutoCommit();

    void setAutoCommit(boolean autoCommit);

    void resume(Collection<MessageQueue> messageQueues);

    Collection<MessageQueue> fetchMessageQueues(String topic) throws MQClientException;

    Long offsetForTimestamp(MessageQueue messageQueue, Long timestamp) throws MQClientException;

    void commitSync();


}