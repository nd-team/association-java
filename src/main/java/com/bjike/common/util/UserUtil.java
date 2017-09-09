package com.bjike.common.util;

import com.alibaba.fastjson.JSON;
import com.bjike.common.constant.UserCommon;
import com.bjike.common.exception.SerException;
import com.bjike.entity.user.User;
import com.bjike.redis.client.RedisClient;
import com.bjike.ser.user.UserSer;
import com.bjike.session.UserSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户工具
 * @Author: [liguiqin]
 * @Date: [2017-08-24 09:20]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class UserUtil {

    public static RedisClient redis;
    public static UserSer userSer;
    /**
     * 获取当前用户,默认从缓存获取
     *
     * @return
     * @throws SerException
     */
    public static User currentUser(String token) throws SerException {
        return getUser(token,true);
    }

    /**
     * 获取当前用户
     *
     * @return
     * @throws SerException
     */
    public static User currentUser(boolean cache) throws SerException {
        HttpServletRequest request = getRequest();
        String token = request.getHeader(UserCommon.TOKEN);
        return getUser(token,cache);

    }
    /**
     * 获取当前用户,默认从缓存获取
     *
     * @return
     * @throws SerException
     */
    public static User currentUser() throws SerException {
        HttpServletRequest request = getRequest();
        String token = request.getHeader(UserCommon.TOKEN);
        return getUser(token,true);

    }

    /**
     * 获取当前用户token
     *
     * @return
     * @throws SerException
     */
    public static String currentToken() throws SerException {
        HttpServletRequest request = getRequest();
        String token = request.getHeader(UserCommon.TOKEN);
        return token;

    }


    /**
     * 获取当前用户id
     *
     * @return
     * @throws SerException
     */
    public static String currentUserID() throws SerException {
        return currentUser().getId();
    }

    private static User getUser(String token,boolean cache) throws SerException {
        if (null != token) {
            User loginUser = UserSession.get(token);
            if (null != loginUser) {
                if(!cache){
                    return userSer.findById(loginUser.getId());
                }
                return loginUser;
            } else { //redis 获取
                String loginUser_str = redis.getMap(UserCommon.LOGIN_USER, token.toString());
                if (StringUtils.isNotBlank(loginUser_str)) {
                    loginUser = JSON.parseObject(loginUser_str, User.class);
                    UserSession.put(token, loginUser); //设置到session
                    if(!cache){
                        return userSer.findById(loginUser.getId());
                    }
                    return loginUser;
                }
            }
            throw new SerException("登录已失效!");
        } else {
            throw new SerException("用户未登录!");
        }
    }

    /**
     * 获取当前线程的request以获取token
     *
     * @return
     */
    private static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
}
