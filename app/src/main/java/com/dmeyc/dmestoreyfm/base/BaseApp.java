package com.dmeyc.dmestoreyfm.base;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.alibaba.fastjson.JSONObject;
import com.dmeyc.dmestoreyfm.adapter.ImConnectionLister;
import com.dmeyc.dmestoreyfm.api.RetrofitService;
//import com.dmeyc.dmestoreyfm.bean.Match;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.constant.SPKey;
import com.dmeyc.dmestoreyfm.event.RefreshEvent;
import com.dmeyc.dmestoreyfm.listener.MyConversationClickListener;
import com.dmeyc.dmestoreyfm.listener.MyConversationListBehaviorListener;
import com.dmeyc.dmestoreyfm.listener.YFMGroupInfoProvider;
import com.dmeyc.dmestoreyfm.newbase.MyActivityLifecycleCallbacks;
import com.dmeyc.dmestoreyfm.receiver.CustomizeMessage;
import com.dmeyc.dmestoreyfm.receiver.CustomizeMessageItemProvider;
import com.dmeyc.dmestoreyfm.utils.ApplicationContextUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.utils.Util;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.orhanobut.logger.Logger;
import com.tencent.ugc.TXUGCBase;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.Map;
//
//import cn.bmob.v3.Bmob;
//import cn.bmob.v3.BmobQuery;
//import cn.bmob.v3.exception.BmobException;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.MessageContent;
import io.rong.imlib.model.UserInfo;
import io.rong.message.TextMessage;
import io.rong.push.RongPushClient;
import io.rong.push.platform.hms.HMSAgent;
import io.rong.push.pushconfig.PushConfig;

/**
 * Created by jockie on 2017/8/31
 * Email:jockie911@gmail.com
 */

public class BaseApp extends MultiDexApplication {

    String ugcLicenceUrl = "http://license.vod2.myqcloud.com/license/v1/cd9c391e7527cf151be418f0ed343bcf/TXUgcSDK.licence"; //您从控制台申请的 licence url
    String ugcKey = "e03f77f7a109114a7d27d42654030291";

    // 是否是debug模式
    public static boolean isDebug = false;
    NotificationManager mNotificationManager;

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        registerActivityLifecycleCallbacks(new MyActivityLifecycleCallbacks());
        init();
        ApplicationContextUtil.init(this);
        final PushConfig config = new PushConfig.Builder()
//                .enableMiPush("", "") //配置小米推送
                .enableHWPush(true)  // 配置华为推送
                .build();
        RongPushClient.setPushConfig(config);
        HMSAgent.init(this);
        RongIM.init(context);
//        ToastUtil.show("初始化");
        if (Util.isLogin()) {
            String token = SPUtils.getStringData(Constant.Config.RC_TOKEN);
            RongIM.connect(token, new RongIMClient.ConnectCallback() {
                @Override
                public void onTokenIncorrect() {
                    ToastUtil.show("onTokenIncorrect");
                }

                @Override
                public void onSuccess(String s) {
//                    RongExtensionManager.getInstance().registerExtensionModule(new SightExtensionModule());
//                    RongIM.getInstance().registerConversationTemplate(new MyPrivateConversationProvider());
                    RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
                        @Override
                        public UserInfo getUserInfo(String s) {
                            Logger.d("getUserInfo>>>>" + s);
                            System.out.print("111111111111111" + s);
                            UserInfo userInfo = new UserInfo(String.valueOf(SPUtils.getStringData(Constant.Config.USER_ID)),
                                    SPUtils.getStringData(Constant.Config.NICK_NAME),
                                    Uri.parse(SPUtils.getStringData(Constant.Config.AVATAR)));
                            RongIM.getInstance().setCurrentUserInfo(userInfo);
                            RongIM.getInstance().refreshUserInfoCache(userInfo);
                            return userInfo;
                        }
                    }, true);
                    RongIM.getInstance().setMessageAttachedUserInfo(true);
                }

                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    ToastUtil.show("onError" + errorCode.ordinal());
                }
            });
        }
