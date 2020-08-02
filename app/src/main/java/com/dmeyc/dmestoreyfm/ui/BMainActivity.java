package com.dmeyc.dmestoreyfm.ui;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.CustomLazyViewPagerHomeAdapter;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BaseTabFragment;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.fragment.BClientFragment;
import com.dmeyc.dmestoreyfm.fragment.BClientHomeFragment;
import com.dmeyc.dmestoreyfm.fragment.ConVersionFragment;
import com.dmeyc.dmestoreyfm.fragment.MinesFragment;
import com.dmeyc.dmestoreyfm.fragment.PKFragment;
import com.dmeyc.dmestoreyfm.fragment.home.BrandFragment;
import com.dmeyc.dmestoreyfm.listener.YFMGroupInfoProvider;
import com.dmeyc.dmestoreyfm.present.MainPresenter;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.AlphaTabsIndicator;
import com.yinglan.alphatabs.AlphaTabView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import io.rong.imkit.RongIM;

public class BMainActivity extends BaseActivity {

    private MinesFragment mif;
    private BClientFragment bmif;
    @Bind(R.id.alphaIndicator)
    AlphaTabsIndicator alphaTabsIndicator;
    @Bind(R.id.viewpage)
    ViewPager viewPager;
    @Bind(R.id.tab_pkfragment)
    AlphaTabView tab_pkfragment;
    @Bind(R.id.tv_bmainredpos)
    TextView tv_bmainredpos;
    BClientHomeFragment homeFragment;
    private List<Fragment> fragments;
    String type="1";
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_bmain;
    }
    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void initData() {
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

//        if(TextUtils.isEmpty(SPUtils.getStringData(Constant.Config.IDENITY))){
//            type="1";
//        }else {
//            type=SPUtils.getStringData(Constant.Config.IDENITY);
//        }

//        if("0".equals(type)){
//            Intent intent=new Intent(BMainActivity.this,BMainActivity.class);
//            startActivity(intent);
//            finish();
//        }else {
//            Intent intent=new Intent(BMainActivity.this,MainActivity.class);
//            startActivity(intent);
//            finish();
//        }


        fragments = new ArrayList<>();
// fragments.add(new HomeFragment());
//  fragments.add(new BrandFragment());
// fragments.add(new MineFragment());
        //  fragments.add(new ShoppingFragment());
//  fragments.add(new HomeTailorFragment());
//   Fragment conversationList = initConversationList();
//     fragments.add(new CalculateFragment());
//     fragments.add(new AppointmentFragemnt());
//     fragments.add(new ConVersionFragment());

//        if("0".equals(type)){
            alphaTabsIndicator.removeView(tab_pkfragment);
            homeFragment= new BClientHomeFragment();
            fragments.add(homeFragment);
            fragments.add(new ConVersionFragment());
            bmif= new BClientFragment();
            fragments.add(bmif);
//        }else {
//            fragments.add(new BrandFragment());
//            fragments.add(new PKFragment());
//            fragments.add(new ConVersionFragment());
//            mif= new MinesFragment();
//            fragments.add(mif);
//        }
        viewPager.setAdapter(new CustomLazyViewPagerHomeAdapter(getSupportFragmentManager(), fragments));
        alphaTabsIndicator.setViewPager(viewPager,getWindow());
//        if("0".equals(type)){
            viewPager.setOffscreenPageLimit(3);
//        }else {
//            viewPager.setOffscreenPageLimit(4);
//        }

//        VersionUpdateUtils.getInstance(MainActivity.this).checkVersion();

//        RestClient.getNovate(this).get(Constant.API.VERSION, new ParamMap.Build().build(), new DmeycBaseSubscriber<CommonBean>() {
//            @Override
//            public void onSuccess(CommonBean bean) {
//                if(bean.getData() instanceof Boolean){
//                    if(!(boolean)bean.getData()){
//                        new CustomDialog(MainActivity.this)
//                                .builder()
//                                .showTitle()
//                                .setMsg("您当前的版本")
//                                .setCancelable(false)
//                                .setPositiveButton("", new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//                                        finish();
//                                    }
//                                }).show();
//                    }
//                }
//            }
//        });
        registerReceiver(new MyBroadcastReceiver(),
                new IntentFilter("send_mess"));
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 2);
        }

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(1==position){
                    SPUtils.savaStringData(Constant.Config.HAS_REDPOS,"0");
                    tv_bmainredpos.setVisibility(View.GONE);
                    alphaTabsIndicator.getTabView(1).removeShow();
                }
//                     if(2==position){
//                         AppointmentFragemnt appointmentFragemnt=(AppointmentFragemnt)     fragments.get(2);
//                         appointmentFragemnt.notappoin();
//                     }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected boolean isContainTitle() {
        return false;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int position = intent.getIntExtra(Constant.Config.POSITION, 0);
        alphaTabsIndicator.setTabCurrenItem(position);
        viewPager.setCurrentItem(position);
    }

    /**
     * @param fragmentPostion   跳转到的fragment positon
     * @param pagePosition      fragment中的positon
     *
     */
    public void changePage(int fragmentPostion,int pagePosition) {
        alphaTabsIndicator.setTabCurrenItem(fragmentPostion);
        viewPager.setCurrentItem(fragmentPostion);
        if(fragments.get(fragmentPostion) instanceof BaseTabFragment){
            BaseTabFragment baseTabFragment = (BaseTabFragment) fragments.get(fragmentPostion);
            baseTabFragment.setCurrentItem(pagePosition);
        }
    }

    @Override
    protected void setStatusBarRevece(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
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
    protected void onDestroy() {
        super.onDestroy();
        RongIM.getInstance().disconnect();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==62225){
            mif.onActivityResult(requestCode, resultCode, data);
        }else if(resultCode==222){
            mif.onActivityResult(requestCode, resultCode, data);
        }else if(resultCode==555){
            homeFragment.onActivityResult(requestCode, resultCode, data);
        }

    }
    private Fragment initConversationList() {
//        if (mConversationListFragment == null) {
//            ConversationListFragment listFragment = new ConversationListFragment();
        ConVersionFragment listFragment = (ConVersionFragment) ConVersionFragment.instantiate(this, ConVersionFragment.class.getName());
//            listFragment.setAdapter(new ConversationListAdapterEx(RongContext.getInstance()));
//            Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
//                    .appendPath("conversationlist")
//                    .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话是否聚合显示
//                    .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")//群组
//                    .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")//公共服务号
//                    .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "false")//订阅号
//                    .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")//系统
//                    .build();
//            listFragment.setUri(uri);
//            mConversationListFragment = listFragment;
//        }
        return  listFragment;
    }
    public class MyBroadcastReceiver extends BroadcastReceiver {

        @Override

        public void onReceive(Context context, Intent intent) {
            if(SPUtils.getStringData(Constant.Config.HAS_REDPOS).equals("1")){
                tv_bmainredpos.setVisibility(View.GONE);
                alphaTabsIndicator.getTabView(1).showPoint();
            }else {
                tv_bmainredpos.setVisibility(View.GONE);
            }
        }
    }
}
