package com.dmeyc.dmestoreyfm.base;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.BaseRvAdapter;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.ui.YFMLoginActivity;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tamic.novate.Throwable;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import okhttp3.ResponseBody;

/**
 * Created by jockie on 2018/3/14
 * Email:jockie911@gmail.com
 */

public abstract class BaseRefreshFragment<T,K extends BaseRvAdapter> extends BaseFragment implements OnRefreshListener, OnLoadmoreListener {

//    @Bind(R.id.recycleview)
    protected RecyclerView mRecycleView;
//    @Bind(R.id.smartRl)
    protected SmartRefreshLayout mSmartRefresh;
   protected TextView tv_nodata;
    protected K adapter;
    protected int mCurrentPage = 1;
    protected int mTotalPages = mCurrentPage;
    protected HeaderAndFooterWrapper wrapper;

    @Override
    protected int getLayoutRes() {
        return R.layout.fm_base_refresh;
    }

    @Override
    protected void initData() {
        mRecycleView = (RecyclerView) mRootView.findViewById(R.id.recycleview); //使用bind 会出现空指针
        mSmartRefresh = (SmartRefreshLayout) mRootView.findViewById(R.id.smartRl); //使用bind 会出现空指针
        tv_nodata=(TextView) mRootView.findViewById(R.id.tv_nodata);
        mRecycleView.setLayoutManager(getLayoutManager());
        adapter = getAdapter();
        if(isWithHead()){
            wrapper = getWrapper();
            mRecycleView.setAdapter(wrapper);
        }else{
            mRecycleView.setAdapter(adapter);
        }
        requestData();
        mSmartRefresh.setOnRefreshListener(this);
        mSmartRefresh.setOnLoadmoreListener(this);
    }


    protected RecyclerView.LayoutManager getLayoutManager(){
        return new LinearLayoutManager(getActivity());
    }

    /**
     * recycleview带head
     * @return
     */
    protected boolean isWithHead(){
        return false;
    }
    /**
     * 使用统一的HeaderAndFooterWrapper
     * @return
     */
    protected HeaderAndFooterWrapper getWrapper(){
        return new HeaderAndFooterWrapper(adapter);
    }
    /**
     * 子类实现adapter
     * @return
     */
    protected abstract K getAdapter();
    /**
     * 请求网络
     */
    protected void requestData(){
        Map<String, Object> paramMap = getParamMap();
//        paramMap.put("page",mCurrentPage);
//        paramMap.put("pageSize",10);
        paramMap.put("pageIndex",mCurrentPage);
        paramMap.put("pageSize",100);
        RestClient.getYfmNovate(getActivity()).post(getUrl(), paramMap, new DmeycBaseSubscriber<T>(getActivity()) {
            @Override
            public void onSuccess(T bean) {
                setData(bean,mCurrentPage == 1);
            }
            @Override
            public void onError(Throwable e) {
//                super.onError(e);
            }
            @Override
            public void onNext(ResponseBody t) {
                try {
                    JSONObject object = new JSONObject(t.string());
                    if(object.has("code")){
                        int code = object.getInt("code");
                        switch (code){
                            case 200:
//                                if(object.has("data")){
//                                    JSONObject data = object.getJSONObject("data");
//                                    if(data.has("paginator")){
//                                        JSONObject paginator = data.getJSONObject("paginator");
//                                        if(paginator.has("totalPages"))
//                                       mTotalPages = paginator.getInt("totalPages");
//                                        if(paginator.has("page"))
//                                            mCurrentPage = paginator.getInt("page"); //防止数据错乱导致mCurrentPage严重错位
//                                    }
//                                }
//                                Class<T> kClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
//                                T k = GsonTools.changeGsonToBean(object.toString(), kClass);
                                T k = parseData(object.toString());
                                onSuccess(k);
                                break;
                            case 300: //java后台定义的错误
                                ToastUtil.show( object.getString("msg"));
                            case 400:
                                ToastUtil.show( object.getString("msg"));
                                break;
                        }
                        closeRefresh();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    closeRefresh();
                }
            }
        });
    }

    /**
     * 解析数据  如果解决泛型问题，可用泛型替代
     * @param result
     * @return
     */
    protected abstract T parseData(String result);

    protected abstract void setData(T t, boolean isRefresh);

    /**
     * 网络url地址
     * @return
     */
    protected abstract String getUrl();
    /**
     * 请求参数
     * @return
     */
    protected abstract Map<String,Object> getParamMap();
    /**
     * 下拉刷新
     * @param refreshlayout
     */
    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
//        SPUtils.setValue(Constant.Config.PROJECTID,"");
//        SPUtils.setValue(Constant.Config.CHECKDAY,"");
//        SPUtils.setValue(Constant.Config.CHECKHOR,"");
        SPUtils.setValue(Constant.Config.HASEKEYWORD,"0");
        mSmartRefresh.finishRefresh();
//        mCurrentPage = 1;
        requestData();
    }
    /**
     * 加载更多
     * @param refreshlayout
     */
    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        SPUtils.setValue(Constant.Config.PROJECTID,"");
        SPUtils.setValue(Constant.Config.CHECKDAY,"");
        SPUtils.setValue(Constant.Config.CHECKHOR,"");
        mSmartRefresh.finishLoadmore();
//        if(mCurrentPage < mTotalPages){
//            mCurrentPage ++;
//            requestData();
//        }else{
//            if(mSmartRefresh.isLoading())
//                mSmartRefresh.finishLoadmore();
//        }
    }

    /**
     * 关闭刷新
     */
    public void closeRefresh(){
        if(mSmartRefresh.isRefreshing())
            mSmartRefresh.finishRefresh();
        if(mSmartRefresh.isLoading())
            mSmartRefresh.finishLoadmore();
    }
    @Override
    public void onResume() {
        super.onResume();
//        requestData();
//        onRefresh(mSmartRefresh);
    }
}
