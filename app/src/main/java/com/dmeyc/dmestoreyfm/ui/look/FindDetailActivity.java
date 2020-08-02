package com.dmeyc.dmestoreyfm.ui.look;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.FindListBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.dialog.ShareDialog;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.ui.look.section.FindDetailAdapter;
import com.dmeyc.dmestoreyfm.ui.look.section.FindDetailSection;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.wedgit.sectioned.SectionedRecyclerViewAdapter;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FindDetailActivity extends BaseActivity {

    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.main_recyclerView)
    RecyclerView contentRecycler;
    @Bind(R.id.tv_introduction_brandName)
    TextView tv_introduction_brandName;
    @Bind(R.id.iv_introduction_avatar)
    RoundedImageView iv_introduction_avatar;
    @Bind(R.id.text_context)
    TextView text_context;
    @Bind(R.id.tv_introduction_follow)
    TextView tv_introduction_follow;

    private ArrayList<FindListBean.DataBean> list = new ArrayList<>();
    private ArrayList<String> imgList = new ArrayList<>();
    private SectionedRecyclerViewAdapter sectionedRecyclerViewAdapter;
    private FindDetailSection findDetailSection;
    private boolean isFirst = true;//第一次加载数据
    private boolean isShowProduct = false;
    private int detailId;
    private int flag_position;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_find_detail;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        detailId = getIntent().getIntExtra("detailId", 0);
        flag_position = getIntent().getIntExtra("flag_position", 0);
        String useId = SPUtils.getStringData(Constant.Config.USER_ID, "67");//未修改
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", detailId);
        map.put("member", useId);
//        map.put("is", ed_save.getText().toString());
        RestClient.getNovate(this).get(Constant.API.DISCOVER_LIST, map, new DmeycBaseSubscriber<FindListBean>() {
            @Override
            public void onSuccess(FindListBean bean) {
                list.clear();
                list.addAll(bean.getData());
                imgList.clear();
                for (int i = 0; i < list.get(flag_position).getImageList().size(); i++) {
                    imgList.add(list.get(flag_position).getImageList().get(i).getSource());
                }
                viewpager.setAdapter(new FindDetailAdapter(imgList, FindDetailActivity.this));
                initView();
            }
        });

    }

    private void initView() {
        if (list.get(flag_position).isIsLike()) {
            tv_introduction_follow.setText("已关注");
        } else {
            tv_introduction_follow.setText("+关注");
        }
        tv_introduction_brandName.setText(list.get(flag_position).getName());
        GlideUtil.loadImage(FindDetailActivity.this, list.get(flag_position).getAvatar(), iv_introduction_avatar);
        text_context.setText(list.get(flag_position).getIntroduction());

    }

    private void initRecyclerView() {
        LinearLayoutManager myLayoutManager = new LinearLayoutManager(this);
        myLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        contentRecycler.setLayoutManager(myLayoutManager);
        sectionedRecyclerViewAdapter = new SectionedRecyclerViewAdapter();
        contentRecycler.setAdapter(sectionedRecyclerViewAdapter);
        initSection();
    }

    private void initSection() {
        if (isFirst) {
            findDetailSection = new FindDetailSection(FindDetailActivity.this, sectionedRecyclerViewAdapter);
//            findDetailSection.setData(list);
        } else {
            sectionedRecyclerViewAdapter.removeAllSections();
//            findDetailSection.setData(list);
        }
        sectionedRecyclerViewAdapter.addSection(findDetailSection);
        isFirst = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ll_like, R.id.ll_comment, R.id.ll_canBuy,R.id.text_finish, R.id.img_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_like:
                break;
            case R.id.ll_comment:
                break;
            case R.id.ll_canBuy:
                HashMap<String, Object> map = new HashMap<>();
                map.put("ids",list.get(flag_position).getGoods());
                RestClient.getNovate(FindDetailActivity.this).get(Constant.API.DISCOVER_LIST_GOODS, map, new DmeycBaseSubscriber<Object>() {
                    @Override
                    public void onSuccess(Object bean) {
                    }
                });
//                if (list.size() == 0) {
//                    Toast.makeText(FindDetailActivity.this, "暂无商品", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                if (!isShowProduct) {
//                    contentRecycler.setVisibility(View.VISIBLE);
//                    isShowProduct = true;
//                } else {
//                    contentRecycler.setVisibility(View.GONE);
//                    isShowProduct = false;
//                }
                break;
            case R.id.text_finish:
                finish();
                break;
            case R.id.img_share:
                new ShareDialog(FindDetailActivity.this).show();
                break;
        }
    }

}
