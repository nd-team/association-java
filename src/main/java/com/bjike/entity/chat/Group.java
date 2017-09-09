package com.bjike.entity.chat;

import com.bjike.entity.BaseEntity;
import com.bjike.entity.user.User;

import javax.persistence.*;

/**
 * 聊天群
 *
 * @Author: [liguiqin]
 * @Date: [2017-07-19 15:04]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "chat_group")
public class Group extends BaseEntity {
    /**
     * 聊天室名称
     */
    @Column(columnDefinition = "VARCHAR(36) COMMENT '聊天室名称' ", nullable = false)
    private String name;

    /**
     * 创建人
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", columnDefinition = "VARCHAR(36) COMMENT '创建人id' ", nullable = false)
    private User user;

    /**
     * 群头像
     */
    @Column(columnDefinition = "VARCHAR(200) COMMENT '群头像' ")
    private String headPath;

    /**
     * 群描述
     */
    @Column(columnDefinition = "VARCHAR(500) COMMENT '群描述' ")
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getHeadPath() {
        return headPath;
    }

    public void setHeadPath(String headPath) {
        this.headPath = headPath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
