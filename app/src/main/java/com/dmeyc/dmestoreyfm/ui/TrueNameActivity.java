package com.dmeyc.dmestoreyfm.ui;

import android.app.Dialog;
import android.content.Intent;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.PublishActionAfterBean;
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

public class TrueNameActivity extends BaseActivity {
    @Bind(R.id.et_turename)
    EditText et_turename;
    @Bind(R.id.et_idenitycode)
    EditText et_idenitycode;
    @Bind(R.id.et_phone)
    EditText et_phone;
    @Bind(R.id.et_code)
    EditText et_code;
    @Bind(R.id.timertasktextview)
    TimerTaskTextView timertasktextview;
    @Bind(R.id.cb_agree)
    CheckBox cb_agree;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_truemane;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }
 @OnClick({R.id.btn_regist,R.id.timertasktextview})
    public void onClick(View view){
        int viweid=view.getId();
        if(R.id.btn_regist==viweid){
                if(TextUtils.isEmpty(et_turename.getText().toString().trim())){
                    ToastUtil.show("请输入真实姓名");
                    return;
                }
            if(TextUtils.isEmpty(et_idenitycode.getText().toString().trim())){
                ToastUtil.show("请输入身份证号码");
                return;
            }
            if(!checkPhoneNum(et_phone.getText().toString().trim())){
                ToastUtil.show("请输入正确手机号");
                return;
            }
            if(TextUtils.isEmpty(et_code.getText().toString().trim())){
                ToastUtil.show("请输入验证码");
                return;
            }
            if(!cb_agree.isChecked()){
                ToastUtil.show("同意保险条款");
                return;
            }
            RestClient.getYfmNovate(this).post(Constant.API.YFM_TOTRUENAME, new ParamMap.Build()
                    .addParams("idNo",et_idenitycode.getText().toString().trim())
                    .addParams("name",et_turename.getText().toString().trim())
                    .addParams("validCode", et_code.getText().toString().trim())
                    .addParams("phoneNo", et_phone.getText().toString().trim())
                    .addParams("user_token",SPUtils.getStringData(Constant.Config.TOKEN))
//                .addParams("group_id",seatBean.getData().getGroup_id())
                    .build(), new DmeycBaseSubscriber<PublishActionAfterBean>() {
                @Override
                public void onSuccess(PublishActionAfterBean bean) {
//                   ToastUtil.show(bean.getMsg());
                    goShop();
                }
            });
        }else if(R.id.timertasktextview==viweid){
            sendCode();
        }
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
                timertasktextview.startTimer();
            }
            @Override
            public void onError(Throwable e) {

            }
        });
    }


    Dialog dialog;
    ListView lv_shop;
    TextView alltv_price;
    TextView tv_toinfo,tv_notcont;
    public void goShop(){
        dialog  = new Dialog(TrueNameActivity.this, R.style.MyDialog);
        //设置它的ContentView
        setFinishOnTouchOutside(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_toinstrument);
        Window window = dialog.getWindow();

        WindowManager.LayoutParams lp = window.getAttributes();
        window.setGravity(Gravity.CENTER);
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高
        WindowManager.LayoutParams params = window.getAttributes();
        params.height = (int) (d.getHeight() * 0.3); // 高度设置为屏幕的0.5
        params.width = (int) (d.getWidth() * 0.8); // 宽度设置为屏幕的0.6
        window.setAttributes(params);
        tv_notcont=dialog.findViewById(R.id.tv_notcont);
        tv_notcont.setText("恭喜您已完善保单信息\n放心去运动吧");
        tv_toinfo=dialog.findViewById(R.id.tv_toinfo);
//        lv_shop = dialog.findViewById(R.id.lv_shop);
//        alltv_price=dialog.findViewById(R.id.alltv_price);
        dialog.show();
        tv_toinfo.setText("返回活动首页");
        tv_toinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TrueNameActivity.this,MainActivity.class));
                finish();
            }
        });
//        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
//            @Override
//            public void onDismiss(DialogInterface dialogInterface) {
//
//
//            }
//        });

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
}
