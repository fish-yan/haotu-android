package com.dmeyc.dmestoreyfm.bean.common;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by jockie on 2018/1/8
 * Email:jockie911@gmail.com
 */

public class GoodsBean implements Parcelable {

    private int id;
    private String createDate;
    private String modifyDate;
    private int version;
    private String name;
    private String productInfo;
    private String caption;
    private int gender;
    private int type;
    private int price;
    private String unit;
    private int weight;
    private boolean isMarketable;
    private boolean isTop;
    private String introduction;
    private String productIntroduction;
    private boolean isDelivery;
    private String memo;
    private int sales;
    private String image;
    private String materialDetail;
    private String keyword;
    private int isCustom;  //开始时boolean 类型 后期修改的
    private String productScene;
//    private int productSeason;
   private String productSeason;
    private String productCategory;
    private boolean isCollection;
    private int brandDesignerId;
    private String brandDesigner;
    private int freight;
    private List<String> sizeList;

    public List<String> getSizeList() {
        return sizeList;
    }

    public void setSizeList(List<String> sizeList) {
        this.sizeList = sizeList;
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

    public String getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(String productInfo) {
        this.productInfo = productInfo;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean isIsMarketable() {
        return isMarketable;
    }

    public void setIsMarketable(boolean isMarketable) {
        this.isMarketable = isMarketable;
    }

    public boolean isIsTop() {
        return isTop;
    }

    public void setIsTop(boolean isTop) {
        this.isTop = isTop;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getProductIntroduction() {
        return productIntroduction;
    }

    public void setProductIntroduction(String productIntroduction) {
        this.productIntroduction = productIntroduction;
    }

    public boolean isIsDelivery() {
        return isDelivery;
    }

    public void setIsDelivery(boolean isDelivery) {
        this.isDelivery = isDelivery;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMaterialDetail() {
        return materialDetail;
    }

    public void setMaterialDetail(String materialDetail) {
        this.materialDetail = materialDetail;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int isIsCustom() {
        return isCustom;
    }

    public void setIsCustom(int isCustom) {
        this.isCustom = isCustom;
    }

    public String getProductScene() {
        return productScene;
    }

    public void setProductScene(String productScene) {
        this.productScene = productScene;
    }

    public String getProductSeason() {
        return productSeason;
    }

    public void setProductSeason(String productSeason) {
        this.productSeason = productSeason;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public boolean isIsCollection() {
        return isCollection;
    }

    public void setIsCollection(boolean isCollection) {
        this.isCollection = isCollection;
    }

    public int getBrandDesignerId() {
        return brandDesignerId;
    }

    public void setBrandDesignerId(int brandDesignerId) {
        this.brandDesignerId = brandDesignerId;
    }

    public String getBrandDesigner() {
        return brandDesigner;
    }

    public void setBrandDesigner(String brandDesigner) {
        this.brandDesigner = brandDesigner;
    }

    public int getFreight() {
        return freight;
    }

    public void setFreight(int freight) {
        this.freight = freight;
    }


    public GoodsBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.createDate);
        dest.writeString(this.modifyDate);
        dest.writeInt(this.version);
        dest.writeString(this.name);
        dest.writeString(this.productInfo);
        dest.writeString(this.caption);
        dest.writeInt(this.gender);
        dest.writeInt(this.type);
        dest.writeInt(this.price);
        dest.writeString(this.unit);
        dest.writeInt(this.weight);
        dest.writeByte(this.isMarketable ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isTop ? (byte) 1 : (byte) 0);
        dest.writeString(this.introduction);
        dest.writeString(this.productIntroduction);
        dest.writeByte(this.isDelivery ? (byte) 1 : (byte) 0);
        dest.writeString(this.memo);
        dest.writeInt(this.sales);
        dest.writeString(this.image);
        dest.writeString(this.materialDetail);
        dest.writeString(this.keyword);
        dest.writeInt(this.isCustom);
        dest.writeString(this.productScene);
        dest.writeString(this.productSeason);
        dest.writeString(this.productCategory);
        dest.writeByte(this.isCollection ? (byte) 1 : (byte) 0);
        dest.writeInt(this.brandDesignerId);
        dest.writeString(this.brandDesigner);
        dest.writeInt(this.freight);
    }

    protected GoodsBean(Parcel in) {
        this.id = in.readInt();
        this.createDate = in.readString();
        this.modifyDate = in.readString();
        this.version = in.readInt();
        this.name = in.readString();
        this.productInfo = in.readString();
        this.caption = in.readString();
        this.gender = in.readInt();
        this.type = in.readInt();
        this.price = in.readInt();
        this.unit = in.readString();
        this.weight = in.readInt();
        this.isMarketable = in.readByte() != 0;
        this.isTop = in.readByte() != 0;
        this.introduction = in.readString();
        this.productIntroduction = in.readString();
        this.isDelivery = in.readByte() != 0;
        this.memo = in.readString();
        this.sales = in.readInt();
        this.image = in.readString();
        this.materialDetail = in.readString();
        this.keyword = in.readString();
        this.isCustom = in.readInt();
        this.productScene = in.readString();
        this.productSeason = in.readString();
        this.productCategory = in.readString();
        this.isCollection = in.readByte() != 0;
        this.brandDesignerId = in.readInt();
        this.brandDesigner = in.readString();
        this.freight = in.readInt();
    }

    public static final Creator<GoodsBean> CREATOR = new Creator<GoodsBean>() {
        @Override
        public GoodsBean createFromParcel(Parcel source) {
            return new GoodsBean(source);
        }

        @Override
        public GoodsBean[] newArray(int size) {
            return new GoodsBean[size];
        }
    };
}
