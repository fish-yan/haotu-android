package com.dmeyc.dmestoreyfm.video.index;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.dmeyc.dmestoreyfm.R;

public class TopMenuPopWindow extends PopupWindow {
    private View mView;
    private TextView tv_intent_to_pic,tv_intent_to_video;
    public TopMenuPopWindow(Activity paramActivity, View.OnClickListener paramOnClickListener,
                        int paramInt1, int paramInt2){
        mView = LayoutInflater.from(paramActivity).inflate(R.layout.popwindow_index_top_right_menu, null);
        tv_intent_to_pic = mView.findViewById(R.id.tv_intent_to_pic);
        tv_intent_to_video = mView.findViewById(R.id.tv_intent_to_video);
        if (paramOnClickListener != null){
            //设置点击监听
            tv_intent_to_pic.setOnClickListener(paramOnClickListener);
            tv_intent_to_video.setOnClickListener(paramOnClickListener);
            setContentView(mView);
            //设置宽度
            setWidth(paramInt1);
            //设置高度
            setHeight(paramInt2);
            //设置显示隐藏动画
//            setAnimationStyle(R.style.AnimTools);
            //设置背景透明
            setBackgroundDrawable(new ColorDrawable(0));
        }
    }
}
