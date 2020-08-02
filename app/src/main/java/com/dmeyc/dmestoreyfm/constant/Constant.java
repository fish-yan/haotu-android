package com.dmeyc.dmestoreyfm.constant;

import com.dmeyc.dmestoreyfm.config.JumpAddress;

import java.sql.Struct;

/**
 * Created by jockie on 2017/9/8
 * Email:jockie911@gmail.com
 */
public class Constant {

    public static final String SHARES_COLOURFUL_NEWS = "shares_colourful_news";
    public static final String NIGHT_THEME_MODE = "night_theme_mode";

    public static class API {
        public static String BASE_URL = "https://storeapi.91moshow.com:8078/shop-controller/";
        //        public static String BASE_URL = "https://www.hotu.club:9595/";
        public static String YFM_SHAREBASE_URL = "http://www.hotu.club:9595/";
        //public static String YFM_BASE_URL = "http://192.168.0.104:1234/";
        public static String YFM_BASE_URL = JumpAddress.getBaseUrl();
//      public static String YFM_BASE_URL = "http://192.168.0.115:1234/";
//      public static String YFM_BASE_URL = "http://139.196.203.76:9595/";
//      public static String YFM_BASE_URL = "http://www.hotu.club:9595/";
//      public static String YFM_BASE_URL = "http://www.hotu.club:9595/";
//      public static String YFM_BASE_URL = "http://101.44.2.178:2019/";

        //      public static String YFM_BASE_URL = "http://47.100.204.154:1234/";
//      public static String YFM_BASE_URL = "http://101.44.2.178:1234/";
//      public static String YFM_BASE_URL = "http://118.89.36.69:2019/";
        public static String YFMBIGACTION_URL = JumpAddress.getBaseUrl();

        public static String UPLOAD_IMAGE_URL = "http://47.100.204.154:1234/";

        public static final String YFM_USER_LOGIN = "api/v1.0/user/login";
        public static final String YFM_USER_FASTLOGIN = "api/v1.0/user/AppFastlogin";

        public static final String YFM_USER_RGISET = "api/v1.0/user/register";
        public static final String YFM_USER_SEND_CODE = "api/v1.0/user/sendValidCode";
        public static final String YFM_TREUNAME = "api/v1.0/user/savedIdNo";
        public static final String YFM_CHECKTRUENAME = "api/v1.0/user/isSavedIdNo";
        public static final String YFM_GETCHECKNAME = "api/v1.0/user/queryUserInfoByUserId";
        public static final String YFM_CHANGETRUENAME = "api/v1.0/user/editUserInfo";
        public static final String YFM_CHANGECOMMINFO = "api/v1.0/group/editGroupInfo";
        public static final String YFM_CHANGECOMBACK = "api/v1.0/group/editGroupInfoAndRemark";

        public static final String YFM_GETPROMOTION = "/api/v1.0/couponUser/";
        public static final String YFM_GETBCPROMOTION = "/api/v1.0/inviteReward/";
        public static final String YFM_USER_INFO = "api/v1.0/user/queryUserInfoAllByUserId";
        public static final String YFM_PKCGET = "api/v1.0/pkinfo/getChallengeDetail";
        public static final String YFM_SHARE_H5 = "api/v1.0/h5Share/";
        public static final String YFM_GETH5 = "api/v1.0/h5Share/";
        public static final String YFM_CHANGEH5SHARE = "api/v1.0/h5Share/update";

        public static final String YFM_GETCOMMINFORBYID = "api/v1.0/group/viewGroupInfo";
        public static final String YFM_GETH5TEAMP = "api/v1.0/h5Template/";
        public static final String YFM_GETLEADERLIST = "api/v1.0/userGroupAccount/findAllManager";
        public static final String YFM_REVERTO = "api/v1.0/userContent/";
        public static final String YFM_GETMYCERATGROP = "api/v1.0/group/queryMyGroup";

        public static final String YFM_ALLACTION = "api/v1.0/activity/queryMyActivityList";
        public static final String YFM_BCLIENALLACTION = "api/v1.0/activity/queryMyCreateActivity";
        public static final String YFM_GETCOMMINFO = "api/v1.0/singup/signUpActivityDetail";

