package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.ClubResultBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.BlurPopWin;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;

import butterknife.Bind;
import butterknife.OnClick;

public class CulbResultActivity extends BaseActivity {


    @Bind(R.id.lv_pkinglist)
    ListView lv_pkinglist;

    @Bind(R.id.ll_content)
    LinearLayout ll_content;
    @Bind(R.id.ll_top)
    LinearLayout ll_top;

    @Bind(R.id.iv_share)
    ImageView iv_share;
    @Bind(R.id.civ_avatar)
    CircleImageView civ_avatar;
    @Bind(R.id.tv_commname)
    TextView tv_commname;
    @Bind(R.id.tv_batnumber)
    TextView tv_batnumber;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_clubresult;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {

        getData();
//
//        lv_pkinglist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                startActivity(new Intent(CulbResultActivity.this,NewPkResultActivity.class));
//            }
//        });
    }
    @OnClick(R.id.iv_share)
    public void onClick(View view){
        int viewid=view.getId();
        if(viewid==R.id.iv_share){
//            Fglass.blur(ll_content, ll_top, 10, 11);
//            new ShareDialog(this).show();

            new BlurPopWin.Builder(CulbResultActivity.this).setContent("该配合你演出的我,眼视而不见,在比一个最爱你的人即兴表演")
                    //Radius越大耗时越长,被图片处理图像越模糊
                    .setRadius(3).setTitle("我是标题")
                    //设置居中还是底部显示
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
    public void getData(){
//        RestClient.getYfmNovate(this).post(Constant.API.YFM_CLUBRESULT, new ParamMap.Build()
        RestClient.getYfmNovate(this).post(Constant.API.YFM_BRESUT, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
//                .addParams("activity_id",2)
                   .addParams("team_id",getIntent().getIntExtra("time_id",-1))

            .addParams("activity_id",getIntent().getIntExtra("activityid",-1))
                .build(), new DmeycBaseSubscriber<ClubResultBean>() {
            @Override
            public void onSuccess(final ClubResultBean bean) {
//                ToastUtil.show(bean.getMsg());


                 GlideUtil.loadImage(CulbResultActivity.this,bean.getData().getBasicInfo().getGroupLogo(),civ_avatar);
                tv_commname.setText(bean.getData().getBasicInfo().getGroupName());
                tv_batnumber.setText(bean.getData().getBasicInfo().getBattleNumber()+"");
//
                lv_pkinglist.setAdapter(new android.widget.BaseAdapter() {
                    @Override
                    public int getCount() {
                        return bean.getData().getMatchList().size();
                    }

                    @Override
                    public Object getItem(int i) {
                        return null;
                    }

                    @Override
                    public long getItemId(int i) {
                        return 0;
                    }
                    CulbResultActivity.HeroDetailViewHorlder heroDetailViewHorlder;
                    @Override
                    public View getView(int i, View view, ViewGroup viewGroup) {
                        if(view==null){
                            view =getLayoutInflater().inflate(R.layout.adapter_clubresultlist,null);
                            heroDetailViewHorlder=new CulbResultActivity.HeroDetailViewHorlder(view);
                            view.setTag(heroDetailViewHorlder);
                        }else {
                            heroDetailViewHorlder=(CulbResultActivity.HeroDetailViewHorlder)view.getTag();
                        }
                        GlideUtil.loadImage(CulbResultActivity.this,bean.getData().getMatchList().get(i).getTeam_a_member_a_logo(),heroDetailViewHorlder.iv_teamone1);
                        GlideUtil.loadImage(CulbResultActivity.this,bean.getData().getMatchList().get(i).getTeam_a_member_b_logo(),heroDetailViewHorlder.iv_teamone2);
                        GlideUtil.loadImage(CulbResultActivity.this,bean.getData().getMatchList().get(i).getTeam_b_member_a_logo(),heroDetailViewHorlder.iv_teamtwo1);
                        GlideUtil.loadImage(CulbResultActivity.this,bean.getData().getMatchList().get(i).getTeam_b_member_b_logo(),heroDetailViewHorlder.iv_teamtwo2);
                        heroDetailViewHorlder.tv_socker.setText(bean.getData().getMatchList().get(i).getTeam_a_score()+":"+bean.getData().getMatchList().get(i).getTeam_b_score());

                        if(bean.getData().getMatchList().get(i).getTeam_a_member_a_sex().equals("1")){
                            heroDetailViewHorlder.iv_sex1.setBackground(getResources().getDrawable(R.drawable.man_check));
                        }else {
                            heroDetailViewHorlder.iv_sex1.setBackground(getResources().getDrawable(R.drawable.woman_check));
                        }
                        if(bean.getData().getMatchList().get(i).getTeam_a_member_b_sex().equals("1")){
                            heroDetailViewHorlder.iv_sex2.setBackground(getResources().getDrawable(R.drawable.man_check));
                        }else {
                            heroDetailViewHorlder.iv_sex2.setBackground(getResources().getDrawable(R.drawable.woman_check));
                        }
                        if(bean.getData().getMatchList().get(i).getTeam_b_member_a_sex().equals("1")){
                            heroDetailViewHorlder.iv_sex3.setBackground(getResources().getDrawable(R.drawable.man_check));
                        }else {
                            heroDetailViewHorlder.iv_sex3.setBackground(getResources().getDrawable(R.drawable.woman_check));
                        }
                        if(bean.getData().getMatchList().get(i).getTeam_b_member_b_sex().equals("1")){
                            heroDetailViewHorlder.iv_sex4.setBackground(getResources().getDrawable(R.drawable.man_check));
                        }else {
                            heroDetailViewHorlder.iv_sex4.setBackground(getResources().getDrawable(R.drawable.woman_check));
                        }

                        heroDetailViewHorlder.tv_teamonename1.setText(bean.getData().getMatchList().get(i).getTeam_a_member_a_nickname());
                        heroDetailViewHorlder.tv_teamonename2.setText(bean.getData().getMatchList().get(i).getTeam_a_member_b_nickname());
                        heroDetailViewHorlder.tv_teantwonaem1.setText(bean.getData().getMatchList().get(i).getTeam_b_member_a_nickname());
                        heroDetailViewHorlder.tv_teantwonaem2.setText(bean.getData().getMatchList().get(i).getTeam_b_member_b_nickname());
                        return view;
                    }
                });

            }
        });

    }
    public class HeroDetailViewHorlder{
        CircleImageView iv_teamone1,iv_teamone2,iv_teamtwo1,iv_teamtwo2;
        TextView tv_teamonename1,tv_teamonename2,tv_teantwonaem1,tv_teantwonaem2,tv_socker;
        ImageView iv_sex1,iv_sex2,iv_sex3,iv_sex4;
        public HeroDetailViewHorlder(View view){
            iv_teamone1=(CircleImageView) view.findViewById(R.id.iv_teamone1);
            iv_teamone2=(CircleImageView) view.findViewById(R.id.iv_teamone2);
            iv_teamtwo1=(CircleImageView) view.findViewById(R.id.iv_teamtwo1);
            iv_teamtwo2=(CircleImageView) view.findViewById(R.id.iv_teamtwo2);
            tv_teamonename1=(TextView) view.findViewById(R.id.tv_teamonename1);
            tv_teamonename2=(TextView) view.findViewById(R.id.tv_teamonename2);
            tv_teantwonaem1=(TextView) view.findViewById(R.id.tv_teantwonaem1);
            tv_socker=(TextView) view.findViewById(R.id.tv_socker);
            tv_teantwonaem2=(TextView) view.findViewById(R.id.tv_teantwonaem2);

            iv_sex1=(ImageView) view.findViewById(R.id.iv_sex1);
            iv_sex2=(ImageView) view.findViewById(R.id.iv_sex2);
            iv_sex3=(ImageView) view.findViewById(R.id.iv_sex3);
            iv_sex4=(ImageView) view.findViewById(R.id.iv_sex4);
        }
    }

}
