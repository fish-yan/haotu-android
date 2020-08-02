package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.bean.RecommendBean;
import com.dmeyc.dmestoreyfm.bean.common.GoodsBean;
import com.dmeyc.dmestoreyfm.dialog.ScreenDialog;
import com.dmeyc.dmestoreyfm.present.CategoryPresenter;
import com.dmeyc.dmestoreyfm.utils.DataClass;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.Util;
import com.dmeyc.dmestoreyfm.wedgit.CommonPopupWindow;
import com.dmeyc.dmestoreyfm.wedgit.PriceView;
import com.dmeyc.dmestoreyfm.wedgit.ScreenView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;


public class CategoryListActivity  extends BaseActivity<CategoryPresenter>   {
    private ScreenView screenView;
//    private GradeViewForScrollView gv_item;
private GridView gv_item;
private int itemid;
    private int categoryuid;
    private TextView tv_desnodata;
    protected SmartRefreshLayout mSmartRefresh;
    MyGradAdapter mga;
private int intseason=3;
private int pricestar=0;
private int priceend=100000;
private LinearLayout  ll_filter;
//private String [] arr;
//    List<ProductCategoryBean> data=new ArrayList<>();
//    ProductCategoryBean pcb=null;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_categorylist;
    }

    @Override
    protected CategoryPresenter initPresenter() {
        return new CategoryPresenter();
    }

    @Override
    protected void initData() {
        screenView = (ScreenView) mRootView.findViewById(R.id.screen_view);
        tv_desnodata=mRootView.findViewById(R.id.tv_desnodata);
        gv_item=mRootView.findViewById(R.id.gv_item);
        ll_filter=mRootView.findViewById(R.id.ll_filter);

        mga=new MyGradAdapter();
        gv_item.setAdapter( mga);

        itemid=getIntent().getIntExtra("itemid",99);
        if(0==itemid){
            itemid=1;
        }
        categoryuid=getIntent().getIntExtra("categoryuid",-99);
        gv_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Util.startProductActivity(CategoryListActivity.this,goodsBeans.get(i).getId());
            }
        });
        mPresenter.getDialogData(this, categoryuid, intseason, 100000000, 0, 1,itemid, new CategoryPresenter.OnDataGetLisener() {
            @Override
            public void onSuccess(RecommendBean bean) {
                RecommendBean sss=bean;
                if(bean.getData().getGoods().size()>0){
                    setData(bean.getData().getGoods());
                    gv_item.setVisibility(View.VISIBLE);
                    tv_desnodata.setVisibility(View.GONE);
                }
            }
            @Override
            public void onFailure(String errMsg) {
                String sss=errMsg;
            }
        });
//        arr=categorychild.split(",");
//        for (int i=0;i<arr.length;i++){
//            pcb=new ProductCategoryBean();
//            pcb.setCategoryName(arr[i]);
//            data.add(pcb);
//        }
//        RestClient.getNovate(this).get(Constant.API.CHOOSE_DETAIL, new ParamMap.Build().addParams("categoryChildren", categoryuid)
//                .addParams("gender", itemid)
//                .addParams("categoryChildren", categoryuid)
//                .addParams("season", 3)
//                .addParams("startPrice", 50)
//                .addParams("endPrice", 10000)
//                .addParams("sort", 1).build(), new DmeycBaseSubscriber<RecommendBean>(this) {
//            @Override
//            public void onSuccess(RecommendBean bean) {
//
//                RecommendBean ttt=bean;
////                adapter.addData(bean.getData());
//            }
//        });
        screenView.addData(DataClass.data);
  }
    ScreenDialog dialog;
    @OnClick({R.id.ll_filter,R.id.ll_synthetical,R.id.ll_vote,R.id.ll_rocord,R.id.iv_search})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.ll_filter:
//                mPresenter.getDialogData();
                dialog  = new ScreenDialog(this, R.style.dialog_style_right,screenView.getmData());
                dialog.onSave(new ScreenDialog.SaveClickLisenter() {
                    @Override
                    public void onSave(String style, String season, String start,String priceend) {

                        requsSave(style,season,start,priceend);
//                        ToastUtil.show(style+"1111"+season+"2222"+start+"333"+priceend);
                    }
                });
                dialog.show();
