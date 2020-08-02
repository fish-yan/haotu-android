package com.dmeyc.dmestoreyfm.fragment;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseFragment;
import com.dmeyc.dmestoreyfm.wedgit.GlideImageLoader;
import com.youth.banner.Banner;

import java.util.ArrayList;

import butterknife.Bind;

@SuppressLint("ValidFragment")
public class ActionNoticeFragment extends BaseFragment {
    @Bind(R.id.banner)
    Banner banner;
    @Bind(R.id.tv_intro)
    TextView tv_intro;
    //    ArrayList<String> banners=new ArrayList<>();
    ArrayList<String> iamge;
    String ramake;
    @SuppressLint("ValidFragment")
    public ActionNoticeFragment(ArrayList<String> iamge,String ramake){
        this.iamge=iamge;
        this.ramake=ramake;
    }
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_actionintro;
    }

    @Override
    protected void initData() {
        if(iamge==null||iamge.size()==0){
            banner.setVisibility(View.GONE);
        }else {
        banner.setVisibility(View.VISIBLE);
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(iamge);
        banner.start();
        }
        tv_intro.setText(ramake);
    }

    @Override
    protected void initData(View view) {

    }
}
