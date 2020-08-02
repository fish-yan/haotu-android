package com.dmeyc.dmestoreyfm.bean;

import com.dmeyc.dmestoreyfm.bean.common.OrderBean;

import java.util.List;

/**
 * Created by jockie on 2018/2/1
 * Email:jockie911@gmail.com
 */

public class OrderStatusBean {

    private int code;
    private String msg;
    private List<List<OrderBean>> data;

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

    public List<List<OrderBean>> getData() {
        return data;
    }

    public void setData(List<List<OrderBean>> data) {
        this.data = data;
    }


}
