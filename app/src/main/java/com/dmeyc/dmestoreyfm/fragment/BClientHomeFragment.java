package com.dmeyc.dmestoreyfm.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.CustomLazyViewPagerAdapter;
import com.dmeyc.dmestoreyfm.base.BaseFragment;
import com.dmeyc.dmestoreyfm.bean.IsTrueNameBean;
import com.dmeyc.dmestoreyfm.bean.ProjectTypeBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.ui.AllActionFragment;
import com.dmeyc.dmestoreyfm.ui.BClientPublishActionActivity;
import com.dmeyc.dmestoreyfm.ui.BClientPublishChalegeActivity;
import com.dmeyc.dmestoreyfm.ui.CommInActivity;
import com.dmeyc.dmestoreyfm.ui.MySelfCertifyActivity;
import com.dmeyc.dmestoreyfm.ui.SubmitOrderActivity;
import com.dmeyc.dmestoreyfm.ui.TrueNameActivity;
import com.dmeyc.dmestoreyfm.ui.YFMSettingActivity;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.PopupMenu;
import com.dmeyc.dmestoreyfm.wedgit.PopupPublicMenu;
import com.tamic.novate.Throwable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

public class BClientHomeFragment extends BaseFragment {

    protected List<String> mTitleLists; //导航条文字List
    protected List<BaseFragment> mFragmentLists;    //导航fragment list
    private ViewPager viewpageraction;
    private TabLayout tablayoutaction;
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_bclienthome;
    }

    @Override
    protected void initData() {
    }
    @Override
    protected void initData(View view) {
        tablayoutaction = (TabLayout) mRootView.findViewById(R.id.tablayoutaction); //使用bind 会出现空指针
        tablayoutaction.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(tablayoutaction,0,0);
            }
        });
        viewpageraction = (ViewPager) mRootView.findViewById(R.id.viewpageraction); //使用bind 会出现空指针
        mTitleLists = new ArrayList<>();
        mFragmentLists = new ArrayList<>();
        getTitleList(mTitleLists);
        getFragmentLists(mFragmentLists);

        FragmentManager fm =getActivity().getSupportFragmentManager();
        viewpageraction.setAdapter(new CustomLazyViewPagerAdapter(fm,mFragmentLists,mTitleLists));
        tablayoutaction.setTabMode(TabLayout.MODE_SCROLLABLE);
        tablayoutaction.setupWithViewPager(viewpageraction);
        viewpageraction.setCurrentItem(1);
    }
    protected void getTitleList(List<String> mTitleLists) {
        mTitleLists.add("全部");
//        mTitleLists.add("校园");
        mTitleLists.add("未开始");
        mTitleLists.add("进行中");
        mTitleLists.add("已结束");
    }
    BClientActionListFragment atf;
    protected void getFragmentLists(List<BaseFragment> mFragmentLists) {
        for (int i=0;i<mTitleLists.size();i++){
            atf=  new BClientActionListFragment(i);
            mFragmentLists.add(atf);
           }
    }
    public void setIndicator (TabLayout tabs,int leftDip,int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }
    int poscode;
    private PopupPublicMenu popupMenu;
    ArrayList <String> ar=new ArrayList<>();
    @OnClick(R.id.iv_right_title_bar)
    public void onClick(View view){
        int viewid=view.getId();
        if(viewid==R.id.iv_right_title_bar){
//            List<ProjectTypeBean.DataBean> lbean= pty.getData();
//            for (int i=0;i<lbean.size();i++){
//                ar.add(lbean.get(i).getItemName());
//            }
            ar.clear();
            ar.add("发布活动");
            ar.add("发布挑战");
//                String[] abs = new String[]{"关于我们", "检查更新", "意见反馈"};
            popupMenu = new PopupPublicMenu(getActivity(),ar);
            popupMenu.showLocation(R.id.iv_right_title_bar);// 设置弹出菜单弹出的位置
            // 设置回调监听，获取点击事件
            popupMenu.setOnItemClickListener(new PopupPublicMenu.OnItemClickListener() {
                @Override
                public void onClick(PopupMenu.MENUITEM item, String str) {
                    popupMenu.dismiss();
                }
                @Override
                public void onClick(String str,int pos) {
                    poscode=pos;
                    popupMenu.dismiss();
                    checkIsTrueName(pos);
                }
            });
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==555){
            atf.notifydata();
        }
    }
    private boolean istruename=false;
    public void checkIsTrueName(final int poss){

        RestClient.getYfmNovate(getActivity()).post(Constant.API.YFM_CHECKTRUENAME, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .build(), new DmeycBaseSubscriber<IsTrueNameBean>() {
            @Override
            public void onSuccess(IsTrueNameBean bean) {
//                finish();
                istruename=bean.isData();
                if(istruename){
                    if(0==poss){
                        Intent bClientPublishActionActivity=  new Intent(getActivity(),BClientPublishActionActivity.class);
                        bClientPublishActionActivity .putExtra("type","0");
                        getActivity().startActivityForResult(bClientPublishActionActivity,123);
                    }else {
                        getActivity().startActivity(new Intent(getActivity(),BClientPublishChalegeActivity.class));
                    }
                }else {

                    goShop();
                }
            }
            @Override
            public void onError(Throwable e) {

            }
        });

    }
    Dialog dialog;
    ListView lv_shop;
    TextView alltv_price;
    TextView tv_toinfo,tv_notcont;
    public void goShop(){
        dialog  = new Dialog(getActivity(), R.style.MyDialog);
        //设置它的ContentView
        getActivity().setFinishOnTouchOutside(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_toinstrument);
        Window window = dialog.getWindow();

        WindowManager.LayoutParams lp = window.getAttributes();
        window.setGravity(Gravity.CENTER);
        WindowManager m = getActivity().getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高
        WindowManager.LayoutParams params = window.getAttributes();
        params.height = (int) (d.getHeight() * 0.3); // 高度设置为屏幕的0.5
        params.width = (int) (d.getWidth() * 0.8); // 宽度设置为屏幕的0.6
        window.setAttributes(params);
        tv_toinfo=dialog.findViewById(R.id.tv_toinfo);
        tv_notcont=dialog.findViewById(R.id.tv_notcont);
        tv_notcont.setText("抱歉，您还未进行实名认证！\n请认证后发布活动呦");
        tv_toinfo.setText("完善个人信息");
//        lv_shop = dialog.findViewById(R.id.lv_shop);
//        alltv_price=dialog.findViewById(R.id.alltv_price);
        dialog.show();
        tv_toinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                startActivity(new Intent(getActivity(),MySelfCertifyActivity.class));
//                startActivity(new Intent(getActivity(),TrueNameActivity.class));
            }
        });
//        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
//            @Override
//            public void onDismiss(DialogInterface dialogInterface) {
//
//
//            }
//        });

    }
}
