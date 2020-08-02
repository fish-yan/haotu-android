package com.dmeyc.dmestoreyfm.newui.menu.goods;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.constant.ExtraKey;
import com.dmeyc.dmestoreyfm.newbase.BaseActivity;
import com.dmeyc.dmestoreyfm.newui.common.goods.GoodsFragment;

/**
 * create by cxg on 2019/12/15
 */
public class MyCourseAndGoodsActivity extends BaseActivity {
    public static final String TYPE_GOODS = "1";
    public static final String TYPE_COURSE = "2";

    private String mType;

    public static void newInstance(Context context, String type) {
        Intent intent = new Intent(context, MyCourseAndGoodsActivity.class);
        intent.putExtra(ExtraKey.TYPE_FROM, type);
        context.startActivity(intent);
    }

    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @Override
    protected int setContentView() {
        return R.layout.activity_goods_and_course;

    }

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {
        mType = getIntent().getStringExtra(ExtraKey.TYPE_FROM);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        GoodsFragment goodsFragment;
        switch (mType) {
            case TYPE_GOODS:
                setTitle("发布的商品");
                goodsFragment = GoodsFragment.newInstance(GoodsFragment.TYPE_GOODS_MINE);
                break;
            case TYPE_COURSE:
                setTitle("发布的课程");
                goodsFragment = GoodsFragment.newInstance(GoodsFragment.TYPE_COURSE_MINE);
                break;
            default:
                throw new RuntimeException("MyCourseAndGoodsActivity 传入type 非法：" + mType);
        }
        ft.replace(R.id.fl_container, goodsFragment).commit();

    }
}
