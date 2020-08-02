package com.dmeyc.dmestoreyfm.fragment;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseFragment;
import com.dmeyc.dmestoreyfm.bean.MyCommListBean;
import com.dmeyc.dmestoreyfm.bean.RoCluldCommBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.video.systeminfo.SystemInfoActivity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imkit.widget.adapter.ConversationListAdapter;
import io.rong.imlib.model.Conversation;

public class ConVersionFragment extends BaseFragment {

    @Bind(R.id.et_search_title)
    EditText et_search_title;
    ConversationListFragment listFragment;
    @Bind(R.id.systemInfo)
    LinearLayout systemInfo;
    @Bind(R.id.activityInfo)
    LinearLayout activityInfo;

    @Override
    protected int getLayoutRes() {
        return R.layout.frag_comver;
    }
//@Override
//protected int getLayoutRes() {
//    return R.layout.rc_fr_conversationlist;
//}

    @Override
    protected void initData() {
//        RongUserInfoManager.getInstance().getGroupInfo();
        listFragment = (ConversationListFragment) ConversationListFragment.instantiate(getActivity(), ConversationListFragment.class.getName());
        Uri uri = Uri.parse("rong://" + getActivity().getApplicationInfo().packageName).buildUpon()
                .appendPath("conversationlist")
                .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false")
                .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")
                .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "false")
                .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")
                .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "false")
                .build();
        listFragment.setUri(uri);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        //将融云的Fragment界面加入到我们的页面。
        transaction.add(R.id.subconversationlist, listFragment);
        transaction.commitAllowingStateLoss();

//        RongIM.getInstance().getConversationList(new RongIMClient.ResultCallback<List<Conversation>>() {
//            @Override
//            public void onSuccess(List<Conversation> conversations) {
//                System.out.print("ccccccc"+conversations);
//                for (int i = 0; i < conversations.size(); i++) {
//                            getListUserInfo(TotalMessageActivity.this, conversations.get(i).getTargetId());
//                    System.out.print(conversations.get(i).getTargetId()+"aaaaaaaa");
//                    System.out.print(conversations.get(i).getPortraitUrl()+"bbbbbbbbbbb");
//                    System.out.print(conversations.get(i).getTargetId()+"ddddddddddd");
//                }
//
//            }
//            @Override
//            public void onError(RongIMClient.ErrorCode errorCode) {
//
//            }
//        });


        ConversationListAdapter conversationListAdapter = listFragment.onResolveAdapter(getActivity());
//        listFragment.;
        conversationListAdapter.notifyDataSetChanged();
//        RongIM.setGroupInfoProvider(new YFMGroupInfoProvider(getContext()),true);
    }


    @Override
    protected void initData(View view) {
        ButterKnife.bind(view);
        et_search_title.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    /**隐藏软键盘**/
                    View view = getActivity().getWindow().peekDecorView();
                    if (view != null) {
                        InputMethodManager inputmanger = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                    if (TextUtils.isEmpty(et_search_title.getText().toString().trim())) {
                        ToastUtil.show("请输入搜索词");
                    } else {
                        roCluldCommBeans.clear();
//                        et_search_title.setText("");
                        getCommList();
//                        actionFragment.refreshSearch(et_search_title.getText().toString().trim());
                    }
                }
                return false;
            }
        });

