package com.bjike.vo.chat;

import com.bjike.vo.BaseVO;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-11 17:36]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class GroupVO extends BaseVO {
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 群名
     */
    private String name;
    /**
     * 是否是自己创建
     */
    private Boolean own;
    /**
     * 群头像
     */
    private String headPath;
    /**
     * 群介绍
     */
    private String description;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getOwn() {
        return own;
    }

    public void setOwn(Boolean own) {
        this.own = own;
    }

    public String getHeadPath() {
        return headPath;
    }

    public void setHeadPath(String headPath) {
        this.headPath = headPath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
