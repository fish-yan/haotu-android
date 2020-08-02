package com.dmeyc.dmestoreyfm.api;

import android.text.TextUtils;

import com.dmeyc.dmestoreyfm.api.interceptor.LoggingInterceptor;
import com.dmeyc.dmestoreyfm.base.BaseApp;
import com.dmeyc.dmestoreyfm.config.JumpAddress;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.utils.FileUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.Util;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Cache;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * create by cxg on 2019/11/24
 */
public class RetrofitService {

    private static RetrofitService retrofitService;
    private static ApiService mApiService;

    public static final int TIME_OUT = 120;

    private RetrofitService() {
    }

    public static RetrofitService getInstance() {
        if (retrofitService == null) {
            synchronized (RetrofitService.class) {
                if (retrofitService == null) {
                    retrofitService = new RetrofitService();
                }
            }
        }
        return retrofitService;
    }

    /**
     * 初始化网络通信服务
     */
    public void init() {
        // 指定缓存路径,缓存大小100Mb
        Cache cache = new Cache(new File(BaseApp.getContext().getCacheDir(), "HttpCache"),
                1024 * 1024 * 100);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().cache(cache)
                .retryOnConnectionFailure(true)
//                .addInterceptor(REWRITE_BASE_URL_INTERCEPTOR)
                .addInterceptor(new LoggingInterceptor())
//                .addInterceptor(LOGGING_INTERCEPTOR)
//                .addInterceptor(S_REWRITE_CACHE_CONTROL_INTERCEPTOR)
//                .addNetworkInterceptor(S_REWRITE_CACHE_CONTROL_INTERCEPTOR)
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .sslSocketFactory(com.dmeyc.dmestoreyfm.utils.CommonUtil.overlockCardSSLContext().getSocketFactory())
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(JumpAddress.getBaseUrl())
                .build();
        mApiService = retrofit.create(ApiService.class);
    }


    private void setObservable(Observable observable, Subscriber subscriber) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 设置公共请求参数
     *
     * @param params
     * @return
     */
    private Map<String, String> setRequestBody(Map<String, String> params) {
        params.put("user_token", SPUtils.getStringData(Constant.Config.TOKEN));
        if (!TextUtils.isEmpty(Util.getUserId())) {
            params.put(Constant.Config.USER_ID, Util.getUserId());
        }

        // TODO: 2019/12/10 关闭日志
        String jsonContent = new Gson().toJson(params);
        Logger.json(jsonContent);

        return params;
    }

    private Map<String, String> setRequestBodyWithOutId(Map<String, String> params) {
        params.put("user_token", SPUtils.getStringData(Constant.Config.TOKEN));

        // TODO: 2019/12/10 关闭日志
        String jsonContent = new Gson().toJson(params);
        Logger.json(jsonContent);

        return params;
    }


    /* ----------------------------   请求的API   ----------------------------*/

