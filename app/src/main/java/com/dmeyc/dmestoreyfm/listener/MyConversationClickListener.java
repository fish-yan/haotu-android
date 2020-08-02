package com.dmeyc.dmestoreyfm.listener;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.dmeyc.dmestoreyfm.bean.RoClodBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.newui.home.groupaction.actiondetail.ActionDetailActivity;
import com.dmeyc.dmestoreyfm.newui.home.groupaction.matchdetail.MatchDetailActivity;
import com.dmeyc.dmestoreyfm.ui.ActionItemActivity;
import com.dmeyc.dmestoreyfm.ui.BClientPKdetail;
import com.dmeyc.dmestoreyfm.ui.BigActionInforActivity;
import com.dmeyc.dmestoreyfm.ui.CommActionShowActivity;
import com.dmeyc.dmestoreyfm.ui.MainActivity;
import com.dmeyc.dmestoreyfm.ui.NewPkResultActivity;
import com.dmeyc.dmestoreyfm.ui.PkResultActivity;
import com.dmeyc.dmestoreyfm.utils.DataClass;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.google.gson.Gson;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.UserInfo;

public  class MyConversationClickListener implements RongIM.ConversationClickListener {

/**
 * 当点击用户头像后执行。
 *
 * @param context          上下文。
 * @param conversationType 会话类型。
 * @param user             被点击的用户的信息。
 * @param targetId         会话 id
 * @return 如果用户自己处理了点击后的逻辑处理，则返回 true，否则返回 false，false 走融云默认处理方式。
 */
@Override
public boolean onUserPortraitClick(Context context, Conversation.ConversationType conversationType, UserInfo user, String targetId) {
        return false;
        }

/**
 * 当长按用户头像后执行。
 *
 * @param context          上下文。
 * @param conversationType 会话类型。
 * @param user             被点击的用户的信息。
 * @param targetId         会话 id
 * @return 如果用户自己处理了点击后的逻辑处理，则返回 true，否则返回 false，false 走融云默认处理方式。
 */
@Override
public boolean onUserPortraitLongClick(Context context, Conversation.ConversationType conversationType, UserInfo user, String targetId) {
        return false;
        }

/**
 * 当点击消息时执行。
 *
 * @param context 上下文。
 * @param view    触发点击的 View。
 * @param message 被点击的消息的实体信息。
 * @return 如果用户自己处理了点击后的逻辑处理，则返回 true， 否则返回 false, false 走融云默认处理方式。
 */
@Override
public boolean onMessageClick(Context context, View view, Message message) {
//    System.out.print( message.getContent().get+"555555555555555555555");
   /* if("RC:FileMsg".equals(message.getObjectName())){
        return true;
    }else */if("RC:TxtMsg".equals(message.getObjectName())){
        String cont=message.getContent().toString();
        String  []consts= cont.split("'");
        if(consts.length>=4){
            String extr=consts[3];
            if(!TextUtils.isEmpty(extr)){
                Gson gson=new Gson();
                RoClodBean roClodBean=  gson.fromJson(extr, RoClodBean.class);
                if(roClodBean!=null&&"RC:challengeInfo".equals(roClodBean.getType())){
                    SPUtils.savaStringData(Constant.Config.IDENITY,"1");
                    context.startActivity(new Intent(context, MainActivity.class).putExtra("ischat",1));
                }else if(roClodBean!=null&&"RC:signedInfo".equals(roClodBean.getType())){
                    context.startActivity(new Intent(context, ActionItemActivity.class).putExtra("activityid", roClodBean.getActivityId()));
                }else if(roClodBean!=null&&"RC:pkMatched".equals(roClodBean.getType())){
//                    context.startActivity(new Intent(context, ActionItemActivity.class).putExtra("activityid", roClodBean.getActivityId()));
                    MatchDetailActivity.newInstance(context, roClodBean.getActivityId()+"");
                }else if(roClodBean!=null&&"RC:pkScore".equals(roClodBean.getType())){
                    context.startActivity(new Intent(context, NewPkResultActivity.class).putExtra("activityid", roClodBean.getActivityId()));
                }else if(roClodBean!=null&&"RC:normalScore".equals(roClodBean.getType())){
                    context.startActivity(new Intent(context, BClientPKdetail.class).putExtra("activityid",roClodBean.getActivityId()));
//                    context.startActivity(new Intent(context,CulbResultActivity.class).putExtra("activityid",roClodBean.getActivityId()));
                }else if(roClodBean!=null&&"RC:challengeApply".equals(roClodBean.getType())){
                    context.startActivity(new Intent(context, PkResultActivity.class)
//                            .putExtra("ispked",roClodBean.getIsPked())
                            .putExtra("PK_groupid",roClodBean.getPkId())
                            .putExtra("headurl",roClodBean.getGroupLogo())
                            .putExtra("graopname",roClodBean.getGroupName()));
                }else if(roClodBean!=null&&"RC:normalActivity".equals(roClodBean.getType())){
//                    context.startActivity(new Intent(context, ActionItemActivity.class).putExtra("activityid", roClodBean.getActivityId()));
                    ActionDetailActivity.newInstance(context,roClodBean.getActivityId()+"");
                }else if(roClodBean!=null&&"RC:memberAdd".equals(roClodBean.getType())){
//                    if("1".equals(DataClass.commidinity)){
//                        context.startActivity(new Intent(context,BClientChangeSetActivity.class).putExtra("activityid",roClodBean.getActivityId()));
//                    }else {
                        context.startActivity(new Intent(context, CommActionShowActivity.class).putExtra("activityid",roClodBean.getActivityId()));


//                    }
                }else if(roClodBean!=null&&"RC:governmentGroupMessage".equals(roClodBean.getType())){
                    context. startActivity(new Intent(context, BigActionInforActivity.class).putExtra("activityid",  roClodBean.getActivityId()));
//                    }
                }
            }
        }
    }
//    Intent intent = new Intent(Intent.ACTION_VIEW);
//    intent.addCategory("android.intent.category.DEFAULT");
//    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//    Uri uri = UriUtil.getUriForFile(BitZApplication.mContext.get(), new File((String) msg.obj));
//    intent.setDataAndType(uri, "application/vnd.android.package-archive");
//    startActivity(intent);
        return false;
    }

/**
 * 当点击链接消息时执行。
 *
 * @param context 上下文。
 * @param link    被点击的链接。
 * @param message 被点击的消息的实体信息
 * @return 如果用户自己处理了点击后的逻辑处理，则返回 true， 否则返回 false, false 走融云默认处理方式。
 */
@Override
public boolean onMessageLinkClick(Context context, String link, Message message) {
//     String extr=   message.getExtra();
        ToastUtil.show("xiaoxidianji");
        return true;
        }
/**
 * 当长按消息时执行。
 *
 * @param context 上下文。
 * @param view    触发点击的 View。
 * @param message 被长按的消息的实体信息。
 * @return 如果用户自己处理了长按后的逻辑处理，则返回 true，否则返回 false，false 走融云默认处理方式。
 */
@Override
public boolean onMessageLongClick(Context context, View view, Message message) {
    DataClass.message=null;
//    if("RC:ImgMsg".equals(message.getObjectName())||"RC:SightMsg".equals(message.getObjectName())){
        DataClass.message=message;
//    }
        return false;
        }
  }
