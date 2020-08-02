package com.dmeyc.dmestoreyfm.newui.release.match;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.codbking.widgets.DatePickDialog;
import com.codbking.widgets.OnSureLisener;
import com.codbking.widgets.bean.DateType;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.ProjectTypeBean;
import com.dmeyc.dmestoreyfm.bean.response.UserListBean;
import com.dmeyc.dmestoreyfm.event.MyEvent;
import com.dmeyc.dmestoreyfm.event.RefreshEvent;
import com.dmeyc.dmestoreyfm.newbase.BaseMvpActivity;
import com.dmeyc.dmestoreyfm.newui.release.selectanchor.SearchAnchorActivity;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.utils.photoselector.ImageChooserHelper;
import com.dmeyc.dmestoreyfm.wedgit.CustomItemView;
import com.dmeyc.dmestoreyfm.wedgit.PopupMenu;
import com.dmeyc.dmestoreyfm.wedgit.TypeSelectorDialog;
import com.dmeyc.dmestoreyfm.wedgit.UploadPictureView;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * create by cxg on 2019/11/30
 */
public class ReleaseMatchActivity extends BaseMvpActivity<IReleaseMatchView, ReleaseMatchPresenter> implements IReleaseMatchView {

    @Bind(R.id.civ_theme)
    CustomItemView mCivTheme;
    @Bind(R.id.civ_time)
    CustomItemView mCivTime;
    @Bind(R.id.civ_address)
    CustomItemView mCivAddress;
    @Bind(R.id.civ_type)
    CustomItemView mCivType;
    @Bind(R.id.civ_anchor)
    CustomItemView mCivAnchor;
    @Bind(R.id.uploadPictureView)
    UploadPictureView mUploadPictureView;
//    private PopupMenu mPopupMenu;
    private ProjectTypeBean.DataBean mSelectorData;


    private ImageChooserHelper mChooseHelper;

    private String anchorNames = "";
    private String anchorIds;

    public static void newInstance(Context context) {
        Intent intent = new Intent(context, ReleaseMatchActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected boolean enableEventBus() {
        return true;
    }

    @Override
    protected ReleaseMatchPresenter createPresenter() {
        return new ReleaseMatchPresenter();
    }

    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @Override
    protected int setContentView() {
        return R.layout.activity_release_match;
    }

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {
        setTitleWithBothText("取消发布", "发布", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkSucc()) {
                    mPresenter.uploadImage(mUploadPictureView.getImgPath());
                }
            }
        });
        mUploadPictureView.setListener(new UploadPictureView.AddClickListerner() {
            @Override
            public void onAddClick() {
                mChooseHelper.showChooseDialog(ImageChooserHelper.MODE_NO_VEDIO);
            }
        });
        mChooseHelper = new ImageChooserHelper(this, false, new ImageChooserHelper.IHandleChooseResult() {
            @Override
            public void onChooseOver(String path) {
                Logger.d("choose path :" + path);
                mUploadPictureView.loadImage(path);
            }

            @Override
            public void onChooseOver(ArrayList<String> paths, String is_check_original_image) {
                Logger.d("choose paths:" + paths);

            }
        });

