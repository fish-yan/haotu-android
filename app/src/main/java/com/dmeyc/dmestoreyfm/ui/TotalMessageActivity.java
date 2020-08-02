package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.ui.chat.ConversationListActivity;
import com.dmeyc.dmestoreyfm.utils.SPUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;

public class TotalMessageActivity extends BaseActivity {
    @Bind(R.id.v_divide)
    View vDivide;

    @Override
    protected int getLayoutRes() {
        return R.layout.ac_total_message;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        tvTitle.setText("我的消息");
        vDivide.setVisibility(View.INVISIBLE);
    }

    @OnClick({R.id.rel_system,R.id.rel_record})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rel_system:

                break;
            case R.id.rel_record:

                RongIM.getInstance().getConversationList(new RongIMClient.ResultCallback<List<Conversation>>() {
                    @Override
                    public void onSuccess(List<Conversation> conversations) {
                        for (int i = 0; i < conversations.size(); i++) {
//                            getListUserInfo(TotalMessageActivity.this, conversations.get(i).getTargetId());
                            setRongUserInfo(conversations.get(i).getTargetId());
                        }


                    }

                    @Override
                    public void onError(RongIMClient.ErrorCode errorCode) {

                    }
                });

                    Map <String,Boolean> map = new HashMap <>();
                    map.put(Conversation.ConversationType.GROUP.getName(),false);
                RongIM.getInstance().startConversationList(TotalMessageActivity.this,map);
//                startActivity(new Intent(this, ConversationListActivity.class));
                break;
        }
    }

    //设置容云用户信息
    private void setRongUserInfo(final String targetid) {
        if (RongIM.getInstance()!=null){
            RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
                @Override
                public UserInfo getUserInfo(String s) {
                    return new UserInfo(String.valueOf(SPUtils.getStringData(Constant.Config.USER_ID)),
                            SPUtils.getStringData(Constant.Config.NICK_NAME),
                            Uri.parse(SPUtils.getStringData(Constant.Config.AVATAR)));
                }
            },true);
        }
    }

}
