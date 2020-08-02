package com.dmeyc.dmestoreyfm.fragment.home;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.base.BaseFragment;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.fragment.shop.ShoppingItemFragment;
import com.dmeyc.dmestoreyfm.ui.ShopCarActivity;

import java.util.List;


/**
 * Created by jockie on 2017/11/6
 * Email:jockie911@gmail.com
 */

public class ShoppingFragment extends BrandFragment{

    @Override
    protected void getTitleList(List<String> mTitleLists) {
//        mTitleLists.add("女装");
//        mTitleLists.add("男装");
    }

    @Override
    protected void getFragmentLists(List<BaseFragment> mFragmentLists) {
//        mFragmentLists.add(new ShoppingItemFragment(Constant.Config.GENDER_FEMALE));
        mFragmentLists.add(new ShoppingItemFragment(Constant.Config.GENDER_MALE));
    }

    @Override
    protected void isShowTitle(TextView view) {
        super.isShowTitle(view);
        view.setVisibility(View.VISIBLE);
    }
    @Override
    public void rightIconClick(){
        Intent intent = new Intent(getContext(), ShopCarActivity.class);
        getContext().startActivity(intent);
    }
}
