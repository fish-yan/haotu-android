package com.dmeyc.dmestoreyfm.bean;

import java.util.List;

public class GodsBean {

    private String brandDesigner;
    private String brandDesignerId;
    private String createDate;
    private int id;
    private String image;
    private int isCustom;
    private String modifyDate;
    private String name;
    private int price;
    private String productCategory;
    private int sales;
    private List<SizeListBean> sizeList;
    private int version;


    public int getId() {
        return id;
    }

    public int getIsCustom() {
        return isCustom;
    }

    public int getPrice() {
        return price;
    }

    public int getSales() {
        return sales;
    }

    public int getVersion() {
        return version;
    }

    public List<SizeListBean> getSizeList() {
        return sizeList;
    }

    public String getBrandDesigner() {
        return brandDesigner;
    }

    public String getBrandDesignerId() {
        return brandDesignerId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getImage() {
        return image;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public String getName() {
        return name;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setBrandDesigner(String brandDesigner) {
        this.brandDesigner = brandDesigner;
    }

    public void setBrandDesignerId(String brandDesignerId) {
        this.brandDesignerId = brandDesignerId;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setIsCustom(int isCustom) {
        this.isCustom = isCustom;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public void setSizeList(List<SizeListBean> sizeList) {
        this.sizeList = sizeList;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
