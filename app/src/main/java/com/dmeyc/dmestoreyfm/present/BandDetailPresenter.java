package com.dmeyc.dmestoreyfm.present;

import android.content.Context;

import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.RecommendBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.tamic.novate.Throwable;

public class BandDetailPresenter extends BasePresenter {


    private OnDetailLisenter detailLisenter;

    public void getDetail(Context context, int id,  final OnDetailLisenter lisener){
        detailLisenter=lisener;

        RestClient.getNovate(context).post(Constant.API.STORE_DETIAL, new ParamMap.Build()
                .addParams("store", id)
                .build(), new DmeycBaseSubscriber<RecommendBean>() {
            @Override
            public void onSuccess(RecommendBean bean) {
                if(detailLisenter != null)
                    detailLisenter.onSuccess(bean);
            }
            @Override
            public void onError(Throwable e) {
                if(detailLisenter != null)
                    detailLisenter.onFailure(e.getMessage());
            }
        });

    }

    public interface OnDetailLisenter{

        void onSuccess(RecommendBean bean);

        void onFailure(String errMsg);
    }
}
