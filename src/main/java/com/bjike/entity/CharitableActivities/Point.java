package com.bjike.entity.CharitableActivities;

import com.bjike.entity.BaseEntity;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * @Author: [dengjunren]
 * @Date: [2017-08-30 17:51]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class Point extends BaseEntity {
    @Column(columnDefinition = "VARCHAR(255) COMMENT '点赞人' ")
    private String praise;

    @Column(columnDefinition = "DATETIME COMMENT '点赞时间' ")
    private LocalDateTime pointTime;

    @Column(columnDefinition = "VARCHAR(255) COMMENT '被点赞人' ")
    private String name;

    @Column(columnDefinition = "BIGINT(20) COMMENT '被点赞人的点赞数' ")
    private Long pointNum;

    public String getPraise() {
        return praise;
    }

    public void setPraise(String praise) {
        this.praise = praise;
    }

    public LocalDateTime getPointTime() {
        return pointTime;
    }

    public void setPointTime(LocalDateTime pointTime) {
        this.pointTime = pointTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPointNum() {
        return pointNum;
    }

    public void setPointNum(Long pointNum) {
        this.pointNum = pointNum;
    }
}
