package com.gzcb.creditcard.utils;


import com.alibaba.fastjson.JSONObject;

/**
 * @description: 统一接口返回格式
 * @author: tangliu
 * @create: 2017-10-25 11:38
 **/
public class ResponseUtil {

    public static JSONObject succeed() {
        return succeed("");
    }

    public static JSONObject succeed(Object data) {

        JSONObject response = new JSONObject();
        response.put("code", 0);
        response.put("msg", "SUCCESS");
        response.put("data", data == null ? "" : data);
        response = JsonUtils.filterNull(response);
        return response;
    }


    public static JSONObject failed() {
        return failed("REQUEST FAILED");
    }

    public static JSONObject failed(String msg) {
        return failed(-1, msg);
    }

    public static JSONObject failed(Integer code, String msg) {

        JSONObject response = new JSONObject();
        response.put("code", code);
        response.put("msg", msg);
        response.put("data", "");

        return response;
    }

}
