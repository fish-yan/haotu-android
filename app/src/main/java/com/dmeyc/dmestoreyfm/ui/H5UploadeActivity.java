package com.dmeyc.dmestoreyfm.ui;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;

import butterknife.Bind;

public class H5UploadeActivity extends BaseActivity {
    @Bind(R.id.gv_acitivtysport)
    GridView gv_acitivtysport;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_h5uplode;
    }
    @Override
    protected BasePresenter initPresenter() {
        return null;
    }
    @Override
    protected void initData() {
        gv_acitivtysport.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 10;
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }
            H5SportViewHorlder h5SportViewHorlder;
            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                if(view==null){
                    view=getLayoutInflater().inflate(R.layout.adapter_h5sport,null);
                    h5SportViewHorlder=new H5SportViewHorlder(view);
                    view.setTag(h5SportViewHorlder);
                }else {
                    h5SportViewHorlder=(H5SportViewHorlder)view.getTag();
                }
                return view;
            }

            class H5SportViewHorlder {
                H5SportViewHorlder (View view){

                }
            }
        });

    }
}
