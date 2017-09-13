package com.bjike.entity.CharitableActivities;

import com.bjike.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 消息表
 *
 * @Author: [dengjunren]
 * @Date: [2017-09-01 14:58]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "charitableactivities_news")
public class News extends BaseEntity {

    @Column(columnDefinition = "VARCHAR(36) COMMENT '消息接受人id' ", nullable = false)
    private String messageID;

    @Column(columnDefinition = "VARCHAR(255) COMMENT '消息内容' ", nullable = false)
    private String content;

    @Column(columnDefinition = "VARCHAR(36) COMMENT '发起人' ", nullable = false)
    private String sponsorID;

    @Column(columnDefinition = "TINYINT(1) COMMENT '是否已读' ")
    private Boolean isRead;

    @Column(columnDefinition = "VARCHAR(36) COMMENT '消息对象id(评论/点赞内容)' ", nullable = false)
    private String objID;

    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
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
}
