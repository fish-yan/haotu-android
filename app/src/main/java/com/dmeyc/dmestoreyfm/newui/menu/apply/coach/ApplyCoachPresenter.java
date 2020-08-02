package com.dmeyc.dmestoreyfm.newui.menu.apply.coach;

import android.text.TextUtils;

import com.dmeyc.dmestoreyfm.api.CustomSubscriber;
import com.dmeyc.dmestoreyfm.api.RetrofitService;
import com.dmeyc.dmestoreyfm.bean.response.BaseRespBean;
import com.dmeyc.dmestoreyfm.bean.response.UploadSingleImageBean;
import com.dmeyc.dmestoreyfm.newbase.BasePresenter;

import java.util.Iterator;
import java.util.Map;

/**
 * create by cxg on 2019/11/30
 */
public class ApplyCoachPresenter extends BasePresenter<IApplyCoachView> {

    private String mHeaderPhotoUrl;
    private String mqualificationUrl;

    public void uploadImages() {
        Map<String, String> imageParams = mView.getImageParams();

        Iterator<Map.Entry<String, String>> entry = imageParams.entrySet().iterator();

        while (entry.hasNext()) {
            final Map.Entry<String, String> next = entry.next();
            subscriber = new CustomSubscriber<UploadSingleImageBean>(mView, mGuid) {
                @Override
                public void onSuccess(UploadSingleImageBean bean) {
                    switch (next.getKey()) {
                        case ApplyCoachActivity.CHECK_PHONE:
                            mHeaderPhotoUrl = bean.getData();
                            break;
                        case ApplyCoachActivity.CHECK_QUALIFICATIONS:
                            mqualificationUrl = bean.getData();
                            break;
                        default:
                    }

                    if (!TextUtils.isEmpty(mHeaderPhotoUrl) && !TextUtils.isEmpty(mqualificationUrl)) {
                        httpRequestData();
                    }
                }
            };
            RetrofitService.getInstance().uploadImage(next.getValue(), subscriber);
        }
    }

    private void httpRequestData(){
        subscriber = new CustomSubscriber<BaseRespBean>(mView, mGuid) {
            @Override
            public void onSuccess(BaseRespBean bean) {
                mView.httpRequestDataSucc();

            }
        };
        Map<String, String> params = mView.getParams();
        params.put("qualification_certificate", mqualificationUrl);
        params.put("identity_certificate", mHeaderPhotoUrl);
        RetrofitService.getInstance().coachApply(params, subscriber);
    }
}
