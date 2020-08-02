package com.dmeyc.dmestoreyfm.wedgit;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jockie on 2017/11/24
 * Email:jockie911@gmail.com
 */

public class AmountView extends RelativeLayout  {

    @Bind(R.id.iv_reduce)
    ImageView ivReduce;
    @Bind(R.id.iv_add)
    ImageView ivAdd;
    @Bind(R.id.tv_count)
    TextView tvCount;

    private int MAX_COUNT = 1; //库存最大量
    private int mCurCount = 1;  //当前的数量

    private static final int ADD_SELECTED = R.drawable.shopping_amount_add_selected;
    private static final int ADD_NORMAL = R.drawable.shopping_amount_add_normal_;
    private static final int REDUCE_SELECTED = R.drawable.shopping_amount_reduce_selected;
    private static final int REDUCE_NORMAL = R.drawable.shopping_amount_reduce_normal_;
    private OnCountChangeLister countChangeLister;

    public AmountView(Context context) {
        super(context);
        init();
    }

    public AmountView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View view = View.inflate(getContext(), R.layout.amount_view, this);
        ButterKnife.bind(view);

        initStatus();
    }

    @OnClick({R.id.iv_reduce,R.id.iv_add})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_reduce:
                setSelectCountStatus(false);
                break;
            case R.id.iv_add:
                setSelectCountStatus(true);
                break;
        }
    }

    private void setSelectCountStatus(boolean isAdd){
        if(mCurCount == 1 && !isAdd){
            ToastUtil.show("不能再少了");
            initStatus();
            return;
        } else if(mCurCount == MAX_COUNT && isAdd){
            ToastUtil.show("不能再多了");
            initStatus();
            return;
        }else{
            if(isAdd){
                mCurCount ++ ;
            }else{
                mCurCount --;
            }
            initStatus();
            if(countChangeLister != null)
                countChangeLister.countChange(mCurCount,isAdd);
        }
        tvCount.setText(String.valueOf(mCurCount));
    }

    public void setCurCount(int count){
        this.mCurCount = count;
        tvCount.setText(String.valueOf(mCurCount));
        initStatus();
    }

    public int getCurCount(){
        return mCurCount;
    }

    /**
     * 最大数量
     * @param maxCount
     */
    public void setMaxCount(int maxCount){
        this.MAX_COUNT = maxCount;
    }

    public interface OnCountChangeLister{
        void countChange(int mCurCount, boolean isAdd);
    }

    public void setOnCountChangeLister(OnCountChangeLister countChangeLister){
        this.countChangeLister = countChangeLister;
    }

    private void initStatus(){
        ivReduce.setBackgroundResource(mCurCount > 1 ? REDUCE_SELECTED : REDUCE_NORMAL);
        ivAdd.setBackgroundResource(mCurCount == MAX_COUNT ? ADD_NORMAL : ADD_SELECTED);
        Log.d("TAG","" + mCurCount);
    }
}
