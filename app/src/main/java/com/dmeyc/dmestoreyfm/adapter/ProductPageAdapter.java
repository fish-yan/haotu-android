package com.dmeyc.dmestoreyfm.adapter;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.ui.ShowPicActivity;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;

import java.util.ArrayList;

/**
 * Created by jockie on 2018/2/7
 * Email:jockie911@gmail.com
 */

public class ProductPageAdapter extends PagerAdapter {
    ArrayList<String> mProductPicLists;

    public ProductPageAdapter(ArrayList<String> mProductPicLists) {
        this.mProductPicLists = mProductPicLists;
    }

    @Override
    public int getCount() {
        return mProductPicLists.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(final View container, final int position) {
        View view = View.inflate(container.getContext(), R.layout.item_vp_product, null);
        ImageView iv = (ImageView) view.findViewById(R.id.iv);

        GlideUtil.loadImage(container.getContext(),mProductPicLists.get(position),iv);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(container.getContext(), ShowPicActivity.class);
                intent.putExtra(Constant.Config.POSITION,position);
                intent.putStringArrayListExtra("pics",mProductPicLists);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                container.getContext().startActivity(intent);
            }
        });
        ((ViewPager) container).addView(view);
        return view;
    }


    @Override
    public void destroyItem(View container, int position, Object view) {
        ((ViewPager) container).removeView((View) view);
    }
}
