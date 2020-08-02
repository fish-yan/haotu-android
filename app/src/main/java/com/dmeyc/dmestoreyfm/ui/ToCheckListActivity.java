package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.PublishActionAfterBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;

import butterknife.Bind;

public class ToCheckListActivity  extends BaseActivity {

    @Bind(R.id.lv_checklist)
    ListView lv_checklist;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_tochcklist;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        getData();
    }
    public class CheckCommViewHoder{
        CircleImageView civ_avatar;
        TextView tv_title,tv_apply, tv_losandwin,tv_commperson;
        public CheckCommViewHoder(View view){
            civ_avatar=view.findViewById(R.id.civ_avatar);
            tv_title=view.findViewById(R.id.tv_title);
            tv_apply=view.findViewById(R.id.tv_apply);
            tv_losandwin=view.findViewById(R.id.tv_losandwin);
            tv_commperson=view.findViewById(R.id.tv_commperson);
        }
    }
    CommApplyBean commApplyBean;
    public void getData(){
        RestClient.getYfmNovate(this).post(Constant.API.YFM_CHALANGELIST, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("group_pk_id", getIntent().getIntExtra("PK_groupid",-1))
//                 .addParams("group_pk_id", getIntent().getIntExtra("PK_groupid",-1))
                .build(), new DmeycBaseSubscriber<CommApplyBean>() {
            @Override
            public void onSuccess(final CommApplyBean bean) {
                commApplyBean=bean;
//                ToastUtil.show(bean.getMsg());
//                if("0".equals(getIntent().getStringExtra("is_ower"))){
//
//                }
                ToCheckListActivity.ApplyAdapter applyAdapter=new ToCheckListActivity.ApplyAdapter();
                lv_checklist.setAdapter(applyAdapter);
                lv_checklist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        startActivity(new Intent(ToCheckListActivity.this,ChartInforActivity.class).putExtra("group_id",commApplyBean.getData().get(position).getGroupChallengeId()));
                    }
                });
            }});
    }

    public class ApplyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return commApplyBean.getData().size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }
        ToCheckListActivity.CheckCommViewHoder checkCommViewHoder;
        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            if(view==null){
                view=getLayoutInflater().inflate(R.layout.adapter_tochalanelist,null);
                checkCommViewHoder=new ToCheckListActivity.CheckCommViewHoder(view);
                view.setTag(checkCommViewHoder);
            }else {
                checkCommViewHoder=(ToCheckListActivity.CheckCommViewHoder) view.getTag();
            }
            GlideUtil.loadImage(ToCheckListActivity.this,commApplyBean.getData().get(i).getGroupLogo(),checkCommViewHoder.civ_avatar);
            checkCommViewHoder.tv_title.setText(commApplyBean.getData().get(i).getGroupName());
            checkCommViewHoder.tv_apply.setText("申请加入："+commApplyBean.getData().get(i).getGroup_name()+"  俱乐部");


            if("2".equals(commApplyBean.getData().get(i).getStatus())){
                checkCommViewHoder.tv_losandwin.setVisibility(View.GONE);
                checkCommViewHoder.tv_commperson.setClickable(false);
                checkCommViewHoder.tv_commperson.setText("已同意");
                checkCommViewHoder.tv_commperson.setTextColor(getResources().getColor(R.color.colorsearchhit_c8c8c8));
            }else if("1".equals(commApplyBean.getData().get(i).getStatus())){
                checkCommViewHoder.tv_losandwin.setVisibility(View.VISIBLE);
                checkCommViewHoder.tv_commperson.setClickable(true);
//                checkCommViewHoder.tv_commperson.setText("已同意");
//                checkCommViewHoder.tv_commperson.setTextColor(getResources().getColor(R.color.colorsearchhit_c8c8c8));
            }else if("3".equals(commApplyBean.getData().get(i).getStatus())){
                checkCommViewHoder.tv_losandwin.setVisibility(View.GONE);
                checkCommViewHoder.tv_commperson.setClickable(false);
                checkCommViewHoder.tv_commperson.setText("已拒绝");
                checkCommViewHoder.tv_commperson.setTextColor(getResources().getColor(R.color.colorsearchhit_c8c8c8));
            }else {
                checkCommViewHoder.tv_losandwin.setVisibility(View.GONE);
                checkCommViewHoder.tv_commperson.setClickable(false);
                checkCommViewHoder.tv_commperson.setText("已同意");
                checkCommViewHoder.tv_commperson.setTextColor(getResources().getColor(R.color.colorsearchhit_c8c8c8));
            }

            if("0".equals(getIntent().getStringExtra("is_ower"))){
                checkCommViewHoder.tv_losandwin.setVisibility(View.GONE);
                checkCommViewHoder.tv_commperson.setVisibility(View.GONE);
            }
//            if("2".equals(commApplyBean.getData().get(i).getStatus())){
//                checkCommViewHoder.tv_losandwin.setVisibility(View.GONE);
//                checkCommViewHoder.tv_commperson.setClickable(false);
//                checkCommViewHoder.tv_commperson.setText("已同意");
//                checkCommViewHoder.tv_commperson.setTextColor(getResources().getColor(R.color.colorsearchhit_c8c8c8));
//            }else {
//                checkCommViewHoder.tv_losandwin.setVisibility(View.GONE);
//                checkCommViewHoder.tv_commperson.setClickable(false);
//                checkCommViewHoder.tv_commperson.setText("已同意");
//                checkCommViewHoder.tv_commperson.setTextColor(getResources().getColor(R.color.colorsearchhit_c8c8c8));
//            }
            checkCommViewHoder.tv_commperson.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RestClient.getYfmNovate(ToCheckListActivity.this).post(Constant.API.YFM_CHALANGEAPPLY, new ParamMap.Build()
                            .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
//                            .addParams("user_token", "07b201cfa7fc4e9e966b866b3d01eb8a")
                            .addParams("status", "2")
                            .addParams("groupPkAcceptId",commApplyBean.getData().get(i).getId())
                            .build(), new DmeycBaseSubscriber<PublishActionAfterBean>() {
                        @Override
                        public void onSuccess(final PublishActionAfterBean bean) {
//                            ToastUtil.show(bean.getMsg());
                            getData();
//                            checkCommViewHoder.tv_commperson.setText("已同意");
                        }});
                }
            });
            checkCommViewHoder.tv_losandwin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RestClient.getYfmNovate(ToCheckListActivity.this).post(Constant.API.YFM_CHALANGEAPPLY, new ParamMap.Build()
                            .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
//                            .addParams("user_token", "07b201cfa7fc4e9e966b866b3d01eb8a")
                            .addParams("status", "3")
                            .addParams("groupPkAcceptId",commApplyBean.getData().get(i).getId())
                            .build(), new DmeycBaseSubscriber<PublishActionAfterBean>() {
                        @Override
                        public void onSuccess(final PublishActionAfterBean bean) {
//                            ToastUtil.show(bean.getMsg());
                            getData();
//                            checkCommViewHoder.tv_commperson.setText("已拒绝");
                        }});
                }
            });
            return view;
        }
    }

}