        mChooseHelper.setSelectMaxCount(1);
        mChooseHelper.selectFromAblum(false);
        mChooseHelper.setNeedCrop(false);
    }

    private boolean checkSucc() {
        if (TextUtils.isEmpty(mCivTheme.getTitle())) {
            ToastUtil.show("请输入赛事主题");
            return false;
        }
        if (TextUtils.isEmpty(mCivTime.getTextTitle())) {
            ToastUtil.show("请输入赛事时间");
            return false;
        }
        if (TextUtils.isEmpty(mCivAddress.getTitle())) {
            ToastUtil.show("请输入赛事地点");
            return false;
        }
        if (TextUtils.isEmpty(mCivType.getTextTitle())) {
            ToastUtil.show("请选择赛事类型");
            return false;
        }
        if (TextUtils.isEmpty(mUploadPictureView.getImgPath())) {
            ToastUtil.show("请上传图片");
            return false;
        }
        return true;
    }

    @OnClick({R.id.civ_type, R.id.civ_time, R.id.civ_anchor})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.civ_type:
                mPresenter.getMatchType();
                break;
            case R.id.civ_time:
                showDatePickDialog(DateType.TYPE_ALL);
                break;
            case R.id.civ_anchor:
                SearchAnchorActivity.newInstance(this,"");
                break;
            default:
        }
    }

    @Override
    public void getBandListSucc(final List<ProjectTypeBean.DataBean> datas) {
        ArrayList<String> banNameList = new ArrayList<>();
        for (int i = 0; i < datas.size(); i++) {
            banNameList.add(datas.get(i).getItemName());
        }

        new TypeSelectorDialog.Build<>(ReleaseMatchActivity.this).setData(datas).setNameItems(banNameList).setOnItemClickListener(new TypeSelectorDialog.ItemClickListener<ProjectTypeBean.DataBean>() {
            @Override
            public void onItemClick(String title, ProjectTypeBean.DataBean bean) {
                mSelectorData = bean;
                mCivType.setTitle(title);
            }
        }).create().show();


        ;

//        mPopupMenu = new PopupMenu(ReleaseMatchActivity.this, banNameList);
//        mPopupMenu.showLocation(R.id.civ_type);// 设置弹出菜单弹出的位置
//        // 设置回调监听，获取点击事件
//        mPopupMenu.setOnItemClickListener(new PopupMenu.OnItemClickListener() {
//            @Override
//            public void onClick(PopupMenu.MENUITEM item, String str) {
//
//            }
//
//            @Override
//            public void onClick(String str, int pos) {
//                mSelectorData = datas.get(pos);
//                mCivType.setTitle(str);
//                mPopupMenu.dismiss();
//            }
//        });
    }

    @Override
    public void httpRequestDataSucc() {
        ToastUtil.show("发布成功");
        EventBus.getDefault().post(new RefreshEvent.Release(RefreshEvent.Release.TYPE_ACTIVITY));
        finish();
    }

    @Override
    public Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("activity_name", mCivTheme.getTitle());
        params.put("start_date", mCivTime.getTextTitle());
        params.put("activity_address", mCivAddress.getTitle());
        params.put("activity_type", mSelectorData.getItemCode());
        if (anchorIds != null) {
            params.put("archor_user_id", anchorIds);
        }
        return params;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getAnchor(MyEvent.SelectorAnchor event) {
        StringBuffer sbNames = new StringBuffer();
        StringBuffer sbIds = new StringBuffer();
        for (UserListBean.DataBean bean : event.checkList) {
            sbNames.append(bean.getNickName()).append(",");
            sbIds.append(bean.getUserId()).append(",");
        }
        if (sbNames.length() > 0) {
            mCivAnchor.setTitle(sbNames.substring(0, sbNames.length() - 1));
            anchorIds = sbIds.substring(0, sbIds.length() - 1);
            mCivAnchor.setRightHintText("可重新选择");
        } else {
            anchorIds = null;
            mCivAnchor.setTitle("");
            mCivAnchor.setRightHintText("非必填");
            mCivAnchor.setTitleHint("请选择主播");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mChooseHelper.onActivityResult(requestCode, resultCode, data);
    }

    private void showDatePickDialog(DateType type) {
        final DatePickDialog dialog = new DatePickDialog(this);
        //设置上下年分限制
        dialog.setYearLimt(5);
        //设置标题
        dialog.setTitle("选择开始时间");
        //设置类型
        dialog.setType(type);
        //设置消息体的显示格式，日期格式
//        dialog.setMessageFormat("yyyy-MM-dd HH:mm");
        dialog.setMessageFormat("yyyy-MM-dd HH");
        //设置选择回调
        dialog.setOnChangeLisener(null);
        //设置点击确定按钮回调
        dialog.setOnSureLisener(new OnSureLisener() {
            @Override
            public void onSure(Date date) {
                mCivTime.setTitle(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date));
            }
        });
        dialog.show();
    }
}
