package com.dmeyc.dmestoreyfm.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;

import butterknife.Bind;

public class HistoryProdActivity extends BaseActivity {

    @Bind(R.id.lv_history)
    ListView lv_history;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_historyprod;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        lv_history.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }
            HistoryViewHoder historyViewHoder;
            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                if(view==null){
                    view=getLayoutInflater().inflate(R.layout.adapter_historypro,null);
                    historyViewHoder=new HistoryViewHoder(view);
                    view.setTag(historyViewHoder);
                }else {
                    historyViewHoder=(HistoryViewHoder) view.getTag();
                }
                return view;
            }
        });

    }

    public class HistoryViewHoder{
        public HistoryViewHoder(View view){

        }
    }
}
