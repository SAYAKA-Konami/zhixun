package com.macro.mall.tiny.common.aop;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(3)
@Slf4j
public class ControllerLog {

    @Pointcut("execution(public * com.macro.mall.tiny..controller.*.*(..))")
    public void controllerAspect(){}

    @Before("controllerAspect()")
    public void printRequest(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();
        String classAndMethodName = signature.toShortString();
        log.info("Method: {}, Params:{}", classAndMethodName, JSON.toJSONString(joinPoint.getArgs()));
    }
}
