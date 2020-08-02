package com.dmeyc.dmestoreyfm.ui;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.Util;

import java.util.ArrayList;

import butterknife.Bind;

public class ShowPicActivity extends BaseActivity {

    @Bind(R.id.viewpage)
    ViewPager viewPager;
    @Bind(R.id.tv_count)
    TextView tvCount;
    ArrayList<String> picLists;
    private int mCurPos;

    @Override
    protected int getLayoutRes() {
        return R.layout.ac_show_pic;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected boolean isFullScreem() {
        return true;
    }

    @Override
    protected boolean isContainTitle() {
        return false;
    }

    @Override
    protected void initData() {
        picLists = getIntent().getStringArrayListExtra("pics");
        mCurPos = getIntent().getIntExtra(Constant.Config.POSITION, 0);
        if(Util.objEmpty(picLists)){
            String pic = getIntent().getStringExtra("pic");
            picLists = new ArrayList<>();
            picLists.add(pic);
        }

        WindowManager wm = this.getWindowManager();

        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();

        RelativeLayout.LayoutParams rl=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,width);
        rl.addRule(RelativeLayout.CENTER_IN_PARENT);
        viewPager.setLayoutParams(rl);
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return picLists.size();
            }
            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(View container, int position) {
                View view = View.inflate(container.getContext(), R.layout.item_pic_show, null);
                ImageView imageview = (ImageView) view.findViewById(R.id.item_pic_iv);
                imageview.setScaleType(ImageView.ScaleType.CENTER_CROP);
                GlideUtil.loadImage(container.getContext(),picLists.get(position),imageview);

                ((ViewPager) container).addView(view);
                imageview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
                return view;
            }

            @Override
            public void destroyItem(View container, int position, Object view) {
                ((ViewPager) container).removeView((View) view);
            }
        });
         viewPager.setCurrentItem(mCurPos);
        if(picLists.size() == 1)
            tvCount.setVisibility(View.GONE);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                tvCount.setText(position + 1 + "/" + picLists.size());
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
}