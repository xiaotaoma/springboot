package com.mxt.aspect;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

public class MyMethodInterceptor implements MethodInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(MyMethodInterceptor.class);
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        Method method = invocation.getMethod();
        logger.info("ddddddddddddddddd:" + method.getName());
        logger.info("ddddddddddddddddd:" + method.getDeclaringClass().toString());
        Object proceed = invocation.proceed();


        return proceed;
    }
}
