package com.bjike.to.chat;

import com.bjike.common.aspect.ADD;
import com.bjike.common.aspect.EDIT;
import com.bjike.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 好友分组传输对象
 *
 * @Author: [liguiqin]
 * @Date: [2017-07-21 11:09]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class FriendGroupTO extends BaseTO {
    /**
     * 分组名
     */
    @NotBlank(message = "分组名不能为空", groups = {ADD.class, EDIT.class})
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
