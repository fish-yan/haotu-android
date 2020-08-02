package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;

public class H5UpdateReturnBean implements Serializable {

    /**
     * code : 200
     * data : 75
     * msg : 操作成功
     */

    private String code;
    private int data;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
