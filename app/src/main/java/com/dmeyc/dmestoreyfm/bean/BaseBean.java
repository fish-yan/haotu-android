package com.dmeyc.dmestoreyfm.bean;

/**
 * Created by jockie on 2017/12/28
 * Email:jockie911@gmail.com
 */

public class BaseBean<T> {

    private int code;
    private String msg;
    private T data;

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

    public void setData(T t){
        this.data = t;
    }

    public T getData(){
        return data;
    }
}
