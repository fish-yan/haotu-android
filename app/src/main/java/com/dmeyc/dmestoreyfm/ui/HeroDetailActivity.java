package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.text.TextUtils;
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
import com.dmeyc.dmestoreyfm.bean.HeroRankBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.dialog.ShareDialog;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.BlurPopWin;
import com.dmeyc.dmestoreyfm.utils.Fglass;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.utils.Util;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;

import butterknife.Bind;
import butterknife.OnClick;

public class HeroDetailActivity extends BaseActivity {

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

    @Bind(R.id.tv_nodata)
    TextView tv_nodata;
    private int posclick;
    @Override
    protected int getLayoutRes() {
        return R.layout.activty_herodetail;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        GlideUtil.loadImage(HeroDetailActivity.this,getIntent().getStringExtra("groupicon"),civ_avatar);
        tv_commname.setText(getIntent().getStringExtra("gorupname"));
        getData();

        lv_pkinglist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                posclick=i;
                startActivity(new Intent(HeroDetailActivity.this,NewPkResultActivity.class));
            }
        });
    }
    String title;
    @OnClick({R.id.iv_share,R.id.civ_avatar})
    public void onClick(View view){
        int viewid=view.getId();
        if(viewid==R.id.iv_share){
//            Fglass.blur(ll_content, ll_top, 10, 11);
//            new ShareDialog(this).show();
if(pkHerstoryListBean!=null){
    if(pkHerstoryListBean.getData()==null||pkHerstoryListBean.getData().size()==0){
        title="暂无";
    }else {
        title=pkHerstoryListBean.getData().get(0).getPk_group_name();
    }
    new BlurPopWin.Builder(HeroDetailActivity.this).setContent(getIntent().getStringExtra("gorupname"))
            //Radius越大耗时越长,被图片处理图像越模糊
            .setRadius(3).setTitle(title)
            .setUrl(Constant.API.YFM_SHAREBASE_URL+Constant.API.SHARE_CHERODETEAL+"&&groupId="+getIntent().getIntExtra("groupid",-1))
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

        }else if(R.id.civ_avatar==viewid){
            startActivity(new Intent(HeroDetailActivity.this,ChartInforActivity.class).putExtra("group_id",getIntent().getIntExtra("groupid",-1)));

        }
    }
    PKHerstoryListBean pkHerstoryListBean;
public void getData(){
    RestClient.getYfmNovate(this).post(Constant.API.YFM_HERORHISRORY, new ParamMap.Build()
            .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
            .addParams("group_id",getIntent().getIntExtra("groupid",-1))
//            .addParams("group_id",6)
            .build(), new DmeycBaseSubscriber<PKHerstoryListBean>() {
        @Override
        public void onSuccess(final PKHerstoryListBean bean) {
//            ToastUtil.show(bean.getMsg());
            pkHerstoryListBean=bean;
            if(bean.getData().size()==0){
                tv_nodata.setVisibility(View.VISIBLE);
                lv_pkinglist.setVisibility(View.GONE);
            }else {
                tv_nodata.setVisibility(View.GONE);
                lv_pkinglist.setVisibility(View.VISIBLE);
            }
            lv_pkinglist.setAdapter(new android.widget.BaseAdapter() {
                @Override
                public int getCount() {
                    return bean.getData().size();
                }

                @Override
                public Object getItem(int i) {
                    return null;
                }

                @Override
                public long getItemId(int i) {
                    return 0;
                }
                HeroDetailViewHorlder heroDetailViewHorlder;
                @Override
                public View getView(int i, View view, ViewGroup viewGroup) {
                    if(view==null){
                        view =getLayoutInflater().inflate(R.layout.adapter_herodetaile,null);
                        heroDetailViewHorlder=new HeroDetailViewHorlder(view);
                        view.setTag(heroDetailViewHorlder);
                    }else {
                        heroDetailViewHorlder=(HeroDetailViewHorlder)view.getTag();
                    }
                    GlideUtil.loadImage(HeroDetailActivity.this,bean.getData().get(i).getGroupLogo(),heroDetailViewHorlder.iv_teamone);
                    heroDetailViewHorlder.tv_teanmane.setText(bean.getData().get(i).getPk_activity_name());
                    heroDetailViewHorlder.tv_adress.setText(bean.getData().get(i).getActivity_address());
                    heroDetailViewHorlder.tv_date.setText(bean.getData().get(i).getStart_time());
                    heroDetailViewHorlder.tv_pksorce.setText(bean.getData().get(i).getGroup_a_win_num()+"比"+bean.getData().get(i).getGroup_b_win_num());
                    if(bean.getData().get(i).getIsSuccess().equals("0")){
                        heroDetailViewHorlder.iv_worn.setBackground(getResources().getDrawable(R.drawable.lost_pk));
                    }else {
                        heroDetailViewHorlder.iv_worn.setBackground(getResources().getDrawable(R.drawable.pkdetail_won));
                    }
                    return view;
                }
            });

        }
    });

}
public class HeroDetailViewHorlder{

CircleImageView iv_teamone;
TextView tv_teanmane,tv_adress,tv_date,tv_pksorce;
ImageView iv_worn;
        public HeroDetailViewHorlder(View view){
            iv_teamone=(CircleImageView) view.findViewById(R.id.iv_teamone);
            tv_teanmane=(TextView) view.findViewById(R.id.tv_teanmane);
            tv_adress=(TextView) view.findViewById(R.id.tv_adress);
            tv_date=(TextView) view.findViewById(R.id.tv_date);
            iv_worn=(ImageView)view.findViewById(R.id.iv_worn);
            tv_pksorce=(TextView) view.findViewById(R.id.tv_pksorce);
        }
}

}
