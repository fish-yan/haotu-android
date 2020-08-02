package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.MyCourseListBean;
import com.dmeyc.dmestoreyfm.bean.common.CommonBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;
import com.tamic.novate.Throwable;

public class MyAppointActivity extends BaseActivity {
    private TextView tv_nodata;
    private ListView lv_appoint;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_myappoint;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        lv_appoint=(ListView) findViewById(R.id.lv_appoint);
        tv_nodata=(TextView) findViewById(R.id.tv_nodata);
        getData();

    }

    private void getData() {

//        if(!checkPhoneNum(et_phone.getText().toString().trim())){
//            ToastUtil.show("请输入正确手机号");
//            return;
//        }
        RestClient.getYfmNovate(this).post(Constant.API.YFM_MYCOURSE, new ParamMap.Build()
                .addParams("user_token", "b1fe67bb673049b6991be92966e99ce9")
                .build(), new DmeycBaseSubscriber<MyCourseListBean>() {
            @Override
            public void onSuccess(final MyCourseListBean bean) {
                ToastUtil.show(bean.getMsg());
//                timerTaskTextView.startTimer();
                if(bean.getData().size()>0){
                    tv_nodata.setVisibility(View.GONE);
                    lv_appoint.setVisibility(View.VISIBLE);
                }else {
                    tv_nodata.setVisibility(View.VISIBLE);
                    lv_appoint.setVisibility(View.GONE);
                }
                lv_appoint.setAdapter(new BaseAdapter() {
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
                    ViewHoder viewHoder;
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {

                        if(convertView==null){
                            convertView= getLayoutInflater().inflate(R.layout.adapter_myappoint,null);
                            viewHoder=new ViewHoder(convertView);
                            convertView.setTag(viewHoder);
                        }else {
                           viewHoder=(ViewHoder) convertView.getTag();
                        }
                        GlideUtil.loadImage(MyAppointActivity.this,bean.getData().get(position).getCoach_logo(),viewHoder.civ_avatar);
                        viewHoder.tv_projecttype.setText("项目："+bean.getData().get(position).getProject_type());
//                        viewHoder.tv_live.setText("级别："+);
                        viewHoder.tv_coursetime.setText("课时："+bean.getData().get(position).getDuration());
                        viewHoder.tv_courprice.setText(bean.getData().get(position).getAmount()+"元");
                        viewHoder.tv_coursestate.setText(bean.getData().get(position).getStatus());
                        return convertView;
                    }
                });

                lv_appoint.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        startActivity(new Intent(MyAppointActivity.this,MyCourseActivity.class).putExtra("orderid",bean.getData().get(i).getOrder_id()));
                    }
                });

            }
            @Override
            public void onError(Throwable e) {

            }
        });
    }

    public class ViewHoder{
        CircleImageView civ_avatar;
        TextView tv_coursename,tv_projecttype,tv_live,tv_coursetime,tv_courprice,tv_coursestate;
        public ViewHoder(View veiw){
            civ_avatar=veiw.findViewById(R.id.civ_avatar);
            tv_coursename=veiw.findViewById(R.id.tv_coursename);
            tv_projecttype=veiw.findViewById(R.id.tv_projecttype);
            tv_live=veiw.findViewById(R.id.tv_live);
            tv_coursetime=veiw.findViewById(R.id.tv_coursetime);
            tv_courprice=veiw.findViewById(R.id.tv_courprice);
            tv_coursestate=veiw.findViewById(R.id.tv_coursestate);
        }

    }

}
