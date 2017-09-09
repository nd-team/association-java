package com.bjike.act.user;

import com.bjike.common.exception.ActException;
import com.bjike.common.exception.SerException;
import com.bjike.common.interceptor.login.LoginAuth;
import com.bjike.common.restful.ActResult;
import com.bjike.common.util.bean.BeanCopy;
import com.bjike.entity.user.Sign;
import com.bjike.ser.user.SignSer;
import com.bjike.vo.user.SignVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
 * 签到
 *
 * @Author: [liguiqin]
 * @Date: [2017-08-29 17:30]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@LoginAuth
@RestController
@RequestMapping
public class SignAct {

    @Autowired
    private SignSer signSer;

    /**
     * 签到
     * @return {name:'data',type:'boolean',defaultValue:'',description:'true/false.'}
     * @version v1
     */
    @PostMapping("sign")
    public ActResult sign() throws ActException {
        try {
            int exp = signSer.sign();
            return ActResult.initialize(exp);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 签到列表
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return class SignVO
     * @version v1
     */
    @GetMapping("sign/list")
    public ActResult signList(String startDate, String endDate) throws ActException {
        try {
            List<Sign> signs = signSer.signList(startDate, endDate);
            List<SignVO> signVOS = BeanCopy.copyProperties(signs, SignVO.class);
            return ActResult.initialize(signVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}
