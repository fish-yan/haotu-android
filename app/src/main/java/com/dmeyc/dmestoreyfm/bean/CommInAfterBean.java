package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;

public class CommInAfterBean implements Serializable {

    private int code;
    private String msg;
    private int  data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(int t){
        this.data = t;
    }

    public int getData(){
        return data;
    }
}
