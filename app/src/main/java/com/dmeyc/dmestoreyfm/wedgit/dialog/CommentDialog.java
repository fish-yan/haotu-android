package com.dmeyc.dmestoreyfm.wedgit.dialog;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.CommentParentAdapter;
import com.dmeyc.dmestoreyfm.bean.response.CommentParentBean;
import com.dmeyc.dmestoreyfm.event.MyEvent;
import com.dmeyc.dmestoreyfm.newui.common.CommentRequestHelper;
import com.dmeyc.dmestoreyfm.newui.release.friend.AtFriendActivity;
import com.dmeyc.dmestoreyfm.utils.CommonUtil;
import com.dmeyc.dmestoreyfm.utils.DensityUtil;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.video.releasedynamic.TopicInEditBean;
import com.dmeyc.dmestoreyfm.wedgit.EmojiView;
import com.orhanobut.logger.Logger;
import com.sqk.emojirelease.Emoji;
import com.sqk.emojirelease.EmojiUtil;
import com.sqk.emojirelease.FaceFragment;
import com.xsm.library.TEditText;
import com.xsm.library.TObject;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * create by cxg on 2019/12/8
 */
public class CommentDialog extends Dialog implements View.OnClickListener, EmojiView.OnEmojiClickListener {
    private List<CommentParentBean.DataBean.CommentListBean> mCommentList = new ArrayList<>();
    private Context mContext;
    private CommentParentAdapter mAdapter;
    private TextView mTvTotalCount;
    private TEditText mEtContent;
    private InputMethodManager mImm;
    private ImageView mIvAt;
    private ImageView mIvEmoji;
    private RecyclerView mRvParent;
    private ICallBack iCallBack;
    private int mVideoPosition;
    private int mVideoId;
    private String mParentId = "0";

    private EmojiView mFlEmoji;
    private int mTotalCommentCount;

