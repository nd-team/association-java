package com.bjike.entity.CharitableActivities;

import com.bjike.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 评论表
 *
 * @Author: [zhuangkaiqin]
 * @Date: [2017-08-36 17:12]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "charitableactivities_comment")
public class Comments extends BaseEntity {
    @Column(columnDefinition = "VARCHAR(36) COMMENT '评论人' ")
    private String commentatorid;

    @Column(columnDefinition = "VARCHAR(36) COMMENT '公益活动id' ")
    private String initiateactivitiesID;

    @Column(columnDefinition = "TEXT COMMENT '评论内容' ")
    private String comments;

    @Column(columnDefinition = "BIGINT(20) COMMENT '评论获赞数' ")
    private Long approved;

    @Column(columnDefinition = "TINYINT(1) COMMENT '是否为回复' ")
    private Boolean isOtherCommer;

    @Column(columnDefinition = "VARCHAR(36) COMMENT '被评论人' ")
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
