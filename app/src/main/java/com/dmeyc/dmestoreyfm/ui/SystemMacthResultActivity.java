package com.dmeyc.dmestoreyfm.ui;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.SystemResultBean;
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

public class SystemMacthResultActivity extends BaseActivity {
    @Bind(R.id.lv_pkinglist)
    ListView lv_pkinglist;
    ResultAdapter resultAdapter ;
    SystemResultBean systemResultBean;
    @Bind(R.id.tv_nodata)
    TextView tv_nodata;
    @Bind(R.id.tv_one)
    TextView tv_one;
    @Bind(R.id.tv_two)
    TextView tv_two;
    @Bind(R.id.tv_three)
    TextView tv_three;
    @Bind(R.id.tv_foru)
    TextView tv_foru;
    @Bind(R.id.tv_five)
    TextView tv_five;
    int stage=2;
    @Bind(R.id.iv_share)
    ImageView iv_share;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_systemmachthresult;
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

        RestClient.getYfmNovate(this).post(Constant.API.YFM_SYSTEMRESULT, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("activityId", getIntent().getIntExtra("pk_activityid",-1))
                .addParams("stage",stage)
                .build(), new DmeycBaseSubscriber<SystemResultBean>() {
            @Override
            public void onSuccess(final SystemResultBean bean) {
//                ToastUtil.show(bean.getMsg());
                if(bean.getData().size()==0){
                    tv_nodata.setVisibility(View.VISIBLE);
                    lv_pkinglist.setVisibility(View.GONE);
                }else {
                    tv_nodata.setVisibility(View.GONE);
                    lv_pkinglist.setVisibility(View.VISIBLE);
                }
                systemResultBean=bean;
                resultAdapter  =new ResultAdapter();
                lv_pkinglist.setAdapter(resultAdapter);
            }
        });
    }
    private String Shareurl;
    @OnClick({R.id.tv_one, R.id.tv_two, R.id.tv_three, R.id.tv_foru, R.id.tv_five, R.id.iv_share})
    public void onClick(View view){
        int viewid=view.getId();
        if(viewid== R.id.tv_one){
            tv_one.setTextColor(getResources().getColor(R.color.black));
            tv_two.setTextColor(getResources().getColor(R.color.gray_deep));
            tv_foru.setTextColor(getResources().getColor(R.color.gray_deep));
            tv_three.setTextColor(getResources().getColor(R.color.gray_deep));
            tv_five.setTextColor(getResources().getColor(R.color.gray_deep));

            stage=2;
            getData();
            resultAdapter.notifyDataSetChanged();
        }else if(viewid== R.id.tv_two){
            stage=3;
            tv_one.setTextColor(getResources().getColor(R.color.gray_deep));
            tv_two.setTextColor(getResources().getColor(R.color.black));
            tv_foru.setTextColor(getResources().getColor(R.color.gray_deep));
            tv_three.setTextColor(getResources().getColor(R.color.gray_deep));
            tv_five.setTextColor(getResources().getColor(R.color.gray_deep));
            getData();
            resultAdapter.notifyDataSetChanged();
        }else if(viewid== R.id.tv_three){
            stage=4;
            tv_one.setTextColor(getResources().getColor(R.color.gray_deep));
            tv_two.setTextColor(getResources().getColor(R.color.gray_deep));
            tv_foru.setTextColor(getResources().getColor(R.color.gray_deep));
            tv_three.setTextColor(getResources().getColor(R.color.black));
            tv_five.setTextColor(getResources().getColor(R.color.gray_deep));
            getData();
            resultAdapter.notifyDataSetChanged();
        }else if(viewid== R.id.tv_foru){
            stage=5;
            tv_one.setTextColor(getResources().getColor(R.color.gray_deep));
            tv_two.setTextColor(getResources().getColor(R.color.gray_deep));
            tv_foru.setTextColor(getResources().getColor(R.color.black));
            tv_three.setTextColor(getResources().getColor(R.color.gray_deep));
            tv_five.setTextColor(getResources().getColor(R.color.gray_deep));
            getData();
            resultAdapter.notifyDataSetChanged();
        }else if(viewid== R.id.tv_five){
            stage=6;
            tv_one.setTextColor(getResources().getColor(R.color.gray_deep));
            tv_two.setTextColor(getResources().getColor(R.color.gray_deep));
            tv_foru.setTextColor(getResources().getColor(R.color.gray_deep));
            tv_three.setTextColor(getResources().getColor(R.color.gray_deep));
            tv_five.setTextColor(getResources().getColor(R.color.black));
            getData();
            resultAdapter.notifyDataSetChanged();
        }else if(viewid== R.id.iv_share){
            if(systemResultBean==null){
                ToastUtil.show("暂无数据");
                return;
            }
            if(systemResultBean.getData()==null){
                ToastUtil.show("暂无数据");
                return;
            }

            Shareurl= Constant.API.YFM_SHAREBASE_URL+ Constant.API.SHARE_BIGRECORD+"&activityId="+getIntent().getIntExtra("acid",-1)+"&stage=2";

            new H5BlurPopWin.Builder(SystemMacthResultActivity.this).setContent(getIntent().getStringExtra("groupname"))
                    //Radius越大耗时越长,被图片处理图像越模糊
                    .setRadius(3).setTitle("晋级记录")
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
    public class ResultAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return systemResultBean.getData().size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }
        SocerAdapterHorder socerAdapterHorder;
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if(view==null){

                view=getLayoutInflater().inflate(R.layout.adaper_socker,null);
                socerAdapterHorder=new SocerAdapterHorder(view);
                view.setTag(socerAdapterHorder);
            }else {
                socerAdapterHorder=(SocerAdapterHorder)view.getTag();
            }
            socerAdapterHorder.tv_team.setText(systemResultBean.getData().get(i).getGroupName()+"组");
            socerAdapterHorder.mResultLayout.removeAllViews();
            if(systemResultBean.getData().get(i).getGroupPlans() != null && systemResultBean.getData().get(i).getGroupPlans().size() > 0){
                for(int m = 0 ; m <  systemResultBean.getData().get(i).getGroupPlans().size() ; m++){
                    View childView = View.inflate(SystemMacthResultActivity.this,R.layout.item_adapter_socker,null);
                    TextView mRankNo = childView.findViewById(R.id.mRankNo);
                    TextView tv_sock = childView.findViewById(R.id.tv_sock);
                    TextView tv_clearsocker = childView.findViewById(R.id.tv_clearsocker);
                    CircleImageView cv_commhead = childView.findViewById(R.id.cv_commhead);
                    TextView tv_name = childView.findViewById(R.id.tv_name);
                    tv_sock.setText("胜负场次："+systemResultBean.getData().get(i).getGroupPlans().get(m).getWinTimes()+"胜"+systemResultBean.getData().get(i).getGroupPlans().get(0).getLoseTimes()+"负");
                    tv_clearsocker.setText("净胜分："+systemResultBean.getData().get(i).getGroupPlans().get(m).getClearWinScore()+"分");
                    GlideUtil.loadImage(SystemMacthResultActivity.this,systemResultBean.getData().get(i).getGroupPlans().get(m).getGroupLogo(),cv_commhead);
                    tv_name.setText(systemResultBean.getData().get(i).getGroupPlans().get(m).getGroupName());
                    mRankNo.setText("第"+(m+1)+"名");
                    socerAdapterHorder.mResultLayout.addView(childView);
              }
            }
            return view;
        }
    }

    public class SocerAdapterHorder {
        TextView tv_team;
        LinearLayout mResultLayout;
        public SocerAdapterHorder(View view){
            tv_team=view.findViewById(R.id.tv_team);
            mResultLayout=view.findViewById(R.id.mResultLayout);
        }
    }
}