    /**
     * 意见反馈、举报
     */
    public void report(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.report(setRequestBody(params)), subscriber);
    }

    /**
     * 获取验证码
     */
    public void getCode(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.getCode(setRequestBody(params)), subscriber);
    }

    public void getListVideo(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.getListVideo(setRequestBody(params)), subscriber);
    }

    public void getBandCardList(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.getBandCardList(setRequestBody(params)), subscriber);
    }

    public void getSaveList(Map params, Subscriber subscriber) {
        setObservable(mApiService.getSaveList(setRequestBody(params)), subscriber);

    }

    public void groupApply(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.groupApply(setRequestBody(params)), subscriber);
    }

    public void businessApply(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.businessApply(setRequestBody(params)), subscriber);
    }

    public void coachApply(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.coachApply(setRequestBody(params)), subscriber);
    }

    public void archorApply(Map<String, String> params, CustomSubscriber subscriber) {
        setObservable(mApiService.archorApply(setRequestBody(params)), subscriber);
    }

    /**
     * 上传图片不带压缩
     */
    public void uploadImage(String filePath, Subscriber subscriber) {
        File file = FileUtil.compressFile( new File(filePath));
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
        Map<String, String> params = new HashMap<>();
        params.put("isLogo", "1");
        params.put("isZoom", "0");


        params.put("user_token", SPUtils.getStringData(Constant.Config.TOKEN));
        setObservable(mApiService.uploadImage(filePart, params), subscriber);
    }

    /**
     * 上传图片带压缩
     */
    public void uploadImageNeedCompress(String filePath, Subscriber subscriber) {
        File file = new File(filePath);
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
        Map<String, String> params = new HashMap<>();
        params.put("isLogo", "0");
        params.put("isZoom", "1");
        params.put("width", "400");
        params.put("height", "400");
        params.put("user_token", SPUtils.getStringData(Constant.Config.TOKEN));
        setObservable(mApiService.uploadImageCompress(filePart, params), subscriber);
    }


    /**
     * 退出登入
     */
    public void logout(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.logout(setRequestBody(params)), subscriber);
    }

    /**
     * 我的活动
     */
    public void queryMyActivityList(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.queryMyActivityList(setRequestBody(params)), subscriber);
    }

    /**
     * 群主根据状态查询创建的活动
     */
    public void queryMyCreateActivity(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.queryMyCreateActivity(setRequestBody(params)), subscriber);
    }

    /**
     * 群主根据状态查询创建的活动
     */
    public void listLinkedActivitys(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.listLinkedActivitys(setRequestBody(params)), subscriber);
    }

    public void queryActivityList(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.queryActivityList(setRequestBody(params)), subscriber);
    }

    public void addCommentLike(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.addCommentLike(setRequestBody(params)), subscriber);
    }

    public void delCommentLike(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.delCommentLike(setRequestBody(params)), subscriber);
    }

    public void addVideoLike(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.addVideoLike(setRequestBody(params)), subscriber);
    }

    public void delVideoLike(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.delVideoLike(setRequestBody(params)), subscriber);
    }

    public void getAccount(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.getAccount(setRequestBody(params)), subscriber);
    }

    public void withdraw(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.withdraw(setRequestBody(params)), subscriber);
    }

    public void getDicItem(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.getDicItem(setRequestBody(params)), subscriber);
    }

    public void addUserBankCard(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.addUserBankCard(setRequestBody(params)), subscriber);
    }

    public void queryUserInfo(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.queryUserInfo(setRequestBody(params)), subscriber);
    }

    public void savedIdNo(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.savedIdNo(setRequestBody(params)), subscriber);
    }

    public void releaseMatch(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.releaseMatch(setRequestBody(params)), subscriber);

    }

    public void publishDynamic(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.publishDynamic(setRequestBody(params)), subscriber);
    }

    public void getGenSign(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.getGenSign(setRequestBody(params)), subscriber);
    }

    public void releaseActivity(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.releaseActivity(setRequestBody(params)), subscriber);

    }

    public void listComment(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.listComment(setRequestBody(params)), subscriber);
    }

    public void addComment(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.addComment(setRequestBody(params)), subscriber);
    }

    public void getActivityDetail(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.getActivityDetail(setRequestBody(params)), subscriber);
    }

    public void publish(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.publish(setRequestBody(params)), subscriber);
    }

    public void login(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.login(setRequestBody(params)), subscriber);

    }

    public void thirdPartLogin(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.thirdPartLogin(setRequestBody(params)), subscriber);
    }

    public void checkBindOpenId(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.checkBindOpenId(setRequestBody(params)), subscriber);
    }

    /**
     * 版本更新
     * @param params
     * @param subscriber
     */
    public void updateVersion(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.updateVersion(setRequestBody(params)), subscriber);
    }


    public void listMyPublishVideo(Map params, Subscriber subscriber) {
        setObservable(mApiService.listMyPublishVideo(setRequestBodyWithOutId(params)), subscriber);

    }

    public void listMyLikedVideo(Map params, Subscriber subscriber) {
        setObservable(mApiService.listMyLikedVideo(setRequestBodyWithOutId(params)), subscriber);
    }

    public void listUserVideo(Map params, Subscriber subscriber) {
        setObservable(mApiService.listUserVideo(setRequestBody(params)), subscriber);
    }

    public void searchHotKey(Map params, Subscriber subscriber) {
        setObservable(mApiService.searchHotKey(setRequestBody(params)), subscriber);
    }

    public void listArchorList(Map params, Subscriber subscriber) {
        setObservable(mApiService.listArchorList(setRequestBody(params)), subscriber);

    }

    public void listFollowUser(Map params, Subscriber subscriber) {
        setObservable(mApiService.listFollowUser(setRequestBody(params)), subscriber);
    }

    public void listByPattern(Map params, Subscriber subscriber) {
        setObservable(mApiService.listByPattern(setRequestBody(params)), subscriber);
    }

    public void listByUserAndType(Map params, Subscriber subscriber) {
        setObservable(mApiService.listByUserAndType(setRequestBody(params)), subscriber);
    }

    public void followUser(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.followUser(setRequestBody(params)), subscriber);
    }

    public void cancelFollowUser(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.cancelFollowUser(setRequestBody(params)), subscriber);
    }

    public void listLinkedGroups(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.listLinkedGroups(setRequestBody(params)), subscriber);

    }

    public void queryMyGroup(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.queryMyGroup(setRequestBody(params)), subscriber);
    }

    public void viewGroupById(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.viewGroupById(setRequestBody(params)), subscriber);
    }

    public void quitGroup(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.quitGroup(setRequestBody(params)), subscriber);
    }

    public void editGroupInfo(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.editGroupInfo(setRequestBody(params)), subscriber);
    }

    public void queryGroupMemberList(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.queryGroupMemberList(setRequestBody(params)), subscriber);
    }

    public void shareVideo(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.shareVideo(setRequestBody(params)), subscriber);
    }

    public void editUserInfo(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.editUserInfo(setRequestBody(params)), subscriber);
    }

    public void noticeInfo(Map params, Subscriber subscriber) {
        setObservable(mApiService.noticeInfo(setRequestBody(params)), subscriber);
    }

    public void videoDetail(String videoId, Subscriber subscriber) {
        setObservable(mApiService.videoDetail(videoId, setRequestBody(new HashMap<String, String>())), subscriber);

    }

    public void payDetail(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.payDetail(setRequestBody(params)), subscriber);
    }

    public void signUpActivity1(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.signUpActivity1(setRequestBody(params)), subscriber);
    }

    public void updateForwardCount(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.updateForwardCount(setRequestBody(params)), subscriber);
    }

    public void updateSeeCount(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.updateSeeCount(setRequestBody(params)), subscriber);
    }

    public void httpMembersData(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.httpMembersData(setRequestBody(params)), subscriber);
    }

    public void joinGroup(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.joinGroup(setRequestBody(params)), subscriber);
    }

    public void broadcastList(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.broadcastList(setRequestBody(params)), subscriber);
    }

    public void broadcastAdd(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.broadcastAdd(setRequestBody(params)), subscriber);
    }

    public void getDicItemByKey(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.getDicItemByKey(setRequestBody(params)), subscriber);
    }

    public void addAnchor(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.addAnchor(setRequestBody(params)), subscriber);
    }

    public void listArchorsByActivityId(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.listArchorsByActivityId(setRequestBody(params)), subscriber);
    }

    public void listImages(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.listImages(setRequestBody(params)), subscriber);
    }

    public void deleteBroadcast(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.deleteBroadcast(setRequestBody(params)), subscriber);
    }

    public void getActivityDetailUrl(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.getActivityDetailUrl(setRequestBody(params)), subscriber);
    }

    public void getActivityEventUrl(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.getActivityEventUrl(setRequestBody(params)), subscriber);
    }

    public void liveBroadcast(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.liveBroadcast(setRequestBody(params)), subscriber);
    }

    public void liveBroadcastQrCode(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.liveBroadcastQrCode(setRequestBody(params)), subscriber);
    }

    public void getCommodityById(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.getCommodityById(setRequestBody(params)), subscriber);
    }

    public void addLikeCount(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.addLikeCount(setRequestBody(params)), subscriber);
    }

    public void addSeeCount(Map<String, String> params, Subscriber subscriber) {
        setObservable(mApiService.addSeeCount(setRequestBody(params)), subscriber);
    }




    /* ---------------------------- 请求的API  end----------------------------*/


}
