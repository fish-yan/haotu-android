package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;
import java.util.List;

public class MoneyManagerBean implements Serializable {

    /**
     * msg : 操作成功
     * code : 200
     * data : [{"nick_name":"掌柜的","project_type":"羽毛球","create_time":"2019-04-12 11:06:39.0","amount":"66","type":"支出"},{"nick_name":"掌柜的","project_type":"羽毛球","create_time":"2019-04-12 15:54:19.0","amount":"88","type":"收入"}]
     */

    private String msg;
    private String code;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * nick_name : 掌柜的
         * project_type : 羽毛球
         * create_time : 2019-04-12 11:06:39.0
         * amount : 66
         * type : 支出
         */

        private String nick_name;
        private String project_type;
        private String create_time;
        private String amount;
        private String type;

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public String getProject_type() {
            return project_type;
        }

        public void setProject_type(String project_type) {
            this.project_type = project_type;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
