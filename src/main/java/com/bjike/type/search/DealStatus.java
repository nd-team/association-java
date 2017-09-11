package com.bjike.type.search;

/**
 * 后台找人处理状态
 * @Author: [chenjunhao]
 * @Date: [2017-09-06 14:18]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum  DealStatus {
    /**
     * 同意
     */
    AGREE(0),
    /**
     * 拒绝
     */
    REJECT(1),
    /**
     * 待处理
     */
    WAITDEAL(2);
    private int code;

    DealStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
