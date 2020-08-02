package com.dmeyc.dmestoreyfm.bean;

/**
 * Created by jockie on 2018/2/7
 * Email:jockie911@gmail.com
 */

public class StoreBean {

    /**
     * code : 200
     * msg : 操作成功
     * data : {"product":[{"id":39,"createDate":"2018-01-16","modifyDate":"2018-01-16","version":1,"goods":2,"price":388,"size":"XL","color":"灰色","material":"","stock":500,"allocatedStock":0,"availableStock":500}]}
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
        private ProductBean product;

        public ProductBean getProduct() {
            return product;
        }

        public void setProduct(ProductBean product) {
            this.product = product;
        }

        public static class ProductBean {
            /**
             * id : 39
             * createDate : 2018-01-16
             * modifyDate : 2018-01-16
             * version : 1
             * goods : 2
             * price : 388
             * size : XL
             * color : 灰色
             * material :
             * stock : 500
             * allocatedStock : 0
             * availableStock : 500
             */

            private int id;
            private String createDate;
            private String modifyDate;
            private int version;
            private int goods;
            private int price;
            private String size;
            private String color;
            private String material;
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

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public String getSize() {
                return size;
            }

            public void setSize(String size) {
                this.size = size;
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
    }
}
