package com.dmeyc.dmestoreyfm.wxapi;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.IsTrueNameBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.event.MyEvent;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.ui.BMainActivity;
import com.dmeyc.dmestoreyfm.ui.ChartInforActivity;
import com.dmeyc.dmestoreyfm.ui.MainActivity;
import com.dmeyc.dmestoreyfm.ui.OrderSuccessActivity;
import com.dmeyc.dmestoreyfm.ui.SubmitOrderActivity;
import com.dmeyc.dmestoreyfm.ui.TeachInActivity;
import com.dmeyc.dmestoreyfm.ui.TrueNameActivity;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.tamic.novate.Throwable;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;

public class WXPayEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {

    private IWXAPI wxapi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxpay_entry);

        wxapi = WXAPIFactory.createWXAPI(this, Constant.Key.WEIXIN_APPID);
        wxapi.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        wxapi.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }
    @Override
    public void onResp(BaseResp baseResp) {
        switch (baseResp.errCode){
            case 0: //成功
                Intent intent = new Intent("wx_success");
                sendBroadcast(intent);
                EventBus.getDefault().post(new MyEvent.WxPaySuccess());
//                checkIsTrueName();
//                goShop();
//                startActivity(new Intent(this,OrderSuccessActivity.class));
                break;
            case -1:  //错误
                ToastUtil.show("签名错误");
                break;
            case -2: //用户取消
                ToastUtil.show("用户取消");
                break;
        }
        finish();
    }


    Dialog dialog;
    ListView lv_shop;
    TextView alltv_price;
    TextView tv_toinfo;
    public void goShop(){
        dialog  = new Dialog(this, R.style.MyDialog);
        //设置它的ContentView
        setFinishOnTouchOutside(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_payresult);
        Window window = dialog.getWindow();

        WindowManager.LayoutParams lp = window.getAttributes();
        window.setGravity(Gravity.CENTER);
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高
        WindowManager.LayoutParams params = window.getAttributes();
        params.height = (int) (d.getHeight() * 0.2); // 高度设置为屏幕的0.5
        params.width = (int) (d.getWidth() * 0.8); // 宽度设置为屏幕的0.6
        window.setAttributes(params);
        tv_toinfo=dialog.findViewById(R.id.tv_toinfo);
        dialog.show();

        tv_toinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
//                checkIsTrueName();
//                toTruename();
//                startActivity(new Intent(WXPayEntryActivity.this,ChartInforActivity.class).putExtra("group_id",(int)commonBean.getData()));
//                finish();
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



    Dialog dialogs;
//    ListView lv_shops;
//    TextView alltv_prices;
//    TextView tv_toinfos;
    public void toTruename(){
        dialogs  = new Dialog(WXPayEntryActivity.this, R.style.MyDialog);
        //设置它的ContentView
        setFinishOnTouchOutside(false);
        dialogs.setCanceledOnTouchOutside(true);
        dialogs.setCancelable(true);
        dialogs.setContentView(R.layout.dialog_toinstrument);
        Window window = dialogs.getWindow();

        WindowManager.LayoutParams lp = window.getAttributes();
        window.setGravity(Gravity.CENTER);
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高
        WindowManager.LayoutParams params = window.getAttributes();
        params.height = (int) (d.getHeight() * 0.3); // 高度设置为屏幕的0.5
        params.width = (int) (d.getWidth() * 0.8); // 宽度设置为屏幕的0.6
        window.setAttributes(params);
        tv_toinfo=dialogs.findViewById(R.id.tv_toinfo);
//        lv_shop = dialog.findViewById(R.id.lv_shop);
//        alltv_price=dialog.findViewById(R.id.alltv_price);
        dialogs.show();
        tv_toinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WXPayEntryActivity.this,TrueNameActivity.class));
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
    private boolean istruename=false;
    public void checkIsTrueName(){
        RestClient.getYfmNovate(this).post(Constant.API.YFM_CHECKTRUENAME, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .build(), new DmeycBaseSubscriber<IsTrueNameBean>() {
            @Override
            public void onSuccess(IsTrueNameBean bean) {
//                finish();
                istruename=bean.isData();
                if(istruename){
                    startActivity(new Intent(WXPayEntryActivity.this,MainActivity.class));
                    finish();
                }else {
                    toTruename();
                }
            }
            @Override
            public void onError(Throwable e) {

            }
        });

    }
}
