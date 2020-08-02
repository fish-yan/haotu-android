package com.dmeyc.dmestoreyfm.fragment;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseFragment;
import com.dmeyc.dmestoreyfm.ui.CalculateActivity;

import butterknife.Bind;
import butterknife.OnClick;

public class CalculateFragment extends BaseFragment {

    Uri mUri;
//@Bind(R.id.player_view)
//IjkPlayerView player_view;
    @Bind(R.id.iv_gif)
    ImageView  iv_gif;

    @Override
    protected int getLayoutRes() {
        return R.layout.calculate;
    }

    @Override
    protected void initData() {
//        Glide.with(this).load(mGifUrl).asBitmap().placeholder(R.mipmap.place).error(R.mipmap.icon_photo_error).into(mIv);
//        Glide.with(this).load("http://ww1.sinaimg.cn/mw600/6345d84ejw1dvxp9dioykg.gif").asGif().into(img8);
//        Glide.with(getActivity()).load(R.drawable.caculate).asGif().into(iv_gif);
        Glide.with(getActivity()).load(R.drawable.caculate).into(iv_gif);
//        Glide.with(getActivity()).load(R.drawable.caculate).into(iv_gif);
//        ArrayList<String> mProductPicLists = new ArrayList<>();
//        mProductPicLists.add("http://www.520mylove.com/1.mp4");
//        mUri = Uri.parse(mProductPicLists.get(0));
//        player_view.init()
//                .setVideoPath(mUri)
//                .setMediaQuality(IjkPlayerView.MEDIA_QUALITY_HIGH)
//                .enableDanmaku()
//                .start();


    }

    @Override
    protected void initData(View view) {

    }
@OnClick(R.id.btn_upload)
    public void onClick(View view){
        int viewid=view.getId();
        if(viewid==R.id.btn_upload){
            startActivity(new Intent(getActivity(),CalculateActivity.class));
        }
    }

}
