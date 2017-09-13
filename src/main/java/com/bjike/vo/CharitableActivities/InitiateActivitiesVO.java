package com.bjike.vo.CharitableActivities;

import com.bjike.vo.BaseVO;


/**
 * @Author: [dengjunren]
 * @Date: [2017-08-31 09:00]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class InitiateActivitiesVO extends BaseVO {

    /**
     * 发起人
     */
    private String sponsor;

    /**
     * 姓名
     */
    private String name;

    /**
     * 发起人头像
     */
    private String sponsorPathHead;

    /**
     * 活动主题名称
     */
    private String activitySubject;

    /**
     * 活动时间
     */
    private String activityTime;

    /**
     * 报名截止时间
     */
    private String deadline;

    /**
     * 出发地
     */
    private String departure;

    /**
     * 活动地点
     */
    private String eventLocation;

    /**
     * 人数限制
     */
    private String numberLimit;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 报名费
     */
    private String registeryFee;

    /**
     * 费用说明
     */
    private String costDescription;

    /**
     * 参与须知
     */
    private String participation;

    /**
     * 活动行程
     */
    private String activityItinerary;

    /**
     * 活动详情
     */
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

    public String getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(String activityTime) {
        this.activityTime = activityTime;
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

    public String getSponsorPathHead() {
        return sponsorPathHead;
    }

    public void setSponsorPathHead(String sponsorPathHead) {
        this.sponsorPathHead = sponsorPathHead;
    }
}
