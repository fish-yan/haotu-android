package com.dmeyc.dmestoreyfm.newui.common;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;

import com.dmeyc.dmestoreyfm.api.CustomSubscriber;
import com.dmeyc.dmestoreyfm.api.RetrofitService;
import com.dmeyc.dmestoreyfm.api.exception.ApiException;
import com.dmeyc.dmestoreyfm.bean.response.AccountInfoBean;
import com.dmeyc.dmestoreyfm.bean.response.AddCommentBean;
import com.dmeyc.dmestoreyfm.bean.response.BaseRespBean;
import com.dmeyc.dmestoreyfm.bean.response.CommentParentBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.constant.SPKey;
import com.dmeyc.dmestoreyfm.utils.CheckInfoUtil;
import com.dmeyc.dmestoreyfm.utils.DataClass;
import com.dmeyc.dmestoreyfm.utils.LoadingUtils;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.video.systeminfo.ReadMsgModel;

import java.util.HashMap;
import java.util.Map;

/**
 * create by cxg on 2019/12/9
 */
public class CommentRequestHelper {
    /**
     * 获取评论列表
     */
    public static void httpCommentListData(Context mContext, String id, String parentCommentId, int pageNo, final ICallBack iCallBack) {
        LoadingUtils.showProgressDialog(((Activity) mContext), "加载中。。。");
        CustomSubscriber subscriber = new CustomSubscriber<CommentParentBean>(null, null) {
            @Override
            public void onSuccess(CommentParentBean bean) {
                LoadingUtils.cancelProgressDialog();
                if (iCallBack != null) {
                    if (bean.getData() == null) {
                        ToastUtil.show("数据错误");
                    } else {
                        iCallBack.onQueryListSucc(bean.getData());
                    }
                }
            }

            @Override
            public void onError(ApiException ex) {
                super.onError(ex);
                LoadingUtils.cancelProgressDialog();
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("video_id", String.valueOf(id));
        params.put("parentId", String.valueOf(parentCommentId));
        params.put("sorttype", "1");// 1 按时间、2按点赞 排序
        params.put("page", String.valueOf(pageNo));
        params.put("size", "100");
        RetrofitService.getInstance().listComment(params, subscriber);
    }

    /**
     * 评论
     */
    public static void httpCommentData(Context context, String id, String parentCommentId, String comment, String atSystemUser, final ICallBack iCallBack) {
        LoadingUtils.showProgressDialog(((Activity) context), "评论中。。。");
        CustomSubscriber subscriber = new CustomSubscriber<AddCommentBean>(null, null) {
            @Override
            public void onSuccess(AddCommentBean bean) {
                LoadingUtils.cancelProgressDialog();
                if (iCallBack != null) {
                    iCallBack.onAddCommentSucc(bean.getData());
                }
            }

            @Override
            public void onError(ApiException ex) {
                super.onError(ex);
                LoadingUtils.cancelProgressDialog();
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("content", comment);
        params.put("video_id", id);
        params.put("parentId", parentCommentId);
        if (!TextUtils.isEmpty(atSystemUser)) {
            params.put("atSystemUser", atSystemUser);// @系统用户，多个以逗号隔开
        }

        RetrofitService.getInstance().addComment(params, subscriber);
    }

    /**
     * 发送验证码
     *
     * @param phoneNo
     */
    public static void httpCodeData(String phoneNo, final ICallBack iCallBack) {
        if (!CheckInfoUtil.isPhoneNo(phoneNo)) {
            return;
        }
        // TODO: 2019/12/13 loading
        CustomSubscriber subscriber = new CustomSubscriber<BaseRespBean>(null, null) {
            @Override
            public void onSuccess(BaseRespBean bean) {
                if (iCallBack != null) {
                    ToastUtil.show("验证码已发送");
                    iCallBack.onGetCodeSucc();
                }
            }
        };
        Map<String, String> params = new HashMap<>();
        params.put("phone_no", phoneNo);
        RetrofitService.getInstance().getCode(params, subscriber);
    }


    /**
     * 关注
     *
     * @param preType ,0 未关注，1已关注
     */
    public static void httpFollowData(Activity context, String userId, String preType, final ICallBack iCallBack) {
        LoadingUtils.showProgressDialog(context, "加载中");
        CustomSubscriber subscriber = new CustomSubscriber<BaseRespBean>(null, null) {
            @Override
            public void onSuccess(BaseRespBean bean) {
                if (iCallBack != null) {
                    iCallBack.onSuccess();
                }
                LoadingUtils.cancelProgressDialog();
            }

            @Override
            public void onError(ApiException ex) {
                super.onError(ex);
                LoadingUtils.cancelProgressDialog();
            }
        };
        Map<String, String> params = new HashMap<>();
        params.put("followerUserId", userId);

        if ("0".equals(preType)) {
            RetrofitService.getInstance().followUser(params, subscriber);
        } else if ("1".equals(preType)) {
            RetrofitService.getInstance().cancelFollowUser(params, subscriber);
        }
    }

    /**
     * 分享首页视频
     */
    public static void share(String videoId, final ICallBack iCallBack) {
        CustomSubscriber subscriber = new CustomSubscriber<ReadMsgModel>(null, null) {
            @Override
            public void onSuccess(ReadMsgModel bean) {
                if (iCallBack != null) {
                    iCallBack.onSuccess(bean.getData());
                }
            }
        };
        Map<String, String> params = new HashMap<>();
        params.put("video_id", videoId);
        RetrofitService.getInstance().shareVideo(params, subscriber);
    }


    // 转发
    public static void httpRequestForward(String videoId) {
        CustomSubscriber subscriber = new CustomSubscriber<BaseRespBean>(null, null) {
            @Override
            public void onSuccess(BaseRespBean bean) {
            }
        };
        Map<String, String> params = new HashMap<>();
        params.put("videoId", videoId);
        RetrofitService.getInstance().updateForwardCount(params, subscriber);
    }

    //添加视频浏览量
    public static void httpRequestVideoNum(String videoId) {
        CustomSubscriber subscriber = new CustomSubscriber<BaseRespBean>(null, null) {
            @Override
            public void onSuccess(BaseRespBean bean) {
            }
        };
        Map<String, String> params = new HashMap<>();
        params.put("videoId", videoId);
        RetrofitService.getInstance().updateSeeCount(params, subscriber);
    }

    //拿到实名认证结果
    public static void httpAuthentication(final ICallBack iCallBack) {
        CustomSubscriber subscriber = new CustomSubscriber<AccountInfoBean>(null, null) {
            @Override
            public void onSuccess(AccountInfoBean bean) {
                if (iCallBack != null) {
                    if (bean.getData() != null) {
                        String isRealName = bean.getData().getIsRealName();
                        SPUtils.savaStringData(SPKey.AUTHENTICATION_RESULT + SPUtils.getStringData(Constant.Config.USER_ID), isRealName);
                        iCallBack.onSuccess(isRealName);
                    }
                }
            }
        };
        RetrofitService.getInstance().queryUserInfo(new HashMap<String, String>(), subscriber);
    }

    // 视频点赞、取消点赞
    public static void onLikeOrNot(String id, final int position, final boolean isUnLikePre, final ICallBack iCallBack) {
        CustomSubscriber subscriber = new CustomSubscriber<BaseRespBean>(null, null) {
            @Override
            public void onSuccess(BaseRespBean bean) {
                if (iCallBack != null) {
                    iCallBack.onLikeSucc(position, isUnLikePre);
                }
            }

            @Override
            public void onError(ApiException ex) {
                super.onError(ex);
                if (iCallBack != null) {
                    iCallBack.onLikeFail(position, isUnLikePre);
                }
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("video_id", id);
        params.put("type", "1");// 1 点赞，2 收藏
        if (isUnLikePre) {
            RetrofitService.getInstance().addVideoLike(params, subscriber);
        } else {
            RetrofitService.getInstance().delVideoLike(params, subscriber);
        }
    }

    /**
     * 直播图片点赞
     */
    public static void onLivingLickOrNot(String id, final int position, final boolean isUnLikePre, final ICallBack iCallBack) {
        CustomSubscriber subscriber = new CustomSubscriber<BaseRespBean>(null, null) {
            @Override
            public void onSuccess(BaseRespBean bean) {
                iCallBack.onLikeSucc(position, isUnLikePre);
            }
        };
        Map<String, String> params = new HashMap<>();
        params.put("id", id);
        // type 1、点赞 0、取消点赞
        if (isUnLikePre) {
            params.put("type", "1");
        } else {
            params.put("type", "0");
        }
        RetrofitService.getInstance().addLikeCount(params, subscriber);
    }

    public static class CallBackAdapter implements ICallBack {

        @Override
        public void onQueryListSucc(CommentParentBean.DataBean data) {

        }

        @Override
        public void onAddCommentSucc(CommentParentBean.DataBean.CommentListBean data) {

        }

        @Override
        public void onGetCodeSucc() {

        }

        @Override
        public void onSuccess() {

        }

        @Override
        public void onSuccess(String string) {

        }

        @Override
        public void onLikeFail(int position, boolean isUnLikePre) {

        }

        @Override
        public void onLikeSucc(int position, boolean isUnLikePre) {

        }
    }

    public interface ICallBack {

        void onQueryListSucc(CommentParentBean.DataBean data);

        void onAddCommentSucc(CommentParentBean.DataBean.CommentListBean data);

        void onGetCodeSucc();

        void onSuccess();

        void onSuccess(String string);

        void onLikeFail(int position, boolean isUnLikePre);

        void onLikeSucc(int position, boolean isUnLikePre);
    }
}
