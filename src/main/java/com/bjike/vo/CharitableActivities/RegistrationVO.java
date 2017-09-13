package com.bjike.vo.CharitableActivities;

import com.bjike.vo.BaseVO;

/**
 * 公益活动报名信息
 *
 * @Author: [zhuangkaiqin]
 * @Date: [2017-08-36 16:45]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class RegistrationVO extends BaseVO {
    /**
     * 报名人id
     */
    private String signUpId;

    /**
     * 报名人姓名
     */
    private String name;

    /**
     * 报名人头像
     */
    private String signUpPathHead;

    /**
     * 公益活动id
     */
    private String initiateactivitiesID;

    /**
     * 手机
     */
    private String tellphone;

    /**
     * 公司
     */
    private String company;

    /**
     * 微信
     */
    private String wechat;

    /**
     * 报名状态
     */
    private Boolean statas;

    public String getSignUpId() {
        return signUpId;
    }

    public void setSignUpId(String signUpId) {
        this.signUpId = signUpId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getSignUpPathHead() {
        return signUpPathHead;
    }

    public void setSignUpPathHead(String signUpPathHead) {
        this.signUpPathHead = signUpPathHead;
    }
}
