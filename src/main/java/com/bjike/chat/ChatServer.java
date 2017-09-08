package com.bjike.chat;

import com.alibaba.fastjson.JSON;
import com.bjike.common.exception.SerException;
import com.bjike.entity.chat.Client;
import com.bjike.entity.chat.Msg;
import com.bjike.entity.msg.Message;
import com.bjike.ser.chat.ChatSer;
import com.bjike.ser.msg.MessageSer;
import com.bjike.session.ChatSession;
import com.bjike.type.msg.MsgType;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-07-19 14:02]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@ServerEndpoint(value = "/chat/{userId}", configurator = GetHttpSessionConfigurator.class)
@Component
public class ChatServer {

    private static final int MAX_SIZE = 1024 * 10;
    private static final int TIMEOUT = 60 * 10 * 10;
    public static ChatSer chatSer;
    public static MessageSer messageSer;


    // OnPen，连接创建时调用的方法
    @OnOpen
    public void join(@PathParam("userId") String userId, Session session) throws SerException {
        try {
//            System.out.println(session.getUserProperties().get("javax.websocket.endpoint.remoteAddress"));
            boolean exists = chatSer.initClient(userId, session);
            if (exists) {
                //读取离线时未接收到的消息
                chatSer.readOffLineMsg(userId);
                messageSer.notice(userId); //读取通知
                //通知上线
                Msg msg = new Msg();
                msg.setMsgType(MsgType.ONLINE);
                msg.setContent("上线通知");
                msg.setUserId(userId);
                chatSer.broadcast(msg);
            }
        } catch (SerException e) {
            e.printStackTrace();
            throw new SerException(e.getMessage());
        }


    }

    // OnClose，连接关闭时调用的方法
    @OnClose
    public void quit(@PathParam("userId") String userId, CloseReason reason, Session session) throws Throwable {
        int code = reason.getCloseCode().getCode();
        if (code != 1000) {//刷新
            Msg msg = new Msg();
            msg.setMsgType(MsgType.OFFLINE);
            msg.setContent("下线通知");
            msg.setUserId(userId);
            chatSer.broadcast(msg);
            ChatSession.remove(userId);
            session.close();
        }


    }

    // OnMessage，传输信息过程中调用的方法
    @OnMessage
    public void incoming(@PathParam("userId") String userId, String content, Session session) throws SerException {
        session.setMaxTextMessageBufferSize(MAX_SIZE); //调大导致内存溢出
        Msg msg = JSON.parseObject(content, Msg.class);
        Client client = ChatSession.get(userId);
        client.setSession(session);
        if (null != client) {
            content.replaceAll("操", "*");
            msg.setContent(msg.getContent());
            msg.setSenderHeadPath(client.getHeadPath());
            msg.setSenderName(client.getUsername());
            chatSer.broadcast(msg);
        }
    }

    // OnError，发生错误时调用的方法
    @OnError
    public void onError(@PathParam("userId") String userId, Throwable t, Session session) throws Throwable {
        /**
         * 非正常关闭浏览器
         */
        Msg msg = new Msg();
        msg.setMsgType(MsgType.OFFLINE);
        msg.setContent("下线通知");
        msg.setUserId(userId);
        chatSer.broadcast(msg);
        session.close();
    }


}