        public static final String YFM_STOPACTION = "api/v1.0/activity/pauseActivity";
        public static final String YFM_STARTCTION = "api/v1.0/activity/openActivity";
        public static final String YFM_CANCELACTION = "api/v1.0/activity/deleteActivity";
        public static final String YFM_SPECIALACTION = "api/v1.0/government/activity/get";
        public static final String YFM_COMMHOSTBAOMING = "api/v1.0/government/activity/joinActivity";

        public static final String YFM_YUE = "api/v1.0/user/account/";
        public static final String YFM_TIXIAN = "api/v1.0/user/account/";

        public static final String YFM_TIJIAOCTION = "api/v1.0/government/activity/endPkActivity";
        public static final String YFM_kaiqiCTION = "api/v1.0/government/activity/startPkActivity";
        public static final String YFM_GETBIGRECORD = "api/v1.0/pkinfo/viewPkGovernmentActivityMatch";


        public static final String YFM_HERORANK = "api/v1.0/group/queryGroupRank";
        public static final String YFM_SYSTEMRESULT = "api/v1.0/government/activity/gerResults";
        public static final String YFM_HERORHISRORY = "api/v1.0/pkinfo/queryPKInfoHistory";
        public static final String YFM_CLUBRESULT = "api/v1.0/match/viewActivityMatchDetail";
        public static final String YFM_BRESUT = "api/v1.0/match/viewScoreEntryByTeamId";

        public static final String YFM_ACTUIBRABK = "api/v1.0/rank/findActivityRank";

        public static final String YFM_GETRECORD = "api/v1.0/pkinfo/viewPkActivityMatch";
        public static final String YFM_GETNOPKRECORD = "api/v1.0/match/viewActivityMatchById";
        public static final String YFM_TOSOCKIN = "api/v1.0/match/editGroupMatch";
        public static final String YFM_TOSOCKINPK = "api/v1.0/pkMatch/editGroupPKMatch";

        public static final String YFM_ADDPROMOTION = "api/v1.0/inviteReward/";
        public static final String YFM_USER_FORGETPASS = "api/v1.0/user/resetPassword";
        public static final String YFM_USER_CARLIST = "api/v1.0/user/queryUserBankList";
        public static final String YFM_USER_CHAREGNAME = "api/v1.0/user/editUserInfo";
        public static final String YFM_USER_OUTLOG = "api/v1.0/user/logout";
        public static final String YFM_Activity_RECORDLIST = "api/v1.0/activity/queryMyActivityList";
        public static final String YFM_COMMACTION = "api/v1.0/activity/queryActivityListByGroupId";
        public static final String YFM_QUERYBANKLIST = "api/v1.0/user/queryUserBankList";
        public static final String YFM_GETWHITER = "/api/v1.0/weather/";

        public static final String YFM_MACTHRESULTDETIAL = "api/v1.0/pkMatch/viewGroupPkinfo";
        public static final String YFM_PKRESULT = "api/v1.0/pkinfo/getPkDetail";

        public static final String YFM_MACTHRESULT = "api/v1.0/match/viewActivityMatch";
        public static final String YFM_CAUCULATEINFO = "api/v1.0/company/queryMyActivityMeasureList";

        public static final String YFM_GETTSTREE = "api/v1.0/dic/getstreetByProvinceCityArea";

        public static final String YFM_SUBMIT_GORPAPPLY = "api/v1.0/group/groupApply";
        public static final String YFM_ACTION_DATA = "api/v1.0/activity/queryActivityList";
        public static final String YFM_PLACEIN = "api/v1.0/venueapply";

        public static final String YFM_TEACH_LIST = "api/v1.0/coach/queryCoachList";
        public static final String YFM_MyPROGARD = "api/v1.0/singup/getSafeList";

        public static final String YFM_PLACE_LIST = "api/v1.0/venue/queryVenueList ";
        //        public static final String YFM_UPLOAD_HEADING = "api/v1.0/user/uploadUserImage";
        public static final String YFM_UPLOAD_HEADING = "api/v1.0/file/uploadSingle";
        public static final String YFM_PROJECTLIST = "api/v1.0/dic/getDicItemByKey";
        public static final String YFM_PROJECTIN = "api/v1.0/venue/venueApply";
        public static final String YFM_MYCOURSE = "api/v1.0/coach/queryMyCourseList";
        public static final String YFM_TEACHIN = "api/v1.0/coach/coachApply";
        public static final String YFM_BUSINESS = "api/v1.0/business/businessApply";

