package com.dmeyc.dmestoreyfm.ui;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.SportItemListBean;
import com.dmeyc.dmestoreyfm.bean.SportSubmitBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.DataClass;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.NoScrollListView;
import com.dmeyc.dmestoreyfm.wedgit.SlideButton;
import com.google.gson.Gson;


import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

import static android.graphics.Color.parseColor;

public class InventoryActivity extends BaseActivity {
        @Bind(R.id.lv_actionlist)
        NoScrollListView lv_actionlist;
        @Bind(R.id.slb_buton)
        SlideButton slb_buton;
        @Bind(R.id.ll_switch)
        LinearLayout ll_switch;
@Bind(R.id.tv_refreceprice)
  TextView   tv_refreceprice;
    @Bind(R.id.tv_refrececoumt)
    TextView   tv_refrececoumt;
    @Bind(R.id.tv_refrececonse)
    TextView   tv_refrececonse;
    @Bind(R.id.tv_refrececonsecount)
    TextView   tv_refrececonsecount;
    @Bind(R.id.tv_insumentprice)
    TextView   tv_insumentprice;
    @Bind(R.id.tv_teamitem)
    TextView   tv_teamitem;
    @Bind(R.id.tv_teamcount)
    TextView   tv_teamcount;
    @Bind(R.id.tv_plantprice)
    TextView   tv_plantprice;
    @Bind(R.id.tv_allprice)
    TextView   tv_allprice;

    @Bind(R.id.btn_login)
    Button btn_login;

    SportSubmitBean sportSubmitBean;

    ArrayList<EditText>etcounts=new ArrayList<>();
    int count;
    double allprice;

    int check=0;


    int clickpos=-1;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_inventory;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        slb_buton.setBigCircleModel(parseColor("#cccccc"), parseColor("#32abff"), parseColor("#00ffffff"), parseColor("#ffffff"), parseColor("#ffffff"));
         this.sportSubmitBean=DataClass.sportSubmitBean;
        SportSubmitBean.DataBean.ItemListBean  item1 =new SportSubmitBean.DataBean.ItemListBean();



//        viewHolder.tv_count.setText("X"+sportSubmitBean.getData().getVenueItem().getShopNum());
//        viewHolder.et_number.setText(sportSubmitBean.getData().getVenueItem().getShopNum()+"");


//        viewHolder.tv_band.setText("品牌："+sportSubmitBean.getData().getItem_list().get(i-1).getBrand());
//        viewHolder.tv_size.setText("规格："+sportSubmitBean.getData().getItem_list().get(i-1).getSpecification());
//        viewHolder.tv_count.setText("X"+sportSubmitBean.getData().getItem_list().get(i-1).getShopNum());
//        viewHolder.et_number.setText(sportSubmitBean.getData().getItem_list().get(i-1).getShopNum()+"");
        item1.setReferenceImage(sportSubmitBean.getData().getVenueItem().getReferenceImage());
        item1.setName(sportSubmitBean.getData().getVenueItem().getName());
        item1.setReferencePrice(sportSubmitBean.getData().getVenueItem().getReferencePrice());
        item1.setUnit(sportSubmitBean.getData().getVenueItem().getUnit());

        item1.setShopNum(sportSubmitBean.getData().getVenueItem().getShopNum());
        item1.setId(sportSubmitBean.getData().getVenueItem().getId());
        sportSubmitBean.getData().getItem_list().add(0,item1);




