package com.gzcb.creditcard.service;

import com.gzcb.creditcard.dao.entities.User;

import java.util.List;

public interface RedisService {
    void setCode(String key,String code);
    String getCode(String key);
    Object get(String key);
    void set(String key,Object object);
    void setList(String key,Object o);
    Object getAll(String pattern);
//    List<?> getList(String )
}
