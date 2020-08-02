package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;
import java.util.List;

public class BillBean implements Serializable  {

    /**
     * code : 200
     * data : {"acountDetailList":[{"amount":"10000","create_time":"2019-08-05 16:53:07.0","group_name":"大宝羽毛球俱乐部","project_name":"羽毛球","type":"1"},{"amount":"53","create_time":"2019-08-05 16:53:30.0","group_name":"大宝羽毛球俱乐部","project_name":"羽毛球","type":"2"},{"amount":"43","create_time":"2019-08-05 17:43:18.0","group_name":"兔上羽毛球俱乐部","project_name":"羽毛球","type":"2"}],"harvest":10096,"payed":0}
     * msg : 操作成功
     */

    private String code;
    private DataBean data;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * acountDetailList : [{"amount":"10000","create_time":"2019-08-05 16:53:07.0","group_name":"大宝羽毛球俱乐部","project_name":"羽毛球","type":"1"},{"amount":"53","create_time":"2019-08-05 16:53:30.0","group_name":"大宝羽毛球俱乐部","project_name":"羽毛球","type":"2"},{"amount":"43","create_time":"2019-08-05 17:43:18.0","group_name":"兔上羽毛球俱乐部","project_name":"羽毛球","type":"2"}]
         * harvest : 10096
         * payed : 0
         */

        private int harvest;
        private int payed;
        private List<AcountDetailListBean> acountDetailList;

        public int getHarvest() {
            return harvest;
        }

        public void setHarvest(int harvest) {
            this.harvest = harvest;
        }

        public int getPayed() {
            return payed;
        }

        public void setPayed(int payed) {
            this.payed = payed;
        }

        public List<AcountDetailListBean> getAcountDetailList() {
            return acountDetailList;
        }

        public void setAcountDetailList(List<AcountDetailListBean> acountDetailList) {
            this.acountDetailList = acountDetailList;
        }

        public static class AcountDetailListBean {
            /**
             * amount : 10000
             * create_time : 2019-08-05 16:53:07.0
             * group_name : 大宝羽毛球俱乐部
             * project_name : 羽毛球
             * type : 1
             */

            private String amount;
            private String create_time;
            private String group_name;
            private String project_name;
            private String type;
            private String logo;
            private String source;

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getGroup_name() {
                return group_name;
            }

            public void setGroup_name(String group_name) {
                this.group_name = group_name;
            }

            public String getProject_name() {
                return project_name;
            }

            public void setProject_name(String project_name) {
                this.project_name = project_name;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }
}
