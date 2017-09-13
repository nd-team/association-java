package com.bjike.vo.CharitableActivities;

import com.bjike.vo.BaseVO;

/**
 * @Author: [dengjunren]
 * @Date: [2017-09-02 08:36]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CommentsVO extends BaseVO {
    /**
     * 评论人ｉｄ
     */
    private String commentatorid;

    /**
     * 评论人
     */
    private String commentator;

    /**
     * 评论人头像
     */
    private String commentatorPathHead;

    /**
     * 公益活动id
     */
    private String initiateactivitiesID;

    /**
     * 公益活动主题
     */
    private String initiateactivities;

    /**
     * 评论内容
     */
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

    /**
     * 被评论人
     */
    private String commented;

    /**
     * 被评论人ｉｄ
     */
    private String commentedPathHead;

    /**
     * 评论创建时间
     */
    private String createTime;

    public String getCommentatorid() {
        return commentatorid;
    }

    public void setCommentatorid(String commentatorid) {
        this.commentatorid = commentatorid;
    }

    public String getCommentator() {
        return commentator;
    }

    public void setCommentator(String commentator) {
        this.commentator = commentator;
    }

    public String getInitiateactivitiesID() {
        return initiateactivitiesID;
    }

    public void setInitiateactivitiesID(String initiateactivitiesID) {
        this.initiateactivitiesID = initiateactivitiesID;
    }

    public String getInitiateactivities() {
        return initiateactivities;
    }

    public void setInitiateactivities(String initiateactivities) {
        this.initiateactivities = initiateactivities;
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

    public String getCommented() {
        return commented;
    }

    public void setCommented(String commented) {
        this.commented = commented;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCommentatorPathHead() {
        return commentatorPathHead;
    }

    public void setCommentatorPathHead(String commentatorPathHead) {
        this.commentatorPathHead = commentatorPathHead;
    }

    public String getCommentedPathHead() {
        return commentedPathHead;
    }

    public void setCommentedPathHead(String commentedPathHead) {
        this.commentedPathHead = commentedPathHead;
    }
}
