package com.dmeyc.dmestoreyfm.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.Toast;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.utils.Util;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jockie on 2017/9/27
 * Email:jockie911@gmail.com
 */

public class ShareDialog extends Dialog {

    private Activity mActivity;
    public ShareDialog(@NonNull Context context) {
        super(context, R.style.dialog_style);
        this.mActivity = (Activity) context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_share);
        ButterKnife.bind(this);
        Window dialogWindow = getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = AbsListView.LayoutParams.MATCH_PARENT;
        lp.y = 0;//设置Dialog距离底部的距离
        dialogWindow.setAttributes(lp);
        initData();
    }
    private void initData() {

    }

    @OnClick({R.id.ll_wechat,R.id.ll_wxcicle,R.id.ll_qq,R.id.ll_sina})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.ll_wechat:
                share(SHARE_MEDIA.WEIXIN,"share");
                break;
            case R.id.ll_wxcicle:
                share(SHARE_MEDIA.WEIXIN_CIRCLE,"share");
                break;
            case R.id.ll_qq:
                share(SHARE_MEDIA.QQ,"share");

                break;
            case R.id.ll_sina:
                share(SHARE_MEDIA.SINA,"share");
                break;
        }
        dismiss();
    }

    private void share(SHARE_MEDIA plat,String content){
        UMWeb web = new UMWeb(Constant.API.BASE_URL+Constant.API.SHARE_REGISTER+"?userId="+Util.getUserId());
        web.setTitle("分享标题");//标题
        web.setThumb(new UMImage(mActivity,R.drawable.yfm_logo));  //缩略图
        web.setDescription("分享内容");//描述
        new ShareAction(mActivity)
                .setPlatform(plat)
                .setCallback(umShareListener)
                .withMedia(web)
                .share();
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
            Toast.makeText(mActivity,"分享成功",Toast.LENGTH_SHORT).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(mActivity,"失败"+t.getMessage(),Toast.LENGTH_SHORT).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(mActivity,"取消了",Toast.LENGTH_SHORT).show();

        }
    };
}
