package com.dmeyc.dmestoreyfm.api;

import com.dmeyc.dmestoreyfm.bean.BankListBean;
import com.dmeyc.dmestoreyfm.bean.MemberListBean;
import com.dmeyc.dmestoreyfm.bean.NewMemberListBean;
import com.dmeyc.dmestoreyfm.bean.ProjectTypeBean;
import com.dmeyc.dmestoreyfm.bean.WXSubmitBean;
import com.dmeyc.dmestoreyfm.bean.YFMLoginBean;
import com.dmeyc.dmestoreyfm.bean.response.AccountBean;
import com.dmeyc.dmestoreyfm.bean.response.AccountInfoBean;
import com.dmeyc.dmestoreyfm.bean.response.ActivityDetailBean;
import com.dmeyc.dmestoreyfm.bean.response.AddCommentBean;
import com.dmeyc.dmestoreyfm.bean.response.AnchorLivesBean;
import com.dmeyc.dmestoreyfm.bean.response.BaseRespBean;
import com.dmeyc.dmestoreyfm.bean.response.CommentParentBean;
import com.dmeyc.dmestoreyfm.bean.response.GoodsBean;
import com.dmeyc.dmestoreyfm.bean.response.GoodsDetailBean;
import com.dmeyc.dmestoreyfm.bean.response.GroupActionBean;
import com.dmeyc.dmestoreyfm.bean.response.GroupDetailBean;
import com.dmeyc.dmestoreyfm.bean.response.GroupMemberBean;
import com.dmeyc.dmestoreyfm.bean.response.LivingBannerBean;
import com.dmeyc.dmestoreyfm.bean.response.LivingListBean;
import com.dmeyc.dmestoreyfm.bean.response.MyClubBean;
import com.dmeyc.dmestoreyfm.bean.response.PayDetailBean;
import com.dmeyc.dmestoreyfm.bean.response.RelationBean;
import com.dmeyc.dmestoreyfm.bean.response.SearchHistoryBean;
import com.dmeyc.dmestoreyfm.bean.response.UploadImageCompressBean;
import com.dmeyc.dmestoreyfm.bean.response.UploadSingleImageBean;
import com.dmeyc.dmestoreyfm.bean.response.UserListBean;
import com.dmeyc.dmestoreyfm.bean.response.UserNoticeBean;
import com.dmeyc.dmestoreyfm.bean.response.VideoItemBean;
import com.dmeyc.dmestoreyfm.newui.update.UpdateResultBean;
import com.dmeyc.dmestoreyfm.ui.ProdListBean;
import com.dmeyc.dmestoreyfm.video.index.IndexDynamicBean;
import com.dmeyc.dmestoreyfm.video.index.IndexDynamicItemBean;
import com.dmeyc.dmestoreyfm.video.releasedynamic.GenSignBean;
import com.dmeyc.dmestoreyfm.video.systeminfo.ReadMsgModel;

import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * create by cxg on 2019/11/24
 */
public interface ApiService {
    /**
     * 意见反馈、举报
     *
     * @param setRequestBody
     * @return
     */
    @FormUrlEncoded
    @POST("api/v1.0/userContent/")
    Observable<BaseRespBean> report(@FieldMap Map<String, String> setRequestBody);

    /**
     * 发送验证码
     *
     * @param setRequestBody
     * @return
     */
    @FormUrlEncoded
    @POST("api/v1.0/user/sendValidCode/")
    Observable<BaseRespBean> getCode(@FieldMap Map<String, String> setRequestBody);


    /**
     * 获取视频列表
     *
     * @param setRequestBody
     * @return
     */
    @GET("api/v1.0/topic/video/listVideo")
    Observable<IndexDynamicBean> getListVideo(@QueryMap Map<String, String> setRequestBody);

    /**
     * 转发视频获取url
     */
    @GET("api/v1.0/topic/share/video/{groupId}")
    Observable<ReadMsgModel> shareVideo(@QueryMap Map<String, String> setRequestBody);

    /**
     * 我的银行卡列表
     *
     * @param setRequestBody
     * @return
     */
    @FormUrlEncoded
    @POST("api/v1.0/user/queryUserBankList/")
    Observable<BankListBean> getBandCardList(@FieldMap Map<String, String> setRequestBody);

