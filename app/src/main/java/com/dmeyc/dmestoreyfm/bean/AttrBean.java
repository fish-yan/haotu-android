package com.dmeyc.dmestoreyfm.bean;

import java.util.List;

/**
 * Created by jockie on 2018/2/7
 * Email:jockie911@gmail.com
 */

public class AttrBean {

    /**
     * code : 200
     * msg : 操作成功
     * data : {"product":{"id":58,"createDate":"2018-01-16","modifyDate":"2018-01-16","version":1,"goods":1,"color":"黑色","material":"麻布","price":129,"stock":0,"allocatedStock":0,"availableStock":0},"customImages":[{"large":"http://storeapi.dmeyc.com:8080/images/biaozhunling.jpg","thumbnail":"http://storeapi.dmeyc.com:8080/images/biaozhunling.jpg"}],"attributeDetails":[{"childrenAttributeName":["麻布"],"attributeName":"面料","attributeKey":"material"},{"childrenAttributeName":["深黑色","黑色"],"attributeName":"颜色","attributeKey":"color"},{"childrenAttributeName":["M,X,XL,XXL"],"attributeName":"尺寸","attributeKey":"size"}]}
     */

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
         * product : {"id":58,"createDate":"2018-01-16","modifyDate":"2018-01-16","version":1,"goods":1,"color":"黑色","material":"麻布","price":129,"stock":0,"allocatedStock":0,"availableStock":0}
         * customImages : [{"large":"http://storeapi.dmeyc.com:8080/images/biaozhunling.jpg","thumbnail":"http://storeapi.dmeyc.com:8080/images/biaozhunling.jpg"}]
         * attributeDetails : [{"childrenAttributeName":["麻布"],"attributeName":"面料","attributeKey":"material"},{"childrenAttributeName":["深黑色","黑色"],"attributeName":"颜色","attributeKey":"color"},{"childrenAttributeName":["M,X,XL,XXL"],"attributeName":"尺寸","attributeKey":"size"}]
         */

        private ProductBean product;
        private List<CustomImagesBean> customImages;
        private List<AttributeDetailsBean> attributeDetails;

        public ProductBean getProduct() {
            return product;
        }

        public void setProduct(ProductBean product) {
            this.product = product;
        }

        public List<CustomImagesBean> getCustomImages() {
            return customImages;
        }

        public void setCustomImages(List<CustomImagesBean> customImages) {
            this.customImages = customImages;
        }

        public List<AttributeDetailsBean> getAttributeDetails() {
            return attributeDetails;
        }

        public void setAttributeDetails(List<AttributeDetailsBean> attributeDetails) {
            this.attributeDetails = attributeDetails;
        }

        public static class ProductBean {
            /**
             * id : 58
             * createDate : 2018-01-16
             * modifyDate : 2018-01-16
             * version : 1
             * goods : 1
             * color : 黑色
             * material : 麻布
             * price : 129
             * stock : 0
             * allocatedStock : 0
             * availableStock : 0
             */

            private int id;
            private String createDate;
            private String modifyDate;
            private int version;
            private int goods;
            private String color;
            private String material;
            private int price;
            private int stock;
            private int allocatedStock;
            private int availableStock;

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

            public int getGoods() {
                return goods;
            }

            public void setGoods(int goods) {
                this.goods = goods;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }

            public String getMaterial() {
                return material;
            }

            public void setMaterial(String material) {
                this.material = material;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getStock() {
                return stock;
            }

            public void setStock(int stock) {
                this.stock = stock;
            }

            public int getAllocatedStock() {
                return allocatedStock;
            }

            public void setAllocatedStock(int allocatedStock) {
                this.allocatedStock = allocatedStock;
            }

            public int getAvailableStock() {
                return availableStock;
            }

            public void setAvailableStock(int availableStock) {
                this.availableStock = availableStock;
            }
        }

        public static class CustomImagesBean {
            /**
             * large : http://storeapi.dmeyc.com:8080/images/biaozhunling.jpg
             * thumbnail : http://storeapi.dmeyc.com:8080/images/biaozhunling.jpg
             */

            private String large;
            private String thumbnail;

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getThumbnail() {
                return thumbnail;
            }

            public void setThumbnail(String thumbnail) {
                this.thumbnail = thumbnail;
            }
        }

        public static class AttributeDetailsBean {
            /**
             * childrenAttributeName : ["麻布"]
             * attributeName : 面料
             * attributeKey : material
             */

            private String attributeName;
            private String attributeKey;
            private List<String> childrenAttributeName;

            public String getAttributeName() {
                return attributeName;
            }

            public void setAttributeName(String attributeName) {
                this.attributeName = attributeName;
            }

            public String getAttributeKey() {
                return attributeKey;
            }

            public void setAttributeKey(String attributeKey) {
                this.attributeKey = attributeKey;
            }

            public List<String> getChildrenAttributeName() {
                return childrenAttributeName;
            }

            public void setChildrenAttributeName(List<String> childrenAttributeName) {
                this.childrenAttributeName = childrenAttributeName;
            }
        }
    }
}
