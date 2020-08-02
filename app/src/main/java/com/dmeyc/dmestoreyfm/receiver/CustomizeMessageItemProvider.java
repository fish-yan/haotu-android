package com.dmeyc.dmestoreyfm.receiver;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;

import io.rong.imkit.emoticon.AndroidEmoji;
import io.rong.imkit.model.ProviderTag;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.widget.provider.IContainerItemProvider;
import io.rong.imlib.model.Message;

@ProviderTag(messageContent = CustomizeMessage.class)
public class CustomizeMessageItemProvider extends IContainerItemProvider.MessageProvider<CustomizeMessage> {

    class ViewHolder {
        TextView message;
    }

    @Override
    public View newView(Context context, ViewGroup group) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_messagelayout, null);
//        View view = LayoutInflater.from(context).inflate(io.rong.imkit.R.layout.item_customize_message, null);
        ViewHolder holder = new ViewHolder();
        holder.message = (TextView) view.findViewById(android.R.id.text1);
        view.setTag(holder);
        return view;
    }

//    @Override
//    public void bindView(View v, int position, CustomizeMessage content, Message message) {
//        ViewHolder holder = (ViewHolder) v.getTag();
//
//        if (message.getMessageDirection() == Message.MessageDirection.SEND) {//消息方向，自己发送的
//            holder.message.setBackgroundResource(io.rong.imkit.R.drawable.rc_ic_bubble_right);
//        } else {
//            holder.message.setBackgroundResource(io.rong.imkit.R.drawable.rc_ic_bubble_left);
//        }
//        holder.message.setText(content.getContent());
//        AndroidEmoji.ensure((Spannable) holder.message.getText());//显示消息中的 Emoji 表情。
//    }

    @Override
    public void bindView(View view, int i, CustomizeMessage customizeMessage, UIMessage uiMessage) {
        ViewHolder holder = (ViewHolder) view.getTag();
        Message message=  uiMessage.getMessage();
        if (message.getMessageDirection() == Message.MessageDirection.SEND) {//消息方向，自己发送的
            holder.message.setBackgroundResource(io.rong.imkit.R.drawable.rc_ic_bubble_right);
        } else {
            holder.message.setBackgroundResource(io.rong.imkit.R.drawable.rc_ic_bubble_left);
        }
        holder.message.setText(customizeMessage.getUserInfo().getExtra());
        AndroidEmoji.ensure((Spannable) holder.message.getText());//显示消息中的 Emoji 表情。
    }

    @Override
    public Spannable getContentSummary(CustomizeMessage data) {
        return new SpannableString("这是一条自定义消息CustomizeMessage");
    }

    @Override
    public void onItemClick(View view, int i, CustomizeMessage customizeMessage, UIMessage uiMessage) {
        ToastUtil.show("wobei");
    }

//    @Override
//    public void onItemClick(View view, int position, CustomizeMessage content, Message message) {
//
//    }

//    @Override
//    public void onItemLongClick(View view, int position, CustomizeMessage content, Message message) {
//
//    }

}