//        RongIM.registerMessageType(SightMessage.class);
//        RongIM.registerMessageTemplate(new SightMessageItemProvider());
        RongIM.registerMessageType(CustomizeMessage.class);
        RongIM.registerMessageTemplate(new CustomizeMessageItemProvider());
        RongIM.setConversationListBehaviorListener(new MyConversationListBehaviorListener());
        RongIM.getInstance().setConversationClickListener(new MyConversationClickListener());
        RongIM.getInstance().setSendMessageListener(new RongIM.OnSendMessageListener() {
            @Override
            public Message onSend(Message message) {
//                ToastUtil.show("sss");
                return message;
            }

            @Override
            public boolean onSent(Message message, RongIM.SentMessageErrorCode sentMessageErrorCode) {
//                ToastUtil.show("sss");
                return true;
            }
        });
        RongIM.setGroupInfoProvider(new YFMGroupInfoProvider(getContext()), true);
        RongIM.setOnReceiveMessageListener(new RongIMClient.OnReceiveMessageListener() {
            @Override
            public boolean onReceived(Message message, int i) {
                MessageContent content = message.getContent();
                if (content!=null){
                    if (content instanceof TextMessage){
                        String extra = ((TextMessage) content).getExtra();
                        parseRYMessage(extra);
                    }
                }

                SPUtils.savaBooleanData(SPKey.PUSH_HAS_INFO,true);
                EventBus.getDefault().post(new RefreshEvent.PUSH_NEW_INFO());
//                SPUtils.savaStringData(Constant.Config.HAS_REDPOS, "1");
//                message.getExtra();
//                Intent intent = new Intent("send_mess");
//                sendBroadcast(intent);
                return false;
            }
        });
        RongIMClient.setConnectionStatusListener(new ImConnectionLister(context));
        BaseAppHelper.init(context);

        TXUGCBase.getInstance().setLicence(this, ugcLicenceUrl, ugcKey);
    }

    private void parseRYMessage(String extra){
        try {
            Map params = (Map) JSONObject.parse(extra);
            String type = (String) params.get("type");
            if (type == null){
                return;
            }
            switch (type) {
                case "RC:User_At":
                    SPUtils.savaIntData(SPKey.PUSH_AT, SPUtils.getIntData(SPKey.PUSH_AT) + 1);
                    break;
                case "RC:User_Like":
                    SPUtils.savaIntData(SPKey.PUSH_LIKE, SPUtils.getIntData(SPKey.PUSH_LIKE) + 1);
                    break;
                case "RC:User_Follower":
                    SPUtils.savaIntData(SPKey.PUSH_FOLLOW, SPUtils.getIntData(SPKey.PUSH_FOLLOW) + 1);
                    break;
                case "RC:UserComment":
                    SPUtils.savaIntData(SPKey.PUSH_COMMEND, SPUtils.getIntData(SPKey.PUSH_COMMEND) + 1);
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void init() {
        RetrofitService.getInstance().init();
    }

    /**
     * 解决android 方法数超过65K限制
     *
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static Context getContext() {
        return context;
    }

    public static int getWidth() {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getHeight() {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public void initImageloader() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
//                .showImageOnLoading(R.drawable.ic_picture_loading)
//                .showImageOnFail(R.drawable.ic_picture_loadfailed)
                .resetViewBeforeLoading(false) // default
                .delayBeforeLoading(0).cacheInMemory(true) // default
                .cacheOnDisk(true) // default
                .considerExifParams(true) // default
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // default
                .bitmapConfig(Bitmap.Config.ARGB_8888) // default
                .displayer(new SimpleBitmapDisplayer()) // default
                .handler(new Handler()) // default
                .build();

        File picPath = new File(Environment.getExternalStorageDirectory().getPath() + File.separator + "MyApp"
                + File.separator + "files");
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .memoryCacheExtraOptions(480, 800)
                // default = device screen dimensions
                .diskCacheExtraOptions(480, 800, null).threadPoolSize(3)
                // default
                .threadPriority(Thread.NORM_PRIORITY - 1)
                // default
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                // default
                .denyCacheImageMultipleSizesInMemory().memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                .memoryCacheSize(2 * 1024 * 1024).memoryCacheSizePercentage(13)
                // default
//                .diskCache(new UnlimitedDiskCache(picPath))
                // default
                .diskCacheSize(50 * 1024 * 1024).diskCacheFileCount(1000)
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator())
                // default
                .imageDownloader(new BaseImageDownloader(getApplicationContext())) // default
                .imageDecoder(new BaseImageDecoder(true)) // default
                .defaultDisplayImageOptions(options) // default
                .writeDebugLogs().build();
        ImageLoader.getInstance().init(config);
    }

    private void doing() {
//        Bmob.initialize(this, "77088ce1637804d97be523f42e14d844");
//
//        BmobQuery<Match> bmobQuery = new BmobQuery<Match>();
//        bmobQuery.getObject("fZG6bbbd",new QueryListener<Match>() {
//            @Override
//            public void done(Match object, BombException e) {
//            }
//
//        });
//        bmobQuery.getObject("6b6c11c537", new QueryListener < Match > ()
//
//        {
//            @Override
//            public void done (Match object, BmobException e){
//            if (e == null) {
//                toast("查询成功");
//            } else {
//                toast("查询失败：" + e.getMessage());
//            }
//        }
//        });
    }

}
