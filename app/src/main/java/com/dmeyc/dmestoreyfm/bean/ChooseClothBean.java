package com.dmeyc.dmestoreyfm.bean;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import com.dmeyc.dmestoreyfm.bean.common.GoodsBean;
import com.dmeyc.dmestoreyfm.bean.common.PaginatorBean;
import com.dmeyc.dmestoreyfm.bean.common.ProductCategoryBean;

import java.util.List;

/**
 * Created by jockie on 2018/1/8
 * Email:jockie911@gmail.com
 */

public class ChooseClothBean {

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
        private List<GoodsBean> goods;
        private List<SceneListBean> sceneList;
        private List<ProductCategoryBean> productCategory;
        //更改数据
        private String images;
        private List<CategoryDataBean> productCategoryParent;

        public String getImages() {
            return images;
        }

        public List<CategoryDataBean> getProductCategoryParent() {
            return productCategoryParent;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public void setProductCategoryParent(List<CategoryDataBean> productCategoryParent) {
            this.productCategoryParent = productCategoryParent;
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

        public List<SceneListBean> getSceneList() {
            return sceneList;
        }

        public void setSceneList(List<SceneListBean> sceneList) {
            this.sceneList = sceneList;
        }

        public List<ProductCategoryBean> getProductCategory() {
            return productCategory;
        }

        public void setProductCategory(List<ProductCategoryBean> productCategory) {
            this.productCategory = productCategory;
        }

        public static class SceneListBean {

            private int id;
            private String image;
            private String productScene;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getProductScene() {
                return productScene;
            }

            public void setProductScene(String productScene) {
                this.productScene = productScene;
            }
        }
    }


    @SuppressLint("ParcelCreator")
    public class CategoryDataBean implements Parcelable  {
        private String categoryName;
        private int id;
        private List<CatgegoryChildrenDateBean> productCategoryChildren;

        public String getCategoryName() {
            return categoryName;
        }

        public int getId() {
            return id;
        }

        public List<CatgegoryChildrenDateBean> getProductCategoryChildren() {
            return productCategoryChildren;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public void setProductCategoryChildren(List<CatgegoryChildrenDateBean> productCategoryChildren) {
            this.productCategoryChildren = productCategoryChildren;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {

        }
    }

    public class CatgegoryChildrenDateBean {
        private int categoryId;
        private String categoryName;
        private int id;
        private String images;


        public int getCategoryId() {
            return categoryId;
        }

        public int getId() {
            return id;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public String getImages() {
            return images;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setImages(String images) {
            this.images = images;
        }
    }
}