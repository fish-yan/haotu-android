package com.dmeyc.dmestoreyfm.bean;

public class CategoryGoodThreeBean {
   private int code;
   private String msg;
   private CategoryGosBean data;

    public int getCode() {
        return code;
    }

    public CategoryGosBean getData() {
        return data;
    }

    public void setData(CategoryGosBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setCode(int code) {
        this.code = code;
    }



    public void setMsg(String msg) {
        this.msg = msg;
    }
}
