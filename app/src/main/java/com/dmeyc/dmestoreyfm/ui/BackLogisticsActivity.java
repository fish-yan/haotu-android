package com.dmeyc.dmestoreyfm.ui;

import android.graphics.Color;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.DeliveryCodeBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class BackLogisticsActivity extends BaseActivity {

    @Bind(R.id.tv_logistics_name)
    TextView tvLogisticsName;
    @Bind(R.id.et_logistics_number)
    EditText et_logistics_number;
    private List<DeliveryCodeBean.DataBean> list = new ArrayList<>();
    private List<String> list_name = new ArrayList<>();
    private int orderItemId;
    private String D_code;

    @Override
    protected int getLayoutRes() {
        return R.layout.ac_back_logistics;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        orderItemId = getIntent().getIntExtra("orderItemId",0);
        HashMap<String, Object> map = new HashMap<>();
        RestClient.getNovate(BackLogisticsActivity.this).get(Constant.API.ORDER_QUERY_DELIVERY_CODE, map, new DmeycBaseSubscriber<DeliveryCodeBean>() {
            @Override
            public void onSuccess(DeliveryCodeBean bean) {
                list.clear();
                list.addAll(bean.getData());
                for (int i = 0; i < list.size(); i++) {
                    list_name.add(list.get(i).getName());
                }
            }
        });
    }

    @OnClick({R.id.tv_logistics_name, R.id.et_logistics_number, R.id.tv_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_logistics_name:
                OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3, View v) {
                        tvLogisticsName.setText(list.get(options1).getName());
                        D_code = list.get(options1).getCode();
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
                pvOptions.setPicker(list_name);//添加数据源
                pvOptions.show();
                break;
            case R.id.et_logistics_number:

                break;
            case R.id.tv_submit:
                if (D_code.equals("") ||et_logistics_number.getText().toString().equals("")) {
                    ToastUtil.show("请填写完整信息");
                    return;
                }
                HashMap<String, Object> map = new HashMap<>();
                map.put("deliveryCode",D_code);
                map.put("deliveryNumber",et_logistics_number.getText().toString());
                map.put("orderItemId",orderItemId);

                RestClient.getNovate(BackLogisticsActivity.this).get(Constant.API.ORDER_INSERT_DELIVERY_NUMBER, map, new DmeycBaseSubscriber<Object>() {
                    @Override
                    public void onSuccess(Object bean) {
                        ToastUtil.show("操作成功");
                        finish();
                    }
                });
                break;
        }
    }
}
