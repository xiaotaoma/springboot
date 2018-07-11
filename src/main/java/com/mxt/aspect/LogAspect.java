package com.mxt.aspect;

import com.mxt.annotation.Action;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class LogAspect {

    //切入点
    @Pointcut("@annotation(com.mxt.annotation.Action)")
    public void log() {

    }

    @Before("log()")
    public void before(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Action action = method.getAnnotation(Action.class);
        System.out.println(action.value());
    }

    @AfterReturning(pointcut = "log()", returning = "returnValue")
    public void after(JoinPoint joinPoint, Object returnValue) {
        System.out.println("returnValue:" + returnValue);
    }


}
