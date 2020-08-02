package com.dmeyc.dmestoreyfm.bean;

import com.dmeyc.dmestoreyfm.bean.common.BrandDesignerBean;
import com.dmeyc.dmestoreyfm.bean.common.GoodsBean;

import java.util.List;

/**
 * Created by jockie on 2018/1/5
 * Email:jockie911@gmail.com
 */

public class RecommendBean {


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


        private List<BrandDesignerBean> designers;
        private List<PromotionsBean> promotions;
        private List<BrandDesignerBean> brands;
        private List<GoodsBean> goods;
         private StoreData store;


        public StoreData getStore() {
            return store;
        }

        public void setStore(StoreData store) {
            this.store = store;
        }

        public List<BrandDesignerBean> getBrands() {
            return brands;
        }

        public void setBrands(List<BrandDesignerBean> brands) {
            this.brands = brands;
        }

        public List<BrandDesignerBean> getDesigners() {
            return designers;
        }

        public void setDesigners(List<BrandDesignerBean> designers) {
            this.designers = designers;
        }

        public List<PromotionsBean> getPromotions() {
            return promotions;
        }

        public void setPromotions(List<PromotionsBean> promotions) {
            this.promotions = promotions;
        }


        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public static class PromotionsBean {

            private int id;
            private String createDate;
            private String modifyDate;
            private int version;
            private String image;
            private String title;
            private String info;
            private String url;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public String getModifyDate() {
                return modifyDate;
            }

            public void setModifyDate(String modifyDate) {
                this.modifyDate = modifyDate;
            }

            public int getVersion() {
                return version;
            }

            public void setVersion(int version) {
                this.version = version;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
