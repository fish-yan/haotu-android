package com.dmeyc.dmestoreyfm.newui.login;

import com.dmeyc.dmestoreyfm.api.CustomSubscriber;
import com.dmeyc.dmestoreyfm.api.RetrofitService;
import com.dmeyc.dmestoreyfm.api.exception.ApiException;
import com.dmeyc.dmestoreyfm.bean.YFMLoginBean;
import com.dmeyc.dmestoreyfm.bean.response.BaseRespBean;
import com.dmeyc.dmestoreyfm.newbase.BasePresenter;
import com.dmeyc.dmestoreyfm.newui.update.UpdateResultBean;

import java.util.Map;

/**
 * create by cxg on 2019/12/13
 */
public class LoginPresenter extends BasePresenter<ILoginView> {

    public void getCode(){
        subscriber = new CustomSubscriber<BaseRespBean>(mView,mGuid) {
            @Override
            public void onSuccess(BaseRespBean bean) {
                mView.getCodeSucc();
            }
        };

        RetrofitService.getInstance().getCode(mView.getCodeParams(),subscriber);
    }

    public void doLogin() {
        subscriber = new CustomSubscriber<YFMLoginBean>(mView,mGuid) {
            @Override
            public void onSuccess(YFMLoginBean bean) {
                mView.doLoginSucc(bean);
            }
        };

        RetrofitService.getInstance().login(mView.getParams(),subscriber);
    }

    public void checkBindOpenId(Map<String, String> params){
        subscriber = new CustomSubscriber<YFMLoginBean>(mView,mGuid) {
            @Override
            public void onSuccess(YFMLoginBean bean) {
                if ("0".equals(bean.getData().getIsBinding())){
                    mView.unBindThirdPart();
                }else {
                    mView.bindThirdPart(bean);
                }
            }
        };
        RetrofitService.getInstance().checkBindOpenId(params,subscriber);
    }

//    public void doThirdPartLogin(Map<String,String> params){
//        subscriber = new CustomSubscriber<YFMLoginBean>(mView,mGuid) {
//            @Override
//            public void onSuccess(YFMLoginBean bean) {
//                mView.doLoginSucc(bean);
//            }
//        };
//        RetrofitService.getInstance().thirdPartLogin(params,subscriber);
//    }

    public void updateVersion(Map<String, String> params){
        subscriber = new CustomSubscriber<UpdateResultBean>(mView,mGuid) {
            @Override
            public void onSuccess(UpdateResultBean bean) {
                mView.getUpdateVerSucc(bean);
            }

            @Override
            public void onError(ApiException ex) {
            }
        };
        RetrofitService.getInstance().updateVersion(params,subscriber);
    }

}
