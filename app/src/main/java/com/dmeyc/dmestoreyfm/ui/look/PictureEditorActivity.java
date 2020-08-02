package com.dmeyc.dmestoreyfm.ui.look;


import android.support.v4.view.ViewPager;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.ui.look.section.PictureEditorPageAdpter;

import java.util.ArrayList;

import butterknife.Bind;

public class PictureEditorActivity extends BaseActivity {

    @Bind(R.id.viewpager)
    ViewPager viewpager;
    private ArrayList<String> imgList;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_pic_editor;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        imgList = getIntent().getStringArrayListExtra("imgList");
        PictureEditorPageAdpter pictureEditorPageAdpter = new PictureEditorPageAdpter(imgList);
        viewpager.setAdapter(pictureEditorPageAdpter);
    }
}
