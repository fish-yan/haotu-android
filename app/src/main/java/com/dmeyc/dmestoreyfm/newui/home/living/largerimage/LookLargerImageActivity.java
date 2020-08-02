package com.dmeyc.dmestoreyfm.newui.home.living.largerimage;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.response.LivingListBean;
import com.dmeyc.dmestoreyfm.constant.ExtraKey;
import com.dmeyc.dmestoreyfm.dialog.LoadingDialog;
import com.dmeyc.dmestoreyfm.newbase.BaseActivity;
import com.dmeyc.dmestoreyfm.newui.common.CommentRequestHelper;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.LoadingUtils;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;
import com.dmeyc.dmestoreyfm.wedgit.PinchImageView;

import java.io.File;
import java.util.concurrent.ExecutionException;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * create by cxg on 2019/12/30
 */
public class LookLargerImageActivity extends BaseActivity {
    private LivingListBean.DataBean.ImageListBean mData;

    @Bind(R.id.civ_header)
    CircleImageView mCivHeader;
    @Bind(R.id.scroll_gallery_view)
    PinchImageView mPinchImageView;
    @Bind(R.id.tv_name)
    TextView mTvName;
    @Bind(R.id.tv_look_pre)
    TextView mTvLookPre;
    @Bind(R.id.tv_take)
    TextView mTvTake;
    @Bind(R.id.iv_like)
    ImageView mIvLike;

    public static void newInstance(Context context, LivingListBean.DataBean.ImageListBean data) {
        Intent intent = new Intent(context, LookLargerImageActivity.class);
        intent.putExtra(ExtraKey.SERIALIZABLE_DATA, data);
        context.startActivity(intent);
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_look_larger_image;
    }

    @Override
    protected void initViews() {
        setImmersiveStatusBar(false, getResources().getColor(R.color.black));
        setTitleBlackBg("查看大图");
        mData = (LivingListBean.DataBean.ImageListBean) getIntent().getSerializableExtra(ExtraKey.SERIALIZABLE_DATA);
        GlideUtil.loadImageCache(this, mData.getUserLogo(), mCivHeader);
        mTvName.setText(mData.getNickName());
        mTvTake.setText(mData.getRoleName());
        resetLikeState();
        mPinchImageView.canScrollHorizontally(1);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final File file = Glide.with(LookLargerImageActivity.this).downloadOnly().load(mData.getUrl()).apply(new RequestOptions().onlyRetrieveFromCache(true)).submit().get();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (file.exists()) {
                                mTvLookPre.setVisibility(View.GONE);
                                GlideUtil.loadImageCache(LookLargerImageActivity.this,file,mPinchImageView);
                            } else {
                                mTvLookPre.setText("查看原图" + mData.getFileSize());
                                GlideUtil.loadImage(LookLargerImageActivity.this, mData.getThumbnail(), mPinchImageView);
                            }
                        }
                    });

                } catch (Exception e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mTvLookPre.setText("查看原图" + mData.getFileSize());
                            GlideUtil.loadImage(LookLargerImageActivity.this, mData.getThumbnail(), mPinchImageView);
                        }
                    });

                }
            }
        }).start();

    }


    @OnClick({R.id.iv_like, R.id.tv_look_pre})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.iv_like:
                boolean isUnLike = !"1".equals(mData.getIsLiked());
                CommentRequestHelper.onLivingLickOrNot(mData.getId() + "", 0, isUnLike, new CommentRequestHelper.CallBackAdapter() {
                    @Override
                    public void onLikeSucc(int position, boolean isUnLikePre) {
                        if (isUnLikePre) {
                            mData.setIsLiked("1");
                        } else {
                            mData.setIsLiked("0");
                        }
                        resetLikeState();
                    }
                });
                break;
            case R.id.tv_look_pre:
                LoadingUtils.showProgressDialog(this, "加载中...");
                RequestOptions requestOptions = new RequestOptions();
                requestOptions.diskCacheStrategy(DiskCacheStrategy.DATA);
                Glide.with(this).load(mData.getUrl()).apply(requestOptions).into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                        mPinchImageView.setImageDrawable(resource);
                        mTvLookPre.setVisibility(View.GONE);
                        LoadingUtils.cancelProgressDialog();
                    }

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        super.onLoadFailed(errorDrawable);
                        LoadingUtils.cancelProgressDialog();
                    }
                });

                break;
        }
    }

    private void resetLikeState() {
        if ("1".equals(mData.getIsLiked())) {
            mIvLike.setImageResource(R.drawable.icon_like_like);
        } else {
            mIvLike.setImageResource(R.drawable.icon_like_normal);
        }
    }
}
