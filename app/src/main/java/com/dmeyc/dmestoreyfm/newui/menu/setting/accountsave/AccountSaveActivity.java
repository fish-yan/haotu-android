package com.dmeyc.dmestoreyfm.newui.menu.setting.accountsave;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.response.AccountInfoBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.constant.SPKey;
import com.dmeyc.dmestoreyfm.event.RefreshEvent;
import com.dmeyc.dmestoreyfm.newbase.BaseMvpActivity;
import com.dmeyc.dmestoreyfm.newui.menu.setting.authentication.AuthenticationActivity;
import com.dmeyc.dmestoreyfm.newui.menu.setting.bindphone.BindPhoneActivity;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.wedgit.CustomItemView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * create by cxg on 2019/11/24
 */
public class AccountSaveActivity extends BaseMvpActivity<IAccountSaveView, AccountSavePresenter> implements IAccountSaveView {


    @Bind(R.id.civ_account_name)
    CustomItemView mCivAccountName;
    @Bind(R.id.civ_bind_phone)
    CustomItemView mCivBindPhone;
    @Bind(R.id.civ_authentication)
    CustomItemView mCivAuthentication;


    public static void startActivity(Context context) {
        Intent intent = new Intent(context, AccountSaveActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_account_save;
    }

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {
        setTitle("账号与安全");
    }

    @Override
    protected void initData() {
        mPresenter.httpRequestData();
    }

    @Override
    protected boolean enableEventBus() {
        return true;
    }

    @Override
    protected AccountSavePresenter createPresenter() {
        return new AccountSavePresenter();
    }

    @OnClick({R.id.civ_bind_phone,
            R.id.civ_authentication})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.civ_bind_phone:
                BindPhoneActivity.startActivity(this);
                break;
            case R.id.civ_authentication:
                AuthenticationActivity.startActivity(this);
                break;
            default:
                break;
        }
    }

    @Override
    public void httpRequestSucc(AccountInfoBean.DataBean data) {
        SPUtils.savaStringData(SPKey.AUTHENTICATION_RESULT+ SPUtils.getStringData(Constant.Config.USER_ID), data.getIsRealName());
        if ("0".equals(data.getIsRealName())) {

        } else {
            mCivAuthentication.setRightText("已认证");
        }
        if (data.getNick_name() != null) {
            mCivAccountName.setRightText(data.getNick_name());
        }
        if (data.getPhoneNO() != null) {
            mCivBindPhone.setRightText(data.getPhoneNO());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refresh(RefreshEvent.AccountSave event) {
        mPresenter.httpRequestData();
    }
}
