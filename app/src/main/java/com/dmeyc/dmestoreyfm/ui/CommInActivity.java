package com.dmeyc.dmestoreyfm.ui;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BaseApp;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.CommInAfterBean;
import com.dmeyc.dmestoreyfm.bean.ImagePathBean;
import com.dmeyc.dmestoreyfm.bean.ProjectTypeBean;
import com.dmeyc.dmestoreyfm.bean.common.CommonBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.dialog.ChooseAddressWheel;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.OsUtil;
import com.dmeyc.dmestoreyfm.utils.PhotoSelectHelper;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.PopupMenu;
import com.dmeyc.dmestoreyfm.wedgit.wheelview.AddressDtailsEntity;
import com.dmeyc.dmestoreyfm.wedgit.wheelview.AddressModel;
import com.dmeyc.dmestoreyfm.wedgit.wheelview.JsonUtil;
import com.dmeyc.dmestoreyfm.wedgit.wheelview.OnAddressChangeListener;
import com.dmeyc.dmestoreyfm.wedgit.wheelview.Utils;
import com.google.gson.Gson;
import com.jph.takephoto.model.TImage;
import com.makeramen.roundedimageview.RoundedImageView;
import com.tamic.novate.Throwable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import me.shihao.library.XStatusBarHelper;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class CommInActivity extends BaseActivity  implements OnAddressChangeListener {
//    @Bind(R.id.tv_selectcity)
//    TextView tv_selectcity;
    @Bind(R.id.iv_roundmage)
    RoundedImageView iv_roundmage;
    @Bind(R.id.et_backtext)
      EditText et_backtext;
    @Bind(R.id.tv_textnum)
    TextView tv_textnum;
    @Bind(R.id.et_commname)
    EditText et_commname;
    @Bind(R.id.et_phone_num)
    EditText et_phone_num;
    @Bind(R.id.tv_projecttype)
    TextView tv_projecttype;

//    @Bind(R.id.tv_commintype)
//    TextView tv_commintype;

    @Bind(R.id.tv_selectcity)
    TextView tv_selectcity;
    @Bind(R.id.tv_grouptype)
    TextView tv_grouptype;


    @Bind(R.id.et_detailaddrss)
    EditText et_detailaddrss;

    @Bind(R.id.cb_GUDING)
    CheckBox cb_GUDING;
    @Bind(R.id.cb_linshi)
    CheckBox cb_linshi;
    @Bind(R.id.cb_cub)
    CheckBox cb_cub;
@Bind(R.id.btn_upload)
Button btn_upload;
//@Bind(R.id.ll_grouptype)
//LinearLayout ll_grouptype;

//    @Bind(R.id.view_status_bar_place)
//    View mViewStatusBarPlace;
    int type=1;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_commin;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {

//if (Build.VERSION.SDK_INT >= 21) {
// View decorView = getWindow().getDecorView();
//decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
// getWindow().setStatusBarColor(Color.TRANSPARENT);
// }

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getWindow();
//            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }
        et_backtext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tv_textnum.setText(et_backtext.getText().toString().length()+"/"+200);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        cb_cub.setChecked(true);
        cb_GUDING.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    type=4;
                    cb_GUDING.setChecked(true);
                    cb_linshi.setChecked(false);
                    cb_cub.setChecked(false);
                }else {
//                    type=2;
//                    cb_linshi.setChecked(true);
                }
            }
        });
        cb_linshi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    type=2;
                    cb_linshi.setChecked(true);
                    cb_GUDING.setChecked(false);
                    cb_cub.setChecked(false);
                }else {
//                    type=1;
//                    cb_GUDING.setChecked(true);
                }
            }
        });
        cb_cub.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    type=1;
                    cb_cub.setChecked(true);
                    cb_linshi.setChecked(false);
                    cb_GUDING.setChecked(false);
                }else {
//                    type=1;
//                    cb_GUDING.setChecked(true);
                }
            }
        });
        getProgectType();
    }
    ProjectTypeBean pty;
    ArrayList <String> ar=new ArrayList<>();
    ArrayList <String> grouptype=new ArrayList<>();
    private void getProgectType() {
        RestClient.getYfmNovate(this).post(Constant.API.YFM_PROJECTLIST, new ParamMap.Build()
//                .addParams("key","PROJECT_TYPE")
                .addParams("key","PROJECT_TYPE_NORMAL")
                .addParams("user_token",SPUtils.getStringData(Constant.Config.TOKEN))
                .build(), new DmeycBaseSubscriber<ProjectTypeBean>() {
            @Override
            public void onSuccess(ProjectTypeBean bean) {
                pty=bean;
                List<ProjectTypeBean.DataBean> lbean= pty.getData();
                for (int i=0;i<lbean.size();i++){
                    ar.add(lbean.get(i).getItemName());
                }
//                String[] abs = new String[]{"关于我们", "检查更新", "意见反馈"};
                popupMenu = new PopupMenu(CommInActivity.this,ar);
            }
            @Override
            public void onError(Throwable e) {

            }
        });
    }

    private PopupMenu popupMenu;
    private PopupMenu popupMenugrouptype;

    int poscode;
    int gropyintype=1;
    @OnClick({R.id.btn_upload,R.id.ll_cityselect,R.id.iv_roundmage,R.id.ll_perojecttype,R.id.ll_grouptype})
   public void onClick(View view){
        int viewid=view.getId();
        if(R.id.btn_upload==viewid){
//            if(images==null||images.size()==0||TextUtils.isEmpty(images.get(0).getPath())){
//                ToastUtil.show("请选择照片");
//                return;
//            }
//            submit();
            submitApply();
        }else if(R.id.ll_cityselect==viewid){
            initWheel();
            getData(view);
        }else if(viewid==R.id.iv_roundmage){
            new PhotoSelectHelper(this,0).setCrop(true).show();
        }else if(viewid==R.id.ll_perojecttype){
            popupMenu.showComminLocation(R.id.tv_projecttype);// 设置弹出菜单弹出的位置
            // 设置回调监听，获取点击事件
            popupMenu.setOnItemClickListener(new PopupMenu.OnItemClickListener() {
                @Override
                public void onClick(PopupMenu.MENUITEM item, String str) {
//                    Toast.makeText(getApplication(), str, Toast.LENGTH_SHORT).show();
                    tv_projecttype.setText(str);
                    popupMenu.dismiss();
                }

                @Override
                public void onClick(String str,int pos) {
                    poscode=pos;
                    tv_projecttype.setText(str);
                    popupMenu.dismiss();
                }
            });
        }else if(R.id.ll_grouptype==viewid){
            grouptype.clear();
            grouptype.add("俱乐部");
            grouptype.add("企业");
            popupMenugrouptype = new PopupMenu(CommInActivity.this,grouptype);
            popupMenugrouptype.showComminLocation(R.id.tv_grouptype);// 设置弹出菜单弹出的位置
            // 设置回调监听，获取点击事件
            popupMenugrouptype.setOnItemClickListener(new PopupMenu.OnItemClickListener() {
                @Override
                public void onClick(PopupMenu.MENUITEM item, String str) {
//                    Toast.makeText(getApplication(), str, Toast.LENGTH_SHORT).show();
                    tv_grouptype.setText(str);
                    popupMenugrouptype.dismiss();
                }

                @Override
                public void onClick(String str,int pos) {
                    if(pos==0){
                       gropyintype=1;
                    }else if(1==pos){
                        gropyintype=4;
                    }
                    tv_grouptype.setText(str);
                    popupMenugrouptype.dismiss();
                }
            });
        }
    }
    CommInAfterBean commonBean;
    private void submitApply() {
//        if(TextUtils.isEmpty(tv_commintype.getText().toString().trim())){
//          ToastUtil.show("请选择群类型");
//            return;
//        }
        if(TextUtils.isEmpty(tv_projecttype.getText().toString().trim())){
            ToastUtil.show("请选择项目类型");
            return;
        }
        if(TextUtils.isEmpty(et_commname.getText().toString().trim())){
            ToastUtil.show("请输入群名称");
            return;
        }
        if(TextUtils.isEmpty(tv_selectcity.getText().toString().trim())){
            ToastUtil.show("请选择城市地区");
            return;
        }
        if(TextUtils.isEmpty(et_detailaddrss.getText().toString().trim())){
            ToastUtil.show("请输入详细地址");
            return;
        }
        if(TextUtils.isEmpty(et_phone_num.getText().toString().trim())){
            ToastUtil.show("请输入联系方式");
            return;
        }
//        if(TextUtils.isEmpty(groupicon)){
//            ToastUtil.show("请选择照片");
//            return;
//        }
        if(TextUtils.isEmpty(et_backtext.getText().toString().trim())){
            ToastUtil.show("请添加备注");
            return;
        }
        btn_upload.setBackground(getResources().getDrawable(R.drawable.shap_grayconner));
        btn_upload.setClickable(false);
        RestClient.getYfmNovate(this).post(Constant.API.YFM_SUBMIT_GORPAPPLY, new ParamMap.Build()
                .addParams("user_token",SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("group_name", et_commname.getText().toString().trim())
                .addParams("group_type", gropyintype)
                .addParams("project_type", pty.getData().get(poscode).getItemCode())
//                .addParams("project_type", 1)
                .addParams("remark",et_backtext.getText().toString())
                .addParams("province", province)
                .addParams("city",city)
                .addParams("area",district)
                .addParams("activity_venue_address", et_detailaddrss.getText().toString().trim())
//                .addParams("group_logo",groupicon)
                .build(), new DmeycBaseSubscriber<CommInAfterBean>() {
            @Override
            public void onSuccess(CommInAfterBean bean) {
                commonBean=bean;
                goShop();
//                ToastUtil.show("群入驻成功,您可切换身份来发布您的活动");
                SPUtils.savaStringData(Constant.Config.ROLECODE,"2");
//                finish();
            }
            @Override
            public void onError(Throwable e) {
                Toast.makeText(BaseApp.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                btn_upload.setBackground(getResources().getDrawable(R.drawable.shape_tailor_sure));
                btn_upload.setClickable(true);
            }
        });
    }
    private ChooseAddressWheel chooseAddressWheel = null;
    private void initWheel() {
        chooseAddressWheel = new ChooseAddressWheel(this);
        chooseAddressWheel.setOnAddressChangeListener(this);
    }
    private void getData(View view) {


        String address = Utils.readAssert(this, "address.txt");
        AddressModel model = JsonUtil.parseJson(address, AddressModel.class);
        if (model != null) {
            AddressDtailsEntity data = model.Result;
            if (data == null) return;
//            chooseAddress.setText(data.Province + " " + data.City + " " + data.Area);
            if (data.ProvinceItems != null && data.ProvinceItems.Province != null) {
                chooseAddressWheel.setProvince(data.ProvinceItems.Province);
                chooseAddressWheel.defaultValue(data.Province, data.City, data.Area);
            }
        }
        chooseAddressWheel.show(view);
    }
    String province,city, district;
    @Override
    public void onAddressChange(String province, String city, String district) {
        this.province=province;
        this.city=city;
        this.district=district;
        tv_selectcity.setText(province + " " + city + " " + district);
    }
    ArrayList<TImage> images;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null && resultCode == RESULT_OK){
            if(data.getBooleanExtra(Constant.Config.SUCCESS,false)){
                images = (ArrayList<TImage>) data.getSerializableExtra("images");
//                if(this!=null){
                GlideUtil.loadImage(this,images.get(0).getPath(),iv_roundmage);
//                }
            }
        }
    }

    public void  submit(){
        OkHttpClient client = new OkHttpClient();
        File file = new File(images.get(0).getPath());
        RequestBody fileBody = RequestBody.create(MediaType.parse("image/*"), file);

        MultipartBody.Builder mb= new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(), fileBody)
