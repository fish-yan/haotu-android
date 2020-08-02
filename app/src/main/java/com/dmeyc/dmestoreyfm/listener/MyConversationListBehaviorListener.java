package com.dmeyc.dmestoreyfm.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.dmeyc.dmestoreyfm.ui.ActionItemActivity;
import com.dmeyc.dmestoreyfm.ui.ChartInforActivity;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;

import io.rong.imkit.RongIM;
import io.rong.imkit.model.UIConversation;
import io.rong.imlib.model.Conversation;

public class MyConversationListBehaviorListener implements RongIM.ConversationListBehaviorListener{
//    /**
//     * 当点击会话头像后执行。
//     *
//     * @param context          上下文。
//     * @param conversationType 会话类型。
//     * @param targetId         被点击的用户id。
//     * @return 如果用户自己处理了点击后的逻辑处理，则返回 true，否则返回 false，false 走融云默认处理方式。
//     */
//    boolean onConversationPortraitClick(Context context, Conversation.ConversationType conversationType, String targetId){
//
//    }
//
//    /**
//     * 当长按会话头像后执行。
//     *
//     * @param context          上下文。
//     * @param conversationType 会话类型。
//     * @param targetId         被点击的用户id。
//     * @return 如果用户自己处理了点击后的逻辑处理，则返回 true，否则返回 false，false 走融云默认处理方式。
//     */
//    boolean onConversationPortraitLongClick(Context context, Conversation.ConversationType conversationType, String targetId){
//        return false;
//    }

//    @Override
//    public boolean onConversationLongClick(Context context, View view, UIConversation uiConversation) {
//        return false;
//    }
//
//    /**
//     * 长按会话列表中的 item 时执行。
//     *
//     * @param context        上下文。
//     * @param view           触发点击的 View。
//     * @param uiConversation 长按时的会话条目。
//     * @return 如果用户自己处理了长按会话后的逻辑处理，则返回 true， 否则返回 false，false 走融云默认处理方式。
//     */
//    @Override
//    public boolean onConversationLongClick(Context context, View view, UIConversation uiConversation) {
//        return false;
//    }

    @Override
    public boolean onConversationPortraitClick(Context context, Conversation.ConversationType conversationType, String s) {
        context.startActivity(new Intent(context,ChartInforActivity.class).putExtra("group_id",Integer.parseInt(s)));

        return true;
    }

    @Override
    public boolean onConversationPortraitLongClick(Context context, Conversation.ConversationType conversationType, String s) {
        return false;
    }

    @Override
    public boolean onConversationLongClick(Context context, View view, UIConversation uiConversation) {
        return false;
    }

    /**
     * 点击会话列表中的 item 时执行。
     *
     * @param context        上下文。
     * @param view           触发点击的 View。
     * @param uiConversation 会话条目。
     * @return 如果用户自己处理了点击会话后的逻辑处理，则返回 true， 否则返回 false，false 走融云默认处理方式。
     */
    @Override
    public boolean onConversationClick(Context context, View view, UIConversation uiConversation) {
//        ToastUtil.show("我点击了");
        return false;
    }

}
