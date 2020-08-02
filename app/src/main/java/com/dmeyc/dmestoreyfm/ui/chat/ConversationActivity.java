package com.dmeyc.dmestoreyfm.ui.chat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.NoticeCommPagerAdapter;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.CommNoticeBean;
import com.dmeyc.dmestoreyfm.bean.CommUserInfro;
import com.dmeyc.dmestoreyfm.bean.CreatCommEditeBean;
import com.dmeyc.dmestoreyfm.bean.MemberListBean;
import com.dmeyc.dmestoreyfm.bean.MyCommListBean;
import com.dmeyc.dmestoreyfm.bean.PublishActionAfterBean;
import com.dmeyc.dmestoreyfm.bean.SetChangeBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.event.MyEvent;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.newbase.BaseActivity;
import com.dmeyc.dmestoreyfm.newui.menu.club.clubdetail.MyClubDetailActivity;
import com.dmeyc.dmestoreyfm.ui.ChartInforActivity;
import com.dmeyc.dmestoreyfm.ui.CommCheckActivity;
import com.dmeyc.dmestoreyfm.ui.CommInforActivity;
import com.dmeyc.dmestoreyfm.ui.MemberListActivity;
import com.dmeyc.dmestoreyfm.ui.MyCommActivity;
import com.dmeyc.dmestoreyfm.ui.SorcerInActivity;
import com.dmeyc.dmestoreyfm.utils.DataClass;
import com.dmeyc.dmestoreyfm.utils.DensityUtil;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationFragment;
import io.rong.imkit.model.Event;
import io.rong.imkit.model.GroupUserInfo;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Group;
import io.rong.imlib.model.UserInfo;
import io.rong.message.RichContentMessage;

public class ConversationActivity extends BaseActivity {

    TextView test;
    //    TextView tv_title,tv_person,tv_state;
    private String targetId;
    //    private ImageView iv_left_title_bar;
    ViewPager viewpager;

    //     String type;
//      ImageView iv_right_title_bar,comm_member,iv_aplylist;
    private Fragment conversation;
    private String mGroupOwnerType;// 1 群主，0 非群众

    @Override
    protected int setContentView() {
        return R.layout.activity_conversation;
    }

    @Override
    protected void initViews() {
        setTitleWithRightIcon("", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mGroupOwnerType == null) {
                    ToastUtil.show("网络异常，请退出重进");
                } else {
                    MyClubDetailActivity.newInstance(ConversationActivity.this, targetId,mGroupOwnerType);

                }

            }
        }, R.drawable.right_iconmore);
        EventBus.getDefault().register(this);
        test = findViewById(R.id.test);
        targetId = getIntent().getData().getQueryParameter("targetId");
        getGroupData(targetId);

        String text = "05月26日   18:00 长兴公园羽毛球馆  28人报名";
        SpannableString spannableString = new SpannableString(text);
//设置颜色
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#32abff")), text.length() - 6, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        test.setText(spannableString);
        TranslateAnimation translateAnimation = new TranslateAnimation(
                //X轴初始位置
                Animation.RELATIVE_TO_SELF, 1.0f,
                //X轴移动的结束位置
                Animation.RELATIVE_TO_SELF, 0.0f,
                //y轴开始位置
                Animation.RELATIVE_TO_SELF, 0.0f,
                //y轴移动后的结束位置
                Animation.RELATIVE_TO_SELF, 0.0f);
        //3秒完成动画
        translateAnimation.setDuration(10000);
        translateAnimation.setRepeatCount(10000);
        test.startAnimation(translateAnimation);
        setData();

        isOwner();
        RongIM.setGroupUserInfoProvider(new RongIM.GroupUserInfoProvider() {
            @Override
            public GroupUserInfo getGroupUserInfo(String s, final String s1) {

                String ssss = s;
                String ewrwre = s1;

                RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
                    @Override
                    public UserInfo getUserInfo(String s) {

                        return findUerInforByUerid(Integer.parseInt(s1));
                    }
                }, true);

                return null;

            }

        }, true);
    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_conversation);
