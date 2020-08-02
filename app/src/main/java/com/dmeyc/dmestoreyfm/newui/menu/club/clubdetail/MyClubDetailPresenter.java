package com.dmeyc.dmestoreyfm.newui.menu.club.clubdetail;

import com.dmeyc.dmestoreyfm.api.CustomSubscriber;
import com.dmeyc.dmestoreyfm.api.RetrofitService;
import com.dmeyc.dmestoreyfm.bean.NewMemberListBean;
import com.dmeyc.dmestoreyfm.bean.response.BaseRespBean;
import com.dmeyc.dmestoreyfm.bean.response.GroupDetailBean;
import com.dmeyc.dmestoreyfm.newbase.BasePresenter;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

/**
 * create by cxg on 2019/11/29
 */
public class MyClubDetailPresenter extends BasePresenter<IMyClubDetaiView> {
    // 退出群
    public void quitGroup() {
        subscriber = new CustomSubscriber<BaseRespBean>(mView, mGuid) {
            @Override
            public void onSuccess(BaseRespBean bean) {
                mView.quitGroupSucc();
            }
        };
        RetrofitService.getInstance().quitGroup(mView.getParams(), subscriber);
    }

    public void httpRequestData() {
        subscriber = new CustomSubscriber<GroupDetailBean>(mView, mGuid) {
            @Override
            public void onSuccess(GroupDetailBean bean) {
                mView.httpDataSucc(bean.getData());
            }
        };
        RetrofitService.getInstance().viewGroupById(mView.getParams(), subscriber);
    }

    public void httpMembersData() {
        subscriber = new CustomSubscriber<NewMemberListBean>(mView, mGuid) {
            @Override
            public void onSuccess(NewMemberListBean bean) {
                List<NewMemberListBean.DataBean.ListBean> list = new ArrayList<>();
                list.addAll(bean.getData().getManagerList());
                list.addAll(bean.getData().getNormalList());
                mView.getMemberSucc(list);
            }
        };
        RetrofitService.getInstance().httpMembersData(mView.getParams(), subscriber);
    }


    public void getNofiticationStatus(final String targetId) {
        RongIM.getInstance().getConversationNotificationStatus(Conversation.ConversationType.GROUP, targetId, new RongIMClient.ResultCallback<Conversation.ConversationNotificationStatus>() {
            @Override
            public void onSuccess(Conversation.ConversationNotificationStatus conversationNotificationStatus) {
                final int value = conversationNotificationStatus.getValue();

                mView.getNotificationStatusResult( value);
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {

            }
        });
    }

    /**
     * 设置群免打扰
     *
     * @param targetId
     */
    public void setNotificationStatus(final String targetId) {
        RongIM.getInstance().getConversationNotificationStatus(Conversation.ConversationType.GROUP, targetId, new RongIMClient.ResultCallback<Conversation.ConversationNotificationStatus>() {
            @Override
            public void onSuccess(final Conversation.ConversationNotificationStatus conversationNotificationStatus) {
                final int value = conversationNotificationStatus.getValue();
                final Conversation.ConversationNotificationStatus conversationNotificationStatus1;
                if (value == 1) {
                    conversationNotificationStatus1 = conversationNotificationStatus.setValue(0);
                } else {
                    conversationNotificationStatus1 = conversationNotificationStatus.setValue(1);
                }
                RongIM.getInstance().setConversationNotificationStatus(Conversation.ConversationType.GROUP,
                        targetId, conversationNotificationStatus1, new RongIMClient.ResultCallback<Conversation.ConversationNotificationStatus>() {
                            @Override
                            public void onSuccess(Conversation.ConversationNotificationStatus conversationNotificationStatus) {
                                final int value = conversationNotificationStatus.getValue();
                                mView.setNotificationStatusResult(true, value);
                            }

                            @Override
                            public void onError(RongIMClient.ErrorCode errorCode) {
                                ToastUtil.show("设置失败");
                                mView.setNotificationStatusResult(false, -1);
                            }
                        });

            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                mView.setNotificationStatusResult(false, -1);
            }
        });
    }
}
