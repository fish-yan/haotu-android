package com.dmeyc.dmestoreyfm.video.index;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseApp;
import com.dmeyc.dmestoreyfm.base.BaseFragment;
import com.dmeyc.dmestoreyfm.bean.NewWhoPkBean;
import com.dmeyc.dmestoreyfm.bean.ProjectTypeBean;
import com.dmeyc.dmestoreyfm.bean.WhiterBean;
import com.dmeyc.dmestoreyfm.bean.YFMUserBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.dialog.YFMScreenCityDialog;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.DataClass;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.LoadingUtils;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.video.dynamicdetail.DynamicPlayDetailActivity;
import com.dmeyc.dmestoreyfm.video.pkactivity.NewPkActivity;
import com.dmeyc.dmestoreyfm.video.releasedynamic.ReleasedynamicActivity;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;
import com.dmeyc.dmestoreyfm.wedgit.PullRecyclerView;
import com.tamic.novate.Throwable;
import com.taobao.library.VerticalBannerView;
import com.tencent.liteav.demo.videoediter.eventbus.EventVideoBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IndexDynamicFragment extends BaseFragment implements View.OnClickListener {

    private TopMenuPopWindow topPopWindow;
    private ArrayList<IndexDynamicBean.DataBean> mListData = new ArrayList<>();
    private IndexDynamicAdapter mAdapter;

    @Bind(R.id.tv_city)
    TextView cityname;

    @Bind(R.id.mSwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Bind(R.id.rv_main_index)
    PullRecyclerView mIndexMainRv;

    @Bind(R.id.iv_toReleaseDynamic)
    ImageView mReleaseDynamic;

    @Bind(R.id.et_search_title)
    EditText et_search_title; //

    @Bind(R.id.tv_wheather)
    TextView tv_wheather; // 天气

    @Bind(R.id.iv_header)
    CircleImageView iv_header;

    YFMScreenCityDialog dialog;

    @OnClick(R.id.ll_city)
    void onCitysChoice() {
        // 去切换城市
        dialog = new YFMScreenCityDialog(getActivity(), R.style.dialog_style_right);
        dialog.onSave(new YFMScreenCityDialog.SaveClickLisenter() {
            @Override
            public void selectCity(String city) {
                cityname.setText(city);
                dialog.dismiss();
                SPUtils.savaStringData(Constant.Config.CURRENT_CITY, city+"市");
                mPage = 1;
                getListData();
                getWither();
            }
        });
        dialog.show();
    }

    @OnClick(R.id.iv_toReleaseDynamic)
    void toReleaseDynamicClick() {
        showTopRightPopMenu();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    /**
     * 事件响应方法
     * 接收消息
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventVideoBean event) {
        String msg = event.getKey();
        if (Constant.HotUEventKeys.RELEASE_DYNAMIC_SUCCESS.equals(msg)) {
            // 发布成功后刷新首页数据
            mPage = 1;
            getListData();
        }
        if (Constant.HotUEventKeys.HAS_LOCATION_PERMISSION.equals(msg)) {
            //去定位
            startSingleLocation();
        }
        if (Constant.HotUEventKeys.NO_LOCATION_PERMISSION.equals(msg)) {
            //使用默认数据
            setDefaultCitysInfo();
            getListData();
            getBanner();
            getPkList();
            getWither();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fm_index_dynamic;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
    @Override
    protected void initData() {

    }
    @Override
    protected void initData(View view) {
        initRecyclerView();
        //初始化定位
        initEditText();
        setUerImage();
        EventBus.getDefault().post(Constant.HotUEventKeys.TO_CHECK_PERMISSION);
    }

    /**
     * 设置输入框搜索
     */
    private boolean isSearch;
    private void initEditText() {
        et_search_title.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //关闭软键盘
                    hideKeyboard(et_search_title.getWindowToken());
                    if (!isSearch) {
                        LoadingUtils.showProgressDialog(getActivity(), "搜索中...");
                        mSearchContent = et_search_title.getText().toString().trim();
                        isSearch = true;
                        mPage = 1;
                        getListData();
                    }
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * 首页列表数据
     */
    private String mSearchContent;
    private int mPage = 1;
    private void getListData() {
        ParamMap.Build mParams = new ParamMap.Build();
        mParams.addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN));
        if(TextUtils.isEmpty(DataClass.LocationDistrict)){
            mParams.addParams("sorttype",1);
        }else{
            mParams.addParams("sorttype",4);
        }
        mParams.addParams("page", mPage);
        mParams.addParams("size", 10);
        if (!TextUtils.isEmpty(mSearchContent)) {
            mParams.addParams("search", mSearchContent);
        }
        mParams.addParams("longitude", SPUtils.getStringData(Constant.Config.LONGTUDE));
        mParams.addParams("latitude", SPUtils.getStringData(Constant.Config.LATITUDE));
        if (!TextUtils.isEmpty(SPUtils.getStringData(Constant.Config.CURRENT_CITY))) {
            mParams.addParams("city", SPUtils.getStringData(Constant.Config.CURRENT_CITY));
        } else {
            SPUtils.savaStringData(Constant.Config.CURRENT_CITY, "上海市");
        }
        RestClient.getYfmNovate(getActivity()).get(Constant.API.DYNAMIC_LIST,
                mParams.build(), new DmeycBaseSubscriber<IndexDynamicBean>() {
                    @Override
                    public void onSuccess(IndexDynamicBean bean) {
                        if(mLocationClient != null){
                            mLocationClient.stopLocation();
                        }
                        LoadingUtils.cancelProgressDialog();
                        isSearch = false;
                        if (mPage == 1) {
                            mListData.clear();
                        }
                        int start = mListData.size();
                        if (bean.getData() != null && bean.getData().size() > 0) {
                            mListData.addAll(bean.getData());
                        }
                        mAdapter.notifyItemRangeChanged(start, mListData.size());
                        stopLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        LoadingUtils.cancelProgressDialog();
                        isSearch = false;
                        Toast.makeText(BaseApp.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        stopLoading();
                    }
                });
    }

    private ArrayList<NewWhoPkBean.DataBean> verticalBannerData = new ArrayList<>();

    /**
     * 上下翻、PK榜
     */
    private void getPkList() {
        ParamMap.Build mParams = new ParamMap.Build();
        mParams.addParams("pageIndex", 1);
        mParams.addParams("pageSize", 5);
        mParams.addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN));
        mParams.addParams("city_name", SPUtils.getStringData(Constant.Config.CURRENT_CITY));
        mParams.addParams("longitude", SPUtils.getStringData(Constant.Config.LONGTUDE));
        mParams.addParams("latitude", SPUtils.getStringData(Constant.Config.LATITUDE));
        mParams.addParams("project_type", 1);
        mParams.addParams("status", 0);
        RestClient.getYfmNovate(getActivity()).post(Constant.API.YFM_PKLIST,
                mParams.build(), new DmeycBaseSubscriber<NewWhoPkBean>() {
                    @Override
                    public void onSuccess(NewWhoPkBean bean) {
                        LoadingUtils.cancelProgressDialog();
                        mPkName = mIndexMainRv.findViewWithTag("10000");
                        verticalBannerData.clear();
                        if (bean.getData() != null && bean.getData().size() > 0) {
                            verticalBannerData.addAll(bean.getData());
                        }
                        if(mPkName != null){
                            if (adapter == null) {
                                adapter = new VerticalBannerAdapter(verticalBannerData);
                                mPkName.setAdapter(adapter);
                            }
                            mPkName.stop();
                            mPkName.start();
                            adapter.setOnContentClickListener(new VerticalBannerAdapter.OnContentClickListener() {
                                @Override
                                public void onContentClick(NewWhoPkBean.DataBean bean) {
                                    //  跳转
                                    NewPkActivity.newIntent(getActivity());
//                                    startActivity(new Intent(getActivity(), PkResultActivity.class)
//                                            .putExtra("ispked", bean.getIsPked())
//                                            .putExtra("PK_groupid", bean.getGroup_pk_id())
//                                            .putExtra("headurl", bean.getGroup_a_logo())
//                                            .putExtra("graopname", bean.getGroup_a_name()));

                                }
                            });
                        }
                        mAdapter.initVerticalBannerData(verticalBannerData);
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                });
    }

    int spanCount = 2;
    StaggeredGridLayoutManager layoutManager;
    private VerticalBannerView mPkName;
    private VerticalBannerAdapter adapter;

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        mAdapter = new IndexDynamicAdapter(getActivity(), mListData);
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        // 绑定布局管理器
        mIndexMainRv.getRecyclerView().setLayoutManager(layoutManager);
        //当滑到底部的时候，最后一个item等待一段时间才显示出来
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mIndexMainRv.getRecyclerView().setItemAnimator(null);
        //解决:从底部滚动到顶部时，会发现顶部item上方偶尔会出现一大片间隔
        mIndexMainRv.getRecyclerView().addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                int[] first = new int[spanCount];
                layoutManager.findFirstCompletelyVisibleItemPositions(first);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && (first[0] == 1 || first[1] == 1)) {
                    layoutManager.invalidateSpanAssignments();
                }
            }
        });
        mIndexMainRv.setAdapter(mAdapter);
        mIndexMainRv.setOutSwipeRefreshLayout(mSwipeRefreshLayout);
        //下拉刷新
        mIndexMainRv.setPullDownRefreshListener(new PullRecyclerView.PullDownRefreshListener() {
            @Override
            public void onRefresh() {
                //下拉刷新时不清空数据，如果返回为空，则还显示原来的数据
//                setTopBannerEnable(false);//下拉刷新中... 禁用Banner
                mPage = 1;
                getListData();
            }
        });
        //上拉加载
        mIndexMainRv.setPullUpLoadMoreListener(new PullRecyclerView.PullUpLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPage++;
                getListData();
            }
        });
        mAdapter.setOnIndexItemClickListener(new IndexDynamicAdapter.OnIndexItemClickListener() {
            @Override
            public void onItemClick(int position) {
                DynamicPlayDetailActivity.newIntent(getActivity(), mListData.get(position - 1).getId(),mListData.get(position - 1).getType(), mListData.get(position - 1).getDistance());
//                Intent intent = new Intent(getActivity(), VideoPlayTestActvity.class);
//                startActivity(intent);
            }
        });
    }

    /* 停止上拉刷新或者下拉加载 */
    private void stopLoading() {
        mIndexMainRv.setPullUpLoadMoreCompleted();
        mIndexMainRv.setPullDownRefreshCompleted();
    }

    /**
     * 显示右上角popup菜单
     */
    private void showTopRightPopMenu() {
        if (topPopWindow == null) {
            //(activity,onclicklistener,width,height)
            topPopWindow = new TopMenuPopWindow(getActivity(), this, 360, 290);
            //监听窗口的焦点事件，点击窗口外面则取消显示
            topPopWindow.getContentView().setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        topPopWindow.dismiss();
                    }
                }
            });
        }
        //设置默认获取焦点
        topPopWindow.setFocusable(true);
        //以某个控件的x和y的偏移量位置开始显示窗口
        topPopWindow.showAsDropDown(mReleaseDynamic, 0, 0);
        //如果窗口存在，则更新
        topPopWindow.update();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_intent_to_pic:
                ReleasedynamicActivity.newIntent(getActivity(), ReleasedynamicActivity.TYPE_OF_PIC);
                if (topPopWindow != null) {
                    topPopWindow.dismiss();
                }
                break;
            case R.id.tv_intent_to_video:
                ReleasedynamicActivity.newIntent(getActivity(), ReleasedynamicActivity.TYPE_OF_VIDEO);
                if (topPopWindow != null) {
                    topPopWindow.dismiss();
                }
                break;
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private ArrayList<String> banners = new ArrayList<>();


    public void getBanner() {
        ParamMap.Build PB = new ParamMap.Build();
        PB.addParams("key", "HOME_ROTATION_BADMINTON");
        RestClient.getYfmNovate(getActivity()).post(Constant.API.YFM_GETBANNER,
                PB.addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                        .build(),
                new DmeycBaseSubscriber<ProjectTypeBean>(getActivity()) {
                    @Override
                    public void onSuccess(ProjectTypeBean bean) {
                        banners.clear();
                        for (int i = 0; i < bean.getData().size(); i++) {
                            banners.add(bean.getData().get(i).getItemName());
                        }
                        mAdapter.initBannerData(banners);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }


    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new MyAMapLocationListener();
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;

    /**
     * 启动单次客户端定位
     */
    void startSingleLocation() {
        //初始化定位
        mLocationClient = new AMapLocationClient(getActivity().getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        /**
         * 设置定位场景，目前支持三种场景（签到、出行、运动，默认无场景）
         */
        mLocationOption.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setOnceLocationLatest(true);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //关闭缓存机制
        mLocationOption.setLocationCacheEnable(false);
//        mLocationClient = new AMapLocationClient(getContext());
//        //设置定位回调监听
//        //初始化AMapLocationClientOption对象
//        mLocationOption = new AMapLocationClientOption();
//        //获取一次定位结果：
//        //该方法默认为false。
//        mLocationOption.setOnceLocation(false);
//        //获取最近3s内精度最高的一次定位结果：
//        //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
//        mLocationOption.setOnceLocationLatest(true);
//        //设置是否返回地址信息（默认返回地址信息）
//        mLocationOption.setNeedAddress(true);
//        mLocationOption.setWifiActiveScan(true);
//        //设置是否允许模拟位置,默认为true，允许模拟位置
//        mLocationOption.setMockEnable(false);
//        mLocationOption.setInterval(2000);
//        //给定位客户端对象设置定位参数
//        mLocationClient.setLocationOption(mLocationOption);
//        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
//        mLocationOption.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);
//        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //启动定位s
        mLocationClient.setLocationListener(mLocationListener);
        mLocationClient.stopLocation();
        mLocationClient.startLocation();
    }

    /**
     * 单次客户端的定位监听
     */
    AMapLocationListener locationSingleListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation location) {
            ToastUtil.show(location.getCity());
        }
    };


    class MyAMapLocationListener implements AMapLocationListener {
        @Override
        public void onLocationChanged(final AMapLocation amapLocation) {

            if (amapLocation != null) {
                mLocationClient.stopLocation();
                if (amapLocation.getErrorCode() == 0) {
                    //定位成功回调信息，设置相关消息
                    Log.e("location==", "getDistrict==" + amapLocation.getDistrict() + ".getStreet(==" + amapLocation.getStreet() + "address==" + amapLocation.getAddress());
                    SPUtils.savaStringData(Constant.Config.CURRENT_CITY, amapLocation.getCity());
                    SPUtils.savaStringData(Constant.Config.CURRENT_PROVINCE, amapLocation.getProvince());
                    SPUtils.savaStringData(Constant.Config.LONGTUDE, amapLocation.getLongitude() + "");
                    SPUtils.savaStringData(Constant.Config.LATITUDE, amapLocation.getLatitude() + "");
                    DataClass.LocationCity = amapLocation.getCity();
                    DataClass.LocationProvince = amapLocation.getProvince();
                    DataClass.LocationDistrict = amapLocation.getDistrict();
                    DataClass.LocationAddress = amapLocation.getAddress();
                    cityname.setText(amapLocation.getCity());
//                    }
//                    amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
//                    amapLocation.getLatitude();//获取纬度
//                    amapLocation.getLongitude();//获取经度
//                    amapLocation.getAccuracy();//获取精度信息
//                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                    Date date = new Date(amapLocation.getTime());
//                    df.format(date);//定位时间
//                    amapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
//                    amapLocation.getCountry();//国家信息
//                    amapLocation.getProvince();//省信息
//                    amapLocation.getCity();//城市信息
//                    amapLocation.getDistrict();//城区信息
//                    amapLocation.getStreet();//街道信息
//                    amapLocation.getStreetNum();//街道门牌号信息
//                    amapLocation.getCityCode();//城市编码
//                    amapLocation.getAdCode();//地区编码
//                    amapLocation.getAOIName();//获取当前定位点的AOI信息
                } else {
//                    ToastUtil.show("shibai");
                    //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + amapLocation.getErrorCode() + ", errInfo:"
                            + amapLocation.getErrorInfo());
                    setDefaultCitysInfo();
                }
            } else{
                mLocationClient.stopLocation();
                setDefaultCitysInfo();
            }
            //定位完成
            mLocationClient.stopLocation();
            getListData();
            getBanner();
            getPkList();
            getWither();
        }
    }

    /**
     * 设置默认定位信息
     */
    private void setDefaultCitysInfo() {
        SPUtils.savaStringData(Constant.Config.CURRENT_CITY, "上海市");
        SPUtils.savaStringData(Constant.Config.CURRENT_PROVINCE, "上海市");
        SPUtils.savaStringData(Constant.Config.LONGTUDE, 121.520050 + "");
        SPUtils.savaStringData(Constant.Config.LATITUDE, 31.228833+ "");
        DataClass.LocationProvince = "上海市";
        DataClass.LocationCity = "上海市";
        cityname.setText("上海市");
    }

    /**
     * 获取InputMethodManager，隐藏软键盘
     *
     * @param token
     */
    private void hideKeyboard(IBinder token){
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 获取天气
     */
    public void getWither() {
        ParamMap.Build pb = new ParamMap.Build();
        if (TextUtils.isEmpty(SPUtils.getStringData(Constant.Config.CURRENT_CITY))) {
            pb.addParams("city", "上海");
        } else {
            pb.addParams("city", SPUtils.getStringData(Constant.Config.CURRENT_CITY).replace("市", ""));
        }
        RestClient.getYfmNovate(getActivity()).get(Constant.API.YFM_GETWHITER, pb
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .build(), new DmeycBaseSubscriber<WhiterBean>(getActivity()) {
            @Override
            public void onSuccess(final WhiterBean bean) {
                GlideUtil.loadImage(getActivity(), bean.getData().getUserLogo(), iv_header);
                tv_wheather.setText(bean.getData().getWeather() + " " + bean.getData().getTemperature() + "°");
//              ToastUtil.show(bean.getMsg());
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                ToastUtil.show(e.getMessage());
            }
        });
    }

    /**
     * 设置头像
     */
    private void setUerImage() {
        RestClient.getYfmNovate(getActivity()).post(Constant.API.YFM_USER_INFO, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .build(), new DmeycBaseSubscriber<YFMUserBean>() {
            @Override
            public void onSuccess(YFMUserBean bean) {
                if (!TextUtils.isEmpty(bean.getData().getUser_logo())) {
                    RequestOptions options = new RequestOptions()
                            .centerCrop()
                            .error(R.drawable.image_default);
                    Glide.with(getActivity().getApplicationContext()).load(bean.getData().getUser_logo()).apply(options).into(iv_header);
                }
            }
        });
    }
}
