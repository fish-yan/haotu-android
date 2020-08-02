package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;

public class CheckChatActivity implements Serializable {

    /**
     * msg : 操作成功
     * code : 200
     * data : 1
     */

    private String msg;
    private String code;
    private int data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

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
}
