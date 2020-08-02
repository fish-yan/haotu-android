package com.dmeyc.dmestoreyfm.fragment.shop;

import android.annotation.SuppressLint;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.ShoppingAdapter;
import com.dmeyc.dmestoreyfm.base.BaseRefreshFragment;
import com.dmeyc.dmestoreyfm.bean.ChooseClothBean;
import com.dmeyc.dmestoreyfm.bean.common.GoodsBean;
import com.dmeyc.dmestoreyfm.bean.common.ProductCategoryBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.dialog.ScreenDialog;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.utils.DataClass;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.GsonTools;
import com.dmeyc.dmestoreyfm.wedgit.ScreenView;
import com.dmeyc.dmestoreyfm.wedgit.flowview.AutoFlowLayout;
import com.makeramen.roundedimageview.RoundedImageView;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by jockie on 2017/11/8
 * Email:jockie911@gmail.com
 */

@SuppressLint("ValidFragment")
public class ShoppingItemFragment extends BaseRefreshFragment<ChooseClothBean,ShoppingAdapter>{

//    @Bind(R.id.iv_roundmage)
//RoundAngleImageView iv_roundmage;

    @Bind(R.id.lv_catory)
    ListView lv_catory;

    @Bind(R.id.rel_head)
    RelativeLayout relHead;

    @Bind(R.id.selected_flowlayout)
    AutoFlowLayout selectedFlowlayout;




    private ScreenView screenView;
    private int gender = Constant.Config.GENDER_MALE;
    private int itemid=-1;
private String arg[]={"男装","女装","童装"};
private int res []={R.drawable.men_img,R.drawable.women_img,R.drawable.children_img};

   public ShoppingItemFragment(){}
    public ShoppingItemFragment(int gender) {
        this.gender = gender;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fm_shopping_item;
    }

    @Override
    protected void initData() {
        super.initData();
        final MyListAdapter mla=new MyListAdapter();
        lv_catory.setAdapter(mla);
        lv_catory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mla.clickItem(position);
            }
        });

//        mRecycleView.set

        mRecycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//                View childAt = recyclerView.getChildAt(1);
