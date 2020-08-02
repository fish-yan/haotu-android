package com.dmeyc.dmestoreyfm.fragment.look;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseFragment;
import com.dmeyc.dmestoreyfm.bean.FindListBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.ui.look.FindDetailActivity;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import me.yuqirong.cardswipelayout.CardConfig;
import me.yuqirong.cardswipelayout.CardItemTouchHelperCallback;
import me.yuqirong.cardswipelayout.CardLayoutManager;
import me.yuqirong.cardswipelayout.OnSwipeListener;

public class FindFragment extends BaseFragment {

    private List<FindListBean.DataBean> list = new ArrayList<>();

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    private ItemTouchHelper touchHelper;

    private boolean isMove = true;
    private boolean isLike = false;
    private int flag_position = 0;


    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_find;
    }

    @Override
    protected void initData() {
        getData();

    }

    private void getData() {
        String useId = SPUtils.getStringData(Constant.Config.USER_ID, "67");
        HashMap<String, Object> map = new HashMap<>();
        map.put("member", useId);//未修改
        RestClient.getNovate(getActivity()).get(Constant.API.DISCOVER_LIST, map, new DmeycBaseSubscriber<FindListBean>() {
            @Override
            public void onSuccess(FindListBean bean) {
                list.clear();
                list.addAll(bean.getData());
                initRecyclerView();
            }
        });
    }

    private void initRecyclerView() {
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(new MyAdapter());
        final CardItemTouchHelperCallback cardCallback = new CardItemTouchHelperCallback(recyclerView.getAdapter(), list);
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        isMove = false;
                        break;
                    case MotionEvent.ACTION_UP:
                        if (!isMove) {
                            if (flag_position <= list.size()) {
                                Intent intent = new Intent();
                                intent.putExtra("detailId", list.get(flag_position).getId());
                                intent.putExtra("flag_position",flag_position);
                                intent.setClass(getActivity(), FindDetailActivity.class);
                                startActivity(intent);
                            }
                        }
                        break;
                }

                return false;
            }

        });
        cardCallback.setOnSwipedListener(new OnSwipeListener<FindListBean.DataBean>() {

            @Override
            public void onSwiping(RecyclerView.ViewHolder viewHolder, float ratio, int direction) {
                if (ratio == 0.0) {
                    isMove = false;
                } else {
                    isMove = true;
                }
                MyAdapter.MyViewHolder myHolder = (MyAdapter.MyViewHolder) viewHolder;
                viewHolder.itemView.setAlpha(1 - Math.abs(ratio) * 0.2f);
                if (direction == CardConfig.SWIPING_LEFT) {
                    myHolder.dislikeImageView.setAlpha(Math.abs(ratio));
                } else if (direction == CardConfig.SWIPING_RIGHT) {
                    myHolder.likeImageView.setAlpha(Math.abs(ratio));
                } else {
                    myHolder.dislikeImageView.setAlpha(0f);
                    myHolder.likeImageView.setAlpha(0f);
                }
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, FindListBean.DataBean dataBean, int direction) {
                MyAdapter.MyViewHolder myHolder = (MyAdapter.MyViewHolder) viewHolder;
                viewHolder.itemView.setAlpha(1f);
                myHolder.dislikeImageView.setAlpha(0f);
                myHolder.likeImageView.setAlpha(0f);
                flag_position++;
                Toast.makeText(getContext(), direction == CardConfig.SWIPED_LEFT ? "swiped left" : "swiped right", Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onSwipedClear() {
                Toast.makeText(getContext(), "data clear", Toast.LENGTH_SHORT).show();
                recyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getData();
//                        recyclerView.getAdapter().notifyDataSetChanged();
                    }
                }, 3000L);
            }

        });

        touchHelper = new ItemTouchHelper(cardCallback);
        final CardLayoutManager cardLayoutManager = new CardLayoutManager(recyclerView, touchHelper);
        recyclerView.setLayoutManager(cardLayoutManager);
        touchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    protected void initData(View view) {

    }

    private class MyAdapter extends RecyclerView.Adapter {
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.section_fragment_look, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            RoundedImageView avatarImageView = ((MyViewHolder) holder).avatarImageView;
            RoundedImageView img_head = ((MyViewHolder) holder).img_head;
            TextView text_title = ((MyViewHolder) holder).text_title;
            TextView text_name = ((MyViewHolder) holder).text_name;
            LinearLayout ll_like = ((MyViewHolder) holder).ll_like;
            final ImageView img_like = ((MyViewHolder) holder).img_like;
            GlideUtil.loadImage(getActivity(), list.get(position).getImageList().get(0).getSource(), avatarImageView);
            GlideUtil.loadImage(getActivity(), list.get(position).getAvatar(), img_head);
            text_title.setText(list.get(position).getIntroduction());
            text_name.setText(list.get(position).getName());
            isLike = list.get(position).isIsLike();
            if (isLike) {
                img_like.setImageResource(R.drawable.ic_like_orange);
            } else {
                img_like.setImageResource(R.drawable.ic_like_black);
            }
            ll_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("name", SPUtils.getStringData(Constant.Config.NICK_NAME, ""));
                    map.put("avatar", SPUtils.getStringData(Constant.Config.AVATAR, ""));
                    map.put("member", SPUtils.getStringData(Constant.Config.USER_ID, ""));
                    map.put("attend", list.get(position).getMember());
                    map.put("images", list.get(position).getImages());
                    RestClient.getNovate(getActivity()).get(Constant.API.DISCOVER_IS_LIKE, map, new DmeycBaseSubscriber<Object>() {
                        @Override
                        public void onSuccess(Object bean) {
                            if (isLike) {
                                img_like.setImageResource(R.drawable.ic_like_black);
                                isLike = false;
                            } else {
                                img_like.setImageResource(R.drawable.ic_like_orange);
                                isLike = true;
                            }
                        }
                    });
                }
            });

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            RoundedImageView avatarImageView;
            RoundedImageView img_head;
            ImageView likeImageView;
            ImageView dislikeImageView;
            ImageView img_like;
            TextView text_title;
            TextView text_name;
            LinearLayout ll_like;

            MyViewHolder(View itemView) {
                super(itemView);
                avatarImageView = (RoundedImageView) itemView.findViewById(R.id.iv_avatar);
                img_head = (RoundedImageView) itemView.findViewById(R.id.img_head);
                likeImageView = (ImageView) itemView.findViewById(R.id.iv_like);
                dislikeImageView = (ImageView) itemView.findViewById(R.id.iv_dislike);
                img_like = itemView.findViewById(R.id.img_like);
                text_title = itemView.findViewById(R.id.text_title);
                text_name = itemView.findViewById(R.id.text_name);
                ll_like = itemView.findViewById(R.id.ll_like);
            }

        }
    }

}
