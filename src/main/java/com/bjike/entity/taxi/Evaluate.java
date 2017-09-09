package com.bjike.entity.taxi;

import com.bjike.entity.BaseEntity;
import com.bjike.entity.user.User;
import com.bjike.type.comment.ScoreType;

import javax.persistence.*;

/**
 * 订单评价
 *
 * @Author: [ liguiqin ]
 * @Date: [  2017-09-06 14:50:50 ]
 * @Description: [ 订单评价 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "taxi_evaluate")
public class Evaluate extends BaseEntity {

    /**
     * 评价人
     */
    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewer_id", columnDefinition = "VARCHAR(36) COMMENT '评价人' ", nullable = false)
    private User reviewer;

    /**
     * 被评价人
     */
    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", columnDefinition = "VARCHAR(36) COMMENT '被评价人' ", nullable = false)
    private User user;

    /**
     * 订单
     */
    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", columnDefinition = "VARCHAR(36) COMMENT '订单' ", nullable = false)
    private TaxiOrder taxiOrder;

    /**
     * 评价内容
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '评价内容' ")
    private String content;
    /**
     * 评分
     */
    @Column(name = "scoreType", columnDefinition = "TINYINT(1) COMMENT '评分' ")
    private ScoreType scoreType;

    public User getReviewer() {
        return reviewer;
    }

    public void setReviewer(User reviewer) {
        this.reviewer = reviewer;
    }

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

    public ScoreType getScoreType() {
        return scoreType;
    }

    public void setScoreType(ScoreType scoreType) {
        this.scoreType = scoreType;
    }

    public TaxiOrder getTaxiOrder() {
        return taxiOrder;
    }

    public void setTaxiOrder(TaxiOrder taxiOrder) {
        this.taxiOrder = taxiOrder;
    }
}
