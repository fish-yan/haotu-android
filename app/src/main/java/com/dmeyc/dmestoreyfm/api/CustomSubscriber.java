package com.dmeyc.dmestoreyfm.api;

import android.content.Intent;
import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.dmeyc.dmestoreyfm.api.exception.ApiException;
import com.dmeyc.dmestoreyfm.api.exception.ErrorSubscriber;
import com.dmeyc.dmestoreyfm.base.BaseApp;
import com.dmeyc.dmestoreyfm.bean.response.BaseRespBean;
import com.dmeyc.dmestoreyfm.newbase.IBaseView;
import com.dmeyc.dmestoreyfm.newui.login.LoginActivity;
import com.dmeyc.dmestoreyfm.ui.YFMLoginActivity;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.orhanobut.logger.Logger;

public abstract class CustomSubscriber<T> extends ErrorSubscriber<T> {

    private IBaseView iBaseView;
    /**
     * 是否需要显示全屏网络加载动画
     */
    public static boolean sShowProgressDialog = true;

    public CustomSubscriber(IBaseView iBaseView, String guid) {
        this.iBaseView = iBaseView;
        SubscriberManager.getInstance().add(guid, this);

    }

    @Override
    public void onStart() {
        if (sShowProgressDialog) {
            if (iBaseView != null) {
                iBaseView.showProgress();
            }
        }
    }

    @Override
    public void onCompleted() {
        sShowProgressDialog = true;
        if (iBaseView != null) {
            iBaseView.hideProgress();
        }
    }

    @Override
    public void onMyError(ApiException ex) {
        sShowProgressDialog = true;
        if (iBaseView != null) {
            iBaseView.hideProgress();
        }
        onError(ex);
    }


    @Override
    public void onNext(T t) {
        String json = JSONObject.toJSONString(t, filter);
        Logger.json(json);

        if (t instanceof BaseRespBean) {
            int code = ((BaseRespBean) t).getCode();
            switch (code) {
                case 200:
                    onSuccess(t);
                    break;
                case 0:
                case 400:
                case 401:
                    ApiException ex = new ApiException(null, code);
                    ex.message = ((BaseRespBean) t).getMsg();
                    onError(ex);
                    break;
                case 300:
                    String msg = ((BaseRespBean) t).getMsg();
                    if (!TextUtils.isEmpty(msg)) {
                        // TODO: 2019/11/24 清空历史activity
                        Intent intent = new Intent(BaseApp.getContext(), LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        BaseApp.getContext().startActivity(intent);
                        ApiException ex1 = new ApiException(null, code);
                        ex1.message = msg;
                        onError(ex1);
                    }

                    break;
                default:
                    break;
            }
        }

    }

    public abstract void onSuccess(T t);

    public void onError(ApiException ex) {
        if (ex.message != null) {
            ToastUtil.show(ex.message);
        }
    }

    private ValueFilter filter = new ValueFilter() {
        @Override
        public Object process(Object obj, String s, Object v) {
            if (v == null) {
                return "";
            } else {
                return v;
            }
        }
    };

}