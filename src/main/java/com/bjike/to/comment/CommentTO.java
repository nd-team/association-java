package com.bjike.to.comment;

import com.bjike.common.aspect.ADD;
import com.bjike.common.aspect.EDIT;
import com.bjike.to.BaseTO;
import com.bjike.type.comment.ScoreType;
import com.bjike.type.comment.VisibleType;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 点评传输对象
 *
 * @Author: [liguiqin]
 * @Date: [2017-06-28 14:44]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CommentTO extends BaseTO {


    /**
     * 点评内容
     */
    @NotBlank(message = "请填写点评内容", groups = {ADD.class, EDIT.class})
    private String content;
    /**
     * 评分
     */
    private ScoreType scoreType;
    /**
     * 地点id
     */
    @NotBlank(message = "请填写地点id", groups = {ADD.class, EDIT.class})
    private String pointId;


    /**
     * 经度
     */
    @NotBlank(message = "请填写经度坐标", groups = {ADD.class, EDIT.class})
    private String longitude;

    /**
     * 纬度
     */
    @NotBlank(message = "请填写纬度坐标", groups = {ADD.class, EDIT.class})
    private String latitude;
    /**
     * 店铺名
     */
    @NotNull(message = "请填写店铺名", groups = {ADD.class, EDIT.class})
    private String shopName;
    /**
     * 详细地址
     */
    private String address;

    /**
     * 可见范围
     */
    @NotNull(message = "请填写可见范围", groups = {ADD.class, EDIT.class})
    private VisibleType visibleType;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ScoreType getScoreType() {
        return scoreType;
    }

    public void setScoreType(ScoreType scoreType) {
        this.scoreType = scoreType;
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

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public VisibleType getVisibleType() {
        return visibleType;
    }

    public void setVisibleType(VisibleType visibleType) {
        this.visibleType = visibleType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPointId() {
        return pointId;
    }

    public void setPointId(String pointId) {
        this.pointId = pointId;
    }
}
