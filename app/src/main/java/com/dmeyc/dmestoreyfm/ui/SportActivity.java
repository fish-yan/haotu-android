package com.dmeyc.dmestoreyfm.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.base.BaseRecyclerViewAdapter;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.GetShopCarBean;
import com.dmeyc.dmestoreyfm.bean.MemberListBean;
import com.dmeyc.dmestoreyfm.bean.SportItemListBean;
import com.dmeyc.dmestoreyfm.bean.SportSubmitBean;
import com.dmeyc.dmestoreyfm.bean.SportThinsBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.fragment.ActionItemFragemnt;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.ui.show.section.SpecialAdapter;
import com.dmeyc.dmestoreyfm.utils.DataClass;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ScreenUtil;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.BadgeView;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;
import com.dmeyc.dmestoreyfm.wedgit.CommonPopupWindow;
import com.dmeyc.dmestoreyfm.wedgit.PickerView;
import com.dmeyc.dmestoreyfm.wedgit.flowview.AutoFlowLayout;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class SportActivity extends BaseActivity {

    @Bind(R.id.tv_band)
    TextView tv_band;
    @Bind(R.id.tv_category)
    TextView tv_category;
    @Bind(R.id.tv_size)
    TextView tv_size;


    @Bind(R.id.gv_sport)
    GridView gv_sport;
    @Bind(R.id.tv_right_title_bar)
    TextView tv_right_title_bar;
    @Bind(R.id.tv_type)
    TextView tv_type;
    @Bind(R.id.iv_shopcar)
    BadgeView iv_shopcar;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_sport;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }
    @Override
    protected void initData() {
//        BadgeView badgeView = new BadgeView(this);
//        badgeView.setTargetView(tv);
//        badgeView.setText("V");
//        badgeView.setBadgeMargin(30, 10, 10, 10);//设置边界
//        badgeView.setBackground(10, 0xFFFFA10C);//黄色
        getData();
    }
    Dialog dialog;
    @OnClick({R.id.tv_category,R.id.tv_band,R.id.tv_foot,R.id.iv_shopcar,R.id.tv_right_title_bar,R.id.tv_type})
    public void onClick(View view){
        int veiwid=view.getId();
        if(veiwid==R.id.tv_band){

        }else if(veiwid==R.id.tv_category){
            tv_category.setTextColor(getResources().getColor(R.color.indicator_selected_color));
            tv_band.setTextColor(getResources().getColor(R.color.gray));
            initPopupWindow();
            PopupWindow win=window.getPopupWindow();
            win.setAnimationStyle(R.style.animAlpha);
            window.showAsDropDown(tv_band,  0, 0);
            WindowManager.LayoutParams lp=SportActivity.this.getWindow().getAttributes();
            lp.alpha=1f;
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            getWindow().setAttributes(lp);
        }else if(R.id.tv_foot==veiwid){
//            getshop();
//            goFoot();
        }else if(R.id.iv_shopcar==veiwid){
            goShop();
        }else if(R.id.tv_right_title_bar==veiwid){
            submit();
        }else if(R.id.tv_type==veiwid){
            tv_type.setTextColor(getResources().getColor(R.color.indicator_selected_color));
            initTypePopup();
            PopupWindow win=window.getPopupWindow();
            win.setAnimationStyle(R.style.animAlpha);

            window.showAsDropDown(tv_type,  0, 20);
            WindowManager.LayoutParams lp=getWindow().getAttributes();
//            lp.alpha=0.5f;

            win.setFocusable(true);
          getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
          getWindow().setAttributes(lp);

        }
    }

    ArrayList<String> popdata=new ArrayList<>();
    private CommonPopupWindow window;
    private Button btn_login;
    AutoFlowLayout<String> hotFlowLayout;
    private void initPopupWindow() {
        // get the height of screen
        DisplayMetrics metrics=new DisplayMetrics();
      getWindowManager().getDefaultDisplay().getMetrics(metrics);

        window=new CommonPopupWindow(this, R.layout.popup_list, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT) {
            @Override
            protected void initView() {
                View view=getContentView();
                btn_login=(Button)view.findViewById(R.id.btn_login);
                hotFlowLayout=view.findViewById(R.id.hotFlowLayout);
                popdata.add("羽毛球");
                popdata.add("足球");
                popdata.add("篮球");
                popdata.add("乒乓球");
                if(hotFlowLayout.getChildCount() > 0)
                    hotFlowLayout.removeAllViews();
                for (String s : popdata) {
                    View inflate = LayoutInflater.from(SportActivity.this).inflate(R.layout.item_flowlayout_search, null);
                    TextView textView = (TextView) inflate.findViewById(R.id.item_tv_flowlayout);
                    textView.setText(s);
                    hotFlowLayout.addView(inflate);
                }
                hotFlowLayout.setOnItemClickListener(new AutoFlowLayout.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position, View view) {
                    }
                });
//                    }
//                });
            }

            @Override
            protected void initEvent() {
                btn_login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(window!=null&&window.getPopupWindow().isShowing()){
                            window.getPopupWindow().dismiss();
                        }
                    }
                });
            }
            @Override
            protected void initWindow() {
                super.initWindow();
                PopupWindow instance=getPopupWindow();
                instance.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {

                    }
                });
            }
        };
    }
         class ViewHolder{
        ImageView iv_pic;
        TextView tv_projecttype,tv_band,tv_member,tv_price;
        Button btn_addcar;
        public ViewHolder(View view){
            iv_pic=view.findViewById(R.id.iv_pic);
            tv_projecttype=view.findViewById(R.id.tv_projecttype);
            tv_member=view.findViewById(R.id.tv_member);
            tv_price=view.findViewById(R.id.tv_price);
            tv_band=view.findViewById(R.id.tv_band);
            btn_addcar=view.findViewById(R.id.btn_addcar);
        }
    }
    ListView lv_shop;
    TextView alltv_price;
    public void goShop(){
        dialog  = new Dialog(SportActivity.this, R.style.MyDialog);
        //设置它的ContentView
         setFinishOnTouchOutside(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_shopcar);
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
        alltv_price=dialog.findViewById(R.id.alltv_price);
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                int shap=0;
                for (int i=0;i<getShopCarBean.getData().size();i++){
                    shap=shap+getShopCarBean.getData().get(i).getShopNum();
                }
                iv_shopcar.setBadgeNum(shap);
                iv_shopcar.redraw();

                json="";
                arrayList.clear();
                Gson gson=new Gson();
                for (int i=0;i<getShopCarBean.getData().size();i++){
                    sportItemListBean=new SportItemListBean();
                    sportItemListBean.setId(getShopCarBean.getData().get(i).getId());
                    sportItemListBean.setItem_count(getShopCarBean.getData().get(i).getShopNum());
                    arrayList.add(sportItemListBean);
                }
                json= gson.toJson(arrayList);

            }
        });
        goShopcard();

    }
    public class ShopViewHorlder{
        ImageView iv_imagpic;
         TextView tv_title,tv_band,tv_money,tv_reduce,tv_plaus;
         EditText et_number;
        public ShopViewHorlder(View view){
            iv_imagpic= view.findViewById(R.id.iv_imagpic);
            tv_title=view.findViewById(R.id.tv_title);
            tv_band=view.findViewById(R.id.tv_band);
            tv_money=view.findViewById(R.id.tv_money);
            et_number=view.findViewById(R.id.et_number);
            tv_reduce=view.findViewById(R.id.tv_reduce);
            tv_plaus=view.findViewById(R.id.tv_plaus);
        }
    }

    PizAdapter pizAdapter;
    SportThinsBean sportThinsBean;
    int itempos;
