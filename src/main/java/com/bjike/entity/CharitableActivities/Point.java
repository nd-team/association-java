package com.bjike.entity.CharitableActivities;

import com.bjike.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 点赞表
 *
 * @Author: [dengjunren]
 * @Date: [2017-08-30 17:51]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "charitableactivities_point")
public class Point extends BaseEntity {
    @Column(columnDefinition = "VARCHAR(36) COMMENT '点赞人' ")
    private String praiseID;

//    @Column(columnDefinition = "VARCHAR(255) COMMENT '被点赞人' ")
//    private String name;
//
//    @Column(columnDefinition = "BIGINT(20) COMMENT '被点赞人的点赞数' ")
//    private Long pointNum;
//
//    @Column(columnDefinition = "VARCHAR(255) COMMENT '点赞的主题' ")
//    private String pointSubject;

    @Column(columnDefinition = "VARCHAR(36) COMMENT '被点赞的评论id' ")
    private String commentID;

    @Column(columnDefinition = "VARCHAR(36) COMMENT '被点赞的公益活动id' ")
    private String initiateactivitiesID;

    public String getPraiseID() {
        return praiseID;
    }

    public void setPraiseID(String praiseID) {
        this.praiseID = praiseID;
    }

    public String getCommentID() {
        return commentID;
    }

    public void setCommentID(String commentID) {
        this.commentID = commentID;
    }

    public String getInitiateactivitiesID() {
        return initiateactivitiesID;
    }

    public void setInitiateactivitiesID(String initiateactivitiesID) {
        this.initiateactivitiesID = initiateactivitiesID;
    }
}