//        HashMap<String, Boolean> hashMap = new HashMap<>();
//        //会话类型 以及是否聚合显示
////        hashMap.put(Conversation.ConversationType.PRIVATE.getName(),false);
////        hashMap.put(Conversation.ConversationType.PUSH_SERVICE.getName(),true);
////        hashMap.put(Conversation.ConversationType.SYSTEM.getName(),true);
//        hashMap.put(Conversation.ConversationType.GROUP.getName(),true);
//        RongIM.getInstance().startConversationList(getActivity(),hashMap);
    }

    private ArrayList<RoCluldCommBean> roCluldCommBeans = new ArrayList<>();
    RoCluldCommBean roCluldCommBean;

    public void getCommList() {
        RestClient.getYfmNovate(getActivity()).post(Constant.API.YFM_MYCOMMlist, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("status", "0")
                .build(), new DmeycBaseSubscriber<MyCommListBean>() {
            @Override
            public void onSuccess(MyCommListBean bean) {
                for (int i = 0; i < bean.getData().size(); i++) {
                    if (bean.getData().get(i).getGroup_name() != null) {
                        if (!bean.getData().get(i).getGroup_name().contains(et_search_title.getText().toString().trim())) {
                            roCluldCommBean = new RoCluldCommBean();
                            roCluldCommBean.setGroup_id(bean.getData().get(i).getGroup_id());
                            roCluldCommBean.setGroup_name(bean.getData().get(i).getGroup_name());
                            roCluldCommBean.setGroup_logo(bean.getData().get(i).getGroup_logo());
                            roCluldCommBeans.add(roCluldCommBean);
                        }
                    }
                }
                setRongUserInfo("");
            }
        });
    }


    //设置容云用户信息
    private void setRongUserInfo(final String targetid) {
        if (RongIM.getInstance() != null) {
//          for (int i=0;i<roCluldCommBeans.size();i++){
//              listFragment.shouldFilterConversation(Conversation.ConversationType.GROUP,roCluldCommBeans.get(i).getGroup_id()+"");
//
//                  RongIM.getInstance().refreshGroupInfoCache(new Group(roCluldCommBeans.get(i).getGroup_id()+"",roCluldCommBeans.get(i).getGroup_name(),Uri.parse(roCluldCommBeans.get(i).getGroup_logo())));
//
//          }

//            RongIM.getInstance().getConversationList(new RongIMClient.ResultCallback<List<Conversation>>() {
//                @Override
//                public void onSuccess(List<Conversation> conversations) {
//                    System.out.print("ccccccc"+conversations);
//                    for (int i = 0; i < conversations.size(); i++) {
//                        getListUserInfo(TotalMessageActivity.this, conversations.get(i).getTargetId());
//                        System.out.print(conversations.get(i).getTargetId()+"aaaaaaaa");
//                        System.out.print(conversations.get(i).getPortraitUrl()+"bbbbbbbbbbb");
//                        System.out.print(conversations.get(i).getTargetId()+"ddddddddddd");
//                    }
//
//                }
//                @Override
//                public void onError(RongIMClient.ErrorCode errorCode) {
//
//                }
//            });
//            RongIM.getInstance().getRongIMClient().getConversationList();
//            RongIM.getInstance().getRongIMClient().getCon
            for (int i = 0; i < roCluldCommBeans.size(); i++) {
//                RongIM.getInstance().getRongIMClient().getConversation(Conversation.ConversationType.GROUP,roCluldCommBeans.get(i).getGroup_id()+"");
                RongIM.getInstance().getRongIMClient().removeConversation(Conversation.ConversationType.GROUP, roCluldCommBeans.get(i).getGroup_id() + "");
//                ConversationListAdapter  conadapter= listFragment. onResolveAdapter(getActivity());
//                conadapter.clear();
//                conadapter.notifyDataSetChanged();
            }

//            Conversation conversation=new Conversation();
//            if(roCluldCommBeans.size()==0){
//                conadapter.clear();
//                conadapter.add(null);
//                listFragment.updateListItem(null);
//                            conadapter.notifyDataSetChanged();
//            }else {
//                conversation.setPortraitUrl(roCluldCommBeans.get(0).getGroup_logo());
//                conversation.setObjectName(roCluldCommBeans.get(0).getGroup_name());
//                conversation.setConversationType(Conversation.ConversationType.GROUP);
//                conversation.setTargetId(roCluldCommBeans.get(0).getGroup_id()+"");
//                UIConversation  uiConversation = UIConversation.obtain(getActivity(), conversation, true);
//                conadapter.add(uiConversation);
//                listFragment.updateListItem(uiConversation);
//                            conadapter.notifyDataSetChanged();
//            }
        }
    }

    @OnClick({R.id.systemInfo, R.id.activityInfo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.systemInfo:
                SystemInfoActivity.newIntent(getActivity(), 1);
                break;
            case R.id.activityInfo:
                SystemInfoActivity.newIntent(getActivity(), 2);
                break;
        }
    }
}
