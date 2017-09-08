package com.bjike.entity.chat;

import com.bjike.entity.BaseEntity;
import com.bjike.type.chat.MsgCategory;
import com.bjike.type.msg.MsgType;

/**
 * 消息
 *
 * @Author: [liguiqin]
 * @Date: [2017-07-19 14:31]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class Msg extends BaseEntity {
    /**
     * 发送人
     */
    private String senderName;
    /**
     * 发送人id
     */
    private String senderId;
    /**
     * 发送人头像
     */
    private String senderHeadPath;
    /**
     * 发送标题
     */
    private String title;
    /**
     * 发送内容
     */
    private String content;
    /**
     * 消息类型
     */
    private MsgType msgType;
    /**
     * 消息分类
     */
    private MsgCategory msgCategory;

    /**
     * 接收组
     */
    private String group;

    /**
     * 接收人
     */
    private String receiver;

    /**
     * 上下线用户
     */
    private String userId;

    /**
     * 是否为已读消息
     */
    private boolean read;

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getSenderHeadPath() {
        return senderHeadPath;
    }

    public void setSenderHeadPath(String senderHeadPath) {
        this.senderHeadPath = senderHeadPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MsgType getMsgType() {
        return msgType;
    }

    public void setMsgType(MsgType msgType) {
        this.msgType = msgType;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public MsgCategory getMsgCategory() {
        return msgCategory;
    }

    public void setMsgCategory(MsgCategory msgCategory) {
        this.msgCategory = msgCategory;
    }
}
