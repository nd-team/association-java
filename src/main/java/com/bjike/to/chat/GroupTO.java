package com.bjike.to.chat;

import com.bjike.common.aspect.ADD;
import com.bjike.common.aspect.EDIT;
import com.bjike.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 群传输对象
 *
 * @Author: [liguiqin]
 * @Date: [2017-07-20 10:56]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class GroupTO extends BaseTO {
    /**
     * 群名
     */
    @NotBlank(message = "群名不能为空", groups = {ADD.class, EDIT.class})
    private String name;

    /**
     * 群描述
     */
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
