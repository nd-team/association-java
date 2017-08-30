package com.bjike.entity.CharitableActivities;

import com.bjike.entity.BaseEntity;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * 评论表
 *
 * @Author: [zhuangkaiqin]
 * @Date: [2017-08-30 17:12]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class Comment extends BaseEntity {
    @Column(columnDefinition = "VARCHAR(30) COMMENT '评论人' ")
    private String commentator;

//    @Column(columnDefinition = "VARCHAR(255) COMMENT '回复人' ")
//    private Set<String> reply;

//    @Column(columnDefinition = "VARCHAR(255) COMMENT '点赞人' ")
//    private Set<String> praise;

    @Column(columnDefinition = "BIGINT(20) COMMENT '评论数' ")
    private Long commentsNum;

    @Column(columnDefinition = "VARCHAR(255) COMMENT '评论内容' ")
    private String comments;

    @Column(columnDefinition = "DATETIME COMMENT '评论时间' ")
    private LocalDateTime commentTime;

    @Column(columnDefinition = "BIGINT(20) COMMENT '评论获赞数' ")
    private Long approved;

    @Column(columnDefinition = "VARCHAR(255) COMMENT '评论的主题' ")
    private String commentSubject;

    public String getCommentator() {
        return commentator;
    }

    public void setCommentator(String commentator) {
        this.commentator = commentator;
    }

    public Long getCommentsNum() {
        return commentsNum;
    }

    public void setCommentsNum(Long commentsNum) {
        this.commentsNum = commentsNum;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public LocalDateTime getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(LocalDateTime commentTime) {
        this.commentTime = commentTime;
    }

    public Long getApproved() {
        return approved;
    }

    public void setApproved(Long approved) {
        this.approved = approved;
    }

    public String getCommentSubject() {
        return commentSubject;
    }

    public void setCommentSubject(String commentSubject) {
        this.commentSubject = commentSubject;
    }
}
