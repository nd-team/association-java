package com.bjike.to.CharitableActivities;

import com.bjike.common.aspect.ADD;
import com.bjike.common.aspect.EDIT;
import com.bjike.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Author: [dengjunren]
 * @Date: [2017-08-31 11:46]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class RegistrationTO extends BaseTO {

    /**
     * 报名人id
     */
    private String signUpId;

    /**
     * 报名的活动主题
     */
    @NotBlank(message = "报名的公益活动id不能为空", groups = {ADD.class, EDIT.class})
    private String initiateactivitiesID;

    /**
     * 手机
     */
    @NotBlank(message = "手机不能为空", groups = {ADD.class, EDIT.class})
    private String tellphone;

    /**
     * 公司
     */
//    @NotBlank(message = "公司不能为空", groups = {ADD.class})
    private String company;

    /**
     * 微信
     */
    @NotBlank(message = "微信不能为空", groups = {ADD.class, EDIT.class})
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
