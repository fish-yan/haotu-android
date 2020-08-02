package com.dmeyc.dmestoreyfm.newui.menu.club.notice;

import com.dmeyc.dmestoreyfm.api.CustomSubscriber;
import com.dmeyc.dmestoreyfm.api.RetrofitService;
import com.dmeyc.dmestoreyfm.bean.response.BaseRespBean;
import com.dmeyc.dmestoreyfm.newbase.BasePresenter;

/**
 * create by cxg on 2019/11/30
 */
public class NoticePresenter extends BasePresenter<INoticeView> {
    public void httpRequestData() {
        subscriber = new CustomSubscriber<BaseRespBean>(mView,mGuid) {
            @Override
            public void onSuccess(BaseRespBean bean) {
                mView.httpDataSucc();
            }
        };
        RetrofitService.getInstance().editGroupInfo(mView.getParams(),subscriber);
    }
}
