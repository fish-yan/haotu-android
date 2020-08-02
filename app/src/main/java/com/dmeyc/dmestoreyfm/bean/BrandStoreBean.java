package com.dmeyc.dmestoreyfm.bean;

import java.util.List;

public class BrandStoreBean {

private int code;

private String msg;
private List<DataContent>  data;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public List<DataContent> getData() {
        return data;
    }

    public void setData(List<DataContent> data) {
        this.data = data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public class DataContent{
    private int id;
    private String title;
    private String introduction;
    private String image;
    private int store;
    private int version;


        public int getStore() {
            return store;
        }

        public int getVersion() {
            return version;
        }

        public String getImage() {
            return image;
        }

        public int getId() {
            return id;
        }

        public String getIntroduction() {
            return introduction;
        }

        public String getTitle() {
            return title;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public void setStore(int store) {
            this.store = store;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