        //        public static final String YFM_CANCELIN = "api/v1.0/singup/cancelSignUpActivity";
        public static final String YFM_CANCELIN = "api/v1.0/singup/cancelSignUpActivity1";
        public static final String YFM_PROJECTPAYTYPE = "api/v1.0/user/queryPayMethodList";

        public static final String YFM_MYCOMM = "api/v1.0/group/queryMyGroupList";
        public static final String YFM_MYCOMMlist = "api/v1.0/group/queryMyGroup";
        public static final String YFM_MYCOMMJOIN = "api/v1.0/group/queryMyJoinGroupList";

        public static final String YFM_MYCOMMHISTORY = "api/v1.0/activity/getHistoricalActivityDetail";

        public static final String YFM_GETMYCOMMINFO = "api/v1.0/group/viewGroupById";
        public static final String YFM_ISOWNER = "api/v1.0/group/isGroupOwner1";
        public static final String YFM_FINDGROUPINFROBYUERID = "api/v1.0/user/queryUserInfoByUserId";
        public static final String YFM_GETCOMMINRO = "api/v1.0/group/queryMyGroupInfo";


        public static final String YFM_VIPMEMBER = "api/v1.0/group/queryGroupMemberList";
        public static final String YFM_VIPMEMBER_PRICECHAGER = "api/v1.0/group/editGroupMember";
        public static final String YFM_ACCOUNTDETAIL = "api/v1.0/group/queryGroupMemberAccountDetail";
        public static final String YFM_ACTIONPUBLISH = "api/v1.0/activity/releaseActivity";

        public static final String YFM_PKPUBLISH = "api/v1.0/pkinfo/pushGroupPkActivity";
        public static final String YFM_CHANGEPKPUBLISH = "api/v1.0/pkinfo/updatePkActivity";

        public static final String YFM_COURSEORDER = "api/v1.0/coach/viewCourseOrder";
        public static final String YFM_TEACHDETAIL = "api/v1.0/coach/viewCoach";
        public static final String YFM_PLACEINFO = "api/v1.0/venue/viewVenueInfo";
        public static final String YFM_TEACHCOURSE = "api/v1.0/coach/viewCoachCourse";
        public static final String YFM_ADDPLACECOMMENT = "api/v1.0/venue/comment/";

        public static final String YFM_COMMSETING = "api/v1.0/group/viewGroupById";
        public static final String YFM_COMMEDIT = "api/v1.0/group/editGroupInfo";
        public static final String YFM_COMMHEAD = "api/v1.0/group/uploadGroupImage";
        public static final String YFM_ACTIVITYDETAIL = "api/v1.0/activity/getActivityDetail";

        public static final String YFM_ACTIONINFOR = "api/v1.0/government/activity/getByActivityId";

        public static final String YFM_ACTIVITSET = "api/v1.0/member/queryTeamMemberList";
        //        public static final String YFM_ACTIVITBAOMING = "api/v1.0/singup/signUpActivity";
        public static final String YFM_ACTIVITBAOMING = "api/v1.0/singup/signUpActivity1";
        public static final String YFM_TOTRUENAME = "api/v1.0/user/savedIdNo";
        public static final String YFM_ACTIONTEAM = "/api/v1.0/singup/signUpActivityDetail";
        public static final String YFM_NOTISIGUP = "api/v1.0/member/queryTeamMemberNotify";
        public static final String YFM_COMMPERCANCHAT = "api/v1.0/group/isCanLookChatRecord";


        public static final String YFM_ACCOUNGROUPTLIST = "api/v1.0/userGroupAccount/getGroupAcountDetail";
        public static final String YFM_ACCOUNTLIST = "api/v1.0/user/queryUserAccountdetail";
        public static final String YFM_GROUPINFO = "api/v1.0/group/viewGroupInfo";
        public static final String YFM_CARELIST = "api/v1.0/user/queryUserBankList";
        public static final String YFM_GROUPMEMBER = "api/v1.0/group/queryGroupMemberList";
        public static final String YFM_GROUPNOSPEAK = "api/v1.0/im/group/ban/add";
        public static final String YFM_GROUPALLSPEAK = "api/v1.0/im/group/ban/remove";
        public static final String YFM_GetRotACTIONDET = "api/v1.0/activity/queryGroupDetailActivitys";