        tv_refreceprice.setText(sportSubmitBean.getData().getRefereeItem().getPrice()+"/位");
        tv_refrececoumt.setText("X"+sportSubmitBean.getData().getRefereeItem().getItem_count());
        tv_refrececonse.setText(sportSubmitBean.getData().getRefereeCostItem().getPrice()+"/位");
        tv_refrececonsecount.setText("X"+sportSubmitBean.getData().getRefereeCostItem().getItem_count());
        tv_insumentprice.setText(sportSubmitBean.getData().getInsuranceItem().getPrice()+"/份");
        tv_teamitem.setText(sportSubmitBean.getData().getTeamItem().getPrice()+"/位");
        tv_teamcount.setText("X"+sportSubmitBean.getData().getTeamItem().getItem_count());
        tv_plantprice.setText(sportSubmitBean.getData().getPlatformServiceItem().getPrice()+"/次");
        for (int ii=0;ii<sportSubmitBean.getData().getItem_list().size();ii++){
            allprice=allprice+(sportSubmitBean.getData().getItem_list().get(ii).getShopNum()*sportSubmitBean.getData().getItem_list().get(ii).getReferencePrice());
        }
        allprice=allprice+sportSubmitBean.getData().getRefereeItem().getItem_count()*sportSubmitBean.getData().getRefereeItem().getPrice();
        allprice=allprice+sportSubmitBean.getData().getRefereeCostItem().getItem_count()*sportSubmitBean.getData().getRefereeCostItem().getPrice();
        allprice=allprice+sportSubmitBean.getData().getInsuranceItem().getItem_count()*sportSubmitBean.getData().getInsuranceItem().getPrice();
        allprice=allprice+sportSubmitBean.getData().getTeamItem().getItem_count()*sportSubmitBean.getData().getTeamItem().getPrice();
        allprice=allprice+sportSubmitBean.getData().getPlatformServiceItem().getItem_count()*sportSubmitBean.getData().getPlatformServiceItem().getPrice();
//        allprice=allprice+sportSubmitBean.getData().getVenueItem().getShopNum()*sportSubmitBean.getData().getVenueItem().getReferencePrice();
        tv_allprice.setText(allprice+"元");


        slb_buton.setChecked(false);
        ll_switch.setVisibility(View.GONE);
        slb_buton.setOnCheckedListener(new SlideButton.SlideButtonOnCheckedListener() {
            @Override
            public void onCheckedChangeListener(boolean isChecked) {
                if(isChecked){
                    ll_switch.setVisibility(View.VISIBLE);
                    check=1;
                }else {
                    ll_switch.setVisibility(View.GONE);
                    check=0;
                }
            }
        });
        lv_actionlist.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return sportSubmitBean.getData().getItem_list().size();
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
                if(view==null){
                    view=getLayoutInflater().inflate(R.layout.adapter_inventoryitem,null);
                    viewHolder=new ViewHolder(view);
                      view.setTag(viewHolder);
                }else {
                    viewHolder=(ViewHolder) view.getTag();
                }

//if(0==i){
//    GlideUtil.loadImage(InventoryActivity.this,sportSubmitBean.getData().getVenueItem().getReferenceImage(),viewHolder.iv_icon);
//    viewHolder.tv_itemtile.setText(sportSubmitBean.getData().getVenueItem().getName());
//    viewHolder.tv_price.setText(sportSubmitBean.getData().getVenueItem().getReferencePrice()+"/"+sportSubmitBean.getData().getVenueItem().getUnit());
//    viewHolder.tv_band.setText("活动时间：："+sportSubmitBean.getData().getStartTime());
//    viewHolder.tv_size.setText("地址："+sportSubmitBean.getData().getAddress());
//    viewHolder.tv_count.setText("X"+sportSubmitBean.getData().getVenueItem().getShopNum());
//    viewHolder.et_number.setText(sportSubmitBean.getData().getVenueItem().getShopNum()+"");
//}else {
//    GlideUtil.loadImage(InventoryActivity.this,sportSubmitBean.getData().getItem_list().get(i).getReferenceImage(),viewHolder.iv_icon);
//    viewHolder.tv_itemtile.setText(sportSubmitBean.getData().getItem_list().get(i).getName());
//    viewHolder.tv_price.setText(sportSubmitBean.getData().getItem_list().get(i).getReferencePrice()+"/"+sportSubmitBean.getData().getItem_list().get(i).getUnit());
//    viewHolder.tv_band.setText("品牌："+sportSubmitBean.getData().getItem_list().get(i).getBrand());
//    viewHolder.tv_size.setText("规格："+sportSubmitBean.getData().getItem_list().get(i).getSpecification());
//    viewHolder.tv_count.setText("X"+sportSubmitBean.getData().getItem_list().get(i).getShopNum());
//    viewHolder.et_number.setText(sportSubmitBean.getData().getItem_list().get(i).getShopNum()+"");
//}

