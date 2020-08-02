package com.dmeyc.dmestoreyfm.newui.release.action;

import com.dmeyc.dmestoreyfm.api.CustomSubscriber;
import com.dmeyc.dmestoreyfm.api.RetrofitService;
import com.dmeyc.dmestoreyfm.bean.ProjectTypeBean;
import com.dmeyc.dmestoreyfm.bean.response.BaseRespBean;
import com.dmeyc.dmestoreyfm.newbase.BasePresenter;

import java.util.HashMap;
import java.util.Map;

/**
 * create by cxg on 2019/11/30
 */
public class ReleaseActionPresenter extends BasePresenter<IReleaseActionView> {
    public void getActionType() {
        subscriber = new CustomSubscriber<ProjectTypeBean>(mView, mGuid) {
            @Override
            public void onSuccess(ProjectTypeBean bean) {
                if (bean.getData() != null) {
                    mView.getActionListSucc(bean.getData());
                }
            }
        };
        Map<String, String> params = new HashMap<>();
        params.put("key", "PROJECT_TYPE");
        RetrofitService.getInstance().getDicItem(params, subscriber);
    }


    public void httpRequestData() {
        subscriber = new CustomSubscriber<BaseRespBean>(mView, mGuid) {
            @Override
            public void onSuccess(BaseRespBean bean) {
                mView.httpRequestSucc();
            }
        };
        RetrofitService.getInstance().releaseActivity(mView.getParams(), subscriber);
    }
}