//                dialog.setSelectedData(screenView.selectMap);
//                dialog.setOnChooseClickListener(new ScreenDialog.OnChooseClickListener() {
//                    @Override
//                    public void onChooseClickListener(int position, boolean isSected, String value, int pos) {
//                        screenView.setChange(position,isSected,value,pos);
//                    }
//                });
                break;
            case R.id.ll_synthetical:
                initPopupWindow();
                PopupWindow win=window.getPopupWindow();
                win.setAnimationStyle(R.style.animAlpha);
                datas.clear();
                datas.add("综合");
                datas.add("新品上架");
                datas.add("价格最低");
                datas.add("价格最高");
                window.showAsDropDown(ll_filter,  0, 0);
//                window.showAtLocation(ll_synthetical, Gravity.TOP, 30, 30);
                WindowManager.LayoutParams lp=getWindow().getAttributes();
//                lp.alpha=0.3f;
                lp.alpha=1f;
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                getWindow().setAttributes(lp);
                break;
            case R.id.ll_vote:
                initPopupWindow();
                PopupWindow wins=window.getPopupWindow();
                wins.setAnimationStyle(R.style.animAlpha);
                datas.clear();

                datas.add("从高到低");
                datas.add("从低到高");
                window.showAsDropDown(ll_filter,  0, 0);
//                window.showAtLocation(ll_synthetical, Gravity.TOP, 30, 30);
                WindowManager.LayoutParams lps=getWindow().getAttributes();
//                lp.alpha=0.3f;
                lps.alpha=1f;
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                getWindow().setAttributes(lps);

                break;
            case R.id.ll_rocord:

                break;
            case R.id.iv_search:

                startActivity(new Intent(this, SearchGoodsActivity.class));
                break;
        }
    }
    List<GoodsBean> goodsBeans;


    public class MyGradAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            if(goodsBeans!=null){
                return goodsBeans.size();
            }else {
                return 0;
            }
        }

        @Override
        public Object getItem(int i) {
            return goodsBeans.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHoder vh=null;
            if(view==null){
                view=getLayoutInflater().inflate(R.layout.adapter_goodsitem,null);
                vh=new ViewHoder(view);
                view.setTag(vh);
            }else {
                vh=(ViewHoder) view.getTag();
            }
            String size = "";
            for (int ii = 0; ii < goodsBeans.get(i).getSizeList().size(); ii++) {
                size = size + goodsBeans.get(i).getSizeList().get(ii)+"";
            }
            vh.item_tv_name.setText(size);
            vh.item_tv_brand.setText(goodsBeans.get(i).getName());
            vh.item_tv_priceview.setPrice(goodsBeans.get(i).getPrice());
            vh.item_iv_istailor.setVisibility(goodsBeans.get(i).isIsCustom() == 0 ? View.INVISIBLE : View.VISIBLE);
            GlideUtil.loadImage(CategoryListActivity.this,goodsBeans.get(i).getImage(),vh.item_iv_cover_pic);
            return view;
        }
    }

    public void setData(List<GoodsBean> goodsBeans){
         this.goodsBeans=goodsBeans;
        mga.notifyDataSetChanged();
    }
    public class ViewHoder{
        private PriceView item_tv_priceview;
        private TextView item_tv_name;
        private TextView item_tv_brand;
        private ImageView item_iv_istailor,item_iv_cover_pic;
        public ViewHoder(View view){
            item_tv_priceview=view.findViewById(R.id.item_tv_priceview);
            item_tv_name=view.findViewById(R.id.item_tv_name);
            item_tv_brand=view.findViewById(R.id.item_tv_brand);
            item_iv_istailor=view.findViewById(R.id.item_iv_istailor);
            item_iv_cover_pic=view.findViewById(R.id.item_iv_cover_pic);
        }
    }
    @OnClick(R.id.iv_right_title_bar)
    public void onClick() {
        Intent intent = new Intent(this, ShopCarActivity.class);
                   startActivity(intent);
    }

    public void requsSave(String style, String season, String start,String end){
        if(!TextUtils.isEmpty(style)){
            for (int i=0;i<DataClass.data.size();i++){
                       if(style.equals(DataClass.data.get(i).getCategoryName())){
                           categoryuid=DataClass.data.get(i).getId();
                       }
            }
        }

        if("春".equals(season)){
            intseason=1;
        }else if("夏".equals(season)){
            intseason=2;
        }else if("秋".equals(season)){
            intseason=3;
        }else {
            intseason=4;
        }

        if(!TextUtils.isEmpty(start)){
           pricestar= Integer.parseInt(start);
           priceend=Integer.parseInt(end);
        }


        mPresenter.getDialogData(this, categoryuid, intseason, priceend, pricestar, 1,itemid, new CategoryPresenter.OnDataGetLisener() {
            @Override
            public void onSuccess(RecommendBean bean) {
                if(dialog!=null){
                    dialog.dismiss();
                }
                RecommendBean sss=bean;
                if(bean.getData().getGoods().size()>0){
                    setData(bean.getData().getGoods());
                    gv_item.setVisibility(View.VISIBLE);
                    tv_desnodata.setVisibility(View.GONE);
                }else {
                    gv_item.setVisibility(View.GONE);
                    tv_desnodata.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onFailure(String errMsg) {
                String sss=errMsg;
            }
        });

    }


    private CommonPopupWindow window;
    private ListView dataList;
    private List<String> datas=new ArrayList<>();

    private List<ImageView> rb_s=new ArrayList<>();
    private   DialogAdapter da;
    private void initPopupWindow() {
        // get the height of screen
        DisplayMetrics metrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenHeight=metrics.heightPixels;
        // create popup window
//        window=new CommonPopupWindow(this, R.layout.popup_list, ViewGroup.LayoutParams.MATCH_PARENT, (int) (screenHeight*0.7)) {
        window=new CommonPopupWindow(this, R.layout.popup_list, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT) {
            @Override
            protected void initView() {
                View view=getContentView();
                dataList=(ListView) view.findViewById(R.id.data_list);
//                dataList.setAdapter(new ArrayAdapter<String>(this, R.layout.item_popup_list, datas));
                da =new DialogAdapter();
                dataList.setAdapter(da);
                dataList.requestFocus();
            }

            @Override
            protected void initEvent() {
                dataList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                        Toast.makeText(CategoryListActivity.this,"wozoule"+position,Toast.LENGTH_LONG).show();
//                        for (int i1=0;i1< rb_s.size();i1++){
//                            if(i1==position){
//                                rb_s.get(position).setVisibility(View.VISIBLE);
////                                rb_s.get(position).setBackground(getResources().getDrawable(R.drawable.screen_ic_right_orange));
//                            }else {
//                                rb_s.get(position).setVisibility(View.GONE);
//                            }
//                        }
//                        rb_s.clear();
                        da.notiftitem(position);

//                        da.notifyDataSetChanged();
                        window.getPopupWindow().dismiss();
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
                        WindowManager.LayoutParams lp=getWindow().getAttributes();
                        lp.alpha=1.0f;
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                        getWindow().setAttributes(lp);
                    }
                });
            }
        };
    }
