package com.bjike.ser.msg;

import com.bjike.common.exception.SerException;
import com.bjike.dto.Restrict;
import com.bjike.dto.msg.UserMessageDTO;
import com.bjike.entity.msg.Message;
import com.bjike.entity.msg.UserMessage;
import com.bjike.ser.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户消息业务实现
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-03-21T09:40:27.620 ]
 * @Description: [ 用户消息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service
public class UserMessageSerImpl extends ServiceImpl<UserMessage, UserMessageDTO> implements UserMessageSer {

    @Override
    public List<Message> unreadList(String userId) throws SerException {
        UserMessageDTO dto =new UserMessageDTO();
        dto.getConditions().add(Restrict.eq("user.id",userId));
        dto.getConditions().add(Restrict.eq("user.id",userId));
        return null;
    }
}