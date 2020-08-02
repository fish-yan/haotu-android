package com.dmeyc.dmestoreyfm.newui.menu.setting.about.report;

import com.dmeyc.dmestoreyfm.api.CustomSubscriber;
import com.dmeyc.dmestoreyfm.api.RetrofitService;
import com.dmeyc.dmestoreyfm.bean.response.BaseRespBean;
import com.dmeyc.dmestoreyfm.newbase.BasePresenter;

/**
 * create by cxg on 2019/11/24
 */
public class ReportPresenter extends BasePresenter<IReportView> {
    public void report() {
        subscriber = new CustomSubscriber<BaseRespBean>(mView,mGuid) {
            @Override
            public void onSuccess(BaseRespBean bean) {
                mView.httpDataSuc();
            }
        };

        RetrofitService.getInstance().report(mView.getParams(),subscriber);
    }
}
