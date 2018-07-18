package com.mxt.service;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageQueue;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProducerService {
    @Autowired
    private DefaultMQProducer producer;


    public void sendMsg() throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
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
    }
}
