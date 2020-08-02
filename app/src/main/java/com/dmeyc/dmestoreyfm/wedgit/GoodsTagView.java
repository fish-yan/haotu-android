package com.dmeyc.dmestoreyfm.wedgit;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.GoodsTagAdapter;
import com.dmeyc.dmestoreyfm.utils.DensityUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * create by cxg on 2020/1/1
 */
public class GoodsTagView extends FrameLayout {
    private RecyclerView mRecyclerView;
    private GoodsTagAdapter mAdapter;

    public GoodsTagView(@NonNull Context context) {
        this(context, null);
    }

    public GoodsTagView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GoodsTagView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.view_goods_tag, this, true);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.left = parent.getChildLayoutPosition(view) == 0 ? 0 : (DensityUtil.dip2px(5));
            }
        });
        mAdapter = new GoodsTagAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.bindToRecyclerView(mRecyclerView);
    }

    public void setTags(String tag) {
        if (tag == null) {
            return;
        }
        List<String> tags = new ArrayList<>();
        String[] split = tag.split(",");
        for (String item : split) {
            String[] split1 = item.split("，");
            tags.addAll(Arrays.asList(split1));
        }
        mAdapter.replaceData(tags);
    }

}
