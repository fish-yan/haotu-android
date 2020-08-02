package com.dmeyc.dmestoreyfm.ui;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.CustomLazyViewPagerHomeAdapter;
import com.dmeyc.dmestoreyfm.adapter.MenuAdapter;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BaseTabFragment;
import com.dmeyc.dmestoreyfm.bean.MenuItemBean;
import com.dmeyc.dmestoreyfm.config.MenuConfig;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.event.MyEvent;
import com.dmeyc.dmestoreyfm.event.RefreshEvent;
import com.dmeyc.dmestoreyfm.fragment.BClientFragment;
import com.dmeyc.dmestoreyfm.fragment.MinesFragment;
import com.dmeyc.dmestoreyfm.newui.home.HomeFragment;
import com.dmeyc.dmestoreyfm.newui.pagerdetail.PagerDetailFragment;
import com.dmeyc.dmestoreyfm.present.MainPresenter;

import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;

import com.dmeyc.dmestoreyfm.utils.updateUtils.VersionUpdateUtils;
import com.dmeyc.dmestoreyfm.wedgit.BottomLayoutView;
import com.orhanobut.logger.Logger;
import com.tencent.liteav.demo.videoediter.eventbus.EventVideoBean;
import com.tencent.rtmp.TXLiveBase;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks, MenuAdapter.ICallBack {
    private MinesFragment mif;
    private BClientFragment bmif;
    @Bind(R.id.viewpage)
    ViewPager viewPager;

    @Bind(R.id.bottomView)
    BottomLayoutView mBottomLayoutView;

    private List<Fragment> fragments;
    String type = "1";
    @Bind(R.id.v4_drawerlayout)
    DrawerLayout drawerLayout;
    @Bind(R.id.lv_drawer)
    ListView lvDrawer;

    @Bind(R.id.et_test)
    EditText mEtTest;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }


    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//黑色

        mBottomLayoutView.setListener(new BottomLayoutView.OnItemCheckListener() {
            @Override
            public void onItemClick(int position) {
                viewPager.setCurrentItem(position);
            }
        });
        initDrawer();
        fragments = new ArrayList<>();
        registerReceiver(new MyBroadcastReceiver(),
                new IntentFilter("send_mess"));
//            fragments.add(new BrandFragment());

//      fragments.add(new PKFragment());
//      fragments.add(new my());

//        fragments.add(new IndexDynamicFragment());
//        fragments.add(new ConVersionFragment());

        fragments.add(new HomeFragment());
        fragments.add(new PagerDetailFragment());

