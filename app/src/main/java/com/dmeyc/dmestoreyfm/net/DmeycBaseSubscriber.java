package com.dmeyc.dmestoreyfm.net;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.dmeyc.dmestoreyfm.base.BaseApp;
import com.dmeyc.dmestoreyfm.dialog.LoadingDialog;
import com.dmeyc.dmestoreyfm.newbase.IBaseView;
import com.dmeyc.dmestoreyfm.newui.login.LoginActivity;
import com.dmeyc.dmestoreyfm.ui.NoNetWorkActivity;
import com.dmeyc.dmestoreyfm.ui.YFMLoginActivity;
import com.dmeyc.dmestoreyfm.utils.GsonTools;
import com.tamic.novate.BaseSubscriber;
import com.tamic.novate.Throwable;
import com.tamic.novate.util.NetworkUtil;

import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;

import okhttp3.ResponseBody;

/**
 * Created by jockie on 2017/12/25
 * Email:jockie911@gmail.com
 */

public abstract class DmeycBaseSubscriber<K> extends BaseSubscriber<ResponseBody> {

    private Context context;
    private LoadingDialog loadingDialog;
    private IBaseView mBaseView;

    public DmeycBaseSubscriber() {
        this.context = BaseApp.getContext();
    }

    public DmeycBaseSubscriber(Context context) {
        super(context);
        this.context = context;
        loadingDialog = new LoadingDialog(context);
    }


    public DmeycBaseSubscriber(IBaseView iBaseView) {
        super(BaseApp.getContext());
        mBaseView = iBaseView;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.v("Novate", "-->http is start");
        // todo some common as show loadding  and check netWork is NetworkAvailable
        // if  NetworkAvailable no !   must to call onCompleted
        if (!NetworkUtil.isNetworkAvailable(context)) {
            onCompleted();
            Intent intent = new Intent(context, NoNetWorkActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            return;
           }
        // 之前的加载弹窗
        if (loadingDialog != null) {
            if (loadingDialog.isShowing()) {
                loadingDialog.dismiss();
            }
            loadingDialog.show();
        }

        //新的加载弹窗
        if(mBaseView!=null){
            mBaseView.showProgress();
        }
    }
    @Override
    public void onCompleted() {
        super.onCompleted();
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
        if (mBaseView!=null){
            mBaseView.hideProgress();
        }
    }

    @Override
    public void onError(Throwable e) {
//        e.getCode()+
                Toast.makeText(BaseApp.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onNext(ResponseBody t) {
        try {
            JSONObject object = new JSONObject(t.string());
            if (object.has("code")) {
                int code = object.getInt("code");
                System.out.println(" ------------ onNext ----------- " + String.valueOf(code) + "  \\ " + object.toString());
                switch (code) {
                    case 200:
                        Class<K> kClass = (Class<K>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
                        K k = GsonTools.changeGsonToBean(object.toString(), kClass);
                        onSuccess(k);
                        break;
                    case 0: //java后台自定义异常
                        if (object.has("msg")) {
                            Throwable e = new Throwable(new java.lang.Throwable(object.getString("msg")), 0, object.getString("msg"));
                            onError(e);
                        }
                        break;
                    case 300: //java后台定义的错误
                        if (object.has("msg")) {
                            Intent intent = new Intent(context, LoginActivity.class);
//                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                            Throwable e = new Throwable(new java.lang.Throwable(object.getString("msg")), 300, object.getString("msg"));
                            onError(e);
                        }
                        break;
                    case 400:
                        Throwable e = new Throwable(new java.lang.Throwable(object.getString("msg")), 400, object.getString("msg"));
                        onError(e);
                        break;
                    case 401:
                        Throwable es = new Throwable(new java.lang.Throwable(object.getString("msg")), 401, object.getString("msg"));
                        onError(es);
                        break;
              }
            } else if (object.has("iserror")) {
                int code = object.getInt("iserror");
                System.out.println(" ------------ onNext ----------- " + String.valueOf(code) + "  \\ " + object.toString());
                if (code == 0) {
                    Class<K> kClass = (Class<K>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
                    K k = GsonTools.changeGsonToBean(object.toString(), kClass);
                    onSuccess(k);
                 } else {
                    if (object.has("info")) {
                        Throwable e = new Throwable(new java.lang.Throwable(object.getString("info")), code, object.getString("info"));
                        onError(e);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public abstract void onSuccess(K bean);
}
