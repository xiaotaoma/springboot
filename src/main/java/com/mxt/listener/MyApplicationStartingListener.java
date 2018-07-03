package com.mxt.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

import java.util.Arrays;

/**
 * spring boot启动开始时执行的事件
 * 在该事件中可以获取到SpringApplication对象，可做一些执行前的设置
 */
public class MyApplicationStartingListener implements ApplicationListener<ApplicationStartingEvent>{

    private static final Logger logger = LoggerFactory.getLogger(MyApplicationStartingListener.class);

    @Override
    public void onApplicationEvent(ApplicationStartingEvent applicationStartingEvent) {
        logger.info("MyApplicationStartingListener:------------------------------------------");
        /*logger.info("source:" + applicationStartingEvent.getSource().toString());//
        logger.info("args:" + applicationStartingEvent.getArgs() == null ? null :Arrays.toString(applicationStartingEvent.getArgs()));
        logger.info("springApplication:" + applicationStartingEvent.getSpringApplication());
        logger.info("timestamp:" + applicationStartingEvent.getTimestamp());
        logger.info("MyApplicationStartingListener:------------------------------------------");*/
    }
}
