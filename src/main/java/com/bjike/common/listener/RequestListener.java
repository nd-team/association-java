package com.bjike.common.listener;

import org.springframework.web.context.request.RequestContextListener;

import javax.servlet.annotation.WebListener;

/**
 * request监听器(可在当前线程获取当前的request)
 * @Author: [liguiqin]
 * @Date: [2017-08-24 09:10]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@WebListener
public class RequestListener extends RequestContextListener {

}
