package com.dmeyc.dmestoreyfm.bean.response;

import java.io.Serializable;

/**
 * create by cxg on 2019/11/24
 */
public class BaseRespBean implements Serializable {

    private String msg;
    private int code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
