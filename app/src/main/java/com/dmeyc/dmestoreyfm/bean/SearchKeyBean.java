package com.dmeyc.dmestoreyfm.bean;

import java.util.List;

public class SearchKeyBean {

    /**
     * code : 200
     * msg : 操作成功
     * data : ["大衣男","大衣女","大衣女秋","大衣男 韩版","大衣女 中长款","大衣女 秋季 2018 新款"]
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