        public static final String YFM_GETGROUPMEBER = "api/v1.0/userGroupAccount/findAllMember";
        public static final String YFM_THINSLIST = "api/v1.0/company/queryMatterItemList";
        public static final String YFM_ADDSHOPCAR = "api/v1.0/company/addShop";
        public static final String YFM_GETSHOPCAR = "api/v1.0/company/findShop";
        public static final String YFM_SUBMITORDER = "api/v1.0/company/addActivityMeasure";

        public static final String YFM_GETCOMM = "api/v1.0/group/queryMyGroupByProject";

        public static final String YFM_TOCHALANGE = "api/v1.0/pkinfo/challengeGroupPkActivity";
        public static final String YFM_SUBMISPORT = "api/v1.0/company/updateActivityMeasure";
        public static final String YFM_NOSPEAK = "api/v1.0/im/group/gag/add";

        public static final String YFM_NOMANAGER = "api/v1.0/userGroupAccount/cancelManager";
        public static final String YFM_ADDMANAGER = "api/v1.0/userGroupAccount/setToManager";

        public static final String YFM_KIPGROUP = "api/v1.0/group/kickGroup";

        public static final String YFM_ALLSPEAK = "api/v1.0/im/group/gag/remove";

        public static final String YFM_JOINCOMM = "api/v1.0/group/joinGroup";
        public static final String YFM_OUTCOMM = "api/v1.0/group/quitGroup";
        public static final String YFM_ATTENT = "api/v1.0/group/followGroup";

        public static final String YFM_PKLIST = "api/v1.0/pkinfo/queryGroupPkList";
        public static final String YFM_DELETACTON = "api/v1.0/activity/deleteActivity";
        public static final String YFM_INITACION = "api/v1.0/activity/queryActivityEditData";
        public static final String YFM_CHANGESET = "api/v1.0/member/queryTeamMemberList";
        public static final String YFM_CHANGEd = "api/v1.0/member/editTeamMember";
        public static final String YFM_CONNNOTICE = "api/v1.0/activity/queryActivityNoticeList";

        public static final String YFM_GROUPMEMVER = "api/v1.0/userGroupAccount/findAllMember";

        public static final String YFM_CHECKAPPLY = "api/v1.0/group/findGroupJoinExamineByUserId";
        public static final String YFM_GETBIGACTIONLIST = "api/v1.0/government/activity/listSignUpGroups";
        public static final String YFM_HOSTAPPLY = "api/v1.0/group/groupJoinExamine";

        public static final String YFM_BIGACTIONAPPLY = "api/v1.0/government/activity/handleAccept";
        public static final String YFM_CHALANGEAPPLY = "api/v1.0/pkinfo/handlePkChallenge";

        public static final String YFM_CHALANGELIST = "api/v1.0/pkinfo/getChallengeGroups";

        public static final String YFM_ACTIONUPDAT = "api/v1.0/activity/updateActivity";
        public static final String YFM_ACTIONUPDATPK = "api/v1.0/pkinfo/updatePkActivity";
        public static final String YFM_PUBLISHPK = "api/v1.0/pkinfo/pushGroupPkActivity";
        public static final String YFM_PUBLISHCOURSE = "api/v1.0/coach/pushCourse";
        public static final String YFM_UPDATACOURSE = "api/v1.0/activity/updateCourse";
        public static final String YFM_DELETCOURSE = "api/v1.0/activity/deleteActivity";
        public static final String YFM_DELETpk = "api/v1.0/pkinfo/deletePkActivity";
        public static final String YFMVERSION_UP = "api/v1.0/version/selectNewVersionUrl";
        public static final String YFM_ACTIONRECORDIN = "api/v1.0/match/viewActivityMatchById";
        public static final String YFM_PKRECORDIN = "api/v1.0/pkMatch/viewGroupPkinfo";

