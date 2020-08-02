package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.AttrBean;
import com.dmeyc.dmestoreyfm.bean.RecordBean;
import com.dmeyc.dmestoreyfm.bean.SelectInfo;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.ui.photo.FrontTailorActivity;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.Util;
import com.dmeyc.dmestoreyfm.wedgit.camera.CameraManager;
import com.dmeyc.dmestoreyfm.wedgit.flowview.AutoFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class ProductSelectSizeActivity extends BaseActivity {

    @Bind(R.id.tv_submit)
    TextView tvSubmit;
    @Bind(R.id.tv_go_tailor)
    TextView tvGotailor;
    @Bind(R.id.tv_select_tailor)
    TextView tvSelectTailor;
    @Bind(R.id.sizeFlowLayout)
    AutoFlowLayout sizeFlowLayout;
    private List<RecordBean.DataBean> mRecordList;
    private OptionsPickerView tailorPickView;
    private String sizeName;


    @Override
    protected int getLayoutRes() {
        return R.layout.activity_product_select_size;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        setSubmitStatus(false);

        String mobile = SPUtils.getStringData(Constant.Config.MOBILE);
        RestClient.getNovate(this, Constant.API.TBASE_URL).get(Constant.API.TAILOR_RECORD, new ParamMap.Build()
                .addParams(Constant.Config.MOBILE, mobile)
                .addParams("string", Util.MD5(mobile + Constant.Config.MD5_KEY + mobile)).build(), new DmeycBaseSubscriber<RecordBean>() {

            @Override
            public void onSuccess(RecordBean bean) {
                if(!Util.objEmpty(bean.getData())){ //数据不为空
                    mRecordList = bean.getData();
                    tvGotailor.setVisibility(View.INVISIBLE);
                    tvSelectTailor.setVisibility(View.VISIBLE);
                }else{
                    tvGotailor.setVisibility(View.VISIBLE);
                    tvSelectTailor.setVisibility(View.INVISIBLE);
                }
            }
        });

        ParamMap.Build build = new ParamMap.Build();
        build.addParams("goods",getIntent().getIntExtra("mProductId",0));
        build.addParams("isCustom",true);
        RestClient.getNovate(this).get(Constant.API.WATCH_PRODUCT_DETAIL, build.build(), new DmeycBaseSubscriber<AttrBean>() {
            @Override
            public void onSuccess(AttrBean bean) {
                if(!Util.objEmpty(bean.getData().getAttributeDetails())){
                    List<AttrBean.DataBean.AttributeDetailsBean> lists = bean.getData().getAttributeDetails();
                    for (final AttrBean.DataBean.AttributeDetailsBean list : lists) {
                        if(TextUtils.equals(list.getAttributeKey(),"size")){
                            for (String s : list.getChildrenAttributeName()) {
                                View inflate = LayoutInflater.from(ProductSelectSizeActivity.this).inflate(R.layout.item_flowlayout_size, null);
                                TextView textView = inflate.findViewById(R.id.item_tv_flowlayout);
                                textView.setText(s);
                                sizeFlowLayout.addView(inflate);
                            }
                            sizeFlowLayout.setOnItemClickListener(new AutoFlowLayout.OnItemClickListener() {
                                @Override
                                public void onItemClick(int position, View view) {
                                    setCommonSizeStatus(position);
                                    sizeName = list.getChildrenAttributeName().get(position);
                                }
                            });
                            break;
                        }
                    }
                }
            }
        });
    }


    public void setCommonSizeStatus(int pos){
        for (int i = 0; i < sizeFlowLayout.getChildCount(); i++) {
            TextView textView = sizeFlowLayout.getChildAt(i).findViewById(R.id.item_tv_flowlayout);
            textView.setBackgroundResource(i == pos ? R.drawable.shape_1radius_fd_stroke : R.drawable.shape_1radius_99_stroke);
            textView.setTextColor(getResources().getColor(i == pos ? R.color.indicator_selected_color : R.color.gray));
        }
        setSubmitStatus(true);
        if(pos == -1){
            sizeName = null;
        }else{
            tvSelectTailor.setText("");
        }
    }

    @OnClick({R.id.tv_submit,R.id.tv_go_tailor,R.id.tv_select_tailor})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_submit:
                SelectInfo selectInfo;
                if(!TextUtils.isEmpty(tvSelectTailor.getText().toString())){ //选择的是量身尺码
                    selectInfo = new SelectInfo(true, tvSelectTailor.getText().toString(), (String) tvSelectTailor.getTag());
                }else{
                    selectInfo = new SelectInfo(false, sizeName, "");
                }
                tvSelectTailor.getTag();
                Intent intent = new Intent();
                intent.putExtra(Constant.Config.ITEM, selectInfo);
                setResult(RESULT_OK,intent);
                finish();
                break;
            case R.id.tv_go_tailor:
                SPUtils.savaIntData("SP_CAMERA_DIRECTION", CameraManager.CameraDirection.CAMERA_BACK.ordinal());
                startActivity(new Intent(this, FrontTailorActivity.class));
                break;
            case R.id.tv_select_tailor:
                if(tailorPickView ==  null)
                    tailorPickView = getOptionsPickerView();
                tailorPickView.show();
                break;
        }
    }

    /**
     * 设置提交按钮的状态
     * @param isCanSubmit
     */
    private void setSubmitStatus(boolean isCanSubmit){
        tvSubmit.setClickable(isCanSubmit);
        tvSubmit.setBackgroundColor(getResources().getColor(isCanSubmit ? R.color.indicator_selected_color : R.color.color_c5c5c5));
    }

    /**
     * 获取量身数据
     * @return
     */
    @NonNull
    private OptionsPickerView getOptionsPickerView() {
        List<String> datas = new ArrayList<>();
        for (RecordBean.DataBean dataBean : mRecordList) {
            datas.add(dataBean.getName());
        }
        OptionsPickerView pvOptions = new  OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3 ,View v) {
                //TODO
                tvSelectTailor.setText(mRecordList.get(options1).getName());
                tvSelectTailor.setTag(mRecordList.get(options1).getId());
                setCommonSizeStatus(-1);
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
        pvOptions.setPicker(datas);//添加数据源
        return pvOptions;
    }
}
