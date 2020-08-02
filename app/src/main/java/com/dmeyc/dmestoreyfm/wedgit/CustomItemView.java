package com.dmeyc.dmestoreyfm.wedgit;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.text.InputType;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.event.MyEvent;
import com.dmeyc.dmestoreyfm.ui.ActionItemActivity;
import com.dmeyc.dmestoreyfm.utils.AppUtil;
import com.dmeyc.dmestoreyfm.utils.DensityUtil;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.LocationUtil;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyItemDialogListener;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * create by cxg on 2019/11/23
 * <p>
 */
public class CustomItemView extends FrameLayout implements View.OnClickListener {

    private boolean mNeedEventWatch = false;
    public static final float SIZE_16 = 16f;

    private TextView mTvTitle;
    private EditText mEtTitle;
    private TextView mTvRight;
    private ImageView mIvRight;
    private ToggleButton mToggleButton;
    private TimerTaskTextView mTtvItemCode;
    private ImageView mIvLeftIcon;
    private Context mContext;
    private int mType = 1;

    private String mPhoneNo;

    private LocationUtil mLocationUtil;// type=11 时用到

    public CustomItemView(@NonNull Context context) {
        this(context, null);
    }

    public CustomItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.view_custom_item, this, true);
        mEtTitle = findViewById(R.id.et_item_left);
        mTvTitle = findViewById(R.id.tv_item_left);
        mTvRight = findViewById(R.id.tv_item_right);
        mIvRight = findViewById(R.id.iv_item_right_icon);
        mToggleButton = findViewById(R.id.toggle_button_item);
        mTtvItemCode = findViewById(R.id.ttv_item_code);
        mIvLeftIcon = findViewById(R.id.iv_item_icon);
        init(context, attrs);

    }

    /**
     * civ_type:
     * 1：不可编辑 title 字体 16sp , ，marginLeft 21.5dp marginTop 23.5dp，有右侧箭头
     * 2: 只有一个输入框 textsize 17sp
     * 3: 不可编辑 title 字体 16sp , ，marginLeft 21.5dp 右边一个ToggleButton
     * 4: 不可编辑 title 字体 16sp ,右边文字
     * 5:只有一个输入框 textsize 16sp
     * 6:只有一个输入框 textsize 16sp + 右侧验证码
     * 7: 只有一个标题 不可编辑 字体 16sp
     * 8:不可编辑 hint 字体17sp ，有右箭头
     * 9: 左边图片 ,title 17sp  ,右边icon 电话 右icon点击监听
     * 10 :只有一个标题 不可编辑 字体 17sp
     * 11 :左边文字，右icon定位 右icon点击监听
     * 12:左边文字，右边hint 文字，右箭头
     */
    private void init(Context context, AttributeSet attrs) {

        if (attrs != null) {

            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomItemView);
            String title = ta.getString(R.styleable.CustomItemView_civ_left_str);
            String rightStr = ta.getString(R.styleable.CustomItemView_civ_right_str);
            String hintStr = ta.getString(R.styleable.CustomItemView_civ_left_hint_str);
            String rightHintStr = ta.getString(R.styleable.CustomItemView_civ_right_hint_str);
            String inputType = ta.getString(R.styleable.CustomItemView_civ_input_type);
            if (inputType!=null){
                switch (inputType){
                    case "number":
                        mEtTitle.setInputType(InputType.TYPE_CLASS_NUMBER);
                        break;
                    case "number_decimal":
                        mEtTitle.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                        break;
                }
            }

            mType = ta.getInt(R.styleable.CustomItemView_civ_type, 1);
            switch (mType) {
                case 1:
                    mIvRight.setVisibility(View.VISIBLE);
                    if (title != null) {
                        mTvTitle.setText(title);
                    }

                    if (rightStr != null) {
                        mTvRight.setText(rightStr);
                    }
                    break;
                case 2:
                    mEtTitle.setVisibility(View.VISIBLE);
                    if (title != null) {
                        mEtTitle.setText(title);
                    }
                    if (hintStr != null) {
                        mEtTitle.setHint(hintStr);
                    }
                    break;
                case 3:
                    mToggleButton.setVisibility(View.VISIBLE);
                    if (title != null) {
                        mTvTitle.setText(title);
                    }
                    break;
                case 4:
                    if (title != null) {
                        mTvTitle.setText(title);
                    }
                    if (rightStr != null) {
                        mTvRight.setText(rightStr);
                    }
                    break;
                case 5:
                    mEtTitle.setVisibility(View.VISIBLE);
                    mEtTitle.setTextSize(SIZE_16);
                    if (hintStr != null) {
                        mEtTitle.setHint(hintStr);
                    }
                    break;
                case 6:
                    mEtTitle.setVisibility(View.VISIBLE);
                    mEtTitle.setTextSize(SIZE_16);
                    if (hintStr != null) {
                        mEtTitle.setHint(hintStr);
                    }
                    mTtvItemCode.setVisibility(View.VISIBLE);

                    break;
                case 8:
                    if (hintStr != null) {
                        mTvTitle.setHint(hintStr);
                    }
                    mTvTitle.setTextSize(17f);
                    changeTextSize(context, mTvTitle, 22.5f);
                    mIvRight.setVisibility(View.VISIBLE);
                    break;
                case 9:

                    mIvLeftIcon.setVisibility(View.VISIBLE);
                    if (title != null) {
                        mTvTitle.setText(title);
                    }
                    mTvTitle.setTextSize(17f);
                    changeTextSize(context, mTvTitle, 22.5f);
                    mIvRight.setVisibility(View.VISIBLE);
                    mIvRight.setImageResource(R.drawable.icon_telphone);
                    mIvRight.setOnClickListener(this);
                    break;
                case 10:
                    if (title != null) {
                        mTvTitle.setText(title);
                    }
                    mTvTitle.setTextSize(17f);
                    changeTextSize(context, mTvTitle, 22.5f);
                    break;
                case 11:
                    if (title != null) {
                        mTvTitle.setText(title);
                    }
                    mTvTitle.setTextSize(17f);
                    changeTextSize(context, mTvTitle, 22.5f);
                    mIvRight.setVisibility(View.VISIBLE);
                    mIvRight.setImageResource(R.drawable.icon_location);
                    mIvRight.setOnClickListener(this);

                    mNeedEventWatch = true;
                    mLocationUtil = new LocationUtil();
                    break;
                case 12:
                    mIvRight.setVisibility(View.VISIBLE);
                    if (title != null) {
                        mTvTitle.setText(title);
                    }

                    if (hintStr != null) {
                        mTvTitle.setHint(hintStr);
                    }

                    if (rightHintStr != null) {
                        mTvRight.setHint(rightHintStr);
                    }
                    break;
                default:
                    break;
            }
            ta.recycle();
        }
    }

    public void setPhoneNo(String phoneNo) {
        this.mPhoneNo = phoneNo;
    }

    private void changeTextSize(Context context, TextView textView, float size) {
        ConstraintLayout.LayoutParams lp3 = (ConstraintLayout.LayoutParams) textView.getLayoutParams();
        lp3.topMargin = dip2px(context, size);
        lp3.bottomMargin = dip2px(context, size);
        textView.setLayoutParams(lp3);
    }

    //db转换为px
    int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 获取输入框内容
     *
     * @return
     */
    public String getTitle() {
        return mEtTitle.getText().toString();
    }

    /**
     * 获取Text
     *
     * @return
     */
    public String getTextTitle() {
        return mTvTitle.getText().toString();
    }

    public void setToggleButtonCheck(boolean b) {
        mToggleButton.setChecked(b);
    }

    public boolean getToggleBttonCheck() {
        return mToggleButton.isChecked();
    }

    public void setToggleButtonEnable(boolean enable) {
        mToggleButton.setEnabled(enable);
    }

    public void setRightTextColor(int color) {
        mTvRight.setTextColor(getResources().getColor(color));
    }

    public void setRightTextContent(String content) {
        if (content != null) {
            mTvRight.setText(content);
        }
    }

    /**
     * 开启验证码倒计时
     */
    public void startTimer() {
        mTtvItemCode.startTimer();
    }

    public void stopTimer() {
        mTtvItemCode.stopTimer();
    }


    public void setItemClickListener(final IOnclickListener listener) {
        if (listener != null) {
            mTtvItemCode.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onCodeClick(CustomItemView.this);
                }
            });
