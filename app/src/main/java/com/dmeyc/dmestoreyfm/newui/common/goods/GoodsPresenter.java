package com.dmeyc.dmestoreyfm.newui.common.goods;

import com.dmeyc.dmestoreyfm.api.CustomSubscriber;
import com.dmeyc.dmestoreyfm.api.RetrofitService;
import com.dmeyc.dmestoreyfm.api.exception.ApiException;
import com.dmeyc.dmestoreyfm.bean.response.GoodsBean;
import com.dmeyc.dmestoreyfm.newbase.BasePresenter;

/**
 * create by cxg on 2019/12/9
 */
public class GoodsPresenter extends BasePresenter<IGoodsView> {
    public void httpRequestData(String mType) {
        subscriber = new CustomSubscriber<GoodsBean>(mView, mGuid) {
            @Override
            public void onSuccess(GoodsBean bean) {
                mView.getDataListSucc(bean.getData());
            }

            @Override
            public void onError(ApiException ex) {
                super.onError(ex);
                mView.onCloseRefresh();
            }
        };
        switch (mType) {
            case GoodsFragment.TYPE_GOODS:
            case GoodsFragment.TYPE_COURSE:
                RetrofitService.getInstance().listByPattern(mView.getParams(), subscriber);

                break;
            case GoodsFragment.TYPE_GOODS_MINE:
            case GoodsFragment.TYPE_COURSE_MINE:
            case GoodsFragment.TYPE_GOODS_HOME_MINE:
            case GoodsFragment.TYPE_COURSE_HOME_MINE:
                RetrofitService.getInstance().listByUserAndType(mView.getParams(), subscriber);
                break;
        }
    }
}
