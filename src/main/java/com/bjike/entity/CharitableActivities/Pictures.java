package com.bjike.entity.CharitableActivities;

import com.bjike.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author: [dengjunren]
 * @Date: [2017-09-01 10:06]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "charitableactivities_picture")
public class Pictures extends BaseEntity {

    /**
     * 评论id
     */
    @Column(columnDefinition = "VARCHAR(36) COMMENT '评论id' ", nullable = false)
    private String commentID;

    /**
     * 评论图片路径
     */
    @Column(columnDefinition = "VARCHAR(150) COMMENT '评论图片路径' ", nullable = false)
    private String path;

    public String getCommentID() {
        return commentID;
    }

    public void setCommentID(String commentID) {
        this.commentID = commentID;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
