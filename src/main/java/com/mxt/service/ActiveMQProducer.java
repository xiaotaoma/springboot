package com.mxt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class ActiveMQProducer {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    /**
     * 发送消息到队列
     * @param queueName 队列名称
     * @param msg 消息
     */
    public void sendMsg(String queueName, String msg) {
        jmsMessagingTemplate.convertAndSend(queueName, msg);
    }
}