public void  getData(){
    RestClient.getYfmNovate(this).post(Constant.API.YFM_THINSLIST, new ParamMap.Build()
                    .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                    .addParams("type", "")
                    .addParams("projectType","")
                    .addParams("brand", "")
                    .addParams("size", "")
//                .addParams("group_id", getIntent().getIntExtra("group_id",-1))
                    .build(), new DmeycBaseSubscriber<SportThinsBean>() {
        @Override
        public void onSuccess(final SportThinsBean bean) {
//              ToastUtil.show(bean.getMsg());
            sportThinsBean=bean;
            pizAdapter=new PizAdapter();
            gv_sport.setAdapter(pizAdapter);
            getshopCar();
//            gv_sport.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                    itempos=i;
//                }
//            });
        }
    });

}
TextView tv_publize,tv_sport,tv_all;
    private void initTypePopup() {
        // get the height of screen
        DisplayMetrics metrics=new DisplayMetrics();
  getWindowManager().getDefaultDisplay().getMetrics(metrics);
//        window=new CommonPopupWindow(getActivity(), R.layout.pop_time,ViewGroup.LayoutParams.MATCH_PARENT, 500) {
        window=new CommonPopupWindow(SportActivity.this, R.layout.pop_sporttype,ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT) {
            @Override
            protected void initView() {
                View view=getContentView();
                tv_publize=view.findViewById(R.id.tv_publize);
                tv_sport=view.findViewById(R.id.tv_sport);
                tv_all=view.findViewById(R.id.tv_all);
            }
            @Override
            protected void initWindow() {
                super.initWindow();
                PopupWindow instance=getPopupWindow();
            }
            @Override
            protected void initEvent() {
                tv_publize.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        tv_type.setTextColor(getResources().getColor(R.color.gray_deep));
                        tv_band.setVisibility(View.GONE);
                        tv_category.setVisibility(View.GONE);
                        tv_size.setVisibility(View.VISIBLE);
                        window.getPopupWindow().dismiss();
                    }
                });
                tv_sport.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        tv_type.setTextColor(getResources().getColor(R.color.gray_deep));
                        tv_band.setVisibility(View.VISIBLE);
                        tv_category.setVisibility(View.VISIBLE);
                        tv_size.setVisibility(View.GONE);
                        window.getPopupWindow().dismiss();
                    }
                });
                tv_all.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        tv_type.setTextColor(getResources().getColor(R.color.gray_deep));
                        tv_band.setVisibility(View.GONE);
                        tv_category.setVisibility(View.GONE);
                        tv_size.setVisibility(View.GONE);
                        window.getPopupWindow().dismiss();
                    }
                });


            }
        };

    }

    public class PizAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return sportThinsBean.getData().size();
        }
        @Override
        public Object getItem(int i) {
            return null;
        }
        @Override
        public long getItemId(int i) {
            return 0;
        }

        ViewHolder viewHolder;

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = getLayoutInflater().inflate(R.layout.adapter_sportgv, null);
                viewHolder = new ViewHolder(view);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            GlideUtil.loadImage(SportActivity.this,sportThinsBean.getData().get(i).getReferenceImage(),viewHolder.iv_pic);
            viewHolder.tv_projecttype.setText(sportThinsBean.getData().get(i).getName());
            viewHolder.tv_band.setText("品牌："+sportThinsBean.getData().get(i).getBrand());
            viewHolder.tv_member.setText(sportThinsBean.getData().get(i).getSpecification());
            viewHolder.tv_price.setText(sportThinsBean.getData().get(i).getReferencePrice()+"");
            viewHolder.btn_addcar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itempos=i;
                    addShopCar();
                }
            });
            return view;
        }
    }

    public void addShopCar(){
        RestClient.getYfmNovate(this).post(Constant.API.YFM_ADDSHOPCAR, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("id", sportThinsBean.getData().get(itempos).getId())
                .addParams("shopNum","")
                .addParams("measureId", "")
                .addParams("sign", "1")
//                .addParams("group_id", getIntent().getIntExtra("group_id",-1))
                .build(), new DmeycBaseSubscriber<SportThinsBean>() {
            @Override
            public void onSuccess(SportThinsBean bean) {
             ToastUtil.show("添加购物车成功");
                shopnum+=1;
                iv_shopcar.setBadgeNum(shopnum);
                iv_shopcar.redraw();
            }
        });
    }
    ArrayList <SportItemListBean>arrayList=new ArrayList();
    SportItemListBean sportItemListBean;
    String json;
           int shopnum;
    public void getshopCar(){
        RestClient.getYfmNovate(this).post(Constant.API.YFM_GETSHOPCAR, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("measureId", "")
                .build(), new DmeycBaseSubscriber<GetShopCarBean>() {
            @Override
            public void onSuccess(GetShopCarBean bean) {
                ToastUtil.show(bean.getMsg());
                for (int i=0;i<bean.getData().size();i++){
                    shopnum=shopnum+bean.getData().get(i).getShopNum();
                }
                iv_shopcar.setBadgeNum(shopnum);
                iv_shopcar.redraw();
                Gson gson=new Gson();
                arrayList.clear();
                for (int i=0;i<bean.getData().size();i++){
                    sportItemListBean=new SportItemListBean();
                    sportItemListBean.setId(bean.getData().get(i).getId());
                    sportItemListBean.setItem_count(bean.getData().get(i).getShopNum());
                    arrayList.add(sportItemListBean);
                }
                json= gson.toJson(arrayList);
            }
        });
    }

    double allprice;
    ArrayList<EditText> editTextslist=new ArrayList();
    public int clicknum;
    GetShopCarBean getShopCarBean;

    int clickitemreduce=-1;
    int clickitempulsee=-1;
    public void goShopcard(){
        RestClient.getYfmNovate(this).post(Constant.API.YFM_GETSHOPCAR, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("measureId", "")
                .build(), new DmeycBaseSubscriber<GetShopCarBean>() {
            @Override
            public void onSuccess(final GetShopCarBean bean) {
                getShopCarBean=bean;
                     dialog.show();
                for (int i=0;i<bean.getData().size();i++){
                    allprice=allprice+(bean.getData().get(i).getShopNum()*bean.getData().get(i).getReferencePrice());
                }
                alltv_price.setText(allprice+"元");
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
                    ShopViewHorlder shopViewHorlder;
                    @Override
                    public View getView(final int i, View view, ViewGroup viewGroup) {
                        if(view==null){
                            view=getLayoutInflater().inflate(R.layout.adapter_shopcar,null);
                            shopViewHorlder=new ShopViewHorlder(view);
                            view.setTag(shopViewHorlder);
                        }else {
                            shopViewHorlder=(ShopViewHorlder) view.getTag();
                        }
//
                        if(i==clickitemreduce){
                            shopViewHorlder.et_number.setText(clicknum+"");
                        }else {
                            shopViewHorlder.et_number.setText(bean.getData().get(i).getShopNum()+"");
                        }
                        if(i==clickitempulsee){
                            shopViewHorlder.et_number.setText(clicknum+"");
                        }else {
                            shopViewHorlder.et_number.setText(bean.getData().get(i).getShopNum()+"");
                        }

                        editTextslist.add(shopViewHorlder.et_number);
                        GlideUtil.loadImage(SportActivity.this,bean.getData().get(i).getReferenceImage(),shopViewHorlder.iv_imagpic);
                        shopViewHorlder.tv_title.setText(bean.getData().get(i).getName());
                        shopViewHorlder.tv_band.setText("品牌："+bean.getData().get(i).getBrand());
                        shopViewHorlder.tv_money.setText(bean.getData().get(i).getReferencePrice()+"");
                        shopViewHorlder.et_number.setText(bean.getData().get(i).getShopNum()+"");
                        shopViewHorlder.tv_reduce.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                clickitemreduce=i;
                                if(bean.getData().get(i).getShopNum()>0){
                                    clicknum=bean.getData().get(i).getShopNum()-1;

                                    bean.getData().get(i).setShopNum(clicknum);
                                    allprice=0;
                                    for (int i=0;i<bean.getData().size();i++){
                                        allprice=allprice+(bean.getData().get(i).getShopNum()*bean.getData().get(i).getReferencePrice());
                                    }
                                    alltv_price.setText(allprice+"元");
                                }
                               notifyDataSetChanged();
                            }
                        });
                        shopViewHorlder.tv_plaus.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
//                                clickitempulsee=i;
                                if(bean.getData().get(i).getShopNum()<100){
                                    clicknum=bean.getData().get(i).getShopNum()+1;
                                    clickitempulsee=i;
                                    bean.getData().get(i).setShopNum(clicknum);

                                    allprice=0;
                                    for (int i=0;i<bean.getData().size();i++){
                                        allprice=allprice+(bean.getData().get(i).getShopNum()*bean.getData().get(i).getReferencePrice());
                                    }
                                    alltv_price.setText(allprice+"元");
                                }
                                notifyDataSetChanged();

                            }
                        });
                        shopViewHorlder.et_number.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                            @Override
                            public void onFocusChange(View view, boolean b) {
                                if(!b){
//                                    ToastUtil.show("我失去了焦点");
                                    bean.getData().get(i).setShopNum(Integer.parseInt(editTextslist.get(i).getText().toString().trim()));
                                    allprice=0;
                                    for (int i=0;i<bean.getData().size();i++){
                                        allprice=allprice+(bean.getData().get(i).getShopNum()*bean.getData().get(i).getReferencePrice());
                                    }
                                    alltv_price.setText(allprice+"元");
                                }
                            }
                        });

                        return view;
                    }
                });
