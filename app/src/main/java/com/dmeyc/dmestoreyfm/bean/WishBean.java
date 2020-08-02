package com.dmeyc.dmestoreyfm.bean;

import com.dmeyc.dmestoreyfm.bean.common.BrandDesignerBean;
import com.dmeyc.dmestoreyfm.bean.common.GoodsBean;
import com.dmeyc.dmestoreyfm.bean.common.PaginatorBean;

import java.util.List;

/**
 * Created by jockie on 2018/2/2
 * Email:jockie911@gmail.com
 */

public class WishBean {

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
        private List<GoodsBean> goods;
        private List<BrandDesignerBean> brandsAndDesigner;
        private PaginatorBean paginator;

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public List<BrandDesignerBean> getBrandsAndDesigner() {
            return brandsAndDesigner;
        }

        public void setBrandsAndDesigner(List<BrandDesignerBean> brandsAndDesigner) {
            this.brandsAndDesigner = brandsAndDesigner;
        }

        public PaginatorBean getPaginator() {
            return paginator;
        }

        public void setPaginator(PaginatorBean paginator) {
            this.paginator = paginator;
        }
    }
}
