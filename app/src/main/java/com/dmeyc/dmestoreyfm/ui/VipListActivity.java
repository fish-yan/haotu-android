package com.dmeyc.dmestoreyfm.ui;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;

public class VipListActivity extends BaseActivity {

    private ListView lv_vip_manlist;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_vipmanager;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        lv_vip_manlist=(ListView) mRootView.findViewById(R.id.lv_vip_manlist);
        lv_vip_manlist.setAdapter(new BaseAdapter() {
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

            ViewHoder viewHoder;
            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                if(view==null){
                    view=  getLayoutInflater().inflate(R.layout.adapter_incommitem,null);
                    viewHoder=new ViewHoder(view);
                    view.setTag(viewHoder);
                }else {
                    viewHoder=  (ViewHoder) view.getTag();
                }
                return view;
            }
        });
    }

        class ViewHoder{
                ImageView iv_commheader;
                public ViewHoder(View view){
                    iv_commheader= view.findViewById(R.id.iv_commheader);
                }
        }
}
