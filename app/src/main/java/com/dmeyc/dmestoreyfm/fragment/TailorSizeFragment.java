package com.dmeyc.dmestoreyfm.fragment;


import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseFragment;
import com.dmeyc.dmestoreyfm.bean.RecordBean;
import com.dmeyc.dmestoreyfm.constant.Constant;

import butterknife.Bind;

/**
 * Created by jockie on 2017/12/4
 * Email:jockie911@gmail.com
 */

@SuppressLint("ValidFragment")
public class TailorSizeFragment extends BaseFragment {

    @Bind(R.id.iv_bottom_clothes)
    ImageView ivBottomClothes;
    @Bind(R.id.iv_top_clothes)
    ImageView ivTopClothes;

    private RecordBean.DataBean itemdata;

    @SuppressLint("ValidFragment")
    public TailorSizeFragment(RecordBean.DataBean item) {
        this.itemdata = item;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fm_tailor_size;
    }

    @Override
    protected void initData() {
        getGender();
    }

    @Override
    protected void initData(View view) {

    }

    public int getGender(){
        if(itemdata != null && itemdata.getInfo() != null){
            String value = itemdata.getInfo().get(itemdata.getInfo().size() - 1).getValue();
            if(TextUtils.equals("女",value)){
                ivTopClothes.setImageResource(R.drawable.top_clothes_woman);
                ivBottomClothes.setImageResource(R.drawable.bottom_clothes_woman);
                return Constant.Config.GENDER_MALE;
            }else if(TextUtils.equals("男",value)){
                ivTopClothes.setImageResource(R.drawable.top_clothes_man);
                ivBottomClothes.setImageResource(R.drawable.bottom_clothes_man);
                return Constant.Config.GENDER_FEMALE;
            }
        }
        return 0;
    }
}