                                if(0==i){
                                    GlideUtil.loadImage(InventoryActivity.this,sportSubmitBean.getData().getItem_list().get(i).getReferenceImage(),viewHolder.iv_icon);
                                    viewHolder.tv_itemtile.setText(sportSubmitBean.getData().getItem_list().get(i).getName());
                                    viewHolder.tv_price.setText(sportSubmitBean.getData().getItem_list().get(i).getReferencePrice()+"/"+sportSubmitBean.getData().getItem_list().get(i).getUnit());
                                    viewHolder.tv_band.setText("活动时间：："+sportSubmitBean.getData().getStartTime());
                                    viewHolder.tv_size.setText("地址："+sportSubmitBean.getData().getAddress());
                                    viewHolder.tv_count.setText("X"+sportSubmitBean.getData().getItem_list().get(i).getShopNum());
                                    viewHolder.et_number.setText(sportSubmitBean.getData().getItem_list().get(i).getShopNum()+"");
                                }else {
                                    GlideUtil.loadImage(InventoryActivity.this,sportSubmitBean.getData().getItem_list().get(i).getReferenceImage(),viewHolder.iv_icon);
                                    viewHolder.tv_itemtile.setText(sportSubmitBean.getData().getItem_list().get(i).getName());
                                    viewHolder.tv_price.setText(sportSubmitBean.getData().getItem_list().get(i).getReferencePrice()+"/"+sportSubmitBean.getData().getItem_list().get(i).getUnit());
                                    viewHolder.tv_band.setText("品牌："+sportSubmitBean.getData().getItem_list().get(i).getBrand());
                                    viewHolder.tv_size.setText("规格："+sportSubmitBean.getData().getItem_list().get(i).getSpecification());
                                    viewHolder.tv_count.setText("X"+sportSubmitBean.getData().getItem_list().get(i).getShopNum());
                                    viewHolder.et_number.setText(sportSubmitBean.getData().getItem_list().get(i).getShopNum()+"");
                                }





                if(0!=i){
                    if(i==clickpos){
                        viewHolder.et_number.setText(sportSubmitBean.getData().getItem_list().get(clickpos).getShopNum() + "");
                    }else {
                        //    count = sportSubmitBean.getData().getItem_list().get(i).getShopNum() - 1;
                        viewHolder.et_number.setText(sportSubmitBean.getData().getItem_list().get(i).getShopNum() + "");
                    }
                }




