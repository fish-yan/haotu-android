package com.dmeyc.dmestoreyfm.bean;

import com.dmeyc.dmestoreyfm.bean.common.PaginatorBean;
import com.dmeyc.dmestoreyfm.bean.common.ReviewsBean;

import java.util.List;

/**
 * Created by jockie on 2017/12/28
 * Email:jockie911@gmail.com
 */

public class LookBean{

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
        private List<ReviewsBean> reviews;

        public PaginatorBean getPaginator() {
            return paginator;
        }

        public void setPaginator(PaginatorBean paginator) {
            this.paginator = paginator;
        }

        public List<ReviewsBean> getReviews() {
            return reviews;
        }

        public void setReviews(List<ReviewsBean> reviews) {
            this.reviews = reviews;
        }
    }
}
