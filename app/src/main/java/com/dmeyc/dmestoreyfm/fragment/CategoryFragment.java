package com.dmeyc.dmestoreyfm.fragment;

import android.annotation.SuppressLint;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.spq;
import com.dmeyc.dmestoreyfm.base.BaseFragment;
import com.dmeyc.dmestoreyfm.bean.RecommendBean;
import com.dmeyc.dmestoreyfm.bean.common.GoodsBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.decoration.SpaceItemDecoration;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.present.BandDetailPresenter;
import com.dmeyc.dmestoreyfm.utils.DataClass;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.CommonPopupWindow;
import com.dmeyc.dmestoreyfm.wedgit.PriceView;
import com.tamic.novate.Throwable;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class CategoryFragment extends BaseFragment {

//    @Bind(R.id.listview)
//    PullToZoomListViewEx listViewEx;
private LinearLayout ll_filter;
//    private GridView gv_item;
    private RecyclerView gv_item;
    MyGradAdapter mga;
    private TextView tv_desnodata;
    private spq sp;
    private int  storyid;
//    private ImageView iv_band;
//    private String log;
    @Override
    protected int getLayoutRes() {
        return R.layout.frg_cat;
    }

    public CategoryFragment(int storyid){
        this.storyid=storyid;
//        this.log=log;
    }
    public CategoryFragment(){

//        this.log=log;
    }
    @Override
    protected void initData() {
//        ll_filter=mRootView.findViewById(R.id.ll_filter);
        gv_item=mRootView.findViewById(R.id.gv_item);
        tv_desnodata=mRootView.findViewById(R.id.tv_desnodata);
//        screenView = (ScreenView) mRootView.findViewById(R.id.screen_view);
//        iv_band=mRootView.findViewById(R.id.iv_band);
        LinearLayoutManager sta = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        gv_item.setLayoutManager(sta);
        GridLayoutManager st=new GridLayoutManager(getActivity(),2);

        gv_item.setHasFixedSize(true);
        gv_item.setLayoutManager(st);
        sp = new spq(getActivity(),goodsBeans, gv_item,tv_desnodata);
        gv_item.addItemDecoration(new SpaceItemDecoration(true));
        gv_item.setAdapter(sp);

        getDetail(storyid,null);
//        gv_item.setAdapter( mga);
//        getDialogData( categoryuid, intseason, 1000000, pricestar, 1,itemid);
//        screenView.addData(DataClass.data);
//        final RecommendAdapter adapter = new RecommendAdapter(getActivity());
//        listViewEx.setAdapter(adapter);
//
//        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
//        getActivity().getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
//        int mScreenWidth = localDisplayMetrics.widthPixels;
//        AbsListView.LayoutParams localObject = new AbsListView.LayoutParams(mScreenWidth, (int) (9.0F * (mScreenWidth / 16.0F)));
//        listViewEx.setHeaderLayoutParams(localObject);
//
//        RestClient.getNovate(getActivity()).get(Constant.API.RECOMMEND, new ParamMap.Build().build(), new DmeycBaseSubscriber<RecommendBean>(getActivity()) {
//            @Override
//            public void onSuccess(RecommendBean bean) {
//                adapter.addData(bean.getData());
//            }
//        });
    }
    @Override
    protected void initData(View view) {

    }



//    public void setLog(String log) {
//
//        GlideUtil.loadImage(getActivity(),
//                log
//                ,iv_band);
//    }
//    ScreenDialog dialog;
//    private ScreenView screenView;

//    @OnClick({R.id.ll_filter,R.id.ll_synthetical,R.id.ll_vote,R.id.ll_rocord})
//    public void onClick(View view){
//        switch (view.getId()){
//            case R.id.ll_filter:
////                mPresenter.getDialogData();
//                dialog  = new ScreenDialog(getActivity(), R.style.dialog_style_right,screenView.getmData());
//                dialog.onSave(new ScreenDialog.SaveClickLisenter() {
//                    @Override
//                    public void onSave(String style, String season, String start,String priceend) {
//                        requsSave(style,season,start,priceend);
//                    }
//                });
//                dialog.show();
////                dialog.setSelectedData(screenView.selectMap);
////                dialog.setOnChooseClickListener(new ScreenDialog.OnChooseClickListener() {
////                    @Override
////                    public void onChooseClickListener(int position, boolean isSected, String value, int pos) {
////                        screenView.setChange(position,isSected,value,pos);
////                    }
////                });
//                break;
//            case R.id.ll_synthetical:
//
//                initPopupWindow();
//                PopupWindow win=window.getPopupWindow();
//                win.setAnimationStyle(R.style.animAlpha);
//                datas.clear();
//                datas.add("综合");
//                datas.add("新品上架");
//                datas.add("价格最低");
//                datas.add("价格最高");
//                window.showAsDropDown(ll_filter,  0, 0);
////                window.showAtLocation(ll_synthetical, Gravity.TOP, 30, 30);
//                WindowManager.LayoutParams lp=getActivity().getWindow().getAttributes();
////                lp.alpha=0.3f;
//                lp.alpha=1f;
//                getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//                getActivity().getWindow().setAttributes(lp);
//                break;
//            case R.id.ll_vote:
//                initPopupWindow();
//                PopupWindow wins=window.getPopupWindow();
//                wins.setAnimationStyle(R.style.animAlpha);
//                datas.clear();
//
//                datas.add("从高到低");
//                datas.add("从低到高");
//                window.showAsDropDown(ll_filter,  0, 0);
////                window.showAtLocation(ll_synthetical, Gravity.TOP, 30, 30);
//                WindowManager.LayoutParams lps=getActivity().getWindow().getAttributes();
////                lp.alpha=0.3f;
//                lps.alpha=1f;
//                getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//                getActivity().getWindow().setAttributes(lps);
//                break;
//            case R.id.ll_rocord:
//                break;
//        }
//    }
    private int itemid=1;
    private int categoryuid=6;
    private int intseason=3;
    private int pricestar=0;
    private int priceend=100000;
    public void requsSave(String style, String season, String start,String end){
        if(!TextUtils.isEmpty(style)){
            for (int i = 0; i<DataClass.data.size(); i++){
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
//        ToastUtil.show(categoryuid+"1111"+intseason+"2222"+priceend+"333"+pricestar);
        getDialogData( categoryuid, intseason, priceend, pricestar, 1,itemid);
    }




    public void getDialogData( int categoryuid, int season, int endPrice, int startPrice, int sort, int gener){
        ParamMap.Build b = new ParamMap.Build();
        if(endPrice!=1000000){
            b .addParams("gender",gener)
                    .addParams("season",season)
                    .addParams("startPrice",startPrice)
                    .addParams("endPrice",endPrice)
                    .addParams("sort",sort);
        }

        RestClient.getNovate(getActivity()).post(Constant.API.CHOOSE_DETAIL,
                b.addParams("categoryChildren",categoryuid)
                .build(), new DmeycBaseSubscriber<RecommendBean>() {
            @Override
            public void onSuccess(RecommendBean bean) {
//                dialog.dismiss();
                RecommendBean sss=bean;
                if(bean.getData().getGoods().size()>0){
                    sp.setData(bean.getData().getGoods(),"");
//                    setData(bean.getData().getGoods());
                    gv_item.setVisibility(View.VISIBLE);
                    tv_desnodata.setVisibility(View.GONE);
                }else {
                    gv_item.setVisibility(View.GONE);
                    tv_desnodata.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onError(Throwable e) {
                ToastUtil.show("我失败了");
            }
        });
    }

    List<GoodsBean> goodsBeans;
    public class MyGradAdapter extends BaseAdapter {

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


            GlideUtil.loadImage(getActivity(),goodsBeans.get(i).getImage(),vh.item_iv_cover_pic);
            return view;
        }
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

    public void setData(List<GoodsBean> goodsBeans){
        this.goodsBeans=goodsBeans;
        mga.notifyDataSetChanged();
    }

    private CommonPopupWindow window;
    private ListView dataList;
    private List<String> datas=new ArrayList<>();

    private List<ImageView> rb_s=new ArrayList<>();
    private DialogAdapter da;
    private void initPopupWindow() {
        // get the height of screen
        DisplayMetrics metrics=new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenHeight=metrics.heightPixels;
        // create popup window
//        window=new CommonPopupWindow(this, R.layout.popup_list, ViewGroup.LayoutParams.MATCH_PARENT, (int) (screenHeight*0.7)) {
        window=new CommonPopupWindow(getActivity(), R.layout.popup_list, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT) {
            @Override
            protected void initView() {
                View view=getContentView();
                dataList=(ListView) view.findViewById(R.id.data_list);
//                dataList.setAdapter(new ArrayAdapter<String>(this, R.layout.item_popup_list, datas));
                da =new DialogAdapter();
                dataList.setAdapter(da);
                dataList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                        Toast.makeText(getActivity(),"wozoule"+i,Toast.LENGTH_LONG).show();
                        for (int i1=0;i1< rb_s.size();i1++){
                            if(i1==i){
                                rb_s.get(i).setVisibility(View.VISIBLE);
                                rb_s.get(i).setBackground(getResources().getDrawable(R.drawable.screen_ic_right_orange));
                            }else {
                                rb_s.get(i).setVisibility(View.GONE);
                            }
                        }
                        da.notifyDataSetChanged();
                    }
                });

//                Toast.makeText(CategoryListActivity.this,"wozoule"+i,Toast.LENGTH_LONG).show();
//                for (int i1=0;i1< rb_s.size();i1++){
//                    if(i1==i){
//                        rb_s.get(i).setChecked(true);
//                    }else {
//                        rb_s.get(i).setChecked(false);
//                    }
//                }
//                da.notifyDataSetChanged();
            }

            @Override
            protected void initEvent() {
                dataList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
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
                        WindowManager.LayoutParams lp=getActivity().getWindow().getAttributes();
                        lp.alpha=1.0f;
                        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                        getActivity().getWindow().setAttributes(lp);
                    }
                });
            }
        };
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
                view=LayoutInflater.from(getActivity()).inflate(R.layout.item_popup_list,null);
