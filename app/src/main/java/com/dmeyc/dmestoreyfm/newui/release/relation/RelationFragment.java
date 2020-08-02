package com.dmeyc.dmestoreyfm.newui.release.relation;

import android.app.Activity;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dmeyc.dmestoreyfm.adapter.RelationAdapter;
import com.dmeyc.dmestoreyfm.bean.response.RelationBean;
import com.dmeyc.dmestoreyfm.constant.ExtraKey;
import com.dmeyc.dmestoreyfm.event.MyEvent;
import com.dmeyc.dmestoreyfm.newbase.BaseRefreshFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

/**
 * create by cxg on 2019/11/30
 */
public class RelationFragment extends BaseRefreshFragment<IRelationView, RelationPresenter, RelationBean.DataBean, RelationAdapter> implements IRelationView {

    public static final int TYPE_COACH = 1;
    public static final int TYPE_SHOP =2;
    public static final int TYPE_CLUB = 3;

    private int mType;
    @Override
    protected RelationAdapter getAdapter() {
        return new RelationAdapter();
    }

    @Override
    protected RelationPresenter createPresenter() {
        return new RelationPresenter();
    }


    @Override
    protected boolean needSearch() {
        return true;
    }

    @Override
    protected void initData() {
        mPresenter.httpRequestData();
    }

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {
        mType = getArguments().getInt(ExtraKey.TYPE_FROM);
        switch (mType){
            case TYPE_COACH:
                mEtSearch.setHint("搜索教练名称");
                break;
            case TYPE_SHOP:
                mEtSearch.setHint("搜索商户名称");
                break;
            case TYPE_CLUB:
                mEtSearch.setHint("搜索俱乐部名称");
                break;
            default:
        }

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                RelationBean.DataBean bean = mData.get(position);
                String type="";
                switch (mType){
                    case TYPE_COACH:
                        type ="3" ;
                        break;
                    case TYPE_SHOP:
                        type ="5" ;
                        break;
                    case TYPE_CLUB:
                        type ="1" ;
                        break;
                    default:
                }
                EventBus.getDefault().post(new MyEvent.ReleaseRelation(bean.getGroupId() + "", bean.getGroupName(),type));
                ((Activity) mContent).finish();
            }
        });
    }

    @Override
    public void requestData() {
        mPresenter.httpRequestData();
    }

    @Override
    public Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("pageSize",PAGE_SIZE);
        params.put("pageIndex",mPageNo+"");
        params.put("keyword",mEtSearch.getText().toString());

        String type="";
        switch (mType){
            case TYPE_COACH:
                type ="3" ;
                break;
            case TYPE_SHOP:
                type ="5" ;
                break;
            case TYPE_CLUB:
                type ="1" ;
                break;
            default:
        }
        params.put("type",type);
        return params;
    }
}
