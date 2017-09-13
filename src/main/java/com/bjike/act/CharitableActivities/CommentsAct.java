package com.bjike.act.CharitableActivities;

import com.bjike.common.aspect.ADD;
import com.bjike.common.aspect.EDIT;
import com.bjike.common.exception.ActException;
import com.bjike.common.exception.SerException;
import com.bjike.common.interceptor.login.LoginAuth;
import com.bjike.common.restful.ActResult;
import com.bjike.common.restful.Result;
import com.bjike.common.util.UserUtil;
import com.bjike.common.util.date.DateUtil;
import com.bjike.common.util.file.FileUtil;
import com.bjike.dto.CharitableActivities.CommentsDTO;
import com.bjike.ser.CharitableActivities.CommentsSer;
import com.bjike.to.CharitableActivities.CommentsTO;
import com.bjike.vo.CharitableActivities.CommentsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.time.LocalDate;
import java.util.List;

/**
 * 公益活动评论
 *
 * @Author: [dengjunren]
 * @Date: [2017-08-31 14:51]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@LoginAuth
@RestController
@RequestMapping("commentsAct")
public class CommentsAct {

    @Autowired
    private CommentsSer commentsSer;

    /**
     * 新增评论
     */
    @PostMapping("/add")
    public Result add(@Validated(ADD.class) CommentsTO to, BindingResult bindingResult) throws ActException {
        try {
            commentsSer.add(to);
            return ActResult.initialize("add success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取评论列表
     */
    @GetMapping("/list")
    public Result list(CommentsDTO dto) throws ActException {
        try {
            List<CommentsVO> list = commentsSer.list(dto);
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取对象
     */
    @GetMapping("/findById/{id}")
    public Result findById(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(commentsSer.find(id));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 公益活动的评论数
     *
     * @param initiateactivitiesID 公益活动ｉｄ
     */
    @GetMapping("/count/{initiateactivitiesID}")
    public Result getTotal(@PathVariable String initiateactivitiesID) throws ActException {
        try {
            return ActResult.initialize(commentsSer.getTotal(initiateactivitiesID));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 评论回复
     *
     * @param to to
     */
    @PostMapping("/reply/{id}") //id为被回复的评论id
    public Result reply(@Validated(EDIT.class) CommentsTO to, BindingResult bindingResult) throws ActException {
        try {
            commentsSer.reply(to);
            return ActResult.initialize("评论成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 评论点赞/取消
     */
    @PutMapping("/like/{commentID}") //被点赞的评论的id
    public Result like(@PathVariable String commentID) throws ActException {
        try {
            commentsSer.like(commentID);
            return ActResult.initialize("点赞成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除评论
     */
    @DeleteMapping("/delete/{commentID}")//要删除的评论id
    public Result delete(@PathVariable String commentID) throws ActException {
        try {
            commentsSer.delete(commentID);
            return ActResult.initialize("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上传
     *
     * @param commentId
     * @return
     * @throws Exception
     */
    @PostMapping("/upload/img/{commentId}")
    public Result uploadImg(@PathVariable String commentId, HttpServletRequest request) throws ActException {
        try {
            String userId = UserUtil.currentUserID();
            List<File> files = FileUtil.save(request, getCommentPath(userId));
            commentsSer.uploadImg(commentId, files);
            return new ActResult("success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 评论图片保存路径
     *
     * @param userId
     */
    private String getCommentPath(String userId) {
        return "/" + userId + "/charitableActivities/comment/" + DateUtil.dateToString(LocalDate.now()).replaceAll("-", "");
    }

    /**
     * 当前用户对该条评论是否已点赞
     */
    @GetMapping("/isPoint/{commentID}")
    public Result isPoint(@PathVariable String commentID) throws ActException {
        try {
            return ActResult.initialize(commentsSer.isPoint(commentID));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 得到当前用户点赞的评论id
     */
    @GetMapping("v1/getComments")
    public Result getComments() throws ActException {
        try {
            List<String> list = commentsSer.getComments();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}
