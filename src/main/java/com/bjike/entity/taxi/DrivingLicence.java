package com.bjike.entity.taxi;

import com.bjike.entity.BaseEntity;

import javax.persistence.*;

/**
 * 测试实体
 *
 * @Author:	[ liguiqin ]
 * @Date: [  2017-09-02 15:13:45 ]
 * @Description: [ 测试实体 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "taxi_driving_licence")
public class DrivingLicence extends BaseEntity{

    /**
     * 所属人
     */
	@ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
	@JoinColumn(name = "driver_id", columnDefinition = "VARCHAR(36) COMMENT '所属人' ", nullable = false)
 	private Driver driver;
    /**
     * 驾驶证照片
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '驾驶证照片' ")
 	private String image;

 	public Driver getDriver () {
		return driver;
	}
	public void setDriver (Driver driver) {
		this.driver =driver;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