//            mIvRight.setOnClickListener(new OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    listener.onRightIconClick(CustomItemView.this);
//                }
//            });
        }
    }


    public void loadLeftIcon(String imgUrl) {
        if (!TextUtils.isEmpty(imgUrl)) {
            GlideUtil.loadLocalImage(mContext, imgUrl, mIvLeftIcon);
        }

    }

    public void setTitle(String title) {
        if (title != null) {
            mTvTitle.setText(title);
        }
    }
    public void setTitleHint(String title) {
        if (title != null) {
            mTvTitle.setHint(title);
        }
    }

    public void setRightText(String text) {
        if (text != null) {
            mTvRight.setText(text);
        }
    }
    public void setRightHintText(String text) {
        if (text != null) {
            mTvRight.setHint(text);
        }
    }

    public String getRightText() {
        return mTvRight.getText().toString();
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_item_right_icon:
                switch (mType) {
                    case 9:// 打电话
                        if (!TextUtils.isEmpty(mPhoneNo)) {
                            callPhone(mPhoneNo);
                        }
                        break;
                    case 11://定位
//                        if (mLocationUtil != null) {
//                            mLocationUtil.requestLocation(LocationUtil.TYPE_CUSTOM_ITEM);
//                        }
                        showLoaction();
                        break;
                }
                break;
        }
    }

    public interface IOnclickListener {
        void onCodeClick(CustomItemView view);

        void onRightIconClick(CustomItemView view);
    }

    public static class OnclickListener implements IOnclickListener {
        public void onCodeClick(CustomItemView view) {
        }

        public void onRightIconClick(CustomItemView view) {
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mNeedEventWatch) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void loaction(MyEvent.Location event) {
        switch (event.mType) {
            case LocationUtil.TYPE_CUSTOM_ITEM:
                Logger.d("location: " + event.mLocation);
                break;
        }
    }

    /**
     * 打电话
     *
     * @param phoneNum
     */
    public void callPhone(final String phoneNum) {
        new CommNewDialog.Builder(mContext).setMessage("提示\n确认拨打电话：" + mPhoneNo + "吗?")
                .setPositiveButton("取消", null)
                .setNegativeButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        Uri data = Uri.parse("tel:" + phoneNum);
                        intent.setData(data);
                        mContext.startActivity(intent);
                    }
                })
                .createTwoButtonDialog().show();

    }

    /**
     * ================================调用地图begin=====================================
     **/

    private String mLongitude;
    private String mLatitude;
    private String mAddress;

    public CustomItemView setmLongitude(String mLongitude) {
        this.mLongitude = mLongitude;
        return this;
    }

    public CustomItemView setmLatitude(String mLatitude) {
        this.mLatitude = mLatitude;
        return this;
    }

    public CustomItemView setmAddress(String mAddress) {
        this.mAddress = mAddress;
        return this;
    }

    private void showLoaction() {
        final List<String> strings = new ArrayList<>();
        strings.add("高德地图");
        strings.add("百度地图");
        StyledDialog.buildBottomItemDialog(mContext, strings, "cancle", new MyItemDialogListener() {
            @Override
            public void onItemClick(CharSequence text, int position) {

                if (0 == position) {
                    if (AppUtil.isAvilible(mContext, "com.autonavi.minimap")) {
                        StringBuffer stringBuffer = new StringBuffer("androidamap://route?sourceApplication=").append("amap");
                        stringBuffer.append("&dlat=").append(mLatitude)
                                .append("&dlon=").append(mLongitude)
                                .append("&dname=").append(mAddress)
                                .append("&dev=").append(0)
                                .append("&t=").append(2);

                        Intent intent = new Intent("android.intent.action.VIEW", android.net.Uri.parse(stringBuffer.toString()));
                        intent.setPackage("com.autonavi.minimap");
                        mContext.startActivity(intent);
                    } else {
                        ToastUtil.show("您尚未安装高德地图或地图版本过低");
                    }
                } else {
                    startNavi();
                }
            }

            @Override
            public void onBottomBtnClick() {
            }
        }).show();
    }

    //    //开启百度导航
    public void startNavi() {
        //移动APP调起Android百度地图方式举例
        if (AppUtil.isAvilible(mContext, "com.baidu.BaiduMap")) {
            Intent intent = null;
            try {
                intent = Intent.getIntent("intent://map/direction?origin=latlng:" + mLatitude + "," + mLongitude + "|name:我的位置&destination=" + mAddress + "&mode=driving&region=西安&src=yourCompanyName|yourAppName#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
            } catch (URISyntaxException e) {
                Log.e("intent", e.getMessage());
            }
            mContext.startActivity(intent); //启动调用
        } else {
            ToastUtil.show("您尚未安装百度地图或地图版本过低");
        }

    }

    /**================================调用地图end=====================================**/
}
