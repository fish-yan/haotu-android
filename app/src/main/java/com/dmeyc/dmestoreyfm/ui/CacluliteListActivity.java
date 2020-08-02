package com.dmeyc.dmestoreyfm.ui;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.base.CauculaterRecordBean;
import com.dmeyc.dmestoreyfm.bean.YFMUserBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;

public class CacluliteListActivity extends BaseActivity {
private ListView lv_cauculater;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_calculate;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        lv_cauculater=mRootView.findViewById(R.id.lv_cauculater);
        getData();
    }

    private void getData() {
        RestClient.getYfmNovate(this).post(Constant.API.YFM_CAUCULATEINFO, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .build(), new DmeycBaseSubscriber<CauculaterRecordBean>() {
            @Override
            public void onSuccess(final CauculaterRecordBean bean) {
//                ToastUtil.show(bean.getMsg());
                lv_cauculater.setAdapter(new BaseAdapter() {
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
                    ViewHolder viewHolder;
                    @Override
                    public View getView(int i, View view, ViewGroup viewGroup) {
                        if(view==null){
                            view=getLayoutInflater().inflate(R.layout.adapter_calculaterecore,null);
                            viewHolder=new ViewHolder(view);
                            view.setTag(viewHolder);
                        }else {
                            viewHolder=(ViewHolder) view.getTag();
                        }
                        viewHolder.tv_name.setText(bean.getData().get(i).getCompany_name());
                      viewHolder.tv_projecttype.setText(bean.getData().get(i).getProjectType());
                      viewHolder.tv_time.setText(bean.getData().get(i).getActivityTime());
                      if(bean.getData().get(i).getStatus().equals("1")){
                        viewHolder.tv_state.setText("已提交");
                          viewHolder.tv_state.setTextColor(getResources().getColor(R.color.red));
                      }else {
                          viewHolder.tv_state.setText("未提交");
                          viewHolder.tv_state.setTextColor(getResources().getColor(R.color.c22));
                      }
                        return view;
                    }
                });
            }
    });
}
public class ViewHolder{
        TextView tv_name,tv_projecttype,tv_time,tv_state;
        public ViewHolder(View view){
            tv_name=view.findViewById(R.id.tv_name);
            tv_projecttype=view.findViewById(R.id.tv_projecttype);
            tv_time=view.findViewById(R.id.tv_time);
            tv_state=view.findViewById(R.id.tv_state);
        }

}
}
