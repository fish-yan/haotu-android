package com.dmeyc.dmestoreyfm.bean;

import java.util.List;

/**
 * Created by jockie on 2018/3/14
 * Email:jockie911@gmail.com
 */

public class HotDataBean {
    /**
     * code : 200
     * msg : 操作成功
     * data : ["裙子","皮夹克","苹果8","芒果","手机"]
     */

    private int code;
    private String msg;
    private List<String> data;

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

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
