package com.dmeyc.dmestoreyfm.ui;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;

import butterknife.Bind;

public class BClientProjectActivity extends BaseActivity {
    @Bind(R.id.lv_projectlist)
    ListView lv_projectlist;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_bclientproject;
    }
    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        lv_projectlist.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 0;
            }
            @Override
            public Object getItem(int i) {
                return null;
            }
            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                return null;
            }
        });
    }
}