        public static final String YFM_CHAGERACTION = "api/v1.0/match/editGroupMatch";
        public static final String YFM_CHAGERPK = "api/v1.0/pkMatch/editGroupPKMatch";
        public static final String YFM_UPLODEIAMGES = "api/v1.0/activity/image/uploadGroupActivityImage";
        public static final String YFM_GETACTIONIMAGES = "api/v1.0/activity/image/getGroupActivityImage";
        public static final String YFM_DELETPKACTION = "api/v1.0/pkinfo/deletePkActivity";//删除PK活动
        public static final String YFM_HERO = "api/v1.0/group/queryGroupRank";//英雄榜
        public static final String YFM_PKINFO = "api/v1.0/pkinfo/joinGroupPkActivity";//英雄榜
        public static final String YFM_ADDCAR = "api/v1.0/user/addUserBankCard";//添加银行卡
        public static final String YFM_CARDCONFORIM = "api/v1.0/user/addBankCardConfirm";//添加银行卡

        public static final String YFM_ADDACTIVITYMIMAGE = "api/v1.0/user/addUserBankCard";//确认添加
        public static final String YFM_THERIDBUND = "api/v1.0/user/isBindOpenId";
        public static final String YFM_THIRDLOG = "api/v1.0/user/thirdPartyRegister";

        public static final String YFM_GETACTIONMEMBER = "api/v1.0/activity/getSignupIconPage";
        public static final String PkMACTCHRESULT = "api/v1.0/pkinfo/viewPkActivityMatch";
        public static final String YFM_GETPKBANNER = "api/v1.0/pkinfo/queryGroupPkIndex";
        public static final String YFM_GETBANNER = "api/v1.0/dic/getDicItemByKey";
        public static final String VERSION = "user/version_controller";
        public static final String VERSION_UP = "user/version_msg";
        public static final String USER_LOGIN = "user/userLogin"; //用户登陆
        public static final String USER_INFOUPDATE = "user/updatePersonInfo"; //用户信息修改
        public static final String RECOMMEND = "show/recommend";   //首页推荐
        public static final String SHARE_REGISTER = "share_register"; //分享
        public static final String SHARE_BRESULT = "fenxiang/resultGame/resultGame.html?token=3dc2e136ad8e4d0ebf8998c0e3e45f08"; //分享
        public static final String SHARE_CHERODETEAL = "fenxiang/heroList/heroesList.html?token=3dc2e136ad8e4d0ebf8998c0e3e45f08"; //分享
        public static final String SHARE_ACTIONITEM = "fenxiang/Tosign_up/activityMsg.html?token=3dc2e136ad8e4d0ebf8998c0e3e45f08"; //分享
        public static final String SHARE_CHINFO = "fenxiang/shareH5/index.html?token=3dc2e136ad8e4d0ebf8998c0e3e45f08"; //分享
        public static final String SHARE_ALLPKRESULT = "fenxiang/pkDetail/activityDetail.html?token=3dc2e136ad8e4d0ebf8998c0e3e45f08"; //分享
        public static final String SHARE_PKDETAIL = "fenxiang/pkList/index.html?token=3dc2e136ad8e4d0ebf8998c0e3e45f08"; //分享
        public static final String SHARE_HERORANK = "fenxiang/example/example.html?token=3dc2e136ad8e4d0ebf8998c0e3e45f08"; //分享
        public static final String SHARE_BIGACTION = "fenxiang/actpkList/index.html?token=3dc2e136ad8e4d0ebf8998c0e3e45f08"; //分享
        public static final String SHARE_BIGRECORD = "fenxiang/Promotion/promo.html?token=3dc2e136ad8e4d0ebf8998c0e3e45f08"; //分享


        public static final String STORE = "store/store_story";   //首页推荐

        public static final String LOOK = "show/look";         //   首页look
        public static final String CHOOSE_CHOSE = "show/chooseClothes";         //选衣
        public static final String CHOOSE_CATEGORY = "show/chooseClothes";
        public static final String CHOOSE_DETAIL = "show/choose_detail";
        public static final String ATTEND_SHOP = "attend/attendDesignerOrBrand";

        public static final String STORE_DETIAL = "store/showStoreDetail";

