package com.bjike.act.comment;

import com.bjike.common.aspect.ADD;
import com.bjike.common.exception.ActException;
import com.bjike.common.exception.SerException;
import com.bjike.common.interceptor.login.LoginAuth;
import com.bjike.common.restful.ActResult;
import com.bjike.common.restful.Result;
import com.bjike.common.util.UserUtil;
import com.bjike.common.util.bean.BeanCopy;
import com.bjike.common.util.date.DateUtil;
import com.bjike.common.util.file.FileUtil;
import com.bjike.dto.comment.CommentDTO;
import com.bjike.entity.comment.Comment;
import com.bjike.ser.comment.CommentSer;
import com.bjike.to.comment.CommentTO;
import com.bjike.vo.comment.CommentDetailsVO;
import com.bjike.vo.comment.CommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.time.LocalDate;
import java.util.List;

/**
 * 点评
 *
 * @Author: [liguiqin]
 * @Date: [2017-06-28 14:25]
 * @Description: [ 点评]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@LoginAuth
@RestController
@RequestMapping("comment")
public class CommentAct {
    @Autowired
    private CommentSer commentSer;

    /**
     * 添加点评
     *
     * @param to 点评内容
     * @return class CommentVO
     * @throws Exception
     * @version v1
     */
    @PostMapping("add")
    public Result add(@Validated(ADD.class) CommentTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            String path =FileUtil.getModulePath("comment",true);
            List<File> files = FileUtil.save(request,path);
            Comment comment = commentSer.add(to, files);
            CommentVO vo = BeanCopy.copyProperties(comment, CommentVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }


    }

    /**
     * 点评列表
     *
     * @return class CommentVO
     * @version v1
     */
    @GetMapping("list")
    public Result list(CommentDTO dto) throws ActException {
        try {
            List<CommentVO> vos = commentSer.list(dto);
            return ActResult.initialize(vos);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 店铺点评量
     *
     * @param pointId 点评地址id
     * @return {name:'data',type:'int',defaultValue:'',description:'点评量.'}
     * @version v1
     */
    @GetMapping("count")
    public Result count(String pointId) throws ActException {
        try {
            return ActResult.initialize(commentSer.count(pointId));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 点评点赞
     *
     * @param commentId 点评id
     *                  {name:'data',type:'string',defaultValue:'',description:'success.'}
     * @version v1
     */
    @PutMapping("like/{commentId}")
    public Result like(@PathVariable String commentId) throws ActException {
        try {
            commentSer.like(commentId);
            return new ActResult("success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 取消点赞
     * {name:'data',type:'string',defaultValue:'',description:'success.'}
     *
     * @param commentId 点评id
     * @version v1
     */
    @PutMapping("cancel/like/{commentId}")
    public Result notLike(@PathVariable String commentId) throws ActException {
        try {
            commentSer.cancelLike(commentId);
            return new ActResult("success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }


    /**
     * 删除点评
     * {name:'data',type:'string',defaultValue:'',description:'success.'}
     *
     * @param commentId 点评id
     * @version v1
     */
    @DeleteMapping("delete/{commentId}")
    public Result delete(@PathVariable String commentId) throws ActException {
        try {
            commentSer.remove(commentId);
            return new ActResult("success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }


    /**
     * 店铺总评分
     * {name:'data',type:'ScoreType',defaultValue:'',description:'ScoreType.'}
     *
     * @param pointId 店铺地址id
     * @version v1
     */
    @GetMapping("score/{pointId}")
    public Result score(String pointId) throws ActException {
        try {
            return ActResult.initialize(commentSer.score(pointId));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上传
     * {name:'data',type:'string',defaultValue:'',description:'success.'}
     *
     * @param commentId 点评id
     * @version v1
     */
    @PostMapping("upload/img/{commentId}")
    public Result uploadImg(@PathVariable String commentId, HttpServletRequest request) throws ActException {
        try {
            String path =FileUtil.getModulePath("comment",true);
            List<File> files = FileUtil.save(request, path);
            commentSer.uploadImg(commentId, files);
            return new ActResult("success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 点评详情
     *
     * @param commentId 点评id
     * @return class CommentDetailsVO
     * @version v1
     */
    @GetMapping("details/{commentId}")
    public Result details(@PathVariable String commentId) throws ActException {
        try {
            CommentDetailsVO vo = commentSer.details(commentId);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }



}
