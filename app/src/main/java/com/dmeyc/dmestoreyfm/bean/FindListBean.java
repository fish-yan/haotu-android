package com.dmeyc.dmestoreyfm.bean;

import java.util.List;

public class FindListBean {

    /**
     * code : 200
     * msg : 操作成功
     * data : [{"id":1,"createDate":"2018-12-12 16:15:40","modifyDate":"2018-12-13 14:57:12","version":1,"name":"李冰","avatar":"http://storeapi.dmeyc.com:8080/images/me_ic_head.png","images":null,"imageList":[{"title":"","source":"https://storeapi.91moshow.com:8078/images/upload/source/1541490591.jpg","thumbnail":"https://storeapi.91moshow.com:8078/images/upload/thumbnail/1541490591.jpg"},{"title":"","source":"https://storeapi.91moshow.com:8078/images/upload/source/1541490600.jpg","thumbnail":"https://storeapi.91moshow.com:8078/images/upload/thumbnail/1541490600.jpg"},{"title":"","source":"https://storeapi.91moshow.com:8078/images/upload/source/1541490614.jpg","thumbnail":"https://storeapi.91moshow.com:8078/images/upload/thumbnail/1541490614.jpg"}],"introduction":"如果说，岁月是一首歌，那么我们便是歌者，纵使孤独，仍会固执高歌；如果说，岁月是一段旅程，那么我们便是行者，且行且梦，让生命丰盈。人生究竟有多长？流年该用怎样的深情去诠释？站在海的面前，感知的不仅仅是沧海一粟的渺小，更是浩浩汤汤的雄浑，与海静静对望，丰盈的是梦，安恬的是心......","likeCount":2,"reviewCount":0,"member":1,"goods":"59,60","isLike":false},{"id":2,"createDate":"2018-12-13 17:35:17","modifyDate":"2018-12-13 17:35:17","version":0,"name":"李冰","avatar":"http://storeapi.dmeyc.com:8080/images/me_ic_head.png","images":null,"imageList":[{"title":"","source":"https://storeapi.91moshow.com:8078/images/upload/source/1541490591.jpg","thumbnail":"https://storeapi.91moshow.com:8078/images/upload/thumbnail/1541490591.jpg"},{"title":"","source":"https://storeapi.91moshow.com:8078/images/upload/source/1541490600.jpg","thumbnail":"https://storeapi.91moshow.com:8078/images/upload/thumbnail/1541490600.jpg"},{"title":"","source":"https://storeapi.91moshow.com:8078/images/upload/source/1541490614.jpg","thumbnail":"https://storeapi.91moshow.com:8078/images/upload/thumbnail/1541490614.jpg"}],"introduction":"\"你的脸上云淡风轻，谁也不知道你的牙咬得有多紧。你走路带着风，谁也不知道你膝盖上仍有曾摔伤的淤青\"","likeCount":0,"reviewCount":0,"member":2,"goods":null,"isLike":false},{"id":3,"createDate":"2018-12-13 17:35:21","modifyDate":"2018-12-13 17:35:21","version":-2,"name":"李冰","avatar":"http://storeapi.dmeyc.com:8080/images/me_ic_head.png","images":null,"imageList":[{"title":"","source":"https://storeapi.91moshow.com:8078/images/upload/source/1541490591.jpg","thumbnail":"https://storeapi.91moshow.com:8078/images/upload/thumbnail/1541490591.jpg"},{"title":"","source":"https://storeapi.91moshow.com:8078/images/upload/source/1541490600.jpg","thumbnail":"https://storeapi.91moshow.com:8078/images/upload/thumbnail/1541490600.jpg"},{"title":"","source":"https://storeapi.91moshow.com:8078/images/upload/source/1541490614.jpg","thumbnail":"https://storeapi.91moshow.com:8078/images/upload/thumbnail/1541490614.jpg"}],"introduction":"\"你的脸上云淡风轻，谁也不知道你的牙咬得有多紧。你走路带着风，谁也不知道你膝盖上仍有曾摔伤的淤青\"","likeCount":0,"reviewCount":0,"member":3,"goods":null,"isLike":false},{"id":4,"createDate":"2018-12-13 17:35:22","modifyDate":"2018-12-13 17:35:22","version":1,"name":"李冰","avatar":"http://storeapi.dmeyc.com:8080/images/me_ic_head.png","images":null,"imageList":[{"title":"","source":"https://storeapi.91moshow.com:8078/images/upload/source/1541490591.jpg","thumbnail":"https://storeapi.91moshow.com:8078/images/upload/thumbnail/1541490591.jpg"},{"title":"","source":"https://storeapi.91moshow.com:8078/images/upload/source/1541490600.jpg","thumbnail":"https://storeapi.91moshow.com:8078/images/upload/thumbnail/1541490600.jpg"},{"title":"","source":"https://storeapi.91moshow.com:8078/images/upload/source/1541490614.jpg","thumbnail":"https://storeapi.91moshow.com:8078/images/upload/thumbnail/1541490614.jpg"}],"introduction":"\"你的脸上云淡风轻，谁也不知道你的牙咬得有多紧。你走路带着风，谁也不知道你膝盖上仍有曾摔伤的淤青\"","likeCount":0,"reviewCount":0,"member":4,"goods":null,"isLike":false},{"id":5,"createDate":"2018-12-13 17:35:24","modifyDate":"2018-12-13 17:35:24","version":-1,"name":"李冰","avatar":"http://storeapi.dmeyc.com:8080/images/me_ic_head.png","images":null,"imageList":[{"title":"","source":"https://storeapi.91moshow.com:8078/images/upload/source/1541490591.jpg","thumbnail":"https://storeapi.91moshow.com:8078/images/upload/thumbnail/1541490591.jpg"},{"title":"","source":"https://storeapi.91moshow.com:8078/images/upload/source/1541490600.jpg","thumbnail":"https://storeapi.91moshow.com:8078/images/upload/thumbnail/1541490600.jpg"},{"title":"","source":"https://storeapi.91moshow.com:8078/images/upload/source/1541490614.jpg","thumbnail":"https://storeapi.91moshow.com:8078/images/upload/thumbnail/1541490614.jpg"}],"introduction":"\"你的脸上云淡风轻，谁也不知道你的牙咬得有多紧。你走路带着风，谁也不知道你膝盖上仍有曾摔伤的淤青\"","likeCount":0,"reviewCount":0,"member":1,"goods":null,"isLike":false}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * createDate : 2018-12-12 16:15:40
         * modifyDate : 2018-12-13 14:57:12
         * version : 1
         * name : 李冰
         * avatar : http://storeapi.dmeyc.com:8080/images/me_ic_head.png
         * images : null
         * imageList : [{"title":"","source":"https://storeapi.91moshow.com:8078/images/upload/source/1541490591.jpg","thumbnail":"https://storeapi.91moshow.com:8078/images/upload/thumbnail/1541490591.jpg"},{"title":"","source":"https://storeapi.91moshow.com:8078/images/upload/source/1541490600.jpg","thumbnail":"https://storeapi.91moshow.com:8078/images/upload/thumbnail/1541490600.jpg"},{"title":"","source":"https://storeapi.91moshow.com:8078/images/upload/source/1541490614.jpg","thumbnail":"https://storeapi.91moshow.com:8078/images/upload/thumbnail/1541490614.jpg"}]
         * introduction : 如果说，岁月是一首歌，那么我们便是歌者，纵使孤独，仍会固执高歌；如果说，岁月是一段旅程，那么我们便是行者，且行且梦，让生命丰盈。人生究竟有多长？流年该用怎样的深情去诠释？站在海的面前，感知的不仅仅是沧海一粟的渺小，更是浩浩汤汤的雄浑，与海静静对望，丰盈的是梦，安恬的是心......
         * likeCount : 2
         * reviewCount : 0
         * member : 1
         * goods : 59,60
         * isLike : false
         */

