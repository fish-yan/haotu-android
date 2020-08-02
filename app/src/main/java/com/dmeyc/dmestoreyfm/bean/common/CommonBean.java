package com.dmeyc.dmestoreyfm.bean.common;

/**
 * Created by jockie on 2018/2/1
 * Email:jockie911@gmail.com
 */

public class CommonBean {
    private int code;
    private String msg;
    private Object data;

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

    public void setData(Object t){
        this.data = t;
    }

    public Object getData(){
        return data;
    }
}