////        iv_left_title_bar=findViewById(R.id.iv_left_title_bar);
////        iv_left_title_bar.setOnClickListener(this);
////        tv_title=findViewById(R.id.tv_title);
////        test=findViewById(R.id.test);
//
////        iv_right_title_bar=findViewById(R.id.iv_right_title_bar);
////        comm_member=findViewById(R.id.comm_member);
////        comm_member.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
//////                startActivity(new Intent(ConversationActivity.this,MemberListActivity.class).putExtra("group_id",targetId).putExtra("isallban",commDetailBean.getData().getIs_all_ban()));
////                startActivity(new Intent(ConversationActivity.this,MemberListActivity.class).putExtra("groupid",targetId).putExtra("isallban","0").putExtra("type",type));
////            }
////        });
////        iv_aplylist=findViewById(R.id.iv_aplylist);
////        viewpager=findViewById(R.id.viewpager);
////        String title = getIntent().getData().getQueryParameter("title");
////        targetId= getIntent().getData().getQueryParameter("targetId");
////        getGroupData(targetId);
////        iv_aplylist.setOnClickListener(this);
//
////        RongIM.getInstance().getConversationNotificationStatus(Conversation.ConversationType.GROUP, targetId, new RongIMClient.ResultCallback<Conversation.ConversationNotificationStatus>() {
////                    @Override
////                    public void onSuccess(Conversation.ConversationNotificationStatus conversationNotificationStatus) {
////                        final int value = conversationNotificationStatus.getValue();
////                        String title;
////                        final Conversation.ConversationNotificationStatus conversationNotificationStatus1;
////                        if(value==1){
////                            iv_right_title_bar.setBackground(getResources().getDrawable(R.drawable.comm_speakcion));
////                        }else{
////                            iv_right_title_bar.setBackground(getResources().getDrawable(R.drawable.comm_nospeakicon));
////                        }
////                    }
////                    @Override
////                    public void onError(RongIMClient.ErrorCode errorCode) {
////
////                    }
////                });
//
////        String text = "05月26日   18:00 长兴公园羽毛球馆  28人报名";
////        SpannableString spannableString = new SpannableString(text);
//////设置颜色
////        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#32abff")), text.length() - 6, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
////        test.setText(spannableString);
////        TranslateAnimation translateAnimation = new TranslateAnimation(
////                //X轴初始位置
////                Animation.RELATIVE_TO_SELF, 1.0f,
////                //X轴移动的结束位置
////                Animation.RELATIVE_TO_SELF, 0.0f,
////                //y轴开始位置
////                Animation.RELATIVE_TO_SELF, 0.0f,
////                //y轴移动后的结束位置
////                Animation.RELATIVE_TO_SELF, 0.0f);
////        //3秒完成动画
////        translateAnimation.setDuration(10000);
////        translateAnimation.setRepeatCount(10000);
////        test.startAnimation(translateAnimation);
////        setData();
//
////        iv_right_title_bar.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                RongIM.getInstance().getConversationNotificationStatus(Conversation.ConversationType.GROUP, targetId, new RongIMClient.ResultCallback<Conversation.ConversationNotificationStatus>() {
////                            @Override
////                            public void onSuccess(final Conversation.ConversationNotificationStatus conversationNotificationStatus) {
////                                final int value = conversationNotificationStatus.getValue();
////                                String title;
////                                final Conversation.ConversationNotificationStatus conversationNotificationStatus1;
////                                if(value==1){
////                                    conversationNotificationStatus1=conversationNotificationStatus.setValue(0);
////                                    title="免打扰";
////                                }else{
////                                    conversationNotificationStatus1=conversationNotificationStatus.setValue(1);
////                                    title="取消免打扰";
////                                }
////                                RongIM.getInstance().setConversationNotificationStatus(Conversation.ConversationType.GROUP,
////                                        targetId, conversationNotificationStatus1, new RongIMClient.ResultCallback<Conversation.ConversationNotificationStatus>() {
////                                            @Override
////                                            public void onSuccess(Conversation.ConversationNotificationStatus conversationNotificationStatus) {
////                                                final int value = conversationNotificationStatus.getValue();
////                                                if(value==1){
////                                                    iv_right_title_bar.setBackground(getResources().getDrawable(R.drawable.comm_speakcion));
//////                                                    conversationNotificationStatus1=conversationNotificationStatus.setValue(0);
//////                                                    title="免打扰";
////                                                }else{
////                                                    iv_right_title_bar.setBackground(getResources().getDrawable(R.drawable.comm_nospeakicon));
//////                                                    conversationNotificationStatus1=conversationNotificationStatus.setValue(1);
//////                                                    title="取消免打扰";
////                                                }
////                                            }
////
////                                            @Override
////                                            public void onError(RongIMClient.ErrorCode errorCode) {
////                                                ToastUtil.show("设置失败");
////                                            }
////                                        });
////
//////                                AgreementDialog.Chatshow(ConversationActivity.this,title, new OnFloatingLayerClickLisenter() {
//////                                    @Override
//////                                    public void onOkClick() {
//////                                        RongIM.getInstance().setConversationNotificationStatus(Conversation.ConversationType.PRIVATE,
//////                                                targetId, conversationNotificationStatus1, new RongIMClient.ResultCallback<Conversation.ConversationNotificationStatus>() {
//////                                                    @Override
//////                                                    public void onSuccess(Conversation.ConversationNotificationStatus conversationNotificationStatus) {
//////                                                        showToast("设置成功");
//////                                                    }
//////
//////                                                    @Override
//////                                                    public void onError(RongIMClient.ErrorCode errorCode) {
//////                                                        showToast("设置失败");
//////                                                    }
//////                                                });
//////                                    }
//////
//////
//////                                });
////                            }
////                            @Override
////                            public void onError(RongIMClient.ErrorCode errorCode) {
////
////                            }
////                        });
////            }
////        });
////        isOwner();
////        RongIM.setGroupUserInfoProvider(new RongIM.GroupUserInfoProvider() {
////            @Override
////            public GroupUserInfo getGroupUserInfo(String s, final String s1) {
////
////                String ssss = s;
////                String ewrwre = s1;
////
////                RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
////                    @Override
////                    public UserInfo getUserInfo(String s) {
////
////                        return findUerInforByUerid(Integer.parseInt(s1));
////                    }
////                }, true);
////
////                return null;
////
////            }
////
////        }, true);
////        getMemberData(targetId);
////        SetBadge(RongIM.getInstance().getUnreadCount(Conversation.ConversationType.GROUP));
//    }
//

    public void isOwner() {

        RestClient.getYfmNovate(this).post(Constant.API.YFM_ISOWNER, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("group_id", targetId)
                .build(), new DmeycBaseSubscriber<PublishActionAfterBean>() {
            @Override
            public void onSuccess(PublishActionAfterBean bean) {
                DataClass.commidinity = bean.getData();
                mGroupOwnerType = bean.getData();
//            type=bean.getData();
//            if("1".equals(type)){
//                iv_right_title_bar.setVisibility(View.GONE);
//                comm_member.setVisibility(View.GONE);
//                iv_aplylist.setVisibility(View.VISIBLE);
//            }else if("2".equals(type)){
//                iv_right_title_bar.setVisibility(View.VISIBLE);
//                comm_member.setVisibility(View.VISIBLE);
//                iv_aplylist.setVisibility(View.GONE);
//            }else {
//                iv_right_title_bar.setVisibility(View.VISIBLE);
//                comm_member.setVisibility(View.VISIBLE);
//                iv_aplylist.setVisibility(View.GONE);
//            }
            }
        });
    }

    CommNoticeBean commNoticeBean;
    ArrayList<CommNoticeBean.DataBean.ActivityListBean> activityList = new ArrayList<>();

    public void setData() {
        RestClient.getYfmNovate(this).post(Constant.API.YFM_CONNNOTICE, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("group_id", Integer.parseInt(targetId))
                .build(), new DmeycBaseSubscriber<CommNoticeBean>() {
            @Override
            public void onSuccess(CommNoticeBean bean) {
                commNoticeBean = bean;
//                getMemberData(targetId);
                setmember();
//                tv_title.setText(bean.getData().getGroupName()+"("+bean.getData().getGroupTotalNo()+")");
                setTitle(bean.getData().getGroupName() + "(" + bean.getData().getGroupTotalNo() + ")");

                if (!TextUtils.isEmpty(bean.getData().getNotice())) {
                    CommNoticeBean.DataBean.ActivityListBean activityListBean = new CommNoticeBean.DataBean.ActivityListBean();
                    activityListBean.setActivityName(bean.getData().getNotice());
                    activityList.add(activityListBean);
                }

                activityList.addAll(bean.getData().getActivityList());
                if (bean.getData().getActivityList().size() == 0 && TextUtils.isEmpty(bean.getData().getNotice())) {
                    viewpager.setVisibility(View.GONE);
                }
//                viewpager.setAdapter(new NoticeCommPagerAdapter(bean,ConversationActivity.this));
                viewpager.setAdapter(new NoticeCommPagerAdapter(activityList, ConversationActivity.this, bean));
                new Thread(new Runnable() {
                    @Override
                    public void run() {
//                        islooper = true;
                        while (true) {
                            SystemClock.sleep(2000);
                            //这里是设置当前页的下一页
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    viewpager.setCurrentItem(viewpager.getCurrentItem() + 1);
                                }
                            });
                        }
                    }
                }).start();
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
    }
