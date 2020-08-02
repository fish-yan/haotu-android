package com.dmeyc.dmestoreyfm.ui;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.IsTrueNameBean;
import com.dmeyc.dmestoreyfm.bean.PublishActionAfterBean;
import com.dmeyc.dmestoreyfm.bean.TrueNameBean;
import com.dmeyc.dmestoreyfm.bean.common.CommonBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.TimerTaskTextView;
import com.tamic.novate.Throwable;

import butterknife.Bind;
import butterknife.OnClick;


public class MySelfCertifyActivity extends BaseActivity {
    @Bind(R.id.et_name)
    EditText et_name;
    @Bind(R.id.et_idenity)
    EditText et_idenity;
    @Bind(R.id.et_phone)
    EditText et_phone;
    @Bind(R.id.et_code)
    EditText et_code;
    @Bind(R.id.timertasktextview)
    TimerTaskTextView timerTaskTextView;
    @Bind(R.id.btn_regist)
    Button btn_regist;
    private boolean istruename=false;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_myselfecertify;
    }
    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {

        checkIsTrueName();
    }
    @OnClick({R.id.timertasktextview,R.id.btn_regist})
    public void onClick(View view){
        int viewid=view.getId();
        if(viewid==R.id.timertasktextview){
            sendCode();
        }else if(R.id.btn_regist==viewid){
            if(istruename){
           ChageTrueName();
//              ToastUtil.show("我实名了");
            }else {
                submit();
            }
        }
    }

    public void checkIsTrueName(){

        RestClient.getYfmNovate(this).post(Constant.API.YFM_CHECKTRUENAME, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .build(), new DmeycBaseSubscriber<IsTrueNameBean>() {
            @Override
            public void onSuccess(IsTrueNameBean bean) {
//                finish();
                istruename=bean.isData();
                if(istruename){
                    getTrueName();
                }
            }
            @Override
            public void onError(Throwable e) {

            }
        });

    }

    private void sendCode() {

        if(!checkPhoneNum(et_phone.getText().toString().trim())){
            ToastUtil.show("请输入正确手机号");
            return;
        }
        RestClient.getYfmNovate(this).post(Constant.API.YFM_USER_SEND_CODE, new ParamMap.Build()
                .addParams("phone_no", et_phone.getText().toString().trim())
                .build(), new DmeycBaseSubscriber<CommonBean>() {
            @Override
            public void onSuccess(CommonBean bean) {
                ToastUtil.show("验证码已发送注意查收");
                timerTaskTextView.startTimer();
            }
            @Override
            public void onError(Throwable e) {

            }
        });
    }

    /**
     * 手机号正则校验
     *
     * @param phoneNum
     * @return
     */
    private boolean checkPhoneNum(String phoneNum) {
        String telRegex = "[1][35678]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(phoneNum)) return false;
        else return phoneNum.matches(telRegex);
    }

    public void submit(){

        if(!checkPhoneNum(et_phone.getText().toString().trim())){
            ToastUtil.show("请输入正确手机号");
            return;
        }
        if(TextUtils.isEmpty(et_code.getText().toString().trim())){
            ToastUtil.show("请输入验证码");
            return;
        }
        if(TextUtils.isEmpty(et_name.getText().toString().trim())){
            ToastUtil.show("请输入真实姓名");
            return;
        }
        if(TextUtils.isEmpty(et_idenity.getText().toString().trim())){
            ToastUtil.show("请输入身份证号码");
            return;
        }
        btn_regist.setBackground(getResources().getDrawable(R.drawable.shap_grayconner));
        btn_regist.setClickable(false);
        RestClient.getYfmNovate(this).post(Constant.API.YFM_TREUNAME, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                        .addParams("idNo", et_idenity.getText().toString().trim())
                        .addParams("name", et_name.getText().toString().trim())
                        .addParams("validCode", et_code.getText().toString().trim())
                        .addParams("phoneNo", et_phone.getText().toString().trim())
                        .build(), new DmeycBaseSubscriber<PublishActionAfterBean>() {
            @Override
            public void onSuccess(PublishActionAfterBean bean) {
                ToastUtil.show(bean.getMsg());
                finish();
            }
            @Override
            public void onError(Throwable e) {
                btn_regist.setBackground(getResources().getDrawable(R.drawable.shape_tailor_sure));
                btn_regist.setClickable(true);
            }
        });

    }
    TrueNameBean trueNameBean;
    public void getTrueName(){
        RestClient.getYfmNovate(this).post(Constant.API.YFM_GETCHECKNAME, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .build(), new DmeycBaseSubscriber<TrueNameBean>() {
            @Override
            public void onSuccess(TrueNameBean bean) {
                trueNameBean=bean;
                         et_idenity.setText(bean.getData().getIdNo()+"");
                       et_name.setText(bean.getData().getName());
                         et_phone.setText(bean.getData().getPhoneNO());
//                  ToastUtil.show(bean.getMsg());
//                finish();
            }
            @Override
            public void onError(Throwable e) {

            }
        });
    }

    public void ChageTrueName(){

        if(!checkPhoneNum(et_phone.getText().toString().trim())){
            ToastUtil.show("请输入正确手机号");
            return;
        }
        if(TextUtils.isEmpty(et_code.getText().toString().trim())){
            ToastUtil.show("请输入验证码");
            return;
        }
        if(TextUtils.isEmpty(et_name.getText().toString().trim())){
            ToastUtil.show("请输入真实姓名");
            return;
        }
        if(TextUtils.isEmpty(et_idenity.getText().toString().trim())){
            ToastUtil.show("请输入身份证号码");
            return;
        }
        btn_regist.setBackground(getResources().getDrawable(R.drawable.shap_grayconner));
        btn_regist.setClickable(false);
        RestClient.getYfmNovate(this).post(Constant.API.YFM_CHANGETRUENAME, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("full_name", et_name.getText().toString().trim())
                .addParams("phoneNo", et_phone.getText().toString().trim())
                .addParams("validCode", et_code.getText().toString().trim())
                .addParams("id_card", et_idenity.getText().toString().trim())
                .build(), new DmeycBaseSubscriber<PublishActionAfterBean>() {
            @Override
            public void onSuccess(PublishActionAfterBean bean) {
//                trueNameBean=bean;
//                et_idenity.setText(bean.getData().getIdNo()+"");
//                et_name.setText(bean.getData().getName());
//                et_phone.setText(bean.getData().getPhoneNO());
                  ToastUtil.show(bean.getMsg());
                finish();
            }
            @Override
            public void onError(Throwable e) {
                btn_regist.setBackground(getResources().getDrawable(R.drawable.shape_tailor_sure));
                btn_regist.setClickable(true);
            }
        });

    }
}
