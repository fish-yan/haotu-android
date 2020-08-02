package com.dmeyc.dmestoreyfm.bean.response;

/**
 * create by cxg on 2019/12/23
 */
public class AddCommentBean extends BaseRespBean {

    private CommentParentBean.DataBean.CommentListBean data;

    public CommentParentBean.DataBean.CommentListBean getData() {
        return data;
    }

    public void setData(CommentParentBean.DataBean.CommentListBean data) {
        this.data = data;
    }
}
