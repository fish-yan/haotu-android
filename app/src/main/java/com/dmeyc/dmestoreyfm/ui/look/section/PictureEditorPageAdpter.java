package com.dmeyc.dmestoreyfm.ui.look.section;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;

import java.util.ArrayList;
import java.util.List;

public class PictureEditorPageAdpter extends PagerAdapter {
    List<String> mPicLists =new ArrayList<>();

    public PictureEditorPageAdpter(List<String> mPicLists) {
        this.mPicLists = mPicLists;
    }

    @Override
    public int getCount() {
        return mPicLists.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
          return view == object;
    }
    @Override
    public Object instantiateItem(final View container, final int position) {
        View view = View.inflate(container.getContext(), R.layout.page_adapter_pic_editor , null);
        ImageView img_big = (ImageView) view.findViewById(R.id.img_big);

        GlideUtil.loadImage(container.getContext(),mPicLists.get(position),img_big);

        ((ViewPager) container).addView(view);
        return view;
    }


    @Override
    public void destroyItem(View container, int position, Object view) {
        ((ViewPager) container).removeView((View) view);
    }


}