//                        .addFormDataPart("imagetype", "image/png")
                .addFormDataPart("user_token", SPUtils.getStringData(Constant.Config.TOKEN));
//        if(1==type){
            mb.addFormDataPart("isLogo",1+"");
//        }else {
//            mb.addFormDataPart("isLogo",0+"");
//        }
              final RequestBody requestBody  =mb .build();

        Request request = new Request.Builder()
                .url(Constant.API.YFM_BASE_URL+"api/v1.0/file/uploadSingle")
                .post(requestBody)
                .build();

//                client.newCall(request).execute(new Callback<ImagePathBean>());
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                ToastUtil.show(call.toString());
            }
            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                Gson gs=new Gson();
                final ImagePathBean imap=  gs.fromJson(response.body().string(),ImagePathBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        GlideUtil.loadImage(CommInActivity.this, imap.getData(), iv_roundmage);
//                        submitApply(imap.getData());
//                        ToastUtil.show(imap.getMsg());
                    }
                });
            }
        });
    }

    /**
     * 设置沉浸式状态栏
     *
     * @param fontIconDark 状态栏字体和图标颜色是否为深色
     */
//    protected void setImmersiveStatusBar(boolean fontIconDark, int statusBarPlaceColor) {
//        setTranslucentStatus();
//        if (fontIconDark) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
//                    || OsUtil.isMIUI()
//                    || OsUtil.isFlyme()) {
//                setStatusBarFontIconDark(true);
//            } else {
//                if (statusBarPlaceColor == Color.WHITE) {
//                    statusBarPlaceColor = 0xffcccccc;
//                }
//            }
//        }
//        setStatusBarPlaceColor(statusBarPlaceColor);
//    }

