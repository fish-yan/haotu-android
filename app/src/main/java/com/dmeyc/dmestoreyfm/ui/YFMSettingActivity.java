package com.dmeyc.dmestoreyfm.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.common.CommonBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.CommDialog;
import com.tamic.novate.Throwable;

import butterknife.Bind;
import butterknife.OnClick;

public class YFMSettingActivity extends BaseActivity {

@Bind(R.id.tv_personinfro)
TextView tv_personinfro;
    @Bind(R.id.tv_messagesetting)
    TextView tv_messagesetting;
@Bind(R.id.tv_outlog)
TextView tv_outlog;
@Bind(R.id.tv_appcache)
TextView tv_appcache;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_yfmsetting;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }
    @OnClick({R.id.tv_outlog,R.id.tv_chagepass,R.id.tv_personinfro,R.id.tv_messagesetting,R.id.tv_system_promisssting,R.id.tv_myselfin,R.id.tv_appcache})
   public void onClick(View view){
        int viewid=view.getId();
        if(R.id.tv_outlog==viewid){
            changePerson();
//            outLogin();
        }else if(R.id.tv_chagepass==viewid){
            startActivity(new Intent(YFMSettingActivity.this,ChangeYFMPassweodActivity.class));
        }else if(viewid==R.id.tv_personinfro){
            startActivity(new Intent(YFMSettingActivity.this,PersonInfromationActivity.class));
//            startActivity(new Intent(YFMSettingActivity.this,MainActivity.class));
//            finish();
        }else if(viewid==R.id.tv_messagesetting){
            startActivity(new Intent(YFMSettingActivity.this,MeassageSttingActivity.class));
        }else if(R.id.tv_system_promisssting==viewid){
            startActivity(new Intent(YFMSettingActivity.this,SystemPromitSettingActivity.class));
        }else if(R.id.tv_appcache==viewid){
//            startActivity(new Intent(YFMSettingActivity.this,InventoryActivity.class));
        }else if(R.id.tv_myselfin==viewid){
            startActivity(new Intent(YFMSettingActivity.this,MySelfCertifyActivity.class));

        }
    }

    public void changePerson(){
        tv_outlog.setBackground(getResources().getDrawable(R.drawable.shap_grayconner));
        tv_outlog.setClickable(false);
        if(SPUtils.getStringData(Constant.Config.ROLECODE).equals("2")){
            if("0".equals(SPUtils.getStringData(Constant.Config.IDENITY))){
                SPUtils.savaStringData(Constant.Config.IDENITY,"1");
                startActivity(new Intent(YFMSettingActivity.this,MainActivity.class));
            }else {
                SPUtils.savaStringData(Constant.Config.IDENITY,"0");
                startActivity(new Intent(YFMSettingActivity.this,BMainActivity.class));
            }
            finish();
        }else {
            builder = new CommDialog.Builder(this);
            showTwoButtonDialog("您还未创建群不能切换身份", "取消", "去创建", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tv_outlog.setBackground(getResources().getDrawable(R.drawable.shape_tailor_sure));
                    tv_outlog.setClickable(true);
                    mDialog.dismiss();
                    //这里写自定义处理XXX
                }
            }, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDialog.dismiss();
                    startActivity(new Intent(YFMSettingActivity.this, CommInActivity.class));
                    finish();
                }
            });
        }
    }
    private CommDialog.Builder builder;
    private CommDialog mDialog;
    private void showTwoButtonDialog(String alertText, String confirmText, String cancelText, View.OnClickListener conFirmListener, View.OnClickListener cancelListener) {
        mDialog = builder.setMessage(alertText)
                .setPositiveButton(confirmText, conFirmListener)
                .setNegativeButton(cancelText, cancelListener)
                .createTwoButtonDialog();
        mDialog.show();
    }
//    private void outLogin() {
//        RestClient.getYfmNovate(this).post(Constant.API.YFM_USER_OUTLOG, new ParamMap.Build()
//                .addParams("user_token",SPUtils.getStringData(Constant.Config.TOKEN))
//                .build(), new DmeycBaseSubscriber<CommonBean>() {
//            @Override
//            public void onSuccess(CommonBean bean) {
//                ToastUtil.show(bean.getMsg());
//                SPUtils.savaBooleanData(Constant.Config.ISLOGIN,false);
//                startActivity(new Intent(YFMSettingActivity.this,YFMLoginActivity.class));
//                finish();
//            }
//            @Override
//            public void onError(Throwable e) {
//                }
//             });
//    }


}
