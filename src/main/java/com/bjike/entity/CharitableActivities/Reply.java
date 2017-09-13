package com.bjike.entity.CharitableActivities;

import com.bjike.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 回复表
 *
 * @Author: [dengjunren]
 * @Date: [2017-08-36 18:17]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "charitableactivities_reply")
public class Reply extends BaseEntity {
    @Column(columnDefinition = "VARCHAR(36) COMMENT '回复人' ")
    private String replyID;

    @Column(columnDefinition = "VARCHAR(255) COMMENT '回复内容' ")
    private String content;

    @Column(columnDefinition = "VARCHAR(255) COMMENT '回复的评论id' ")
    private String commentID;

    public String getReplyID() {
        return replyID;
    }

    public void setReplyID(String replyID) {
        this.replyID = replyID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCommentID() {
        return commentID;
    }

    public void setCommentID(String commentID) {
        this.commentID = commentID;
    }
}
