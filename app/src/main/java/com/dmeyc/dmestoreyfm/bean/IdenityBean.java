package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;

public class IdenityBean implements Serializable {

    /**
     * indentity : 1
     * nick_name : llife
     * sex : 2
     * url : http://thirdwx.qlogo.cn/mmopen/vi_32/Bqc9fL9libiauhjf3o7nTvhakYs9ibLb0yTvrIg3gvocHoryt7jgD8FmZW4l9sLjqHs7ZMs1lKZOmBCm3ITJokicKQ/132
     * user_id : 2
     */

    private String indentity;
    private String nick_name;
    private String sex;
    private String url;
    private int user_id;

    public String getIndentity() {
        return indentity;
    }

    public void setIndentity(String indentity) {
        this.indentity = indentity;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
