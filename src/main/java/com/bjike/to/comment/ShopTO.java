package com.bjike.to.comment;

import com.bjike.common.aspect.ADD;
import com.bjike.common.aspect.EDIT;
import com.bjike.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 店铺传输对象
 *
 * @Author: [liguiqin]
 * @Date: [2017-06-27 16:34]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */

public class ShopTO extends BaseTO {
    /**
     * 地址
     */
    @NotBlank(message = "请填写店铺地址", groups = {ADD.class, EDIT.class})
    private String address;
    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 范围(千米)
     */
    private Double range = 0.5;
    /**
     * 店铺名
     */
    @NotBlank(message = "请填写店铺名", groups = {ADD.class, EDIT.class})
    private String name;


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

    public Double getRange() {
        return range;
    }

    public void setRange(Double range) {
        this.range = range;
    }
}
