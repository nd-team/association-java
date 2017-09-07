package com.bjike.ser.msg;


import com.bjike.common.exception.SerException;
import com.bjike.dto.msg.UserMessageDTO;
import com.bjike.entity.msg.Message;
import com.bjike.entity.msg.UserMessage;
import com.bjike.ser.Ser;
import com.bjike.type.msg.MsgType;

import java.util.List;

/**
 * 用户消息业务接口
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-03-21T09:40:27.610 ]
 * @Description: [ 用户消息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface UserMessageSer extends Ser<UserMessage, UserMessageDTO> {
    /**
     * 个人未读消息列表
     * @param userId
     * @return
     * @throws SerException
     */
    default List<Message> unreadList(String userId) throws SerException{
        return null;
    }

}