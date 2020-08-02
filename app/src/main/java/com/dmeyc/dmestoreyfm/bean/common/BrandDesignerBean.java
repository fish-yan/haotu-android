package com.dmeyc.dmestoreyfm.bean.common;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jockie on 2018/1/5
 * Email:jockie911@gmail.com
 */

public class BrandDesignerBean implements Parcelable {

    /**
     * id : 1
     * createDate : 2017-12-12
     * modifyDate : 2017-12-16
     * version : null
     * name : lisa
     * heading : null
     * image : http://www.icicle.com/assets/imgsupl/RG6.0_0.89707900_1505876258_1d5a_news_images_image_1-2.jpg
     * imageDetail : null
     * recentImage : null
     * introduction : null
     * designserTop : MashamaXLofi秋冬lookbook
     * designserCentre : 明星最爱的设计师
     * designserBottom : Fall/winter2017
     * isSelection : null
     * isAttend : null
     */

    private int id;
    private int type;
    private String createDate;
    private String modifyDate;
    private String name;
    private String heading;
    private String image;
    private String imageDetail;
    private String recentImage;
    private String introduction;
    private String designserTop;
    private String designserCentre;
    private String designserBottom;
    private boolean isAttend;
    private  int isSelection;
    private String logo;
    private int store;

    public int getIsSelection() {
        return isSelection;
    }

    public int getStore() {
        return store;
    }

    public String getLogo() {
        return logo;
    }

    public void setAttend(boolean attend) {
        isAttend = attend;
    }

    public void setIsSelection(int isSelection) {
        this.isSelection = isSelection;
    }

    public void setStore(int store) {
        this.store = store;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageDetail() {
        return imageDetail;
    }

    public void setImageDetail(String imageDetail) {
        this.imageDetail = imageDetail;
    }

    public String getRecentImage() {
        return recentImage;
    }

    public void setRecentImage(String recentImage) {
        this.recentImage = recentImage;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getDesignserTop() {
        return designserTop;
    }

    public void setDesignserTop(String designserTop) {
        this.designserTop = designserTop;
    }

    public String getDesignserCentre() {
        return designserCentre;
    }

    public void setDesignserCentre(String designserCentre) {
        this.designserCentre = designserCentre;
    }

    public String getDesignserBottom() {
        return designserBottom;
    }

    public void setDesignserBottom(String designserBottom) {
        this.designserBottom = designserBottom;
    }

    public boolean getIsAttend() {
        return isAttend;
    }

    public void setIsAttend(boolean isAttend) {
        this.isAttend = isAttend;
    }

    public BrandDesignerBean() {
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.type);
        dest.writeString(this.createDate);
        dest.writeString(this.modifyDate);
        dest.writeString(this.name);
        dest.writeString(this.heading);
        dest.writeString(this.image);
        dest.writeString(this.imageDetail);
        dest.writeString(this.recentImage);
        dest.writeString(this.introduction);
        dest.writeString(this.designserTop);
        dest.writeString(this.designserCentre);
        dest.writeString(this.designserBottom);
        dest.writeInt(this.store);
        dest.writeString(this.logo);
        dest.writeInt(this.isSelection);
        dest.writeByte(this.isAttend ? (byte) 1 : (byte) 0);
    }

    protected BrandDesignerBean(Parcel in) {
        this.id = in.readInt();
        this.type = in.readInt();
        this.createDate = in.readString();
        this.modifyDate = in.readString();
        this.name = in.readString();
        this.heading = in.readString();
        this.image = in.readString();
        this.imageDetail = in.readString();
        this.recentImage = in.readString();
        this.introduction = in.readString();
        this.designserTop = in.readString();
        this.designserCentre = in.readString();
        this.designserBottom = in.readString();
        this.isAttend = in.readByte() != 0;
    }

    public static final Creator<BrandDesignerBean> CREATOR = new Creator<BrandDesignerBean>() {
        @Override
        public BrandDesignerBean createFromParcel(Parcel source) {
            return new BrandDesignerBean(source);
        }

        @Override
        public BrandDesignerBean[] newArray(int size) {
            return new BrandDesignerBean[size];
        }
    };
}
