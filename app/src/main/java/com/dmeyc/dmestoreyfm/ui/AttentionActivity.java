package com.dmeyc.dmestoreyfm.ui;

import android.os.Bundle;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseFragment;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.base.BaseTabActivity;
import com.dmeyc.dmestoreyfm.fragment.WishFragment;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AttentionActivity extends BaseTabActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;

    @Override
    protected int getLayoutRes() {
        return R.layout.ac_attention;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void getFragmentLists(List<BaseFragment> mFragmentLists) {
        tvTitle.setText("心愿单");
//        mFragmentLists.add(new WishFragment(WishFragment.TYPE_BRAND));
        mFragmentLists.add(new WishFragment(WishFragment.TYPE_PRODUCT));
    }

    @Override
    protected void getTitleList(List<String> mTitleLists) {
        mTitleLists.add("单品");
//        mTitleLists.add("品牌");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
