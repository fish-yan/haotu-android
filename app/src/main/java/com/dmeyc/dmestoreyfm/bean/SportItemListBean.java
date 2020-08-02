package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;

public class SportItemListBean implements Serializable {

    int id;
    int item_count;

    public int getId() {
        return id;
    }

    public int getItem_count() {
        return item_count;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setItem_count(int item_count) {
        this.item_count = item_count;
    }
}