private List<LinearLayout> ll_items=new ArrayList<>();

    public class ViewHoders{
           TextView item_text;
        LinearLayout ll_imte;
        private ImageView rb_by;
        public ViewHoders(View view){
            item_text=view.findViewById(R.id.item_text);
            rb_by= view.findViewById(R.id.rb_by);
            ll_imte=view.findViewById(R.id.ll_imte);
        }
    }
    public class DialogAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int i) {
            return datas.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            ViewHoders vh;
            if(view==null){
//                             view=getLayoutInflater().inflate(getc,R.layout.popup_list);
                view=LayoutInflater.from(CategoryListActivity.this).inflate(R.layout.item_popup_list,null);
//                ll_items.add(ll_imte);
                vh=new ViewHoders(view);
                view.setTag(vh);
            }else {
                vh=(ViewHoders)view.getTag();
            }
//            rb_s.add(vh.rb_by);

            if(itempos==i){
                vh.rb_by.setVisibility(View.VISIBLE);
               vh.rb_by .setBackground(getResources().getDrawable(R.drawable.screen_ic_right_orange));
            }else {
                vh.rb_by.setVisibility(View.GONE);
                vh.rb_by .setBackground(getResources().getDrawable(R.drawable.screen_ic_right_orange));
            }
            vh.item_text.setText(datas.get(i));
            return view;
        }

        int itempos=-1;
        public void notiftitem(int pos){
            this.itempos=pos;
//            notifyDataSetChanged();
            notifyDataSetInvalidated();
        }
    }
}
