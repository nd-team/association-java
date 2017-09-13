package com.bjike.vo.search;

import com.bjike.type.user.SexType;
import com.bjike.type.user.UserType;
import com.bjike.vo.BaseVO;

/**
 * 同意找人返回对象
 *
 * @Author: [chenjunhao]
 * @Date: [2017-09-05 13:53]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class SearchUserVO extends BaseVO {
    /**
     * 用户名
     */
    private String username;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 编号
     */
    private String number;
    /**
     * 头像
     */
    private String headPath;
    /**
     * 性别
     */
    private SexType sexType;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 会员类型
     */
    private UserType userType;
    /**
     * 地址
     */
    private String address;
    /**
     * 贡献值
     */
    private Double contribute;
    /**
     * 信誉值
     */
    private Double reputation;
    /**
     * 公司
     */
    private String company;
    /**
     * 职位
     */
    private String job;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getHeadPath() {
        return headPath;
    }

    public void setHeadPath(String headPath) {
        this.headPath = headPath;
    }

    public SexType getSexType() {
        return sexType;
    }

    public void setSexType(SexType sexType) {
        this.sexType = sexType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
