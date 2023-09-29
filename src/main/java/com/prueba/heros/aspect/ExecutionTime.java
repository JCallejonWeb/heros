package com.prueba.heros.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class ExecutionTime {

    private long startTime;

    @Before("@annotation(com.prueba.heros.annotation.RequestExecutionTime)")
    public void before(JoinPoint joinPoint) {
        startTime = System.currentTimeMillis();
    }

    @After("@annotation(com.prueba.heros.annotation.RequestExecutionTime)")
    public void after(JoinPoint joinPoint) {
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        String methodName = joinPoint.getSignature().toShortString();
        log.info(methodName + " executed in " + executionTime + "ms");
    }
}