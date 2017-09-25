package com.bjike.to.taxi;

import com.bjike.common.aspect.ADD;
import com.bjike.common.aspect.EDIT;
import com.bjike.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import java.time.LocalDate;

/**
 * 司机传输实体
 *
 * @Author: [liguiqin]
 * @Date: [2017-07-19 13:58]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DriverTO extends BaseTO {
    /**
     * 城市
     */
    @NotBlank(message = "请填写城市", groups = {ADD.class, EDIT.class})
    private String address;
    /**
     * 身份证
     */
    @NotBlank(message = "请填写身份证", groups = {ADD.class, EDIT.class})
    private String idCard;
    /**
     * 车型
     */
    @NotBlank(message = "请填写车型", groups = {ADD.class, EDIT.class})
    private String motorcycleType;
    /**
     * 车牌
     */
    @NotBlank(message = "请填写车牌", groups = {ADD.class, EDIT.class})
    private String plateNumber;

    /**
     * 车主姓名
     */
    @NotBlank(message = "请填写车主姓名", groups = {ADD.class, EDIT.class})
    private String carUsername;
    /**
     * 司机姓名
     */
    @NotBlank(message = "请填写司机姓名", groups = {ADD.class, EDIT.class})
    private String driverUsername;
    /**
     * 第一次获得驾照日期
     */
    @NotBlank(message = "请填写第一次获得驾照日期", groups = {ADD.class, EDIT.class})
    private String gainDate ;


    /**
     * 车辆注册日期
     */
    @NotBlank(message = "请填写车辆注册日期", groups = {ADD.class, EDIT.class})
    private String registerDate ;

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
