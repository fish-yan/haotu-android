package com.dmeyc.dmestoreyfm.bean.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * create by cxg on 2019/12/14
 */
public class SearchHistoryBean extends BaseRespBean {

    /**
     * code : 200
     * data : [{"id":1,"keyword":"好兔运动","searchNum":34,"type":"3","createTime":1576259987000},{"id":9,"keyword":"哈哈","searchNum":14,"type":"2","createTime":1576286537000},{"id":2,"keyword":"羽毛球","searchNum":12,"type":"2","createTime":1576260013000},{"id":11,"keyword":"活动","searchNum":4,"type":"4","createTime":1576286941000},{"id":4,"keyword":"好兔杯","searchNum":2,"type":"4","createTime":1576260035000},{"id":12,"keyword":"12","searchNum":2,"type":"4","createTime":1576351988000},{"id":8,"keyword":"你","searchNum":2,"type":"4","createTime":1576284244000},{"id":10,"keyword":"高科西路","searchNum":2,"type":"4","createTime":1576286933000},{"id":3,"keyword":"乒乓球","searchNum":1,"type":"1","createTime":1576260026000},{"id":5,"keyword":"你好呀","searchNum":1,"type":"1","createTime":1576265525000}]
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * keyword : 好兔运动
         * searchNum : 34
         * type : 3
         * createTime : 1576259987000
         */

        private int id;
        private String keyword;
        private String searchNum;
        private String type;
        private long createTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public String getSearchNum() {
            return searchNum;
        }

        public void setSearchNum(String searchNum) {
            this.searchNum = searchNum;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }
    }
}
