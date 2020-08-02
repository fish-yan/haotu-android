package com.dmeyc.dmestoreyfm.video.index;

import com.dmeyc.dmestoreyfm.bean.response.BaseRespBean;
import com.dmeyc.dmestoreyfm.video.index.IndexDynamicBean.DataBean;

public class IndexDynamicItemBean extends BaseRespBean {
    private DataBean data;

    /**
     * code : 200
     * data : {"id":167,"userId":493,"url":null,"content":"亲","address":null,"createTime":1576535700000,"commentCount":4,"likeCount":2,"storeCount":0,"forwardCount":0,"seeCount":0,"isLike":1,"isStore":0,"isFollow":0,"userImageUrl":"http://101.44.2.178/group1/M00/00/26/wKgAaF3-bGCARQR3AAA0mp_but8439.png","userNickName":"好兔5554","imageUrls":[{"id":null,"videoId":167,"url":"http://101.44.2.178/group1/M00/00/26/wKgAaF34BpKAezAtAAR5cyeDnEg298.jpg","thumbnailUrl":"http://101.44.2.178/group1/M00/00/26/wKgAaF34BpOAdfctAAA-Yp2Z8WE653.jpg"}],"type":2,"geoHash":"uxypyzupzxvr","coverUrl":null,"txId":null,"linkedType":3,"linkedId":145,"linkedTitle":null,"linkedImage":null,"linkedUserId":null,"topicImageDTOs":[],"companyDetailDTO":null}
     */

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }


}
