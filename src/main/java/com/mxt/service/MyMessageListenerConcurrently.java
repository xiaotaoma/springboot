package com.mxt.service;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.message.MessageExt;

import java.util.List;

public class MyMessageListenerConcurrently implements MessageListenerConcurrently {

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        for (int i = 0; i < list.size(); i++) {
            MessageExt messageExt = list.get(i);
            byte[] body = messageExt.getBody();
            System.out.println(new String(body));
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
