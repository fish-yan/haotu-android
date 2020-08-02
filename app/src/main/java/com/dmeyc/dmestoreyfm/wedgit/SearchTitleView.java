package com.dmeyc.dmestoreyfm.wedgit;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;

/**
 * create by cxg on 2019/12/2
 */
public class SearchTitleView extends ConstraintLayout implements TextView.OnEditorActionListener, View.OnClickListener {
    private EditText mEtSearch;
    private TextView mTvLocation;
    private TextView mTvCancel;
    private OnItemClickListener mListener;

    private InputMethodManager mImm;

    public SearchTitleView(Context context) {
        this(context, null);
    }

    public SearchTitleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SearchTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.view_search_title, this, true);
        mEtSearch = findViewById(R.id.et_search);
        mTvLocation = findViewById(R.id.tv_location);
        mTvCancel = findViewById(R.id.tv_cancel);
        mImm = (InputMethodManager) mEtSearch.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        mEtSearch.setOnEditorActionListener(this);
        mTvLocation.setOnClickListener(this);
        mTvCancel.setOnClickListener(this);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        switch (actionId) {
            case EditorInfo.IME_ACTION_SEARCH:
                if (TextUtils.isEmpty((mEtSearch.getText().toString().trim()))) {
                    ToastUtil.show("请输入搜索内容");
                    return true;
                }
                if (mImm.isActive()) {
                    mImm.hideSoftInputFromWindow(mEtSearch.getWindowToken(), 0);
                }
                if (mListener != null) {
                    mListener.onSearch(mEtSearch.getText().toString());
                }
                break;
            default:
                break;
        }
        return true;
    }

    public void setListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public void setLocationString(String string) {
        if (string != null) {
            mTvLocation.setText(string);
        }
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_location:
                if (mListener != null) {
                    mListener.onLocationClick();
                }
                break;
            case R.id.tv_cancel:
                if (mListener != null) {
                    mListener.onCancel();
                }
                break;
            default:
        }
    }

    public void setSearchText(String search) {
        if (search != null) {
            mEtSearch.setText(search);
        }
    }

    public interface OnItemClickListener {
        void onLocationClick();

        void onSearch(String searchString);

        void onCancel();
    }
}
