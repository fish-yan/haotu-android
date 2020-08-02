package com.dmeyc.dmestoreyfm.newui.login.bindthirdpart;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.YFMLoginBean;
import com.dmeyc.dmestoreyfm.constant.ExtraKey;
import com.dmeyc.dmestoreyfm.newbase.BaseMvpActivity;
import com.dmeyc.dmestoreyfm.newui.common.CommentRequestHelper;
import com.dmeyc.dmestoreyfm.newui.login.LoginActivity;
import com.dmeyc.dmestoreyfm.ui.MainActivity;
import com.dmeyc.dmestoreyfm.utils.CheckInfoUtil;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.utils.Util;
import com.dmeyc.dmestoreyfm.wedgit.CustomItemView;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;

/**
 * create by cxg on 2019/12/13
 */
public class BindThirdPartActivity extends BaseMvpActivity<IBindThirdPartView,BindThirdPartPresenter> implements IBindThirdPartView {

    @Bind(R.id.civ_new_phone_no)
    CustomItemView mCivNewPhoneNo;
    @Bind(R.id.civ_code)
    CustomItemView mCivCode;

    private Map<String,String> mThirdPartParams ;

    public static void newInstance(Context context,Map<String, String> thirdPartData) {
        Intent intent = new Intent(context, BindThirdPartActivity.class);
        intent.putExtra(ExtraKey.THIRD_PART_PARAM, (Serializable) thirdPartData);
        context.startActivity(intent);
    }
    @Override
    protected BindThirdPartPresenter createPresenter() {
        return new BindThirdPartPresenter();
    }

    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @Override
    protected int setContentView() {
        return R.layout.activity_bind_third_part;
    }

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {
        setTitleWithBothText("手机绑定", "提交", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkSucc()){
                    mPresenter.httpRequestData();
                }
            }
        });

        mThirdPartParams = ((HashMap<String, String>) getIntent().getSerializableExtra(ExtraKey.THIRD_PART_PARAM));

        mCivNewPhoneNo.setItemClickListener(new CustomItemView.OnclickListener() {
            @Override
            public void onCodeClick(CustomItemView view) {
                    CommentRequestHelper.httpCodeData(mCivNewPhoneNo.getTitle(),new CommentRequestHelper.CallBackAdapter(){
                        @Override
                        public void onGetCodeSucc() {
                            super.onGetCodeSucc();
                            mCivNewPhoneNo.startTimer();
                        }
                    });
            }
        });

    }
    private boolean checkSucc() {
        if (TextUtils.isEmpty(mCivCode.getTitle())) {
            ToastUtil.show("请填写验证码");
            return false;
        }

        return CheckInfoUtil.isPhoneNo(mCivNewPhoneNo.getTitle());
    }

    @Override
    public Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("phone_no",mCivNewPhoneNo.getTitle());
        params.put("validate_code",mCivCode.getTitle());
        params.put("loginType","1");
        params.put("openId",mThirdPartParams.get("openid"));
        params.put("nickName",mThirdPartParams.get("name"));
        if (!TextUtils.isEmpty(mThirdPartParams.get("iconurl"))) {
            params.put("iconUrl", mThirdPartParams.get("iconurl"));
        }
        if (!TextUtils.isEmpty(mThirdPartParams.get("sex"))){
            params.put("sex",mThirdPartParams.get("sex"));
        }else {
            params.put("sex","1");
        }
        return params;
    }

    @Override
    public void httpDataSucc(YFMLoginBean bean) {
        Util.savaYFMUserInfo(bean);
        Intent intent = new Intent(BindThirdPartActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
