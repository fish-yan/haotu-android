package com.dmeyc.dmestoreyfm.newui.menu.setting.authentication;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.event.RefreshEvent;
import com.dmeyc.dmestoreyfm.newbase.BaseMvpActivity;
import com.dmeyc.dmestoreyfm.ui.WebviewActivity;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.CustomItemView;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * create by cxg on 2019/11/24
 *
 */
public class AuthenticationActivity extends BaseMvpActivity<IAuthenticationView, AuthenticationPresenter> implements IAuthenticationView {

    @Bind(R.id.vic_name)
    CustomItemView mVicName;
    @Bind(R.id.vic_idcard)
    CustomItemView mVicIdcard;
    public static void startActivity(Context context) {
        Intent intent = new Intent(context, AuthenticationActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected AuthenticationPresenter createPresenter() {
        return new AuthenticationPresenter();
    }

    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @Override
    protected int setContentView() {
        return R.layout.activity_authontication;
    }

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {
        setTitleWithRightText("实名认证", "提交", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkSucc()){
                    mPresenter.httpRequestData();
                }
            }

        });
    }

    private boolean checkSucc() {
        if (TextUtils.isEmpty(mVicName.getTitle())){
            ToastUtil.show("请输入名字");
            return false;
        }
        if (TextUtils.isEmpty(mVicIdcard.getTitle())){
            ToastUtil.show("请输入身份证");
            return false;
        }
        return true;
    }
    @OnClick(R.id.tv_protocol)
    public void click(){
        WebviewActivity.newInstance(this,"用户协议","http://www.hotu.club:9595/agreement/agreementhz.html");
    }
    @OnClick(R.id.tv_private)
    public void click1(){
        WebviewActivity.newInstance(this,"隐私条款","http://www.hotu.club:9595/agreement/agreement.html");
    }

    @Override
    public Map<String, String> getParams() {
        Map<String, String> params  = new HashMap<>();
        params.put("idNo",mVicIdcard.getTitle());
        params.put("name",mVicName.getTitle());
        return params;
    }

    @Override
    public void httpRequestSucc() {
        ToastUtil.show("实名成功");
        finish();
        EventBus.getDefault().post(new RefreshEvent.AccountSave());
    }
}