    /**
     * 上传图片
     */
    @Multipart
    @POST("api/v1.0/file/uploadSingle")
    Observable<UploadSingleImageBean> uploadImage(@Part MultipartBody.Part part, @QueryMap Map<String, String> map);

    /**
     * 上传图片带压缩
     */
    @Multipart
    @POST("api/v1.0/file/uploadSingle")
    Observable<UploadImageCompressBean> uploadImageCompress(@Part MultipartBody.Part part, @QueryMap Map<String, String> map);

    /**
     * 保单
     */
    @FormUrlEncoded
    @POST("api/v1.0/singup/getSafeList/")
    Observable<ProdListBean> getSaveList(@FieldMap Map<String, String> setRequestBody);

    /**
     * 成为俱乐部群主
     */
    @FormUrlEncoded
    @POST("api/v1.0/group/groupApply/")
    Observable<BaseRespBean> groupApply(@FieldMap Map<String, String> setRequestBody);

    /**
     * 商家入驻
     */
    @FormUrlEncoded
    @POST("api/v1.0/business/businessApply/")
    Observable<BaseRespBean> businessApply(@FieldMap Map<String, String> setRequestBody);

    /**
     * 教练入驻
     */
    @FormUrlEncoded
    @POST("api/v1.0/coach/coachApply/")
    Observable<BaseRespBean> coachApply(@FieldMap Map<String, String> setRequestBody);

    /**
     * 主播入驻
     */
    @FormUrlEncoded
    @POST("api/v1.0/group/archorApply/")
    Observable<BaseRespBean> archorApply(@FieldMap Map<String, String> setRequestBody);

    /**
     * 登入
     */
    @FormUrlEncoded
    @POST("api/v1.0/user/AppFastlogin/")
    Observable<YFMLoginBean> login(@FieldMap Map<String, String> setRequestBody);

    /**
     * 是否绑定该openId
     */
    @FormUrlEncoded
    @POST("api/v1.0/user/isBindOpenId/")
    Observable<YFMLoginBean> checkBindOpenId(@FieldMap Map<String, String> setRequestBody);

    /**
     * 检查版本更新
     */
    @FormUrlEncoded
    @POST("api/v1.0/version/selectNewVersionUrl/")
    Observable<UpdateResultBean> updateVersion(@FieldMap Map<String, String> setRequestBody);



    /**
     * 第三方登入
     */
    @FormUrlEncoded
    @POST("api/v1.0/user/thirdPartyRegister/")
    Observable<YFMLoginBean> thirdPartLogin(@FieldMap Map<String, String> setRequestBody);

    /**
     * 退出登入
     */
    @FormUrlEncoded
    @POST("api/v1.0/user/logout/")
    Observable<BaseRespBean> logout(@FieldMap Map<String, String> setRequestBody);

    /**
     * 我的活动记录
     */
    @FormUrlEncoded
    @POST("api/v1.0/activity/queryMyActivityList/")
    Observable<GroupActionBean> queryMyActivityList(@FieldMap Map<String, String> setRequestBody);

    /**
     * 群主根据状态查询创建的活动
     */
    @FormUrlEncoded
    @POST("api/v1.0/activity/queryMyCreateActivity/")
    Observable<GroupActionBean> queryMyCreateActivity(@FieldMap Map<String, String> setRequestBody);

    /**
     * 关联活动赛事
     */
    @FormUrlEncoded
    @POST("api/v1.0/activity/listLinkedActivitys/")
    Observable<GroupActionBean> listLinkedActivitys(@FieldMap Map<String, String> setRequestBody);

    /**
     * 首页活动
     */
    @FormUrlEncoded
    @POST("api/v1.0/activity/queryActivityList/")
    Observable<GroupActionBean> queryActivityList(@FieldMap Map<String, String> setRequestBody);

    /**
     * 发布视频列表
     */
    @FormUrlEncoded
    @POST("api/v1.0/topic/video/listMyPublishVideo/")
    Observable<IndexDynamicBean> listMyPublishVideo(@FieldMap Map<String, String> setRequestBody);

    /**
     * 喜欢视频列表
     */
    @FormUrlEncoded
    @POST("api/v1.0/topic/video/listMyLikedVideo/")
    Observable<IndexDynamicBean> listMyLikedVideo(@FieldMap Map<String, String> setRequestBody);

