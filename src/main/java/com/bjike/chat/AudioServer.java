package com.bjike.chat;

import com.bjike.common.exception.SerException;
import com.bjike.common.restful.ActResult;
import com.bjike.entity.chat.AudioClient;
import com.bjike.entity.chat.Client;
import com.bjike.session.AudioClientSession;
import com.bjike.session.AudioSession;
import com.bjike.session.ChatSession;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Map;

/**
 * WebSocket 音频通讯
 *
 * @author liguiqin
 */
@ServerEndpoint(value = "/audio/{userId}", configurator = GetHttpSessionConfigurator.class) //开启语音服务
@Component
public class AudioServer {

    /**
     * @param session
     * @param sender  请求语音人
     * @param reviver //语音接收人
     */
    public void OnOpen(Session session, @PathParam("sender") String sender, @PathParam("reviver") String reviver) throws SerException {
        Client client = ChatSession.get(reviver);
        if (null == null || !client.getSession().isOpen()) {
            throw new SerException("用户不在线.");
        } else {
            AudioSession.put(sender, session);
            AudioClient audioClient = AudioClientSession.get(sender);
            if (null != audioClient) {
                audioClient.getRequester().put(reviver, false);
            } else {
                AudioClientSession.put(sender, new AudioClient());
            }
        }
    }

    public void quit(Session session, @PathParam("sender") String sender) throws SerException {
        try {
            session.close();
            AudioSession.remove(sender);
            AudioClientSession.remove(sender);
        } catch (IOException e) {
            throw new SerException(e.getMessage());
        }
    }

    @OnMessage(maxMessageSize = 10000000)
    public void OnMessage(@PathParam("reviver") String reviver, @PathParam("sender") String sender, ByteBuffer data) throws SerException {
        Session client = AudioSession.get(reviver);
        if (null != client && client.isOpen()) {
            AudioClient to_client = AudioClientSession.get(sender);
            if (null != to_client) {
                Map<String, Boolean> map = AudioClientSession.get(sender).getRequester();
                for (Map.Entry<String, Boolean> entry : map.entrySet()) {
                    if (entry.getValue().equals(reviver) && entry.getValue()) {
                        try {
                            client.getBasicRemote().sendBinary(data);
                        } catch (IOException e) {
                            throw new SerException(e.getMessage());
                        }
                        break;
                    }
                }

            }
        }

    }

    public void error(Session session, Throwable t) throws Throwable {
        session.close();
    }


}
