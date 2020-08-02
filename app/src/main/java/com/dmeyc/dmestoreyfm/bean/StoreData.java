package com.dmeyc.dmestoreyfm.bean;

import java.util.List;

public class StoreData {
    private int attend;
    private String createDate;
    private int id;
    private String introduction;
    private boolean isAttend;
    private String logo;
    private String modifyDate;
    private String name;
    private int product;
    private List<BrandsData>  brands;

    public String getName() {
        return name;
    }
    public String getModifyDate() {
        return modifyDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public int getId() {
        return id;
    }

    public List<BrandsData> getBrands() {
        return brands;
    }

    public int getAttend() {
        return attend;
    }

    public int getProduct() {
        return product;
    }

    public String getIntroduction() {
        return introduction;
    }

    public String getLogo() {
        return logo;
    }

    public boolean isAttend() {
        return isAttend;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void setAttend(int attend) {
        this.attend = attend;
    }

    public void setAttend(boolean attend) {
        isAttend = attend;
    }

    public void setBrands(List<BrandsData> brands) {
        this.brands = brands;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setProduct(int product) {
        this.product = product;
    }

}
