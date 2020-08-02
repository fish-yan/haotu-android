package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;

public class WeightHeightBean implements Serializable {

    /**
     * code : 200
     * msg : 操作成功
     * data : {"id":2654,"createDate":"2018-11-12 17:04:43","modifyDate":"2018-11-12 17:34:10","version":null,"memo":null,"member":null,"ankle":20.3,"arm":55,"bust":84.8,"downSide":72.8,"shoulder":42.9,"wrist":15.7,"waistline":71.9,"hipline":86.8,"pheight":165,"pweight":55}
     */

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

    public static class DataBean  implements  Serializable{
        /**
         * id : 2654
         * createDate : 2018-11-12 17:04:43
         * modifyDate : 2018-11-12 17:34:10
         * version : null
         * memo : null
         * member : null
         * ankle : 20.3
         * arm : 55
         * bust : 84.8
         * downSide : 72.8
         * shoulder : 42.9
         * wrist : 15.7
         * waistline : 71.9
         * hipline : 86.8
         * pheight : 165
         * pweight : 55
         */

        private int id;
        private String createDate;
        private String modifyDate;
        private Object version;
        private Object memo;
        private Object member;
        private double ankle;
        private int arm;
        private double bust;
        private double downSide;
        private double shoulder;
        private double wrist;
        private double waistline;
        private double hipline;
        private int pheight;
        private int pweight;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getModifyDate() {
            return modifyDate;
        }

        public void setModifyDate(String modifyDate) {
            this.modifyDate = modifyDate;
        }

        public Object getVersion() {
            return version;
        }

        public void setVersion(Object version) {
            this.version = version;
        }

        public Object getMemo() {
            return memo;
        }

        public void setMemo(Object memo) {
            this.memo = memo;
        }

        public Object getMember() {
            return member;
        }

        public void setMember(Object member) {
            this.member = member;
        }

        public double getAnkle() {
            return ankle;
        }

        public void setAnkle(double ankle) {
            this.ankle = ankle;
        }

        public int getArm() {
            return arm;
        }

        public void setArm(int arm) {
            this.arm = arm;
        }

        public double getBust() {
            return bust;
        }

        public void setBust(double bust) {
            this.bust = bust;
        }

        public double getDownSide() {
            return downSide;
        }

        public void setDownSide(double downSide) {
            this.downSide = downSide;
        }

        public double getShoulder() {
            return shoulder;
        }

        public void setShoulder(double shoulder) {
            this.shoulder = shoulder;
        }

        public double getWrist() {
            return wrist;
        }

        public void setWrist(double wrist) {
            this.wrist = wrist;
        }

        public double getWaistline() {
            return waistline;
        }

        public void setWaistline(double waistline) {
            this.waistline = waistline;
        }

        public double getHipline() {
            return hipline;
        }

        public void setHipline(double hipline) {
            this.hipline = hipline;
        }

        public int getPheight() {
            return pheight;
        }

        public void setPheight(int pheight) {
            this.pheight = pheight;
        }

        public int getPweight() {
            return pweight;
        }

        public void setPweight(int pweight) {
            this.pweight = pweight;
        }
    }
}
