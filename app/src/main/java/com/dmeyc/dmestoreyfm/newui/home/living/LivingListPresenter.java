package com.dmeyc.dmestoreyfm.newui.home.living;

import com.dmeyc.dmestoreyfm.api.CustomSubscriber;
import com.dmeyc.dmestoreyfm.api.RetrofitService;
import com.dmeyc.dmestoreyfm.api.exception.ApiException;
import com.dmeyc.dmestoreyfm.bean.response.BaseRespBean;
import com.dmeyc.dmestoreyfm.newbase.BasePresenter;

import java.util.HashMap;
import java.util.Map;

/**
 * create by cxg on 2019/12/29
 */
public class LivingListPresenter extends BasePresenter<ILivingListView> {
    public void httpDeleteImage(final int position,String id){
        subscriber = new CustomSubscriber<BaseRespBean>(mView,mGuid) {
            @Override
            public void onSuccess(BaseRespBean o) {
                mView.deleteImageSucc(position);
            }

            @Override
            public void onError(ApiException ex) {
                super.onError(ex);
                mView.onCloseRefresh();
            }
        };
        Map<String,String> params = new HashMap<>();
        params.put("id",id);
        RetrofitService.getInstance().deleteBroadcast(params,subscriber);
    }
}