//                ll_items.add(ll_imte);
                vh=new ViewHoders(view);
                view.setTag(vh);
            }else {
                vh=(ViewHoders)view.getTag();
            }
            rb_s.add(vh.rb_by);
            vh.item_text.setText(datas.get(i));

            return view;
        }
    }
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
    public void getDetail( int id,  final BandDetailPresenter.OnDetailLisenter lisener){

        RestClient.getNovate(getActivity()).post(Constant.API.STORE_DETIAL, new ParamMap.Build()
                .addParams("store", id)
                .build(), new DmeycBaseSubscriber<RecommendBean>() {
            @Override
            public void onSuccess(RecommendBean bean) {
                if(bean.getData().getGoods().size()>0){
                    sp.setData(bean.getData().getGoods(),logo);
//                    sp.setLog(logo);
                    gv_item.setVisibility(View.VISIBLE);
                    tv_desnodata.setVisibility(View.GONE);

//                    setData(bean.getData().getGoods());
//                    setData(bean.getData().getGoods());
                }else {
                    gv_item.setVisibility(View.GONE);
                    tv_desnodata.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onError(Throwable e) {
                gv_item.setVisibility(View.GONE);
                tv_desnodata.setVisibility(View.VISIBLE);
            }
        });
    }
    private String logo;
    public void setLog(String log){
        logo=log;
    }
}
