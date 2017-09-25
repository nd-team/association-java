package com.bjike.act.shake;

import com.bjike.common.exception.ActException;
import com.bjike.common.exception.SerException;
import com.bjike.common.interceptor.login.LoginAuth;
import com.bjike.common.restful.ActResult;
import com.bjike.common.restful.Result;
import com.bjike.entity.user.User;
import com.bjike.ser.shake.ShakeSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 摇一摇
 *
 * @Author: [liguiqin]
 * @Date: [2017-07-06 13:51]
 * @Description: [摇一摇 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@LoginAuth
@RestController
public class ShakeAct {
    /**
     * 摇一摇
     *
     * @return class User
     * @version v1
     * @throws Exception
     */
    @Autowired
    private ShakeSer shakeSer;

    @GetMapping("shake")
    public Result list(String longitude, String latitude) throws ActException {
        try {
            User vos = shakeSer.shake(longitude, latitude);
            return ActResult.initialize(vos);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}
