package com.bjike.common.constant;

/**
 * @Author: [liguiqin]
 * @Date: [2017-03-30 14:36]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class UserCommon {
    /**
     * 登录用户
     */
    public static final String LOGIN_USER = "loginUser";

    public static final String TOKEN = "token";
    public static final String EXPERIENCE = "experience";


    /**
     * redis登录失效时间 30天
     */
    public static final Integer LOGIN_TIMEOUT = 30*60 * 60 * 24;

    /**
     * 经验缓存失效2天
     */
    public static final Integer EXP_TIMEOUT = 2*60 * 60 * 24;

}