        private int id;
        private String createDate;
        private String modifyDate;
        private int version;
        private String name;
        private String avatar;
        private Object images;
        private String introduction;
        private int likeCount;
        private int reviewCount;
        private int member;
        private String goods;
        private boolean isLike;
        private List<ImageListBean> imageList;

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public Object getImages() {
            return images;
        }

        public void setImages(Object images) {
            this.images = images;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public int getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(int likeCount) {
            this.likeCount = likeCount;
        }

        public int getReviewCount() {
            return reviewCount;
        }

        public void setReviewCount(int reviewCount) {
            this.reviewCount = reviewCount;
        }

        public int getMember() {
            return member;
        }

        public void setMember(int member) {
            this.member = member;
        }

        public String getGoods() {
            return goods;
        }

        public void setGoods(String goods) {
            this.goods = goods;
        }

        public boolean isIsLike() {
            return isLike;
        }

        public void setIsLike(boolean isLike) {
            this.isLike = isLike;
        }

        public List<ImageListBean> getImageList() {
            return imageList;
        }

        public void setImageList(List<ImageListBean> imageList) {
            this.imageList = imageList;
        }

        public static class ImageListBean {
            /**
             * title :
             * source : https://storeapi.91moshow.com:8078/images/upload/source/1541490591.jpg
             * thumbnail : https://storeapi.91moshow.com:8078/images/upload/thumbnail/1541490591.jpg
             */

            private String title;
            private String source;
            private String thumbnail;

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

            public String getThumbnail() {
                return thumbnail;
            }

            public void setThumbnail(String thumbnail) {
                this.thumbnail = thumbnail;
            }
        }
    }
}
