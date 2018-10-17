package com.jqmkj.WaterResponseSystem.aop.logging;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//@Component
//@Aspect
public class LoggingAspect {

    private final Logger log = Logger.getLogger(this.getClass());

   
    @Pointcut("within(com.jqmkj.WaterResponseSystem.service..*)")
    public void loggingPointcut() {
        // Method is empty as this is just a Poincut, the implementations are in the advices.
    }

    
    @AfterThrowing(pointcut = "loggingPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
            log.error("Exception in "+joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName()+"() with cause = "+e.getCause() != null? e.getCause() : "NULL" );
    }

    @Around("loggingPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        if (log.isDebugEnabled()) {
            log.debug("Enter: "+joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName()+"() with args = "+Arrays.toString(joinPoint.getArgs()));
        }
        try {
            Object result = joinPoint.proceed();
            if (log.isDebugEnabled()) {
                log.debug("Exit: "+joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName()+"() with result = "+result+"" );
            }
            return result;
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument: "+ Arrays.toString(joinPoint.getArgs())+" in "+joinPoint.getSignature().getDeclaringTypeName()+"."+ joinPoint.getSignature().getName()+"()");
            throw e;
        }
    }
}
