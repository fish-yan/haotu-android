package com.dmeyc.dmestoreyfm.bean.common;

import java.util.List;

/**
 * Created by jockie on 2018/1/31
 * Email:jockie911@gmail.com
 */

public class ReviewsBean {

    /**
     * id : 6
     * createDate : 2017-12-06
     * modifyDate : 2017-12-14
     * version : 1
     * member : 1
     * content : 大衣非常好，口袋里秀了个喵喵简直不要太棒！买这个大衣我就把模特里那个毛衣也一起买了。毛衣也是相当好看。喇叭袖，虽然是个灰色但是真的是少女款。陪着这个大衣无比好看。我在你们家也是几年的老客户。真心喜欢这个牌子哟。
     * likeCount : 112
     * isLike : false
     * avatar :
     * nickname : 2O90568H0266Au49R7
     * reviewImages : [{"title":null,"source":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2909064781,1892834277&fm=27&gp=0.jpg","thumbnail":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2909064781,1892834277&fm=27&gp=0.jpg"},{"title":null,"source":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2909064781,1892834277&fm=27&gp=0.jpg","thumbnail":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2909064781,1892834277&fm=27&gp=0.jpg"},{"title":null,"source":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2909064781,1892834277&fm=27&gp=0.jpg","thumbnail":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2909064781,1892834277&fm=27&gp=0.jpg"}]
     */

    private int id;
    private String createDate;
    private String modifyDate;
    private int version;
    private int member;
    private String content;
    private int likeCount;
    private boolean isLike;
    private String avatar;
    private String nickname;
    private List<ReviewImagesBean> reviewImages;

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

    public int getMember() {
        return member;
    }

    public void setMember(int member) {
        this.member = member;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public boolean isIsLike() {
        return isLike;
    }

    public void setIsLike(boolean isLike) {
        this.isLike = isLike;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public List<ReviewImagesBean> getReviewImages() {
        return reviewImages;
    }

    public void setReviewImages(List<ReviewImagesBean> reviewImages) {
        this.reviewImages = reviewImages;
    }

    public static class ReviewImagesBean {
        /**
         * title : null
         * source : https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2909064781,1892834277&fm=27&gp=0.jpg
         * thumbnail : https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2909064781,1892834277&fm=27&gp=0.jpg
         */

        private Object title;
        private String source;
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

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }
    }
}
