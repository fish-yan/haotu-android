package com.dmeyc.dmestoreyfm.newui.menu.setting.bindphone;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.event.RefreshEvent;
import com.dmeyc.dmestoreyfm.newbase.BaseMvpActivity;
import com.dmeyc.dmestoreyfm.utils.CheckInfoUtil;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.CustomItemView;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;

/**
 * create by cxg on 2019/11/24
 */
public class BindPhoneActivity extends BaseMvpActivity<IBIndPhoneView, BindPhonePresenter> implements IBIndPhoneView {


    @Bind(R.id.civ_pre_phone_no)
    CustomItemView mCivPrePhoneNo;
    @Bind(R.id.civ_new_phone_no)
    CustomItemView mCivNewPhoneNo;
    @Bind(R.id.civ_code)
    CustomItemView mCivCode;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, BindPhoneActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected BindPhonePresenter createPresenter() {
        return new BindPhonePresenter();
    }

    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @Override
    protected int setContentView() {
        return R.layout.activity_bind_phone;
    }

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {
        setTitleWithRightText("手机绑定", "提交", new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (checkSucc()) {
                    mPresenter.bindPhoneNo();
                }
            }
        });

        mCivNewPhoneNo.setItemClickListener(new CustomItemView.OnclickListener() {
            @Override
            public void onCodeClick(CustomItemView view) {
                if (CheckInfoUtil.isPhoneNo(mCivPrePhoneNo.getTitle(), "请输入正确原手机号")) {
                    mPresenter.getCode();
                }
            }
        });
    }

    private boolean checkSucc() {
        if (TextUtils.isEmpty(mCivCode.getTitle())) {
            ToastUtil.show("请填写验证码");
            return false;
        }

        return CheckInfoUtil.isPhoneNo(mCivPrePhoneNo.getTitle(), "请输入正确原手机号") && CheckInfoUtil.isPhoneNo(mCivNewPhoneNo.getTitle(), "请输入正确新手机号");
    }

    @Override
    public Map<String, String> getCodeParams() {
        Map<String, String> params = new HashMap<>();
        params.put("phone_no", mCivNewPhoneNo.getTitle());
        return params;
    }

    @Override
    public void getCodeSucc() {
        ToastUtil.show("验证码已发送注意查收");
        mCivNewPhoneNo.startTimer();
    }

    @Override
    public Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("phoneNo",mCivNewPhoneNo.getTitle());
        params.put("validCode",mCivCode.getTitle());
        return params;
    }

    @Override
    public void bindPhoneSucc() {
        finish();
        EventBus.getDefault().post(new RefreshEvent.AccountSave());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
