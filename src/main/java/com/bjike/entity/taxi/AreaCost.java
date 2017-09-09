package com.bjike.entity.taxi;

import com.bjike.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 地区费用
 *
 * @Author: [ liguiqin ]
 * @Date: [  2017-09-05 10:15:43 ]
 * @Description: [ 打车费用 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "taxi_area_cost")
public class AreaCost extends BaseEntity {

    /**
     * 地区
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '地区' ",unique = true)
    private String area;
    /**
     * 每公里单价
     */
    @Column(columnDefinition = "DECIMAL(5,2) COMMENT '每公里单价' ")
    private Double unitPrice;
    /**
     * 每分钟单价
     */
    @Column(columnDefinition = "DECIMAL(5,2) COMMENT '每分钟单价' ")
    private Double perMinute;
    /**
     * 是否启用
     */
    @Column(name = "is_enable", columnDefinition = "TINYINT(1) DEFAULT 0 COMMENT '是否启用'",nullable = false,insertable = false)
    private Boolean enable;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getPerMinute() {
        return perMinute;
    }

    public void setPerMinute(Double perMinute) {
        this.perMinute = perMinute;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }


}
