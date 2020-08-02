package com.dmeyc.dmestoreyfm.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;

import io.rong.imkit.RongIM;
import io.rong.imkit.model.ConversationProviderTag;
import io.rong.imkit.model.UIConversation;
import io.rong.imkit.widget.provider.PrivateConversationProvider;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Group;
import io.rong.imlib.model.UserInfo;


@ConversationProviderTag(conversationType = "private", portraitPosition = 2)
public class MyPrivateConversationProvider extends PrivateConversationProvider {

    @Override
    public View newView(Context context, ViewGroup group) {
        return super.newView(context, group);
    }


    @Override
    public void bindView(View v, int position, UIConversation data) {
        if (data.getConversationType().equals(Conversation.ConversationType.GROUP)) {
            data.setUnreadType(UIConversation.UnreadRemindType.REMIND_ONLY);
            //设置会话发送者ID、会话标题、会话头像URL
            String targetid = data.getConversationTargetId();
//            ConsultationActivity consultationActivity = new ConsultationActivity();
//            consultationActivity.getInfoOfCreateGroupUser(targetid);

//            if(ClientMemberList!=null&&ClientMemberList.size()!=0){
//                data.setIconUrl(Uri.parse(ClientMemberList.get(position).getHeadPortrait()+""));
//                data.setUIConversationTitle(ClientMemberList.get(position).getUserName());
//                RongIM.getInstance().refreshGroupInfoCache(new Group(targetid + "", ClientMemberList.get(position).getUserName(),
//                        Uri.parse(ClientMemberList.get(position).getHeadPortrait()+"")));
//                /**
//                 * 刷新用户缓存数据。
//                 *
//                 * @param userInfo 需要更新的用户缓存数据。
//                 */
//                RongIM.getInstance().refreshUserInfoCache(
//                        new UserInfo(ClientMember.getUserId() + "",
//                                ClientMember.getUserName(), Uri.parse(ClientMember.getHeadPortrait()+"")));
//            }
        }
        super.bindView(v, position, data);
    }

}
