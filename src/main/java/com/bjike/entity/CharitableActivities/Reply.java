package com.bjike.entity.CharitableActivities;

import com.bjike.entity.BaseEntity;

import javax.persistence.Column;

/**
 * @Author: [dengjunren]
 * @Date: [2017-08-30 18:17]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class Reply extends BaseEntity {
    @Column(columnDefinition = "VARCHAR(255) COMMENT '回复人' ")
    private String name;

    private
}
