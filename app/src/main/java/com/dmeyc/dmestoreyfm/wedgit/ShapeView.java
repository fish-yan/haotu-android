package com.dmeyc.dmestoreyfm.wedgit;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.constant.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yc on 2017/5/15.
 */

public class ShapeView extends RelativeLayout {
    private List<TextView> mTextViewLists;
    private int[] mResArrays = {
            R.drawable.size_man_bust,
            R.drawable.size_man_waistline,
            R.drawable.size_man_hipline,
            R.drawable.size_man_wrist,
            R.drawable.size_man_shoulder,
            R.drawable.size_man_brachium,
            R.drawable.size_man_downside,
            R.drawable.size_man_ankle};


    private int[] mResMaleArrays = {
            R.drawable.size_man_bust,
            R.drawable.size_man_waistline,
            R.drawable.size_man_hipline,
            R.drawable.size_man_wrist,
            R.drawable.size_man_shoulder,
            R.drawable.size_man_brachium,
            R.drawable.size_man_downside,
            R.drawable.size_man_ankle};
    private List<LinearLayout> mLinearLists;

    public ShapeView(Context context) {
        super(context);
        init();
    }

    public ShapeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ShapeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        View rootView = View.inflate(getContext(), R.layout.shape_view, this);

        mTextViewLists = new ArrayList<>();
        mTextViewLists.add((TextView) rootView.findViewById(R.id.tv_1));
        mTextViewLists.add((TextView) rootView.findViewById(R.id.tv_2));
        mTextViewLists.add((TextView) rootView.findViewById(R.id.tv_3));
        mTextViewLists.add((TextView) rootView.findViewById(R.id.tv_4));
        mTextViewLists.add((TextView) rootView.findViewById(R.id.tv_5));
        mTextViewLists.add((TextView) rootView.findViewById(R.id.tv_6));
        mTextViewLists.add((TextView) rootView.findViewById(R.id.tv_7));
        mTextViewLists.add((TextView) rootView.findViewById(R.id.tv_8));

        mLinearLists = new ArrayList<>();
        mLinearLists.add((LinearLayout) rootView.findViewById(R.id.ll_1));
        mLinearLists.add((LinearLayout) rootView.findViewById(R.id.ll_2));
        mLinearLists.add((LinearLayout) rootView.findViewById(R.id.ll_3));
        mLinearLists.add((LinearLayout) rootView.findViewById(R.id.ll_4));
        mLinearLists.add((LinearLayout) rootView.findViewById(R.id.ll_5));
        mLinearLists.add((LinearLayout) rootView.findViewById(R.id.ll_6));
        mLinearLists.add((LinearLayout) rootView.findViewById(R.id.ll_7));
        mLinearLists.add((LinearLayout) rootView.findViewById(R.id.ll_8));
    }

    /**
     * 设置数据
     *
     * @param gender
     * @param lisner
     */
    public void initData(final int gender, List<String> mTextDatas, final OnSwitchPicShowLisner lisner) {
        if (gender == Constant.Config.GENDER_MALE) {
            mResArrays = mResMaleArrays;
        }
        for (int i = 0; i < mTextDatas.size(); i++) {
            mTextViewLists.get(i).setText(mTextDatas.get(i));
        }
        if (lisner != null) {
            lisner.switchPicShow(mResArrays[2]);
            mLinearLists.get(2).setBackgroundResource(gender == Constant.Config.GENDER_MALE ? R.drawable.size_man_btn_orange : R.drawable.size_btn_orange);
        }
        for (int i = 0; i < mLinearLists.size(); i++) {

            final int finalI = i;
            mLinearLists.get(i).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int j = 0; j < mLinearLists.size(); j++) {
                        mLinearLists.get(j).setBackgroundResource(finalI == j ? (gender == Constant.Config.GENDER_MALE ? R.drawable.size_man_btn_orange : R.drawable.size_btn_orange) : R.drawable.size_btn_black);
                    }
                    if (lisner != null)
                        lisner.switchPicShow(mResArrays[finalI]);
                }
            });
        }
    }

    public interface OnSwitchPicShowLisner {
        void switchPicShow(@DrawableRes int mDrawableResId);
    }
}
