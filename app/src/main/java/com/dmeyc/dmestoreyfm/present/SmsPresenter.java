package com.dmeyc.dmestoreyfm.present;

import android.content.Context;
import android.text.TextUtils;

import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.common.CommonBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.tamic.novate.Throwable;

/**
 * Created by jockie on 2018/3/20
 * Email:jockie911@gmail.com
 */

public class SmsPresenter extends BasePresenter{

    /**短信模板类型*/
    //注册
    public static final int SMS_REGISTER_CODE=1;
    //登录
    public static final int SMS_LOGIN_CODE=2;
    //登录异常验证码
    public static final int SMS_LOGIN_EXPECTION_CODE=3;
    //更改密码验证码
    public static final int SMS_UPDATE_PASSWORD_CODE=4;
    //变更个人信息
    public static final int SMS_IMFORTIOM_UPDATE_CODE=5;
    //用户身份验证码
    public static final int SMS_IDENTITY_CODE=6;


    public final static int SMSCODE_LENGTH = 6; //手机验证码的长度
    private OnSmsSendLisener sendListener;
    private OnSmsCheckListener checkListener;

    /**
     * 发送手机验证
     * @param context
     * @param phoneNum
     * @param verifyType 短信模板type
     */
    public void sendSmsCode(Context context, String phoneNum, int verifyType, final OnSmsSendLisener lisener){
        this.sendListener = lisener;
        if(checkPhoneNum(phoneNum)){
            RestClient.getNovate(context).post(Constant.API.SMS_SEND, new ParamMap.Build()
                    .addParams("phone", phoneNum)
                    .addParams("storeOrMeasure", 1)
                    .addParams("verifyType", verifyType).build(), new DmeycBaseSubscriber<CommonBean>() {
                @Override
                public void onSuccess(CommonBean bean) {
                    if(lisener != null)
                        lisener.onSuccess();
                }

                @Override
                public void onError(Throwable e) {
                    if(lisener != null)
                        lisener.onFailure(e.getMessage());
                }
            });
        }
    }

    /**
     * 手机号正则校验
     * @param phoneNum
     * @return
     */
    private boolean checkPhoneNum(String phoneNum){
        if(TextUtils.isEmpty(phoneNum) || phoneNum.length() < 11){
            if(sendListener != null)
                sendListener.onFailure("请出入正确的手机号码");
            return false;
        }
        return true;
    }

    /**
     * 验证验证码
     * @param phoneNum
     * @param smsCode
     * @param listener
     */
    public void checkSmsCode(Context context, String phoneNum, String smsCode, int verifyType, final OnSmsCheckListener listener){
        this.checkListener = listener;
        if(checkPhoneNum(phoneNum) && checkSmsCode(smsCode)){
            RestClient.getNovate(context).post(Constant.API.SMS_CHECK, new ParamMap.Build()
                    .addParams("phone", phoneNum)
                    .addParams("verifyType", verifyType)
                    .addParams("verifyCode", smsCode).build(), new DmeycBaseSubscriber<CommonBean>() {
                @Override
                public void onSuccess(CommonBean bean) {
                    if(listener != null)
                        listener.onSuccess();
                }

                @Override
                public void onError(Throwable e) {
                    if(listener != null)
                        listener.onFailure(e.getMessage());
                }
            });
        }
    }

    /**
     * 验证码的简单校验
     * @param smsCode
     * @return
     */
    private boolean checkSmsCode(String smsCode){
        if(TextUtils.isEmpty(smsCode) || smsCode.length() < SMSCODE_LENGTH){
            // TODO 长度不够
            if(checkListener != null)
                checkListener.onFailure("请输入正确的验证码");
            return false;
        }
        return true;
    }

    /**
     * 发送短信验证码
     */
    public interface OnSmsSendLisener{

        void onSuccess();

        void onFailure(String errMsg);
    }

    /**
     * 短信验证码的回掉
     */
    public interface OnSmsCheckListener{

        void onSuccess();

        void onFailure(String errMsg);
    }
}
