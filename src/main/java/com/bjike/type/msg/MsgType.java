package com.bjike.type.msg;

/**
 * 消息类型
 *
 * @Author: [liguiqin]
 * @Date: [2017-03-14 10:48]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum MsgType {

    /**
     * 系统通知
     */
    SYS(0),
    /**
     * 消息通知
     */
    MSG(1),
    /**
     * 邮件通知
     */
    MAIL(2),
    /**
     * 消息邮件通知
     */
    MSG_MAIL(3),
    /**
     * 点对点
     */
    POINT(4),
    /**
     * 群发
     */
    GROUP(5),
    /**
     * 上线通知
     */
    ONLINE(6),
    /**
     * 下线通知
     */
    OFFLINE(7),
    /**
     * 加好友通知
     */
    FRIEND(8),;
    private int code;

    MsgType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
