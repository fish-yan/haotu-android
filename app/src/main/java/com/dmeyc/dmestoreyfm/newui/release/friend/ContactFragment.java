package com.dmeyc.dmestoreyfm.newui.release.friend;

import android.Manifest;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dmeyc.dmestoreyfm.adapter.ContactAdapter;
import com.dmeyc.dmestoreyfm.bean.ContactBean;
import com.dmeyc.dmestoreyfm.event.MyEvent;
import com.dmeyc.dmestoreyfm.newbase.BaseRefreshFragment;
import com.dmeyc.dmestoreyfm.newbase.IBaseRefreshView;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.video.releasedynamic.TopicInEditBean;
import com.tbruyelle.rxpermissions.RxPermissions;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

import rx.functions.Action1;

/**
 * create by cxg on 2019/11/30
 */
public class ContactFragment extends BaseRefreshFragment<IBaseRefreshView, ContactPresenter, ContactBean, ContactAdapter> {


    @Override
    protected ContactAdapter getAdapter() {
        return new ContactAdapter();
    }

    @Override
    protected ContactPresenter createPresenter() {
        return new ContactPresenter();
    }

    @Override
    protected boolean needSearch() {
        return true;
    }

    @Override
    protected void onSearch(String searchStr) {
        mPresenter.locationRequest(searchStr);
    }

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {
        mEtSearch.setHint("搜索联系人");
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                TopicInEditBean bean = new TopicInEditBean();
                bean.setType(TopicInEditBean.TYPE_CONTACT);
                bean.setPhone(mData.get(position).phoneNo);
                bean.setName(mData.get(position).name);
                bean.setObjectRule("@");
                bean.setObjectText(mData.get(position).name);
                EventBus.getDefault().post(new MyEvent.ReleaseVideo(bean));
                getActivity().finish();
            }
        });
    }

    @Override
    protected void initData() {
        RxPermissions.getInstance(getContext()).request(Manifest.permission.READ_CONTACTS).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                if (aBoolean) {
                    mPresenter.locationRequest(null);
                } else {
                    ToastUtil.show("请求权限 " + Manifest.permission.READ_CONTACTS + " 被拒");
                }
            }
        });

    }

    @Override
    public void requestData() {

    }

    @Override
    public Map<String, String> getParams() {

        return null;
    }

}
