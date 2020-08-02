package com.dmeyc.dmestoreyfm.present;

import android.content.Context;

import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.RecommendBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.tamic.novate.Throwable;

public class CategoryPresenter  extends BasePresenter {

    private CategoryPresenter.OnDataGetLisener sendListener;



    public void getDialogData(Context context, int categoryuid, int season,int endPrice,int startPrice,int sort,int gener, final CategoryPresenter.OnDataGetLisener lisener){
        this.sendListener = lisener;

        ParamMap.Build b = new ParamMap.Build();

        if(endPrice!=100000000){
                    b.addParams("gender", gener)
                    .addParams("season", season)
                    .addParams("startPrice", startPrice)
                    .addParams("endPrice", endPrice)
                    .addParams("sort", sort);
        }
        RestClient.getNovate(context).post(Constant.API.CHOOSE_DETAIL, b.addParams("categoryChildren", categoryuid)
                .build(), new DmeycBaseSubscriber<RecommendBean>() {
            @Override
            public void onSuccess(RecommendBean bean) {
                if(sendListener != null)
                    sendListener.onSuccess(bean);
            }
            @Override
            public void onError(Throwable e) {
                if(sendListener != null)
                    sendListener.onFailure(e.getMessage());
            }
        });
    }
    /**
     * 发送短信验证码
     */
    public interface OnDataGetLisener{
        void onSuccess(RecommendBean bean);
        void onFailure(String errMsg);
    }
}
