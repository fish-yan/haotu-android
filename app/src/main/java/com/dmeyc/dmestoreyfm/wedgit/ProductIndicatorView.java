package com.dmeyc.dmestoreyfm.wedgit;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jockie on 2017/11/10
 * Email:jockie911@gmail.com
 */
@SuppressWarnings("ResourceType")
public class ProductIndicatorView extends LinearLayout{

    private OnItemClickLister lister;
    private int oldSelectedPos;
    private List<TextView> textViewLists;
    private List<View> viewLists;

    public ProductIndicatorView(Context context) {
        super(context);
        init();
    }

    public ProductIndicatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.product_indicator_view, this, true);
        ButterKnife.bind(mView);

        textViewLists = new ArrayList<>();
        viewLists = new ArrayList();

        addToList(mView,R.id.tv_1, textViewLists,true);
        addToList(mView,R.id.tv_2, textViewLists,true);
        addToList(mView,R.id.tv_3, textViewLists,true);
        addToList(mView,R.id.tv_4, textViewLists,true);

        addToList(mView,R.id.v1, viewLists,false);
        addToList(mView,R.id.v2, viewLists,false);
        addToList(mView,R.id.v3, viewLists,false);
        addToList(mView,R.id.v4, viewLists,false);

        changeSelectedStatus(0);
    }

    private void addToList(View parentView, @StringRes int resId, List list,boolean isTextView){
        list.add(isTextView ? (TextView) parentView.findViewById(resId) : parentView.findViewById(resId));
    }

    @OnClick({R.id.tv_1,R.id.tv_2,R.id.tv_3,R.id.tv_4,})
    public void onClick(View view){
        int clickPosition = 0;
        switch (view.getId()){
            case R.id.tv_1:
                clickPosition = 0;
                break;
            case R.id.tv_2:
                clickPosition = 1;
                break;
            case R.id.tv_3:
                clickPosition = 2;
                break;
            case R.id.tv_4:
                clickPosition = 3;
                break;
        }
        if(lister != null)
            lister.onClick(clickPosition);
        changeSelectedStatus(clickPosition);
    }

    private void changeSelectedStatus(int clickPosition) {
        for (int i = 0; i < textViewLists.size(); i++) {
            textViewLists.get(i).setTextColor(getResources().getColor(i == clickPosition ? R.color.indicator_selected_color : R.color.color_1a1a1a));
            viewLists.get(i).setVisibility(i == clickPosition ? VISIBLE : INVISIBLE);
        }
    }

    public void setOnItemClickLister(OnItemClickLister lister){
        this.lister = lister;
    }

    public interface OnItemClickLister{
        void onClick(int position);
    }

    /**
     * scrollview滑动过程中改变状态位置
     * @param selectedPosition
     */
    public void scrollToSelected(int selectedPosition){
        if(selectedPosition != oldSelectedPos){
            changeSelectedStatus(selectedPosition);
            oldSelectedPos = selectedPosition;
        }
    }
}