    private CommentDialog(@NonNull Context context) {
        super(context, R.style.Dialog);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View layout = LayoutInflater.from(mContext).inflate(R.layout.dialog_comment_new, null, false);
        setContentView(layout);
        mTvTotalCount = layout.findViewById(R.id.tv_commont_count);
        mEtContent = layout.findViewById(R.id.et_comment);
        mFlEmoji = layout.findViewById(R.id.fl_emoji);
        mFlEmoji.setListener(this);
        mImm = (InputMethodManager) mEtContent.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

        mAdapter = new CommentParentAdapter(mContext);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mEtContent.setHint("@ " + mCommentList.get(position).getUserNickName());
                mParentId = mCommentList.get(position).getId();
                mEtContent.requestFocus();
                mImm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
            }
        });
        mAdapter.setListener(new CommentParentAdapter.MyListener() {
            @Override
            public void onItemClick(String parentId, String nickName) {
                mEtContent.setHint("@ " + nickName);
                mParentId = parentId;
                mEtContent.requestFocus();
                mImm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
            }
        });
        mRvParent = layout.findViewById(R.id.rv_comment_parent);
        mRvParent.setLayoutManager(new LinearLayoutManager(mContext));
        mRvParent.setAdapter(mAdapter);

        layout.findViewById(R.id.iv_comment_close).setOnClickListener(this);


        initListener();

        setCancelable(true);
        setCanceledOnTouchOutside(true);

        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.height = DensityUtil.dip2px(490);
        lp.width = mContext.getResources().getDisplayMetrics().widthPixels;
        lp.gravity = Gravity.BOTTOM;
        getWindow().setAttributes(lp);

        CommentRequestHelper.httpCommentListData(mContext, mVideoId + "", "0", 1, new CommentRequestHelper.CallBackAdapter() {
            @Override
            public void onQueryListSucc(CommentParentBean.DataBean data) {
                mTvTotalCount.setText(data.getCount() + "条评论");
                mTotalCommentCount = data.getCount();
                mCommentList.addAll(data.getCommentList());
                mAdapter.replaceData(mCommentList);
            }
        });
    }

    private void initListener() {
        mEtContent.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    if (!CommonUtil.isLogin(mContext)) {
                        return true;
                    }

                    if (TextUtils.isEmpty(mEtContent.getText().toString())) {
                        ToastUtil.show("请输入评论内容");
                        return true;
                    }
                    if (mImm.isActive()) {
                        mImm.hideSoftInputFromWindow(mEtContent.getWindowToken(), 0);
                    }
                    CommentRequestHelper.httpCommentData(mContext, mVideoId + "", mParentId, mEtContent.getText().toString(), getAtSystemUser(), new CommentRequestHelper.CallBackAdapter() {

                        //评论成功
                        @Override
                        public void onAddCommentSucc(CommentParentBean.DataBean.CommentListBean data) {
                            Logger.d("onAddCommentSucc===============");
                            mEtContent.setText("");
                            mEtContent.clearFocus();
                            if (data != null) {
                                mTotalCommentCount++;
                                mTvTotalCount.setText(mTotalCommentCount + "条评论");
                                if ("0".equals(data.getParentId())) {
                                    mCommentList.add(0, data);
                                } else {
                                    for (int i = 0; i < mCommentList.size(); i++) {
                                        if (data.getParentId().equals(mCommentList.get(i).getId())) {
                                            List<CommentParentBean.DataBean.CommentListBean> commentListBean = mCommentList.get(i).getCommentListBean();
                                            if (commentListBean == null) {
                                                commentListBean = new ArrayList<CommentParentBean.DataBean.CommentListBean>();
                                            }
                                            commentListBean.add(data);
                                            mCommentList.get(i).setCommentListBean(commentListBean);
                                            mCommentList.get(i).setOpen(true);
                                            break;
                                        }
                                    }
                                }
                                mAdapter.replaceData(mCommentList);
                            }
                        }
                    });
//                    if (iCallBack!=null){
//                        iCallBack.onSendMessage(mVideoPosition,0,mEtContent.getText().toString());

//                    }
                    return true;
                }
                return false;
            }
        });
        mEtContent.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    mParentId = "0";
                    mEtContent.setHint("支持一下吧～");
                }
            }
        });

        findViewById(R.id.iv_at_to).setOnClickListener(this);
        findViewById(R.id.iv_emji).setOnClickListener(this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_comment_close:
                dismiss();
                break;
            case R.id.iv_at_to:
                if (!CommonUtil.isLogin(mContext)) {
                    return;
                }
                AtFriendActivity.newInstance(mContext, AtFriendActivity.TYPE_COMMENT);
                break;
            case R.id.iv_emji:
                if (isEmojiOpen) {
                    mFlEmoji.setVisibility(View.GONE);
                } else {
                    if (mImm.isActive()) {
                        mImm.hideSoftInputFromWindow(mEtContent.getWindowToken(), 0);
                    }
                    mFlEmoji.setVisibility(View.VISIBLE);
                }
                isEmojiOpen = !isEmojiOpen;
                break;
        }
    }

    private boolean isEmojiOpen = false;

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);

    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    // @ 好友
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAtFriend(MyEvent.ReleaseVideo event) {
        mEtContent.setObject(event.mTopicInEditBean);
    }

    /**
     * 设置topicId、atFriend、atSystem
     */
    private String getAtSystemUser() {
        StringBuffer sbSystemUser = new StringBuffer();

        List<TObject> mTopicList = mEtContent.getObjects();
        if (mTopicList.size() > 0) {
            for (int i = 0; i < mTopicList.size(); i++) {
                TObject tObject = mTopicList.get(i);
                if (tObject instanceof TopicInEditBean) {
                    TopicInEditBean bean = (TopicInEditBean) tObject;
                    switch (bean.getType()) {
                        case TopicInEditBean.TYPE_SYSTEM_FRIEND:
                            sbSystemUser.append(bean.getTopicId()).append(",");
                            break;
                    }
                }
            }
        }
        if (sbSystemUser.length() > 0) {

            return sbSystemUser.substring(0, sbSystemUser.length() - 1);
        }
        return null;
    }

    @Override
    public void onEmojiDelete() {
        int action = KeyEvent.ACTION_DOWN;
        int code = KeyEvent.KEYCODE_DEL;
        KeyEvent event = new KeyEvent(action, code);
        mEtContent.onKeyDown(KeyEvent.KEYCODE_DEL, event);
    }

    @Override
    public void onEmojiClick(Emoji emoji) {
        if (emoji != null) {
            int index = mEtContent.getSelectionStart();
            Editable editable = mEtContent.getEditableText();
            if (index < 0) {
                editable.append(emoji.getContent());
            } else {
                editable.insert(index, emoji.getContent());
            }
        }
        try {
            EmojiUtil.handlerEmojiText(mEtContent, mEtContent.getText().toString(), mContext);
            mEtContent.setSelection(mEtContent.getText().toString().length());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class Build {
        private CommentDialog mDialog;

        public Build(Context context) {
            mDialog = new CommentDialog(context);
        }

        public Build setCallBack(ICallBack iCallBack) {
            mDialog.iCallBack = iCallBack;
            return this;
        }

        public Build setVideoPosition(int position) {
            mDialog.mVideoPosition = position;
            return this;
        }

        public Build setVideoId(int videoId) {
            mDialog.mVideoId = videoId;
            return this;
        }

        public CommentDialog create() {
            return mDialog;
        }
    }


    public interface ICallBack {
        void onSendMessage(int mVideoPosition, int parentCommentId, String comment);
    }
}