        public static final String BRAND_DESIGNER = "show/showBrand";         //品牌设计师
        public static final String ATTEND_PRODUCT = "attend/collectionProduct";         //关注商品
        public static final String ATTEND_DESIGNER = "attend/attendDesignerOrBrand";  //关注设计师品牌
        public static final String ADD_EDIT_ADDRESS = "addArea/addOrUpdateReceiverAddress";         //收货地址添加或修改接口
        public static final String ADD_DEFAULT_ADDRESS = "addArea/updateDefaultReceiverAddress";        //添加默认地址接口
        public static final String SHOW_RECEIVER_ADDRESS = "addArea/showReceiverAddress";         //地址管理列表
        public static final String DELETE_RECEIVER_ADDRESS = "addArea/delete_receiver_address";         //删除收货地址接口
        public static final String ADD_SHOPCAR = "car/add";     //添加购物车
        public static final String SHOW_SHOPCAR = "car/list";    //购物车展示

        public static final String DELETE_SHOPCAR = "car/delete";         //删除商品
        public static final String UPDATE_CAR_COUNT = "car/update_quantity";         //修改购物车数量
        public static final String WATCH_PRODUCT_DETAIL = "product/chose_the_clothes";         //查看商品参数的详情

        public static final String CAR_COUNT = "car/count_cart_quantity";         //购物车数量

        public static final String TAILOR_DETAIL = "product/searchCustomClothes";         //定制内容
        public static final String ATTEND_FOLLOW = "attend/isLike";         //点赞
        public static final String PRODUCT_DETAIL = "product/productDetail";         //商品详情
        public static final String PRODUCT_SEARCH = "product/search";         //商品搜索
        public static final String PRODUCT_SEARCH_KEY = "product/search_keyword";         //提示词


        public static final String DETAIL_DESIGNER = "designer/showDesignerDetail";         //设计师详情
        public static final String DETAIL_BRAND = "brand/showBrandDetail";         //品牌详情

        public static final String UPLOAD_HEADING = "upload/heading";         //上传图片
        public static final String ORDER_CREATE = "order/create";         //创建订单
        public static final String ORDER_MANAGER = "order/showObligationOrder";         //订单状态查看接口
        public static final String ORDER_DETAIL = "order/show_obligation_order_detail";         //订单详情
        public static final String ORDER_CANCLE = "order/cancel_order"; //取消订单
        public static final String ORDER_RETURN_FUND = "order/return_goods"; // 申退款金额接口
        public static final String ORDER_RETURN_FUND_PRICE = "order/cancel_return_order";// 取消申退款金额接口
        public static final String ORDER_CONFIRM_RECEIVE_GOODS = "order/confirm_receive_goods";  // 申退款金额接口
        public static final String ORDER_INSERT_DELIVERY_NUMBER = "order/insert_delivery_number";         // 物流
        public static final String ORDER_QUERY_DELIVERY_CODE = "order/query_delivery_code";         // 物流
        public static final String FEED_BACK = "user/opinion_by_user";   //意见反馈

        public static final String PAY_ALI = "aplipay/alipay_pay";         //支付宝请求
        public static final String PAY_WX = "weinxin_pay/send_payment";         //微信请求

        public static final String WISH = "wish/findWish";         //心愿单
        public static final String SHOW_COUPON = "coupon/showCoupon";         //优惠券列表
        public static final String HOT_SEARCH = "product/hot_search";         //热门搜索
        public static final String LOGISTIC = "query_order_where";         //物流
        public static final String FACE_TEST = "face/face_body";         //face++ 检测
        public static final String SMS_SEND = "sms/send.pass";         //发送验证码
        public static final String SMS_CHECK = "sms/check.pass";         //验证验证码

        public static final String UPLOAD_REVIEWIMAGE = "upload/reviewImage";         //上传评论接口
        public static final String REVIEW_ORDER = "order/review_order";         //订单评论接口

        // --------------  record---------------
        public static final String TBASE_URL = "http://114.55.145.129/somatometry/";         //量身记录
        //        public static final String WBASE_URL = "https://storeapi.dmeyc.com:8079/";         //微信支付
//        public static final String TAILOR_RECORD = "searchobj.php";         //量身记录
        public static final String TAILOR_RECORD = "size/find_people_data";         //量身记录
        //        public static final String GET_MEASURE_INFO = "makedatainfo.php";         //获取量身数据
        public static final String GET_MEASURE_INFO = "size/query_people_data";         //获取量身数据
        public static final String SAVE_MEASURE_INFO = "size/keep_people_data";   //保存

