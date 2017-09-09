package com.bjike.entity.user;

import com.bjike.entity.BaseEntity;
import com.bjike.type.Status;
import com.bjike.type.user.SexType;
import com.bjike.type.user.UserType;
import org.hibernate.validator.constraints.Email;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 用户
 *
 * @Author: [liguiqin]
 * @Date: [2017-06-28 15:13]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "user")
public class User extends BaseEntity {
    /**
     * 用户名
     */
    @Column(columnDefinition = "VARCHAR(30) COMMENT '用户名' ", nullable = false)
    private String username;
    /**
     * 密码
     */
    @Column(columnDefinition = "VARCHAR(256) COMMENT '密码' ", nullable = false)
    private String password;
    /**
     * 昵称
     */
    @Column(columnDefinition = "VARCHAR(30) COMMENT '昵称' ", nullable = false)
    private String nickname;
    /**
     * 编号
     */
    @Column(columnDefinition = "VARCHAR(30) COMMENT '编号' ", nullable = false, unique = true)
    private String number;
    /**
     * 头像
     */
    @Column(columnDefinition = "VARCHAR(256) COMMENT '头像' ")
    private String headPath;
    /**
     * 性别
     */
    @Column(columnDefinition = "TINYINT(1) DEFAULT 0 COMMENT '性别' ", nullable = false, insertable = false)
    private SexType sexType ;
    /**
     * 邮箱
     */
    @Column(columnDefinition = "VARCHAR(256) COMMENT '邮箱' ",unique = true)
    @Email
    private String email;
    /**
     * 手机号码
     */
    @Column(columnDefinition = "VARCHAR(11) COMMENT '手机号码' ", nullable = false, unique = true)
    private String phone;
    /**
     * 状态
     */
    @Column(columnDefinition = "TINYINT(1) DEFAULT 0 COMMENT '状态' ", nullable = false, insertable = false)
    private Status status ;
    /**
     * 会员类型
     */
    @Column(columnDefinition = "TINYINT(1) DEFAULT 0 COMMENT '会员类型' ", nullable = false, insertable = false)
    private UserType userType ;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
