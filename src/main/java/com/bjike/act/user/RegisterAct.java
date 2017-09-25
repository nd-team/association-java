package com.bjike.act.user;

import com.bjike.common.exception.ActException;
import com.bjike.common.exception.SerException;
import com.bjike.common.restful.ActResult;
import com.bjike.common.restful.Result;
import com.bjike.common.util.IpUtil;
import com.bjike.common.util.regex.CheckMobile;
import com.bjike.ser.user.RegisterSer;
import com.bjike.to.user.RegisterTO;
import com.bjike.type.user.LoginType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 注册
 *
 * @Author: [liguiqin]
 * @Date: [2017-08-22 09:58]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
public class RegisterAct {
    @Autowired
    private RegisterSer registerSer;

    /**
     * 手机号注册
     *
     * @param to 注册信息
     * @return {name:'data',type:'string',defaultValue:'',description:'success.'}
     * @des 注册之前请先获取到注册验证码:AuthCodeAct
     * @version v1
     */
    @PostMapping("register")
    public Result register(@Validated({RegisterTO.PHONE.class}) RegisterTO to, BindingResult rs, HttpServletRequest request) throws ActException {
        try {
            String userAgent = request.getHeader("USER-AGENT").toLowerCase();
            LoginType type = LoginType.PC;
            if (CheckMobile.check(userAgent)) { //判断是否为移动端访问
                type = LoginType.MOBILE;
            }
            to.setLoginType(type);
            to.setIp(IpUtil.getIp(request));
            to.setSid(request.getSession().getId());
            String token = registerSer.register(to);
            return ActResult.initialize(token);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 邀请码注册
     *
     * @param to 注册信息
     * @return {name:'data',type:'string',defaultValue:'',description:'success.'}
     * @version v1
     */
    @PostMapping("invite/register")
    public Result inviteReg(@Validated({RegisterTO.INVITE.class}) RegisterTO to, BindingResult rs, HttpServletRequest request) throws ActException {
        try {
            to.setIp(IpUtil.getIp(request));
            to.setSid(request.getSession().getId());
            String userAgent = request.getHeader("USER-AGENT").toLowerCase();
            LoginType type = LoginType.PC;
            if (CheckMobile.check(userAgent)) { //判断是否为移动端访问
                type = LoginType.MOBILE;
            }
            to.setLoginType(type);
            String token = registerSer.register(to);
            return ActResult.initialize(token);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}
