package com.dmeyc.dmestoreyfm.newui.home.message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dmeyc.dmestoreyfm.api.CustomSubscriber;
import com.dmeyc.dmestoreyfm.api.RetrofitService;
import com.dmeyc.dmestoreyfm.bean.response.BaseRespBean;
import com.dmeyc.dmestoreyfm.bean.response.GroupDetailBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.newbase.BasePresenter;
import com.dmeyc.dmestoreyfm.utils.RongImHelper;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.Message;

/**
 * create by cxg on 2019/12/4
 */
public class MessagePresenter extends BasePresenter<IMessageView> {
    private String groupId;

    public void httpAddGroup(String result) {
        subscriber = new CustomSubscriber<BaseRespBean>(mView, mGuid) {
            @Override
            public void onSuccess(BaseRespBean bean) {

                RongImHelper.sendGroupMessage(groupId, SPUtils.getStringData(Constant.Config.NICK_NAME) + "加入了群组",new RongImHelper.CallBackAdapter(){
                    @Override
                    public void onSuccess(Message message) {
                        super.onSuccess(message);
                        getGourpById();
                    }
                });
            }
        };
        try {
//            JSONObject jsonObject = JSON.parseObject(result);
            String str = result.substring(result.lastIndexOf("id="));
            groupId = str.substring(3,str.indexOf("&"));
//            groupId = jsonObject.getString("id");
            Map<String, String> params = new HashMap<>();
            params.put("group_id", groupId);
            RetrofitService.getInstance().joinGroup(params, subscriber);

        } catch (Exception e) {
            ToastUtil.show("非法二维码");
        }

    }


    private void getGourpById(){
        subscriber = new CustomSubscriber<GroupDetailBean>(mView,mGuid) {
            @Override
            public void onSuccess(GroupDetailBean bean) {
                mView.jumpToChat(bean.getData());
            }

        };
        Map<String,String> params = new HashMap<>();
        params.put("group_id",groupId);
        RetrofitService.getInstance().viewGroupById(params,subscriber);
    }
}
