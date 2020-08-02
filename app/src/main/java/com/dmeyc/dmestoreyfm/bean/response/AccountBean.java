package com.dmeyc.dmestoreyfm.bean.response;

/**
 * create by cxg on 2019/12/7
 */
public class AccountBean extends BaseRespBean {
    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public class DataBean {
        private String totalAmount;
        private String frozenAmount;
        private String avaliableAmount;

        public String getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(String totalAmount) {
            this.totalAmount = totalAmount;
        }

        public String getFrozenAmount() {
            return frozenAmount;
        }

        public void setFrozenAmount(String frozenAmount) {
            this.frozenAmount = frozenAmount;
        }

        public String getAvaliableAmount() {
            return avaliableAmount;
        }

        public void setAvaliableAmount(String avaliableAmount) {
            this.avaliableAmount = avaliableAmount;
        }
    }
}
