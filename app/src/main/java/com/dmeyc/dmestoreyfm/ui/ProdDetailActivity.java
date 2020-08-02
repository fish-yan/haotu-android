package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.dialog.ShareDialog;

import butterknife.Bind;
import butterknife.OnClick;

public class ProdDetailActivity extends BaseActivity {
    @Bind(R.id.tv_name)
    TextView tv_name;
    @Bind(R.id.tv_aount)
    TextView tv_aount;
    @Bind(R.id.tv_data)
    TextView tv_data;
    @Bind(R.id.tv_nubetr)
    TextView tv_nubetr;
    @Bind(R.id.tv_insument)
    TextView tv_insument;
    @Bind(R.id.iv_ban_stauts)
    ImageView iv_ban_stauts;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_prodetail;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {

        tv_name.setText(getIntent().getStringExtra("name"));
        tv_aount.setText(getIntent().getStringExtra("saftacount"));
        tv_data.setText(getIntent().getStringExtra("data"));
        tv_nubetr.setText(getIntent().getStringExtra("datanumber"));
      String stat=  getIntent().getStringExtra("staust");
        if("1".equals(stat)){
            iv_ban_stauts.setBackground(getResources().getDrawable(R.drawable.unstart_instrment));
        }else if("2".equals(stat)){
            iv_ban_stauts.setBackground(getResources().getDrawable(R.drawable.insument));
        } else {
            iv_ban_stauts.setBackground(getResources().getDrawable(R.drawable.end_instrment));
        }
    }
    @OnClick({R.id.rl_insumentonline,R.id.rl_insumentshare,R.id.tv_insument})
   public void onClick(View v){
        int  viewid=v.getId();
        if(viewid==R.id.rl_insumentonline){
//            if("1".equals(bean.getStatus())){
//                textView.setText("保险未开始");
//            }else if("2".equals(bean.getStatus())){
//                textView.setText("保障中");
//            }else {
//                textView.setText("保障结束");
//            }
        }else if(viewid==R.id.rl_insumentshare){
            new ShareDialog(this).show();
        }else if(R.id.tv_insument==viewid){
//            Intent intent = new Intent(this, WebviewActivity.class);
//            intent.putExtra(Constant.Config.TITLE,"用户协议");
//            intent.putExtra(Constant.Config.URL,"http://192.168.0.104/agreement/agreementhz.html");
////            intent.putExtra(Constant.Config.URL,Constant.API.BASE_URL+Constant.API.USER_AGREEMENT);
//            startActivity(intent);
        }
    }
}
