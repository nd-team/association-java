package com.bjike.entity.search;

import com.bjike.entity.BaseEntity;
import com.bjike.type.search.DealStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 后台找人
 *
 * @Author: [chenjunhao]
 * @Date: [2017-09-06 13:49]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "search_search")
public class Search extends BaseEntity {
    /**
     * 要找的人id
     */
    @Column(columnDefinition = "VARCHAR(36) COMMENT '要找的人id' ", nullable = false)
    private String userId;
    /**
     * 操作者
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '操作者' ", nullable = false)
    private String name;
    /**
     * 处理状态
     */
    @Column(name = "dealStatus", columnDefinition = "TINYINT(2) COMMENT '处理状态'", nullable = false)
    private DealStatus dealStatus;

    public DealStatus getDealStatus() {
        return dealStatus;
    }

    public void setDealStatus(DealStatus dealStatus) {
        this.dealStatus = dealStatus;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
