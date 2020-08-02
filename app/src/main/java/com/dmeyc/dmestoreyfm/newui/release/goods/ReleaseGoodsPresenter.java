package com.dmeyc.dmestoreyfm.newui.release.goods;

import com.dmeyc.dmestoreyfm.api.CustomSubscriber;
import com.dmeyc.dmestoreyfm.api.RetrofitService;
import com.dmeyc.dmestoreyfm.bean.response.BaseRespBean;
import com.dmeyc.dmestoreyfm.bean.response.UploadSingleImageBean;
import com.dmeyc.dmestoreyfm.newbase.BasePresenter;
import com.dmeyc.dmestoreyfm.newui.release.action.IReleaseActionView;

import java.util.Map;

/**
 * create by cxg on 2019/11/30
 */
public class ReleaseGoodsPresenter extends BasePresenter<IReleaseGoodsView> {
    private String imgUrl;
    private void httpRequestData() {
        subscriber = new CustomSubscriber<BaseRespBean>(mView,mGuid) {
            @Override
            public void onSuccess(BaseRespBean o) {
                mView.httpDataSucc();
            }
        };
        Map<String, String> params = mView.getParams();
        params.put("img", imgUrl);
        RetrofitService.getInstance().publish(params,subscriber);
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
}
