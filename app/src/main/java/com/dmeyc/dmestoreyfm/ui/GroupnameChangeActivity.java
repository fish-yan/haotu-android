package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.PublishActionAfterBean;
import com.dmeyc.dmestoreyfm.bean.TrueNameBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.tamic.novate.Throwable;

import butterknife.Bind;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Group;

public class GroupnameChangeActivity extends BaseActivity {
    @Bind(R.id.et_name)
    EditText et_name;
    @Bind(R.id.tv_title)
    TextView tv_title;

    String type="";
    String header="";
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_nicknamechage;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        if(!TextUtils.isEmpty(getIntent().getStringExtra("groupname"))){
            et_name.setText(getIntent().getStringExtra("groupname"));
            type="name";
            header=   getIntent().getStringExtra("grouplog");
            tv_title.setText("修改群名称");
        }
        if(!TextUtils.isEmpty(getIntent().getStringExtra("phonenumber"))){
            et_name.setText(getIntent().getStringExtra("phonenumber"));
            type="phone";
            tv_title.setText("修改手机号");
        }
        if(!TextUtils.isEmpty(getIntent().getStringExtra("address"))){
            et_name.setText(getIntent().getStringExtra("address"));
            type="address";
            tv_title.setText("修改场馆地址");
        }

    }




    public void changeName(){
        if(TextUtils.isEmpty(et_name.getText().toString().trim())){
            ToastUtil.show("请输入昵称");
            return;
        }
        ParamMap.Build  pb=   new ParamMap.Build();
        if("name".equals(type)){
            pb .addParams("group_name", et_name.getText().toString().trim());
        }else if("phone".equals(type)){
            pb .addParams("phoneNo", et_name.getText().toString().trim());
        }else if("address".equals(type)){
            pb .addParams("activity_venue_address", et_name.getText().toString().trim());
        }
        RestClient.getYfmNovate(this).post(Constant.API.YFM_CHANGECOMMINFO,
                pb.addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("group_id", getIntent().getIntExtra("groupid",-1))
                .build(), new DmeycBaseSubscriber<PublishActionAfterBean>() {
            @Override
            public void onSuccess(PublishActionAfterBean bean) {
//                trueNameBean=bean;
//                et_idenity.setText(bean.getData().getIdNo()+"");
//                et_name.setText(bean.getData().getName());
//                et_phone.setText(bean.getData().getPhoneNO());
                Intent intent=new Intent();
                if("name".equals(type)){
                    intent.putExtra("name",et_name.getText().toString().trim());
                    RongIM.getInstance().refreshGroupInfoCache(new Group(getIntent().getIntExtra("groupid",-1)+"",et_name.getText().toString().trim(),Uri.parse(header)));

                }else if("phone".equals(type)){
                    intent.putExtra("phone",et_name.getText().toString().trim());
                }
                setResult(444,intent);
                ToastUtil.show(bean.getMsg());
                finish();
            }
            @Override
            public void onError(Throwable e) {

            }
        });
    }
    @OnClick(R.id.btn_regist)
    public void onClick(View view){
        int viewid =view.getId();
        if(viewid==R.id.btn_regist){
            changeName();
        }
    }
}
