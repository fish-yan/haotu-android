package com.dmeyc.dmestoreyfm.ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.ui.chat.ConversationActivity;
import com.dmeyc.dmestoreyfm.utils.PermissionPageUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.SlideButton;

import butterknife.Bind;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imkit.widget.adapter.BaseAdapter;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

import static android.graphics.Color.parseColor;

public class CommInforActivity extends BaseActivity{
    @Bind(R.id.slb_buton2)
    SlideButton slb_buton2;
    @Bind(R.id.ll_commnotice)
    LinearLayout ll_commnotice;
    @Bind(R.id.tv_personnumber)
    TextView tv_personnumber;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_comminfro;
    }
    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        slb_buton2.setBigCircleModel(parseColor("#cccccc"), parseColor("#32abff"), parseColor("#00ffffff"), parseColor("#ffffff"), parseColor("#ffffff"));

        RongIM.getInstance().getConversationNotificationStatus(Conversation.ConversationType.PRIVATE, getIntent().getStringExtra("groupid"), new RongIMClient.ResultCallback<Conversation.ConversationNotificationStatus>() {
            @Override
            public void onSuccess(Conversation.ConversationNotificationStatus conversationNotificationStatus) {
                final int value = conversationNotificationStatus.getValue();
                String title;
                final Conversation.ConversationNotificationStatus conversationNotificationStatus1;
                if(value==1){
                    slb_buton2.setChecked(true);
//                    iv_right_title_bar.setBackground(getResources().getDrawable(R.drawable.comm_nospeakicon));
                }else{
                    slb_buton2.setChecked(false);
//                    iv_right_title_bar.setBackground(getResources().getDrawable(R.drawable.comm_speakcion));
                }
            }
            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {

            }
        });


        slb_buton2.setOnCheckedListener(new SlideButton.SlideButtonOnCheckedListener() {
            @Override
            public void onCheckedChangeListener(boolean isChecked) {
                if(isChecked){

                    RongIM.getInstance().getConversationNotificationStatus(Conversation.ConversationType.GROUP, getIntent().getStringExtra("groupid"), new RongIMClient.ResultCallback<Conversation.ConversationNotificationStatus>() {
                        @Override
                        public void onSuccess(final Conversation.ConversationNotificationStatus conversationNotificationStatus) {
                            final int value = conversationNotificationStatus.getValue();
                            String title;
                            final Conversation.ConversationNotificationStatus conversationNotificationStatus1;
                            if(value==1){
                                conversationNotificationStatus1=conversationNotificationStatus.setValue(0);
                                title="免打扰";
                            }else{
                                conversationNotificationStatus1=conversationNotificationStatus.setValue(1);
                                title="取消免打扰";
                            }
                            RongIM.getInstance().setConversationNotificationStatus(Conversation.ConversationType.GROUP,
                                    getIntent().getStringExtra("groupid"), conversationNotificationStatus1, new RongIMClient.ResultCallback<Conversation.ConversationNotificationStatus>() {
                                        @Override
                                        public void onSuccess(Conversation.ConversationNotificationStatus conversationNotificationStatus) {
                                            final int value = conversationNotificationStatus.getValue();
                                            if(value==1){
                                                slb_buton2.setChecked(true);
                                            }else{
                                                slb_buton2.setChecked(false);
                                            }
                                            ToastUtil.show("设置成功");
                                        }

                                        @Override
                                        public void onError(RongIMClient.ErrorCode errorCode) {
                                            ToastUtil.show("设置失败");
                                        }
                                    });
                        }

                        @Override
                        public void onError(RongIMClient.ErrorCode errorCode) {

                        }
                    });
                }else {
                    RongIM.getInstance().getConversationNotificationStatus(Conversation.ConversationType.GROUP, getIntent().getStringExtra("groupid"), new RongIMClient.ResultCallback<Conversation.ConversationNotificationStatus>() {
                        @Override
                        public void onSuccess(final Conversation.ConversationNotificationStatus conversationNotificationStatus) {
                            final int value = conversationNotificationStatus.getValue();
                            String title;
                            final Conversation.ConversationNotificationStatus conversationNotificationStatus1;
                            if(value==1){
                                conversationNotificationStatus1=conversationNotificationStatus.setValue(0);
                                title="免打扰";
                            }else{
                                conversationNotificationStatus1=conversationNotificationStatus.setValue(1);
                                title="取消免打扰";
                            }
                            RongIM.getInstance().setConversationNotificationStatus(Conversation.ConversationType.GROUP,
                                    getIntent().getStringExtra("groupid"), conversationNotificationStatus1, new RongIMClient.ResultCallback<Conversation.ConversationNotificationStatus>() {
                                        @Override
                                        public void onSuccess(Conversation.ConversationNotificationStatus conversationNotificationStatus) {
                                            final int value = conversationNotificationStatus.getValue();
                                            if(value==1){
                                                slb_buton2.setChecked(true);
                                            }else{
                                                slb_buton2.setChecked(false);
                                            }
                                            ToastUtil.show("设置成功");
                                        }
                                        @Override
                                        public void onError(RongIMClient.ErrorCode errorCode) {
                                            ToastUtil.show("设置失败");
                                        }
                                    });
                        }

                        @Override
                        public void onError(RongIMClient.ErrorCode errorCode) {

                        }
                    });
                }
            }
        });
//        tv_personnumber.setText();
    }
    @OnClick({R.id.ll_applylist,R.id.ll_commmember,R.id.ll_commmoneymanager,R.id.ll_commnotice})
    public void onCLick(View view){
        int viewid=view.getId();
        if(R.id.ll_applylist==viewid){
            startActivity(new Intent(CommInforActivity.this,CommCheckActivity.class));
        }else if(R.id.ll_commmember==viewid){
            startActivity(new Intent(CommInforActivity.this,MemberListActivity.class).putExtra("groupid",getIntent().getStringExtra("groupid")).putExtra("isallban","0").putExtra("type",getIntent().getStringExtra("type")));
        }else if(R.id.ll_commmoneymanager==viewid){
        startActivity(new Intent(CommInforActivity.this,VipManagerActivity.class).putExtra("groupid",getIntent().getStringExtra("groupid")));
        }else if(R.id.ll_commnotice==viewid){
            startActivityForResult(new Intent(CommInforActivity.this,CommNoticeActivity.class).putExtra("groupid",getIntent().getStringExtra("groupid")),123);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