        //发现
        public static final String PUBLISH_ARTICLE = "discover/publish_article";         //发现-发布文章接口
        public static final String DISCOVER_LIST = "discover/discover_list";         //发现-列表和列表详情文章接口
        public static final String DISCOVER_IS_LIKE = "discover/discover_is_like";         //发现-点赞、取消
        public static final String DISCOVER_LIST_GOODS = "discover/discover_list_goods";         //发现-点赞、取消
        public static final String ATTEND_LIST = "discover/attend_list";         //发现-点赞、取消
        public static final String USER_AGREEMENT = "user_agreement";         //用户协议

        //视频
        public static final String VIDEO_GET_GENSIGN = "api/v1.0/sign/video/generator";
        public static final String VIDEO_TO_GET_LIST_IMG = "api/v1.0/topic/image/listImage";
        public static final String PUBLISH_DYNAMIC = "api/v1.0/topic/video/addVideo";
        public static final String DYNAMIC_LIST = "api/v1.0/topic/video/listVideo";
        public static final String ADD_TOPIC = "api/v1.0/topic/image/addImage";
        public static final String GET_ASSOCIATED_DATA = "api/v1.0/topic/video/listCompany";
        public static final String YFM_TEACHIN_NEW = "api/v2.0/coach/coachApply";
        public static final String YFM_CREATE_BUSINESS_MODULE = "api/v1.0/topic/video/addCompanyModel";
        public static final String YFM_GET_TYPE_OF_REPORT = "api/v1.0/complain/listComplainType";
        public static final String YFM_TO_REPORT = "api/v1.0/complain/addComplain";

        public static final String FACEPP = "https://api-cn.faceplusplus.com/humanbodypp/";
    }

    public static class HotUEventKeys {
        public static final String EVENT_KEYS_DYNAMIC = "CHOICE_TOPIC_SUCCESS";
        public static final String EVENT_KEYS_DYNAMIC_CREATE = "CREATE_TOPIC_SUCCESS";
        public static final String EVENT_KEYS_DYNAMIC_ASSOCIATED = "CHOICE_ASSOCIATED_SUCCESS";
        public static final String EVENT_KEYS_DYNAMIC_ASSOCIATED_CREATE = "CREATE_ASSOCIATED_SUCCESS";
        public static final String VIDEO_EDIT_CHOICE_SUCCESS = "video_event_edit_finish";
        public static final String RELEASE_DYNAMIC_SUCCESS = "release_dynamic_success";
        public static final String HAS_LOCATION_PERMISSION = "has_location_permission";
        public static final String NO_LOCATION_PERMISSION = "no_location_permission";
        public static final String TO_CHECK_PERMISSION = "check_permission";
    }

    public static class Config {

        public static final int TC_VIDEO_APPID = 1300388427;

        public static final int GENDER_MALE = 1;
        public static final int GENDER_FEMALE = 2;
        public static final int GENDER_CHILD = 3;

        public static final int TYPE_BRAND = 1;
        public static final int TYPE_DESIGNER = 2;

        public static final int SMS_CODE_LENGTH = 6;

        public static final String ISLOGIN = "is_login";
        public static final String USER_ID = "userId";
        public static final String POSITION = "position";
        public static final String TOKEN = "token";
        public static final String ROLECODE = "rolecode";
        public static final String RC_TOKEN = "rc_token"; //融云token
        public static final String IDENITY = "idenity";
        public static final String CURRENT_CITY = "current_city";
        public static final String CURRENT_PROVINCE = "current_province";
        public static final String LONGTUDE = "longitude";
        public static final String LATITUDE = "latitude";

        public static final String MOBILE = "mobile";
        public static final String AVATAR = "avatar";
        public static final String NICK_NAME = "nick_name";
        public static final String HAS_REDPOS = "redpos";
        public static final String SEX = "sex";
        public static final String TITLE = "title";
        public static final String URL = "url";

        public static final String COMMVIP = "commvip";
        public static final String COMMNAME = "commname";
        public static final String COMMAVD = "commavd";
        public static final String COMMADRESS = "commadress";
        public static final String COMMINTRO = "commintro";

        public static final String GENDER = "gender";
        public static final String TYPE = "type";
        public static final String ID = "id";
        public static final String STORY_ID = "story_id";
        public static final String ITEM = "item";
        public static final String IS_FOR_RESULT = "is_for_result";

        public static final int PAY_ZFB = 1;
        public static final int PAY_WX = 2;
        public static final int PAY_YL = 3;
        public static final String ORDER_ID = "order_id";

