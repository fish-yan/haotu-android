package com.dmeyc.dmestoreyfm.newui.pagerdetail.editinfo;

import android.text.TextUtils;

import com.dmeyc.dmestoreyfm.api.CustomSubscriber;
import com.dmeyc.dmestoreyfm.api.RetrofitService;
import com.dmeyc.dmestoreyfm.bean.response.BaseRespBean;
import com.dmeyc.dmestoreyfm.bean.response.UploadSingleImageBean;
import com.dmeyc.dmestoreyfm.newbase.BasePresenter;

import java.util.Map;

/**
 * create by cxg on 2019/12/21
 */
public class EditInfoPresenter extends BasePresenter<IEditInfoView> {
    private String imgUrl = "";

    public void httpRequestData() {
        subscriber = new CustomSubscriber<BaseRespBean>(mView, mGuid) {
            @Override
            public void onSuccess(BaseRespBean bean) {
                mView.httpRequestDataSucc();
            }
        };
        Map<String, String> params = mView.getParams();
        if (!TextUtils.isEmpty(imgUrl)) {
            params.put("userLogo", imgUrl);
        }
        RetrofitService.getInstance().editUserInfo(params, subscriber);
    }

    public void uploadImage(String filePath) {
        subscriber = new CustomSubscriber<UploadSingleImageBean>(mView, mGuid) {
            @Override
            public void onSuccess(UploadSingleImageBean bean) {
                imgUrl = bean.getData();
                httpRequestData();
            }
        };
        RetrofitService.getInstance().uploadImage(filePath, subscriber);
    }
    public String getImgUrl(){
        return imgUrl;
    }
}