//    private void setStatusBarPlaceColor(int statusColor) {
//        if (mViewStatusBarPlace != null) {
//            mViewStatusBarPlace.setBackgroundColor(statusColor);
//        }
//    }

    /**
     * 全透状态栏
     */
    protected void setStatusBarFullTransparent() {
        if (Build.VERSION.SDK_INT >= 21) {//21表示5.0
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= 19) {//19表示4.4
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //虚拟键盘也透明
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }




    Dialog dialog;
    ListView lv_shop;
    TextView alltv_price;
    TextView tv_toinfo,tv_changeident;
    public void goShop(){
        dialog  = new Dialog(CommInActivity.this, R.style.MyDialog);
        //设置它的ContentView
        setFinishOnTouchOutside(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_commin);
        Window window = dialog.getWindow();

        WindowManager.LayoutParams lp = window.getAttributes();
        window.setGravity(Gravity.CENTER);
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高
        WindowManager.LayoutParams params = window.getAttributes();
        params.height = (int) (d.getHeight() * 0.3); // 高度设置为屏幕的0.5
        params.width = (int) (d.getWidth() * 0.8); // 宽度设置为屏幕的0.6
        window.setAttributes(params);
        tv_toinfo=dialog.findViewById(R.id.tv_toinfo);
//        lv_shop = dialog.findViewById(R.id.lv_shop);
        tv_changeident=dialog.findViewById(R.id.tv_changeident);
        dialog.show();
        tv_changeident.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                SPUtils.savaStringData(Constant.Config.IDENITY,"0");
                startActivity(new Intent(CommInActivity.this,BMainActivity.class));
                finish();
            }
        });

        tv_toinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                startActivity(new Intent(CommInActivity.this,ChartInforActivity.class).putExtra("group_id",(int)commonBean.getData()));
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


}