//        fragments.add(new MinesFragment());
        viewPager.setAdapter(new CustomLazyViewPagerHomeAdapter(getSupportFragmentManager(), fragments));
        viewPager.setOffscreenPageLimit(3);
        if (1 == getIntent().getIntExtra("ischat", -1)) {
            viewPager.setCurrentItem(1);
        }
        if (SPUtils.getBooleanData(Constant.Config.ISLOGIN)) {
            VersionUpdateUtils.getInstance(MainActivity.this).checkyfmVersion();
        }

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                // TODO: 2019/12/18  清空消息 
//                if (1 == position) {
//                    SPUtils.savaStringData(Constant.Config.HAS_REDPOS, "0");
//                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        SetBadge(RongIM.getInstance().getTotalUnreadCount());

        String sdkver = TXLiveBase.getSDKVersionStr();
        Log.d("liteavsdk", "liteav sdk version is : " + sdkver);

        // TODO: 2019/12/24 delete
        findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SPUtils.savaStringData(Constant.Config.ROLECODE, mEtTest.getText().toString());
                EventBus.getDefault().post(new RefreshEvent.PagerDetailFragments());
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RongIM.getInstance().disconnect();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected boolean isContainTitle() {
        return false;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        EventBus.getDefault().post(new RefreshEvent.PagerDetailFragments());
        int position = intent.getIntExtra(Constant.Config.POSITION, 0);
        viewPager.setCurrentItem(position);
        mBottomLayoutView.setIsHomeVideo(true);
    }

    /**
     * @param fragmentPostion 跳转到的fragment positon
     * @param pagePosition    fragment中的positon
     */
    public void changePage(int fragmentPostion, int pagePosition) {
        viewPager.setCurrentItem(fragmentPostion);
        if (fragments.get(fragmentPostion) instanceof BaseTabFragment) {
            BaseTabFragment baseTabFragment = (BaseTabFragment) fragments.get(fragmentPostion);
            baseTabFragment.setCurrentItem(pagePosition);
        }
    }

    @Override
    protected void setStatusBarRevece() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private long exitTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                ToastUtil.show("再按一次返回桌面");
                exitTime = System.currentTimeMillis();
            } else {
                Intent setIntent = new Intent(Intent.ACTION_MAIN);
                setIntent.addCategory(Intent.CATEGORY_HOME);
                setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(setIntent);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 62225) {
            mif.onActivityResult(requestCode, resultCode, data);
        } else if (resultCode == 222) {
            mif.onActivityResult(requestCode, resultCode, data);
        }

    }


    public class MyBroadcastReceiver extends BroadcastReceiver {

        @Override

        public void onReceive(Context context, Intent intent) {
            Logger.d("MyBroadcastReceiver >>>>>>>>>>>>>>>>" );
//            Toast.makeText(context, "received in MyBroadcastReceiver",
//
//                Toast.LENGTH_SHORT).show();

            //todo 消息红点
//            if (SPUtils.getStringData(Constant.Config.HAS_REDPOS).equals("1")) {
//                tv_redpos.setVisibility(View.GONE);
//            } else {
//                tv_redpos.setVisibility(View.GONE);
//            }

        }

    }


    public void SetBadge(int num) {
        try {
            String pName = getPackageName();
            // Toast.makeText(ConversationActivity.this, pName, Toast.LENGTH_LONG).show();
            Bundle bunlde = new Bundle();
            bunlde.putString("package", "com.dmeyc.dmestoreyfm"); // 包名
            bunlde.putString("class", "com.dmeyc.dmestoreyfm.ui.YFMIndicatoActivity"); //类名
            bunlde.putInt("badgenumber", 0);
            Bundle res = this.getContentResolver().call(Uri.parse("content://com.huawei.android.launcher.settings/badge/"), "change_badge", null, bunlde);
        } catch (Exception e) {
            // Toast.makeText(ConversationActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 事件响应方法
     * 接收消息
     *
     * @param msg
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(String msg) {
        if (Constant.HotUEventKeys.TO_CHECK_PERMISSION.equals(msg)) {
            // 发布成功后刷新首页数据
            Log.e("TAG==", "11111111");
            getPermission();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void showMenu(MyEvent.ShowMenuList event) {
        showDrawerLayout();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void resetBottomBarStyle(MyEvent.MainBottomBar event) {
        mBottomLayoutView.setIsHomeVideo(event.isVideo);
    }


    private int RC_CAMERA_AND_WIFI = 100;

    private void getPermission() {
        String[] mpermissions = {
                Manifest.permission.WRITE_EXTERNAL_STORAGE
                , Manifest.permission.READ_EXTERNAL_STORAGE
                , Manifest.permission.ACCESS_COARSE_LOCATION
                , Manifest.permission.ACCESS_FINE_LOCATION
                , Manifest.permission.READ_PHONE_STATE};
        if (EasyPermissions.hasPermissions(this, mpermissions)) {
            //...有权限
            EventVideoBean bean = new EventVideoBean();
            bean.setKey(Constant.HotUEventKeys.HAS_LOCATION_PERMISSION);
            EventBus.getDefault().post(bean);
        } else {
            //...
            EasyPermissions.requestPermissions(this, "定位需要此权限", RC_CAMERA_AND_WIFI, mpermissions);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    //成功
    @Override
    public void onPermissionsGranted(int requestCode, List<String> list) {
        EventVideoBean bean = new EventVideoBean();
        bean.setKey(Constant.HotUEventKeys.HAS_LOCATION_PERMISSION);
        EventBus.getDefault().post(bean);
    }

    //失败
    @Override
    public void onPermissionsDenied(int requestCode, List<String> list) {
        EventVideoBean bean = new EventVideoBean();
        bean.setKey(Constant.HotUEventKeys.NO_LOCATION_PERMISSION);
        EventBus.getDefault().post(bean);
    }

    /**
     * 侧滑菜单
     */
    private void initDrawer() {
        // 禁止手势滑动
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        List<MenuItemBean> menuList = MenuConfig.getMenuList();
        lvDrawer.setAdapter(new MenuAdapter(this, menuList, this));

//        drawerLayout.openDrawer(Gravity.LEFT);//侧滑打开  不设置则不会默认打开
    }

    @Override
    public void onMenuItemClick(MenuConfig.MenuType menuType) {
        showDrawerLayout();
        MenuConfig.doAction(this, menuType);

    }

    private void showDrawerLayout() {

        List<MenuItemBean> menuList = MenuConfig.getMenuList();
        lvDrawer.setAdapter(new MenuAdapter(this, menuList, this));

        if (!drawerLayout.isDrawerOpen(Gravity.LEFT)) {
            drawerLayout.openDrawer(Gravity.LEFT);
        } else {
            drawerLayout.closeDrawer(Gravity.LEFT);
        }
    }

}