    /**
     * 58、主播获取自己所能直播的列表
     */
    @FormUrlEncoded
    @POST("api/v1.0/broadcast/list/")
    Observable<IndexDynamicBean> listUserVideo(@FieldMap Map<String, String> setRequestBody);

    /**
     * 评论点赞
     */
    @FormUrlEncoded
    @POST("api/v2.0/comment/like/addLike/")
    Observable<BaseRespBean> addCommentLike(@FieldMap Map<String, String> setRequestBody);

    /**
     * 取消评论点赞
     */
    @FormUrlEncoded
    @POST("api/v2.0/comment/like/delLike/")
    Observable<BaseRespBean> delCommentLike(@FieldMap Map<String, String> setRequestBody);

    /**
     * 视频点赞
     */
    @FormUrlEncoded
    @POST("api/v1.0/topic/video/likeorstore/addLikeOrStore/")
    Observable<BaseRespBean> addVideoLike(@FieldMap Map<String, String> setRequestBody);

    /**
     * 取消视频点赞
     */
    @FormUrlEncoded
    @POST("api/v1.0/topic/video/likeorstore/delLikeOrStore/")
    Observable<BaseRespBean> delVideoLike(@FieldMap Map<String, String> setRequestBody);

    /**
     * 查看用户钱包
     */
    @GET("api/v1.0/user/account/")
    Observable<AccountBean> getAccount(@QueryMap Map<String, String> setRequestBody);

    /**
     * 提现
     */
    @FormUrlEncoded
    @POST("api/v1.0/user/account/")
    Observable<BaseRespBean> withdraw(@FieldMap Map<String, String> setRequestBody);

    /**
     * 查询可添加银行卡类型
     */
    @FormUrlEncoded
    @POST("api/v1.0/dic/getDicItemByKey/")
    Observable<ProjectTypeBean> getDicItem(@FieldMap Map<String, String> setRequestBody);

    /**
     * 查询可添加银行卡类型
     */
    @FormUrlEncoded
    @POST("api/v1.0/user/addUserBankCard/")
    Observable<BaseRespBean> addUserBankCard(@FieldMap Map<String, String> setRequestBody);

    /**
     * 获取我的基本信息
     */
    @FormUrlEncoded
    @POST("api/v1.0/user/queryUserInfoByUserId/")
    Observable<AccountInfoBean> queryUserInfo(@FieldMap Map<String, String> setRequestBody);

    /**
     * 实名认证
     */
    @FormUrlEncoded
    @POST("api/v1.0/user/savedIdNo/")
    Observable<BaseRespBean> savedIdNo(@FieldMap Map<String, String> setRequestBody);

    /**
     * 发布赛事
     */
    @FormUrlEncoded
    @POST("api/v1.0/activity/releaseMatch/")
    Observable<BaseRespBean> releaseMatch(@FieldMap Map<String, String> setRequestBody);

    /**
     * 发布活动
     */
    @FormUrlEncoded
    @POST("api/v1.0/activity/releaseActivity/")
    Observable<BaseRespBean> releaseActivity(@FieldMap Map<String, String> setRequestBody);

    /**
     * 发布图片、视频
     */
    @FormUrlEncoded
    @POST("api/v1.0/topic/video/addVideo/")
    Observable<BaseRespBean> publishDynamic(@FieldMap Map<String, String> setRequestBody);

    /**
     * 发布商品、课程
     */
    @FormUrlEncoded
    @POST("api/v1.0/commodity/publish/")
    Observable<BaseRespBean> publish(@FieldMap Map<String, String> setRequestBody);


    @GET("api/v1.0/sign/video/generator/")
    Observable<GenSignBean> getGenSign(@QueryMap Map<String, String> setRequestBody);

    /**
     * 通过videoId获取视频详情
     *
     * @param setRequestBody
     * @return
     */
    @GET("api/v1.0/topic/video/videodetail/{videoId}")
    Observable<IndexDynamicItemBean> videoDetail(@Path("videoId") String videoId, @QueryMap Map<String, String> setRequestBody);

    /**
     * 查看评论
     */
    @GET("api/v1.0/topic/video/comment/listComment/")
    Observable<CommentParentBean> listComment(@QueryMap Map<String, String> setRequestBody);

    /**
     * 发表评论
     */
    @FormUrlEncoded
    @POST("api/v1.0/topic/video/comment/addComment/")
    Observable<AddCommentBean> addComment(@FieldMap Map<String, String> setRequestBody);

