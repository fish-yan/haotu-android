package com.dmeyc.dmestoreyfm.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.city.CityListAdapter;
import com.dmeyc.dmestoreyfm.adapter.city.ResultListAdapter;
import com.dmeyc.dmestoreyfm.bean.city.City;
import com.dmeyc.dmestoreyfm.bean.city.LocateState;
import com.dmeyc.dmestoreyfm.db.DBManager;
import com.dmeyc.dmestoreyfm.ui.cityview.SideLetterBar;
import com.dmeyc.dmestoreyfm.utils.DataClass;
import com.dmeyc.dmestoreyfm.utils.DensityUtil;
import com.dmeyc.dmestoreyfm.utils.city.StringUtils;
import com.dmeyc.dmestoreyfm.utils.city.ToastUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jockie on 2017/11/17
 * Email:jockie911@gmail.com
 */


public class YFMScreenCityDialog extends Dialog implements View.OnClickListener {


    private CityListAdapter mCityAdapter;
    private ResultListAdapter mResultAdapter;
    private List<City> mAllCities;
    private DBManager dbManager;


    private AMapLocationClient mLocationClient;



    private ListView mListView;
    private ListView mResultListView;
    private SideLetterBar mLetterBar;
    private EditText searchBox;
    private ImageView clearBtn;
    private ImageView backBtn;
    private ViewGroup emptyView;

//    @Bind(R.id.recycleview)
//    RecyclerView mRecycleView;

//    ExpandView expandView;
//    private List<ExpandView> expandlist = new ArrayList<>();
//    private List<List<String>> mData = new ArrayList<>();
//    private List<String> data1 = new ArrayList<>();
//    private List<String> data2 = new ArrayList<>();
    private OnChooseClickListener listener;
Context context;
    public YFMScreenCityDialog(@NonNull Context context) {
        super(context);
        this.context=context;
    }

