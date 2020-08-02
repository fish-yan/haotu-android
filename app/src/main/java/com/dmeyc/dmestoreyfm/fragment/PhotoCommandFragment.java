package com.dmeyc.dmestoreyfm.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BaseApp;
import com.dmeyc.dmestoreyfm.base.BaseFragment;
import com.dmeyc.dmestoreyfm.ui.SideTailorActivity;
import com.dmeyc.dmestoreyfm.ui.photo.FrontTailorActivity;
import com.dmeyc.dmestoreyfm.utils.DensityUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.SnackBarUtil;
import com.dmeyc.dmestoreyfm.wedgit.TimeDownView;
import com.dmeyc.dmestoreyfm.wedgit.camera.CameraManager;
import com.dmeyc.dmestoreyfm.wedgit.camera.SquareCameraContainer;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by jockie on 2018/4/18
 * Email:jockie911@gmail.com
 */

public class PhotoCommandFragment extends BaseFragment implements SensorEventListener {

    @Bind(R.id.cameraContainer)
    SquareCameraContainer mCameraContainer;
    @Bind(R.id.tv_flashlight)
    TextView mFlashLight;
    @Bind(R.id.ib_take_photo)
    ImageView mTakePhoto;
    @Bind(R.id.iv_shape_bg)
    ImageView ivShapeBg;

    @Bind(R.id.ib_close)
    ImageButton ibClose;

    @Bind(R.id.ll_vertical)
    LinearLayout llVertical;

    @Bind(R.id.v1)
    View v1;
    @Bind(R.id.v2)
    View v2;

    @Bind(R.id.rel_tips)
    RelativeLayout relTips;
    @Bind(R.id.time_down_view)
    TimeDownView timeDownView;

    private boolean isBackDireation;   //前置摄像头或者后置摄像头
    private boolean isFrontTakePhoto; // 拍正面照片或者侧面照片
    private CameraManager mCameraManager;
    private int mMaxRetryCount = 1; //前置摄像头最多自动尝试拍照的次数
    private int mCurRetryCount = 0; //前置摄像头最多 当前重试的次数
    public PhotoCommandFragment() {
    }

    /**
     * @param isFrontTakePhoto is photo front
     * @param isBackDireation
     */
    @SuppressLint("ValidFragment")
    public PhotoCommandFragment(boolean isFrontTakePhoto, boolean isBackDireation) {
        this.isFrontTakePhoto = isFrontTakePhoto;
        this.isBackDireation = isBackDireation;
    }
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_photo_command;
    }

    private SensorManager sensorManager;
    private Sensor magneticSensor;
    private Sensor accelerometerSensor;
    private Sensor gyroscopeSensor;

