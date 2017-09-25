package com.bjike.act.activity;

import com.bjike.common.aspect.ADD;
import com.bjike.common.aspect.EDIT;
import com.bjike.common.exception.ActException;
import com.bjike.common.exception.SerException;
import com.bjike.common.interceptor.login.LoginAuth;
import com.bjike.common.restful.ActResult;
import com.bjike.common.restful.Result;
import com.bjike.ser.activity.ActivityCommentSer;
import com.bjike.to.activity.ActivityCommentTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 活动评论控制器
 *
 * @Author: [ chenjunhao ]
 * @Date: [  2017-09-04 13:58:03 ]
 * @Description: [ 活动评论 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [com.bjike]
 */

@LoginAuth //登录验证注解,header必须携带token
@RestController
@RequestMapping("activity/activity/comment")
public class ActivityCommentAct {
    @Autowired
    private ActivityCommentSer activityCommentSer;

    /**
     * 评论
     *
     * @param to to
     * @throws ActException
     */
    @PostMapping("/comment")
    public Result comment(@Validated(ADD.class) ActivityCommentTO to, BindingResult result) throws ActException {
        try {
            activityCommentSer.comment(to);
            return new ActResult("评论成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除评论
     *
     * @param id id
     * @throws ActException
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            activityCommentSer.delete(id);
            return new ActResult("删除评论成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 回复评论
     *
     * @param to to
     * @throws ActException
     */
    @PostMapping("/repay")
    public Result repay(@Validated(EDIT.class) ActivityCommentTO to, BindingResult result) throws ActException {
        try {
            activityCommentSer.repay(to);
            return new ActResult("回复评论成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查看某活动的所有评论
     *
     * @param activityId 活动id
     * @return class ActivityCommentVO
     * @throws ActException
     */
    @GetMapping("/comments/{activityId}")
    public Result comments(@PathVariable String activityId) throws ActException {
        try {
            return ActResult.initialize(activityCommentSer.comments(activityId));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查看某活动的评论数
     *
     * @param activityId 活动id
     * @throws ActException
     */
    @GetMapping("/comment/num/{activityId}")
    public Result commentNum(@PathVariable String activityId) throws ActException {
        try {
            return ActResult.initialize(activityCommentSer.commentNum(activityId));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 对评论点赞
     *
     * @param id id
     * @throws ActException
     */
    @PostMapping("/thumb/{id}")
    public Result thumb(@PathVariable String id) throws ActException {
        try {
            activityCommentSer.thumb(id);
            return new ActResult("点赞成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 取消点赞
     *
     * @param id id
     * @throws ActException
     */
    @DeleteMapping("/cancelThumb/{id}")
    public Result cancelThumb(@PathVariable String id) throws ActException {
        try {
            activityCommentSer.cancelThumb(id);
            return new ActResult("取消点赞成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 某条评论的点赞数
     *
     * @param id id
     * @throws ActException
     */
    @GetMapping("/thumb/num/{id}")
    public Result thumbNum(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(activityCommentSer.thumbNum(id));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查看当前用户对某条评论是否有点赞
     *
     * @param id id
     * @throws ActException
     */
    @GetMapping("/check/{id}")
    public Result check(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(activityCommentSer.check(id));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}
