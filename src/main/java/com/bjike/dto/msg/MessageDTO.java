package com.bjike.dto.msg;

import com.bjike.common.aspect.GET;
import com.bjike.dto.BaseDTO;
import com.bjike.type.msg.MsgType;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 消息推送数据传输对象
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-03-14T10:21:59.619 ]
 * @Description: [ 消息推送数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MessageDTO extends BaseDTO {

    private MsgType msgType;


    public MsgType getMsgType() {
        return msgType;
    }

    public void setMsgType(MsgType msgType) {
        this.msgType = msgType;
    }
}