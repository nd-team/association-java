package com.bjike.to.taxi;

import com.bjike.common.aspect.ADD;
import com.bjike.common.aspect.EDIT;
import com.bjike.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;


/**
 * 地区费用添加更新操作对象
 *
 * @Author: [liguiqin]
 * @Date: [2017-07-19 13:58]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class AreaCostTO extends BaseTO {

    /**
     * 地区
     */
	@NotBlank(message = "请填写地区", groups = {ADD.class, EDIT.class})
	private String area;
    /**
     * 每公里单价
     */
	@NotNull(message = "请填写每公里单价", groups = {ADD.class, EDIT.class})
	private Double unitPrice;
    /**
     * 每分钟单价
     */
	@NotNull(message = "请填写每分钟单价", groups = {ADD.class, EDIT.class})
	private Double perMinute;
    /**
     * 是否启用
     */
 	private Boolean enable;

 	public String getArea () {
		return area;
	}
	public void setArea (String area) {
		this.area =area;
	}

 	public Double getUnitPrice () {
		return unitPrice;
	}
	public void setUnitPrice (Double unitPrice) {
		this.unitPrice =unitPrice;
	}

 	public Double getPerMinute () {
		return perMinute;
	}
	public void setPerMinute (Double perMinute) {
		this.perMinute =perMinute;
	}

 	public Boolean getEnable () {
		return enable;
	}
	public void setEnable (Boolean enable) {
		this.enable =enable;
	}



}
