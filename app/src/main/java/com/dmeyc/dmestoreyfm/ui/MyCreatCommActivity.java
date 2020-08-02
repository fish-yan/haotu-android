package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.CustomLazyViewPagerAdapter;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BaseFragment;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.MyCommListBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.fragment.MyCommFragment;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;
import com.tamic.novate.Throwable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class MyCreatCommActivity extends BaseActivity {
@Bind(R.id.lv_createcommlist)
    ListView lv_createcommlist;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_mycreatecomm;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {


    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }
    MyCommListBean dataBean;
    private void getData() {

        RestClient.getYfmNovate(this).post(Constant.API.YFM_MYCOMMlist, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("status", "1")
                .build(), new DmeycBaseSubscriber<MyCommListBean>() {
            @Override
            public void onSuccess(final MyCommListBean bean) {
                dataBean=bean;
                MyCOmmAdapter myCOmmAdapter=new MyCOmmAdapter();
                lv_createcommlist.setAdapter(myCOmmAdapter);
            }
            @Override
            public void onError(Throwable e) {

            }
        });
        lv_createcommlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
     startActivity(new Intent(MyCreatCommActivity.this,MyCommEditeActivity.class).putExtra("groupid",dataBean.getData().get(i).getGroup_id()));
            }
        });
    }



    @OnClick(R.id.tv_right_title_bar)
    public void onClick(View view){
        int ivewid=view.getId();
        if(R.id.tv_right_title_bar==ivewid){
//            startActivity(new Intent(MyCommActivity.this,CommCheckActivity.class));
        }
    }

    public class MyCOmmAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return dataBean.getData().size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }
        CommViewHorler commViewHorler;
        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            if(view==null){
                view=getLayoutInflater().inflate(R.layout.adapter_mycommlist,null);
                commViewHorler=new CommViewHorler(view);
                view.setTag(commViewHorler);
            }else {
                commViewHorler=(CommViewHorler) view.getTag();
            }
            GlideUtil.loadImage(MyCreatCommActivity.this,dataBean.getData().get(i).getGroup_logo(),commViewHorler.cv_commhead);
            commViewHorler.tv_name.setText(dataBean.getData().get(i).getGroup_name());

            if(dataBean.getData().get(i).getGroup_type().equals("1")){
                commViewHorler.iv_grouptype.setVisibility(View.GONE);
            }else if(dataBean.getData().get(i).getGroup_type().equals("3")){
                commViewHorler.iv_grouptype.setVisibility(View.VISIBLE);
                commViewHorler.iv_grouptype.setBackground(getResources().getDrawable(R.drawable.teach_type));
            }else if(dataBean.getData().get(i).getGroup_type().equals("4")){
                commViewHorler.iv_grouptype.setVisibility(View.VISIBLE);
                commViewHorler.iv_grouptype.setBackground(getResources().getDrawable(R.drawable.instuce_type));
            }else if(dataBean.getData().get(i).getGroup_type().equals("5")){
                commViewHorler.iv_grouptype.setVisibility(View.VISIBLE);
                commViewHorler.iv_grouptype.setBackground(getResources().getDrawable(R.drawable.buess_type));
            }
            commViewHorler.cv_commhead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MyCreatCommActivity.this,ChartInforActivity.class).putExtra("group_id",(int)dataBean.getData().get(i).getGroup_id()));

                }
            });

            return view;
        }
        public class CommViewHorler{
            CircleImageView cv_commhead;
            ImageView iv_grouptype;
            TextView tv_name;
            public CommViewHorler(View view){
                iv_grouptype=view.findViewById(R.id.iv_grouptype);
                cv_commhead=(CircleImageView) view.findViewById(R.id.cv_commhead);
                tv_name=(TextView) view.findViewById(R.id.tv_name);
            }

        }
    }
}
