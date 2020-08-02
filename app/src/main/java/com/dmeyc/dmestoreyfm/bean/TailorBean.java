package com.dmeyc.dmestoreyfm.bean;

import java.util.List;

/**
 * Created by jockie on 2018/1/12
 * Email:jockie911@gmail.com
 */

public class TailorBean {

    /**
     * code : 200
     * msg : 操作成功
     * data : {"parentCustomProduct":[{"id":1,"customName":"领","childrenCustomProduct":[{"id":13,"goods":1,"custom":1,"customName":"领","name":"标准领","imageList":[{"large":"http://storeapi.dmeyc.com:8080/images/biaozhunling.jpg","thumbnail":"http://storeapi.dmeyc.com:8080/images/biaozhunling.jpg"}],"price":20},{"id":19,"goods":1,"custom":1,"customName":"领","name":"温莎领","imageList":[{"large":"http://storeapi.dmeyc.com:8080/images/liling.jpg","thumbnail":"http://storeapi.dmeyc.com:8080/images/liling.jpg"}],"price":20},{"id":20,"goods":1,"custom":1,"customName":"领","name":"立领","imageList":[{"large":"http://storeapi.dmeyc.com:8080/images/liling.jpg","thumbnail":"http://storeapi.dmeyc.com:8080/images/liling.jpg"}],"price":20}]},{"id":11,"customName":"门襟类","childrenCustomProduct":[{"id":14,"goods":1,"custom":11,"customName":"门襟类","name":"贴门襟","imageList":[{"large":"http://storeapi.dmeyc.com:8080/images/tmj.jpg","thumbnail":"http://storeapi.dmeyc.com:8080/images/tmj.jpg"}],"price":20},{"id":15,"goods":1,"custom":11,"customName":"门襟类","name":"法式门襟","imageList":[{"large":"http://storeapi.dmeyc.com:8080/images/fsmj.jpg","thumbnail":"http://storeapi.dmeyc.com:8080/images/fsmj.jpg"}],"price":20},{"id":21,"goods":1,"custom":11,"customName":"门襟类","name":"平门襟","imageList":[{"large":"http://storeapi.dmeyc.com:8080/images/pmj.jpg","thumbnail":"http://storeapi.dmeyc.com:8080/images/pmj.jpg"}],"price":20}]},{"id":5,"customName":"纽扣","childrenCustomProduct":[{"id":16,"goods":1,"custom":5,"customName":"纽扣","name":"六角单扣","imageList":[{"large":"http://storeapi.dmeyc.com:8080/images/liling.jpg","thumbnail":"http://storeapi.dmeyc.com:8080/images/liling.jpg"}],"price":20},{"id":22,"goods":1,"custom":5,"customName":"纽扣","name":"六角两扣","imageList":[{"large":"http://storeapi.dmeyc.com:8080/images/ljlk.jpg","thumbnail":"http://storeapi.dmeyc.com:8080/images/ljlk.jpg"}],"price":20},{"id":24,"goods":1,"custom":5,"customName":"纽扣","name":"六角三扣","imageList":[{"large":"http://storeapi.dmeyc.com:8080/images/ljlk.jpg","thumbnail":"http://storeapi.dmeyc.com:8080/images/ljlk.jpg"}],"price":20}]},{"id":8,"customName":"织带","childrenCustomProduct":[{"id":17,"goods":1,"custom":8,"customName":"织带","name":"织带1","imageList":[{"large":"http://storeapi.dmeyc.com:8080/images/ljlk.jpg","thumbnail":"http://storeapi.dmeyc.com:8080/images/ljlk.jpg"}],"price":20},{"id":18,"goods":1,"custom":8,"customName":"织带","name":"织带2","imageList":[{"large":"http://storeapi.dmeyc.com:8080/images/ljlk.jpg","thumbnail":"http://storeapi.dmeyc.com:8080/images/ljlk.jpg"}],"price":20}]},{"id":10,"customName":"贴标","childrenCustomProduct":[{"id":23,"goods":1,"custom":10,"customName":"贴标","name":"贴标2","imageList":[{"large":"http://storeapi.dmeyc.com:8080/images/ljlk.jpg","thumbnail":"http://storeapi.dmeyc.com:8080/images/ljlk.jpg"}],"price":20}]}]}
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

        private List<SetMealBean> defaultClothesList;
        private List<ParentCustomProductBean> parentCustomProduct;

        public List<ParentCustomProductBean> getParentCustomProduct() {
            return parentCustomProduct;
        }

        public void setParentCustomProduct(List<ParentCustomProductBean> parentCustomProduct) {
            this.parentCustomProduct = parentCustomProduct;
        }

        public List<SetMealBean> getDefaultClothesList() {
            return defaultClothesList;
        }

        public void setDefaultClothesList(List<SetMealBean> defaultClothesList) {
            this.defaultClothesList = defaultClothesList;
        }

        public static class ParentCustomProductBean {
            /**
             * id : 1
             * customName : 领
             * childrenCustomProduct : [{"id":13,"goods":1,"custom":1,"customName":"领","name":"标准领","imageList":[{"large":"http://storeapi.dmeyc.com:8080/images/biaozhunling.jpg","thumbnail":"http://storeapi.dmeyc.com:8080/images/biaozhunling.jpg"}],"price":20},{"id":19,"goods":1,"custom":1,"customName":"领","name":"温莎领","imageList":[{"large":"http://storeapi.dmeyc.com:8080/images/liling.jpg","thumbnail":"http://storeapi.dmeyc.com:8080/images/liling.jpg"}],"price":20},{"id":20,"goods":1,"custom":1,"customName":"领","name":"立领","imageList":[{"large":"http://storeapi.dmeyc.com:8080/images/liling.jpg","thumbnail":"http://storeapi.dmeyc.com:8080/images/liling.jpg"}],"price":20}]
             */

