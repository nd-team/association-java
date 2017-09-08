package com.bjike.to.CharitableActivities;

import com.bjike.common.aspect.ADD;
import com.bjike.common.aspect.EDIT;
import com.bjike.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Author: [dengjunren]
 * @Date: [2017-08-31 15:20]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CommentsTO extends BaseTO {
    /**
     * 评论人
     */
    private String commentatorid;

    /**
     * 公益活动id
     */
    @NotBlank(message = "公益活动id不能为空", groups = {ADD.class})
    private String initiateactivitiesID;

    /**
     * 评论内容
     */
    @NotBlank(message = "评论内容不能为空", groups = {ADD.class, EDIT.class})
    private String comments;

    /**
     * 评论获赞数
     */
    private Long approved;

    /**
     * 是否为回复
     */
    private Boolean isOtherCommer;

    /**
     * 被评论人id
     */
    private String commentedID;

    public String getCommentatorid() {
        return commentatorid;
    }

    public void setCommentatorid(String commentatorid) {
        this.commentatorid = commentatorid;
    }

    public String getInitiateactivitiesID() {
        return initiateactivitiesID;
    }

    public void setInitiateactivitiesID(String initiateactivitiesID) {
        this.initiateactivitiesID = initiateactivitiesID;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Long getApproved() {
        return approved;
    }

    public void setApproved(Long approved) {
        this.approved = approved;
    }

    public Boolean getOtherCommer() {
        return isOtherCommer;
    }

    public void setOtherCommer(Boolean otherCommer) {
        isOtherCommer = otherCommer;
    }

    public String getCommentedID() {
        return commentedID;
    }

    public void setCommentedID(String commentedID) {
        this.commentedID = commentedID;
    }
}
