package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;

public class TestUplodeBean  implements Serializable {


    /**
     * code : 200
     * data : http://192.168.0.104/group1/M00/00/01/wKgAaFywUOWAHu-8AAEQu2UwE4M439.jpg
     * msg : 操作成功
     */

    private String code;
    private Object data;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