                viewHolder.tv_contreduce.setTag(viewHolder.et_number);
                viewHolder.tv_counpluse.setTag(viewHolder.et_number);
                viewHolder.tv_contreduce.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        ToastUtil.show("我失去了焦点"+i);
                        clickpos=i;
                        notifyDataSetChanged();
                        if (sportSubmitBean.getData().getItem_list().get(i).getShopNum() >0) {
                            count = sportSubmitBean.getData().getItem_list().get(i).getShopNum() - 1;
                            sportSubmitBean.getData().getItem_list().get(i).setShopNum(count);

                            viewHolder.tv_count.setText("X" +  sportSubmitBean.getData().getItem_list().get(i).getShopNum());
                            allprice = 0;
                            for (int ii = 0; ii< sportSubmitBean.getData().getItem_list().size(); ii++) {
                                allprice = allprice + (sportSubmitBean.getData().getItem_list().get(ii).getShopNum() * sportSubmitBean.getData().getItem_list().get(ii).getReferencePrice());
                            }
                            allprice = allprice + sportSubmitBean.getData().getRefereeItem().getItem_count() * sportSubmitBean.getData().getRefereeItem().getPrice();
                            allprice = allprice + sportSubmitBean.getData().getRefereeCostItem().getItem_count() * sportSubmitBean.getData().getRefereeCostItem().getPrice();
                            allprice = allprice + sportSubmitBean.getData().getInsuranceItem().getItem_count() * sportSubmitBean.getData().getInsuranceItem().getPrice();
                            allprice = allprice + sportSubmitBean.getData().getTeamItem().getItem_count() * sportSubmitBean.getData().getTeamItem().getPrice();
                            allprice = allprice + sportSubmitBean.getData().getPlatformServiceItem().getItem_count() * sportSubmitBean.getData().getPlatformServiceItem().getPrice();
                            tv_allprice.setText(allprice + "元");
                        }
                    }
                });
                viewHolder.tv_counpluse.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        ToastUtil.show("我失去了焦点"+i);
                        clickpos=i;
                        notifyDataSetChanged();
                        if (sportSubmitBean.getData().getItem_list().get(i).getShopNum() < 100) {
                            count = sportSubmitBean.getData().getItem_list().get(i).getShopNum() + 1;
//                            etcounts.get(i).setText(count + "");
                            sportSubmitBean.getData().getItem_list().get(i).setShopNum(count);
                            viewHolder.tv_count.setText("X" +  sportSubmitBean.getData().getItem_list().get(i).getShopNum());
                            allprice = 0;
                            for (int ii = 0; ii < sportSubmitBean.getData().getItem_list().size(); ii++) {
                                allprice = allprice + (sportSubmitBean.getData().getItem_list().get(ii).getShopNum() * sportSubmitBean.getData().getItem_list().get(ii).getReferencePrice());
                            }
                            allprice = allprice + sportSubmitBean.getData().getRefereeItem().getItem_count() * sportSubmitBean.getData().getRefereeItem().getPrice();
                            allprice = allprice + sportSubmitBean.getData().getRefereeCostItem().getItem_count() * sportSubmitBean.getData().getRefereeCostItem().getPrice();
                            allprice = allprice + sportSubmitBean.getData().getInsuranceItem().getItem_count() * sportSubmitBean.getData().getInsuranceItem().getPrice();
                            allprice = allprice + sportSubmitBean.getData().getTeamItem().getItem_count() * sportSubmitBean.getData().getTeamItem().getPrice();
                            allprice = allprice + sportSubmitBean.getData().getPlatformServiceItem().getItem_count() * sportSubmitBean.getData().getPlatformServiceItem().getPrice();
                            tv_allprice.setText(allprice + "元");
                        }
                    }
                });
