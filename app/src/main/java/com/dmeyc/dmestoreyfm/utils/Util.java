package com.dmeyc.dmestoreyfm.utils;

/**
 * 通用Util
 * Created by jockie on 2017/12/4
 * Email:jockie911@gmail.com
 */

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.base.BaseApp;
import com.dmeyc.dmestoreyfm.bean.LoginBean;
import com.dmeyc.dmestoreyfm.bean.YFMLoginBean;
import com.dmeyc.dmestoreyfm.bean.common.GoodsBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.dialog.LoginDialog;
import com.dmeyc.dmestoreyfm.ui.BrandDetailActivity;
import com.dmeyc.dmestoreyfm.ui.ProductActivity;
import com.dmeyc.dmestoreyfm.ui.WebviewActivity;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import io.rong.imkit.RongExtensionManager;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.UserInfo;
import io.rong.sight.SightExtensionModule;

/**
 * tablayout滑块宽度
 */
public class Util {


    /**
     * 关闭软键盘
     *
     * @param mEditText 输入框
     * @param mContext 上下文
     */
    public static void closeKeybord(EditText mEditText, Context mContext) {
        InputMethodManager imm = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
    }


    /**
     * 打开软键盘
     *
     * @param mEditText
     * @param mContext
     */
    public static void openKeybord(EditText mEditText, Context mContext) {
        InputMethodManager imm = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
                InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    /**
     * 判断当前软键盘是否打开
     *
     * @param activity
     * @return
     */
    public static boolean isSoftInputShow(Activity activity) {

        // 虚拟键盘隐藏 判断view是否为空
        View view = activity.getWindow().peekDecorView();
        if (view != null) {
            // 隐藏虚拟键盘
            InputMethodManager inputmanger = (InputMethodManager) activity
                    .getSystemService(Activity.INPUT_METHOD_SERVICE);
            return inputmanger.isActive() && activity.getWindow().getCurrentFocus() != null;
        }
        return false;
    }


    /**
     * TabLayout 满屏情况，一般是动态划分区间
     * @param mTabLayout 滑块的数量
     * @param tabCount
     */
    public static void reflexFix(final TabLayout mTabLayout,int tabCount){
        final int itemWidth = BaseApp.getWidth() / tabCount;
        //了解源码得知 线的宽度是根据 tabView的宽度来设置的
        mTabLayout.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //拿到tabLayout的mTabStrip属性
                    LinearLayout mTabStrip = (LinearLayout) mTabLayout.getChildAt(0);
                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);
                        //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
                        Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                        mTextViewField.setAccessible(true);
                        TextView mTextView = (TextView) mTextViewField.get(tabView);
                        tabView.setPadding(0, 0, 0, 0);
                        //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                        int width = 0;
                        width = mTextView.getWidth();
                        if (width == 0) {
                            mTextView.measure(0, 0);
                            width = mTextView.getMeasuredWidth();
                        }
                        //设置tab左右间距为10dp  注意这里不能使用Padding 因为源码中线的宽度是根据 tabView的宽度来设置的
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                        params.width = width ;

                        params.leftMargin = (itemWidth - width) / 2;
                        params.rightMargin = (itemWidth - width) / 2;

                        tabView.setLayoutParams(params);
                        tabView.invalidate();
                    }
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 设置tablayout的间距
     * @param mTabLayout
     * @param dpCount   1/2间距
     */
    public static void reflex(final TabLayout mTabLayout, final int dpCount){
        //了解源码得知 线的宽度是根据 tabView的宽度来设置的
        mTabLayout.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //拿到tabLayout的mTabStrip属性
                    LinearLayout mTabStrip = (LinearLayout) mTabLayout.getChildAt(0);
                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);
                        //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
                        Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                        mTextViewField.setAccessible(true);
                        TextView mTextView = (TextView) mTextViewField.get(tabView);
                        tabView.setPadding(0, 0, 0, 0);
                        //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                        int width = 0;
                        width = mTextView.getWidth();
                        if (width == 0) {
                            mTextView.measure(0, 0);
                            width = mTextView.getMeasuredWidth();
                        }
                        //设置tab左右间距为10dp  注意这里不能使用Padding 因为源码中线的宽度是根据 tabView的宽度来设置的
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                        params.width = width ;
                        if(i == 0){
                            params.rightMargin = dpCount;
                        }else if(i == mTabStrip.getChildCount() - 1){
                            params.leftMargin = dpCount;
                        }else{
                            params.leftMargin = dpCount;
                            params.rightMargin = dpCount;
                        }
                        tabView.setLayoutParams(params);
                        tabView.invalidate();
                    }
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void scaleReset(RelativeLayout relativeLayout){
        long duration = 400;
        float[] scale = new float[2];
        scale[0] = 0.9f;
        scale[1] = 1.0f;
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(relativeLayout, "scaleX", scale).setDuration(duration);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(relativeLayout, "scaleY", scale).setDuration(duration);
        float[] rotation = new float[]{0,0,0};
        ObjectAnimator rotationX = ObjectAnimator.ofFloat(relativeLayout, "rotationX", rotation).setDuration(duration);

        float[] translation = new float[1];
        translation[0] = 0;
        ObjectAnimator translationY = ObjectAnimator.ofFloat(relativeLayout, "translationY",translation).setDuration(duration);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleX,scaleY,rotationX,translationY);
        animatorSet.setTarget(relativeLayout);
        animatorSet.start();
    }

    public static void scaleStart(RelativeLayout relativeLayout){
        long duration = 400;
        float[] scale = new float[2];
        scale[0] = 1.0f;
        scale[1] = 0.9f;
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(relativeLayout, "scaleX", scale).setDuration(duration);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(relativeLayout, "scaleY", scale).setDuration(duration);
        float[] rotation = new float[]{0,0,0};
        ObjectAnimator rotationX = ObjectAnimator.ofFloat(relativeLayout, "rotationX", rotation).setDuration(duration);

        float[] translation = new float[1];
        translation[0] = -BaseApp.getWidth() * 0.1f / 2;
        ObjectAnimator translationY = ObjectAnimator.ofFloat(relativeLayout, "translationY",translation).setDuration(duration);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleX,scaleY,rotationX,translationY);
        animatorSet.setTarget(relativeLayout);
        animatorSet.start();
    }
    /**
     * 判断用户是否已经登陆
     * @return
     */
    public static boolean isLogin(){
        return SPUtils.getBooleanData(Constant.Config.ISLOGIN,false);
    }

    /**
     * 获取用户id
     * @return
     */
    public static String getUserId(){
        return SPUtils.getStringData(Constant.Config.USER_ID,"");
    }

    /**
     * 退出登陆,清楚账号信息
     */
    public static void exit() {
        SPUtils.savaBooleanData(Constant.Config.ISLOGIN,false);

    }

    public static boolean checkLoginStatus(Context mContext){
        if(!isLogin())
            new LoginDialog(mContext).show();
        return isLogin();
//        return true;
    }

    public static void startProductActivity(Context context, GoodsBean bean){
        Intent intent = new Intent(context, ProductActivity.class);
        if(!(context instanceof Activity)){
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        intent.putExtra(Constant.Config.ID,bean.getId());
//        intent.putExtra(Constant.Config.ITEM,bean);
        context.startActivity(intent);
    }

    public static void startProductActivity(Context context, int id){
        Intent intent = new Intent(context, ProductActivity.class);
        if(!(context instanceof Activity)){
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        intent.putExtra(Constant.Config.ID,id);
//        intent.putExtra(Constant.Config.ITEM,bean);
        context.startActivity(intent);
    }

    public static void startProductActivity(Context context, int id,String type){
        Intent intent = new Intent(context, ProductActivity.class);
        if(!(context instanceof Activity)){
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        intent.putExtra("type",type);
        intent.putExtra(Constant.Config.ID,id);
//        intent.putExtra(Constant.Config.ITEM,bean);
        context.startActivity(intent);
    }

    /**
     * 设计师详情界面
     * @param context
     * @param type
     * @param id
     */
    public static void startBrandDesignDetailActivity(Context context,int type,int id,int storid){
        Intent intent = new Intent(context, BrandDetailActivity.class);
        intent.putExtra(Constant.Config.TYPE,type);
        intent.putExtra(Constant.Config.ID,id);
        intent.putExtra(Constant.Config.STORY_ID,storid);
        if(!(context instanceof Activity))
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 打開不需要交互的webview
     * @param context
     * @param title
     * @param targetUrl
     */
    public static void startWebView(Context context,String title,String targetUrl){
        startWebView(context,title,targetUrl,WebviewActivity.class);
    }

    public static void startWebView(Context context,String title,String targetUrl,Class targetActivity){
        Intent intent = new Intent(context, targetActivity);
        intent.putExtra(Constant.Config.TITLE,title);
        intent.putExtra(Constant.Config.URL,targetUrl);
        if(!(context instanceof Activity))
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static boolean objEmpty(List list) {
        return list == null || list.size() == 0;
    }

    public static String MD5(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes(/*HTTP.UTF_8*/"UTF-8"));

            byte[] byteArray = messageDigest.digest();
            StringBuffer md5StrBuff = new StringBuffer();
            for (int i = 0; i < byteArray.length; i++) {
                if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                    md5StrBuff.append("0").append(
                            Integer.toHexString(0xFF & byteArray[i]));
                else
                    md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
            }
            return md5StrBuff.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取本地软件版本号
     */
    public static int getLocalVersion(Context ctx) {
        int localVersion = 0;
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }

    /**
     * 获取本地软件版本号名称
     */
    public static String getLocalVersionName(Context ctx) {
        String localVersion = "";
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }

    /**
     * 处理手机号 中间四位数字 -> ****
     * @param mobile
     * @return
     */
    public static String getBlurNumber(String mobile) {
        if(TextUtils.isEmpty(mobile))
            return "";
        if(mobile.length() < 11){
            return mobile;
        }
        return mobile.substring(0,3) + "****" + mobile.substring(7,11);
    }

    /**
     * 登录成功之后保存用户的信息
     * @param bean
     */
    public static void savaUserInfo(final LoginBean bean){
        SPUtils.savaStringData(Constant.Config.TOKEN,bean.getData().getToken().getValue());
        SPUtils.savaStringData(Constant.Config.RC_TOKEN,bean.getData().getServiceToken());
        SPUtils.savaStringData(Constant.Config.USER_ID,String.valueOf(bean.getData().getUser().getUserId()));
        SPUtils.savaStringData(Constant.Config.MOBILE,bean.getData().getUser().getMobile());
        SPUtils.savaStringData(Constant.Config.AVATAR,bean.getData().getUser().getAvatar());
        SPUtils.savaStringData(Constant.Config.NICK_NAME,bean.getData().getUser().getNickname());
        SPUtils.savaBooleanData(Constant.Config.ISLOGIN,true);

        RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
            @Override
            public UserInfo getUserInfo(String s) {
                UserInfo userInfo  = new UserInfo(String.valueOf(bean.getData().getUser().getUserId()),
                        bean.getData().getUser().getNickname(),
                        Uri.parse(bean.getData().getUser().getAvatar()));
                RongIM.getInstance().setMessageAttachedUserInfo(true);
                RongIM.getInstance().setCurrentUserInfo(userInfo);
                RongIM.getInstance().refreshUserInfoCache(userInfo);
                return userInfo;
            }
        },true);
    }


    public static void savaYFMUserInfo(final YFMLoginBean bean){
        SPUtils.savaStringData(Constant.Config.ROLECODE,bean.getData().getRole_code());
        SPUtils.savaStringData(Constant.Config.TOKEN,bean.getData().getToken_sys());
        SPUtils.savaStringData(Constant.Config.RC_TOKEN,bean.getData().getToken_third());
        SPUtils.savaStringData(Constant.Config.USER_ID,String.valueOf(bean.getData().getUser_id()));
        SPUtils.savaStringData(Constant.Config.AVATAR,bean.getData().getImg_url());
        SPUtils.savaStringData(Constant.Config.NICK_NAME,bean.getData().getNick_name());
        SPUtils.savaStringData(Constant.Config.SEX,bean.getData().getSex());
        SPUtils.savaBooleanData(Constant.Config.ISLOGIN,true);


        RongIM.connect(bean.getData().getToken_third(), new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                ToastUtil.show("启动服务失败");
            }
            @Override
            public void onSuccess(String s) {
//                RongExtensionManager.getInstance().registerExtensionModule(new SightExtensionModule());
                ToastUtil.show("启动服务成功");
                RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
                    @Override
                    public UserInfo getUserInfo(String s) {
                        System.out.print("111111111111111"+s);
                        UserInfo   userInfo= new UserInfo(String.valueOf(bean.getData().getUser_id()),
                                bean.getData().getNick_name(),
                                Uri.parse(bean.getData().getImg_url()));
                        RongIM.getInstance().setCurrentUserInfo(userInfo);
                        RongIM.getInstance().refreshUserInfoCache(userInfo);
                        return userInfo;
                    }
                },true);
            }
            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                ToastUtil.show("启动服务失败");
            }
        });

//        RongIM.getInstance().setMessageAttachedUserInfo(true);
    }

    public static void dynamicSetTabLayoutMode(TabLayout tabLayout) {
        int tabWidth = calculateTabWidth(tabLayout);
        int screenWidth = getScreenWith();

        if (tabWidth <= screenWidth) {
            tabLayout.setTabMode(TabLayout.MODE_FIXED);
        } else {
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        }
    }
    private static int calculateTabWidth(TabLayout tabLayout) {
        int tabWidth = 0;
        for (int i = 0; i < tabLayout.getChildCount(); i++) {
            final View view = tabLayout.getChildAt(i);
            view.measure(0, 0); // 通知父view测量，以便于能够保证获取到宽高
            tabWidth += view.getMeasuredWidth();
        }
        return tabWidth;
    }
    public static int getScreenWith() {
        return BaseApp.getContext().getResources().getDisplayMetrics().widthPixels;
    }

    public static boolean isNightMode() {
        SharedPreferences preferences = BaseApp.getContext().getSharedPreferences(
                Constant.SHARES_COLOURFUL_NEWS, Activity.MODE_PRIVATE
        );
        return preferences.getBoolean(Constant.NIGHT_THEME_MODE, false);
    }
}
