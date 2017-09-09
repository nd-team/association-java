package com.bjike.entity.taxi;

import com.bjike.entity.BaseEntity;
import com.bjike.entity.user.User;
import com.bjike.type.taxi.VerifyType;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * 司机信息
 *
 * @Author: [liguiqin]
 * @Date: [2017-09-02 09:17]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "taxi_driver")
public class Driver extends BaseEntity {
    /**
     * 所属人
     */
    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", columnDefinition = "VARCHAR(36) COMMENT '所属人id' ", nullable = false)
    private User user;
    /**
     * 城市
     */
    @Column(columnDefinition = "VARCHAR(100) COMMENT '城市' ", nullable = false)

    private String address;
    /**
     * 身份证
     */
    @Column(columnDefinition = "VARCHAR(18) COMMENT '身份证' ", nullable = false)
    private String idCard;
    /**
     * 车型
     */
    @Column(columnDefinition = "VARCHAR(100) COMMENT '车型' ", nullable = false)
    private String motorcycleType;
    /**
     * 车牌
     */
    @Column(columnDefinition = "VARCHAR(8) COMMENT '车牌' ", nullable = false)
    private String plateNumber;
    /**
     * 车主姓名
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT '车主姓名' ", nullable = false)
    private String carUsername;
    /**
     * 司机姓名
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT '车主姓名' ", nullable = false)
    private String driverUsername;
    /**
     * 第一次获得驾照日期
     */

    @Column(name = "gainDate", columnDefinition = "DATE  COMMENT '第一次获得驾照日期'", nullable = false)
    private LocalDate gainDate;


    /**
     * 车辆注册日期
     */

    @Column(name = "registerDate", columnDefinition = "DATE  COMMENT '车辆注册日期'", nullable = false)
    private LocalDate registerDate;


    /**
     * 审核
     */
    @Column(columnDefinition = "TINYINT(2) DEFAULT 0 COMMENT '审核状态'", nullable = false,insertable = false)
    private VerifyType verifyType;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public VerifyType getVerifyType() {
        return verifyType;
    }

    public void setVerifyType(VerifyType verifyType) {
        this.verifyType = verifyType;
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

    public LocalDate getGainDate() {
        return gainDate;
    }

    public void setGainDate(LocalDate gainDate) {
        this.gainDate = gainDate;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }
}
