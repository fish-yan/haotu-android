package com.dmeyc.dmestoreyfm.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.codbking.widgets.DatePickDialog;
import com.codbking.widgets.OnSureLisener;
import com.codbking.widgets.bean.DateType;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.base.CauculaterRecordBean;
import com.dmeyc.dmestoreyfm.bean.ProjectTypeBean;
import com.dmeyc.dmestoreyfm.bean.StreeBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.dialog.ChooseAddressWheel;
import com.dmeyc.dmestoreyfm.dialog.PersonDialog;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.PickerView;
import com.dmeyc.dmestoreyfm.wedgit.PopupMenu;
import com.dmeyc.dmestoreyfm.wedgit.wheelview.AddressDtailsEntity;
import com.dmeyc.dmestoreyfm.wedgit.wheelview.AddressModel;
import com.dmeyc.dmestoreyfm.wedgit.wheelview.JsonUtil;
import com.dmeyc.dmestoreyfm.wedgit.wheelview.OnAddressChangeListener;
import com.dmeyc.dmestoreyfm.wedgit.wheelview.Utils;
import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.adapter.SuperRcvAdapter;
import com.hss01248.dialog.adapter.SuperRcvHolder;
import com.hss01248.dialog.config.ConfigBean;
import com.tamic.novate.Throwable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class CalculateActivity extends BaseActivity implements OnAddressChangeListener {

    @Bind(R.id.tv_selectcity)
    TextView tv_selectcity;

    @Bind(R.id.tv_time)
    TextView tv_time;
    @Bind(R.id.tv_endtiem)
    TextView tv_endtiem;
    @Bind(R.id.tv_project)
    TextView tv_project;
    @Bind(R.id.tv_person)
    EditText tv_person;
    @Bind(R.id.tv_place)
    TextView tv_place;
    @Bind(R.id.ll_streen)
    TextView ll_streen;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_calculate;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        getProgectType();
//        popupMenu = new PopupMenu(CalculateActivity.this,ar);
    }
