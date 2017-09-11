package com.bjike.vo.search;

import com.bjike.type.search.DealStatus;
import com.bjike.vo.BaseVO;

/**
 * 找人对象
 *
 * @Author: [chenjunhao]
 * @Date: [2017-09-06 10:44]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class FindVO extends BaseVO {
    /**
     * 用户id
     */
    private String userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 编号
     */
    private String number;
    /**
     * 头像
     */
    private String headPath;
    /**
     * 想去认识人的那个人的姓名
     */
    private String name;
    /**
     * 是否为好友
     */
    private Boolean friend;
    /**
     * 处理状态
     */
    private DealStatus dealStatus;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public DealStatus getDealStatus() {
        return dealStatus;
    }

    public void setDealStatus(DealStatus dealStatus) {
        this.dealStatus = dealStatus;
    }

    public Boolean getFriend() {
        return friend;
    }

    public void setFriend(Boolean friend) {
        this.friend = friend;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getHeadPath() {
        return headPath;
    }

    public void setHeadPath(String headPath) {
        this.headPath = headPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
