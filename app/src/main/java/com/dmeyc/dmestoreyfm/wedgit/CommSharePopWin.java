package com.dmeyc.dmestoreyfm.wedgit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.annotation.UiThread;

import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.CustomizeMessage;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.ui.SharePicInActivity;
import com.dmeyc.dmestoreyfm.utils.FastBlur;
import com.dmeyc.dmestoreyfm.utils.ScreenUtil;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.editorpage.ShareActivity;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import io.rong.imkit.RongIM;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.MessageContent;
import io.rong.message.TextMessage;


/**
 * Created by jiajiewang on 16/7/30.
 */
public class CommSharePopWin {
    private RelativeLayout pop_root_layout;
    private CardView pop_layout;
    private TextView title;
    private TextView content;
    private TextView close;
    private Builder builder;
    private PopupWindow popupWindow;
    private int radius;
    private float touchY;
    private Bitmap localBit;

    public static String titles, shareurl, logo, groupid;
    public static String contents;

    protected static Context context;
    public static final String GRAVITY_BOTTOM = "BOTTOM";
    public static final String GRAVITY_CENTER = "CENTER";

    public CommSharePopWin(Builder builder) {
        this.builder = builder;
        builder.blurPopWin = initBlurPopWin(builder);
    }

    @UiThread
    public void show(View view) {
//        builder.blurPopWin.popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        builder.blurPopWin.popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, -40);
    }

    @UiThread
    public void dismiss() {
        if (builder != null && builder.blurPopWin != null)
            builder.blurPopWin.popupWindow.dismiss();
    }

    /*
    截取屏幕
    * */
    @Nullable
    private Bitmap getIerceptionScreen() {
        // View是你需要截图的View
        View view = builder.activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap b = view.getDrawingCache();

        // 获取状态栏高度
        Rect frame = new Rect();
        builder.activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;

        // 获取屏幕长和高
        int width = builder.activity.getWindowManager().getDefaultDisplay().getWidth();
        int height = builder.activity.getWindowManager().getDefaultDisplay()
                .getHeight();
        // 去掉标题栏
        // Bitmap b = Bitmap.createBitmap(b1, 0, 25, 320, 455);
        Bitmap bitmap = Bitmap.createBitmap(b, 0, statusBarHeight, width, height
                - statusBarHeight);
        view.destroyDrawingCache();
        bitmap = FastBlur.fastBlur(bitmap, radius);
        if (bitmap != null) {
            return bitmap;
        } else {
            return null;
        }
    }


    public void setGroupShareShowVisibility(int type) {
        if (ll_h5share != null) {
            ll_h5share.setVisibility(type);
        }
    }

    /*
    初始化
    * */
    LinearLayout ll_wechatbur, ll_wxciclebur, ll_qqblur, ll_sina, ll_h5share;

    @UiThread
    private CommSharePopWin initBlurPopWin(final Builder builder) {
        if (builder != null) {

            View rootView = builder.activity.getLayoutInflater().inflate(R.layout.pop_commshare, null, false);
            popupWindow = new PopupWindow(rootView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
            pop_layout = (CardView) rootView.findViewById(R.id.pop_layout);
            pop_root_layout = (RelativeLayout) rootView.findViewById(R.id.pop_root_layout);
//            title = (TextView) rootView.findViewById(R.id.title);
//            content = (TextView) rootView.findViewById(R.id.content);
            close = (TextView) rootView.findViewById(R.id.close);
            ll_wechatbur = (LinearLayout) rootView.findViewById(R.id.ll_wechatbur);
            ll_wxciclebur = (LinearLayout) rootView.findViewById(R.id.ll_wxciclebur);
            ll_qqblur = (LinearLayout) rootView.findViewById(R.id.ll_qqblur);
            ll_sina = (LinearLayout) rootView.findViewById(R.id.ll_sina);
            ll_h5share = rootView.findViewById(R.id.ll_h5share);

            ll_h5share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    commShareClickLisenter.onCOMMClick();
                    popupWindow.dismiss();

                }
            });
            ll_wechatbur.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    share(SHARE_MEDIA.WEIXIN, contents);
                }
            });
            ll_wxciclebur.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    share(SHARE_MEDIA.WEIXIN_CIRCLE, "share");
                }
            });
            ll_qqblur.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    share(SHARE_MEDIA.QQ, "share");
                }
            });
            ll_sina.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    share(SHARE_MEDIA.QZONE, "share");
                }
            });
//            if (builder.title != null) {
//                title.setText(builder.title);
//            }

