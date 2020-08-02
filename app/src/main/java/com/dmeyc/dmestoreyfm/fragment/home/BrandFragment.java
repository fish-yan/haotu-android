package com.dmeyc.dmestoreyfm.fragment.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseFragment;
import com.dmeyc.dmestoreyfm.bean.WhiterBean;
import com.dmeyc.dmestoreyfm.bean.YFMLoginBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.dialog.YFMScreenCityDialog;
import com.dmeyc.dmestoreyfm.fragment.ActionFragment;
import com.dmeyc.dmestoreyfm.fragment.PKFragment;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.ui.MainActivity;
import com.dmeyc.dmestoreyfm.ui.MyCommActivity;
import com.dmeyc.dmestoreyfm.ui.PublishActivity;

import com.dmeyc.dmestoreyfm.ui.TestWebVeiw;
import com.dmeyc.dmestoreyfm.ui.YFMLoginActivity;
import com.dmeyc.dmestoreyfm.ui.chat.ConversationListActivity;
import com.dmeyc.dmestoreyfm.utils.DensityUtil;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.utils.Util;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;
import com.tamic.novate.Throwable;

import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;

/**
 * Created by jockie on 2017/11/6
 * Email:jockie911@gmail.com
 */
public class BrandFragment extends HomeFragment implements View.OnClickListener {
    @Bind(R.id.view_divide_shadow)
    View shadowDivideView;
    @Bind(R.id.tv_catory)
    TextView tv_catory;
    @Bind(R.id.tv_city)
    TextView tv_city;
    @Bind(R.id.tablayout)
    TabLayout tablayout;
    @Bind(R.id.iv_shopcar)
    ImageView iv_shopcar;
    @Bind(R.id.et_search_title)
    EditText et_search_title;
    @Bind(R.id.iv_header)
    CircleImageView iv_header;
    @Bind(R.id.tv_wheather)
    TextView  tv_wheather;

    @Override
    protected int getLayoutRes() {
        return R.layout.fm_brand;
    }

