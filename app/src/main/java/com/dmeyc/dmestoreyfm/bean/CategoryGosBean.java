package com.dmeyc.dmestoreyfm.bean;

import java.util.List;

public class CategoryGosBean {

    private List<GodsBean>goods;
    private PageindcatorBean paginator;

    public List<GodsBean> getGoods() {
        return goods;
    }

    public PageindcatorBean getPaginator() {
        return paginator;
    }

    public void setGoods(List<GodsBean> goods) {
        this.goods = goods;
    }

    public void setPaginator(PageindcatorBean paginator) {
        this.paginator = paginator;
    }
}
