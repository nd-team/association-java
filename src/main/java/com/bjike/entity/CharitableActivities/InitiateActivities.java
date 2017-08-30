package com.bjike.entity.CharitableActivities;

import com.bjike.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * 公益活动发起
 *
 * @Author: [zhuangkaiqin]
 * @Date: [2017-08-30 15:30]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "initiateActivities")
public class InitiateActivities extends BaseEntity {

    @Column(columnDefinition = "VARCHAR(30) COMMENT '发起人' ", nullable = false)
    private String sponsor;

    @Column(columnDefinition = "VARCHAR(255) COMMENT '活动主题名称' ", nullable = false)
    private String activitySubject;

    @Column(columnDefinition = "DATETIME COMMENT '活动时间' ", nullable = false)
    private LocalDateTime activityTime;

    @Column(columnDefinition = "DATETIME COMMENT '报名截止时间' ", nullable = false)
    private LocalDateTime deadline;

    @Column(columnDefinition = "VARCHAR(255) COMMENT '出发地' ")
    private String departure;

    @Column(columnDefinition = "VARCHAR(255) COMMENT '活动地点' ")
    private String eventLocation;

    @Column(columnDefinition = "VARCHAR(255) COMMENT '人数限制' ")
    private String numberLimit;

    @Column(columnDefinition = "VARCHAR(255) COMMENT '联系电话' ")
    private String phone;

    @Column(columnDefinition = "VARCHAR(255) COMMENT '姓名' ")
    private String name;

    @Column(columnDefinition = "VARCHAR(255) COMMENT '报名费' ")
    private String registeryFee;

    @Column(columnDefinition = "VARCHAR(255) COMMENT '费用说明' ")
    private String costDescription;

    @Column(columnDefinition = "VARCHAR(255) COMMENT '参与须知' ")
    private String participation;

    @Column(columnDefinition = "VARCHAR(255) COMMENT '活动行程' ")
    private String activityItinerary;

    @Column(columnDefinition = "VARCHAR(255) COMMENT '活动详情' ")
    private String detail;

    @Column(columnDefinition = "TINYINT(1) COMMENT '活动状态' ")
    private Boolean status;

    @Column(columnDefinition = "BIGINT(20) COMMENT '已报名数' ")
    private Long num;

    @Column(columnDefinition = "BIGINT(20) COMMENT '转发数' ")
    private Long forwardingNumber;

    @Column(columnDefinition = "BIGINT(20) COMMENT '点赞数' ")
    private Long praiseNumber;

    @Column(columnDefinition = "BIGINT(20) COMMENT '评论数' ")
    private Long commentsNumber;

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public String getActivitySubject() {
        return activitySubject;
    }

    public void setActivitySubject(String activitySubject) {
        this.activitySubject = activitySubject;
    }

    public LocalDateTime getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(LocalDateTime activityTime) {
        this.activityTime = activityTime;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getNumberLimit() {
        return numberLimit;
    }

    public void setNumberLimit(String numberLimit) {
        this.numberLimit = numberLimit;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegisteryFee() {
        return registeryFee;
    }

    public void setRegisteryFee(String registeryFee) {
        this.registeryFee = registeryFee;
    }

    public String getCostDescription() {
        return costDescription;
    }

    public void setCostDescription(String costDescription) {
        this.costDescription = costDescription;
    }

    public String getParticipation() {
        return participation;
    }

    public void setParticipation(String participation) {
        this.participation = participation;
    }

    public String getActivityItinerary() {
        return activityItinerary;
    }

    public void setActivityItinerary(String activityItinerary) {
        this.activityItinerary = activityItinerary;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public Long getForwardingNumber() {
        return forwardingNumber;
    }

    public void setForwardingNumber(Long forwardingNumber) {
        this.forwardingNumber = forwardingNumber;
    }

    public Long getPraiseNumber() {
        return praiseNumber;
    }

    public void setPraiseNumber(Long praiseNumber) {
        this.praiseNumber = praiseNumber;
    }

    public Long getCommentsNumber() {
        return commentsNumber;
    }

    public void setCommentsNumber(Long commentsNumber) {
        this.commentsNumber = commentsNumber;
    }
}
