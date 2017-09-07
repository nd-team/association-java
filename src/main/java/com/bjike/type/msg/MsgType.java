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
     * 系统
     */
    SYS(0),
    /**
     * 点对点
     */
    POINT(1),
    /**
     * 群发
     */
    GROUP(2),
    /**
     * 上线通知
     */
    ONLINE(3),
    /**
     * 下线通知
     */
    OFFLINE(4),
    /**
     * 加好友通知
     */
    FRIEND(5),;
    private int code;

    MsgType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