    @Override
    protected void getTitleList(List<String> mTitleLists) {
        mTitleLists.add("");
//        mTitleLists.add("PK场");
        isShowTitle(tv_catory);
    }
    protected void isShowTitle(TextView view) {
//        mViewPager.setsca(false);
//        mViewPagena
        view.setVisibility(View.GONE);
    }
    ActionFragment actionFragment;
    PKFragment pkFragment;
    @Override
    protected void getFragmentLists(List<BaseFragment> mFragmentLists) {
        actionFragment=  new ActionFragment(tv_city);
//        pkFragment= new PKFragment();
        mFragmentLists.add(actionFragment);
//        mFragmentLists.add(pkFragment);
    }
    @Override
    protected void reSetTabBlockWidth() {
        Util.reflex(mTabLayout, DensityUtil.dip2px(20));
    }
    public void setShadowViewVisbiablty(boolean visbiablty) {
        shadowDivideView.setVisibility(visbiablty ? View.VISIBLE : View.GONE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        tv_city.setText(SPUtils.getStringData(Constant.Config.CURRENT_CITY));
        tv_city.setOnClickListener(this);
        iv_shopcar.setOnClickListener(this);
//        et_search_title.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean b) {
//                if (b) {
//                    et_search_title.setCursorVisible();
//                    android:cursorVisible="false"
//                } else {
//
//                }
//            }
//        });
        et_search_title.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

                if(i == EditorInfo.IME_ACTION_SEARCH)
                {
                   /**隐藏软键盘**/
                    View view = getActivity().getWindow().peekDecorView();
                    if (view != null) {
                        InputMethodManager inputmanger = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                   if(TextUtils.isEmpty(et_search_title.getText().toString().trim())){
                       ToastUtil.show("请输入搜索词");

                   }else {
                       SPUtils.setValue(Constant.Config.HASEKEYWORD,"1");
                       actionFragment.refreshSearch(et_search_title.getText().toString().trim());
                   }
//                    Toast.makeText(getActivity(),"呵呵",Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });

        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                View view = tab.getCustomView();
                if (null == view) {
                    tab.setCustomView(R.layout.custom_tab_layout_text);
                }
                updateTabTextView(tab, true);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                if (null == view) {
                    tab.setCustomView(R.layout.custom_tab_layout_text);
                }
                updateTabTextView(tab, false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        getWither();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


//    @OnClick(R.id.iv_shopcar)
//    public void onClick() {
//        rightIconClick();
//    }
   YFMScreenCityDialog dialog;

    @Override
    public void onClick(View view) {
        super.onClick(view);
        int viewid=view.getId();
        if(R.id.tv_city==viewid){
            dialog  = new YFMScreenCityDialog(getActivity(), R.style.dialog_style_right);
            dialog.onSave(new YFMScreenCityDialog.SaveClickLisenter() {

                @Override
                public void selectCity(String city) {
                    tv_city.setText(city);
                    dialog.dismiss();
                    SPUtils.savaStringData(Constant.Config.CURRENT_CITY,city);
                    if(0==tablayout.getSelectedTabPosition()){
                        actionFragment.refreshCity();
                    }else {
                        pkFragment.refreshCity();
                    }
               getWither();
                }
            });
            dialog.show();
        }else if(R.id.iv_shopcar==viewid){
//startActivity(new Intent(getActivity(),TestWebVeiw.class));
            rightIconClick();
        }
    }
    public void rightIconClick(){

        HashMap<String, Boolean> hashMap = new HashMap<>();
        //会话类型 以及是否聚合显示
//        hashMap.put(Conversation.ConversationType.PRIVATE.getName(),false);
//        hashMap.put(Conversation.ConversationType.PUSH_SERVICE.getName(),true);
//        hashMap.put(Conversation.ConversationType.SYSTEM.getName(),true);
        hashMap.put(Conversation.ConversationType.GROUP.getName(),true);
        RongIM.getInstance().startConversationList(getActivity(),hashMap);

//        RongIM.getInstance().startConversationList(getActivity());
//        RongIM.getInstance().startSubConversationList(getActivity(),Conversation.ConversationType.GROUP);
//                          RongIM.getInstance().startGroupChat(getActivity(), "5", "标题");
//            startActivity(new Intent(getActivity(), ConversationListActivity.class));
//        startActivity(new Intent(getActivity(),MyCommActivity.class));
    }

    @SuppressLint("NewApi")
    private void updateTabTextView(TabLayout.Tab tab, boolean isSelect) {
        if (isSelect) {
            //选中加粗
            TextView textView = tab.getCustomView().findViewById(android.R.id.text1);
//            TextView tabSelect = (TextView) tab.getCustomView().findViewById(R.id.tab_item_textview);
//            textView.setCompoundDrawables(null,null,getActivity().getDrawable(R.drawable.gengduo),null);
//            textView.setCompoundDrawablesWithIntrinsicBounds(null,null,getActivity().getDrawable(R.drawable.gengduo),null);
            textView.setTextColor(getActivity().getResources().getColor(R.color.white));
            textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            textView.setText(tab.getText());
        } else {
            TextView tabUnSelect = tab.getCustomView().findViewById(android.R.id.text1);
            tabUnSelect.setTextColor(getActivity().getResources().getColor(R.color.white));
            tabUnSelect.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            tabUnSelect.setText(tab.getText());
        }
    }


    public void getWither(){
        ParamMap.Build pb=   new ParamMap.Build();
        if(TextUtils.isEmpty(SPUtils.getStringData(Constant.Config.CURRENT_CITY))){
            pb.addParams("city", "上海");
        }else {
            pb.addParams("city", SPUtils.getStringData(Constant.Config.CURRENT_CITY));
        }

        RestClient.getYfmNovate(getActivity()).get(Constant.API.YFM_GETWHITER,pb
                 .addParams("user_token",SPUtils.getStringData(Constant.Config.TOKEN) )
                .build(), new DmeycBaseSubscriber<WhiterBean>(getActivity()) {
            @Override
            public void onSuccess(final WhiterBean bean) {
                GlideUtil.loadImage(getActivity(),bean.getData().getUserLogo(),iv_header);
                tv_wheather.setText(bean.getData().getWeather()+" "+bean.getData().getTemperature()+"°");
//              ToastUtil.show(bean.getMsg());
            }
            @Override
            public void onError(Throwable e) {
                super.onError(e);
                ToastUtil.show(e.getMessage());
            }
        });

    }
}