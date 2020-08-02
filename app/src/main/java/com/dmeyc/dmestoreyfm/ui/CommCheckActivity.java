package com.dmeyc.dmestoreyfm.ui;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.CommNoticeBean;
import com.dmeyc.dmestoreyfm.bean.PublishActionAfterBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;

import butterknife.Bind;

public class CommCheckActivity extends BaseActivity {

    @Bind(R.id.lv_checklist)
    ListView lv_checklist;
    @Bind(R.id.tv_nodata)
    TextView tv_nodata;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_commcheck;
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
    ApplyAdapter applyAdapter;
    public void getData(){
        RestClient.getYfmNovate(this).post(Constant.API.YFM_CHECKAPPLY, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
//                 .addParams("user_token", "07b201cfa7fc4e9e966b866b3d01eb8a")
                .build(), new DmeycBaseSubscriber<CommApplyBean>() {
            @Override
            public void onSuccess(final CommApplyBean bean) {
                commApplyBean=bean;
                if(bean.getData().size()==0){
                    tv_nodata.setVisibility(View.VISIBLE);
                    lv_checklist.setVisibility(View.GONE);
                }else {
                    tv_nodata.setVisibility(View.GONE);
                    lv_checklist.setVisibility(View.VISIBLE);
                }
//                ToastUtil.show(bean.getMsg());
                applyAdapter  =new ApplyAdapter();
                lv_checklist.setAdapter(applyAdapter);
            }});
    }
    int clickpos=0;

    public class ApplyAdapter extends BaseAdapter{
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
        CheckCommViewHoder checkCommViewHoder;
        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            if(view==null){
                view=getLayoutInflater().inflate(R.layout.adapter_checkcomm,null);
                checkCommViewHoder=new CheckCommViewHoder(view);
                view.setTag(checkCommViewHoder);
            }else {
                checkCommViewHoder=(CheckCommViewHoder) view.getTag();
            }
            GlideUtil.loadImage(CommCheckActivity.this,commApplyBean.getData().get(i).getUser_logo(),checkCommViewHoder.civ_avatar);
            checkCommViewHoder.tv_title.setText(commApplyBean.getData().get(i).getNick_name());
            checkCommViewHoder.tv_apply.setText("申请加入："+commApplyBean.getData().get(i).getGroup_name()+"  俱乐部");

            if("2".equals(commApplyBean.getData().get(i).getStatus())){
                checkCommViewHoder.tv_losandwin.setVisibility(View.GONE);
                checkCommViewHoder.tv_commperson.setClickable(false);
                checkCommViewHoder.tv_commperson.setText("已同意");
                checkCommViewHoder.tv_commperson.setTextColor(getResources().getColor(R.color.colorsearchhit_c8c8c8));
            }else if("1".equals(commApplyBean.getData().get(i).getStatus())){
                checkCommViewHoder.tv_losandwin.setVisibility(View.VISIBLE);
                checkCommViewHoder.tv_losandwin.setClickable(true);
                checkCommViewHoder.tv_commperson.setClickable(true);
                checkCommViewHoder.tv_commperson.setText("同意");
                checkCommViewHoder.tv_commperson.setTextColor(getResources().getColor(R.color.color_1890FF));
            }else if("3".equals(commApplyBean.getData().get(i).getStatus())){
                checkCommViewHoder.tv_losandwin.setVisibility(View.GONE);
                checkCommViewHoder.tv_commperson.setClickable(false);
                checkCommViewHoder.tv_commperson.setText("已拒绝");
                checkCommViewHoder.tv_commperson.setTextColor(getResources().getColor(R.color.colorsearchhit_c8c8c8));
            }else {
                checkCommViewHoder.tv_losandwin.setVisibility(View.VISIBLE);
                checkCommViewHoder.tv_losandwin.setClickable(true);
                checkCommViewHoder.tv_commperson.setClickable(true);
                checkCommViewHoder.tv_commperson.setText("同意");
                checkCommViewHoder.tv_commperson.setTextColor(getResources().getColor(R.color.color_1890FF));
//            checkCommViewHoder.tv_commperson.setTextColor(getResources().getColor(R.color.colorsearchhit_c8c8c8));
            }
            checkCommViewHoder.tv_commperson.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickpos=i;
                    RestClient.getYfmNovate(CommCheckActivity.this).post(Constant.API.YFM_HOSTAPPLY, new ParamMap.Build()
                            .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
//                            .addParams("user_token", "07b201cfa7fc4e9e966b866b3d01eb8a")
                            .addParams("status", "2")
                            .addParams("examine_id",commApplyBean.getData().get(i).getId())
                            .build(), new DmeycBaseSubscriber<PublishActionAfterBean>() {
                        @Override
                        public void onSuccess(final PublishActionAfterBean bean) {
//                            ToastUtil.show(bean.getMsg());
                            getData();
                            applyAdapter.notifyDataSetChanged();
//                            checkCommViewHoder.tv_commperson.setText("已同意");
                        }});
                }
            });
            checkCommViewHoder.tv_losandwin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickpos=i;
                    RestClient.getYfmNovate(CommCheckActivity.this).post(Constant.API.YFM_HOSTAPPLY, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
//                            .addParams("user_token", "07b201cfa7fc4e9e966b866b3d01eb8a")
                            .addParams("status", "3")
                            .addParams("examine_id",commApplyBean.getData().get(i).getId())
                            .build(), new DmeycBaseSubscriber<PublishActionAfterBean>() {
                        @Override
                        public void onSuccess(final PublishActionAfterBean bean) {
//                            ToastUtil.show(bean.getMsg());
                            getData();
                            applyAdapter.notifyDataSetChanged();
//                            checkCommViewHoder.tv_commperson.setText("已拒绝");
                        }});
                }
            });
            return view;
        }
    }
}
