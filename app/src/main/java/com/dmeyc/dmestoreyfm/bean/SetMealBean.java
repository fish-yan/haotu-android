package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;

public class SetMealBean implements Serializable {


    private String defaultChose;
    private String image;
    private String introduction;
    private  String name;

    public String getName() {
        return name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public String getImage() {
        return image;
    }

    public String getDefaultChose() {
        return defaultChose;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDefaultChose(String defaultChose) {
        this.defaultChose = defaultChose;
    }
}