//                if(childAt != null){
//                    relHead.setVisibility(childAt.getTop() - DensityUtil.dip2px(20) - 1 < 0 ? View.VISIBLE : View.GONE); // -1 是因为view会被复用
//                    ((ShoppingFragment)getParentFragment()).setShadowViewVisbiablty(childAt.getTop() - DensityUtil.dip2px(20) - 1 >= 0);
//                }
            }
        });
    }

    @Override
    protected void initData(View view) {
//        iv_roundmage=(RoundAngleImageView) view.findViewById(R.id.iv_roundmage);

//        mRecycleView.addItemDecoration(new SpaceItemDecoration(true));
    }
    List<GoodsBean> listdata;
    ShoppingAdapter sha=null;
    @Override
    protected ShoppingAdapter getAdapter() {

        return new ShoppingAdapter(getActivity(), R.layout.item_lv_gv_item_single,new ArrayList<ChooseClothBean.CategoryDataBean>(),true,itemid+1);
    }
    ChooseClothBean chooseClothBean;
    @Override
    protected ChooseClothBean parseData(String string) {
        chooseClothBean = GsonTools.changeGsonToBean(string, ChooseClothBean.class);
//        GlideUtil.loadImage(getActivity(),chooseClothBean.getData().getImages(),iv_roundmage);
        return chooseClothBean;
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
    }
    @Override
    protected boolean isWithHead() {
        return true;
    }
    private void setHeadView(HeaderAndFooterWrapper headerAndFooterWrapper, ChooseClothBean.DataBean data) {
        if (headerAndFooterWrapper.getHeadersCount() > 0)
            return;
        View headview = View.inflate(getContext(), R.layout.item_rv_shopping_headview, null);
        headerAndFooterWrapper.addHeaderView(headview);
        RoundedImageView headGridView = (RoundedImageView) headview.findViewById(R.id.iv_roundmage);
        headGridView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        GlideUtil.loadImage(getActivity(),
                data.getImages()
                ,headGridView);
//        Glide.with(getActivity()).load(data.getImages()).into(headGridView).;
//        GlideUtil.loadImage(getContext(), data.getImages(), headGridView);
    }

    @OnClick({R.id.screening})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.screening:
                ScreenDialog dialog = new ScreenDialog(getActivity(), R.style.dialog_style_right,screenView.getmData());
                dialog.show();
                dialog.setSelectedData(screenView.selectMap);
                dialog.setOnChooseClickListener(new ScreenDialog.OnChooseClickListener() {
                    @Override
                    public void onChooseClickListener(int position, boolean isSected, String value, int pos) {
                        screenView.setChange(position,isSected,value,pos);
                    }
                });
                break;
              }
            }

    List<ProductCategoryBean> data=new ArrayList<>();
    ProductCategoryBean pcb=null;
    @Override
    protected void setData(ChooseClothBean bean, boolean isRefresh) {
        setHeadView(wrapper,bean.getData());
        adapter.addData(bean.getData().getProductCategoryParent(),isRefresh);
        adapter.count(bean.getData().getProductCategoryParent(),itemid+1);
        if(DataClass.data!=null){
                    DataClass.data.clear();
        }
        for (int i=0;i<bean.getData().getProductCategoryParent().size();i++){
             for (int ii=0;ii<bean.getData().getProductCategoryParent().get(i).getProductCategoryChildren().size();ii++){
                 pcb=new ProductCategoryBean();
                 pcb.setCategoryName(bean.getData().getProductCategoryParent().get(i).getProductCategoryChildren().get(ii).getCategoryName());
                 pcb.setId(bean.getData().getProductCategoryParent().get(i).getProductCategoryChildren().get(ii).getId());
                 data.add(pcb);
             }
        }
        DataClass.data=data;
        wrapper.notifyDataSetChanged();
    }

    @Override
    protected String getUrl() {
//        return Constant.API.CHOOSE_CHOSE;
        return Constant.API.CHOOSE_CATEGORY;
    }

    @Override
    protected Map<String, Object> getParamMap() {
        Map<String, Object> map = new ParamMap.Build().addParams(Constant.Config.GENDER, gender).build();
        if (screenView != null){
            map = screenView.setSeasonParam(screenView.setPriceParam(screenView.setCategoryParam(map)));
        }
        return map;
    }
    public class MyListAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return arg.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHoder vh=null;
            if(convertView==null){
                convertView= LayoutInflater.from(getActivity()).inflate(R.layout.adapter_categorylist, null);
                convertView.findFocus();
                vh=new ViewHoder(convertView);
                convertView.setTag(vh);
            }else {
                vh=(ViewHoder) convertView.getTag();
            }
            vh.tv_itemtext.setText(arg[position]);
            if(-1==itemid){
                if(0==position){
                    vh.iv_gener.setBackgroundResource(R.drawable.men_img);
                    vh.tv_lefline.setVisibility(View.VISIBLE);
                    vh.tv_itemtext.setTextColor(getResources().getColor(R.color.indicator_selected_color));
                    vh.ll_item.setBackgroundColor(getResources().getColor(R.color.white));
                }else if(1==position){
                    vh.iv_gener.setBackgroundResource(R.drawable.women_img);
                    vh.tv_lefline.setVisibility(View.GONE);
                }else {
                    vh.ll_item.setBackgroundColor(getResources().getColor(R.color.divide_line));
                    vh.tv_itemtext.setTextColor(getResources().getColor(R.color.black));
                    vh.iv_gener.setBackgroundResource(res[position]);
                    vh.tv_lefline.setVisibility(View.GONE);
                }
            }else {
                if(itemid==position){
                    vh.tv_lefline.setVisibility(View.VISIBLE);
                    vh.tv_itemtext.setTextColor(getResources().getColor(R.color.indicator_selected_color));
                    vh.ll_item.setBackgroundColor(getResources().getColor(R.color.white));
                }else {
                    vh.ll_item.setBackgroundColor(getResources().getColor(R.color.divide_line));
                    vh.tv_itemtext.setTextColor(getResources().getColor(R.color.black));
                    vh.iv_gener.setBackgroundResource(res[position]);
                    vh.tv_lefline.setVisibility(View.GONE);
                }
            }

            return convertView;
        }



        public void clickItem(int pos){
            itemid=pos;
            if (pos == 0) {
                gender = Constant.Config.GENDER_MALE;
            } else if (pos == 1) {
                gender = Constant.Config.GENDER_FEMALE;
            } else {
                gender = Constant.Config.GENDER_CHILD;
            }
            requestData();
            notifyDataSetChanged();
        }
    }


    public class ViewHoder{
        private TextView tv_lefline,tv_itemtext;
        private ImageView iv_gener;
        private LinearLayout ll_item;
        public ViewHoder(View view){
            tv_lefline=view.findViewById(R.id.tv_lefline);
            ll_item=view.findViewById(R.id.ll_item);
            tv_itemtext=view.findViewById(R.id.tv_itemtext);
            iv_gener=view.findViewById(R.id.iv_gener);
        }
    }
}
