package com.mxt.utils;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageQueue;

import java.util.UUID;

public class Producer {
    public static void main(String[] args) {
        DefaultMQProducer producer = new DefaultMQProducer("producerGroupName");
        producer.setNamesrvAddr("192.168.0.33:9876");
        producer.setInstanceName("producer");
        try {
            producer.start();

            String topic = "topic";
            Message message = new Message();
            message.setTopic(topic);
            message.setBody("hello".getBytes());
            message.setTags("aaaddsads");
            message.setKeys(UUID.randomUUID().toString());
            SendResult send = producer.send(message);

            System.out.println("sendStatus:" + send.getSendStatus());
            System.out.println("msgId:" + send.getMsgId());
            System.out.println("offSetMsgId:" + send.getOffsetMsgId());
            System.out.println("regionId:" + send.getRegionId());
            System.out.println("transactionId:" + send.getTransactionId());
            MessageQueue messageQueue = send.getMessageQueue();
            System.out.println("messageQueue, brokerName:" + messageQueue.getBrokerName());
            System.out.println("messageQueue, topic:" + messageQueue.getTopic());
            System.out.println("messageQueue, queueId:" + messageQueue.getQueueId());

        } catch (Exception e) {
            e.printStackTrace();
        }
        producer.shutdown();
    }
}