    /**
     * 群活动详情
     */
    @FormUrlEncoded
    @POST("api/v1.0/activity/getActivityDetail/")
    Observable<ActivityDetailBean> getActivityDetail(@FieldMap Map<String, String> setRequestBody);

    /**
     * 热门搜索关键词
     */
    @FormUrlEncoded
    @POST("api/v1.0/searchKeyword/list/")
    Observable<SearchHistoryBean> searchHotKey(@FieldMap Map<String, String> setRequestBody);

    /**
     * 67、根据关键字搜索用户
     */
    @FormUrlEncoded
    @POST("api/v1.0/user/listArchorList/")
    Observable<UserListBean> listArchorList(@FieldMap Map<String, String> setRequestBody);

    /**
     * 67、根据关键字搜索我的好友用户
     */
    @FormUrlEncoded
    @POST("api/v1.0/user/listFollowUser/")
    Observable<UserListBean> listFollowUser(@FieldMap Map<String, String> setRequestBody);

    /**
     * 68、根据关键字获取产品
     */
    @FormUrlEncoded
    @POST("api/v1.0/commodity/listByPattern/")
    Observable<GoodsBean> listByPattern(@FieldMap Map<String, String> setRequestBody);

    /**
     * 68、获取自己的产品
     */
    @FormUrlEncoded
    @POST("api/v1.0/commodity/listByUserAndType/")
    Observable<GoodsBean> listByUserAndType(@FieldMap Map<String, String> setRequestBody);

    /**
     * 68、关注
     */
    @FormUrlEncoded
    @POST("api/v1.0/user/followUser/")
    Observable<BaseRespBean> followUser(@FieldMap Map<String, String> setRequestBody);

    /**
     * 68、取消关注
     */
    @FormUrlEncoded
    @POST("api/v1.0/user/cancelFollowUser/")
    Observable<BaseRespBean> cancelFollowUser(@FieldMap Map<String, String> setRequestBody);

    /**
     * 68、获取关联 1、俱乐部、3、教练、5、合作商户
     */
    @FormUrlEncoded
    @POST("api/v1.0/group/listLinkedGroups/")
    Observable<RelationBean> listLinkedGroups(@FieldMap Map<String, String> setRequestBody);

    /**
     * 68、查询我的俱乐部
     */
    @FormUrlEncoded
    @POST("api/v1.0/group/queryMyGroup/")
    Observable<MyClubBean> queryMyGroup(@FieldMap Map<String, String> setRequestBody);

    /**
     * 68、查询我的俱乐部详情
     */
    @FormUrlEncoded
    @POST("api/v1.0/group/viewGroupById/")
    Observable<GroupDetailBean> viewGroupById(@FieldMap Map<String, String> setRequestBody);

    /**
     * 68、退群
     */
    @FormUrlEncoded
    @POST("api/v1.0/group/quitGroup/")
    Observable<BaseRespBean> quitGroup(@FieldMap Map<String, String> setRequestBody);

    /**
     * 68、修改群信息
     */
    @FormUrlEncoded
    @POST("api/v1.0/group/editGroupInfo/")
    Observable<BaseRespBean> editGroupInfo(@FieldMap Map<String, String> setRequestBody);

    /**
     * 68、群主查看群成员
     */
    @FormUrlEncoded
    @POST("api/v1.0/group/queryGroupMemberList/")
    Observable<GroupMemberBean> queryGroupMemberList(@FieldMap Map<String, String> setRequestBody);

    /**
     * 68、修改信息
     */
    @FormUrlEncoded
    @POST("api/v1.0/user/editUserInfo/")
    Observable<BaseRespBean> editUserInfo(@FieldMap Map<String, String> setRequestBody);

