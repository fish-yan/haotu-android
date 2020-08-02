package com.dmeyc.dmestoreyfm.bean;

import com.dmeyc.dmestoreyfm.bean.common.OrderBean;
import com.dmeyc.dmestoreyfm.bean.common.PaginatorBean;

import java.util.List;

/**
 * Created by jockie on 2018/2/8
 * Email:jockie911@gmail.com
 */

public class OrderListBean {

    private int code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        private PaginatorBean paginator;
        private List<List<List<OrderBean>>> listOrder;

        public PaginatorBean getPaginator() {
            return paginator;
        }

        public void setPaginator(PaginatorBean paginator) {
            this.paginator = paginator;
        }

        public List<List<List<OrderBean>>> getListOrder() {
            return listOrder;
        }

        public void setListOrder(List<List<List<OrderBean>>> listOrder) {
            this.listOrder = listOrder;
        }
    }
}
