package com.bjike.entity.activity;

import com.bjike.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * 活动评论
 *
 * @Author: [ chenjunhao ]
 * @Date: [  2017-09-04 13:58:03 ]
 * @Description: [ 活动评论 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "activity_activityComment")
public class ActivityComment extends BaseEntity {

    /**
     * 活动id
     */
    @Column(columnDefinition = "VARCHAR(36) COMMENT '活动id' ")
    private String activityId;
    /**
     * 评论人id
     */
    @Column(columnDefinition = "VARCHAR(36) COMMENT '评论人id' ", nullable = false)
    private String userId;
    /**
     * 评论内容
     */
    @Column(columnDefinition = "TEXT COMMENT '评论内容' ", nullable = false)
    private String content;
    /**
     * 评论时间
     */
    @Column(columnDefinition = "DATETIME COMMENT '评论时间' ", nullable = false)
    private LocalDateTime time;
    /**
     * 回复评论id
     */
    @Column(columnDefinition = "VARCHAR(36) COMMENT '回复评论id' ")
    private String repayId;

    public String getRepayId() {
        return repayId;
    }

    public void setRepayId(String repayId) {
        this.repayId = repayId;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }


}