// 将纳秒转化为秒

    private static final float NS2S = 1.0f / 1000000000.0f;

    private float timestamp;

    private float angle[] =new float[3];

    @Bind(R.id.tv_direction)
    TextView tvDirection;
    @Override
    protected void initData() {
        initShape();
        mCameraContainer.bindActivity((BaseActivity) getActivity(), isFrontTakePhoto);
        mCameraManager = CameraManager.getInstance(getActivity());
//        mCameraManager.bindOptionMenuView(mFlashLight, tvDirection,mCameraContainer);
        mCameraManager.bindOptionMenuView(mFlashLight, null,mCameraContainer);
//        tvDirection.setVisibility(isFrontTakePhoto ? View.VISIBLE : View.GONE);

//        ibClose.setVisibility(SPUtils.getBooleanData("switch") ? View.VISIBLE : View.INVISIBLE);

        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        magneticSensor =sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        accelerometerSensor =sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gyroscopeSensor =sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        //初始化数组
        values = new float[3];//用来保存最终的结果
        gravity = new float[3];//用来保存加速度传感器的值
        r = new float[9];//
        geomagnetic = new float[3];//用来保存地磁传感器的值

        timeDownView.setOnTimeDownListener(new TimeDownView.DownTimeWatcher() {
            @Override
            public void onTime(int num) {

            }

            @Override
            public void onLastTime(int num) {

            }

            @Override
            public void onLastTimeFinish(int num) {
                mCameraContainer.takePicture();
            }
        });

        mCameraContainer.setOnDirectionChangeListener(new SquareCameraContainer.OnDirectionChangeListener() {
            @Override
            public void directionChange(boolean isFromFront) {
                SPUtils.savaIntData("SP_CAMERA_DIRECTION", mCameraManager.getCameraDirection().ordinal());
                isBackDireation = mCameraManager.getCameraDirection().ordinal() == CameraManager.CameraDirection.CAMERA_BACK.ordinal();
            }
        });

        if(!isFrontTakePhoto && !isBackDireation){
            autoTimer();
        }
        restartRegist();
    }

    @Override
    protected void initData(View view) {

    }

    private float[] values, r, gravity, geomagnetic;

    private void initShape() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), getBmpRes());
        bitmap.getHeight();
        int height = BaseApp.getHeight() - DensityUtil.dip2px(170);

        int relWidth = bitmap.getWidth();
        int relHeight = bitmap.getHeight();

        if(relHeight > height){
            relHeight = height;
            relWidth = (int) (bitmap.getWidth() * relHeight / (float)bitmap.getHeight() + 0.5f);
        }

        llVertical.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, relHeight));
        ivShapeBg.setLayoutParams(new LinearLayout.LayoutParams(relWidth,relHeight + 3));
        ivShapeBg.setScaleType(ImageView.ScaleType.CENTER_CROP);
        v1.setLayoutParams(new LinearLayout.LayoutParams((BaseApp.getWidth() - relWidth )/2, ViewGroup.LayoutParams.MATCH_PARENT));
        v2.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        ivShapeBg.setImageBitmap(bitmap);
    }

    /**
     * 获取照片的模板
     * @return
     */
    @DrawableRes
    private int getBmpRes(){
        if(isFrontTakePhoto){
            return R.drawable.camera_front_male;
        }else{
            if(isBackDireation){        //后置摄像头和前置使用不同的照片模板
                return R.drawable.camera_side_male;
            }else{
                return R.drawable.camera_side_male_1;
            }
        }
    }

    @OnClick({ R.id.ib_take_photo, R.id.tv_flashlight,R.id.tv_direction,R.id.ib_close})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_close:
                if (getActivity() != null) {
                    if (getActivity() instanceof FrontTailorActivity) {
                        ((FrontTailorActivity) getActivity()).openAlbumChoosePic();
                    } else if (getActivity() instanceof SideTailorActivity) {
                        ((SideTailorActivity) getActivity()).openAlbumChoosePic();
                    }
                }
                break;
            case R.id.ib_take_photo:
                takePhotoing();
                if(isBackDireation){
                    mCameraContainer.takePicture();
                }else{
                    mCurRetryCount = 0;
                    autoTimer();
                }
                break;
            case R.id.tv_flashlight:
                mCameraContainer.switchFlashMode();
                break;
            case R.id.tv_direction:
                mCameraContainer.switchCamera();
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        restartPreview();
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//    }

    @Override
    public void onPause() {
        super.onPause();
        stopPreview();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mCameraManager != null) {
            mCameraManager.unbinding();
            mCameraManager.releaseActivityCamera();
        }
    }

    public void restartPreview() {
        if (mCameraContainer != null)
            mCameraContainer.onStart();
    }

    public void stopPreview(){
        if (mCameraContainer != null)
            mCameraContainer.onStop();
    }

    private int mSenceDegree = 20;
    private int fontDegree = 20;
    private int sideDegree = 5;
    private int backDegree = 5;
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            geomagnetic = event.values;
        }
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            gravity = event.values;
            // r从这里返回
            SensorManager.getRotationMatrix(r, null, gravity, geomagnetic);
            //values从这里返回
            SensorManager.getOrientation(r, values);
            //提取数据
            double azimuth = Math.toDegrees(values[0]);
            if (azimuth<0) {
//                azimuth=azimuth+360;
            }
            double pitch = Math.toDegrees(values[1]);
            double roll = Math.toDegrees(values[2]);

            if(pitch < -70 ){
                relTips.setVisibility(View.INVISIBLE);
                mTakePhoto.setClickable(true);
                mTakePhoto.setBackgroundResource(R.drawable.camera_btn_pass);

            }else{
                relTips.setVisibility(View.VISIBLE);
                mTakePhoto.setClickable(false);
                mTakePhoto.setBackgroundResource(R.drawable.camera_btn_pass_white);

                isTimering = false;
                mCurRetryCount =  0;
                timeDownView.cancel();
            }
//            System.out.println("======= start ========= : " + azimuth + "  \\  " + pitch + "  \\  " + roll);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public boolean isTimering; //是否在倒计时中
    /**
     * 正面拍照的时候自动倒计时
     */
    protected void autoTimer(){
        if(!isBackDireation && !isTimering && mCurRetryCount <= mMaxRetryCount){
            isTimering = true;
            timeDownView.downSecond(needDownSecond());
        }
    }

    /**
     * 需要倒计时的时间
     * @return  正面拍照 9秒 侧面5秒
     */
    protected int needDownSecond(){
        return isFrontTakePhoto ? 9 : 5;
    }

    public void resetFrontCameraParams() {
        isTimering = false;
        mCurRetryCount ++;
        if(mCurRetryCount <= mMaxRetryCount){
            autoTimer();
        }else{
            SnackBarUtil.showShortSnackbar(ivShapeBg,"请调整好姿势重新点击按钮拍照");
        }
    }

    /**
     *重新开始注册监听
     */
    public void restartRegist(){
        if(sensorManager != null){
            sensorManager.registerListener(this,gyroscopeSensor, SensorManager.SENSOR_DELAY_UI);
            sensorManager.registerListener(this,magneticSensor, SensorManager.SENSOR_DELAY_UI);
            sensorManager.registerListener(this,accelerometerSensor, SensorManager.SENSOR_DELAY_UI);
        }
        relTips.setVisibility(View.INVISIBLE);
        mTakePhoto.setClickable(true);
        mTakePhoto.setBackgroundResource(R.drawable.camera_btn_pass);
    }

    /**
     * 正在拍照检测中
     */
    public void takePhotoing(){
//        sensorManager.unregisterListener(this);
        relTips.setVisibility(View.INVISIBLE);
//        mTakePhoto.setClickable(false);
        mTakePhoto.setBackgroundResource(R.drawable.camera_btn_pass_white);
    }
}
