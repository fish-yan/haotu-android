package com.dmeyc.dmestoreyfm.newui.menu.apply;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.config.CommonConfig;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.constant.SPKey;
import com.dmeyc.dmestoreyfm.event.MyEvent;
import com.dmeyc.dmestoreyfm.event.RefreshEvent;
import com.dmeyc.dmestoreyfm.newbase.BaseActivity;
import com.dmeyc.dmestoreyfm.newui.common.CommentRequestHelper;
import com.dmeyc.dmestoreyfm.newui.menu.apply.anchor.ApplyAnchorActivity;
import com.dmeyc.dmestoreyfm.newui.menu.apply.coach.ApplyCoachActivity;
import com.dmeyc.dmestoreyfm.newui.menu.apply.group.ApplyGroupActivity;
import com.dmeyc.dmestoreyfm.newui.menu.apply.merchant.ApplyMerchantActivity;
import com.dmeyc.dmestoreyfm.newui.menu.setting.authentication.AuthenticationActivity;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.OnClick;

/**
 * create by cxg on 2019/11/23
 */
public class ApplyForActivity extends BaseActivity {

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, ApplyForActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_apply_for;
    }

    @Override
    protected void initViews() {
        setTitle("申请");
        EventBus.getDefault().register(this);

        if (CommonConfig.ROLE_ANCHOR.equals(SPUtils.getStringData(Constant.Config.ROLECODE))) {
            findViewById(R.id.civ_anchor).setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.civ_grouper,
            R.id.civ_merchant,
            R.id.civ_coach,
            R.id.civ_anchor})
    public void click(final View view) {
        if ( !CommonConfig.isGroupOwner(SPUtils.getStringData(SPKey.AUTHENTICATION_RESULT+ SPUtils.getStringData(Constant.Config.USER_ID)))) {
            CommentRequestHelper.httpAuthentication(new CommentRequestHelper.CallBackAdapter() {
                @Override
                public void onSuccess(String string) {
                    if (CommonConfig.isGroupOwner(string)) {
                        doNext(view);
                    } else {
                        AuthenticationActivity.startActivity(ApplyForActivity.this);
                        ToastUtil.show("请先实名认证");
                    }
                }
            });
        }else {
            doNext(view);
        }


    }

    private void doNext(View view){
        switch (view.getId()) {
            case R.id.civ_grouper:
                ApplyGroupActivity.startActivity(this);
                break;
            case R.id.civ_merchant:
                ApplyMerchantActivity.newInstance(this);
                break;
            case R.id.civ_coach:
                ApplyCoachActivity.newInstance(this);
                break;
            case R.id.civ_anchor:
                ApplyAnchorActivity.newInstance(this);
                break;
            default:
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void myFinish(MyEvent.Close.ApplyForActivity event) {
        EventBus.getDefault().post(new RefreshEvent.PagerDetailFragments());
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