int timetype=-1;
PickerView minute_pv;
    Dialog dialog;
    String count="";
    int poscode=-1;
    @OnClick({R.id.rl_selecity,R.id.tv_time,R.id.tv_endtiem,R.id.rl_projecttype,R.id.rl_perosncount,R.id.rl_place,R.id.rl_sport,R.id.rl_publicize,R.id.btn_upload,R.id.ll_streen})
    public void onClick(View view){
        int viewid=view.getId();
        if(viewid==R.id.rl_selecity){
            initWheel();
            getData(view);
        }else if(viewid==R.id.tv_time){
            timetype=1;
            showDatePickDialog(DateType.TYPE_ALL);
        }else if(viewid==R.id.tv_endtiem){
            timetype=2;
            showDatePickDialog(DateType.TYPE_ALL);
        }else if(viewid==R.id.rl_projecttype){
            popupMenu.showLocation(R.id.rl_projecttype);// 设置弹出菜单弹出的位置
            // 设置回调监听，获取点击事件
            popupMenu.setOnItemClickListener(new PopupMenu.OnItemClickListener() {
                @Override
                public void onClick(PopupMenu.MENUITEM item, String str) {
//                    Toast.makeText(getApplication(), str, Toast.LENGTH_SHORT).show();
                    tv_project.setText(str);
                    popupMenu.dismiss();
                }

                @Override
                public void onClick(String str,int pos) {
                    poscode=pos;
//                    Toast.makeText(getApplication(), str, Toast.LENGTH_SHORT).show();
                    tv_project.setText(str);
                    popupMenu.dismiss();
                }
            });
        }else if(R.id.rl_perosncount==viewid){
//            dialog  = new Dialog(CalculateActivity.this, R.style.MyDialog);
//            //设置它的ContentView
//            dialog.setContentView(R.layout.pop_person);
//            minute_pv = (PickerView) dialog.findViewById(R.id.minute_pv);
//          TextView  tv_cancel = (TextView) dialog.findViewById(R.id.tv_cancel);
//         TextView   tv_sub = (TextView) dialog.findViewById(R.id.tv_sub);
//            List<String> data = new ArrayList<String>();
//
//                            for (int i = 0; i < 100; i++) {
//                        data.add(i+"");
//                                 }
//            minute_pv.setData(data);
//
//            minute_pv.setOnSelectListener(new PickerView.onSelectListener()
//            {
//
//                @Override
//                public void onSelect(String text)
//                {
//                    count=text;
//                }
//            });
//            tv_cancel.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    dialog.dismiss();
//                }
//            });
//            tv_sub.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    tv_person.setText(count+"人");
//                    dialog.dismiss();
//                }
//            });
//            dialog.show();
        }else if(R.id.rl_place==viewid){
            dialog  = new Dialog(CalculateActivity.this, R.style.MyDialog);
            //设置它的ContentView
            dialog.setContentView(R.layout.pop_person);
            minute_pv = (PickerView) dialog.findViewById(R.id.minute_pv);
            TextView  tv_cancel = (TextView) dialog.findViewById(R.id.tv_cancel);
            TextView   tv_sub = (TextView) dialog.findViewById(R.id.tv_sub);
            List<String> data = new ArrayList<String>();

            for (int i = 0; i < 100; i++) {
                data.add(i+"");
            }
            minute_pv.setData(data);

            minute_pv.setOnSelectListener(new PickerView.onSelectListener()
            {

                @Override
                public void onSelect(String text)
                {
                    count=text;
                }
            });
            tv_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            tv_sub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tv_place.setText(count+"块");
                    dialog.dismiss();
                }
            });
            dialog.show();
        }else if(R.id.rl_sport==viewid){
            startActivity(new Intent(CalculateActivity.this,SportActivity.class));
        }else if(R.id.rl_publicize==viewid){
            startActivity(new Intent(CalculateActivity.this,PublicizeActivity.class));
        }else if(R.id.btn_upload==viewid){

            if(-1==poscode){
                ToastUtil.show("请选择项目类型");
                return;
            }
            if(TextUtils.isEmpty(tv_selectcity.getText().toString())){
                ToastUtil.show("请选择城市");
                return;
            }
            if(TextUtils.isEmpty(tv_time.getText().toString())){
                ToastUtil.show("请选择时间");
                return;
            }
            if(TextUtils.isEmpty(tv_person.getText().toString().trim())){
                ToastUtil.show("请选择项人数");
                return;
            }
//            if(TextUtils.isEmpty(ll_streen.getText().toString().trim())){
//                ToastUtil.show("请选择街道");
//                return;
//            }
            startActivity(new Intent(CalculateActivity.this,SportActivity.class).
                    putExtra("projectid",pty.getData().get(poscode).getItemCode()).
                    putExtra("tv_selectcity",tv_selectcity.getText().toString().trim()).
                    putExtra("select_time",tv_time.getText().toString()).
                    putExtra("select_person",tv_person.getText().toString().trim())
//                    .
//                    putExtra("detailadress",ll_streen.getText().toString().trim())
            );
//            startActivity(new Intent(CalculateActivity.this,InventoryActivity.class));
        }else if(viewid==R.id.ll_streen){

            getStree();
        }
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

    @Override
    public void onAddressChange(String province, String city, String district) {
        tv_selectcity.setText(province + "—" + city + "—" + district);
    }

    private void showDatePickDialog(DateType type) {
        final DatePickDialog dialog = new DatePickDialog(this);
        //设置上下年分限制
        dialog.setYearLimt(1);
        //设置标题
        dialog.setTitle("选择时间");
        //设置类型
        dialog.setType(type);
        //设置消息体的显示格式，日期格式
//        dialog.setMessageFormat("yyyy-MM-dd HH:mm");
        dialog.setMessageFormat("yyyy-MM-dd");
        //设置选择回调
        dialog.setOnChangeLisener(null);
        //设置点击确定按钮回调
        dialog.setOnSureLisener(new OnSureLisener() {
            @Override
            public void onSure(Date date) {
                if(1==timetype){
                    tv_time.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date));
                }else {
                    tv_endtiem.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date));
                }

//                Toast.makeText(CalculateActivity.this,new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date),Toast.LENGTH_LONG).show();
            }
        });
        dialog.show();
    }
    private PopupMenu popupMenu;
    ProjectTypeBean pty;
    ArrayList<String> ar=new ArrayList<>();
    private void getProgectType() {

        RestClient.getYfmNovate(this).post(Constant.API.YFM_PROJECTLIST, new ParamMap.Build()
                .addParams("key","PROJECT_TYPE_SUAN")
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .build(), new DmeycBaseSubscriber<ProjectTypeBean>() {
            @Override
            public void onSuccess(ProjectTypeBean bean) {
                pty=bean;
                List<ProjectTypeBean.DataBean> lbean= pty.getData();
                for (int i=0;i<lbean.size();i++){
                    ar.add(lbean.get(i).getItemName());
                }
//                String[] abs = new String[]{"关于我们", "检查更新", "意见反馈"};
                popupMenu = new PopupMenu(CalculateActivity.this,ar);
            }
            @Override
            public void onError(Throwable e) {

            }
        });
    }
    ListView lv_shop;
