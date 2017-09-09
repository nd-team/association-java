package com.bjike.entity.user;

import com.bjike.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * 签到
 *
 * @Author: [liguiqin]
 * @Date: [2017-08-29 17:26]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "user_sign")
public class Sign extends BaseEntity {
    /**
     * 签到用户
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", columnDefinition = "VARCHAR(36) COMMENT '签到用户id' ", nullable = false)
    private User user;


    /**
     * 签到日期
     */
    @Column(name = "signDate", columnDefinition = " DATE  COMMENT '签到日期' ", nullable = false)
    private LocalDate signDate =LocalDate.now();

    /**
     * 签到次数
     */
    @Column(name = "signCount", columnDefinition = "int COMMENT '连续签到次数' ", nullable = false)
    private Integer signCount = 0;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getSignDate() {
        return signDate;
    }

    public void setSignDate(LocalDate signDate) {
        this.signDate = signDate;
    }

    public Integer getSignCount() {
        return signCount;
    }

    public void setSignCount(Integer signCount) {
        this.signCount = signCount;
    }
}
