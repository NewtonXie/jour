package com.gzcb.creditcard.controller;

import com.alibaba.fastjson.JSONObject;
import com.gzcb.creditcard.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class ExceptionController {

    private static Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JSONObject exceptionGet(Exception e){
        if(e instanceof  RuntimeException){
            return ResponseUtil.failed(e.getMessage());
        }
        logger.error("【异常】{}",e);
        return  ResponseUtil.failed("错误无法查询");
    }
}
