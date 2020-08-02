package com.dmeyc.dmestoreyfm.ui;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.CommInAfterBean;
import com.dmeyc.dmestoreyfm.bean.PayAddCardBean;
import com.dmeyc.dmestoreyfm.bean.ProjectTypeBean;
import com.dmeyc.dmestoreyfm.bean.YFMLoginBean;
import com.dmeyc.dmestoreyfm.bean.common.CommonBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.utils.Util;
import com.dmeyc.dmestoreyfm.utils.city.ToastUtils;
import com.dmeyc.dmestoreyfm.wedgit.PopupMenu;
import com.dmeyc.dmestoreyfm.wedgit.TimerTaskTextView;
import com.tamic.novate.Throwable;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

public class AddCreaditActivity extends BaseActivity   {
      @Bind(R.id.btn_upload)
     Button btn_upload;

    @Bind(R.id.et_phone_num)
    EditText et_phone_num;

    @Bind(R.id.et_bankno)
    EditText et_bankno;
    @Bind(R.id.et_ident)
    EditText et_ident;
    @Bind(R.id.tv_banktype)
    TextView tv_banktype;
    @Bind(R.id.et_code)
    EditText et_code;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_addcard;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }
    private void addCard() {

        if(TextUtils.isEmpty(et_phone_num.getText().toString().trim())||!checkPhoneNum(et_phone_num.getText().toString().trim())){
            ToastUtils.showToast(AddCreaditActivity.this,"请输入正确手机号");
            return;
        }
        if(TextUtils.isEmpty(tv_banktype.getText().toString().trim())){
            ToastUtils.showToast(AddCreaditActivity.this,"请选择银行卡类型");
            return;
        }
        if(TextUtils.isEmpty(et_bankno.getText().toString().trim())){
            ToastUtils.showToast(AddCreaditActivity.this,"请输入银行卡号");
            return;
        }
        if(TextUtils.isEmpty(et_code.getText().toString().trim())){
            ToastUtils.showToast(AddCreaditActivity.this,"请输入验证码");
            return;
        }

        RestClient.getYfmNovate(AddCreaditActivity.this).post(Constant.API.YFM_CARDCONFORIM, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("requestNo", commInAfterBean.getData())
                .addParams("validateCode",et_code.getText().toString().trim())
                .build(), new DmeycBaseSubscriber<PayAddCardBean>(AddCreaditActivity.this) {
            @Override
            public void onSuccess(final PayAddCardBean bean) {
             ToastUtil.show(bean.getMsg());
             finish();
            }
        });
    }

    @OnClick({R.id.btn_upload,R.id.tv_banktype,R.id.tv_code})
    public void onClick(View view){
        int viewid=view.getId();
     /*   if(R.id.timertasktextview==viewid){
            if(!checkPhoneNum(et_phone_num.getText().toString().trim())){
                ToastUtil.show("请输入正确手机号");
                return;
            }
            RestClient.getYfmNovate(this).post(Constant.API.YFM_USER_SEND_CODE, new ParamMap.Build()
                    .addParams("phone_no", et_phone_num.getText().toString().trim())
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

        }else */if (viewid == R.id.btn_upload) {
            addCard();
           }else if(R.id.tv_banktype==viewid){
            getBankeTyoe();
        }else if(R.id.tv_code==viewid){
            getThridCode();
        }
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
    ProjectTypeBean pty;
    ArrayList<String> ar=new ArrayList<>();
    private PopupMenu popupMenu;
    int poscode;
    private void getBankeTyoe() {
        RestClient.getYfmNovate(this).post(Constant.API.YFM_PROJECTLIST, new ParamMap.Build()
                .addParams("key","BANK_CODE")
                .addParams("user_token",SPUtils.getStringData(Constant.Config.TOKEN))
                .build(), new DmeycBaseSubscriber<ProjectTypeBean>() {
            @Override
            public void onSuccess(ProjectTypeBean bean) {
               ToastUtil.show(bean.getMsg());
                pty=bean;
                List<ProjectTypeBean.DataBean> lbean= pty.getData();
                for (int i=0;i<lbean.size();i++){
                    ar.add(lbean.get(i).getItemName());
                }
//                String[] abs = new String[]{"关于我们", "检查更新", "意见反馈"};
                popupMenu = new PopupMenu(AddCreaditActivity.this,ar);
                popupMenu.showLocation(R.id.tv_banktype);// 设置弹出菜单弹出的位置
                // 设置回调监听，获取点击事件
                popupMenu.setOnItemClickListener(new PopupMenu.OnItemClickListener() {
                    @Override
                    public void onClick(PopupMenu.MENUITEM item, String str) {

                    }

                    @Override
                    public void onClick(String str,int pos) {
                        poscode=pos;
                        tv_banktype.setText(str);
                        popupMenu.dismiss();
                    }
                });
            }
            @Override
            public void onError(Throwable e) {

            }
        });
    }
    PayAddCardBean commInAfterBean;
    public void getThridCode(){
//        if(TextUtils.isEmpty(et_name.getText().toString().trim())){
//            ToastUtils.showToast(AddCreaditActivity.this,"请输入真实姓名");
//            return;
//        }
        if(TextUtils.isEmpty(et_phone_num.getText().toString().trim())||!checkPhoneNum(et_phone_num.getText().toString().trim())){
            ToastUtils.showToast(AddCreaditActivity.this,"请输入正确手机号");
            return;
        }
        if(TextUtils.isEmpty(tv_banktype.getText().toString().trim())){
            ToastUtils.showToast(AddCreaditActivity.this,"请选择银行卡类型");
            return;
        }
        if(TextUtils.isEmpty(et_bankno.getText().toString().trim())){
            ToastUtils.showToast(AddCreaditActivity.this,"请输入银行卡号");
            return;
        }

        RestClient.getYfmNovate(AddCreaditActivity.this).post(Constant.API.YFM_ADDCAR, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("bank_phone_no", et_phone_num.getText().toString().trim())
                .addParams("bank_account",et_bankno .getText().toString().trim())
                .addParams("bank_code",pty.getData().get(poscode).getExt() )//
                .addParams("bank_logo", pty.getData().get(poscode).getItemCode())
                .addParams("bank_name", tv_banktype.getText().toString().trim())
                .build(), new DmeycBaseSubscriber<PayAddCardBean>(AddCreaditActivity.this) {
            @Override
            public void onSuccess(final PayAddCardBean bean) {
                ToastUtil.show("验证码已发送注意接收");
                commInAfterBean=bean;
            }
        });
    }
}

