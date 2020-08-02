package com.dmeyc.dmestoreyfm.wedgit;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.HeaderIconAdatper;
import com.dmeyc.dmestoreyfm.bean.response.ActivityDetailBean;

import java.util.ArrayList;
import java.util.List;

/**
 * create by cxg on 2019/12/1
 */
public class HeaderIconView extends ConstraintLayout {
    private RecyclerView mRecyclerView;
    private HeaderIconAdatper mAdapter;
    private ImageView mIvArrow;
    private TextView mTvJoinCount;

    public HeaderIconView(Context context) {
        this(context, null);
    }

    public HeaderIconView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HeaderIconView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View layout = LayoutInflater.from(context).inflate(R.layout.item_header_icon, this, true);
        mRecyclerView = layout.findViewById(R.id.rv_header_icon);
        mIvArrow = layout.findViewById(R.id.iv_icon_right);
        mTvJoinCount = layout.findViewById(R.id.tv_join_count);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        mAdapter = new HeaderIconAdatper(context);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.bindToRecyclerView(mRecyclerView);

        if (attrs != null) {

            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.HeaderIconView);
            int type = ta.getInt(R.styleable.HeaderIconView_hiv_type, 0);

            switch (type) {
                case 0:
                    break;
                case 1:
                    mIvArrow.setVisibility(View.VISIBLE);
                    break;
                default:
            }
            ta.recycle();
        }
    }

    public void setData(List<ActivityDetailBean.DataBean.SignUp> data) {
        if (data ==null){
            data = new ArrayList<>();
        }
        mAdapter.replaceData(data);
    }

    public void setCount(String count) {
        if (count != null) {
            mTvJoinCount.setText(count);
        }
    }
}
