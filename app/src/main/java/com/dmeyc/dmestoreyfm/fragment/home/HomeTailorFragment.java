package com.dmeyc.dmestoreyfm.fragment.home;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseFragment;
import com.dmeyc.dmestoreyfm.bean.TailorListBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.wedgit.sectioned.SectionedRecyclerViewAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jockie on 2018/3/20
 * Email:jockie911@gmail.com
 */
public class HomeTailorFragment extends BaseFragment implements OnRefreshListener {

    protected RecyclerView contentRecycler;
    protected SmartRefreshLayout mSmartRefresh;
    private SectionedRecyclerViewAdapter sectionedRecyclerViewAdapter;
    private boolean isFirst = true;//第一次下载数据
    private TailorSection tailorSection;
    private List<TailorListBean.DataBean> list = new ArrayList();

    @Override
    protected int getLayoutRes() {
        return R.layout.fm_tailor;
    }

    @Override
    protected void initData() {
        contentRecycler = (RecyclerView) mRootView.findViewById(R.id.main_recyclerView); //使用bind 会出现空指针
        mSmartRefresh = (SmartRefreshLayout) mRootView.findViewById(R.id.smartRl); //使用bind 会出现空指针
    }

    @Override
    protected void initData(View view) {
//        if(Util.checkLoginStatus(getContext()))
        initRecyclerView();
    }

    private void initRecyclerView() {
        LinearLayoutManager myLayoutManager = new LinearLayoutManager(getActivity());
        contentRecycler.setLayoutManager(myLayoutManager);
        sectionedRecyclerViewAdapter = new SectionedRecyclerViewAdapter();
        contentRecycler.setAdapter(sectionedRecyclerViewAdapter);
        mSmartRefresh.setOnRefreshListener(this);
    }

    public void getData() {
//        startActivity(new Intent(getActivity(), TailorRecordActivity.class));
        String mobile = SPUtils.getStringData(Constant.Config.USER_ID, "");
//        RestClient.getNovate(getActivity(), Constant.API.TBASE_URL).get(Constant.API.TAILOR_RECORD, new ParamMap.Build()
//                .addParams(Constant.Config.MOBILE, mobile)
//                .addParams("string", Util.MD5(mobile + Constant.Config.MD5_KEY + mobile)).build(), new DmeycBaseSubscriber<RecordBean>(getActivity()) {
//            @Override
//            public void onNext(ResponseBody t) {
//                try {
//                    JSONObject object = new JSONObject(t.string());
//                    if(object.has("iserror")){
//                        String code = object.getString("iserror");
//                        if(TextUtils.equals("0",code)){
//                            RecordBean recordBean = new GsonTools().changeGsonToBean(object.toString(), RecordBean.class);
//                            onSuccess(recordBean);
//                        }
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onSuccess(RecordBean bean) {
//                if(Util.objEmpty(bean.getData()))
//                    return;
//                closeRefresh();
//                initSection(bean.getData());
//            }
//        });
        RestClient.getNovate(getActivity(),Constant.API.BASE_URL).get(Constant.API.TAILOR_RECORD, new ParamMap.Build()
                .addParams("id",mobile).build(), new DmeycBaseSubscriber<TailorListBean>() {
            @Override
            public void onSuccess(TailorListBean bean) {
                closeRefresh();
                initSection(bean.getData());
            }
        });
    }

    private void initSection(List<TailorListBean.DataBean> data) {
        list.clear();
        list.addAll(data);
//        if (isFirst) {
            if (null != list) {
                tailorSection = new TailorSection(getActivity(), sectionedRecyclerViewAdapter);
//                tailorSection.setData(list);
            }
//        }else {
            sectionedRecyclerViewAdapter.removeAllSections();
            tailorSection.setData(list);
//        }
            sectionedRecyclerViewAdapter.addSection(tailorSection);
        isFirst = false;
    }
    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        getData();
    }
//    @OnClick({R.id.tv_start_measure,R.id.tv_measure_record})
//    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.tv_start_measure:
//                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
//                        ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
//                    SPUtils.savaIntData("SP_CAMERA_DIRECTION", CameraManager.CameraDirection.CAMERA_BACK.ordinal());
//                    startActivity(new Intent(getActivity(), FrontTailorActivity.class));
//                }else{
//                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
//                }
//                break;
//            case R.id.tv_measure_record:
//                if(Util.checkLoginStatus(getContext()))
//                    startActivity(new Intent(getActivity(), TailorRecordActivity.class));
//                break;
//        }
//    }
    @Override
    public void onResume() {
        super.onResume();
               getData();
    }
    /**
     * 关闭刷新
     */
    public void closeRefresh() {
        if (mSmartRefresh.isRefreshing())
            mSmartRefresh.finishRefresh();
    }
}
