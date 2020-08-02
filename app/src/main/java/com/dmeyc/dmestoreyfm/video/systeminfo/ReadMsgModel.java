package com.dmeyc.dmestoreyfm.video.systeminfo;

import com.dmeyc.dmestoreyfm.bean.response.BaseRespBean;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ReadMsgModel extends BaseRespBean {


    /**
     * msg : 操作成功
     * code : 200
     * data :
     */

    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
