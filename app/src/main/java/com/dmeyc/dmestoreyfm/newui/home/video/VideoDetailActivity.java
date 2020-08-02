package com.dmeyc.dmestoreyfm.newui.home.video;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.response.VideoItemBean;
import com.dmeyc.dmestoreyfm.constant.ExtraKey;
import com.dmeyc.dmestoreyfm.newbase.BaseActivity;
import com.dmeyc.dmestoreyfm.video.index.IndexDynamicBean;

import java.io.Serializable;
import java.util.List;

import butterknife.OnClick;

/**
 * create by cxg on 2019/12/19
 */
public class VideoDetailActivity extends BaseActivity {
    public static void newInstance(Context context, List<IndexDynamicBean.DataBean> list, int position) {
        Intent intent = new Intent(context, VideoDetailActivity.class);
        intent.putExtra(ExtraKey.VIDEO_INFO_LIST, (Serializable) list);
        intent.putExtra(ExtraKey.VIDEO_SELECOTR_POSITION, position);
        context.startActivity(intent);
    }

    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @Override
    protected int setContentView() {
        return R.layout.activity_video_detail;
    }

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {
        setImmersiveStatusBar(false, getResources().getColor(R.color.black));
        VideoHomeFragment videoHomeFragment = new VideoHomeFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ExtraKey.VIDEO_INFO_LIST, getIntent().getSerializableExtra(ExtraKey.VIDEO_INFO_LIST));
        bundle.putInt(ExtraKey.VIDEO_SELECOTR_POSITION, getIntent().getIntExtra(ExtraKey.VIDEO_SELECOTR_POSITION, 0));
        videoHomeFragment.setArguments(bundle);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fl_container, videoHomeFragment).commit();

    }

    @OnClick(R.id.iv_back)
    public void colse() {
        finish();
    }
}
