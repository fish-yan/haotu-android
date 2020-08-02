package com.dmeyc.dmestoreyfm.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;

import com.alipay.sdk.app.PayTask;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.WXPayBean;
import com.dmeyc.dmestoreyfm.bean.common.CommonBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.ui.OrderSuccessActivity;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jockie on 2017/9/27
 * Email:jockie911@gmail.com
 */

public class PayDialog extends Dialog {

    private String orderId; //订单orderId
    private Context mContext;

    public PayDialog(@NonNull Context context) {
        super(context,R.style.dialog_style);
        this.mContext = context;

    }

    public PayDialog(@NonNull Context context, String orderId) {
        this(context);
        this.orderId = orderId;

//        ToastUtil.show(orderId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_pay);
        ButterKnife.bind(this);

        Window dialogWindow = getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = AbsListView.LayoutParams.MATCH_PARENT;
        lp.y = 0;//设置Dialog距离底部的距离
        dialogWindow.setAttributes(lp);
        initData();
    }

    private void initData() {

    }

    @OnClick({R.id.ll_zfb,R.id.ll_wx,R.id.ll_yl})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.ll_zfb:
                prePay(Constant.Config.PAY_ZFB);
                break;
            case R.id.ll_wx:
                prePay(Constant.Config.PAY_WX);
                break;
            case R.id.ll_yl:
                ToastUtil.show("暂未开放");
                break;
        }
        dismiss();
    }

    /**
     * 根据订单orderId去获取预生成订单详情
     */
    public void prePay(int payMethod){
        if(payMethod == Constant.Config.PAY_WX){
            RestClient.getNovate(mContext, Constant.API.BASE_URL).get(Constant.API.PAY_WX, new ParamMap.Build().addParams("orderIds", orderId).build(), new DmeycBaseSubscriber<WXPayBean>(mContext) {
                @Override
                public void onSuccess(WXPayBean bean) {
                    SPUtils.savaStringData(Constant.Config.ORDER_ID,orderId.contains(",") ? orderId.split(",")[0] : orderId);
                    WXPayBean.DataBean data = bean.getData();

                    IWXAPI msgApi = WXAPIFactory.createWXAPI(mContext, null);
                    msgApi.registerApp(Constant.Key.WEIXIN_APPID);

                    PayReq request = new PayReq();
                    request.appId = data.getAppid();
                    request.partnerId = data.getPartnerid();
                    request.prepayId= data.getPrepayid();
//                    request.packageValue = data.getPackageX();
                    request.packageValue = "Sign=WXPay";
                    request.nonceStr= data.getNoncestr();
                    request.timeStamp= String.valueOf(data.getTimestamp());
                    request.sign= data.getSign();
                    msgApi.sendReq(request);
                }
            });
        }else if(payMethod == Constant.Config.PAY_ZFB){
            RestClient.getNovate(mContext,Constant.API.BASE_URL).get(Constant.API.PAY_ALI, new ParamMap.Build().addParams("orderIds", orderId).build(), new DmeycBaseSubscriber<CommonBean>(mContext) {
                @Override
                public void onSuccess(final CommonBean bean) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            PayTask alipay = new PayTask((Activity) mContext);
                            Map<String, String> stringStringMap = alipay.payV2((String) bean.getData(), true);

                            String resultStatus = stringStringMap.get("resultStatus");
                            Message obtain = Message.obtain();
                            obtain.obj = resultStatus;
                            obtain.what = 0;
                            handler.sendMessage(obtain);
                        }
                    }).start();
                }
            });
        }
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 0){
                String result = (String) msg.obj;
//                AliPayBean aliPayBean = GsonTools.changeGsonToBean(result, AliPayBean.class);
                switch (result){
                    case "9000":
                        SPUtils.savaStringData(Constant.Config.ORDER_ID,orderId);
                        mContext.startActivity(new Intent(mContext, OrderSuccessActivity.class));
                        if(isShowing())
                            dismiss();
                        break;
                    case "8000": //支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态

                        break;
                    case "4000":  //订单支付失败
                        ToastUtil.show("订单支付失败");
                        break;
                    case "5000": //重复请求
                        ToastUtil.show("重复请求");
                        break;
                    case "6001": //用户中途取消
                        ToastUtil.show("用户中途取消");
                        break;
                    case "6002": //网络连接出错
                        ToastUtil.show("网络连接出错");
                        break;
                    case "6004": //支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态

                        break;
                }
            }
        }
    };
}
