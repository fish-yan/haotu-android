package com.dmeyc.dmestoreyfm.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.CourseBuyBean;
import com.dmeyc.dmestoreyfm.bean.TeachCourseBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;
import com.dmeyc.dmestoreyfm.wedgit.NoScrollListView;
import com.tamic.novate.Throwable;

import java.util.ArrayList;

public class CourseBuyActivity extends Activity {
    NoScrollListView no_list;
    CircleImageView iv_activitylog;
    TextView tv_activityname,tv_amount,tv_projecttype,tv_intro,tv_all,tv_paymoney;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coursebuy);
        no_list=findViewById(R.id.no_list);
        iv_activitylog=findViewById(R.id.iv_activitylog);
        tv_activityname=findViewById(R.id.tv_activityname);
        tv_amount=findViewById(R.id.tv_amount);
        tv_projecttype=findViewById(R.id.tv_projecttype);
        tv_intro=findViewById(R.id.tv_intro);
        tv_all=findViewById(R.id.tv_all);
        tv_paymoney=findViewById(R.id.tv_paymoney);
        setdata();
    }

    ArrayList<ImageView> rbs=new ArrayList<>();
    CourseBuyBean courseBuyBean;
    payAdapter payadapter;

    int clickpos=-1;
    private void setdata() {
        RestClient.getYfmNovate(this).post(Constant.API.YFM_TEACHCOURSE, new ParamMap.Build()
                        .addParams("user_token",SPUtils.getStringData(Constant.Config.TOKEN))
//                        .addParams("course_id", getIntent().getStringExtra("groupid")).build(),
                        .addParams("course_id", 36).build(),
                new DmeycBaseSubscriber<CourseBuyBean>(this) {

                    @Override
                    public void onSuccess(final CourseBuyBean bean) {
                        ToastUtil.show(bean.getMsg());
                        courseBuyBean=bean;
                        GlideUtil.loadImage(CourseBuyActivity.this,courseBuyBean.getData().getGroup_logo(),iv_activitylog);
                        tv_activityname.setText(courseBuyBean.getData().getCourse_name());
                        tv_amount.setText(courseBuyBean.getData().getCourse_amount()+"元");
                        tv_projecttype.setText("项目类型："+bean.getData().getProject_type());
                        tv_intro.setText(bean.getData().getCourse_remark());
                        tv_all.setText(bean.getData().getCourse_amount()+"");
                        tv_paymoney.setText(bean.getData().getCourse_amount()+"元");
                        payadapter=new payAdapter();
                        no_list.setAdapter(payadapter);

                        no_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                clickpos=i;
                                payadapter.notifyDataSetChanged();
//                                for (int ii=0;ii<rbs.size();ii++){
//                                    if(ii==i){
//                                        rbs.get(ii).setChecked(true);
//
//                                    }else {
//                                        rbs.get(ii).setChecked(false);
//                                    }
//                                }
//                                payadapter.notifyDataSetChanged();
                            }
                        });
                    }
                    @Override
                    public void onError(Throwable e) {
//                mBaseView.requestDataError();
                    }
                });
    }

    class viewHoder{
        ImageView rb_alipay;
        TextView tv_paytype;
        LinearLayout ll_item;
        public viewHoder(View view){
            tv_paytype=view.findViewById(R.id.tv_paytype);
            rb_alipay=view.findViewById(R.id.rb_alipay);
            ll_item=view.findViewById(R.id.ll_item);
        }
    }

    public class  payAdapter extends BaseAdapter{

            @Override
            public int getCount() {
                return courseBuyBean.getData().getPayList().size();
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            viewHoder viewHoder;
            @Override
            public View getView(final int i, View view, ViewGroup viewGroup) {
                if(view==null){
                    view=getLayoutInflater().inflate(R.layout.adapter_payitem,null);
                    viewHoder=new viewHoder(view);
                    view.setTag(viewHoder);
                }else {
                    viewHoder=(viewHoder)view.getTag();
                }
//                rbs.add(viewHoder.rb_alipay);
                viewHoder.tv_paytype.setText(courseBuyBean.getData().getPayList().get(i).getPay_name());
                if((-1==clickpos&&0==i)||i==clickpos){
                    viewHoder.rb_alipay.setBackground(CourseBuyActivity.this.getResources().getDrawable(R.color.indicator_selected_color));
                }else {
                    viewHoder.rb_alipay.setBackground(CourseBuyActivity.this.getResources().getDrawable(R.color.black));
                }
//                                viewHoder.ll_item.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View view) {
//                                        ToastUtil.show(i+"ssss");
//                                        for (int ii=0;ii<rbs.size();ii++){
//                                    if(ii==i){
//                                        rbs.get(ii).setBackground(CourseBuyActivity.this.getResources().getDrawable(R.color.indicator_selected_color));
//
//                                    }else {
//                                        rbs.get(ii).setBackground(CourseBuyActivity.this.getResources().getDrawable(R.color.black));
//                                    }
//                                }
//
//                                    }
//                                });
                return view;
            }

    }
}
