package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.BankListBean;
import com.dmeyc.dmestoreyfm.bean.YFMLoginBean;
import com.dmeyc.dmestoreyfm.bean.YFMUserBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.DataClass;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;

import butterknife.Bind;

public class CreditCarActivity extends BaseActivity implements View.OnClickListener {
    ListView ll_credit;
    ImageView iv_right_title_bar;
    @Bind(R.id.tv_nodata)
     TextView tv_nodata;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_creditcar;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        iv_right_title_bar=(ImageView) findViewById(R.id.iv_right_title_bar);
        iv_right_title_bar.setOnClickListener(this);
        ll_credit=(ListView) findViewById(R.id.ll_credit);

//        ll_credit.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
//            @Override
//            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
//
//                    contextMenu.add(Menu.NONE, 0, 0, "删除");
//
//            }
//        });
        if("tixian".equals(getIntent().getStringExtra("type"))){
            ll_credit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    DataClass.bankid=bankListBean.getData().get(i).getId();
                    DataClass.bankcardnumber=bankListBean.getData().get(i).getBank_account();
                    DataClass.banklogo=bankListBean.getData().get(i).getBank_logo();
                    DataClass.bankname=bankListBean.getData().get(i).getBank_name();
                        Intent intent=new Intent();
                        intent.putExtra("cardname,",bankListBean.getData().get(i).getBank_name());
                        intent.putExtra("cardid,",bankListBean.getData().get(i).getId());
                        intent.putExtra("cardlogo,",bankListBean.getData().get(i).getBank_logo());
                        intent.putExtra("cardnumber,",bankListBean.getData().get(i).getBank_account());
                        setResult(222,intent);
                        finish();
                }
            });
        }

    }

    BankListBean bankListBean;
    @Override
    protected void onResume() {
        super.onResume();
        RestClient.getYfmNovate(this).post(Constant.API.YFM_USER_CARLIST, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .build(), new DmeycBaseSubscriber<BankListBean>() {
            @Override
            public void onSuccess(final BankListBean bean) {
                bankListBean  =bean;
                BankListBean bankListBeasn=bean;
                if(bean.getData().size()>0){
                    tv_nodata.setVisibility(View.GONE);
                    ll_credit.setVisibility(View.VISIBLE);
                }else {
                    tv_nodata.setVisibility(View.VISIBLE);
                    ll_credit.setVisibility(View.GONE);
                }
                ll_credit.setAdapter(new BaseAdapter() {
                    @Override
                    public int getCount() {
                        return bean.getData().size();
                    }

                    @Override
                    public Object getItem(int position) {
                        return null;
                    }

                    @Override
                    public long getItemId(int position) {
                        return 0;
                    }

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        ViewHoder viewHoder=null;
                        if(convertView==null){
                            convertView=  LayoutInflater.from(CreditCarActivity.this).inflate(R.layout.adapter_creadite_item, parent, false);
                            viewHoder=new ViewHoder(convertView);
                            convertView.setTag(viewHoder);
                        }else {
                            viewHoder=(ViewHoder)  convertView.getTag();
                        }
                        if(bean.getData().get(position)!=null){
                            if(!TextUtils.isEmpty(bean.getData().get(position).getBank_logo())){
                                GlideUtil.loadImage(CreditCarActivity.this,bean.getData().get(position).getBank_logo(),viewHoder.iv_bankicon);
                            }
                            if(!TextUtils.isEmpty(bean.getData().get(position).getBank_name())){
                                viewHoder.tv_bankname.setText(bean.getData().get(position).getBank_name());
                            }
                            if(!TextUtils.isEmpty(bean.getData().get(position).getBank_account())){
                                viewHoder.tv_bank_numb.setText(bean.getData().get(position).getBank_account());
                            }
                        }
                        return convertView;
                    }
                });
            }
        });

    }

    @Override
    public void onClick(View v) {
        int viewid=v.getId();
        if(viewid==R.id.iv_right_title_bar){
startActivity(new Intent(this,AddCreaditActivity.class));
        }
    }

    class ViewHoder{

        private CircleImageView iv_bankicon;
        private TextView tv_bankname,tv_bank_numb;
        public ViewHoder(View view){
            iv_bankicon=(CircleImageView)  view.findViewById(R.id.iv_bankicon);
            tv_bankname=(TextView) view.findViewById(R.id.tv_bankname);
            tv_bank_numb=(TextView) view.findViewById(R.id.tv_bank_numb);
        }

    }


    @Override

    public boolean onContextItemSelected(MenuItem item)
    {
        //关键代码在这里
        AdapterView.AdapterContextMenuInfo menuInfo;
        menuInfo =(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();


        switch (item.getItemId()){
            case 0:
                deletCar();
                break;

        }
        return super.onContextItemSelected(item);
    }

    public void deletCar(){

        RestClient.getYfmNovate(CreditCarActivity.this).post(Constant.API.YFM_ADDCAR, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .build(), new DmeycBaseSubscriber<YFMLoginBean>(CreditCarActivity.this) {
            @Override
            public void onSuccess(final YFMLoginBean bean) {

                ToastUtil.show(bean.getMsg());
                finish();
            }
        });

    }
}
