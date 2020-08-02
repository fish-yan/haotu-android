package com.dmeyc.dmestoreyfm.newui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.YFMLoginBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.newbase.BaseMvpActivity;
import com.dmeyc.dmestoreyfm.newbase.MyActivityLifecycleCallbacks;
import com.dmeyc.dmestoreyfm.newui.login.bindthirdpart.BindThirdPartActivity;
import com.dmeyc.dmestoreyfm.newui.update.UpdateManager;
import com.dmeyc.dmestoreyfm.newui.update.UpdateResultBean;
import com.dmeyc.dmestoreyfm.ui.MainActivity;
import com.dmeyc.dmestoreyfm.ui.WebviewActivity;
import com.dmeyc.dmestoreyfm.ui.YFMLoginActivity;
import com.dmeyc.dmestoreyfm.ui.YfmBundTherActiivty;
import com.dmeyc.dmestoreyfm.utils.CheckInfoUtil;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.utils.Util;
import com.dmeyc.dmestoreyfm.wedgit.TimerTaskTextView;
import com.sina.weibo.sdk.utils.LogUtil;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * create by cxg on 2019/12/13
 */
public class LoginActivity extends BaseMvpActivity<ILoginView, LoginPresenter> implements ILoginView {

    @Bind(R.id.ttv_item_code)
    TimerTaskTextView mTtvItemCode;
    @Bind(R.id.et_phone_no)
    EditText mEtPhoneNo;
    @Bind(R.id.et_code)
    EditText mEtCode;

    private UMShareAPI umShareAPI;
    private Map<String, String> mThirdPartData;

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setImmersiveStatusBar(false, getResources().getColor(R.color.black));
    }

    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @Override
    protected int setContentView() {
        return R.layout.activity_login_new;
    }

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {
        super.initData();
        umShareAPI = UMShareAPI.get(this);
        mPresenter.updateVersion(getUpdateVersionParams());
    }

    @Override
    public Map<String, String> getCodeParams() {
        Map<String, String> params = new HashMap<>();
        params.put("phone_no", mEtPhoneNo.getText().toString());
        return params;
    }

    @Override
    public Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("phone_no", mEtPhoneNo.getText().toString());
        params.put("validate_code", mEtCode.getText().toString());
        return params;
    }

    @Override
    public void doLoginSucc(YFMLoginBean bean) {
        Util.savaYFMUserInfo(bean);
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void unBindThirdPart() {
        BindThirdPartActivity.newInstance(LoginActivity.this, mThirdPartData);
    }

    @Override
    public void bindThirdPart(YFMLoginBean bean) {
        Util.savaYFMUserInfo(bean);
        startActivity(new Intent(LoginActivity.this, MainActivity.class));

    }

    @Override
    public Map<String, String> getUpdateVersionParams(){
        Map<String, String> params = new HashMap<>();
        params.put("versionCode", Util.getLocalVersionName(LoginActivity.this));
        return params;
    }

    @Override
    public void getUpdateVerSucc(UpdateResultBean bean) {
        // 获取版本信息成功
        if(bean != null && bean.getData() != null){
            if("0".equals(bean.getData().getVersionType()) || "1".equals(bean.getData().getVersionType())){
                UpdateManager updateManager = new UpdateManager(LoginActivity.this);
                updateManager.showUpdateDialog(bean.getData());
            }
        }
    }

    @Override
    public void getCodeSucc() {
        ToastUtil.show("验证码已发送");
        mTtvItemCode.startTimer();
    }

    @OnClick({R.id.tv_skip, R.id.iv_wechat, R.id.tv_login, R.id.tv_protocol, R.id.ttv_item_code, R.id.tv_private})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.tv_skip:
                if (MyActivityLifecycleCallbacks.getActivitySize() > 1) {
                    finish();
                } else {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }

                break;
            case R.id.iv_wechat:
                umShareAPI.getPlatformInfo(this, SHARE_MEDIA.WEIXIN, umAuthListener);
                break;
            case R.id.tv_login:
                if (CheckInfoUtil.isPhoneNo(mEtPhoneNo.getText().toString())) {
                    mPresenter.doLogin();
                }
                break;
            case R.id.tv_protocol:
                WebviewActivity.newInstance(this, "用户协议", "http://www.hotu.club:9595/agreement/agreementhz.html");
                break;
            case R.id.tv_private:
                WebviewActivity.newInstance(this, "隐私条款", "http://www.hotu.club:9595/agreement/agreement.html");
                break;
            case R.id.ttv_item_code:
                if (CheckInfoUtil.isPhoneNo(mEtPhoneNo.getText().toString())) {
                    mPresenter.getCode();
                }
                break;
            default:
        }
    }

    private UMAuthListener umAuthListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            mThirdPartData = data;
            if (platform.ordinal() == SHARE_MEDIA.WEIXIN.ordinal()) {
                Map<String, String> params = new HashMap<>();
                params.put("openId", data.get("openid"));
                params.put("loginType", "1");
                if (!TextUtils.isEmpty(data.get("iconurl"))) {
                    params.put("headIcon", data.get("iconurl"));
                }
                mPresenter.checkBindOpenId(params);
            }
        }

        @Override
        public void onError(SHARE_MEDIA share_media, int i, java.lang.Throwable throwable) {
            ToastUtil.show("登录失败换种方式登录");
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(LoginActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