//    @Override
//    public void onClick(View view) {
//        int viewid=view.getId();
//        if(R.id.iv_left_title_bar==viewid){
//                 finish();
//        }else if(R.id.iv_aplylist==viewid){
//// startActivity(new Intent(this,CommCheckActivity.class));
//            startActivity(new Intent(this,CommInforActivity.class).putExtra("groupid",targetId).putExtra("type",type));
//        }
//    }

    MemberListBean memberListBean;

    public void getGroupData(String tagetid) {

        RestClient.getYfmNovate(this).post(Constant.API.YFM_GROUPMEMVER, new ParamMap.Build()
                        .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                        .addParams("group_id", Integer.parseInt(tagetid))
                        .build(), new DmeycBaseSubscriber<MemberListBean>() {
                    @Override
                    public void onSuccess(MemberListBean bean) {
                        MemberListBean.DataBean.NormalListBean normalListBean;
                        for (int i = 0; i < bean.getData().getManagerList().size(); i++) {
                            normalListBean = new MemberListBean.DataBean.NormalListBean();
                            normalListBean.setGroup_id(bean.getData().getManagerList().get(i).getGroup_id());
                            normalListBean.setHead_icon(bean.getData().getManagerList().get(i).getHead_icon());
                            normalListBean.setNick_name(bean.getData().getManagerList().get(i).getNick_name());
                            normalListBean.setUser_id(bean.getData().getManagerList().get(i).getUser_id());
                            bean.getData().getNormalList().add(normalListBean);
                        }
                        MemberListBean.DataBean.NormalListBean nomsecrice = new MemberListBean.DataBean.NormalListBean();
                        nomsecrice.setUser_id(1);
                        nomsecrice.setNick_name("群小蜜");
//                        nomsecrice.setGroup_id(bean.getData().getManagerList().get(i).getGroup_id());
                        nomsecrice.setHead_icon("http://192.168.0.104/group1/M00/00/17/wKgAaF1AZN-AU40sAAAvY7OvKA4717.png");

                        bean.getData().getNormalList().add(nomsecrice);
                        memberListBean = bean;
                    }
                }
        );

    }

    UserInfo userInfo;

    public UserInfo findUerInforByUerid(final int userid) {


        RestClient.getYfmNovate(this).post(Constant.API.YFM_FINDGROUPINFROBYUERID, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("userId", userid)
                .build(), new DmeycBaseSubscriber<CommUserInfro>() {
            @Override
            public void onSuccess(CommUserInfro bean) {
//                ToastUtil.show(bean.getMsg());
                userInfo = new UserInfo(String.valueOf(userid), bean.getData().getNick_name(), Uri.parse(bean.getData().getUser_logo()));

            }
        });
        SystemClock.sleep(3000);
        return userInfo;
    }

    ArrayList<UserInfo> uerinfos = new ArrayList<>();
    UserInfo userinfros;
    MemberListBean.DataBean.NormalListBean nb;
    MemberListBean dsfdfs;
    String sdfsdf;

    public void getMemberData(final String groupid, final RongIM.IGroupMemberCallback caback) {
        uerinfos.clear();
        RestClient.getYfmNovate(this).post(Constant.API.YFM_GETGROUPMEBER, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("group_id", groupid)
                .build(), new DmeycBaseSubscriber<MemberListBean>() {
            @Override
            public void onSuccess(final MemberListBean bean) {
                for (int i = 0; i < bean.getData().getManagerList().size(); i++) {
                    nb = new MemberListBean.DataBean.NormalListBean();
                    nb.setNick_name(bean.getData().getManagerList().get(i).getNick_name());
                    nb.setUser_id(bean.getData().getManagerList().get(i).getUser_id());
                    nb.setGroup_id(bean.getData().getManagerList().get(i).getGroup_id());
                    nb.setGroup_id(bean.getData().getManagerList().get(i).getGroup_id());
                    nb.setSex(bean.getData().getManagerList().get(i).getSex());
                    nb.setHead_icon(bean.getData().getManagerList().get(i).getHead_icon());
                    bean.getData().getNormalList().add(nb);
                }

                for (int i = 0; i < bean.getData().getNormalList().size(); i++) {
                    userinfros = new UserInfo(bean.getData().getNormalList().get(i).getUser_id() + "", bean.getData().getNormalList().get(i).getNick_name(), Uri.parse(memberListBean.getData().getNormalList().get(i).getHead_icon()));
                    uerinfos.add(userinfros);
                }
//                    RongIM.getInstance().setGroupMembersProvider(new RongIM.IGroupMembersProvider() {
//                        @Override
//                        public void getGroupMembers(String groupId, RongIM.IGroupMemberCallback callback) {
                dsfdfs = bean;
                sdfsdf = groupid;
                caback.onGetGroupMembersResult(uerinfos); // 调用 callback 的 onGetGroupMembersResult 回传群组信息
//                        }
//                    });
            }
        });
    }

    public void setmember() {
        RongIM.getInstance().setGroupMembersProvider(new RongIM.IGroupMembersProvider() {
            @Override
            public void getGroupMembers(String groupId, RongIM.IGroupMemberCallback callback) {
                getMemberData(groupId, callback);
//                 callback.onGetGroupMembersResult(uerinfos); // 调用 callback 的 onGetGroupMembersResult 回传群组信息
            }
        });
    }


//    public void SetBadge(int num){
//        try{
//            String pName= getPackageName();
////            Toast.makeText(ConversationActivity.this, pName, Toast.LENGTH_LONG).show();
//            Bundle bunlde =new Bundle();
//            bunlde.putString("package", "com.dmeyc.dmestoreyfm"); // 包名
//            bunlde.putString("class", "com.dmeyc.dmestoreyfm.ui.YFMIndicatoActivity"); //类名
//            bunlde.putInt("badgenumber",0);
//            Bundle res= this.getContentResolver().call(Uri.parse("content://com.huawei.android.launcher.settings/badge/"), "change_badge", null, bunlde);
//        }catch(Exception e){
////            Toast.makeText(ConversationActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
//        }
//    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void myFinish(MyEvent.Close.ConversationActivity event){
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
