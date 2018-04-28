package com.gzcb.creditcard.utils;

/**
 * 异常类
 *
 * @author tangliu
 */
public class JourException extends Exception {

    private String code;
    private String msg;

    public JourException(Exception err) {
        super(err);

        this.code = "-1";
        this.msg = err.getMessage();
    }

    public JourException(String msg) {
        super(msg);

        this.code = "-1";
        this.msg = msg;
    }

    public JourException(String code, String msg) {

        super(code + ":" + msg);

        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
