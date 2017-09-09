package com.bjike.entity.comment;

import com.bjike.entity.BaseEntity;
import com.bjike.entity.user.User;

import javax.persistence.*;

/**
 * 点赞
 *
 * @Author: [liguiqin]
 * @Date: [2017-07-01 16:25]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "comment_likes")
public class Likes extends BaseEntity {
    /**
     * 点评
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "comment_id", columnDefinition = "VARCHAR(36) COMMENT '点评id' ", nullable = false)
    private Comment comment;
    /**
     * 点赞人
     */

    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", columnDefinition = "VARCHAR(36) COMMENT '点评人' ", nullable = false)
    private User user;

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
