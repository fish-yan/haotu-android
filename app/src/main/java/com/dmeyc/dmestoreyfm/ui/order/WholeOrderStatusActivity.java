package com.dmeyc.dmestoreyfm.ui.order;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseFragment;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.base.BaseTabActivity;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.fragment.OrderManagerFragment;
import com.dmeyc.dmestoreyfm.utils.Util;

import java.util.List;

public class WholeOrderStatusActivity extends BaseTabActivity{

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_whole_order_status;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    protected void initData() {
        super.initData();
        tvTitle.setText("订单");
        int position = getIntent().getIntExtra("position", 0);
        mViewPager.setCurrentItem(position);
    }

    @Override
    protected void reSetTabBlockWidth() {
        Util.reflexFix(mTabLayout,5);
    }

    @Override
    protected void getFragmentLists(List<BaseFragment> mFragmentLists) {
        mFragmentLists.add(new OrderManagerFragment(Constant.OrderStatus.ALL));
        mFragmentLists.add(new OrderManagerFragment(Constant.OrderStatus.WAIT_PAY));
        mFragmentLists.add(new OrderManagerFragment(Constant.OrderStatus.COMMITTED));
        mFragmentLists.add(new OrderManagerFragment(Constant.OrderStatus.STAY));
        mFragmentLists.add(new OrderManagerFragment(Constant.OrderStatus.EVALUATE));
    }

    @Override
    protected void getTitleList(List<String> mTitleLists) {
        mTitleLists.add("全部");
        mTitleLists.add("待付款");
        mTitleLists.add("待发货");
        mTitleLists.add("待收货");
        mTitleLists.add("待评价");
    }
}
