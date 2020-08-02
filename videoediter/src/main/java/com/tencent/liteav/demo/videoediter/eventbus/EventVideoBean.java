package com.tencent.liteav.demo.videoediter.eventbus;

import java.io.Serializable;

public class EventVideoBean implements Serializable {

    private String key;
    private String videopath;
    private String imgpath;
    private String topic;
    private int topicId;

    // 关联商户相关
    private int assobusinessId;
    private String name;
    private String distance;
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getAssobusinessId() {
        return assobusinessId;
    }

    public void setAssobusinessId(int assobusinessId) {
        this.assobusinessId = assobusinessId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getVideopath() {
        return videopath;
    }

    public void setVideopath(String videopath) {
        this.videopath = videopath;
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }
}
