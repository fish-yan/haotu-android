package com.dmeyc.dmestoreyfm.newui.home.message.container;

import com.dmeyc.dmestoreyfm.api.CustomSubscriber;
import com.dmeyc.dmestoreyfm.api.RetrofitService;
import com.dmeyc.dmestoreyfm.api.exception.ApiException;
import com.dmeyc.dmestoreyfm.bean.response.UserNoticeBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.newbase.BasePresenter;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.video.index.IndexDynamicBean;
import com.dmeyc.dmestoreyfm.video.index.IndexDynamicItemBean;

import java.util.ArrayList;
import java.util.List;

/**
 * create by cxg on 2019/12/22
 */
public class MessageInfoPresenter extends BasePresenter<IMessageInfoView> {
    public void httpRequestData() {
        subscriber = new CustomSubscriber<UserNoticeBean>(mView, mGuid) {
            @Override
            public void onSuccess(UserNoticeBean bean) {
                mView.getDataListSucc(bean.getData());
            }
            @Override
            public void onError(ApiException ex) {
                super.onError(ex);
                mView.onCloseRefresh();
            }
        };
        RetrofitService.getInstance().noticeInfo(mView.getParams(), subscriber);
    }

    public void httpVideoInfo() {
        subscriber = new CustomSubscriber<IndexDynamicItemBean>(mView, mGuid) {
            @Override
            public void onSuccess(IndexDynamicItemBean bean) {
                List<IndexDynamicBean.DataBean> list = new ArrayList<>();
                IndexDynamicBean.DataBean dataX = bean.getData();
                if (dataX != null) {
                    dataX.setItemType(Constant.AdapterItemType.TYPE_HOME_VIDEO);
                    list.add(dataX);
                    mView.getVideoDetailSucc(list);
                } else {
                    ToastUtil.show("数据返回异常");
                }
            }
        };
        RetrofitService.getInstance().videoDetail(mView.getVideoDetailParams(), subscriber);
    }
}
