package com.dmeyc.dmestoreyfm.wedgit;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;

/**
 * create by cxg on 2019/11/23
 * title + 红字说明 + 右边 数量增减
 */
public class CustomItemView1 extends FrameLayout implements AddMinusView.OnTextChangeListener {


    private TextView mTvTitle;
    private TextView mTvTip;
    private AddMinusView mAddMinusView;
    private Context mContext;

    public CustomItemView1(@NonNull Context context) {
        this(context, null);
    }

    public CustomItemView1(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomItemView1(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.view_custom_item1, this, true);
        mTvTip = findViewById(R.id.tv_tip);
        mAddMinusView = findViewById(R.id.addMinusView);
        mTvTitle = findViewById(R.id.tv_item_left);
        mAddMinusView.setListener(this);
        init(context, attrs);
    }


    /**
     * type:
     * 1 、左文字 + 中间红色文字
     * 2 、左文字 + 中间红色文字 + 后面增减
     *
     * @param context
     * @param attrs
     */
    private void init(Context context, AttributeSet attrs) {

        if (attrs != null) {

            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomItemView1);
            String title = ta.getString(R.styleable.CustomItemView1_civ1_left_str);
            String tips = ta.getString(R.styleable.CustomItemView1_civ1_tip_str);
            int type = ta.getInt(R.styleable.CustomItemView1_civ1_type, 1);
            if (title != null) {
                mTvTitle.setText(title);
            }
            if (tips != null) {
                mTvTip.setText(tips);
            }

            switch (type) {
                case 1:

                    break;

                case 2:
                    mAddMinusView.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    break;
            }


            ta.recycle();
        }
    }

    //db转换为px
    int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public void setTitle(String title) {
        mTvTitle.setText(title);
    }

    public void setTip(String tip) {
        mTvTip.setText(tip);
    }

    @Override
    public void onTextChange(int nowCount) {
        if (mListener != null) {
            mListener.onTextChange(nowCount);
        }
    }

    public int getCount(){
        return mAddMinusView.getCount();
    }
    private OnTextChangeListener mListener;

    public void setListener(OnTextChangeListener listener) {
        this.mListener = listener;
    }

    public interface OnTextChangeListener {
        void onTextChange(int nowCount);
    }
}
