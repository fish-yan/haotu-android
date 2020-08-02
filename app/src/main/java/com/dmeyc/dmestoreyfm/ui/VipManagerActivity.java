package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.CustomLazyViewPagerAdapter;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.MyCommListBean;
import com.dmeyc.dmestoreyfm.bean.VipMemberListBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.tamic.novate.Throwable;

import butterknife.Bind;

public class VipManagerActivity extends BaseActivity {

    private ListView lv_vip_manager;


       @Bind(R.id.tv_amount)
       TextView tv_amount;
    @Bind(R.id.tv_title)
    TextView tv_title;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_vipmanger;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {

        lv_vip_manager=(ListView) findViewById(R.id.lv_vip_manager);
//       tv_amoun=(TextView)findViewById(R.id.tv_amount);
//        setData();
//
//
//        lv_vip_manager.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                SPUtils.savaStringData(Constant.Config.COMMNAME,vipMemberListBean.getData().getGroupMemberList().get(i).getAmount()+"");
//
//                startActivity(new Intent(VipManagerActivity.this,VipWareActivity.class)
//                        .putExtra("user_group_account_id", vipMemberListBean.getData().getGroupMemberList().get(i).getUser_group_account_id())
//                        .putExtra("level", vipMemberListBean.getData().getGroupMemberList().get(i).getLevel())
////                        .putExtra("changetext",vipMemberListBean.getData().getGroupMemberList().get(i).getAmount()+"")
//                        .putExtra("type","vip"));
//            }
//        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        setData();


        lv_vip_manager.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                SPUtils.savaStringData(Constant.Config.COMMNAME,vipMemberListBean.getData().getGroupMemberList().get(i).getAmount()+"");
                startActivity(new Intent(VipManagerActivity.this,VipWareActivity.class)
                        .putExtra("user_group_account_id", vipMemberListBean.getData().getGroupMemberList().get(i).getUser_group_account_id())
                        .putExtra("level", vipMemberListBean.getData().getGroupMemberList().get(i).getLevel())
                        .putExtra("changetext",vipMemberListBean.getData().getGroupMemberList().get(i).getAmount()+"")
                        .putExtra("type","vip"));
            }
        });
    }

    VipMemberListBean vipMemberListBean;
    private void setData() {
        RestClient.getYfmNovate(this).post(Constant.API.YFM_VIPMEMBER, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("group_id",getIntent().getStringExtra("groupid"))
                .build(), new DmeycBaseSubscriber<VipMemberListBean>() {
            @Override
            public void onSuccess(final VipMemberListBean bean) {
//                ToastUtil.show(bean.getMsg());
                vipMemberListBean=bean;
                tv_amount.setText(bean.getData().getTotal_amount()+"元");
                tv_title.setText(bean.getData().getGroup_name());
                lv_vip_manager.setAdapter(new BaseAdapter() {
                    @Override
                    public int getCount() {
                        return bean.getData().getGroupMemberList().size();
                    }

                    @Override
                    public Object getItem(int i) {
                        return null;
                    }

                    @Override
                    public long getItemId(int i) {
                        return 0;
                    }

                    ViewHoder viewHoder;
                    @Override
                    public View getView(int i, View view, ViewGroup viewGroup) {

                        if(view==null){
                            view = getLayoutInflater().inflate(R.layout.adapter_vipmanageritem,null);
                            viewHoder=new ViewHoder(view);
                            view.setTag(viewHoder);
                        }else {
                            viewHoder=(ViewHoder) view.getTag();
                        }
                        viewHoder.tv_timein.setText(bean.getData().getGroupMemberList().get(i).getCreate_time());
                        GlideUtil.loadImage(VipManagerActivity.this,bean.getData().getGroupMemberList().get(i).getUser_logo(),viewHoder.iv_commheader);
                        viewHoder.tv_mmbname.setText(bean.getData().getGroupMemberList().get(i).getNick_name());
                        viewHoder.tv_itemmoney.setText(bean.getData().getGroupMemberList().get(i).getAmount()+"元");
                        return view;
                    }
                });

            }
            @Override
            public void onError(Throwable e) {

            }
        });

    }

    class ViewHoder{
        ImageView iv_commheader;
        TextView tv_mmbname,tv_timein,tv_itemmoney;
        public ViewHoder(View view){
            iv_commheader=view.findViewById(R.id.iv_commheader);
            tv_mmbname=view.findViewById(R.id.tv_mmbname);
            tv_timein=view.findViewById(R.id.tv_timein);
            tv_itemmoney=view.findViewById(R.id.tv_itemmoney);
                       }
      }
 }
