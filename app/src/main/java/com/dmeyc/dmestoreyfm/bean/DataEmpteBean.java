package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;

public class DataEmpteBean  implements Serializable {

    /**
     * msg : 操作成功
     * code : 200
     * data :
     */

    private String msg;
    private String code;
    private String data;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
