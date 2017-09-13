package com.bjike.entity.chat;

import com.bjike.entity.BaseEntity;
import com.bjike.entity.user.User;

import javax.persistence.*;

/**
 * 群成员
 *
 * @Author: [liguiqin]
 * @Date: [2017-07-19 15:11]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "chat_group_member")
public class GroupMember extends BaseEntity {
    /**
     * 归属群
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "group_id", columnDefinition = "VARCHAR(36) COMMENT '群id' ", nullable = false)
    private Group group;
    /**
     * 群成员id
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", unique = true, columnDefinition = "VARCHAR(36) COMMENT '群成员id' ", nullable = false)
    private User user;


    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
