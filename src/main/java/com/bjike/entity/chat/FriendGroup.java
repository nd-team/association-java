package com.bjike.entity.chat;

import com.bjike.entity.BaseEntity;
import com.bjike.entity.user.User;

import javax.persistence.*;

/**
 * 好友分组
 *
 * @Author: [liguiqin]
 * @Date: [2017-07-19 15:07]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "chat_friend_group")
public class FriendGroup extends BaseEntity {
    /**
     * 分组名
     */
    @Column(columnDefinition = "VARCHAR(36) COMMENT '朋友分组名' ", nullable = false)
    private String name;
    /**
     * 归属人
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id",  columnDefinition = "VARCHAR(36) COMMENT '用户id' ", nullable = false)
    private User user;

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
}
