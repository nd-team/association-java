package com.bjike.session;


import com.bjike.common.exception.SerException;
import com.bjike.entity.user.User;
import com.bjike.entity.user.other.ShakeUser;
import com.google.common.cache.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * 摇一摇session管理类
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public final class ShareSession {
    private static final RuntimeException TOKEN_NOT_NULL = new RuntimeException("token令牌不能为空");
    private static Logger logger = LoggerFactory.getLogger(ShareSession.class);

    private ShareSession() {
    }

    private static final LoadingCache<String, ShakeUser> USER_SESSION = CacheBuilder.newBuilder()
            .expireAfterWrite(5, TimeUnit.SECONDS)
            .maximumSize(1000)
            .removalListener(new RemovalListener<String, ShakeUser>() {
                @Override
                public void onRemoval(RemovalNotification<String, ShakeUser> notification) {
                    if (!notification.getCause().equals(RemovalCause.REPLACED)) {
                        logger.info("remove:" + notification.getKey());
                    }
                }
            })
            .build(new CacheLoader<String, ShakeUser>() {
                @Override
                public ShakeUser load(String key) throws Exception {
                    return null;
                }
            });


    /**
     * 新增用户会话信息
     *
     * @param token 令牌值
     * @param user  登录用户信息
     * @return 是否已经登录
     */
    public static void put(String token, ShakeUser user) {
        if (StringUtils.isNotBlank(token)) {
            USER_SESSION.put(token, user);
        } else {
            throw TOKEN_NOT_NULL;
        }


    }

    /**
     * 根据令牌删除用户会话信息
     *
     * @param time 登录令牌
     * @return 是否删除成功
     */
    public static void remove(String time) {
        if (StringUtils.isNotBlank(time)) {
            USER_SESSION.invalidate(time);
        } else {

            throw TOKEN_NOT_NULL;
        }
    }


    public static ShakeUser get(String time) {
        try {
            if (StringUtils.isNotBlank(time)) {
                return USER_SESSION.get(time);
            }
            throw TOKEN_NOT_NULL;
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 获取用户会话总数
     *
     * @return 总数
     */
    public static long count() {
        return USER_SESSION.size();
    }

    /**
     * 获取全部用户会话信息
     *
     * @return 会话信息集合
     */
    public static LoadingCache<String, ShakeUser> sessions() {
        if (null != USER_SESSION && USER_SESSION.size() > 0) {
            return USER_SESSION;
        }
        return null;
    }

    public static boolean verify(String time) throws SerException {
        try {
            return USER_SESSION.get(time) != null;
        } catch (Exception e) {
            throw new SerException(e.getMessage());
        }
    }


}
