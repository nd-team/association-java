package com.bjike.ser.msg;

import com.alibaba.fastjson.JSON;
import com.bjike.common.exception.SerException;
import com.bjike.common.util.UserUtil;
import com.bjike.common.util.bean.BeanCopy;
import com.bjike.common.util.date.DateUtil;
import com.bjike.common.util.regex.Validator;
import com.bjike.dto.Restrict;
import com.bjike.dto.msg.MessageDTO;
import com.bjike.dto.msg.UserMessageDTO;
import com.bjike.entity.msg.Message;
import com.bjike.entity.msg.MessageRead;
import com.bjike.entity.msg.UserMessage;
import com.bjike.entity.user.User;
import com.bjike.kafka.IKafkaProducer;
import com.bjike.redis.client.RedisClient;
import com.bjike.ser.ServiceImpl;
import com.bjike.ser.user.UserSer;
import com.bjike.to.msg.MessageTO;
import com.bjike.type.msg.MsgType;
import com.bjike.type.msg.RangeType;
import com.bjike.type.msg.SendType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 消息推送业务实现
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-03-14T10:21:59.627 ]
 * @Description: [ 消息推送业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service
public class MessageImpl extends ServiceImpl<Message, MessageDTO> implements MessageSer {
    @Autowired
    private UserSer userSer;
    @Autowired
    private RedisClient redisClient;
    @Autowired
    private IKafkaProducer kafkaProducer;
    @Autowired
    private UserMessageSer userMessageSer;
    /**
     * 公共消息
     */
    private static final String PUB_MSG = "assn_pub_msg";
    /**
     * 个人消息
     */
    private static final String UNREAD_MSG = "assn_unread_msg";

    @Transactional
    @Override
    public void send(MessageTO messageTO) throws SerException {
        initMsg(messageTO);
        Message message = BeanCopy.copyProperties(messageTO, Message.class, true);
        super.save(message); //保存消息到数据库
        messageTO.setId(message.getId());
        String[] receiversId = null;
        String[] receiversEmail = null;
        boolean isEmail = true;
        for (String email : messageTO.getReceivers()) { //要么全部为id,要么全部为邮箱
            if (!Validator.isEmail(email)) {
                isEmail = false;
            }
        }
        if (!isEmail) { //如果是用户id则查找用户邮箱
            receiversId = messageTO.getReceivers();
            receiversEmail = userSer.findIdByMail(receiversId);
        } else {//receivers 直接为邮箱方式
            receiversEmail = messageTO.getReceivers();
            receiversId = userSer.findMailById(receiversEmail);
        }

        SendType sendType = messageTO.getSendType();
        switch (sendType) {
            case EMAIL:
                messageTO.setReceivers(receiversEmail);
                kafkaProducer.produce(messageTO);
                break;
            case MSG:
                saveMsgToRedis(messageTO, message.getId(), receiversId); //保存到redis
            case ALL:
                messageTO.setReceivers(receiversEmail);
                kafkaProducer.produce(messageTO);
                saveMsgToRedis(messageTO, message.getId(), receiversId);//保存到redis
                break;
        }
        savePersonalMsg(receiversId, messageTO.getRangeType(), message); //保存个人消息到数据库

    }

    @Override
    public void read(String messageId) throws SerException {
        String userId = UserUtil.currentUserID();
        UserMessageDTO dto = new UserMessageDTO();
        dto.getConditions().add(Restrict.eq("message.id", messageId));
        UserMessage userMessage = userMessageSer.findOne(dto);
        if (null != userMessage) {
            userMessage.setRead(true);
            userMessageSer.update(userMessage);
        }
        redisClient.removeToList(userId + UNREAD_MSG, messageId); //从用户消息列表移除
    }

    @Override
    public List<Message> list(MessageDTO dto) throws SerException {
        String userId = UserUtil.currentUserID();
        Integer start = (dto.getPage() - 1 < 0 ? 0 : dto.getPage() - 1) * dto.getLimit();
        StringBuilder sb = new StringBuilder();
        sb.append("select id,create_time as createTime,modify_time as modifyTime,title,content ,sender_id as senderId,sender_name as senderName from (select * from  msg_message where range_type = 0 ");//公共消息
        sb.append(" union "); //个人消息
        sb.append(" select a.*  from  msg_message a, msg_user_message b where a.id = b.message_id and b.user_id='%s' and range_type=2 ) as a ");
        if (null != dto.getMsgType()) {
            sb.append(" where msg_type=" + dto.getMsgType().getCode());
        }
        sb.append(" order by create_time desc limit " + start + "," + dto.getLimit());
        String sql = sb.toString();
        sql = String.format(sql, userId);
        String[] fields = new String[]{"id", "createTime", "modifyTime", "title", "content", "senderId", "senderName"};
        List<Message> messages = super.findBySql(sql, Message.class, fields); //公共与个人消息
        return messages;
    }

    @Override
    public List<Message> unreadList(String userId, MsgType type) throws SerException {
        List<Message> messages = new ArrayList<>(0);
        List<String> messageIds = redisClient.getList(userId + UNREAD_MSG); //未读公共消息
        if (messageIds.size() > 0) {
            MessageDTO dto = new MessageDTO();
            dto.getConditions().add(Restrict.in("id", messageIds.toArray()));
            if (null != type) {
                dto.getConditions().add(Restrict.eq("msgType", type.getCode()));
            }
            messages.addAll(super.findByCis(dto));
        }
        List<Message> msgs = userMessageSer.unreadList(userId); // 未读个人消息
        messages.addAll(msgs);
        return messages;
    }

    @Override
    public void remove(String messageId) throws SerException {
        redisClient.removeMap(PUB_MSG, messageId); //从缓存消息列表删除
        super.remove(messageId); //数据库删除
    }

    @Override
    public void edit(MessageTO messageTO) throws SerException {
        Message message = super.findById(messageTO.getId());
        if (null != message) {
            BeanCopy.copyProperties(messageTO, message);
            message.setModifyTime(LocalDateTime.now());
            super.update(message);
            MessageRead messageRead = BeanCopy.copyProperties(message, MessageRead.class);
            String json_messageRead = JSON.toJSONString(messageRead);
            redisClient.appendToMap(PUB_MSG, message.getId(), json_messageRead);
        } else {
            throw new SerException("not exist data by id");
        }

    }


    /**
     * 保存个人消息
     *
     * @throws SerException
     */
    private void savePersonalMsg(String[] receiversId, RangeType type, Message m) throws SerException {
        Message message = super.findById(m.getId());
        if (type.equals(RangeType.SPECIFIED)) {
            List<UserMessage> userMessages = new ArrayList<>();
            for (String id : receiversId) {
                UserMessage userMessage = new UserMessage();
                userMessage.setMessage(message);
                userMessage.setRead(false);
                userMessage.setUser(userSer.findById(id));
                userMessages.add(userMessage);
            }
            userMessageSer.save(userMessages);
        }

    }

    private void saveMsgToRedis(MessageTO messageTO, String message_id, String[] receivers) throws SerException {

        MessageRead messageRead = BeanCopy.copyProperties(messageTO, MessageRead.class);
        String json_messageRead = JSON.toJSONString(messageRead);

        if (null != receivers) {
            for (int i = 0; i < receivers.length; i++) {
                redisClient.appendToList(receivers[i] + UNREAD_MSG, 30 * 24 * 60 * 60, message_id); //未读消息保存一个月
            }
            //保存系统所发送的所有消息，以供查询
            redisClient.appendToMap(PUB_MSG, message_id, json_messageRead, 7 * 24 * 60 * 60);//系统所发出的消息缓存7天
        }


    }

    private void initMsg(MessageTO messageTO) {
        if (StringUtils.isBlank(messageTO.getCreateTime())) {
            messageTO.setCreateTime(DateUtil.dateToString(LocalDateTime.now()));
        }
        if (StringUtils.isBlank(messageTO.getSenderId())) {
            try {//如果经过action调用,必须登录用户
                User user = UserUtil.currentUser();
                messageTO.setSenderId(user.getId());
                messageTO.setSenderName(user.getUsername());
            } catch (SerException e) { //定时器自动调用没有登录用户
                messageTO.setSenderId("sys");
                messageTO.setSenderName("admin");
            }

        }
        messageTO.setMsgType(null != messageTO.getMsgType() ? messageTO.getMsgType() : MsgType.SYS);
        messageTO.setSendType(null != messageTO.getSendType() ? messageTO.getSendType() : SendType.MSG);
        messageTO.setRangeType(null != messageTO.getRangeType() ? messageTO.getRangeType() : RangeType.SPECIFIED);
    }

}