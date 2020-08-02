package com.dmeyc.dmestoreyfm.wedgit;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.dmeyc.dmestoreyfm.R;

/**
 * create by cxg on 2019/12/1
 */
public class AddMinusView extends ConstraintLayout implements View.OnClickListener {

    private EditText mEditText;
    private View mViewMinus;
    private View mViewAdd;

    private int mMaxCount = 99;
    private OnTextChangeListener mListener;

    public AddMinusView(Context context) {
        this(context, null);
    }

    public AddMinusView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AddMinusView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.view_add_minus, this, true);
        mEditText = view.findViewById(R.id.et_count);
        mViewMinus = view.findViewById(R.id.view_minus);
        mViewAdd = view.findViewById(R.id.view_add);
        mViewMinus.setOnClickListener(this);
        mViewAdd.setOnClickListener(this);
    }


    public int getCount() {
        try {
            return Integer.valueOf(mEditText.getText().toString());
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        int nowCount;

        switch (v.getId()) {
            case R.id.view_minus:
                try {
                    nowCount = Integer.valueOf(mEditText.getText().toString());
                } catch (Exception e) {
                    nowCount = 0;
                }
                nowCount = nowCount > 0 ? nowCount - 1 : 0;
                mEditText.setText(String.valueOf(nowCount));
                if (mListener != null) {
                    mListener.onTextChange(nowCount);
                }
                break;

            case R.id.view_add:
                try {
                    nowCount = Integer.valueOf(mEditText.getText().toString()) + 1;
                } catch (Exception e) {
                    nowCount = 0;
                }
                mEditText.setText(String.valueOf(nowCount));
                if (mListener != null) {

                    mListener.onTextChange(nowCount);
                }
                break;

            default:
        }
    }

    public void setListener(OnTextChangeListener listener) {
        this.mListener = listener;
    }

    public interface OnTextChangeListener {
        void onTextChange(int nowCount);
    }
}
