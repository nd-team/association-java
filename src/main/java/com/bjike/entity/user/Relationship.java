package com.bjike.entity.user;

import com.bjike.entity.BaseEntity;
import com.bjike.entity.user.other.FriendChain;
import com.bjike.type.Status;
import com.bjike.type.user.RelationshipType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 好友关系
 *
 * @Author: [liguiqin]
 * @Date: [2017-07-10 14:43]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "friends_relationship")
public class Relationship extends BaseEntity {
    /**
     * 用户
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", columnDefinition = "VARCHAR(36) COMMENT '用户id' ", nullable = false)
    private User user;
    /**
     * 朋友
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "friend_id", columnDefinition = "VARCHAR(36) COMMENT '朋友id' ", nullable = false)
    private User friend;
    /**
     * 朋友关系
     */
    @Column(columnDefinition = "TINYINT(1)  COMMENT '朋友关系' ", nullable = false)
    private RelationshipType relationshipType;
    /**
     * 状态
     */
    @Column(columnDefinition = "TINYINT(1) DEFAULT 0 COMMENT '状态' ", nullable = false, insertable = false)
    private Status status;

    /**
     * 关系链列表
     */
    @Transient
    private List<List<FriendChain>> friendChains = new ArrayList<>();

    @Transient
    private boolean yourFriend = false;

    public List<List<FriendChain>> getFriendChains() {
        return friendChains;
    }

    public void setFriendChains(List<List<FriendChain>> friendChains) {
        this.friendChains = friendChains;
    }

    public boolean isYourFriend() {
        return yourFriend;
    }

    public void setYourFriend(boolean yourFriend) {
        this.yourFriend = yourFriend;
    }

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

    public RelationshipType getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(RelationshipType relationshipType) {
        this.relationshipType = relationshipType;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