        public static String SHOP_CAR_ID = "shop_car_id";
        public static String HOME_DATA = "home_data";

        public static final String MD5_KEY = "QW!!22*&90)";


        public static String FRONT_PIC_PATH = "frontPicPath";
        public static String SIDE_PIC_PATH = "sidePicPath";
        public static final String SD_CARD = "/sdcard/yc";
        public static final String JPG = ".jpg";

        public static final String IS_USE_PHOTO = "is_use_photo";   //是否使用摄像头拍照
        public static final String IS_CROPTYPE = "croptype";   //是否使用摄像头拍照
        public static final String MAX_PIC_COUNT = "max_pic_count";
        public static final String IS_USER_CROP = "is_use_crop";
        public static final String SUCCESS = "success";
        public static final int PHOTO_PIC_REQUEST_CODE = 62225;

        public static final int QQ_THIRD = 1;
        public static final int WEIXIN_THIRD = 2;
        public static final int WEIBO_THIRD = 3;
        public static final String CHECKDAY = "checkday";
        public static final String CHECKHOR = "checkhor";
        public static final String HASEKEYWORD = "keyword";

        public static final String CHECKENDHOR = "endhor";
        public static final String ROOT_PATH = "/Football";
        public static final String PROJECTID = "projectid";
        public static final String DOWNLOAD_PATH = ROOT_PATH + "/DOWNLOAD/";

        public static final String BUSINESS_REGISTER_TYPE = "business_register_type";
    }

    /**
     * key管理
     */
    public static class Key {
        public static final String ZFB_APPID = "";
        //        public static final String WEIXIN_APPID = "2018010701679339";
//        public static final String WEIXIN_APPID = "wx82cdaef96e37dbeb";
//        public static final String WEIXIN_APPID = "wxe044b5e09438bbb3";
        public static final String WEIXIN_APPID = "wxe044b5e09438bbb3";

        public static final String UMENG_KEY = "5c19ed4ff1f5565bd400029b";
        //public static final String UMENG_KEY = "5cd3e95b0cafb255ff0010d6";
        public static final String FACE_KEY = "KXpXlB42_GzRrm3fH9bgM_MfQSgF03DI";
        public static final String FACE_SECRET = "UP0-H6Aey1LAcxrrAJ0R-2OWwLrMohSr";

        public static final int TLS_APPID = 1256504099;
        public static final String TLS_SECRETID = "AKIDmkfUK90XAhAeKjxb1Sn8QXSEpFTanemo";
        public static final String TLS_SECRETKEY = "4yhNvJWXN1TkpjR2UpzTp25wb4MSM5jK";

        // 融云
        public static final String RONGIM_KEY_DEBUG = "pvxdm17jpitor";
        public static final String RONGIM_SECRET_DEBUG = "yUCITtlLnpGZYt";
    }

    /**
     * 订单管理 状态码
     */
    public static class OrderStatus {
        public static final int ALL = 10;        //全部
        public static final int WAIT_PAY = 0;   //待付款

        public static final int COMMITTED = 1;  //待发货
        public static final int STAY = 2;       //待收货
        public static final int EVALUATE = 3;   //待晒单
        public static final int BACK = -2;   //
        public static final int FINISH = 4;   //完成
    }

    /**
     * 多条目
     */
    public static class AdapterItemType {
        public static final int MATCH = 1;
        public static final int ACTION = 2;

        // 视频条目
        public static final int TYPE_HOME_VIDEO = 3;//首页Home视频
        public static final int TYPE_SEARCH_VIDEO = 4;//搜索页面
        public static final int TYPE_ME_VIDEO = 5;//首页Me
        public static final int TYPE_ME_VIDEO_LIVE = 6;//首页Me直播
        public static final int TYPE_ME_VIDEO_LIKE = 7;//首页Me喜欢

        //用户列表
        public static final int TYPE_SEARCH_AUCHOR = 8;//选择主播

        // 可直播列表
        public static final int TYPE_LIVING_HOME = 10;
        public static final int TYPE_LIVING_RELEASE = 11;

    }

    public static class BadgeKey {
        public static final String FANS = "535";
        public static final String FAV = "536";
        public static final String AT = "537";
        public static final String COMMENT = "538";
    }

}
