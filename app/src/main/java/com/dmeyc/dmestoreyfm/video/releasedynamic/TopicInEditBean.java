package com.dmeyc.dmestoreyfm.video.releasedynamic;

import com.xsm.library.TObject;

public class TopicInEditBean extends TObject {

    public static final int TYPE_TOPIC = 1;
    public static final int TYPE_SYSTEM_FRIEND = 2;
    public static final int TYPE_CONTACT = 3;

    private int type = TYPE_TOPIC;
    private String name;
    private String phone;
    private int topicId;

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