//    TextView alltv_price;
    public void getStree(){
        if(TextUtils.isEmpty(tv_selectcity.getText().toString().trim())){
            ToastUtil.show("请选择城市");
            return;
        }
        String loc[]= tv_selectcity.getText().toString().trim().split("—");

        RestClient.getYfmNovate(this).post(Constant.API.YFM_GETTSTREE, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("province", loc[0])
                .addParams("city", loc[1])
                .addParams("area",loc[2])
                .build(), new DmeycBaseSubscriber<StreeBean>() {
            @Override
            public void onSuccess(final StreeBean bean) {


                dialog  = new Dialog(CalculateActivity.this, R.style.MyDialog);
                //设置它的ContentView
                dialog.setContentView(R.layout.listv_streelist);
                Window window = dialog.getWindow();
                WindowManager.LayoutParams lp = window.getAttributes();
                window.setGravity(Gravity.BOTTOM);
                WindowManager m = getWindowManager();
                Display d = m.getDefaultDisplay(); // 获取屏幕宽、高
                WindowManager.LayoutParams params = window.getAttributes();
                params.height = (int) (d.getHeight() * 0.5); // 高度设置为屏幕的0.5
                params.width = (int) (d.getWidth() * 1); // 宽度设置为屏幕的0.6
                window.setAttributes(params);
                lv_shop = dialog.findViewById(R.id.lv_shop);
                lv_shop.setAdapter(new BaseAdapter() {
                    @Override
                    public int getCount() {
                        return bean.getData().size();
                    }

                    @Override
                    public Object getItem(int i) {
                        return null;
                    }

                    @Override
                    public long getItemId(int i) {
                        return 0;
                    }
                    StreeViewHolder streeViewHolder;
                    @Override
                    public View getView(final int i, View view, ViewGroup viewGroup) {
                        if(view==null){
                            view=getLayoutInflater().inflate(R.layout.item_text,null);
                            streeViewHolder=new StreeViewHolder(view);
                            view.setTag(streeViewHolder);
                        }else {
                            streeViewHolder=(StreeViewHolder) view.getTag();
                        }
                        streeViewHolder.btnee.setText(bean.getData().get(i).getName());
                        streeViewHolder.btnee.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
//                                ToastUtil.show("ss"+i);
                                ll_streen.setText(bean.getData().get(i).getName());
                                   dialog.dismiss();
                            }
                        });
                        return view;
                    }
                });
                dialog.show();

//                lv_shop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> adapterView, View view, int po, long l) {
//                        ll_streen.setText(bean.getData().get(po).getName());
//                        dialog.dismiss();
//                    }
//                });

//                alltv_price=dialog.findViewById(R.id.alltv_price);

//                String[] words3 = new String[]{"12","78","45","89","88","00"};
//                List<String> datas = Arrays.asList(words3);

//                List<String>allstree=new ArrayList<>();
//                allstree.clear();
//                for (int i=0;i<bean.getData().size();i++){
//                    allstree.add(bean.getData().get(i).getName());
//                }
//                // final BottomSheetDialog dialog = new BottomSheetDialog(this);
//                RecyclerView recyclerView = new RecyclerView(CalculateActivity.this);
//                recyclerView.setLayoutManager(new LinearLayoutManager(CalculateActivity.this,LinearLayoutManager.VERTICAL,false));
//                ConfigBean configBean;
//                final ConfigBean finalConfigBean;
//                SuperRcvAdapter adapter = new SuperRcvAdapter(CalculateActivity.this) {
//                    @Override
//                    protected SuperRcvHolder generateCoustomViewHolder(int viewType) {
//
//                        return new SuperRcvHolder<String>(inflate(R.layout.item_text)) {
//
//                            Button mButton;
//                            @Override
//                            public void assignDatasAndEvents(Activity context, final String data) {
//                                if (mButton==null){
//                                    mButton = (Button) itemView.findViewById(R.id.btnee);
//                                }
//                                mButton.setText(data);
//                                mButton.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//                                        ToastUtil.show(data);
//                                    }
//                                });
//                            }
//                        };
//                    }
//                };
//                recyclerView.setAdapter(adapter);
//                adapter.addAll(allstree);
////                adapter.addAll(datas);
////                adapter.addAll(datas);
////                adapter.addAll(datas);
//
//                configBean = StyledDialog.buildCustomBottomSheet(CalculateActivity.this,recyclerView);//不好建立回调
//                configBean.show();


            }
        });

    }


    class StreeViewHolder{
        Button btnee;
        public StreeViewHolder(View view){
            btnee=view.findViewById(R.id.btnee);
        }
    }
}
