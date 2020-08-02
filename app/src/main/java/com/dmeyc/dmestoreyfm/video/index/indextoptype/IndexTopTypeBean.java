package com.dmeyc.dmestoreyfm.video.index.indextoptype;

import java.io.Serializable;

public class IndexTopTypeBean implements Serializable {
    private String typeName;
    private int imgAddress;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getImgAddress() {
        return imgAddress;
    }

    public void setImgAddress(int imgAddress) {
        this.imgAddress = imgAddress;
    }
}
