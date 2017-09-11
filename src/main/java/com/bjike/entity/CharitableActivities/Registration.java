package com.bjike.entity.CharitableActivities;

import com.bjike.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 公益活动报名信息
 *
 * @Author: [zhuangkaiqin]
 * @Date: [2017-08-36 16:45]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "charitableactivities_registration")
public class Registration extends BaseEntity {
    @Column(columnDefinition = "VARCHAR(36) COMMENT '报名人id' ", nullable = false)
    private String signUpId;

    @Column(columnDefinition = "VARCHAR(255) COMMENT '公益活动id' ", nullable = false)
    private String initiateactivitiesID;

    @Column(columnDefinition = "VARCHAR(255) COMMENT '手机' ", nullable = false)
    private String tellphone;

    @Column(columnDefinition = "VARCHAR(255) COMMENT '公司' ")
    private String company;

    @Column(columnDefinition = "VARCHAR(255) COMMENT '微信' ", nullable = false)
    private String wechat;

    @Column(columnDefinition = "TINYINT(1) COMMENT '报名状态' ")
    private Boolean statas;

    public String getSignUpId() {
        return signUpId;
    }

    public void setSignUpId(String signUpId) {
        this.signUpId = signUpId;
    }

    public String getInitiateactivitiesID() {
        return initiateactivitiesID;
    }

    public void setInitiateactivitiesID(String initiateactivitiesID) {
        this.initiateactivitiesID = initiateactivitiesID;
    }

    public String getTellphone() {
        return tellphone;
    }

    public void setTellphone(String tellphone) {
        this.tellphone = tellphone;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public Boolean getStatas() {
        return statas;
    }

    public void setStatas(Boolean statas) {
        this.statas = statas;
    }
}
