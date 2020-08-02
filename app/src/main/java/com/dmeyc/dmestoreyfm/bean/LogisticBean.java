package com.dmeyc.dmestoreyfm.bean;

import java.util.List;

/**
 * Created by jockie on 2018/3/16
 * Email:jockie911@gmail.com
 */

public class LogisticBean {

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * LogisticCode : 888591388308999074
         * ShipperCode : YTO
         * Traces : [{"AcceptStation":"客户 签收人: 邮件收发章 已签收 感谢使用圆通速递，期待再次为您服务","AcceptTime":"2018-03-14 19:46:11"},{"AcceptStation":"【上海市闵行区虹桥公司】 派件人: 易海 派件中 派件员电话18321539314","AcceptTime":"2018-03-14 14:21:23"},{"AcceptStation":"【上海市闵行区虹桥公司】 已收入","AcceptTime":"2018-03-14 14:12:03"},{"AcceptStation":"【上海转运中心】 已发出 下一站 【上海市闵行区虹桥公司】","AcceptTime":"2018-03-14 06:45:05"},{"AcceptStation":"【上海转运中心】 已收入","AcceptTime":"2018-03-14 02:45:25"},{"AcceptStation":"【义乌转运中心公司】 已收入","AcceptTime":"2018-03-13 21:02:36"},{"AcceptStation":"【义乌转运中心公司】 已发出 下一站 【上海转运中心】","AcceptTime":"2018-03-13 21:02:29"},{"AcceptStation":"【浙江省金华市义乌市联平公司】 已发出 下一站 【义乌转运中心公司】","AcceptTime":"2018-03-13 18:57:19"},{"AcceptStation":"【浙江省金华市义乌市联平公司】 已打包","AcceptTime":"2018-03-13 18:51:19"},{"AcceptStation":"【浙江省金华市义乌市联平公司】 已收件","AcceptTime":"2018-03-13 18:38:57"}]
         * State : 3
         * EBusinessID : 1296056
         * Success : true
         */

        private String LogisticCode;
        private String ShipperCode;
        private String State;
        private String EBusinessID;
        private boolean Success;
        private List<TracesBean> Traces;

        public String getLogisticCode() {
            return LogisticCode;
        }

        public void setLogisticCode(String LogisticCode) {
            this.LogisticCode = LogisticCode;
        }

        public String getShipperCode() {
            return ShipperCode;
        }

        public void setShipperCode(String ShipperCode) {
            this.ShipperCode = ShipperCode;
        }

        public String getState() {
            return State;
        }

        public void setState(String State) {
            this.State = State;
        }

        public String getEBusinessID() {
            return EBusinessID;
        }

        public void setEBusinessID(String EBusinessID) {
            this.EBusinessID = EBusinessID;
        }

        public boolean isSuccess() {
            return Success;
        }

        public void setSuccess(boolean Success) {
            this.Success = Success;
        }

        public List<TracesBean> getTraces() {
            return Traces;
        }

        public void setTraces(List<TracesBean> Traces) {
            this.Traces = Traces;
        }

        public static class TracesBean {
            /**
             * AcceptStation : 客户 签收人: 邮件收发章 已签收 感谢使用圆通速递，期待再次为您服务
             * AcceptTime : 2018-03-14 19:46:11
             */

            private String AcceptStation;
            private String AcceptTime;

            public String getAcceptStation() {
                return AcceptStation;
            }

            public void setAcceptStation(String AcceptStation) {
                this.AcceptStation = AcceptStation;
            }

            public String getAcceptTime() {
                return AcceptTime;
            }

            public void setAcceptTime(String AcceptTime) {
                this.AcceptTime = AcceptTime;
            }
        }
    }
}
