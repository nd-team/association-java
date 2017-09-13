package com.bjike.vo.CharitableActivities;

import com.bjike.vo.BaseVO;

/**
 * @Author: [dengjunren]
 * @Date: [2017-09-02 08:43]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class NewsVO extends BaseVO {

    /**
     * 消息接受人id
     */
    private String messageID;

    /**
     * 消息接收人
     */
    private String message;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 发起人id
     */
    private String sponsorID;

    /**
     * 发起人
     */
    private String sponsor;

    /**
     * 是否已读
     */
    private Boolean isRead;

    /**
     * 消息对象id(评论/点赞对象)
     */
    private String objID;

    /**
     * 消息生成时间
     */
    private String createTime;

    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSponsorID() {
        return sponsorID;
    }

    public void setSponsorID(String sponsorID) {
        this.sponsorID = sponsorID;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

    public String getObjID() {
        return objID;
    }

    public void setObjID(String objID) {
        this.objID = objID;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
