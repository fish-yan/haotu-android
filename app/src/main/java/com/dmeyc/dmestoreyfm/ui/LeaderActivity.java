package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.LeaderListBean;
import com.dmeyc.dmestoreyfm.bean.YFMUserBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;
import com.dmeyc.dmestoreyfm.wedgit.NoScrollListView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

public class LeaderActivity  extends BaseActivity {
    @Bind(R.id.cv_header)
    CircleImageView cv_header;
    @Bind(R.id.tv_name)
    TextView tv_name;
    @Bind(R.id.iv_sex)
    ImageView iv_sex;
    ArrayList<LeaderListBean.DataBean> almanager=new ArrayList<>();
    ArrayList<LeaderListBean.DataBean> almember=new ArrayList<>();
    @Bind(R.id.lv_vip_meneger)
    NoScrollListView lv_vip_meneger;
    @Bind(R.id.lv_vip_member)
    NoScrollListView lv_vip_member;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_leader;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
          getData();
    }
    LeaderListBean leaderListBean;
    public void getData(){

                RestClient.getYfmNovate(this).post(Constant.API.YFM_GETLEADERLIST, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                 .addParams("group_id", getIntent().getIntExtra("groupid",-1))
                .build(), new DmeycBaseSubscriber<LeaderListBean>() {
            @Override
            public void onSuccess(LeaderListBean bean) {
//                ToastUtil.show(bean.getMsg());
                leaderListBean=bean;
                for (int i=0;i<bean.getData().size();i++){
                    if(bean.getData().get(i).getIdentify_status().equals("2")){
                        almanager.add(bean.getData().get(i));
                    }else if(bean.getData().get(i).getIdentify_status().equals("3")) {
                        almember.add(bean.getData().get(i));
                    }
                }
                tv_name.setText(bean.getData().get(0).getNick_name());
                GlideUtil.loadImage(LeaderActivity.this,bean.getData().get(0).getHead_icon(),cv_header);
                if("1".equals(bean.getData().get(0).getSex())){
                    iv_sex.setBackground(getResources().getDrawable(R.drawable.man_check));
                }else {
                    iv_sex.setBackground(getResources().getDrawable(R.drawable.woman_check));
                   }
                LeaderAdapter  leaderAdapter=new LeaderAdapter();
                lv_vip_member.setAdapter(leaderAdapter);
                LeaderMemberAdapter  leadermember=new LeaderMemberAdapter();
                lv_vip_member.setAdapter(leadermember);
                    }
            });
         }
         @OnClick(R.id.ll_holder)
    public void onClick(View view){
        int viewid=view.getId();
        if(viewid==R.id.ll_holder){
            Intent intent=new Intent();
            intent.putExtra("leaderid",leaderListBean.getData().get(0).getId());
            intent.putExtra("leaderidnaem",leaderListBean.getData().get(0).getNick_name());
            setResult(221,intent);
            finish();
        }
    }

    public class LeaderAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return almanager.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }
        LeaderViewHolder leaderViewHolder;
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if(view==null){
                view=getLayoutInflater().inflate(R.layout.adapter_leaderitem,null);
                leaderViewHolder=new LeaderViewHolder(view);
                view.setTag(leaderListBean);
            }else {
                leaderViewHolder=(LeaderViewHolder) view.getTag();
            }
            GlideUtil.loadImage(LeaderActivity.this,almanager.get(i).getHead_icon(),leaderViewHolder.cv_headermanager);
            leaderViewHolder.tv_namemanger.setText(almanager.get(i).getNick_name());
            if("1".equals(almanager.get(i).getSex())){
                leaderViewHolder.iv_sexmanager.setBackground(getResources().getDrawable(R.drawable.man_check));
            }else {
                leaderViewHolder.iv_sexmanager.setBackground(getResources().getDrawable(R.drawable.woman_check));
            }

            return view ;
        }

        public class LeaderViewHolder {
            CircleImageView cv_headermanager;
            TextView tv_namemanger;
            ImageView iv_sexmanager;
            public LeaderViewHolder(View view){
                cv_headermanager=view.findViewById(R.id.cv_headermanager);
                tv_namemanger=view.findViewById(R.id.tv_namemanger);
                iv_sexmanager=view.findViewById(R.id.iv_sexmanager);
            }
        }
    }

    public class LeaderMemberAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return almember.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }
        LeaderViewHolder leaderViewHolder;
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if(view==null){
                view=getLayoutInflater().inflate(R.layout.adapter_leaderitem,null);
                leaderViewHolder=new LeaderViewHolder(view);
                view.setTag(leaderListBean);
            }else {
                leaderViewHolder=(LeaderViewHolder) view.getTag();
            }
            GlideUtil.loadImage(LeaderActivity.this,almember.get(i).getHead_icon(),leaderViewHolder.cv_headermanager);
            leaderViewHolder.tv_namemanger.setText(almember.get(i).getNick_name());
            if("1".equals(almember.get(i).getSex())){
                leaderViewHolder.iv_sexmanager.setBackground(getResources().getDrawable(R.drawable.man_check));
            }else {
                leaderViewHolder.iv_sexmanager.setBackground(getResources().getDrawable(R.drawable.woman_check));
            }

            return view ;
        }

        public class LeaderViewHolder {
            CircleImageView cv_headermanager;
            TextView tv_namemanger;
            ImageView iv_sexmanager;
            public LeaderViewHolder(View view){
                cv_headermanager=view.findViewById(R.id.cv_headermanager);
                tv_namemanger=view.findViewById(R.id.tv_namemanger);
                iv_sexmanager=view.findViewById(R.id.iv_sexmanager);
            }
        }
    }

}
