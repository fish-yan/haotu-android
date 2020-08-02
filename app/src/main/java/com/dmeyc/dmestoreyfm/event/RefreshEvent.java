package com.dmeyc.dmestoreyfm.event;

/**
 * create by cxg on 2019/12/7
 */
public class RefreshEvent {
    public static class BankCard {
    }

    public static class AccountSave {
    }

    public static class PagerDetailFragments {
    }


    public static class Release {
        public static final String TYPE_VIDEO = "TYPE_VIDEO";
        public static final String TYPE_LIVING = "TYPE_LIVING";
        public static final String TYPE_LIKE = "TYPE_LIKE";
        public static final String TYPE_GOODS = "TYPE_GOODS";
        public static final String TYPE_COURSE = "TYPE_COURSE";
        public static final String TYPE_ACTIVITY = "TYPE_ACTIVITY";

        public String mType;

        public Release(String type) {
            mType = type;
        }
    }

    public static class ActionDetailActivity {
    }

    public static class PUSH_NEW_INFO {
    }
}
