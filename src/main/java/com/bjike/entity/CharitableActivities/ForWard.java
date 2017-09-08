package com.bjike.entity.CharitableActivities;

import com.bjike.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 转发表
 *
 * @Author: [dengjunren]
 * @Date: [2017-09-01 13:48]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "charitableactivities_forward")
public class ForWard extends BaseEntity {

    @Column(columnDefinition = "VARCHAR(36) COMMENT '转发人id' ")
    private String forwarders;

    @Column(columnDefinition = "VARCHAR(36) COMMENT '公益活动id' ")
    private String initiateactivitiesID;

    public String getForwarders() {
        return forwarders;
    }

    public void setForwarders(String forwarders) {
        this.forwarders = forwarders;
    }

    public String getInitiateactivitiesID() {
        return initiateactivitiesID;
    }

    public void setInitiateactivitiesID(String initiateactivitiesID) {
        this.initiateactivitiesID = initiateactivitiesID;
    }
}
