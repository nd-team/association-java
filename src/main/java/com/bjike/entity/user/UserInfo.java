package com.bjike.entity.user;

import com.bjike.entity.BaseEntity;
import com.bjike.type.user.RelationshipType;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;

/**
 * 用户详情
 *
 * @Author: [liguiqin]
 * @Date: [2017-08-23 09:36]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "user_info")
public class UserInfo extends BaseEntity {
    /**
     * 所属用户
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", unique = true, columnDefinition = "VARCHAR(36) COMMENT '用户id' ", nullable = false)
    private User user;
    /**
     * 经验值
     */
    @Column(columnDefinition = "DECIMAL(5,2)  COMMENT '经验值'")
    private Double experience;
    /**
     * 贡献值
     */
    @Column(columnDefinition = "DECIMAL(5,2)  COMMENT '贡献值'")
    private Double contribute;
    /**
     * 信誉值
     */
    @Column(columnDefinition = "DECIMAL(3,2)  COMMENT '信誉值'")
    private Double reputation;
    /**
     * 真实姓名
     */
    @Column(columnDefinition = "VARCHAR(30) COMMENT '真实姓名' ")
    private String realName;
    /**
     * 邮箱
     */
    @Email
    @Column(columnDefinition = "VARCHAR(30) COMMENT '邮箱' ")
    private String email;
    /**
     * 联系号码
     */
    @Column(columnDefinition = "VARCHAR(20) COMMENT '联系号码' ")
    private String telephone;
    /**
     * 兴趣
     */
    @Column(columnDefinition = "VARCHAR(256) COMMENT '兴趣' ")
    private String interest;
    /**
     * 地址
     */
    @Column(columnDefinition = "VARCHAR(256) COMMENT '地址' ")
    private String address;
    /**
     * 关系
     */
    @Column(columnDefinition = "TINYINT(1) COMMENT '关系' ")
    private RelationshipType relationshipType;
    /**
     * 生日
     */
    @Column(columnDefinition = "VARCHAR(100) COMMENT '生日' ")
    private String birthday;
    /**
     * 性格
     */
    @Column(columnDefinition = "VARCHAR(256) COMMENT '性格' ")
    private String disposition;
    /**
     * 籍贯
     */
    @Column(columnDefinition = "VARCHAR(100) COMMENT '籍贯' ")
    private String nativePlace;
    /**
     * 毕业学校
     */
    @Column(columnDefinition = "VARCHAR(100) COMMENT '毕业学校' ")
    private String school;
    /**
     * 学历
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT '学历' ")
    private String education;
    /**
     * 公司
     */
    @Column(columnDefinition = "VARCHAR(100) COMMENT '公司' ")
    private String company;
    /**
     * 职位
     */
    @Column(columnDefinition = "VARCHAR(100) COMMENT '职位' ")
    private String job;
    /**
     * 父亲姓名
     */
    @Column(columnDefinition = "VARCHAR(30) COMMENT '父亲姓名' ")
    private String fatherName;
    /**
     * 母亲姓名
     */
    @Column(columnDefinition = "VARCHAR(30) COMMENT '母亲姓名' ")
    private String motherName;
    /**
     * 婚姻状况
     */
    @Column(name = "is_marriage", columnDefinition = "TINYINT(1)  COMMENT '婚姻状况'")
    private Boolean marriage;
    /**
     * qq
     */
    @Column(columnDefinition = "VARCHAR(30) COMMENT 'qq' ")
    private String qq;

    /**
     * 微信
     */
    @Column(columnDefinition = "VARCHAR(30) COMMENT '微信' ")
    private String weChat;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getExperience() {
        return experience;
    }

    public void setExperience(Double experience) {
        this.experience = experience;
    }

    public Double getContribute() {
        return contribute;
    }

    public void setContribute(Double contribute) {
        this.contribute = contribute;
    }

    public Double getReputation() {
        return reputation;
    }

    public void setReputation(Double reputation) {
        this.reputation = reputation;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public RelationshipType getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(RelationshipType relationshipType) {
        this.relationshipType = relationshipType;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getDisposition() {
        return disposition;
    }

    public void setDisposition(String disposition) {
        this.disposition = disposition;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public Boolean getMarriage() {
        return marriage;
    }

    public void setMarriage(Boolean marriage) {
        this.marriage = marriage;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }


    public String getWeChat() {
        return weChat;
    }

    public void setWeChat(String weChat) {
        this.weChat = weChat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
