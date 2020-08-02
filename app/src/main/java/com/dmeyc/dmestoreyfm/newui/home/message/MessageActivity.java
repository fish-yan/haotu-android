package com.dmeyc.dmestoreyfm.newui.home.message;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.coremedia.iso.boxes.Container;
import com.dmeyc.dmestoreyfm.bean.response.GroupDetailBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.constant.SPKey;
import com.dmeyc.dmestoreyfm.event.MyEvent;
import com.dmeyc.dmestoreyfm.event.RefreshEvent;
import com.dmeyc.dmestoreyfm.newbase.BaseMvpActivity;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.newui.common.ScanActivity;
import com.dmeyc.dmestoreyfm.newui.common.container.ContainerActivity;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.umeng.debug.log.E;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imkit.widget.adapter.ConversationListAdapter;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Conversation;
import rx.functions.Action1;

/**
 * create by cxg on 2019/12/4
 */
public class MessageActivity extends BaseMvpActivity<IMessageView, MessagePresenter> implements IMessageView {


    private ConversationListFragment listFragment;

    public static void newInstance(Context context) {
        Intent intent = new Intent(context, MessageActivity.class);
        context.startActivity(intent);
    }

    @Bind(R.id.et_test)
    EditText mEtTest;

    @Bind(R.id.tv_fans_tip)
    TextView mTvFansCount;
    @Bind(R.id.iv_prise_tip)
    TextView mTvPriseCount;
    @Bind(R.id.tv_at_me_tip)
    TextView mTvAtMeCount;
    @Bind(R.id.tv_comment_tip)
    TextView mTvCommentCount;

    @Override
    protected boolean enableEventBus() {
        return true;
    }

    @Override
    protected MessagePresenter createPresenter() {
        return new MessagePresenter();
    }

    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @Override
    protected int setContentView() {
        return R.layout.activity_message_new;
    }

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {
        resetRedPointCount();
        findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.httpAddGroup(mEtTest.getText().toString());
            }
        });

        setTitleWithRightIcon("消息", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxPermissions.getInstance(MessageActivity.this).request(Manifest.permission.CAMERA).subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (aBoolean){
                            ScanActivity.newInstance(MessageActivity.this);
                        }else {
                            ToastUtil.show("请求相机权限被拒");
                        }
                    }
                });


            }
        }, R.drawable.icon_scan);
        listFragment = (ConversationListFragment) ConversationListFragment.instantiate(this, ConversationListFragment.class.getName());
        Uri uri = Uri.parse("rong://" + this.getApplicationInfo().packageName).buildUpon()
                .appendPath("conversationlist")
//                .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "true")
                .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")
//                .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "false")
//                .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")
//                .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "false")
                .build();
        listFragment.setUri(uri);
        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
        //将融云的Fragment界面加入到我们的页面。
        transaction.add(R.id.subconversationlist, listFragment);
        transaction.commitAllowingStateLoss();
        ConversationListAdapter conversationListAdapter = listFragment.onResolveAdapter(this);
        conversationListAdapter.notifyDataSetChanged();
    }

    @OnClick({R.id.iv_fans, R.id.iv_prise, R.id.iv_at_me, R.id.iv_comment})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.iv_fans:
                ContainerActivity.newInstance(this, ContainerActivity.TYPE_MESSAGE_FUNS, "粉丝");
//                SPUtils.savaIntData(SPKey.PUSH_FOLLOW, 0);
                RongIM.getInstance().clearMessagesUnreadStatus(Conversation.ConversationType.PRIVATE, Constant.BadgeKey.FANS);
                mTvFansCount.setVisibility(View.GONE);
                break;
            case R.id.iv_prise:
                ContainerActivity.newInstance(this, ContainerActivity.TYPE_MESSAGE_LIKE, "赞");
//                SPUtils.savaIntData(SPKey.PUSH_LIKE, 0);
                RongIM.getInstance().clearMessagesUnreadStatus(Conversation.ConversationType.PRIVATE, Constant.BadgeKey.FAV);
                mTvPriseCount.setVisibility(View.GONE);
                break;
            case R.id.iv_at_me:
                ContainerActivity.newInstance(this, ContainerActivity.TYPE_MESSAGE_AT_ME, "@我");
//                SPUtils.savaIntData(SPKey.PUSH_AT, 0);
                RongIM.getInstance().clearMessagesUnreadStatus(Conversation.ConversationType.PRIVATE, Constant.BadgeKey.AT);
                mTvAtMeCount.setVisibility(View.GONE);
                break;
            case R.id.iv_comment:
                ContainerActivity.newInstance(this, ContainerActivity.TYPE_MESSAGE_COMMON, "评论");
//                SPUtils.savaIntData(SPKey.PUSH_COMMEND, 0);
                RongIM.getInstance().clearMessagesUnreadStatus(Conversation.ConversationType.PRIVATE, Constant.BadgeKey.COMMENT);
                mTvCommentCount.setVisibility(View.GONE);
                break;
            default:
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getQrcodeResult(MyEvent.ScanResult event) {
        mPresenter.httpAddGroup(event.result);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshMessageCount(RefreshEvent.PUSH_NEW_INFO event) {
        resetRedPointCount();
    }

    private void resetRedPointCount() {
        int commend = RongIM.getInstance().getUnreadCount(Conversation.ConversationType.PRIVATE, Constant.BadgeKey.COMMENT);
        int like = RongIM.getInstance().getUnreadCount(Conversation.ConversationType.PRIVATE, Constant.BadgeKey.FAV);
        int at = RongIM.getInstance().getUnreadCount(Conversation.ConversationType.PRIVATE, Constant.BadgeKey.AT);
        int follow = RongIM.getInstance().getUnreadCount(Conversation.ConversationType.PRIVATE, Constant.BadgeKey.FANS);
        if (commend > 0) {
            mTvCommentCount.setText(String.valueOf(commend));
            mTvCommentCount.setVisibility(View.VISIBLE);
        } else {
            mTvCommentCount.setVisibility(View.GONE);
        }
        setViewStyle(commend, mTvCommentCount);
        setViewStyle(like, mTvPriseCount);
        setViewStyle(at, mTvAtMeCount);
        setViewStyle(follow, mTvFansCount);
    }

    private void setViewStyle(int count, TextView textView) {
        if (count > 0) {
            textView.setText(String.valueOf(count));
            textView.setVisibility(View.VISIBLE);
        } else {
            textView.setVisibility(View.GONE);
        }
    }

    @Override
    public void jumpToChat(GroupDetailBean.DataBean data) {
        RongIM.getInstance().startPrivateChat(this, data.getGroup_id(), data.getGroup_name());
    }
}
