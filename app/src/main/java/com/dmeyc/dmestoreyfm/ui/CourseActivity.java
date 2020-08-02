package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dl7.player.media.IjkPlayerView;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.ProductPageAdapter;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.TeachCourseBean;
import com.dmeyc.dmestoreyfm.bean.TeachDetailBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.tamic.novate.Throwable;

import java.util.ArrayList;

public class CourseActivity extends BaseActivity {
    private IjkPlayerView player_view;
    private ViewPager viewpager;
    private ListView lv_courselist;
    Uri mUri;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_course;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {


        viewpager=(ViewPager)mRootView. findViewById(R.id.viewpager_player);
        lv_courselist=(ListView)mRootView. findViewById(R.id.lv_courselist);

        setData();

        WindowManager wm = this.getWindowManager();

        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        RelativeLayout.LayoutParams rl=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,400);
        viewpager.setLayoutParams(rl);
        ArrayList<String> mProductPicLists = new ArrayList<>();
        mProductPicLists.add("http://www.520mylove.com/1.mp4");
        mProductPicLists.add("http://www.520mylove.com/1.mp4");
        mProductPicLists.add("http://www.520mylove.com/1.mp4");
        mProductPicLists.add("http://www.520mylove.com/1.mp4");
        viewpager.setAdapter(new PlayerPagerAdapter(mProductPicLists));
        lv_courselist.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 10;
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }
            ViewHoder viewHoder;
            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                if(view==null){
                    view=  getLayoutInflater().inflate(R.layout.adapter_listitem,null);
                    viewHoder=new ViewHoder(view);
                    view.setTag(viewHoder);
                }else {
                    viewHoder=(ViewHoder) view.getTag();
                }
                viewHoder.tv_gobuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int viewid=view.getId();
                        if(R.id.tv_gobuy==viewid){
                            startActivity(new Intent(CourseActivity.this,GoBuyInforActivity.class));
                        }
                    }
                });

                return view;
            }
        });

    }

    private void setData() {

        RestClient.getYfmNovate(this).post(Constant.API.YFM_TEACHCOURSE, new ParamMap.Build()
                        .addParams("user_token",SPUtils.getStringData(Constant.Config.TOKEN))
//                        .addParams("course_id", getIntent().getStringExtra("groupid")).build(),
                        .addParams("course_id", 36).build(),
                new DmeycBaseSubscriber<TeachCourseBean>(this) {

                    @Override
                    public void onSuccess(final TeachCourseBean bean) {
                      ToastUtil.show(bean.getMsg());
                    }
                    @Override
                    public void onError(Throwable e) {
//                mBaseView.requestDataError();
                    }
                });

    }


    public class PlayerPagerAdapter extends PagerAdapter {
        ArrayList<String> mProductPicLists;

        public PlayerPagerAdapter(ArrayList<String> mProductPicLists) {
            this.mProductPicLists = mProductPicLists;
        }

        @Override
        public int getCount() {
            return mProductPicLists.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }


        @Override
        public Object instantiateItem(final View container, final int position) {
//            View view = View.inflate(container.getContext(), R.layout.adapter_teachcomment, null);

            View view = View.inflate(container.getContext(), R.layout.adapterpager_player, null);
//
            player_view=(IjkPlayerView)view.findViewById(R.id.player_view);
                    mUri = Uri.parse(mProductPicLists.get(position));
                     player_view.init()
                  .setVideoPath(mUri)
                  .setMediaQuality(IjkPlayerView.MEDIA_QUALITY_HIGH)
                   .enableDanmaku()
                   .start();



//            ImageView iv = (ImageView) view.findViewById(R.id.iv);
//
//            GlideUtil.loadImage(container.getContext(),mProductPicLists.get(position),iv);
//
//            iv.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(container.getContext(), ShowPicActivity.class);
//                    intent.putExtra(Constant.Config.POSITION,position);
//                    intent.putStringArrayListExtra("pics",mProductPicLists);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    container.getContext().startActivity(intent);
//                }
//            });
            ((ViewPager) container).addView(view);
            return view;
        }
        @Override
        public void destroyItem(View container, int position, Object view) {
            ((ViewPager) container).removeView((View) view);
        }
    }

    public class ViewHoder{

        public TextView tv_gobuy;
        public ViewHoder(View view){
            tv_gobuy=view.findViewById(R.id.tv_gobuy);
        }

    }
}
