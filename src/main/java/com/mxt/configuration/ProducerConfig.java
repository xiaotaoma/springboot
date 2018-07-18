package com.mxt.configuration;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListener;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.mxt.service.MyMessageListenerConcurrently;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ProducerConfig {
    @Value("${rocketmq.namesrv}")
    private String namersrv;
    @Value("${rocketmq.broker}")
    private String broker;
    @Value("${rocketmq.producer.group}")
    private String producerGroupName;

    @Bean
    public DefaultMQProducer producer() throws MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer(producerGroupName);
        producer.setNamesrvAddr(namersrv);
        producer.setInstanceName("producer");
        producer.start();
        return producer;
    }

    @Bean
    public DefaultMQPushConsumer consumer() throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumerGroupName");
        consumer.setNamesrvAddr(namersrv);
        consumer.setInstanceName("consumer");
        consumer.subscribe("topic", "*");
        consumer.registerMessageListener(new MyMessageListenerConcurrently());
        consumer.start();
        return consumer;
    }
}
