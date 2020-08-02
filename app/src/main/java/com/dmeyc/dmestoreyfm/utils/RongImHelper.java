package com.dmeyc.dmestoreyfm.utils;

import com.orhanobut.logger.Logger;

import io.rong.imkit.RongIM;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.message.TextMessage;

/**
 * create by cxg on 2019/12/24
 */
public class RongImHelper {
    public static void sendGroupMessage(String groupId, String message, final ICallBack iCallBack) {
        TextMessage myTextMessage = TextMessage.obtain(message);
        Message myMessage = Message.obtain(groupId, Conversation.ConversationType.GROUP, myTextMessage);
        RongIM.getInstance().sendMessage(myMessage, null, null, new IRongCallback.ISendMessageCallback() {
            @Override
            public void onAttached(Message message) {
                //消息本地数据库存储成功的回调
                Logger.d(message.toString());
                if (iCallBack!=null){
                    iCallBack.onAttached(message);
                }
            }

            @Override
            public void onSuccess(Message message) {
                //消息通过网络发送成功的回调
                if (iCallBack!=null){
                    iCallBack.onSuccess(message);
                }
            }

            @Override
            public void onError(Message message, RongIMClient.ErrorCode errorCode) {
                //消息发送失败的回调
                Logger.d(message.toString() + "::" + errorCode.getValue());
                if (iCallBack!=null){
                    iCallBack.onError(message,errorCode);
                }
            }
        });
    }

    public interface ICallBack {
        void onAttached(Message message);

        void onSuccess(Message message);

        void onError(Message message, RongIMClient.ErrorCode errorCode);
    }

    public static class CallBackAdapter implements ICallBack {

        @Override
        public void onAttached(Message message) {

        }

        @Override
        public void onSuccess(Message message) {

        }

        @Override
        public void onError(Message message, RongIMClient.ErrorCode errorCode) {

        }
    }


}
