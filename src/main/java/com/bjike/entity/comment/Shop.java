package com.bjike.entity.comment;

import com.bjike.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 店铺
 *
 * @Author: [liguiqin]
 * @Date: [2017-06-28 14:01]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "shop")
public class Shop extends BaseEntity {
    /**
     * 地址
     */
    @Column(columnDefinition = "VARCHAR(100) COMMENT '地址' ")
    private String address;
    /**
     * 地点id
     */
    @Column(columnDefinition = " VARCHAR(100) COMMENT '地点id' ", nullable = false)

    private String pointId;

    /**
     * 经度
     */
    @Column(columnDefinition = " VARCHAR(100) COMMENT '经度' ", nullable = false)
    private String longitude;

    /**
     * 纬度
     */
    @Column(columnDefinition = "VARCHAR(100) COMMENT '经度' ", nullable = false)
    private String latitude;


    /**
     * 店铺名
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT '店铺名' ", nullable = false)
    private String name;

    /**
     * 排序序列
     */
    @Column(columnDefinition = "TINYINT COMMENT '店铺名' ", nullable = false)
    private Integer seq;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getPointId() {
        return pointId;
    }

    public void setPointId(String pointId) {
        this.pointId = pointId;
    }
}
