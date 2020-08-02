package com.dmeyc.dmestoreyfm.newui.release.live;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.constant.ExtraKey;
import com.dmeyc.dmestoreyfm.event.RefreshEvent;
import com.dmeyc.dmestoreyfm.newbase.BaseActivity;
import com.dmeyc.dmestoreyfm.newui.home.living.LivingListActivity;
import com.dmeyc.dmestoreyfm.photolive.LivingActivity;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.tbruyelle.rxpermissions.RxPermissions;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * create by cxg on 2019/12/25
 */
public class LiveActivity extends BaseActivity {
    public static final String TYPE_MATCH = "TYPE_MATCH";
    public static final String TYPE_LIVES = "TYPE_LIVES";
    public static final String TYPE_OTHER = "TYPE_OTHER";

    @Bind(R.id.imageView)
    ImageView mImageView;

    @Bind(R.id.tv_begin)
    TextView mTvBegin;

    private String mActivityId;

    private String mType;

    public static void newInstance(Context context, String type, String activityId, String url) {
        Intent intent = new Intent(context, LiveActivity.class);
        intent.putExtra(ExtraKey.TYPE_FROM, type);
        intent.putExtra(ExtraKey.ACTIVITY_ID, activityId);
        intent.putExtra(ExtraKey.IMAGE_URL, url);
        context.startActivity(intent);
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_live;
    }

    @Override
    protected void initViews() {
        mType = getIntent().getStringExtra(ExtraKey.TYPE_FROM);
        switch (mType) {
            case TYPE_MATCH:
                setTitleWithRightIcon("赛事直播", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                break;
            case TYPE_LIVES:
                setTitleWithRightIcon("赛事直播", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                break;
            case TYPE_OTHER:
                setTitleWithRightIcon("直播", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                mTvBegin.setText("进入直播间");
                break;

            default:
        }
        mActivityId = getIntent().getStringExtra(ExtraKey.ACTIVITY_ID);
        String imageUrl = getIntent().getStringExtra(ExtraKey.IMAGE_URL);
        if (imageUrl != null) {
            GlideUtil.loadImage(this, imageUrl, mImageView);
        }

    }

    @OnClick(R.id.tv_begin)
    public void beginLive() {
        if (TYPE_OTHER.equals(mType)) {
            LivingListActivity.newInstance(this, mActivityId);
        } else {
            RxPermissions.getInstance(this).request(Manifest.permission.READ_EXTERNAL_STORAGE).subscribe(new Action1<Boolean>() {
                @Override
                public void call(Boolean aBoolean) {
                    if (aBoolean) {

                        EventBus.getDefault().post(new RefreshEvent.Release(RefreshEvent.Release.TYPE_LIVING));
                        LivingActivity.newInstance(LiveActivity.this, mActivityId);
                    } else {
                        ToastUtil.show("申请权限 " + Manifest.permission.READ_EXTERNAL_STORAGE + "被拒绝");
                    }
                }
            });
        }

    }
}
