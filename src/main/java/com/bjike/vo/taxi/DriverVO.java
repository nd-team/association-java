package com.bjike.vo.taxi;

import com.bjike.vo.BaseVO;


/**
 * 申请司机信息
 *
 * @Author: [liguiqin]
 * @Date: [2017-07-19 13:58]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DriverVO extends BaseVO {
    /**
     * 城市
     */

    private String address;
    /**
     * 身份证
     */
    private String idCard;
    /**
     * 车型
     */
    private String motorcycleType;
    /**
     * 车牌
     */
    private String plateNumber;

    /**
     * 车主姓名
     */
    private String carUsername;
    /**
     * 司机姓名
     */
    private String driverUsername;
    /**
     * 第一次获得驾照日期
     */
    private String gainDate ;


    /**
     * 车辆注册日期
     */
    private String registerDate ;

    /**
     * 驾驶证照片
     */
    private String[] images;


    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getMotorcycleType() {
        return motorcycleType;
    }

    public void setMotorcycleType(String motorcycleType) {
        this.motorcycleType = motorcycleType;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getCarUsername() {
        return carUsername;
    }

    public void setCarUsername(String carUsername) {
        this.carUsername = carUsername;
    }

    public String getDriverUsername() {
        return driverUsername;
    }

    public void setDriverUsername(String driverUsername) {
        this.driverUsername = driverUsername;
    }

    public String getGainDate() {
        return gainDate;
    }

    public void setGainDate(String gainDate) {
        this.gainDate = gainDate;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }
}
