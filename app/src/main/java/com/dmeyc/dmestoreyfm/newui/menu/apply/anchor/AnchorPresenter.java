package com.dmeyc.dmestoreyfm.newui.menu.apply.anchor;

import android.text.TextUtils;

import com.dmeyc.dmestoreyfm.api.CustomSubscriber;
import com.dmeyc.dmestoreyfm.api.RetrofitService;
import com.dmeyc.dmestoreyfm.bean.response.BaseRespBean;
import com.dmeyc.dmestoreyfm.bean.response.UploadSingleImageBean;
import com.dmeyc.dmestoreyfm.newbase.BasePresenter;
import com.dmeyc.dmestoreyfm.newui.menu.apply.coach.ApplyCoachActivity;

import java.util.Iterator;
import java.util.Map;

/**
 * create by cxg on 2019/11/30
 */
public class AnchorPresenter extends BasePresenter<IAnchorView> {
    private String mFrontPath;
    private String mBackPath;
    public void uploadImages() {
        Map<String, String> imageParams = mView.getImageParams();

        Iterator<Map.Entry<String, String>> entry = imageParams.entrySet().iterator();

        while (entry.hasNext()) {
            final Map.Entry<String, String> next = entry.next();
            subscriber = new CustomSubscriber<UploadSingleImageBean>(mView, mGuid) {
                @Override
                public void onSuccess(UploadSingleImageBean bean) {
                    switch (next.getKey()) {
                        case ApplyAnchorActivity.IDCARD_FRONT:
                            mFrontPath = bean.getData();
                            break;
                        case ApplyAnchorActivity.IDCARD_BACK:
                            mBackPath = bean.getData();
                            break;
                        default:
                    }

                    if (!TextUtils.isEmpty(mFrontPath) && !TextUtils.isEmpty(mBackPath)) {
                        httpRequestData();
                    }
                }
            };
            RetrofitService.getInstance().uploadImage(next.getValue(), subscriber);
        }
    }
    private void httpRequestData() {
        subscriber = new CustomSubscriber<BaseRespBean>(mView,mGuid) {
            @Override
            public void onSuccess(BaseRespBean o) {
                mView.httpRequestSucc();
            }
        };
        Map<String, String> params = mView.getParams();
        params.put("indentity_front",mFrontPath);
        params.put("indentity_background",mBackPath);
        RetrofitService.getInstance().archorApply(params,subscriber);
    }

}
