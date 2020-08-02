package com.dmeyc.dmestoreyfm.base;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;

import com.dmeyc.dmestoreyfm.bean.UserBean;
import com.dmeyc.dmestoreyfm.bean.YFMLoginBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.ui.ProductActivity;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.utils.Util;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.UserInfo;
import io.rong.message.RichContentMessage;


/**
 * Created by jockie on 2018/1/4
 * Email:jockie911@gmail.com
 */

public class BaseAppHelper {

    static {
//        PlatformConfig.setWeixin("wxeddf73cd8858ed6e", "fc3ba8fe8ee42a9ec5665b0481ca5721");
       PlatformConfig.setWeixin("wx82cdaef96e37dbeb", "ce148969d9ea9a78f2ad48189432ab18");

        PlatformConfig.setQQZone("1108954860", "U23qngqPr9Dczp3j");
//        PlatformConfig.setQQZone("101448357", "b5c2237ec8fb468c6e1b8823d54cc33a");
        PlatformConfig.setSinaWeibo("1748423728", "7ae7945a22c345815cedcca0497b8fdf", "http://sns.whalecloud.com");
    }

    public static void init(Context context){
        setFont(context);
        initUmeng(context);
        initBugly(context);
//        initRong(context);

    }

    /**
     * 初始化融云的相关信息
     * @param context
     */
    private static void initRong(final Context context) {
//        RongIM.getInstance().init(context);
        RongIM.init(context);
        ToastUtil.show("bbbbbbbbsss");
        if(Util.isLogin()){
            RongIM.connect(SPUtils.getStringData(Constant.Config.RC_TOKEN), new RongIMClient.ConnectCallback() {
                @Override
                public void onTokenIncorrect() {
                    ToastUtil.show("onTokenIncorrect");
                }

                @Override
                public void onSuccess(String s) {
                    ToastUtil.show("启动服务成功sss");
                }
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    ToastUtil.show("onError" + errorCode.ordinal());
                }
            });
        }

//        RestClient.getYfmNovate(context).post(Constant.API.YFM_USER_LOGIN, new ParamMap.Build()
//                .addParams(Constant.Config.MOBILE, SPUtils.getStringData(Constant.Config.MOBILE))
//                .build(), new DmeycBaseSubscriber<YFMLoginBean>() {
//            @Override
//            public void onSuccess(YFMLoginBean bean) {
//                final UserBean  user = bean.getData().getUser();
//                RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
//                    @Override
//                    public UserInfo getUserInfo(String s) {
//                        return new UserInfo(String.valueOf(user.getUserId()),user.getNickname(), Uri.parse(user.getAvatar()));
//                    }
//                },true);
//            }
//        });

//        RongIM.getInstance().setConversationBehaviorListener(new RongIM.ConversationBehaviorListener() {
//            @Override
//            public boolean onUserPortraitClick(Context context, Conversation.ConversationType conversationType, UserInfo userInfo) {
//                return false;
//            }
//            @Override
//            public boolean onUserPortraitLongClick(Context context, Conversation.ConversationType conversationType, UserInfo userInfo) {
//                return false;
//            }
//            @Override
//            public boolean onMessageClick(Context context, View view, Message message) {
//                if(message.getContent() instanceof RichContentMessage){
//                    RichContentMessage mes = (RichContentMessage)message.getContent();
//                    String extra = mes.getExtra();
//                    if(!TextUtils.isEmpty(extra)){
//                        Intent intent = new Intent(context, ProductActivity.class);
//                        intent.putExtra(Constant.Config.ID,Integer.parseInt(extra));
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        context.startActivity(intent);
//                    }
//                    return true;
//                }
//                return false;
//            }
//            @Override
//            public boolean onMessageLinkClick(Context context, String s) {
//                return false;
//            }
//
//            @Override
//            public boolean onMessageLongClick(Context context, View view, Message message) {
//                return false;
//            }
//        });
    }
    /**
     * 设置字体大小为默认尺寸，不跟随系统
     * @param context
     */
    private static void setFont(Context context) {
        Resources res = context.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
    }

    /**
     * 初始化bugly
     * @param context
     */
    private static void initBugly(Context context) {
        CrashReport.initCrashReport(context, "0ba34a390b", true);
    }

    /**
     * 初始化友盟
     * @param context
     */
    private static void initUmeng(Context context) {
        UMShareAPI.get(context);
        UMConfigure.init(context, Constant.Key.UMENG_KEY
                ,"umeng",UMConfigure.DEVICE_TYPE_PHONE,"");
//        UMConfigure.init(context, UMConfigure.DEVICE_TYPE_PHONE, "");
    }

}
