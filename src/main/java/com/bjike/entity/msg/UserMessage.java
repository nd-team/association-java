package com.bjike.entity.msg;


import com.bjike.entity.BaseEntity;
import com.bjike.entity.user.User;

import javax.persistence.*;


/**
 * 用户消息
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-03-21 09:40 ]
 * @Description: [ 用户消息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "msg_user_message")
public class UserMessage extends BaseEntity {

    /**
     * 所属人
     */
    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", columnDefinition = "VARCHAR(36) COMMENT '所属人id' ", nullable = false)
    private User user;

    /**
     * 消息id
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "message_id", columnDefinition = "VARCHAR(36) COMMENT '消息id' ", nullable = false)
    private Message message;
    /**
     * 是否已经阅读
     */
    @Column(name = "is_read", columnDefinition = "TINYINT(1) DEFAULT 0 COMMENT '是否已经阅读'", nullable = false, insertable = false)
    private Boolean read;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }
}