package com.bjike.entity.chat;

import com.bjike.entity.BaseEntity;
import com.bjike.entity.user.User;
import com.bjike.type.chat.ApplyType;

import javax.persistence.*;

/**
 * 好友
 *
 * @Author: [liguiqin]
 * @Date: [2017-07-21 11:37]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "chat_friend")
public class Friend extends BaseEntity {
    /**
     * 归属人
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id",  columnDefinition = "VARCHAR(36) COMMENT '用户id' ", nullable = false)
    private User user;
    /**
     * 朋友id
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "friend_id", columnDefinition = "VARCHAR(36) COMMENT '朋友id' ", nullable = false)
    private User friend;

    /**
     * 朋友备注
     */
    @Column(columnDefinition = "VARCHAR(36) COMMENT '朋友备注' ")
    private String remark;
    /**
     * 朋友所在分组
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "friend_group_id", columnDefinition = "VARCHAR(36) COMMENT '朋友所在分组id' ")
    private FriendGroup friendGroup;
    /**
     * 朋友申请状态
     */
    @Column(columnDefinition = "TINYINT(2) DEFAULT 0 COMMENT '申请状态'", nullable = false, insertable = false)
    private ApplyType applyType;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getFriend() {
        return friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }

    public FriendGroup getFriendGroup() {
        return friendGroup;
    }

    public void setFriendGroup(FriendGroup friendGroup) {
        this.friendGroup = friendGroup;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public ApplyType getApplyType() {
        return applyType;
    }

    public void setApplyType(ApplyType applyType) {
        this.applyType = applyType;
    }
}
