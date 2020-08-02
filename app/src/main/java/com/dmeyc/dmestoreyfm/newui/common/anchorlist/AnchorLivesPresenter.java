package com.dmeyc.dmestoreyfm.newui.common.anchorlist;

import com.dmeyc.dmestoreyfm.api.CustomSubscriber;
import com.dmeyc.dmestoreyfm.api.RetrofitService;
import com.dmeyc.dmestoreyfm.api.exception.ApiException;
import com.dmeyc.dmestoreyfm.bean.response.AnchorLivesBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.newbase.BasePresenter;

import java.util.List;

/**
 * create by cxg on 2019/12/26
 */
public class AnchorLivesPresenter extends BasePresenter<IAnchorLivesView> {
    public void httpRequestData(final String mType) {
        subscriber = new CustomSubscriber<AnchorLivesBean>(mView, mGuid) {
            @Override
            public void onSuccess(AnchorLivesBean bean) {
                List<AnchorLivesBean.DataBean> data = bean.getData();
                if (data != null) {
                    for (int i = 0; i < data.size(); i++) {
                        switch (mType) {
                            case AnchorLivesFragment.TYPE_HOME:
                                data.get(i).setItemType(Constant.AdapterItemType.TYPE_LIVING_HOME);
                                break;
                            case AnchorLivesFragment.TYPE_LIST:
                                data.get(i).setItemType(Constant.AdapterItemType.TYPE_LIVING_RELEASE);
                                break;
                        }
                    }
                }

                mView.getDataListSucc(bean.getData());
            }

            @Override
            public void onError(ApiException ex) {
                super.onError(ex);
                mView.onCloseRefresh();
            }
        };
        RetrofitService.getInstance().broadcastList(mView.getParams(), subscriber);
    }
}
