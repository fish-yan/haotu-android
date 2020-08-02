package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;
import java.util.List;

public class TailorListBean {

    /**
     * code : 200
     * msg : 操作成功
     * data : [{"id":1984,"createDate":"2018-12-01 17:35:18","modifyDate":"2018-12-01 17:35:18","version":null,"memo":"李","member":39,"ankle":23.5,"arm":56.8,"bust":95.3,"downSide":72.8,"shoulder":42.8,"wrist":17.6,"waistline":83.9,"hipline":97.1,"pheight":168,"pweight":75}]
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

    public static class DataBean implements Serializable {
        /**
         * id : 1984
         * createDate : 2018-12-01 17:35:18
         * modifyDate : 2018-12-01 17:35:18
         * version : null
         * memo : 李
         * member : 39
         * ankle : 23.5
         * arm : 56.8
         * bust : 95.3
         * downSide : 72.8
         * shoulder : 42.8
         * wrist : 17.6
         * waistline : 83.9
         * hipline : 97.1
         * pheight : 168
         * pweight : 75
         */

        private int id;
        private String createDate;
        private String modifyDate;
        private Object version;
        private String memo;
        private int member;
        private double ankle;
        private double arm;
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

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public int getMember() {
            return member;
        }

        public void setMember(int member) {
            this.member = member;
        }

        public double getAnkle() {
            return ankle;
        }

        public void setAnkle(double ankle) {
            this.ankle = ankle;
        }

        public double getArm() {
            return arm;
        }

        public void setArm(double arm) {
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