    public YFMScreenCityDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.context=context;
    }

    public YFMScreenCityDialog(@NonNull Context context, @StyleRes int themeResId, List<List<String>> lists) {
        super(context, themeResId);
//        this.mData = lists;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_city);
        ButterKnife.bind(this);
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//        lp.width = (int) (dialogWindow.getWindowManager().getDefaultDisplay().getWidth() * 0.85);
        lp.width = (int) (dialogWindow.getWindowManager().getDefaultDisplay().getWidth() * 1);
        lp.height = dialogWindow.getWindowManager().getDefaultDisplay().getHeight() - DensityUtil.dip2px(20);
        lp.gravity = Gravity.RIGHT | Gravity.BOTTOM;
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dialogWindow.setAttributes(lp);
        initData();
        initView();
        initLocation();
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.listview_all_city);
        mListView.setAdapter(mCityAdapter);

        TextView overlay = (TextView) findViewById(R.id.tv_letter_overlay);
        mLetterBar = (SideLetterBar) findViewById(R.id.side_letter_bar);
        mLetterBar.setOverlay(overlay);
        mLetterBar.setOnLetterChangedListener(new SideLetterBar.OnLetterChangedListener() {
            @Override
            public void onLetterChanged(String letter) {
                int position = mCityAdapter.getLetterPosition(letter);
                mListView.setSelection(position);
            }
        });

        searchBox = (EditText) findViewById(R.id.et_search);
        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                String keyword = s.toString();
                if (TextUtils.isEmpty(keyword)) {
                    clearBtn.setVisibility(View.GONE);
                    emptyView.setVisibility(View.GONE);
                    mResultListView.setVisibility(View.GONE);
                } else {
                    clearBtn.setVisibility(View.VISIBLE);
                    mResultListView.setVisibility(View.VISIBLE);
                    List<City> result = dbManager.searchCity(keyword);
                    if (result == null || result.size() == 0) {
                        emptyView.setVisibility(View.VISIBLE);
                    } else {
                        emptyView.setVisibility(View.GONE);
                        mResultAdapter.changeData(result);
                    }
                }
            }
        });

        emptyView = (ViewGroup) findViewById(R.id.empty_view);
        mResultListView = (ListView) findViewById(R.id.listview_search_result);
        mResultListView.setAdapter(mResultAdapter);
        mResultListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                back(mResultAdapter.getItem(position).getName());
            }
        });

        clearBtn = (ImageView) findViewById(R.id.iv_search_clear);
        backBtn = (ImageView) findViewById(R.id.back);

        clearBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);
    }
    private void initData() {

        dbManager = new DBManager(context);
        dbManager.copyDBFile();
        mAllCities = dbManager.getAllCities();
        mCityAdapter = new CityListAdapter(context, mAllCities);
        mCityAdapter.setOnCityClickListener(new CityListAdapter.OnCityClickListener() {
            @Override
            public void onCityClick(String name) {
                back(name);
            }

            @Override
            public void onLocateClick() {
                Log.e("onLocateClick", "重新定位...");
                mCityAdapter.updateLocateState(LocateState.LOCATING, null);
                mLocationClient.startLocation();
            }
        });

        mResultAdapter = new ResultListAdapter(context, null);




    }


    private void initLocation() {
        mLocationClient = new AMapLocationClient(context);
        AMapLocationClientOption option = new AMapLocationClientOption();
        option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        option.setOnceLocation(true);
        mLocationClient.setLocationOption(option);
        mLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null) {
                    if (aMapLocation.getErrorCode() == 0) {
                        String city = aMapLocation.getCity();
                        String district = aMapLocation.getDistrict();
                        Log.e("onLocationChanged", "city: " + city);
                        Log.e("onLocationChanged", "district: " + district);
                        String location = StringUtils.extractLocation(city, district);
                        mCityAdapter.updateLocateState(LocateState.SUCCESS, location);
                    } else {
                        //定位失败
                        mCityAdapter.updateLocateState(LocateState.FAILED, null);
                    }
                }
            }
        });
        mLocationClient.startLocation();
    }


    private void back(String city){
        saveClickLisenter. selectCity(city);
//        ToastUtils.showToast(context, "点击的城市：" + city);
//        Intent data = new Intent();
//        data.putExtra(KEY_PICKED_CITY, city);
//        setResult(RESULT_OK, data);
//        finish();
    }




    public void setOnChooseClickListener(OnChooseClickListener listener) {
        this.listener = listener;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_search_clear:
                searchBox.setText("");
                clearBtn.setVisibility(View.GONE);
                emptyView.setVisibility(View.GONE);
                mResultListView.setVisibility(View.GONE);
                break;
            case R.id.back:
                ( (Activity)context).finish();
                break;
        }
    }



    public interface OnChooseClickListener{
        void onChooseClickListener(int position, boolean isSected, String value, int pos);
    }

//    @OnClick(R.id.tv_reset)
//    public void onClickreset(){
//        for (int i=0;i<expandlist.size();i++){
//            expandlist.get(i).ClearItem();
//        }
//    }
//    String chitem0;
//    String chitem1;
//    String chitem2;
//    String pricestar;
//    String priceend;
//    @OnClick(R.id.tv_save)
//    public void onSaveClick(){
//        chitem0 =  expandlist.get(0).checkItem0();
//        chitem1  =  expandlist.get(1).checkItem1();
//        chitem2  =  expandlist.get(2).checkItem2();
//        if(!TextUtils.isEmpty(chitem2)){
//            pricestar=chitem2.split("-")[0];
//            priceend=chitem2.split("-")[1];
//        }
//        saveClickLisenter.onSave(chitem0,chitem1,pricestar,priceend);
//    }
    public SaveClickLisenter saveClickLisenter;
    public void onSave(SaveClickLisenter save ){
        saveClickLisenter=save;
    }


    public interface SaveClickLisenter{
        void   selectCity(String city);
    }
}
