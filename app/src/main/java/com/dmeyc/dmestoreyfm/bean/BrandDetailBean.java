package com.dmeyc.dmestoreyfm.bean;

import com.dmeyc.dmestoreyfm.bean.common.BrandDesignerBean;
import com.dmeyc.dmestoreyfm.bean.common.GoodsBean;
import com.dmeyc.dmestoreyfm.bean.common.PaginatorBean;
import com.dmeyc.dmestoreyfm.bean.common.ProductCategoryBean;

import java.util.List;

/**
 * Created by jockie on 2018/1/9
 * Email:jockie911@gmail.com
 */

public class BrandDetailBean {


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
        /**
         * goods : [{"id":23,"createDate":"2017-12-06","modifyDate":"2017-12-23","version":null,"name":"XFDS","caption":"皮衣","price":1299,"sales":208,"image":"http://www.icicle.com/assets/imgsupl/RG6.0_0.66019100_1505876360_9c4f_news_images_image_3-4.jpg","isCustom":false,"productCategory":34,"brandDesigner":null,"isCollection":null}]
         * designer : {"id":1,"createDate":"2017-12-12","modifyDate":"2017-12-16","version":null,"name":"lisa","heading":null,"image":null,"imageDetail":"http://www.icicle.com/assets/imgsupl/RG6.0_0.69960300_1510736797_543a_landing_page_desktop_image_-3mini.jpg","recentImage":null,"introduction":"在2014年10月举行的\u201c共生国际\r\n\u201c空谷幽兰\u2014\u2014中国山西大同造园\u201d\r\n\u201c空谷幽兰\u2014\u2014中国山西大同造园\u201d(5张)\r\n 建筑展\u201d中，戴帆展示了他新的建筑概念作品\u201c空间、时间以及超越\u201c，一起参展的还有英国扎哈·哈迪德、奥地利蓝天组、日本矶崎新、美国的理查德·迈耶建筑事务所等在国际上活跃的建筑师，戴帆的作品表现了卵生的建筑空间哲学，空间万物产生在一个卵形物中，卵是混沌，蒙蒙灰云，一团大气或初生状态，它体现了中国时间和空间回转往复的特质。","designserTop":null,"designserCentre":null,"designserBottom":null,"isSelection":null,"isAttend":false}
         * paginator : {"limit":10,"page":1,"totalCount":1,"offset":0,"slider":[1],"endRow":1,"hasNextPage":false,"startRow":1,"totalPages":1,"hasPrePage":false,"firstPage":true,"prePage":1,"lastPage":true,"nextPage":1}
         * productCategory : [{"id":34,"categoryName":"西裤"}]
         */

        private BrandDesignerBean designer;
        private BrandDesignerBean brand;
        private PaginatorBean paginator;
        private List<GoodsBean> goods;
        private List<ProductCategoryBean> productCategory;

        public BrandDesignerBean getDesigner() {
            return designer;
        }

        public void setDesigner(BrandDesignerBean designer) {
            this.designer = designer;
        }

        public PaginatorBean getPaginator() {
            return paginator;
        }

        public void setPaginator(PaginatorBean paginator) {
            this.paginator = paginator;
        }

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public List<ProductCategoryBean> getProductCategory() {
            return productCategory;
        }

        public void setProductCategory(List<ProductCategoryBean> productCategory) {
            this.productCategory = productCategory;
        }

        public BrandDesignerBean getBrand() {
            return brand;
        }

        public void setBrand(BrandDesignerBean brand) {
            this.brand = brand;
        }
    }
}
