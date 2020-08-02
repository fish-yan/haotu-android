package com.dmeyc.dmestoreyfm.bean.response;

import java.util.List;

/**
 * create by cxg on 2019/11/30
 * 俱乐部bean
 */
public class RelationShopsBean extends BaseRespBean {
    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public class DataBean{

    }
}
