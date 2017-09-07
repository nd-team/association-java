package com.bjike.dao.msg;


import com.bjike.dao.JpaRep;
import com.bjike.dto.msg.MessageDTO;
import com.bjike.entity.msg.Message;

/**
 * 消息推送持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-03-14T10:21:59.626 ]
 * @Description: [ 消息推送持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface IMessage extends JpaRep<Message, MessageDTO> {

}