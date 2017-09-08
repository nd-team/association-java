package com.bjike.dto.comment;

import com.bjike.dto.BaseDTO;

/**
 * @Author: [liguiqin]
 * @Date: [2017-06-27 16:34]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ShopDTO extends BaseDTO{
    /**
     * 经度
     */
    private double longitude ;
    /**
     * 纬度
     */
    private  double latitude ;

    /**
     * 范围(千米)
     */
    private Double range=0.5;



    public Double getRange() {
        return range;
    }

    public void setRange(Double range) {
        this.range = range;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
