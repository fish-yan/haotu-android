package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.AddressBean;
import com.dmeyc.dmestoreyfm.bean.BaseBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.utils.Util;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.citywheel.CityPickerView;

import butterknife.Bind;
import butterknife.OnClick;

public class EditAddressActivity extends BaseActivity {

    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.et_detail)
    EditText etDetail;
    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.tv_make_sure)
    TextView tvMakeSure;
    @Bind(R.id.tv_head_name)
    TextView tvHeadName;
    @Bind(R.id.tv_area)
    TextView tvArea;

    private boolean isEditAddress; //默认是新增收获地址 , true->修改地址
    private int mAreaID; // 修改不为null
    @Override
    protected int getLayoutRes() {
        return R.layout.ac_edit_address;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        AddressBean.DataBean.ReceiverBean item = getIntent().getParcelableExtra("item");
        isEditAddress = item != null;

        if (isEditAddress){
            tvHeadName.setText("编辑收货地址");
            tvMakeSure.setText("确认修改");

            etName.setText(item.getReceiverPeople());
            etName.setSelection(item.getReceiverPeople().length());
            etPhone.setText(item.getMobile());
            etDetail.setText(item.getAddress());
            tvArea.setText(item.getArea());

            mAreaID = item.getId();
        }else{
            tvHeadName.setText("添加收货地址");
            tvMakeSure.setText("确认添加");
        }
    }

    @OnClick({R.id.tv_make_sure,R.id.tv_area})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_make_sure:
                if(TextUtils.isEmpty(etName.getText().toString()) || TextUtils.isEmpty(etDetail.getText().toString())
                        || TextUtils.isEmpty(etPhone.getText().toString()) || TextUtils.isEmpty(tvArea.getText().toString())){
                    ToastUtil.show("请完善信息");
                    return;
                }

                ParamMap.Build build = new ParamMap.Build();
                build.addParams("people",etName.getText().toString())
                        .addParams("mobile",etPhone.getText().toString())
                        .addParams("area",tvArea.getText().toString())
                        .addParams("detailPlace",etDetail.getText().toString())
                        .addParams("userId", Util.getUserId());
                if(isEditAddress)
                    build.addParams("areaId",mAreaID);
                RestClient.getNovate(this).get(Constant.API.ADD_EDIT_ADDRESS, build.build(), new DmeycBaseSubscriber<BaseBean>(this) {
                    @Override
                    public void onSuccess(BaseBean bean) {
                        ToastUtil.show(bean.getMsg());
                        Intent intent = new Intent();
                        if(isEditAddress){
                            intent.putExtra("position",getIntent().getIntExtra("position",0));
                        }
                        AddressBean.DataBean.ReceiverBean address = new AddressBean.DataBean.ReceiverBean();
                        address.setReceiverPeople(etName.getText().toString());
                        address.setReceiverPeople(etPhone.getText().toString());
                        address.setArea(tvArea.getText().toString());
                        address.setAddress(etDetail.getText().toString());
                        intent.putExtra("item",address);
                        setResult(RESULT_OK,intent);
                        finish();
                    }
                });
                break;
            case R.id.tv_area:
                CityConfig cityConfig = new CityConfig.Builder(EditAddressActivity.this)
                        .titleBackgroundColor("#dfdfdf")
                        .textSize(14)
                        .cancelTextColor("#1A1A1A")
                        .confirTextColor("#007aff")
//                        .textColor("0xFF585858")
                        .province("江苏")
                        .city("常州")
                        .district("新北区")
                        .visibleItemsCount(7)
                        .showBackground(true)
                        .itemPadding(5)
                        .setCityInfoType(CityConfig.CityInfoType.BASE)
                        .setCityWheelType(CityConfig.WheelType.PRO_CITY_DIS)
                        .build();
                CityPickerView cityPicker = new CityPickerView(cityConfig);
                cityPicker.show();
                cityPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
                    @Override
                    public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                        super.onSelected(province, city, district);
                        tvArea.setText(city.getName() + " " +district.getName());
                    }
                });
                break;
        }
    }
}
