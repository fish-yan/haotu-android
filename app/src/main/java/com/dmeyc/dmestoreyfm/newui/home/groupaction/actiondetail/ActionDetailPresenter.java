package com.dmeyc.dmestoreyfm.newui.home.groupaction.actiondetail;

import android.text.TextUtils;
import android.view.View;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.api.CustomSubscriber;
import com.dmeyc.dmestoreyfm.api.RetrofitService;
import com.dmeyc.dmestoreyfm.bean.response.ActivityDetailBean;
import com.dmeyc.dmestoreyfm.newbase.BasePresenter;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.video.systeminfo.ReadMsgModel;

import java.util.HashMap;
import java.util.Map;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

/**
 * create by cxg on 2019/12/1
 */
public class ActionDetailPresenter extends BasePresenter<IActionDetailView> {
    public static final String TYPE_ACTIVITY ="TYPE_ACTIVITY";
    public static final String TYPE_MATCH ="TYPE_MATCH";
    public void httpRequestData() {
        subscriber = new CustomSubscriber<ActivityDetailBean>(mView,mGuid) {
            @Override
            public void onSuccess(ActivityDetailBean bean) {
                mView.httpDataSucc(bean.getData());
            }
        };
        RetrofitService.getInstance().getActivityDetail(mView.getParams(),subscriber);
    }

    public void getShareUrl(String activityId,String type){
        subscriber = new CustomSubscriber<ReadMsgModel>(mView,mGuid) {
            @Override
            public void onSuccess(ReadMsgModel bean) {
                if (!TextUtils.isEmpty(bean.getData())){
                    mView.getShareUrlSucc(bean.getData());
                }
            }
        };

        Map<String,String> params = new HashMap<>();
        params.put("activityId",activityId);
        switch (type){
            case TYPE_ACTIVITY:
                RetrofitService.getInstance().getActivityDetailUrl(params,subscriber);
                break;
            case TYPE_MATCH:
                RetrofitService.getInstance().getActivityEventUrl(params,subscriber);
                break;
        }
    }
}
