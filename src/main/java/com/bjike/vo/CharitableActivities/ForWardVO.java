package com.bjike.vo.CharitableActivities;

import com.bjike.vo.BaseVO;

/**
 * @Author: [dengjunren]
 * @Date: [2017-09-02 08:40]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ForWardVO extends BaseVO {

    /**
     * 转发人id
     */
    private String forwarders;

    /**
     * 转发人
     */
    private String forwardersName;

    /**
     * 转发人头像
     */
    private String forwardersPathHead;

    /**
     * 公益活动id
     */
    private String initiateactivitiesID;

    /**
     * 公益活动主题
     */
    private String initiateactivities;

    public String getForwarders() {
        return forwarders;
    }

    public void setForwarders(String forwarders) {
        this.forwarders = forwarders;
    }

    public String getForwardersName() {
        return forwardersName;
    }

    public void setForwardersName(String forwardersName) {
        this.forwardersName = forwardersName;
    }

    public String getInitiateactivitiesID() {
        return initiateactivitiesID;
    }

    public void setInitiateactivitiesID(String initiateactivitiesID) {
        this.initiateactivitiesID = initiateactivitiesID;
    }

    public String getInitiateactivities() {
        return initiateactivities;
    }

    public void setInitiateactivities(String initiateactivities) {
        this.initiateactivities = initiateactivities;
    }

    public String getForwardersPathHead() {
        return forwardersPathHead;
    }

    public void setForwardersPathHead(String forwardersPathHead) {
        this.forwardersPathHead = forwardersPathHead;
    }
}
