package com.dmeyc.dmestoreyfm.bean;

public class BrandsData {

     private String createDate;
     private int id;
     private String image;
     private String introduction;




     private int isSelection;
     private String logo;
     private String modifyDate;
     private String name;
      private int store;
      private int version;

    public String getLogo() {
        return logo;
    }

    public String getIntroduction() {
        return introduction;
    }

    public int getId() {
        return id;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public int getVersion() {
        return version;
    }

    public int getIsSelection() {
        return isSelection;
    }

    public int getStore() {
        return store;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStore(int store) {
        this.store = store;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setIsSelection(int isSelection) {
        this.isSelection = isSelection;
    }
}
