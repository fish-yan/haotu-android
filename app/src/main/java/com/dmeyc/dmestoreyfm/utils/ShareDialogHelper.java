package com.dmeyc.dmestoreyfm.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.dmeyc.dmestoreyfm.bean.RoClodBean;
import com.dmeyc.dmestoreyfm.bean.response.ActivityDetailBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.wedgit.CommSharePopWin;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import io.rong.imkit.RongIM;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.message.TextMessage;

import static android.view.View.GONE;

/**
 * create by cxg on 2019/12/20
 */
public class ShareDialogHelper {
    // 分享活动
    public static void shareAction(Context context, final ActivityDetailBean.DataBean bean, final String activityIdStr, View iv_share, String shareUrl) {
        if (bean != null && !TextUtils.isEmpty(shareUrl)) {
            final int activityId = Integer.valueOf(activityIdStr);
            CommSharePopWin dd = new CommSharePopWin.Builder(context).setContent(bean.getActivity_name())
                    //Radius越大耗时越长,被图片处理图像越模糊
                    .setRadius(3).setTitle(bean.getStart_date() + "  " + bean.getActivity_address())
                    //设置居中还是底部显示
                    .setUrl(shareUrl)
                    .setshowAtLocationType(1)
                    .setShowCloseButton(true)
                    .setOutSideClickable(false)
                    .show(iv_share);

            dd.setOnCommShareClick(new CommSharePopWin.CommShareClickLisenter() {
                @Override
                public void onCOMMClick() {
                    TextMessage myTextMessage = null;
                    RoClodBean roClodBean = null;
                    if ("1".equals(bean.getIsPk())) {
                        if (TextUtils.isEmpty(bean.getIsSponser())) {

                        } else {
                            if ("1".equals(bean.getIsSponser())) {
                                myTextMessage = TextMessage.obtain("俱乐部" + bean.getGroup_name() + "要来砸场子，童靴们起来保家护群 ！！！----点击报名");
                                roClodBean = new RoClodBean();
                                roClodBean.setPkId(activityId);
                                roClodBean.setType("RC:pkMatched");
                            } else {
                                myTextMessage = TextMessage.obtain("俺们去打劫" + bean.getGroup_name() + "俱乐部，兄弟姐妹们冲啊 ！！！----点击报名");
                                roClodBean = new RoClodBean();
                                roClodBean.setActivityId(activityId);
                                roClodBean.setType("RC:pkMatched");
                            }
                        }
                    } else {
                        myTextMessage = TextMessage.obtain("欢迎大家报名新活动！----点击报名");
                        roClodBean = new RoClodBean();
                        roClodBean.setActivityId(activityId);
                        roClodBean.setType("RC:normalActivity");
                    }

                    Gson gs = new Gson();
                    myTextMessage.setExtra(gs.toJson(roClodBean));
                    Message myMessage = Message.obtain(bean.getGroup_id() + "", Conversation.ConversationType.GROUP, myTextMessage);
                    RongIM.getInstance().sendMessage(myMessage, null, null, new IRongCallback.ISendMessageCallback() {
                        @Override
                        public void onAttached(Message message) {
                            //消息本地数据库存储成功的回调
                            Logger.d(message.toString());
                        }

                        @Override
                        public void onSuccess(Message message) {
                            //消息通过网络发送成功的回调
                            ToastUtil.show("已分享");
                        }

                        @Override
                        public void onError(Message message, RongIMClient.ErrorCode errorCode) {
                            //消息发送失败的回调
                            Logger.d(message.toString() + "::" +errorCode.getValue());
                        }
                    });


                }
            });


        }
    }  // 分享直播H5、二维码
    public static void shareOthers(Context context, String content,String title, View iv_share, String shareUrl) {
        if (!TextUtils.isEmpty(shareUrl)) {
            CommSharePopWin dd = new CommSharePopWin.Builder(context).setContent(title)
                    //Radius越大耗时越长,被图片处理图像越模糊
                    .setRadius(3).setTitle(content)
                    //设置居中还是底部显示
                    .setUrl(shareUrl)
                    .setshowAtLocationType(1)
                    .setShowCloseButton(true)
                    .setOutSideClickable(false)
                    .show(iv_share);
            dd.setGroupShareShowVisibility(GONE);

        }
    }
}

