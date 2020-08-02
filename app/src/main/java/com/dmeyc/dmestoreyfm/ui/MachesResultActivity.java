package com.dmeyc.dmestoreyfm.ui;

import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.ProductPageAdapter;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.ActionRecordListBean;
import com.dmeyc.dmestoreyfm.bean.GoodDetailBean;
import com.dmeyc.dmestoreyfm.bean.MatchResultBean;
import com.dmeyc.dmestoreyfm.bean.ResultImagBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;
import com.dmeyc.dmestoreyfm.wedgit.GlideImageLoader;
import com.dmeyc.dmestoreyfm.wedgit.NoScrollListView;
import com.tamic.novate.Throwable;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class MachesResultActivity extends BaseActivity {
    private ViewPager viewpager;
    protected NoScrollListView mRecycleView;


    @Bind(R.id.civ_avatar)
    CircleImageView civ_avatar;
    @Bind(R.id.tv_groupname)
    TextView tv_groupname;
    @Bind(R.id.tv_data)
    TextView tv_data;
    @Bind(R.id.tv_result)
    TextView tv_result;
    @Bind(R.id.tv_adress)
    TextView tv_adress;
    @Bind(R.id.banner)
    Banner banner;

    @Override
    protected int getLayoutRes() {

        return R.layout.activity_machesresult;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        viewpager=(ViewPager) findViewById(R.id.viewpager);
        mRecycleView=(NoScrollListView)findViewById(R.id.recycleview);

        setData();
        WindowManager wm = this.getWindowManager();

        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        RelativeLayout.LayoutParams rl=new RelativeLayout.LayoutParams(width,400);
        viewpager.setLayoutParams(rl);
        getPicData();
    }
    List<String> imgs = new ArrayList<>();
    public void getPicData(){
        RestClient.getYfmNovate(this).post(Constant.API.YFM_GETACTIONIMAGES, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("activity_id",getIntent().getIntExtra("activityid",-1))
                .build(), new DmeycBaseSubscriber<ResultImagBean>() {
            @Override
            public void onSuccess(ResultImagBean bean) {
//                ToastUtil.show(bean.getMsg());

                imgs.clear();
                for (int i=0;i<bean.getData().size();i++){
                    imgs.add(bean.getData().get(i).getUrl());
//                    imgs.add(url);
//                    tips.add("");
                }
                banner.setVisibility(View.VISIBLE);
                banner.setImageLoader(new GlideImageLoader());
                banner.setImages(imgs);
                banner.start();
            }
        });

//        RestClient.getNovate(this).get(Constant.API.PRODUCT_DETAIL, new ParamMap.Build().addParams(Constant.Config.ID, 23).build(), new DmeycBaseSubscriber<GoodDetailBean>(this) {
//            @Override
//            public void onSuccess(final GoodDetailBean bean) {
////                mBaseView.requestDataSuccess(bean);
//                ArrayList<String> mProductPicLists = new ArrayList<>();
//                for (GoodDetailBean.DataBean.ProductImageListTopBean topBean : bean.getData().getProductImageListTop()) {
//                    mProductPicLists.add(topBean.getSource());
//                }
//                viewpager.setAdapter(new ProductPageAdapter(mProductPicLists));
//            }
//            @Override
//            public void onError(Throwable e) {
////                mBaseView.requestDataError();
//            }
//           });
    }


    public void setData(){
        RestClient.getYfmNovate(this).post(Constant.API.YFM_MACTHRESULT, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("activity_id",getIntent().getIntExtra("activityid",-1))
                .build(), new DmeycBaseSubscriber<MatchResultBean>() {
            @Override
            public void onSuccess(final MatchResultBean bean) {
//                ToastUtil.show(bean.getMsg());
                GlideUtil.loadImage(MachesResultActivity.this,bean.getData().getGroup_logo(),civ_avatar);
                tv_groupname.setText(bean.getData().getGroup_name());
                tv_data.setText(bean.getData().getStart_time());
                tv_result.setText(bean.getData().getWinner_no()+"胜"+bean.getData().getLoser_no()+"负");
                tv_adress.setText("地址："+bean.getData().getActivity_address());
                mRecycleView.setAdapter(new BaseAdapter() {
                    @Override
                    public int getCount() {
                        return bean.getData().getMatchList().size();
                    }

                    @Override
                    public Object getItem(int position) {
                        return null;
                    }

                    @Override
                    public long getItemId(int position) {
                        return 0;
                    }
                    ViewHoder viewHoder;
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {

                        if(convertView==null){
                            convertView=LayoutInflater.from(MachesResultActivity.this).inflate(R.layout.adapter_pkinfo, parent, false);
                            viewHoder=new ViewHoder(convertView);
                            convertView.setTag(viewHoder);
                        }else {
                            viewHoder=(ViewHoder) convertView.getTag();
                        }
                        viewHoder.tv_macha.setText(bean.getData().getMatchList().get(position).getTeam_a_members());
                        viewHoder.tv_machb.setText(bean.getData().getMatchList().get(position).getTeam_b_members());
                        viewHoder.tv_pkresult.setText(bean.getData().getMatchList().get(position).getTeam_a_score()+"-"+bean.getData().getMatchList().get(position).getTeam_b_score());
                        return convertView;
                    }
                });
            }
        });
    }

    public class ViewHoder{
        TextView tv_macha,tv_machb,tv_pkresult;
        public ViewHoder(View view){
            tv_macha=view.findViewById(R.id.tv_macha);
            tv_machb=view.findViewById(R.id.tv_machb);
            tv_pkresult=view.findViewById(R.id.tv_pkresult);
        }
    }
}
