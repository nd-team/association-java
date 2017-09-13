package com.bjike.act.user;

import com.bjike.common.exception.ActException;
import com.bjike.common.exception.SerException;
import com.bjike.common.interceptor.login.LoginAuth;
import com.bjike.common.restful.ActResult;
import com.bjike.common.util.UserUtil;
import com.bjike.entity.user.LoginLog;
import com.bjike.ser.user.LoginLogSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-13 09:19]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@LoginAuth
@RestController
public class LoginLogAct {
    @Autowired
    private LoginLogSer loginLogSer;

    @GetMapping("login/log")
    public ActResult sign() throws ActException {
        try {
            List<LoginLog> logs = loginLogSer.findByUserId(UserUtil.currentUserID());
            return ActResult.initialize(logs);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}
