package com.dmeyc.dmestoreyfm.event;

import com.dmeyc.dmestoreyfm.bean.response.UserListBean;
import com.dmeyc.dmestoreyfm.photolive.mtp.PicInfo;
import com.dmeyc.dmestoreyfm.video.releasedynamic.TopicInEditBean;

import java.util.List;

/**
 * create by cxg on 2019/11/23
 */
public class MyEvent {
    public static class ShowMenuList {
    }

    public static class Location {
        public int mType;
        public String mLocation;

        public Location(int type, String location) {
            this.mType = type;
            this.mLocation = location;
        }
    }

    /**
     * 群活动时间筛选
     */
    public static class SelectorDates {

        public static final int TYPE_UN_REFRESH = 1;
        public static final int TYPE_REFRESH = 2;

        public int mType = TYPE_REFRESH;
        public String mDates;

        public SelectorDates(int type, String dates) {
            this.mType = type;
            this.mDates = dates;
        }
    }

    public static class ReleaseVideo {
        public TopicInEditBean mTopicInEditBean;

        public ReleaseVideo(TopicInEditBean topicInEditBean) {
            this.mTopicInEditBean = topicInEditBean;
        }
    }

    public static class ReleaseRelation {
        public String groupId;
        public String groupName;
        public String type;

        public ReleaseRelation(String groupId, String groupName, String type) {
            this.groupId = groupId;
            this.groupName = groupName;
            this.type = type;
        }
    }

    public static class UpdateNotice {
        public String notice;

        public UpdateNotice(String notice) {
            this.notice = notice;
        }
    }

    public static class MainBottomBar {
        public boolean isVideo;

        public MainBottomBar(boolean isVideo) {
            this.isVideo = isVideo;
        }
    }

    // 个人资料
    public static class EditInfo {
        public static final String TYPE_NAME = "1";
        public static final String TYPE_MARK = "2";
        public static final String TYPE_ALL = "3";

        private String name;
        private String mark;
        private String imageUrl;
        private String type;

        public EditInfo() {

        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMark() {
            return mark;
        }

        public void setMark(String mark) {
            this.mark = mark;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    //首页群活动点击全部
    public static class GroupActionTabClick {
    }

    public static class ScanResult {
        public String result;

        public ScanResult(String result) {
            this.result = result;
        }
    }

    public static class Close {
        public static class ApplyForActivity {
        }
        public static class ConversationActivity {
        }
    }

    public static class SelectorAnchor {
        public List<UserListBean.DataBean> checkList;

        public SelectorAnchor(List<UserListBean.DataBean> checkList) {
            this.checkList = checkList;
        }
    }

    public static class LivingPath {
        public String filePath;

        public LivingPath(String filePaht) {
            filePath = filePaht;
        }
    }
    public static class LivingPath1 {
        public List<PicInfo> picInfo;

        public LivingPath1(List<PicInfo> picInfo) {
            this.picInfo = picInfo;
        }
    }

    public static class CloseHomePlay {
    }

    public static class WxPaySuccess {
    }
}
