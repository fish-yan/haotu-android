package com.dmeyc.dmestoreyfm.net;

import android.text.TextUtils;

import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.utils.Util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jockie on 2017/12/28
 * Email:jockie911@gmail.com
 */

public class ParamMap {

    private ParamMap(){}

    public static class Build{
        private Map<String,Object> map;
        public Build(){
            map = new HashMap();
            if(!TextUtils.isEmpty(Util.getUserId()))
                map.put(Constant.Config.USER_ID,Util.getUserId());
        }

        public Build addParams(String key,Object value){
            map.put(key,value);
            return this;
        }

        public Map<String,Object> build(){
            String params = "";
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                System.out.println("ParamMap map : key " + entry.getKey() + " value " + entry.getValue());
                params += "&" + entry.getKey() + "=" + entry.getValue();
            }
            System.out.println(" -- !== == ==! -- start output request paramas ----- : " + params);
            return map;
        }
    }
}
