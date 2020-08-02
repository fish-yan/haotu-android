package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.LoginBean;
import com.dmeyc.dmestoreyfm.bean.common.CommonBean;
import com.dmeyc.dmestoreyfm.bean.event.EditMobileEvent;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.PhotoSelectHelper;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.utils.Util;
import com.dmeyc.dmestoreyfm.wedgit.MoveEditText;
import com.jph.takephoto.model.TImage;
import com.makeramen.roundedimageview.RoundedImageView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.OnClick;

public class PresonInfoActivity extends BaseActivity implements MoveEditText.OnCompleteListener {

    @Bind(R.id.tv_gender)
    TextView tvGender;
    @Bind(R.id.tv_birth)
    TextView tvBirth;
    @Bind(R.id.met_email)
    MoveEditText metEmail;
    @Bind(R.id.met_nikename)
    MoveEditText metNickname;
    @Bind(R.id.riv_avatar)
    RoundedImageView rivAvatar;
    @Bind(R.id.tv_mobile)
    TextView tvMobile;
    private OptionsPickerView pvOptions;
    private TimePickerView pvTime;

    @Override
    protected int getLayoutRes() {
        return R.layout.ac_preson_info;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        RestClient.getNovate(this).post(Constant.API.USER_LOGIN, new ParamMap.Build().addParams(Constant.Config.MOBILE, SPUtils.getStringData(Constant.Config.MOBILE)).build(), new DmeycBaseSubscriber<LoginBean>(this) {
            @Override
            public void onSuccess(LoginBean bean) {
                LoginBean.DataBean.UserBean user = bean.getData().getUser();
                tvGender.setText(user.getSex());
                metNickname.setText(user.getNickname());
                tvMobile.setText(Util.getBlurNumber(user.getMobile()));
                metEmail.setText(user.getEmail());
                tvBirth.setText(user.getBirthday());

                if(!TextUtils.isEmpty(user.getAvatar()))
                    GlideUtil.loadHeadImage(PresonInfoActivity.this,user.getAvatar(),rivAvatar);
            }
        });


        rivAvatar.setScaleType(ImageView.ScaleType.CENTER_CROP);
        EventBus.getDefault().register(this);

        metEmail.setOnCompleteListener(this);
        metNickname.setOnCompleteListener(this);
    }

    @OnClick({R.id.rel_gender,R.id.rel_birth,R.id.rel_mobile,R.id.rel_avatar,R.id.ll_root,R.id.ll_root1})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rel_gender:
                if(pvOptions == null)
                    pvOptions = getOptionsPickerView();
                pvOptions.show();
                break;
            case R.id.rel_birth:
                if(pvTime == null)
                    pvTime = getTimePickerView();
                pvTime.show();
                break;
            case R.id.rel_mobile:
                startActivity(new Intent(this,EditMobileActivity.class));
                break;
            case R.id.rel_avatar:
                new PhotoSelectHelper(this,0).setCrop(true).show();
                break;
            case R.id.ll_root:
            case R.id.ll_root1:
                metEmail.clearFocus();
                metNickname.clearFocus();
                if(Util.isSoftInputShow(this))
                    Util.closeKeybord(metNickname,this);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null && resultCode == RESULT_OK){
            if(data.getBooleanExtra(Constant.Config.SUCCESS,false)){
                ArrayList<TImage> images = (ArrayList<TImage>) data.getSerializableExtra("images");
                GlideUtil.loadImage(this,images.get(0).getPath(),rivAvatar);
                RestClient.getNovate(PresonInfoActivity.this).uploadImage(Constant.API.UPLOAD_HEADING, new File(images.get(0).getPath()), new DmeycBaseSubscriber<CommonBean>() {
                    @Override
                    public void onSuccess(CommonBean bean) {
                        String url = (String) bean.getData();
                        updateUserInfo("avatar",url);
                    }
                });
            }
        }
    }

    public void updateUserInfo(final String key, final Object value){
        if(value == null) return;
        if(value instanceof String && TextUtils.isEmpty((String)value)) return;
        RestClient.getNovate(this).post(Constant.API.USER_INFOUPDATE, new ParamMap.Build()
                .addParams(key, value)
                .build(), new DmeycBaseSubscriber<CommonBean>() {
            @Override
            public void onSuccess(CommonBean bean) {
                ToastUtil.show("操作成功");
                if(TextUtils.equals("avatar",key)){
                    SPUtils.savaStringData(Constant.Config.AVATAR,(String) value);
                }else if(TextUtils.equals("nickname",key)){
                    SPUtils.savaStringData(Constant.Config.NICK_NAME,(String) value);
                }
            }
        });
    }

    /**
     * 相机拍照callback
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void takePhotoCallBack(EditMobileEvent event){
        tvMobile.setText(Util.getBlurNumber(event.getNewMobile()));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
    }

    @Override
    public void onComplete(View view) {
        if(view.getId() == metEmail.getId()){
            updateUserInfo("email",metEmail.getText().toString());
        }else if(view.getId() == metNickname.getId()){
            updateUserInfo("nickname",metNickname.getText().toString());
        }
    }

    /**
     * 获取时间选择器
     * @return
     */
    @NonNull
    private TimePickerView getTimePickerView() {
        Calendar startDate = Calendar.getInstance();
        startDate.set(1900, 1, 1);
        Calendar endDate = Calendar.getInstance();
        TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                tvBirth.setText(new SimpleDateFormat("yyyy-MM-dd").format(date));
                updateUserInfo("birthday",new SimpleDateFormat("yyyy-MM-dd").format(date));
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
                .setLabel("","","","","","")//默认设置为年月日时分秒
                .setRangDate(startDate,endDate)
                .setSubmitText("确定")//确定按钮文字
                .setCancelText("关闭")//取消按钮文字
                .setSubCalSize(14)//确定和取消文字大小
                .setSubmitColor(Color.parseColor("#007aff"))//确定按钮文字颜色
                .setCancelColor(Color.parseColor("#1a1a1a"))//取消按钮文字颜色
                .setTitleBgColor(0xFFFAFAFA)//标题背景颜色 Night mode
                .setBgColor(0xFFFFFFFF)//滚轮背景颜色 Night mode
                .build();
        pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        return pvTime;
    }

    /**
     * 获取男女选择器
     * @return
     */
    @NonNull
    private OptionsPickerView getOptionsPickerView() {
        OptionsPickerView pvOptions = new  OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3 ,View v) {
                tvGender.setText(options1 == 0 ? "男" : "女");
                updateUserInfo("sex",tvGender.getText().toString());
            }
        })
                .setSubmitText("确定")//确定按钮文字
                .setCancelText("关闭")//取消按钮文字
                .setSubCalSize(14)//确定和取消文字大小
                .setSubmitColor(Color.parseColor("#007aff"))//确定按钮文字颜色
                .setCancelColor(Color.parseColor("#1a1a1a"))//取消按钮文字颜色
                .setTitleBgColor(0xFFFAFAFA)//标题背景颜色 Night mode
                .setBgColor(0xFFFFFFFF)//滚轮背景颜色 Night mode
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(Color.parseColor("#dfdfdf"))
                .build();
        pvOptions.setPicker(Arrays.asList("男","女"));//添加数据源
        return pvOptions;
    }
}
