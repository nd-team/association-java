package com.bjike.act.user;

import com.bjike.common.aspect.ADD;
import com.bjike.common.constant.UserCommon;
import com.bjike.common.exception.ActException;
import com.bjike.common.exception.SerException;
import com.bjike.common.interceptor.login.LoginAuth;
import com.bjike.common.restful.ActResult;
import com.bjike.common.restful.Result;
import com.bjike.common.util.IpUtil;
import com.bjike.common.util.regex.CheckMobile;
import com.bjike.ser.user.LoginSer;
import com.bjike.to.user.LoginTO;
import com.bjike.type.user.LoginType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录
 *
 * @Author: [liguiqin]
 * @Date: [2017-08-22 09:58]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
public class LoginAct {

    @Autowired
    private LoginSer loginSer;

    /**
     * 登录
     *
     * @param to 登录信息
     * @return {name:'data',type:'string',defaultValue:'',description:'token.'}
     * @throws ActException
     * @version v1
     */
    @PostMapping("login")
    public Result login(@Validated({ADD.class}) LoginTO to, BindingResult rs, HttpServletRequest request) throws ActException {
        try {
            String userAgent = request.getHeader("USER-AGENT").toLowerCase();
            LoginType type = LoginType.PC;
            if (CheckMobile.check(userAgent)) { //判断是否为移动端访问
                type = LoginType.MOBILE;
            }
            to.setLoginType(type);
            to.setIp(IpUtil.getIp(request));
            String token = loginSer.login(to);
            return ActResult.initialize(token);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 退出登录
     *
     * @return {name:'data',type:'string',defaultValue:'',description:'success.'}
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("logout")
    public Result logout(HttpServletRequest request) throws ActException {
        try {
            String token = request.getHeader(UserCommon.TOKEN);
            loginSer.logout(token);
            return new ActResult("success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}
