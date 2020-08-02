package com.dmeyc.dmestoreyfm.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubscriberManager {
    private static SubscriberManager subscriberManager;
    private Map<String, List<CustomSubscriber>> subscriberMap = null;

    public SubscriberManager() {
        subscriberMap = new HashMap<>();
    }

    public static SubscriberManager getInstance() {
        if (subscriberManager == null) {
            synchronized (SubscriberManager.class) {
                if (subscriberManager == null) {
                    subscriberManager = new SubscriberManager();
                }
            }
        }
        return subscriberManager;
    }

    void add(String tag, CustomSubscriber customSubscriber) {
        if (subscriberMap.containsKey(tag)) {
            subscriberMap.get(tag).add(customSubscriber);
            return;
        }
        List<CustomSubscriber> subscriberList = new ArrayList<>();
        subscriberList.add(customSubscriber);
        subscriberMap.put(tag, subscriberList);
    }

    public void relieveSubscriber(String tag) {
        if (subscriberMap.containsKey(tag)) {
            if (subscriberMap.get(tag) != null) {
                for (CustomSubscriber customSubscriber : subscriberMap.get(tag)) {
                    if(customSubscriber != null) {
                        customSubscriber.unsubscribe();
                    }
                }
                subscriberMap.get(tag).clear();
                subscriberMap.remove(tag);
            }
        }
    }
}