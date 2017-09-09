package com.bjike.to.chat;

import com.bjike.common.aspect.ADD;
import com.bjike.common.aspect.EDIT;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 好友传输对象
 *
 * @Author: [liguiqin]
 * @Date: [2017-07-21 11:47]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class FriendTO {

    /**
     * 好友id
     */
    @NotBlank(message = "好友id不能为空", groups = {ADD.class, EDIT.class})
    private String friendId;
    /**
     * 备注
     */
    private String remark;

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
