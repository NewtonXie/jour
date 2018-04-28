package com.gzcb.creditcard.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class WebAroundAop {
    static Logger logger = LoggerFactory.getLogger(WebAroundAop.class);

    @Pointcut("execution(* com.gzcb.creditcard.controller.*.*(..))")
    private  void executeService(){}

    @Around("executeService()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        logger.info("通知方法：{}",proceedingJoinPoint.getSignature().getName());
        try{
            Object proceed = proceedingJoinPoint.proceed();
            return proceed;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
