package com.dmeyc.dmestoreyfm.photolive;

import com.dmeyc.dmestoreyfm.newbase.IBaseView;

/**
 * create by cxg on 2019/12/26
 */
public interface ILivingView extends IBaseView {
    String getActivityId();

    void beginUpload();

    void endUpload();

    void upLoadImageSucc();
}
