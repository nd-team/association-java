package com.bjike.ser.chat;

import com.alibaba.fastjson.JSON;
import com.bjike.common.exception.SerException;
import com.bjike.dto.Restrict;
import com.bjike.dto.chat.FriendDTO;
import com.bjike.dto.chat.GroupMemberDTO;
import com.bjike.dto.chat.MsgDTO;
import com.bjike.entity.chat.Client;
import com.bjike.entity.chat.Friend;
import com.bjike.entity.chat.GroupMember;
import com.bjike.entity.chat.Msg;
import com.bjike.entity.user.User;
import com.bjike.ser.chat.mongo.MsgSer;
import com.bjike.ser.user.UserSer;
import com.bjike.session.ChatSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.websocket.Session;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-07-20 14:09]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class ChatSerImpl implements ChatSer {

    @Autowired
    private GroupMemberSer groupMember;
    @Autowired
    private UserSer userSer;
    @Autowired
    private MsgSer msgSer;
    @Autowired
    private FriendSer friendSer;

    /**
     * 初始化客户端
     *
     * @param userId
     * @return
     */
    public Boolean initClient(String userId, Session session) throws SerException {
        Boolean exists = false; //已存在会话
        try {
            Client client = null;
            client = ChatSession.get(userId);
            if (null != client) {
                client.setSession(session); //更新session
                exists = true;

            } else {
                client = new Client();
                User sender = userSer.findById(userId);
                if(null!=sender){
                    client.setUsername(sender.getNickname());
                    client.setHeadPath(sender.getHeadPath());
                    client.setSession(session);
                    ChatSession.put(userId, client);
                    exists = false;
                }else {
                    throw  new SerException("找不到该用户!");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exists;
    }

    public void broadcast(Msg msg) throws SerException {
        if (null != msg.getMsgType()) {
            switch (msg.getMsgType()) {
                case POINT:
                    sendMsg(msg, msg.getReceiver());
                    break;
                case GROUP:
                    GroupMemberDTO dto = new GroupMemberDTO();
                    dto.getConditions().add(Restrict.eq("id", msg.getGroup()));
                    List<GroupMember> groupMembers = groupMember.findByCis(dto);
                    for (GroupMember member : groupMembers) {
                        msg.setReceiver(member.getUser().getId());
                        sendMsg(msg, member.getUser().getId());
                    }
                    break;
                case ONLINE:
                    sendLineMsg(msg);
                    break;
                case OFFLINE:
                    sendLineMsg(msg);
                    break;
                case SYS:
                    break; //all
            }
        } else {
            throw new SerException("消息类型不能为空");
        }
    }

    /**
     * 读取离线时未接收到的消息
     *
     * @param userId
     */
    public void readOffLineMsg(String userId) throws SerException {
        MsgDTO dto = new MsgDTO();
        dto.getConditions().add(Restrict.eq("receiver", userId));
        List<Msg> msgs = msgSer.findByCis(dto);
        if ((null != msgs)) {
            for (Msg msg : msgs) {
                sendMsg(msg, userId);
                msgSer.remove(msg);
            }
        }
    }

    /**
     * 消息发送
     *
     * @param msg
     * @throws SerException
     */
    public void sendMsg(Msg msg, String receiver) throws SerException {
        Session session = null;
        Client client = ChatSession.get(receiver);
        msg.setCreateTime(LocalDateTime.now());
        if (null != client) {
            session = client.getSession();
            if (session.isOpen()) {
                try {
                    msg.setRead(true);
                    session.getBasicRemote().sendText(JSON.toJSONString(msg));
                    msgSer.save(msg);
                } catch (IOException e) {
                    throw new SerException(e.getMessage());
                }
            } else {
                msgSer.save(msg);
            }
        } else { //消息未推送
//            off-line
            msgSer.save(msg);
        }
    }

    private void sendLineMsg(Msg msg) throws SerException {
        FriendDTO dto = new FriendDTO();
        dto.getConditions().add(Restrict.eq("user.id", msg.getUserId()));
        dto.getConditions().add(Restrict.eq("applyType", 1));
        List<Friend> friends = friendSer.findByCis(dto);
        for (Friend friend : friends) {
            Client client = ChatSession.get(friend.getUser().getId());
            if (null != client && client.getSession().isOpen()) {
                try {
                    client.getSession().getBasicRemote().sendText(JSON.toJSONString(msg));
                } catch (IOException e) {
                    throw new SerException(e.getMessage());
                }
            }
        }

    }


}
