package com.dmeyc.dmestoreyfm.newui.release.video;

import com.dmeyc.dmestoreyfm.newbase.IBaseView;
import com.dmeyc.dmestoreyfm.video.videoupload.TXUGCPublishTypeDef;

import java.util.Map;

/**
 * create by cxg on 2019/11/30
 */
public interface IReleaseVideoView extends IBaseView {
    void upLoadSucc();

    Map<String,String> getParams();

    void publishDynamicSucc();

    TXUGCPublishTypeDef.TXPublishParam getTXPublishParam();
}
