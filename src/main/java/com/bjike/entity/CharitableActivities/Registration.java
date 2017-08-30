package com.bjike.entity.CharitableActivities;

import com.bjike.entity.BaseEntity;

import javax.persistence.Column;

/**
 * 公益活动报名信息
 *
 * @Author: [zhuangkaiqin]
 * @Date: [2017-08-30 16:45]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class Registration extends BaseEntity {
    @Column(columnDefinition = "VARCHAR(30) COMMENT '姓名' ", nullable = false)
    private String name;

    @Column(columnDefinition = "VARCHAR(255) COMMENT '手机' ", nullable = false)
    private String tellphone;

    @Column(columnDefinition = "VARCHAR(255) COMMENT '公司' ", nullable = false)
    private String company;

    @Column(columnDefinition = "VARCHAR(255) COMMENT '微信' ", nullable = false)
    private String wechat;

    @Column(columnDefinition = "TINYINT(1) COMMENT '报名状态' ")
    private Boolean statas;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
