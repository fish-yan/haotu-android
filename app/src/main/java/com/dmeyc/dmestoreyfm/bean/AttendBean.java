package com.dmeyc.dmestoreyfm.bean;

public class AttendBean  {
    private int code;
    private boolean data;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    public boolean isData() {
        return data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(boolean data) {
        this.data = data;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
