package com.bjike.ser.msg;


import com.bjike.common.exception.SerException;
import com.bjike.dto.msg.MessageDTO;
import com.bjike.entity.msg.Message;
import com.bjike.ser.Ser;
import com.bjike.to.msg.MessageTO;
import com.bjike.type.msg.MsgType;

import java.util.List;

/**
 * 消息推送业务接口
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-03-14T10:21:59.621 ]
 * @Description: [ 消息推送业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MessageSer extends Ser<Message, MessageDTO> {
    /**
     * 发送邮件
     *
     * @param messageTO
     * @des 默认为个人邮件(SPECIFIED), 指定PUB则发送所有人员
     */
    default void sendMail(MessageTO messageTO) throws SerException {

    }

    /**
     * 推送移动端消息
     *
     * @param messageTO
     * @des 默认为个人消息(SPECIFIED), 指定PUB则发送所有人员
     */
    default void pushMsg(MessageTO messageTO) throws SerException {

    }

    /**
     * 推送移动端消息及发送邮件
     * 默认范围个人
     *
     * @param messageTO
     */
    default void pushAndMail(MessageTO messageTO) throws SerException {

    }

    /**
     * 读取消息
     *
     * @param messageId
     */
    default void read(String messageId) throws SerException {
    }

    /**
     * 读取消息列表
     *
     * @param dto
     */
    default List<Message> list(MessageDTO dto) throws SerException {
        return null;
    }

    /**
     * 未读消息列表
     *
     * @param userId
     * @param type
     * @return
     * @throws SerException
     */
    default List<Message> unreadList(String userId, MsgType type) throws SerException {
        return null;
    }


    /**
     * 删除消息
     *
     * @param messageId 消息id
     */
    default void remove(String messageId) throws SerException {
    }

    /**
     * 修改消息
     *
     * @param messageTO 消息id
     */
    default void edit(MessageTO messageTO) throws SerException {
    }

    /**
     * 登录后推送用户未接收到的消息
     *
     * @param userId 用户id
     */
    default void notice(String userId) throws SerException {
    }

}