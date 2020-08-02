package com.dmeyc.dmestoreyfm.newui.menu.club.qrcode;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.constraint.ConstraintLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.constant.ExtraKey;
import com.dmeyc.dmestoreyfm.newbase.BaseActivity;
import com.dmeyc.dmestoreyfm.newbase.BaseMvpActivity;
import com.dmeyc.dmestoreyfm.utils.BitmapUtils;
import com.dmeyc.dmestoreyfm.utils.DateUtil;
import com.dmeyc.dmestoreyfm.utils.FileUtil;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.tbruyelle.rxpermissions.RxPermissions;

import butterknife.Bind;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * create by cxg on 2019/11/30
 */
public class ClubQrCodeActivity extends BaseActivity {
    @Bind(R.id.constraintLayout)
    ConstraintLayout constraintLayout;

    public static void newInstance(Context context, String groupLogo, String groupName, String qrCode) {
        Intent intent = new Intent(context, ClubQrCodeActivity.class);
        intent.putExtra(ExtraKey.GROUP_LOGO, groupLogo);
        intent.putExtra(ExtraKey.GROUP_NAME, groupName);
        intent.putExtra(ExtraKey.GROUP_QR_CODE, qrCode);
        context.startActivity(intent);
    }


    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @Override
    protected int setContentView() {
        return R.layout.activity_club_qrcode;
    }

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {
        setTitle("俱乐部二维码");
        Intent intent = getIntent();
        ((TextView) findViewById(R.id.tv_group_name)).setText(intent.getStringExtra(ExtraKey.GROUP_NAME));
        GlideUtil.loadImage(this, intent.getStringExtra(ExtraKey.GROUP_LOGO), ((ImageView) findViewById(R.id.iv_logo)));
        GlideUtil.loadImage(this, intent.getStringExtra(ExtraKey.GROUP_QR_CODE), ((ImageView) findViewById(R.id.iv_qrcode)));
    }

    @OnClick(R.id.tv_save_qrcode)
    public void click() {
        RxPermissions.getInstance(this).request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean granted) {
                if (granted) {
                    Bitmap bitmap = BitmapUtils.convertViewToBitmap(constraintLayout);
                    BitmapUtils.saveBitmap(bitmap,true);
                } else {
                    ToastUtil.show(Manifest.permission.WRITE_EXTERNAL_STORAGE
                            + "权限被拒绝");
                }
            }
        });
    }
}
