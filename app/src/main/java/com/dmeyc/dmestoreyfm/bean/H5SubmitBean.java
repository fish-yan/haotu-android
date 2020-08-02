package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class H5SubmitBean implements Serializable {

    private String content;
    private String url;

    public String getContent() {
        return content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setContent(String content) {
        this.content = content;
    }
    //    private Map<String,ArrayList<String>> content;
//    private Map<String,ArrayList<String>> url;
//    private Map<String,ArrayList<String>> h5ShareImageList;
//
//    public Map<String, ArrayList<String>> getH5ShareImageList() {
//        return h5ShareImageList;
//    }
//
//    public void setH5ShareImageList(Map<String, ArrayList<String>> h5ShareImageList) {
//        this.h5ShareImageList = h5ShareImageList;
//    }
//
//    public Map<String, ArrayList<String>> getContent() {
//        return content;
//    }
//
//    public Map<String, ArrayList<String>> getUrl() {
//        return url;
//    }
//
//    public void setContent(Map<String, ArrayList<String>> content) {
//        this.content = content;
//    }
//
//    public void setUrl(Map<String, ArrayList<String>> url) {
//        this.url = url;
//    }
    //    private List<String> content;
//    private List<String> url;
//
//    public List<String> getContent() {
//        return content;
//    }
//
//    public List<String> getUrl() {
//        return url;
//    }
//
//    public void setUrl(List<String> url) {
//        this.url = url;
//    }
//
//    public void setContent(List<String> content) {
//        this.content = content;
//    }
}
