package com.bjike.entity.addressBook;

import com.bjike.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 通讯录
 *
 * @Author: [chenjunhao]
 * @Date: [2017-08-29 14:01]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "addressBook_address_book")
public class AddressBook extends BaseEntity{
    /**
     * 归属人id
     */
    @Column(columnDefinition = "VARCHAR(36) COMMENT '归属人id' ", nullable = false)
    private String userId;
    /**
     * 通讯录人id
     */
    @Column(columnDefinition = "VARCHAR(36) COMMENT '通讯录人id' ", nullable = false)
    private String bookId;

    /**
     * 备注
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '备注' ")
    private String remark;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
