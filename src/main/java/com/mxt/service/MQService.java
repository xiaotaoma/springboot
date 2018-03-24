package com.mxt.service;

import com.alibaba.fastjson.JSONObject;
import com.mxt.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class MQService {

    @Autowired
    private TestService testService;

    /**
     * 监听消息队列
     * @param msg
     */
    @JmsListener(destination = "test.queue")
    public void receiveMsg(String msg) {
        Test test = JSONObject.parseObject(msg, Test.class);
        testService.insert(test);
    }

    /**
     * 监听消息队列
     * 一个消息队列可以有多个消费者监听，分别在不用的线程处理
     * @param msg
     */
    @JmsListener(destination = "test.queue")
    public void receiveMsg1(String msg) {
        Test test = JSONObject.parseObject(msg, Test.class);
        testService.insert(test);
    }
}
