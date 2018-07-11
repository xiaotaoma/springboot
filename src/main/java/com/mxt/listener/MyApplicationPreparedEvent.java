package com.mxt.listener;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * spring boot上下文context创建完成，但此时spring中的bean是没有完全加载完成的。
 * 在获取完上下文后，可以将上下文传递出去做一些额外的操作。
 */
public class MyApplicationPreparedEvent implements ApplicationListener<ApplicationPreparedEvent> {
    @Override
    public void onApplicationEvent(ApplicationPreparedEvent applicationPreparedEvent) {
        ConfigurableApplicationContext applicationContext = applicationPreparedEvent.getApplicationContext();
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        BeanFactory parentBeanFactory = applicationContext.getParentBeanFactory();

        System.out.println("MyApplicationPreparedEvent");
    }
}
