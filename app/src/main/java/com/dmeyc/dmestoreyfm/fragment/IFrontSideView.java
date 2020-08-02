package com.dmeyc.dmestoreyfm.fragment;

import android.content.Intent;

import com.dmeyc.dmestoreyfm.base.IBaseView;


/**
 * Created by yc on 2017/5/23.
 */

public interface IFrontSideView extends IBaseView {

    int getGender();

    boolean isFrontPic();

    void openAlbumChoosePic();

    void ndkResultSuccess(int gender);

    void ndkresultPicError(int code, String picAbsPath);

    void stopPreview();

    Intent setOldIntent(Intent intent);
}
