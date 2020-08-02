package com.dmeyc.dmestoreyfm.bean;

import java.util.List;

public class DeliveryCodeBean {

    /**
     * code : 200
     * msg : 操作成功
     * data : [{"id":1,"name":"中通快递","code":"ZTO"},{"id":2,"name":"圆通快递","code":"YTO"},{"id":3,"name":"韵达快递","code":"YD"},{"id":4,"name":"邮政快递包裹","code":"YZPY"},{"id":5,"name":"EMS","code":"EMS"},{"id":6,"name":"天天快递","code":"HHTT"},{"id":7,"name":"京东物流","code":"JD"},{"id":8,"name":"优速快递","code":"UC"},{"id":9,"name":"德邦快递","code":"DBL"},{"id":10,"name":"快捷快递","code":"FAST"},{"id":11,"name":"宅急送","code":"ZJS"},{"id":12,"name":"TNT快递","code":"TNT"},{"id":13,"name":"百世快运","code":"BTWL"},{"id":14,"name":"城市100","code":"CITY100"},{"id":15,"name":"速派快递","code":"FASTGO"},{"id":16,"name":"国通快递","code":"GTO"},{"id":17,"name":"中通快运","code":"ZTOKY"}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * name : 中通快递
         * code : ZTO
         */

        private int id;
        private String name;
        private String code;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}
