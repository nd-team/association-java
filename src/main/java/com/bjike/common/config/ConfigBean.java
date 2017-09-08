package com.bjike.common.config;

import com.bjike.common.interceptor.login.LoginIntercept;
import com.bjike.chat.ChatServer;
import com.bjike.common.util.UserUtil;
import com.bjike.redis.client.RedisClient;
import com.bjike.ser.chat.ChatSer;
import com.bjike.ser.msg.MessageSer;
import com.bjike.ser.user.UserSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Author: [liguiqin]
 * @Date: [2017-07-07 09:53]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Component
public class ConfigBean {
    @Autowired
    private UserSer userSer;
    @Autowired
    private ChatSer chatSer;
    @Autowired
    private RedisClient redis;
    @Autowired
    private MessageSer messageSer;

    @Bean
    public Object init() {
        LoginIntercept.userSer = userSer;
        ChatServer.chatSer = chatSer;
        ChatServer.messageSer = messageSer;
        UserUtil.redis = redis;
        UserUtil.userSer =userSer;
        return new Object();
    }
}
