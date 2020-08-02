package com.dmeyc.dmestoreyfm.bean;

import com.dmeyc.dmestoreyfm.bean.common.BrandDesignerBean;
import com.dmeyc.dmestoreyfm.bean.common.GoodsBean;
import com.dmeyc.dmestoreyfm.bean.common.PaginatorBean;
import com.dmeyc.dmestoreyfm.bean.common.ReviewsBean;

import java.util.List;

/**
 * Created by jockie on 2018/1/15
 * Email:jockie911@gmail.com
 */

public class GoodDetailBean {

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
         * productImageListTop : [{"title":null,"source":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2909064781,1892834277&fm=27&gp=0.jpg","large":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2909064781,1892834277&fm=27&gp=0.jpg","thumbnail":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2909064781,1892834277&fm=27&gp=0.jpg"},{"title":null,"source":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2909064781,1892834277&fm=27&gp=0.jpg","large":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2909064781,1892834277&fm=27&gp=0.jpg","thumbnail":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2909064781,1892834277&fm=27&gp=0.jpg"},{"title":null,"source":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2909064781,1892834277&fm=27&gp=0.jpg","large":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2909064781,1892834277&fm=27&gp=0.jpg","thumbnail":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2909064781,1892834277&fm=27&gp=0.jpg"}]
         * productImageListBottom : [{"title":null,"source":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2909064781,1892834277&fm=27&gp=0.jpg"},{"title":null,"source":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2909064781,1892834277&fm=27&gp=0.jpg"},{"title":null,"source":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2909064781,1892834277&fm=27&gp=0.jpg"},{"title":null,"source":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2909064781,1892834277&fm=27&gp=0.jpg"},{"title":null,"source":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2909064781,1892834277&fm=27&gp=0.jpg"}]
         * brandDesigner : {"id":1,"createDate":"2017-12-05","modifyDate":"2017-12-07","version":1,"type":1,"name":"AMH","heading":"http://img13.360buyimg.com/n2/jfs/t12865/45/1265159264/257203/36c2cce3/5a1d1933Nc09f5e08.jpg","isAttend":false}
         * reviews : []
         * recommendMatch : [{"id":25,"createDate":"2017-12-05","modifyDate":"2017-12-06","version":1,"name":"SDSS","caption":"菲菲皮衣","price":555,"sales":1578,"image":"http://storeapi.dmeyc.com:8080/images/TB2mztihmB0XKJjSZFsXXaxfpXa_!!2228361831.jpg","isCustom":true,"productCategory":36,"brandDesignerId":6,"brandDesigner":"ll","isCollection":null},{"id":18,"createDate":"2017-12-21","modifyDate":"2017-12-22","version":1,"name":"KKKv","caption":"大皮衣","price":5621,"sales":882,"image":"http://storeapi.dmeyc.com:8080/images/TB2NWKThInI8KJjSspeXXcwIpXa_!!3490994700.jpg","isCustom":true,"productCategory":30,"brandDesignerId":2,"brandDesigner":"啊","isCollection":null},{"id":16,"createDate":"2017-12-06","modifyDate":"2017-12-10","version":1,"name":"HIGFB3z","caption":"花裙子","price":1666,"sales":592,"image":"http://storeapi.dmeyc.com:8080/images/TB2gv_wbQfb_uJkSndVXXaBkpXa_!!2228361831.jpg","isCustom":true,"productCategory":24,"brandDesignerId":3,"brandDesigner":"KJH","isCollection":null},{"id":22,"createDate":"2017-12-06","modifyDate":"2017-12-10","version":1,"name":"HIGFB4","caption":"花裙子","price":1666,"sales":579,"image":"http://storeapi.dmeyc.com:8080/images/TB2gv_wbQfb_uJkSndVXXaBkpXa_!!2228361831.jpg","isCustom":false,"productCategory":33,"brandDesignerId":1,"brandDesigner":"AMH","isCollection":null},{"id":9,"createDate":"2017-12-06","modifyDate":"2017-12-10","version":1,"name":"HIGFB2j","caption":"花裙子","price":1666,"sales":564,"image":"http://www.icicle.com/assets/imgsupl/RG6.0_0.63890300_1505987349_21aa_news_images_image_2-3.jpg","isCustom":true,"productCategory":14,"brandDesignerId":2,"brandDesigner":"王五","isCollection":null},{"id":3,"createDate":"2017-12-06","modifyDate":"2017-12-10","version":1,"name":"HIGFBd","caption":"花裙子","price":1666,"sales":562,"image":"http://www.icicle.com/assets/imgsupl/RG6.0_0.63890300_1505987349_21aa_news_images_image_2-3.jpg","isCustom":false,"productCategory":4,"brandDesignerId":2,"brandDesigner":"aa","isCollection":null}]
         * goods : {"id":1,"createDate":"2017-12-09","modifyDate":"2017-12-09","version":1,"name":"LILY","productInfo":"下摆开叉大大喇叭裤1","caption":"小短裙","gender":2,"type":1,"price":286,"unit":"克","weight":200,"isMarketable":true,"isTop":false,"introduction":"裙，裙子，是一种围于下体的服装，略呈环状，为下装的两种基本形式（另一种为裤装）之一，广义上包括连衣裙、衬裙、腰裙。古谓下裳，古时男女同用，今以 女性穿着居多。","productIntroduction":"美国最新预测的\u201c改变未来的十大科技\u201d中，\u201c个性定制\u201d被排在首位，这个判断是来自于市场的变化趋势。两个因素导致消费者的产品需求出现差异，一是消费者分化，二是消费者收入水平和价值判断出现差别。迎合消费者的商家正在个性化上倾注心思满足消费者需求。","isDelivery":true,"memo":"赶紧买","sales":16,"image":"http://www.icicle.com/assets/imgsupl/RG6.0_0.89707900_1505876258_1d5a_news_images_image_1-2.jpg","materialDetail":"丝绒,棉,蚕丝","keyword":"短裙","isCustom":true,"productScene":1,"productSeason":2,"productCategory":"连衣裙","isCollection":false,"brandDesignerId":1,"brandDesigner":"AMH","freight":10,"size":[{"id":1,"size":"M","price":288,"availableStock":88},{"id":2,"size":"L","price":288,"availableStock":105},{"id":3,"size":"XL","price":688,"availableStock":102}],"material":[{"id":1,"material":"黒麻料","price":288,"availableStock":88},{"id":2,"material":"绿麻料","price":688,"availableStock":102},{"id":3,"material":"棉","price":588,"availableStock":104}]}
         * paginator : {"limit":10,"page":1,"totalCount":0,"offset":0,"totalPages":0,"endRow":0,"startRow":0,"hasNextPage":false,"firstPage":true,"slider":[],"prePage":1,"hasPrePage":false,"lastPage":true,"nextPage":1}
         */

        private BrandDesignerBean brandDesigner;
        private GoodsBean goods;
        private PaginatorBean paginator;
        private List<ProductImageListTopBean> productImageListTop;
        private List<ProductImageListBottomBean> productImageListBottom;
        private List<ReviewsBean> reviews;
        private List<GoodsBean> recommendMatch;
        private StoreData store;

        public StoreData getStore() {
            return store;
        }

        public void setStore(StoreData store) {
            this.store = store;
        }

        public BrandDesignerBean getBrandDesigner() {
            return brandDesigner;
        }

        public void setBrandDesigner(BrandDesignerBean brandDesigner) {
            this.brandDesigner = brandDesigner;
        }

        public GoodsBean getGoods() {
            return goods;
        }

        public void setGoods(GoodsBean goods) {
            this.goods = goods;
        }

        public PaginatorBean getPaginator() {
            return paginator;
        }

        public void setPaginator(PaginatorBean paginator) {
            this.paginator = paginator;
        }

        public List<ProductImageListTopBean> getProductImageListTop() {
            return productImageListTop;
        }

        public void setProductImageListTop(List<ProductImageListTopBean> productImageListTop) {
            this.productImageListTop = productImageListTop;
        }

        public List<ProductImageListBottomBean> getProductImageListBottom() {
            return productImageListBottom;
        }

        public void setProductImageListBottom(List<ProductImageListBottomBean> productImageListBottom) {
            this.productImageListBottom = productImageListBottom;
        }

        public List<ReviewsBean> getReviews() {
            return reviews;
        }

        public void setReviews(List<ReviewsBean> reviews) {
            this.reviews = reviews;
        }

        public List<GoodsBean> getRecommendMatch() {
            return recommendMatch;
        }

        public void setRecommendMatch(List<GoodsBean> recommendMatch) {
            this.recommendMatch = recommendMatch;
        }


        public static class ProductImageListTopBean {
            /**
             * title : null
             * source : https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2909064781,1892834277&fm=27&gp=0.jpg
             * large : https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2909064781,1892834277&fm=27&gp=0.jpg
             * thumbnail : https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2909064781,1892834277&fm=27&gp=0.jpg
             */

            private Object title;
            private String source;
            private String large;
            private String thumbnail;

            public Object getTitle() {
                return title;
            }

            public void setTitle(Object title) {
                this.title = title;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

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

        public static class ProductImageListBottomBean {
            /**
             * title : null
             * source : https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2909064781,1892834277&fm=27&gp=0.jpg
             */

            private String title;
            private String source;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }
        }

    }
}
