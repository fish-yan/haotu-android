package com.dmeyc.dmestoreyfm.bean;

import com.dmeyc.dmestoreyfm.config.ReleaseConfig;

/**
 * create by cxg on 2019/11/30
 */
public class ReleaseItemBean {
    public ReleaseConfig.Type type;
    public String title;

    public ReleaseItemBean(ReleaseConfig.Type type, String title) {
        this.type = type;
        this.title = title;
    }
}
