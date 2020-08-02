package com.dmeyc.dmestoreyfm.ui;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;
import com.dmeyc.dmestoreyfm.wedgit.H5BlurPopWin;
import com.tamic.novate.Throwable;

import butterknife.Bind;
import butterknife.OnClick;

public class NewPkResultActivity extends BaseActivity {
    @Bind(R.id.lv_pkinglist)
    ListView lv_pkinglist;
@Bind(R.id.iv_share)
    ImageView iv_share;

@Bind(R.id.iv_teamone)
CircleImageView iv_teamone;
    @Bind(R.id.iv_teamtwo)
    CircleImageView iv_teamtwo;
    @Bind(R.id.tv_teamonename)
    TextView tv_teamonename;
    @Bind(R.id.tv_teantwonaem)
    TextView tv_teantwonaem;
    @Bind(R.id.tv_time_state)
    TextView tv_time_state;
    @Bind(R.id.tv_sorcker)
    TextView tv_sorcker;
@Bind(R.id.tv_suppoutname)
TextView tv_suppoutname;


    @Override
    protected int getLayoutRes() {
        return R.layout.activity_newpkresult;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {


//        lv_pkinglist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                startActivity(new Intent(NewPkResultActivity.this,NewPkResultActivity.class));
//            }
//        });
//if("1".equals(getIntent().getStringExtra("isgover"))){
//    goverdata();
//}else {
    setDate();
//}


    }
private String Shareurl;
    @OnClick(R.id.iv_share)
    public void onClick(View view){
        int viewid=view.getId();
        if(viewid== R.id.iv_share){
            if(newPKResultBean==null){
                ToastUtil.show("暂无数据");
                return;
            }
            if(newPKResultBean.getData()==null){
                ToastUtil.show("暂无数据");
                return;
            }
            if("1".equals(getIntent().getStringExtra("isgover"))){
                Shareurl= Constant.API.YFM_SHAREBASE_URL+ Constant.API.SHARE_ALLPKRESULT+"&activity_id="+getIntent().getIntExtra("groverpkid",-1)+"&isGovernment=1";
            }else {
                Shareurl= Constant.API.YFM_SHAREBASE_URL+ Constant.API.SHARE_ALLPKRESULT+"&activity_id="+getIntent().getIntExtra("activityid",-1)+"&isGovernment=0";
            }
           new H5BlurPopWin.Builder(NewPkResultActivity.this).setContent(newPKResultBean.getData().getBasicInfo().getPkName())
                    //Radius越大耗时越长,被图片处理图像越模糊
                    .setRadius(3).setTitle(newPKResultBean.getData().getBasicInfo().getGroup_b_name()+"俱乐部PK")
                    //设置居中还是底部显示&activity_id=XX
//                    .setId(getIntent().getIntExtra("activityid",-1),getIntent().getStringExtra("ispk"))
                    .setUrl(Shareurl)
//                    .setId(getIntent().getIntExtra("activityid",-1))
                    .setshowAtLocationType(1)
                    .setShowCloseButton(true)
                    .setOutSideClickable(false)
                    .show(iv_share);
        }
    }
    NewPKResultBean newPKResultBean;
    private String url;
    public void setDate(){
       ParamMap.Build pb= new ParamMap.Build();
        if("1".equals(getIntent().getStringExtra("isgover"))){
            url= Constant.API.YFM_GETBIGRECORD;
            pb .addParams("activity_id", getIntent().getIntExtra("groverpkid",-1));
        }else {
            url= Constant.API.PkMACTCHRESULT;
               pb .addParams("activity_id", getIntent().getIntExtra("activityid",-1));
        }

        RestClient.getYfmNovate(this).post(url, pb

//                .addParams("activity_id", 10)
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .build(), new DmeycBaseSubscriber<NewPKResultBean>() {
            @Override
            public void onSuccess(final NewPKResultBean bean) {
                newPKResultBean=bean;
                GlideUtil.loadImage(NewPkResultActivity.this,bean.getData().getBasicInfo().getGroup_a_logo(),iv_teamone);
                GlideUtil.loadImage(NewPkResultActivity.this,bean.getData().getBasicInfo().getGroup_b_logo(),iv_teamtwo);
                tv_teamonename.setText(bean.getData().getBasicInfo().getGroup_a_name());
                tv_teantwonaem.setText(bean.getData().getBasicInfo().getGroup_b_name());
                String date=bean.getData().getBasicInfo().getStart_time().split(" ")[0];
              String reltiem=  date.split("-")[1]+"月"+date.split("-")[2]+"日";
              if("0".equals(bean.getData().getBasicInfo().getStatus())){
                  tv_time_state.setText(reltiem+"  配对中");
              }else if("1".equals(bean.getData().getBasicInfo().getStatus())){
                  tv_time_state.setText(reltiem+"  报名中");
              }else if("2".equals(bean.getData().getBasicInfo().getStatus())){
                  tv_time_state.setText(reltiem+"  报名结束");
              }else if("3".equals(bean.getData().getBasicInfo().getStatus())){
                  tv_time_state.setText(reltiem+"  活动结束");
              }else if("5".equals(bean.getData().getBasicInfo().getStatus())){
                  tv_time_state.setText(reltiem+"  活动暂停");
              }else if("6".equals(bean.getData().getBasicInfo().getStatus())){
                  tv_time_state.setText(reltiem+"  活动取消");
              }

                tv_sorcker.setText(bean.getData().getBasicInfo().getGroup_a_score()+":"+bean.getData().getBasicInfo().getGroup_b_score());

                lv_pkinglist.setAdapter(new android.widget.BaseAdapter() {
                    @Override
                    public int getCount() {
                        return bean.getData().getList().size();
                    }

                    @Override
                    public Object getItem(int i) {
                        return null;
                    }

                    @Override
                    public long getItemId(int i) {
                        return 0;
                    }
                    NewPkViewHolder newPkViewHolder;
                    @Override
                    public View getView(int i, View view, ViewGroup viewGroup) {
                        if(view==null){
                            view =getLayoutInflater().inflate(R.layout.adapter_newpkresult,null);
                            newPkViewHolder=new NewPkViewHolder(view);
                            view.setTag(newPkViewHolder);
                        }else {
                            newPkViewHolder=(NewPkViewHolder) view.getTag();
                        }
                        GlideUtil.loadImage(NewPkResultActivity.this,bean.getData().getList().get(i).getTeam_a_member_a_logo(),newPkViewHolder.iv_teamone1);
                        GlideUtil.loadImage(NewPkResultActivity.this,bean.getData().getList().get(i).getTeam_a_member_b_logo(),newPkViewHolder.iv_teamone2);
                        GlideUtil.loadImage(NewPkResultActivity.this,bean.getData().getList().get(i).getTeam_b_member_a_logo(),newPkViewHolder.iv_teamtwo1);
                        GlideUtil.loadImage(NewPkResultActivity.this,bean.getData().getList().get(i).getTeam_b_member_b_logo(),newPkViewHolder.iv_teamtwo2);
                        newPkViewHolder.tv_teamonename1.setText(bean.getData().getList().get(i).getTeam_a_member_a_nickname());
                        newPkViewHolder.tv_teamonename2.setText(bean.getData().getList().get(i).getTeam_a_member_b_nickname());
                        newPkViewHolder.tv_teantwonaem1.setText(bean.getData().getList().get(i).getTeam_b_member_a_nickname());
                        newPkViewHolder.tv_teantwonaem2.setText(bean.getData().getList().get(i).getTeam_b_member_b_nickname());

                        if(bean.getData().getList().get(i).getTeam_a_member_a_sex().equals("1")){
                            newPkViewHolder.iv_sex1.setBackground(getResources().getDrawable(R.drawable.man_check));
                        }else {
                            newPkViewHolder.iv_sex1.setBackground(getResources().getDrawable(R.drawable.woman_check));
                        }
                        if(bean.getData().getList().get(i).getTeam_a_member_b_sex().equals("1")){
                            newPkViewHolder.iv_sex2.setBackground(getResources().getDrawable(R.drawable.man_check));
                        }else {
                            newPkViewHolder.iv_sex2.setBackground(getResources().getDrawable(R.drawable.woman_check));
                        }
                        if(bean.getData().getList().get(i).getTeam_b_member_a_sex().equals("1")){
                            newPkViewHolder.iv_sex3.setBackground(getResources().getDrawable(R.drawable.man_check));
                        }else {
                            newPkViewHolder.iv_sex3.setBackground(getResources().getDrawable(R.drawable.woman_check));
                        }
                        if(bean.getData().getList().get(i).getTeam_b_member_b_sex().equals("1")){
                            newPkViewHolder.iv_sex4.setBackground(getResources().getDrawable(R.drawable.man_check));
                        }else {
                            newPkViewHolder.iv_sex4.setBackground(getResources().getDrawable(R.drawable.woman_check));
                        }

                        newPkViewHolder.tv_socker.setText(bean.getData().getList().get(i).getTeam_a_score()+":"+bean.getData().getList().get(i).getTeam_b_score());
                        String date=bean.getData().getBasicInfo().getStart_time().split(" ")[0];
                        String reltiem=  date.split("-")[1]+"月"+date.split("-")[2]+"日";
                        newPkViewHolder.tv_date.setText(reltiem);
                        return view;
                    }
                });
            }
            @Override
            public void onError(Throwable e) {

            }
        });
    }

    public class NewPkViewHolder {
        CircleImageView iv_teamtwo1,iv_teamtwo2,iv_teamone2,iv_teamone1;
        TextView tv_teantwonaem1,tv_teantwonaem2,tv_teamonename1,tv_teamonename2,tv_socker,tv_date;
        ImageView iv_sex4,iv_sex3,iv_sex2,iv_sex1;
        public NewPkViewHolder(View view){
            iv_teamtwo1=(CircleImageView) view.findViewById(R.id.iv_teamtwo1);
            iv_teamtwo2=(CircleImageView) view.findViewById(R.id.iv_teamtwo2);
            iv_teamone2=(CircleImageView) view.findViewById(R.id.iv_teamone2);
            iv_teamone1=(CircleImageView) view.findViewById(R.id.iv_teamone1);
            tv_teantwonaem1=(TextView) view.findViewById(R.id.tv_teantwonaem1);
            tv_teantwonaem2=(TextView) view.findViewById(R.id.tv_teantwonaem2);
            tv_teamonename1=(TextView) view.findViewById(R.id.tv_teamonename1);
            tv_teamonename2=(TextView) view.findViewById(R.id.tv_teamonename2);
            tv_socker=(TextView) view.findViewById(R.id.tv_socker);
            tv_date=(TextView) view.findViewById(R.id.tv_date);
            iv_sex4=view.findViewById(R.id.iv_sex4);
            iv_sex3=view.findViewById(R.id.iv_sex3);
            iv_sex2=view.findViewById(R.id.iv_sex2);
            iv_sex1=view.findViewById(R.id.iv_sex1);
        }
    }
}
