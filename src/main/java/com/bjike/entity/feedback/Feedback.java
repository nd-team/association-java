package com.bjike.entity.feedback;

import com.bjike.entity.BaseEntity;
import com.bjike.entity.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 反馈
 *
 * @Author: [liguiqin]
 * @Date: [2017-08-31 16:42]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "feedback")
public class Feedback extends BaseEntity {
    /**
     * 反馈人
     */
    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", columnDefinition = "VARCHAR(36) COMMENT '反馈人id' ", nullable = false)
    private User user;
    /**
     * 反馈内容
     */
    @Column(columnDefinition = "TEXT COMMENT '反馈内容' " ,nullable = false)
    private String content;


    /**
     * 处理时间
     */
    @Column(name = "handleTime", columnDefinition = "DATETIME COMMENT '处理时间' ")
    private LocalDateTime handleTime;

    /**
     * 是否已处理
     */
    @Column(name = "is_handle", columnDefinition = "TINYINT(1) DEFAULT 0 COMMENT '是否已处理'", nullable = false,insertable = false)
    private Boolean handle ;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(LocalDateTime handleTime) {
        this.handleTime = handleTime;
    }

    public Boolean getHandle() {
        return handle;
    }

    public void setHandle(Boolean handle) {
        this.handle = handle;
    }
}
