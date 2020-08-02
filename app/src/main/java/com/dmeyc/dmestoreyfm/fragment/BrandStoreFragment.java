package com.dmeyc.dmestoreyfm.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseFragment;
import com.dmeyc.dmestoreyfm.bean.BrandStoreBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.ui.WebviewActivity;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

@SuppressLint("ValidFragment")
public class BrandStoreFragment extends BaseFragment {

//    @Bind(R.id.tv_picktext)
//    PickTextView tv_picktext;
    @Bind(R.id.gv_item)
    RecyclerView gv_item;
    BrandStoreBean bean1;
    TextAdapter textap;
         int id;
         private String intr;
        List<Object>cont=new ArrayList<>();

    public BrandStoreFragment(int id){
        this.id=id;
//        this.intr=intr;
    }


    public BrandStoreFragment(){

    }
    @Override
    protected int getLayoutRes() {
        return R.layout.frag_store;
    }

    @Override
    protected void initData() {
//        final RecommendAdapter adapter = new RecommendAdapter(getActivity());
//        listViewEx.setAdapter(adapter);
//        gv_item=mRootView.findViewById(R.id.gv_item);
        LinearLayoutManager sta = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        gv_item.setLayoutManager(sta);


        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        int mScreenWidth = localDisplayMetrics.widthPixels;
        AbsListView.LayoutParams localObject = new AbsListView.LayoutParams(mScreenWidth, (int) (9.0F * (mScreenWidth / 16.0F)));
//        listViewEx.setHeaderLayoutParams(localObject);

        RestClient.getNovate(getActivity()).get(Constant.API.STORE, new ParamMap.Build().addParams("store",id).build(), new DmeycBaseSubscriber<BrandStoreBean>(getActivity()) {
            @Override
            public void onSuccess(BrandStoreBean bean) {

               bean1 =bean;

                if(bean.getData().size()>0){
//                    tv_picktext.setExpandText(Html.fromHtml(bean.getData().get(0).getIntroduction()).toString());
//                    MyBean mb=new MyBean();
//                    mb.setName();
//                    mb.setIamge(bean.getData().get(0).getImage());
//                    cont.add(Html.fromHtml(bean.getData().get(0).getIntroduction()).toString());
                    cont.add(bean.getData().get(0).getIntroduction());
                    cont.add(bean.getData().get(0).getImage());
                    textap = new TextAdapter();
                    gv_item.setAdapter(textap);
//                    tv_picktext.setCloseText(Html.fromHtml(bean.getData().get(0).getIntroduction()).toString());
                }
//                adapter.addData(bean.getData());
            }
        });
    }
    @Override
    protected void initData(View view) {

    }
    public class TextAdapter extends RecyclerView.Adapter<TextAdapter.ViewHolder>{
        @Override
        public TextAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.rcyview_text, parent, false);
            return new TextAdapter.ViewHolder(inflate);
        }

        @Override
        public void onBindViewHolder(TextAdapter.ViewHolder holder, int position) {
            if(0==position){
                holder.introd.setVisibility(View.VISIBLE);
                holder.rimag.setVisibility(View.GONE);
//                holder.introd.setCloseText(cont.get(0));
                holder.introd.setText(intr);
                holder.tv_titl.setText("品牌介绍");
            }else {
                holder.rimag.setVisibility(View.VISIBLE);
                holder.introd.setVisibility(View.GONE);
                holder.rimag.setScaleType(ImageView.ScaleType.CENTER_CROP);
                GlideUtil.loadImage(getActivity(),
                        cont.get(1),holder.rimag);
                holder.tv_titl.setText("品牌专栏");
//
                holder.rimag.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), WebviewActivity.class);
                        intent.putExtra(Constant.Config.TITLE,bean1.getData().get(0).getTitle());
                        intent.putExtra("type","type");
                        intent.putExtra(Constant.Config.URL,bean1.getData().get(0).getIntroduction());
                        startActivity(intent);
                    }
                });
            }

        }
        @Override
        public int getItemCount() {
            if(cont.size()<2){
                return 2;
            }else {
                return cont.size();
            }
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView introd;
            private RoundedImageView rimag;
            private TextView tv_titl;
            public ViewHolder(View itemView) {
                super(itemView);
                introd=itemView.findViewById(R.id.tv_picktext);
//                recycleview1= (RecyclerView) itemView.findViewById(R.id.recycleview1);
                         rimag= (RoundedImageView) itemView.findViewById(R.id.iv_roundmage);
                tv_titl=  itemView.findViewById(R.id.tv_titl);

            }
        }

    }

//    Html.ImageGetter imgGetter = new Html.ImageGetter() {
//        public Drawable getDrawable(String source) {
////            Log.i("RG", "source---?>>>" + source);
//            Drawable drawable = null;
//            URL url;
//            try {
//                url = new URL(source);
////                Log.i("RG", "url---?>>>" + url);
//                drawable = Drawable.createFromStream(url.openStream(), ""); // 获取网路图片
//            } catch (Exception e) {
//                e.printStackTrace();
//                return null;
//            }
//            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
//                    drawable.getIntrinsicHeight());
////            Log.i("RG", "url---?>>>" + url);
//            return drawable;
//        }
//    };

//
//    Html.ImageGetter imgGetter = new Html.ImageGetter() {
//        public Drawable getDrawable(String source) {
//            final Drawable[] drawable = new Drawable[1];
////            URL url;
////            try {
////                url = new URL(source);
//////                Log.i("RG", "url---?>>>" + url);
////                drawable = Drawable.createFromStream(url.openStream(), ""); // 获取网路图片
////            } catch (Exception e) {
////                e.printStackTrace();
////                return null;
////            }
////            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
////                    drawable.getIntrinsicHeight());
//////            Log.i("RG", "url---?>>>" + url);
////            return drawable;
////            RequestOptions options = new RequestOptions()
////                    .diskCacheStrategy(DiskCacheStrategy.NONE);
//            Glide.with(getActivity()).load(source)
//                    .into(new SimpleTarget<Drawable>() {
//                        @Override
//                        public void onResourceReady(@NonNull   Drawable resource, @Nullable Transition<? super Drawable> transition) {
//
//                            drawable[0] =resource;
////                            BitmapDrawable bd = (BitmapDrawable) resource;
////                            mBitmap = bd.getBitmap();
////                            mImageView.setImageBitmap(mBitmap);
////                            mAttacher.update();
//                        }
//                    });
////            Drawable drawable = new BitmapDrawable(mBitmap);
//            return drawable[0];
//        }
//    };
//    Bitmap mBitmap;

    public void setIntr(String intro){
        this.intr=intro;
        textap.notifyDataSetChanged();

    }
}

