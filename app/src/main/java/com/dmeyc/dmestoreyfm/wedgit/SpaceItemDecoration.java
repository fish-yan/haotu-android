package com.dmeyc.dmestoreyfm.wedgit;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int space;

    public SpaceItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //不是第一个的格子都设一个左边和底部的间距
        outRect.bottom = space;
        // Add top margin only for the first item to avoid double space between items
        if (parent.getChildPosition(view) == 0)
            outRect.top = space;
    }

}