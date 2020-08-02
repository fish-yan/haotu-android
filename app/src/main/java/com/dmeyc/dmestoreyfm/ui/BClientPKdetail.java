package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.ClubDetailBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;
import com.dmeyc.dmestoreyfm.wedgit.H5BlurPopWin;

import butterknife.Bind;
import butterknife.OnClick;

public class BClientPKdetail  extends BaseActivity {
    @Bind(R.id.lv_pkinglist)
    ListView lv_pkinglist;
    @Bind(R.id.iv_share)
    ImageView iv_share;

    @Bind(R.id.civ_avatar)
    CircleImageView civ_avatar;
    @Bind(R.id.tv_commname)
    TextView tv_commname;
    @Bind(R.id.tv_batnumber)
    TextView tv_batnumber;

    @Bind(R.id.tv_adress)
    TextView tv_adress;
    @Bind(R.id.tv_tiemhor)
    TextView tv_tiemhor;
    @Bind(R.id.tv_activityname)
    TextView tv_activityname;
    @Bind(R.id.tv_groupname)
    TextView tv_groupname;
    @Bind(R.id.tv_owername)
    TextView tv_owername;
    @Bind(R.id.tv_time_mone)
    TextView tv_time_mone;


    String time;
    String data[];
    String hor;
    String day;
    ClubDetailBean clubDetailBean;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_bclientpkdetail;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        getData();
    }

    public void getData(){
        RestClient.getYfmNovate(this).post(Constant.API.YFM_ACTUIBRABK, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
//                .addParams("activityId",2)
                .addParams("activityId",getIntent().getIntExtra("activityid",-1))
                .build(), new DmeycBaseSubscriber<ClubDetailBean>() {
            @Override
            public void onSuccess(final ClubDetailBean bean) {
//                ToastUtil.show(bean.getMsg());

                clubDetailBean=bean;

                tv_activityname.setText(bean.getData().getActivitybaseInfo().getActivityName());
                tv_groupname.setText("俱乐部："+bean.getData().getActivitybaseInfo().getGroupName());
                tv_owername.setText("组织者："+bean.getData().getActivitybaseInfo().getOrganizedName());
                tv_adress.setText("地址："+bean.getData().getActivitybaseInfo().getActivityAddress());
                time=bean.getData().getActivitybaseInfo().getStartTime();
                if(!TextUtils.isEmpty(time)){
                    data=  time.split(" ");
                    day=  data[0];
                    hor=data[1];
                    tv_time_mone.setText(day.split("-")[1]+"月"+day.split("-")[2]+"日");
                    tv_tiemhor.setText(hor.split(":")[0]+":"+hor.split(":")[1]);
                }


                GlideUtil.loadImage(BClientPKdetail.this,bean.getData().getGroupBaseInfo().getGroupLogo(),civ_avatar);
                tv_commname.setText(bean.getData().getGroupBaseInfo().getGroupName());
                tv_batnumber.setText(bean.getData().getGroupBaseInfo().getBattleNumber()+"");
//
                lv_pkinglist.setAdapter(new android.widget.BaseAdapter() {
                    @Override
                    public int getCount() {
                        return bean.getData().getActivityRankResult().size();
                    }

                    @Override
                    public Object getItem(int i) {
                        return null;
                    }

                    @Override
                    public long getItemId(int i) {
                        return 0;
                    }
                    BClientPKdetail.HeroDetailViewHorlder heroDetailViewHorlder;
                    @Override
                    public View getView(int i, View view, ViewGroup viewGroup) {
                        if(view==null){
                            view =getLayoutInflater().inflate(R.layout.adapter_herranklist,null);
                            heroDetailViewHorlder=new BClientPKdetail.HeroDetailViewHorlder(view);
                            view.setTag(heroDetailViewHorlder);
                        }else {
                            heroDetailViewHorlder=(BClientPKdetail.HeroDetailViewHorlder)view.getTag();
                        }

                        heroDetailViewHorlder.tv_ranknum.setText("第"+bean.getData().getActivityRankResult().get(i).getRankingNo()+"名");
                        GlideUtil.loadImage(BClientPKdetail.this,bean.getData().getActivityRankResult().get(i).getMemberALogo(),heroDetailViewHorlder.iv_teamone1);
                        GlideUtil.loadImage(BClientPKdetail.this,bean.getData().getActivityRankResult().get(i).getMemberBLogo(),heroDetailViewHorlder.iv_teamone2);
                       heroDetailViewHorlder.tv_resulnumb.setText(bean.getData().getActivityRankResult().get(i).getWinnerNo()+"胜"+bean.getData().getActivityRankResult().get(i).getLoserNo()+"负");
                      heroDetailViewHorlder.tv_teamonename1.setText(bean.getData().getActivityRankResult().get(i).getMemberANickName());
                       heroDetailViewHorlder.tv_teamonename2.setText(bean.getData().getActivityRankResult().get(i).getMemberBNickName());
                       if(bean.getData().getActivityRankResult().get(i).getMemberASex().equals("1")){
                           heroDetailViewHorlder.iv_iv_sex1.setBackground(getResources().getDrawable(R.drawable.man_check));
                       }else {
                           heroDetailViewHorlder.iv_iv_sex1.setBackground(getResources().getDrawable(R.drawable.woman_check));
                       }
                        if(bean.getData().getActivityRankResult().get(i).getMemberBSex().equals("1")){
                            heroDetailViewHorlder.iv_sex2.setBackground(getResources().getDrawable(R.drawable.man_check));
                        }else {
                            heroDetailViewHorlder.iv_sex2.setBackground(getResources().getDrawable(R.drawable.woman_check));
                        }

                        lv_pkinglist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                startActivity(new Intent(BClientPKdetail.this,CulbResultActivity.class)
                                        .putExtra("activityid",bean.getData().getActivityRankResult().get(i).getActivityId()).putExtra("time_id",bean.getData().getActivityRankResult().get(i).getTeam_id()));
                            }
                        });

                        return view;
                    }
                });

            }
        });

    }
    public class HeroDetailViewHorlder{
        CircleImageView iv_teamone1,iv_teamone2;
        TextView tv_ranknum,tv_resulnumb,tv_teamonename1,tv_teamonename2;
        ImageView iv_sex2,iv_iv_sex1;
        public HeroDetailViewHorlder(View view){
            iv_teamone1=(CircleImageView) view.findViewById(R.id.iv_teamone1);
            iv_teamone2=(CircleImageView) view.findViewById(R.id.iv_teamone2);

            tv_ranknum=(TextView) view.findViewById(R.id.tv_ranknum);
            tv_resulnumb=(TextView) view.findViewById(R.id.tv_resulnumb);
            tv_teamonename1=(TextView) view.findViewById(R.id.tv_teamonename1);
            tv_teamonename2=(TextView)view.findViewById(R.id.tv_teamonename2);

            iv_sex2=(ImageView) view.findViewById(R.id.iv_sex2);
            iv_iv_sex1=(ImageView) view.findViewById(R.id.iv_iv_sex1);

        }
    }

    @OnClick(R.id.iv_share)
    public void onClick(View view){
        int viewid=view.getId();
        if(viewid==R.id.iv_share){

            new H5BlurPopWin.Builder(BClientPKdetail.this).setContent(clubDetailBean.getData().getActivitybaseInfo().getGroupName())
                    //Radius越大耗时越长,被图片处理图像越模糊
                    .setRadius(3).setTitle(clubDetailBean.getData().getActivitybaseInfo().getActivityName())
                    //设置居中还是底部显示
//                    .setId(getIntent().getIntExtra("activityid",-1),getIntent().getStringExtra("ispk"))
//                    .setId(getIntent().getIntExtra("activityid",-1))
                    .setUrl(Constant.API.YFM_SHAREBASE_URL+Constant.API.SHARE_BRESULT+"&activity_id="+getIntent().getIntExtra("activityid",-1))
                    .setshowAtLocationType(1)
                    .setShowCloseButton(true)
                    .setOutSideClickable(false)
                    /*.onClick(new BlurPopWin.PopupCallback() {
                        @Override
                        public void onClick(@NonNull BlurPopWin blurPopWin) {
                            Toast.makeText(MainActivity.this, "中间被点了", Toast.LENGTH_SHORT).show();
                            blurPopWin.dismiss();
                        }
                    })*/.show(iv_share);
        }

    }

}
