package com.dmeyc.dmestoreyfm.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseFragment;
import com.dmeyc.dmestoreyfm.bean.RecommendBean;
import com.dmeyc.dmestoreyfm.bean.common.GoodsBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.dialog.ShareDialog;
import com.dmeyc.dmestoreyfm.fragment.section.MineGoodsListSection;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.ui.AttentionActivity;
import com.dmeyc.dmestoreyfm.ui.CouponsActivity;
import com.dmeyc.dmestoreyfm.ui.ManageAddressActivity;
import com.dmeyc.dmestoreyfm.ui.PresonInfoActivity;
import com.dmeyc.dmestoreyfm.ui.SettingActivity;
import com.dmeyc.dmestoreyfm.ui.TotalMessageActivity;
import com.dmeyc.dmestoreyfm.ui.order.OrderBackActivity;
import com.dmeyc.dmestoreyfm.ui.order.WholeOrderStatusActivity;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;
import com.dmeyc.dmestoreyfm.wedgit.sectioned.SectionedRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;

/**
 * Created by jockie on 2017/8/31
 * Email:jockie911@gmail.com
 */

public class MineFragment extends BaseFragment {

    //    @Bind(R.id.num_tv_cloth)
//    NumberTextView numberTextView;
//    @Bind(R.id.num_tv_record)
//    NumberTextView recordTextView;
    @Bind(R.id.tv_login)
    TextView tvLogin;
    @Bind(R.id.civ_avatar)
    CircleImageView civAvatar;
    @Bind(R.id.main_recyclerView)
    RecyclerView contentRecycler;

    private SectionedRecyclerViewAdapter sectionedRecyclerViewAdapter;
    private ArrayList<GoodsBean> goodsList = new ArrayList<>();
    private MineGoodsListSection mineGoodsListSection;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initData() {
        String mobile = SPUtils.getStringData(Constant.Config.MOBILE, "");
//        if(Util.isLogin()){
////            RestClient.getNovate(getActivity(), Constant.API.TBASE_URL).get(Constant.API.TAILOR_RECORD, new ParamMap.Build()
////                    .addParams(Constant.Config.MOBILE, mobile)
////                    .addParams("string", Util.MD5(mobile + Constant.Config.MD5_KEY + mobile)).build(), new DmeycBaseSubscriber<RecordBean>(getActivity()) {
////
////                @Override
////                public void onSuccess(RecordBean bean) {
////                    recordTextView.notifyCount(bean.getData() == null ? 0 : bean.getData().size());
////                }
////            });
//            RestClient.getNovate(getActivity()).get(Constant.API.SHOW_COUPON, new ParamMap.Build().build(), new DmeycBaseSubscriber<CouponBean>(getActivity()) {
//                @Override
//                public void onSuccess(CouponBean bean) {
////                    numberTextView.notifyCount(bean.getData().size());
//                }
//            });
//        }
        RestClient.getNovate(getActivity()).get(Constant.API.RECOMMEND, new ParamMap.Build().build(), new DmeycBaseSubscriber<RecommendBean>(getActivity()) {
            @Override
            public void onSuccess(RecommendBean bean) {
                initRecyclerView(bean.getData().getGoods());
            }
        });
    }

    private void initRecyclerView(List<GoodsBean> goods) {
        GridLayoutManager myLayoutManager = new GridLayoutManager(getActivity(), 2);
        contentRecycler.setLayoutManager(myLayoutManager);
        contentRecycler.setNestedScrollingEnabled(false);//禁止滑动
        sectionedRecyclerViewAdapter = new SectionedRecyclerViewAdapter();
        contentRecycler.setAdapter(sectionedRecyclerViewAdapter);
        initSection(goods);
    }

    private void initSection(List<GoodsBean> goods) {
        goodsList.clear();
        goodsList.addAll(goods);
        mineGoodsListSection = new MineGoodsListSection(getActivity(), sectionedRecyclerViewAdapter);
        mineGoodsListSection.setData(goodsList);
        sectionedRecyclerViewAdapter.addSection(mineGoodsListSection);
    }

    @Override
    protected void initData(View view) {

    }

//    private void submit() {
//        List<EvaliateImagesBean.DataBean> data = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            EvaliateImagesBean.DataBean dataBean = new EvaliateImagesBean.DataBean();
//            dataBean.setSource("111111111111111");
//            dataBean.setThumbnail("222222222222");
//            data.add(dataBean);
//        }
//        Gson gson = new Gson();
//        String reviewImage = gson.toJson(data);
//
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("member", SPUtils.getStringData(Constant.Config.USER_ID, ""));
//        map.put("reviewImageA", reviewImage);
//        RestClient.getNovate(getActivity()).get(Constant.API.REVIEW_ORDER, map, new DmeycBaseSubscriber<Object>() {
//            @Override
//            public void onSuccess(Object bean) {
//
//            }
//        });
//    }

    @OnClick({R.id.rl_like, R.id.tv_all_order, R.id.rel_pay_for, R.id.rel_wait_send, R.id.rel_wait_receive, R.id.rel_wait_evaluate, R.id.rel_after_sale
            , R.id.tv_setting, R.id.iv_msg, R.id.civ_avatar, R.id.kefu, R.id.tv_address_manager, R.id.rel_conpons, R.id.tv_attention, R.id.rel_record, R.id.tv_invate})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_like:
//                submit();
                break;
            case R.id.tv_all_order:
                startActivity(0);
                break;
            case R.id.rel_pay_for:
                startActivity(1);
                break;
            case R.id.rel_wait_send:
                startActivity(2);

                break;
            case R.id.rel_wait_receive:
                startActivity(3);
                break;
            case R.id.rel_wait_evaluate:
                startActivity(4);
                break;
            case R.id.rel_after_sale:
                startActivity(new Intent(getActivity(), OrderBackActivity.class));
                break;
            case R.id.tv_setting:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.iv_msg:
                startActivity(new Intent(getActivity(), TotalMessageActivity.class));
                break;
            case R.id.civ_avatar:
//                RongIM.getInstance().startConversation(getActivity(), Conversation.ConversationType.PRIVATE,"1","helo");
                startActivity(new Intent(getActivity(), PresonInfoActivity.class));
                break;
            case R.id.kefu:
                //首先需要构造使用客服者的用户信息
                RongIM.getInstance().startConversation(getContext(), Conversation.ConversationType.CUSTOMER_SERVICE, "KEFU150846864452015", "平台客服");
                break;
            case R.id.tv_address_manager:
                startActivity(new Intent(getActivity(), ManageAddressActivity.class));
                break;
            case R.id.rel_conpons:
                startActivity(new Intent(getActivity(), CouponsActivity.class));
                break;
            case R.id.tv_attention:
                startActivity(new Intent(getActivity(), AttentionActivity.class));
                break;
            case R.id.rel_record:
//                startActivity(new Intent(getActivity(),TailorRecordActivity.class));
                break;
            case R.id.tv_invate:
                new ShareDialog(getActivity()).show();
                break;
        }
    }

    private void startActivity(int position) {
        Intent intent = new Intent(getActivity(), WholeOrderStatusActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!TextUtils.isEmpty(SPUtils.getStringData(Constant.Config.AVATAR)) && civAvatar != null) {
            GlideUtil.loadHeadImage(getActivity(), SPUtils.getStringData(Constant.Config.AVATAR), civAvatar);
        }
        tvLogin.setText(SPUtils.getStringData(Constant.Config.NICK_NAME));
    }
}
