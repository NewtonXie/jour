package com.gzcb.creditcard.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 推送信息对象
 */
public class RedisVo implements Serializable {
    private static  final  long serialVersionUID = 1L;
    private String content;
    private Date time;
    private String name;
    private String cron;
    private Integer id;
    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