//
                viewHolder.et_number.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean b) {
                        if(!b){
//                                    ToastUtil.show("我失去了焦点");

                            sportSubmitBean.getData().getItem_list().get(i).setShopNum(Integer.parseInt(viewHolder.et_number.getText().toString().trim()));
                            allprice=0;
                            for (int i=0;i<sportSubmitBean.getData().getItem_list().size();i++){
                                allprice=allprice+(sportSubmitBean.getData().getItem_list().get(i).getShopNum()*sportSubmitBean.getData().getItem_list().get(i).getReferencePrice());
                            }
                            allprice=allprice+sportSubmitBean.getData().getRefereeItem().getItem_count()*sportSubmitBean.getData().getRefereeItem().getPrice();
                           double refrecost= sportSubmitBean.getData().getRefereeCostItem().getItem_count()*sportSubmitBean.getData().getRefereeCostItem().getPrice();
                            allprice=allprice+refrecost;
                            allprice=allprice+sportSubmitBean.getData().getInsuranceItem().getItem_count()*sportSubmitBean.getData().getInsuranceItem().getPrice();
                            allprice=allprice+sportSubmitBean.getData().getTeamItem().getItem_count()*sportSubmitBean.getData().getTeamItem().getPrice();
                            allprice=allprice+sportSubmitBean.getData().getPlatformServiceItem().getItem_count()*sportSubmitBean.getData().getPlatformServiceItem().getPrice();
                            tv_allprice.setText(allprice+"元");
                            notifyDataSetChanged();
                        }
                    }
                });
                return view;
            }
        });
    }
    class ViewHolder{
        ImageView iv_icon;
        TextView tv_itemtile,tv_price,tv_band,tv_size,tv_count,tv_contreduce,tv_counpluse;
        EditText et_number;
        public ViewHolder(View view){
            iv_icon= view.findViewById(R.id.iv_icon);
            tv_itemtile=view.findViewById(R.id.tv_itemtile);
            tv_price=view.findViewById(R.id.tv_price);
            tv_band=view.findViewById(R.id.tv_band);
            tv_size=view.findViewById(R.id.tv_size);
            tv_count=view.findViewById(R.id.tv_count);
            et_number=view.findViewById(R.id.et_number);
            tv_contreduce=view.findViewById(R.id.tv_contreduce);
            tv_counpluse=view.findViewById(R.id.tv_counpluse);
        }
    }
    @OnClick(R.id.btn_login)
    public void onClick(View view){
        int viewid=view.getId();
        if(viewid==R.id.btn_login){
            submitorder();
        }
    }
    String json="";
    @Bind({R.id.tv_comminfor})
    EditText tv_comminfor;
    @Bind({R.id.et_commcode})
    EditText et_commcode;
    @Bind({R.id.et_adress})
    EditText et_adress;
    @Bind({R.id.et_callname})
    EditText et_callname;
    @Bind({R.id.et_callnum})
    EditText et_callnum;

    ArrayList <SportItemListBean>arrayList=new ArrayList();
    SportItemListBean sportItemListBean;
    public void submitorder(){

        if(1==check){
            if(TextUtils.isEmpty(tv_comminfor.getText().toString().trim())){
                ToastUtil.show("请输入企业信息");
                return;
            }
            if(TextUtils.isEmpty(et_commcode.getText().toString().trim())){
                ToastUtil.show("请输入企业税务编号");
                return;
            }
            if(TextUtils.isEmpty(et_adress.getText().toString().trim())){
                ToastUtil.show("请输入邮寄地址");
                return;
            }
        }
        if(TextUtils.isEmpty(et_callname.getText().toString().trim())){
            ToastUtil.show("请输入联系人");
            return;
        }
        if(TextUtils.isEmpty(et_callnum.getText().toString().trim())){
            ToastUtil.show("请输入电话号码");
            return;
        }
        arrayList.clear();
        Gson gs=new Gson();
      ParamMap.Build pb=  new ParamMap.Build();
      for (int i=1;i<sportSubmitBean.getData().getItem_list().size();i++){
              sportItemListBean=new SportItemListBean();
              sportItemListBean.setId(sportSubmitBean.getData().getItem_list().get(i).getId());
              sportItemListBean.setItem_count(sportSubmitBean.getData().getItem_list().get(i).getShopNum());
              arrayList.add(sportItemListBean);
      }
        pb.addParams("itemList", gs.toJson(arrayList));
        RestClient.getYfmNovate(this).post(Constant.API.YFM_SUBMISPORT,
                pb.addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("is_have_invoice", check+"")
                .addParams("contactsName", et_callname.getText().toString().trim())
                .addParams("companyName",tv_comminfor.getText().toString().trim() )
                .addParams("ratepayingNumber",et_commcode.getText().toString().trim())
                .addParams("contactsPhone",et_callnum.getText().toString().trim() )
                .addParams("totalPrice",Double.parseDouble(tv_allprice.getText().toString().trim().replace("元","")) )
                .addParams("id",sportSubmitBean.getData().getId())
                .addParams("companyAddress",et_adress.getText().toString().trim())
                .addParams("sportInsurance", "1")
                .build(), new DmeycBaseSubscriber<SportSubmitBean>() {
            @Override
            public void onSuccess(SportSubmitBean bean) {
         ToastUtil.show(bean.getMsg());
            }
        });

    }
}
