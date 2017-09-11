package com.bjike.to.CharitableActivities;

import com.bjike.common.aspect.ADD;
import com.bjike.common.aspect.EDIT;
import com.bjike.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @Author: [dengjunren]
 * @Date: [2017-08-31 09:00]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class InitiateActivitiesTO extends BaseTO {

    /**
     * 发起人
     */
    private String sponsor;

    /**
     * 活动主题名称
     */
    @NotBlank(message = "活动主题名称不能为空", groups = {ADD.class, EDIT.class})
    private String activitySubject;

    /**
     * 活动开始时间
     */
    @NotNull(message = "活动开始时间不能为空", groups = {ADD.class, EDIT.class})
    private String activityStartTime;

    /**
     * 活动结束时间
     */
    @NotNull(message = "活动结束时间不能为空", groups = {ADD.class, EDIT.class})
    private String activityEndTime;

    /**
     * 报名截止时间
     */
    @NotNull(message = "报名截止时间不能为空", groups = {ADD.class, EDIT.class})
    private String deadline;

    /**
     * 出发地
     */
    @NotBlank(message = "出发地不能为空", groups = {ADD.class, EDIT.class})
    private String departure;

    /**
     * 活动地点
     */
    @NotBlank(message = "活动地点不能为空", groups = {ADD.class, EDIT.class})
    private String eventLocation;

    /**
     * 人数限制(数字可变，如：500人/无限制 ２选１,)
     */
    @NotBlank(message = "人数限制不能为空", groups = {ADD.class, EDIT.class})
    private String numberLimit;

    /**
     * 联系电话
     */
    @NotBlank(message = "联系电话不能为空", groups = {ADD.class, EDIT.class})
    private String phone;

//    /**
//     * 姓名
//     */
//    private String name;

    /**
     * 报名费
     */
    @NotBlank(message = "报名费不能为空", groups = {ADD.class, EDIT.class})
    private String registeryFee;

    /**
     * 费用说明
     */
    @NotBlank(message = "费用说明不能为空", groups = {ADD.class, EDIT.class})
    private String costDescription;

    /**
     * 参与须知
     */
    @NotBlank(message = "参与须知不能为空", groups = {ADD.class, EDIT.class})
    private String participation;

    /**
     * 活动行程
     */
    @NotBlank(message = "活动行程不能为空", groups = {ADD.class, EDIT.class})
    private String activityItinerary;

    /**
     * 活动详情
     */
    @NotBlank(message = "活动详情不能为空", groups = {ADD.class, EDIT.class})
    private String detail;

    /**
     * 活动状态(１：进行中　０：已结束)
     */
    private Boolean status;

    /**
     * 已报名数
     */
    private Long num;

    /**
     * 转发数
     */
    private Long forwardingNumber;

    /**
     * 点赞数
     */
    private Long praiseNumber;

    /**
     * 评论数
     */
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

    public String getActivityStartTime() {
        return activityStartTime;
    }

    public void setActivityStartTime(String activityStartTime) {
        this.activityStartTime = activityStartTime;
    }

    public String getActivityEndTime() {
        return activityEndTime;
    }

    public void setActivityEndTime(String activityEndTime) {
        this.activityEndTime = activityEndTime;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
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

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

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