//                json="";
//                arrayList.clear();
//                Gson gson=new Gson();
//                for (int i=0;i<bean.getData().size();i++){
//                    sportItemListBean=new SportItemListBean();
//                    sportItemListBean.setId(bean.getData().get(i).getId());
//                    sportItemListBean.setItem_count(bean.getData().get(i).getShopNum());
//                    arrayList.add(sportItemListBean);
//                }
//                json= gson.toJson(arrayList);
            }
        });

    }
    public void submit(){
       String loc[]= getIntent().getStringExtra("tv_selectcity").split("—");
        RestClient.getYfmNovate(this).post(Constant.API.YFM_SUBMITORDER, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("project_type", getIntent().getIntExtra("projectid",-1)+"")
                .addParams("activity_time", getIntent().getStringExtra("select_time"))
                .addParams("total_number",Integer.parseInt(getIntent().getStringExtra("select_person")) )
                .addParams("province", loc[0])
                .addParams("city", loc[1])
                .addParams("area", loc[2])
               .addParams("street", getIntent().getStringExtra("detailadress"))
               .addParams("itemList", json)
               .addParams("id", "")
               .build(), new DmeycBaseSubscriber<SportSubmitBean>() {
            @Override
            public void onSuccess(SportSubmitBean bean) {
                DataClass.sportSubmitBean=bean;
                startActivity(new Intent(SportActivity.this,InventoryActivity.class));
            }
        });
    }
}