            private int id;
            private String customName;
            private List<ChildrenCustomProductBean> childrenCustomProduct;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCustomName() {
                return customName;
            }

            public void setCustomName(String customName) {
                this.customName = customName;
            }

            public List<ChildrenCustomProductBean> getChildrenCustomProduct() {
                return childrenCustomProduct;
            }

            public void setChildrenCustomProduct(List<ChildrenCustomProductBean> childrenCustomProduct) {
                this.childrenCustomProduct = childrenCustomProduct;
            }

            public static class ChildrenCustomProductBean {
                /**
                 * id : 13
                 * goods : 1
                 * custom : 1
                 * customName : 领
                 * name : 标准领
                 * imageList : [{"large":"http://storeapi.dmeyc.com:8080/images/biaozhunling.jpg","thumbnail":"http://storeapi.dmeyc.com:8080/images/biaozhunling.jpg"}]
                 * price : 20.0
                 */

                private int id;
                private int goods;
                private int custom;
                private String customName;
                private String name;
                private double price;
                private List<ImageListBean> imageList;
                private  int defaultNumber;
                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getGoods() {
                    return goods;
                }

                public void setGoods(int goods) {
                    this.goods = goods;
                }

                public int getCustom() {
                    return custom;
                }

                public void setCustom(int custom) {
                    this.custom = custom;
                }

                public String getCustomName() {
                    return customName;
                }

                public void setCustomName(String customName) {
                    this.customName = customName;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public double getPrice() {
                    return price;
                }

                public void setPrice(double price) {
                    this.price = price;
                }

                public List<ImageListBean> getImageList() {
                    return imageList;
                }

                public void setImageList(List<ImageListBean> imageList) {
                    this.imageList = imageList;
                }

                public int getDefaultNumber() {
                    return defaultNumber;
                }

                public void setDefaultNumber(int defaultNumber) {
                    this.defaultNumber = defaultNumber;
                }

                public static class ImageListBean {
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
            }
        }
    }
}
