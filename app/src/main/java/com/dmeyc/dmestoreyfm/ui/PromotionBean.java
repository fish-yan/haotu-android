package com.dmeyc.dmestoreyfm.ui;

import java.io.Serializable;
import java.util.List;

public class PromotionBean implements Serializable {

    /**
     * msg : 操作成功
     * code : 200
     * data : [{"id":1,"userId":2,"groupId":null,"status":"0","createTime":1560965902000,"inviteRewardDTO":{"id":13,"groupId":2,"type":1,"rule":"{\"endDate\":\"2019-06-31 10:20:00\",\"discountAmount\":10,\"userRule\":\"恭喜你，本群赠送你一张优惠券\",\"name\":\"优惠券立减\"}","status":"2","createTime":1560801795000}},{"id":2,"userId":2,"groupId":null,"status":"0","createTime":1560965922000,"inviteRewardDTO":{"id":13,"groupId":2,"type":1,"rule":"{\"endDate\":\"2019-06-31 10:20:00\",\"discountAmount\":10,\"userRule\":\"恭喜你，本群赠送你一张优惠券\",\"name\":\"优惠券立减\"}","status":"2","createTime":1560801795000}}]
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
         * id : 1
         * userId : 2
         * groupId : null
         * status : 0
         * createTime : 1560965902000
         * inviteRewardDTO : {"id":13,"groupId":2,"type":1,"rule":"{\"endDate\":\"2019-06-31 10:20:00\",\"discountAmount\":10,\"userRule\":\"恭喜你，本群赠送你一张优惠券\",\"name\":\"优惠券立减\"}","status":"2","createTime":1560801795000}
         */

        private int id;
        private int userId;
        private int  groupId;
        private String status;
        private long createTime;
        private InviteRewardDTOBean inviteRewardDTO;
        private String rule;
        private int type;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getRule() {
            return rule;
        }

        public void setRule(String rule) {
            this.rule = rule;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int  getGroupId() {
            return groupId;
        }

        public void setGroupId(int  groupId) {
            this.groupId = groupId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public InviteRewardDTOBean getInviteRewardDTO() {
            return inviteRewardDTO;
        }

        public void setInviteRewardDTO(InviteRewardDTOBean inviteRewardDTO) {
            this.inviteRewardDTO = inviteRewardDTO;
        }

        public static class InviteRewardDTOBean {
            /**
             * id : 13
             * groupId : 2
             * type : 1
             * rule : {"endDate":"2019-06-31 10:20:00","discountAmount":10,"userRule":"恭喜你，本群赠送你一张优惠券","name":"优惠券立减"}
             * status : 2
             * createTime : 1560801795000
             */

            private int id;
            private int groupId;
            private int type;
            private String rule;
            private String status;
            private long createTime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getGroupId() {
                return groupId;
            }

            public void setGroupId(int groupId) {
                this.groupId = groupId;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getRule() {
                return rule;
            }

            public void setRule(String rule) {
                this.rule = rule;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }
        }
    }
}
