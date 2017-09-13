package com.bjike.act.CharitableActivities;

import com.bjike.common.aspect.ADD;
import com.bjike.common.exception.ActException;
import com.bjike.common.exception.SerException;
import com.bjike.common.interceptor.login.LoginAuth;
import com.bjike.common.restful.ActResult;
import com.bjike.common.restful.Result;
import com.bjike.ser.CharitableActivities.ReplySer;
import com.bjike.to.CharitableActivities.ReplyTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: [dengjunren]
 * @Date: [2017-08-31 17:42]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@LoginAuth
@RestController
@RequestMapping("replyAct")
public class ReplyAct {

//    @Autowired
//    private ReplySer replySer;

//    /**
//     * 回复评论
//     */
//    @PostMapping("/reply/{commentID}")
//    public Result reply(@Validated(ADD.class) ReplyTO to) throws ActException {
//        try {
//            replySer.reply(to);
//            return ActResult.initialize("回复成功");
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }

}
