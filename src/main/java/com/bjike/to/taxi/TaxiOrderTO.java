package com.bjike.to.taxi;

import com.bjike.to.BaseTO;
import com.bjike.type.taxi.OrderStatus;
import org.hibernate.validator.constraints.NotBlank;


/**
 * 叫车订单 添加更新操作对象
 *
 * @Author: [liguiqin]
 * @Date: [2017-07-19 13:58]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class TaxiOrderTO extends BaseTO {

    public interface PUBLISH {

    }

    /**
     * 经度
     */
    @NotBlank(message = "请填写经度", groups = {TaxiOrderTO.PUBLISH.class})
    private String longitude;
    /**
     * 纬度
     */
    @NotBlank(message = "请填写纬度", groups = {TaxiOrderTO.PUBLISH.class})
    private String latitude;
    /**
     * 订单状态
     */
    private OrderStatus status;

    /**
     * 估计费用
     */
    private Double cost;
    /**
     * 每公里单价
     */
    private Double unitPrice;
    /**
     * 起始地
     */
    @NotBlank(message = "请填写起始地", groups = {TaxiOrderTO.PUBLISH.class})
    private String startPoint;
    /**
     * 目的地
     */
    @NotBlank(message = "请填写目的地", groups = {TaxiOrderTO.PUBLISH.class})
    private String destination;


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

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }


}
