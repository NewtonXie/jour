//package com.gzcb.creditcard.aop;
//
//import com.alibaba.fastjson.JSONObject;
//import com.gzcb.creditcard.controller.ExceptionController;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//@Aspect
//public class ExceptionHandler{
//
//    private Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);
//
//    @Autowired
//    private ExceptionController exceptionController;
//    @Pointcut("execution(* com.gzcb.creditcard.controller.*.*(..))")
//    private  void log(){}
//
//
//    @Around("log()")
//    public JSONObject doAround(ProceedingJoinPoint proceedingJoinPoint)throws Throwable{
//        logger.info("通知异常方法：{}",proceedingJoinPoint.getSignature().getName());
//        try{
//
//        }catch (Exception e){
//            return exceptionController.exceptionGet(e);
//        }
//        return null;
//    }
//
//
//}
