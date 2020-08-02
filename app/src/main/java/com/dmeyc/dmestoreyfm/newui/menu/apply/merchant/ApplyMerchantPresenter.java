package com.dmeyc.dmestoreyfm.newui.menu.apply.merchant;

import com.dmeyc.dmestoreyfm.api.CustomSubscriber;
import com.dmeyc.dmestoreyfm.api.RetrofitService;
import com.dmeyc.dmestoreyfm.bean.response.BaseRespBean;
import com.dmeyc.dmestoreyfm.bean.response.UploadSingleImageBean;
import com.dmeyc.dmestoreyfm.newbase.BasePresenter;

import java.util.Map;

/**
 * create by cxg on 2019/11/30
 */
public class ApplyMerchantPresenter extends BasePresenter<IApplyMerchantView> {
    private String imgUrl = "";

    private void httpRequestData() {
        subscriber = new CustomSubscriber<BaseRespBean>(mView, mGuid) {
            @Override
            public void onSuccess(BaseRespBean bean) {
                mView.httpRequestDataSucc();

            }
        };
        Map<String, String> params = mView.getParams();
        params.put("business_logo", imgUrl);
        RetrofitService.getInstance().businessApply(params, subscriber);
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