//            if (builder.content != null) {
//                content.setText(builder.content);
//            }

            if (builder.radius != 0) {
                radius = builder.radius;
            } else {
                radius = 5;
            }

            if (builder.titleTextSize != 0) {
                title.setTextSize(builder.titleTextSize);
            }

            if (builder.contentTextSize != 0) {
                content.setTextSize(builder.contentTextSize);
            }

            if (builder.isShowClose) {
                close.setVisibility(View.VISIBLE);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();
                    }
                });
            } else {
                close.setClickable(false);
                close.setVisibility(View.INVISIBLE);
            }

            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtil.dp2px(context, 260));
            if (builder.showAtLocationType.equals(GRAVITY_CENTER)) {
                lp.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
            } else {
                lp.bottomMargin = 16;
                lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
            }
            pop_layout.setLayoutParams(lp);

            if (localBit == null) {
                localBit = getIerceptionScreen();
            }

            pop_root_layout.setBackground(new BitmapDrawable(localBit));
            pop_root_layout.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    switch (motionEvent.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            touchY = motionEvent.getY();
                            break;
                        case MotionEvent.ACTION_MOVE:
                            break;
                        case MotionEvent.ACTION_UP:

                            if (builder.isBackgroundClose) {
                                if (builder.showAtLocationType.equals(GRAVITY_CENTER)) {
                                    if (touchY < pop_layout.getTop() || touchY > pop_layout.getBottom()) {
                                        popupWindow.dismiss();
                                    }
                                } else if (builder.showAtLocationType.equals(GRAVITY_BOTTOM)) {
                                    if (touchY < pop_layout.getTop()) {
                                        popupWindow.dismiss();
                                    }
                                }
                            }
                            break;
                        default:
                            break;
                    }
                    return true;
                }
            });
        } else {
            throw new NullPointerException("---> BlurPopWin ---> initBlurPopWin --->builder=null");
        }
        pop_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (builder.popupCallback != null) {
                    builder.popupCallback.onClick(CommSharePopWin.this);
                }
            }
        });
        return this;
    }

    public static class Builder {

        protected CommSharePopWin blurPopWin;
        protected int titleTextSize, contentTextSize;
        protected Activity activity;

        protected PopupCallback popupCallback;
        protected int radius;
        //        protected String title, contents;
        protected boolean isCancelable;
        //默认不显示XX
        protected boolean isShowClose = false;
        protected boolean isBackgroundClose = true;
        protected String showAtLocationType = GRAVITY_CENTER;

        public Builder(@NonNull Context contex) {
            this.activity = (Activity) contex;
            context = contex;
            this.isCancelable = true;
        }

        public Builder onClick(PopupCallback popupCallback) {
            this.popupCallback = popupCallback;
            return this;
        }

        /*
         * 设置标题
         * */
        public Builder setTitle(@StringRes int titleRes) {
            setTitle(context.getString(titleRes));
            return this;
        }


        public Builder setTitle(@NonNull String title) {
            titles = title;
            return this;
        }

        public Builder setRadius(int radius) {
            this.radius = radius;
            return this;
        }

        public Builder setUrl(String url) {
            shareurl = url;
            return this;
        }

        public Builder setlogo(String log) {
            logo = log;
            return this;
        }

        public Builder setid(String groupi) {
            groupid = groupi;
            return this;
        }

        public Builder setTitleTextSize(int size) {
            this.titleTextSize = size;
            return this;
        }

        public Builder setContentTextSize(int size) {
            this.contentTextSize = size;
            return this;
        }

        /*
         * 设置主文内容
         * */
        public Builder setContent(@StringRes int contentRes) {
            setContent(context.getString(contentRes));
            return this;
        }

        /*
         * 设置主文内容
         * */
        public Builder setContent(@NonNull String content) {
            contents = content;
            return this;
        }

        /*
         * 默认居中,手动设置了才在最下面
         * */
        public Builder setshowAtLocationType(int type) {
            if (type == 0) {
                this.showAtLocationType = GRAVITY_CENTER;
            } else if (type == 1) {
                this.showAtLocationType = GRAVITY_BOTTOM;
            }

            return this;
        }

        public Builder setShowCloseButton(@NonNull boolean flag) {
            this.isShowClose = flag;
            return this;
        }

        public Builder setOutSideClickable(@NonNull boolean flag) {
            this.isBackgroundClose = flag;
            return this;
        }

        @UiThread
        public CommSharePopWin build() {
            return new CommSharePopWin(this);
        }

        @UiThread
        public CommSharePopWin show(View view) {
            CommSharePopWin blurPopWin = build();
            blurPopWin.show(view);

            return blurPopWin;
        }

    }

    public interface PopupCallback {
        void onClick(@NonNull CommSharePopWin blurPopWin);
    }

    private void share(SHARE_MEDIA plat, String content) {
        if (isShareImage(plat)){
            return;
        }

        UMWeb web = new UMWeb(shareurl);
        web.setTitle(contents);//标题
//        web.setThumb(new UMImage(context,logo));
        web.setThumb(new UMImage(context, R.drawable.yfm_logo));
        //缩略图
        web.setDescription(titles);//描述
        new ShareAction((Activity) context)
                .setPlatform(plat)
                .setCallback(umShareListener)
                .withMedia(web)
                .share();
    }

    private boolean isShareImage(SHARE_MEDIA plat){
        if (TextUtils.isEmpty(titles) && TextUtils.isEmpty(contents)){
            UMImage image = new UMImage(context, shareurl);
            image.setThumb(new UMImage(context, R.drawable.yfm_logo));
            new ShareAction((Activity) context)
                    .setPlatform(plat)
                    .setCallback(umShareListener)
                    .withMedia(image)
                    .share();
            return true;
        }
        return false;
    }


    private UMShareListener umShareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(context, "分享成功", Toast.LENGTH_SHORT).show();
            if (iShare != null) {
                iShare.onShareSuccess();
            }
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(context, "失败" + t.getMessage(), Toast.LENGTH_SHORT).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(context, "取消了", Toast.LENGTH_SHORT).show();

        }
    };
    public CommShareClickLisenter commShareClickLisenter;

    public void setOnCommShareClick(CommShareClickLisenter commShareClickLisenter) {
        this.commShareClickLisenter = commShareClickLisenter;
    }

    public interface CommShareClickLisenter {
        void onCOMMClick();
    }

    public IShare iShare;

    public void setiShare(IShare iShare) {
        this.iShare = iShare;
    }

    public interface IShare {
        void onShareSuccess();
    }
}
