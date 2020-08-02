package com.dmeyc.dmestoreyfm.ui;

import android.text.InputType;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.PublishActionAfterBean;
import com.dmeyc.dmestoreyfm.bean.VipMemberListBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ScreemUtil;
import com.dmeyc.dmestoreyfm.utils.ScreenUtil;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.tamic.novate.Throwable;

import butterknife.Bind;
import butterknife.OnClick;

public class VipWareActivity extends BaseActivity {

    @Bind(R.id.et_amonet)
    EditText et_amonet;
    @Bind(R.id.tv_title)
    TextView tv_title;
    String type;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_vipware;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        et_amonet.setText(getIntent().getStringExtra("changetext"));
        type= getIntent().getStringExtra("type");
        if("vip".equals(type)){
              tv_title.setText("充值");
            et_amonet.setInputType(InputType.TYPE_CLASS_NUMBER);
            LinearLayout.LayoutParams ll=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
            ll.height=ScreenUtil.dp2px(VipWareActivity.this,50);
            ll.gravity=Gravity.CENTER;
            et_amonet.setLayoutParams(ll);
            et_amonet.setText("");
            et_amonet.setHint("请输入充值金额");
//            et_amonet.setText(SPUtils.getStringData(Constant.Config.COMMVIP));
            ;
        }else if("name".equals(type)){
            tv_title.setText("修改群名称");
            et_amonet.setInputType(InputType.TYPE_CLASS_TEXT);
//            et_amonet.setText(SPUtils.getStringData(Constant.Config.COMMNAME));
        }else if("adv".equals(type)){
            tv_title.setText("修改群公告");
            LinearLayout.LayoutParams ll=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
            ll.height=ScreenUtil.dp2px(VipWareActivity.this,200);
            ll.gravity=Gravity.TOP;
            et_amonet.setLayoutParams(ll);
            et_amonet.setSelection(0);
            et_amonet.setInputType(InputType.TYPE_CLASS_TEXT);
//            et_amonet.setText(SPUtils.getStringData(Constant.Config.COMMAVD));
        }else if("add".equals(type)){
            tv_title.setText("修改群地址");
            et_amonet.setInputType(InputType.TYPE_CLASS_TEXT);
//            et_amonet.setText(SPUtils.getStringData(Constant.Config.COMMADRESS));
        }else if("intro".equals(type)){
            tv_title.setText("修改群简介");
            et_amonet.setInputType(InputType.TYPE_CLASS_TEXT);
//            et_amonet.setText(SPUtils.getStringData(Constant.Config.COMMINTRO));
        }
    }

    private void setVipData() {
        if(TextUtils.isEmpty(et_amonet.getText().toString())){
            ToastUtil.show("请输入金额");
            return;
        }
        RestClient.getYfmNovate(this).post(Constant.API.YFM_VIPMEMBER_PRICECHAGER, new ParamMap.Build()
                .addParams("user_group_account_id", getIntent().getIntExtra("user_group_account_id",-1))
                .addParams("amount", Double.parseDouble(et_amonet.getText().toString()))
                .addParams("level", getIntent().getIntExtra("level",-1))
                .addParams("status", 1)
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .build(), new DmeycBaseSubscriber<PublishActionAfterBean>() {
            @Override
            public void onSuccess(final PublishActionAfterBean bean) {
//                ToastUtil.show(bean.getMsg());
//                if("vip".equals(type)){
//                   SPUtils.savaStringData(Constant.Config.COMMVIP,bean.getData().getGroup_name());
//                }else if("name".equals(type)){
//                    tv_title.setText("修改群名称");
//                    SPUtils.savaStringData(Constant.Config.COMMNAME,bean.getData().getGroup_name());
//                }else if("adv".equals(type)){
//                    tv_title.setText("修改群公告");
//                    SPUtils.savaStringData(Constant.Config.COMMAVD,bean.getData().getGroup_name());
//                }else if("add".equals(type)){
//                    tv_title.setText("修改群地址");
//                    SPUtils.savaStringData(Constant.Config.COMMADRESS,bean.getData().getGroup_name());
//                }else if("intro".equals(type)){
//                    tv_title.setText("修改群简介");
//                    SPUtils.savaStringData(Constant.Config.COMMINTRO,bean.getData().getGroup_name());
//                }
                    finish();

            }
            @Override
            public void onError(Throwable e) {

            }
        });
  }
    @OnClick(R.id.tv_right_title_bar)
    public  void  onClick() {

        if ("vip".equals(type)) {
            setVipData();
        } else  {
         setCommData();
        }
    }

    private void setCommData() {
        ParamMap.Build  pb=  new ParamMap.Build();
        if("name".equals(type)){
                     pb .addParams("group_id", getIntent().getIntExtra("group_id",-1))
                    .addParams("group_logo", "")
                    .addParams("group_name", et_amonet.getText().toString().trim())
                    .addParams("activity_venue_address","")
                    .addParams("remark", "")
                    .addParams("notice", "");
        }else if("adv".equals(type)){
                  pb .addParams("group_id", getIntent().getIntExtra("group_id",-1))
                    .addParams("group_logo", "")
                    .addParams("group_name", "")
                    .addParams("activity_venue_address", "")
                    .addParams("remark", "")
                    .addParams("notice", et_amonet.getText().toString().trim());

        }else if("add".equals(type)){
                    pb .addParams("group_id", getIntent().getIntExtra("group_id",-1))
                    .addParams("group_logo", "")
                    .addParams("group_name", "")
                    .addParams("activity_venue_address", et_amonet.getText().toString().trim())
                    .addParams("remark", "")
                    .addParams("notice", "");

        }else if("intro".equals(type)){
                    pb .addParams("group_id", getIntent().getIntExtra("group_id",-1))
                    .addParams("group_logo","")
                    .addParams("group_name", "")
                    .addParams("activity_venue_address", "")
                    .addParams("remark", et_amonet.getText().toString().trim())
                    .addParams("notice", "");
        }

        RestClient.getYfmNovate(this).post(Constant.API.YFM_COMMEDIT,  pb
                .addParams("user_token",SPUtils.getStringData(Constant.Config.TOKEN))
                .build(), new DmeycBaseSubscriber<VipMemberListBean>() {
            @Override
            public void onSuccess(final VipMemberListBean bean) {
                ToastUtil.show(bean.getMsg());
//                if("vip".equals(type)){
//                    SPUtils.savaStringData(Constant.Config.COMMVIP,bean.getData().getGroup_name());
//                }else if("name".equals(type)){
//                    tv_title.setText("修改群名称");
//                    SPUtils.savaStringData(Constant.Config.COMMNAME,bean.getData().getGroup_name());
//                }else if("adv".equals(type)){
//                    tv_title.setText("修改群公告");
//                    SPUtils.savaStringData(Constant.Config.COMMAVD,bean.getData().getGroup_name());
//                }else if("add".equals(type)){
//                    tv_title.setText("修改群地址");
//                    SPUtils.savaStringData(Constant.Config.COMMADRESS,bean.getData().getGroup_name());
//                }else if("intro".equals(type)){
//                    tv_title.setText("修改群简介");
//                    SPUtils.savaStringData(Constant.Config.COMMINTRO,bean.getData().getGroup_name());
//                }
                finish();
            }
            @Override
            public void onError(Throwable e) {

            }
        });
    }
}
