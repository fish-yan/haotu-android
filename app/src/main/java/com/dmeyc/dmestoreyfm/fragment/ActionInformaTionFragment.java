package com.dmeyc.dmestoreyfm.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseFragment;
import com.dmeyc.dmestoreyfm.bean.PkResultDetailBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.ui.ActionItemActivity;
import com.dmeyc.dmestoreyfm.ui.PkResultActivity;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.CommDialog;

import butterknife.Bind;
import butterknife.OnClick;

import static com.dmeyc.dmestoreyfm.ui.ActionItemActivity.isAvilible;

@SuppressLint("ValidFragment")
public class ActionInformaTionFragment extends BaseFragment {
@Bind(R.id.tv_activityname)
    TextView tv_activityname;
@Bind(R.id.tv_time)
        TextView tv_time;
@Bind(R.id.tv_activityaddrss)
        TextView tv_activityaddrss;
@Bind(R.id.tv_actionadress)
        TextView tv_actionadress;
@Bind(R.id.tv_phone)
        TextView tv_phone;
    int groupid;
    @Bind(R.id.tv_map)
    LinearLayout tv_map;
    @SuppressLint("ValidFragment")
    public ActionInformaTionFragment(int groupid){
        this.groupid=groupid;
    }
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_pageractioninfo;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initData(View view) {
      setData();
    }
    PkResultDetailBean pkResultDetailBean;
    public void setData(){

            RestClient.getYfmNovate(getActivity()).post(Constant.API.YFM_PKRESULT, new ParamMap.Build()
                    .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
//                    .addParams("group_pk_id", 1)
                    .addParams("group_pk_id", groupid)
                    .build(), new DmeycBaseSubscriber<PkResultDetailBean>() {
                @Override
                public void onSuccess(PkResultDetailBean bean) {
                    pkResultDetailBean=bean;
                    tv_activityname.setText(bean.getData().getGroup_pk_name());
                    tv_time.setText(bean.getData().getStart_date());
                    tv_activityaddrss.setText(bean.getData().getVenueName());
                    tv_actionadress.setText(bean.getData().getAddress());
                    tv_phone.setText(bean.getData().getOrganizerName()+":"+bean.getData().getOrganizerPhone());
//                    ToastUtil.show(bean.getMsg());
//                    GlideUtil.loadImage(getActivity(),bean.getData().get);
                }
            });

    }
    final public static int REQUEST_CODE_ASK_CALL_PHONE=123;
    private CommDialog.Builder builder;
    private CommDialog mDialog;
    @OnClick({R.id.tv_map,R.id.tv_call})
    public void onClick(View view){
        int viewid=view.getId();
        if(R.id.tv_map==viewid){
            if (isAvilible(getActivity(), "com.autonavi.minimap")) {

                StringBuffer stringBuffer = new StringBuffer("androidamap://route?sourceApplication=").append("amap");
                stringBuffer.append("&dlat=").append(pkResultDetailBean.getData().getLatitude())
                        .append("&dlon=").append(pkResultDetailBean.getData().getLongitude())
                        .append("&dname=").append(pkResultDetailBean.getData().getAddress())
                        .append("&dev=").append(0)
                        .append("&t=").append(2);

                Intent intent = new Intent("android.intent.action.VIEW", android.net.Uri.parse(stringBuffer.toString()));
                intent.setPackage("com.autonavi.minimap");
                startActivity(intent);


//                try {
//                    //sourceApplication
//
////                    Intent intent = Intent.getIntent("androidamap://navi?sourceApplication=公司的名称（随意写）&poiname=我的目的地&lat=" + activityDeatilBean.getData().getLatitude()+ "&lon=" + activityDeatilBean.getData().getLongitude() + "&dev=0");
////                    startActivity(intent);
//                } catch (URISyntaxException e) {
//                    e.printStackTrace();
//                }
            } else {
                ToastUtil.show("您尚未安装高德地图或地图版本过低");
            }
        }else if(R.id.tv_call==viewid){
            builder = new CommDialog.Builder(getActivity());
            showTwoButtonDialog("提示\n确认拨打电话："+pkResultDetailBean.getData().getOrganizerPhone()+"吗?", "取消", "确定", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDialog.dismiss();
                    //这里写自定义处理XXX
                }
            }, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDialog.dismiss();
                    //这里写自定义处理XXX

                    if (Build.VERSION.SDK_INT >= 23) {
                        int checkCallPhonePermission = ContextCompat.checkSelfPermission(getActivity(),
                                Manifest.permission.CALL_PHONE);
                        if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(getActivity(), new String[] {
                                    Manifest.permission.CALL_PHONE
                            }, REQUEST_CODE_ASK_CALL_PHONE);
                            return;
                        } else {
                            Intent intent = new Intent(Intent.ACTION_CALL);
                            Uri data = Uri.parse("tel:" + pkResultDetailBean.getData().getOrganizerPhone());
                            intent.setData(data);
                            startActivity(intent);
                            // 上面已经写好的拨号方法 callDirectly(mobile);
                        }
                    } else {
                        // 上面已经写好的拨号方法 callDirectly(mobile);
                        Intent intent = new Intent(Intent.ACTION_CALL);
                        Uri data = Uri.parse("tel:" + pkResultDetailBean.getData().getOrganizerPhone());
                        intent.setData(data);
                        startActivity(intent);
                    }
//                    Intent intent = new Intent(Intent.ACTION_CALL);
//                    Uri data = Uri.parse("tel:" + pkResultDetailBean.getData().getOrganizerPhone());
//                    intent.setData(data);
//                    startActivity(intent);
                }
            });
        }
    }
    private void showTwoButtonDialog(String alertText, String confirmText, String cancelText, View.OnClickListener conFirmListener, View.OnClickListener cancelListener) {
        mDialog = builder.setMessage(alertText)
                .setPositiveButton(confirmText, conFirmListener)
                .setNegativeButton(cancelText, cancelListener)
                .createTwoButtonDialog();
        mDialog.show();
    }
}