    /**
     * 68、群主查看群成员
     */
    @FormUrlEncoded
    @POST("api/v1.0/im/user/notify/listByUserIdAndType/")
    Observable<UserNoticeBean> noticeInfo(@FieldMap Map<String, String> setRequestBody);
    /**
     * 68、用户详情费用明细
     */
    @FormUrlEncoded
    @POST("api/v1.0/singup/getSignUpActivityDetail/")
    Observable<PayDetailBean> payDetail(@FieldMap Map<String, String> setRequestBody);
    /**
     * 付款生成订单
     */
    @FormUrlEncoded
    @POST("api/v1.0/singup/signUpActivity1/")
    Observable<WXSubmitBean> signUpActivity1(@FieldMap Map<String, String> setRequestBody);
    /**
     * 视频转发
     */
    @GET("api/v1.0/topic/video/updateForwardCount/")
    Observable<BaseRespBean> updateForwardCount(@QueryMap Map<String, String> setRequestBody);
    /**
     * 视频查看
     */
    @GET("api/v1.0/topic/video/updateSeeCount/")
    Observable<BaseRespBean> updateSeeCount(@QueryMap Map<String, String> setRequestBody);
    /**
     * 所有群成员
     */
    @FormUrlEncoded
    @POST("api/v1.0/userGroupAccount/findAllMember/")
    Observable<NewMemberListBean> httpMembersData(@FieldMap Map<String, String> setRequestBody);
    /**
     * 加群
     */
    @FormUrlEncoded
    @POST("api/v1.0/group/joinGroup/")
    Observable<BaseRespBean> joinGroup(@FieldMap Map<String, String> setRequestBody);
    /**
     * 用户获取自己的主播列表
     */
    @FormUrlEncoded
    @POST("api/v1.0/broadcast/list/")
    Observable<AnchorLivesBean> broadcastList(@FieldMap Map<String, String> setRequestBody);
    /**
     * 用户获取自己的主播列表
     */
    @FormUrlEncoded
    @POST("api/v1.0/broadcast/add/")
    Observable<BaseRespBean> broadcastAdd(@FieldMap Map<String, String> setRequestBody);
    /**
     * 用户获取自己的主播列表
     */
    @FormUrlEncoded
    @POST("api/v1.0/dic/getDicItemByKey/")
    Observable<LivingBannerBean> getDicItemByKey(@FieldMap Map<String, String> setRequestBody);
    /**
     * 添加主播列表
     */
    @FormUrlEncoded
    @POST("api/v1.0/broadcast/addArchor/")
    Observable<BaseRespBean> addAnchor(@FieldMap Map<String, String> setRequestBody);
    /**
     * 获取某个活动的直播图片列表
     */
    @FormUrlEncoded
    @POST("api/v1.0/broadcast/listImages/")
    Observable<LivingListBean> listImages(@FieldMap Map<String, String> setRequestBody);
    /**
     * 获取主播列表
     */
    @FormUrlEncoded
    @POST("api/v1.0/broadcast/listArchorsByActivityId/")
    Observable<UserListBean> listArchorsByActivityId(@FieldMap Map<String, String> setRequestBody);
    /**
     * 删除播放图片
     */
    @FormUrlEncoded
    @POST("api/v1.0/broadcast/delete/")
    Observable<BaseRespBean> deleteBroadcast(@FieldMap Map<String, String> setRequestBody);

    /**
     * 获取商品详情
     */
    @FormUrlEncoded
    @POST("api/v1.0/commodity/getById/")
    Observable<GoodsDetailBean> getCommodityById(@FieldMap Map<String, String> setRequestBody);

    /**
     * 直播图片点赞
     */
    @FormUrlEncoded
    @POST("api/v1.0/broadcast/addLikeCount/")
    Observable<BaseRespBean> addLikeCount(@FieldMap Map<String, String> setRequestBody);

    /**
     * 添加浏览次数
     */
    @FormUrlEncoded
    @POST("api/v1.0/broadcast/addSeeCount/")
    Observable<BaseRespBean> addSeeCount(@FieldMap Map<String, String> setRequestBody);


    /**
     * 活动转发
     */
    @GET("api/v1.0/topic/share/getActivityDetailUrl/")
    Observable<ReadMsgModel> getActivityDetailUrl(@QueryMap Map<String, String> setRequestBody);
    /**
     * 赛事转发
     */
    @GET("api/v1.0/topic/share/getActivityEventUrl/")
    Observable<ReadMsgModel> getActivityEventUrl(@QueryMap Map<String, String> setRequestBody);
    /**
     * 直播二维码
     */
    @GET("api/v1.0/topic/share/liveBroadcastQrCode/")
    Observable<ReadMsgModel> liveBroadcastQrCode(@QueryMap Map<String, String> setRequestBody);
    /**
     * 直播H5
     */
    @GET("api/v1.0/topic/share/liveBroadcast/")
    Observable<ReadMsgModel> liveBroadcast(@QueryMap Map<String, String> setRequestBody);
}
