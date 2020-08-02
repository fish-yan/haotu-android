package com.dmeyc.dmestoreyfm.ui;

import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BaseFragment;
import com.dmeyc.dmestoreyfm.bean.SearchKeyBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.fragment.SearchRecordFragment;
import com.dmeyc.dmestoreyfm.fragment.SearchResultFragment;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.present.SearchPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class SearchGoodsActivity extends BaseActivity<SearchPresenter> {

    @Bind(R.id.et_search_title)
    EditText etSearchTitle;
    @Bind(R.id.tv_search)
    TextView tvSearch;
    @Bind(R.id.ll_list)
    LinearLayout ll_list;
    private SearchRecordFragment searchFragment;
    private SearchResultFragment resultFragment;
    private List<SearchKeyBean> searchKeyBeanList = new ArrayList<>();
    private boolean isSearch = true;

    @Override
    protected SearchPresenter initPresenter() {
        return new SearchPresenter();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_search;
    }

    @Override
    protected void initData() {
        searchFragment = new SearchRecordFragment();
        resultFragment = new SearchResultFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_container, searchFragment)
                .add(R.id.frame_container, resultFragment)
                .show(searchFragment)
                .hide(resultFragment)
                .commit();

        etSearchTitle.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                if (hasFocus && searchFragment.isHidden()) {
//                    fragmentTransaction.show(searchFragment).hide(resultFragment);
//                    fragmentTransaction.commit();
                    showFragment(searchFragment);
                }
            }
        });

        etSearchTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!isSearch) {
                    return;
                }
                RestClient.getNovate(SearchGoodsActivity.this).get(Constant.API.PRODUCT_SEARCH_KEY, new ParamMap.Build().addParams("keyword", etSearchTitle.getText().toString()).build()
                        , new DmeycBaseSubscriber<SearchKeyBean>(SearchGoodsActivity.this) {
                            @Override
                            public void onSuccess(SearchKeyBean bean) {
                                searchKeyBeanList.clear();
                                searchKeyBeanList.add(bean);
                                if (searchKeyBeanList != null && searchKeyBeanList.get(0).getData().size() > 0) {
                                    addSonView();
                                }
                            }
                        });
            }
        });
    }


    private void addSonView() {
        ll_list.setVisibility(View.VISIBLE);
        ll_list.removeAllViews();
        for (int i = 0; i < searchKeyBeanList.get(0).getData().size(); i++) {

            final LinearLayout sonView = (LinearLayout) LayoutInflater.from(SearchGoodsActivity.this).inflate(
                    R.layout.son_ll_search_list, null);
            TextView text_name = sonView.findViewById(R.id.text_name);
            text_name.setText(searchKeyBeanList.get(0).getData().get(i));
            final int finalI = i;
            text_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ll_list.setVisibility(View.GONE);
                    if (!resultFragment.isAdded() || resultFragment.isHidden()) {
                        isSearch = false;
                        etSearchTitle.setText(searchKeyBeanList.get(0).getData().get(finalI));
                        doSearch(searchKeyBeanList.get(0).getData().get(finalI));
                        searchFragment.addHistoryRecord(searchKeyBeanList.get(0).getData().get(finalI));
                    }
                }
            });
            ll_list.addView(sonView);
        }
    }

    @OnClick({R.id.tv_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_search:
                ll_list.setVisibility(View.GONE);
                isSearch = true;
                if (TextUtils.equals("取消", tvSearch.getText().toString())) {
                    etSearchTitle.setText("");
                    onBackPressed();
                } else {
                    if (TextUtils.isEmpty(etSearchTitle.getText().toString())) {
                        Toast.makeText(this, "搜索不能为空", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (!resultFragment.isAdded() || resultFragment.isHidden()) {
                        doSearch(etSearchTitle.getText().toString());
                        searchFragment.addHistoryRecord(etSearchTitle.getText().toString());
                    }
                }
                break;
        }
    }

    /**
     * 设置搜索结果
     *
     * @param content
     */
    public void setEtSearchContent(String content) {
        etSearchTitle.setText(content);
        etSearchTitle.setSelection(content.length());
    }

    public void doSearch(String content) {
        tvSearch.setText("取消");
        etSearchTitle.clearFocus();
        showFragment(resultFragment);
        resultFragment.search(content);
    }

    @Override
    protected boolean isContainTitle() {
        return false;
    }

    public void showFragment(BaseFragment fm) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (fm instanceof SearchRecordFragment) {
            fragmentTransaction.show(searchFragment).hide(resultFragment).commit();
        } else if (fm instanceof SearchResultFragment) {
            fragmentTransaction.show(resultFragment).hide(searchFragment).commit();
        }
    }

    @Override
    public void onBackPressed() {
        if (!searchFragment.isAdded() || searchFragment.isHidden()) {
            showFragment(searchFragment);
            tvSearch.setText("搜索");
            return;
        }
        super.onBackPressed();
    }
}
