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
import com.bjike.dto.CharitableActivities.InitiateActivitiesDTO;
import com.bjike.ser.CharitableActivities.InitiateActivitiesSer;
import com.bjike.to.CharitableActivities.InitiateActivitiesTO;
import com.bjike.vo.CharitableActivities.InitiateActivitiesVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.time.LocalDate;
import java.util.List;

/**
 * 公益活动发起
 *
 * @Author: [zhuangkaiqin]
 * @Date: [2017-08-30 19:11]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@LoginAuth
@RestController
@RequestMapping("initiateActivitiesAct")
public class InitiateActivitiesAct {

    @Autowired
    private InitiateActivitiesSer initiateActivitiesSer;

    /**
     * 公益活动列表
     */
    @GetMapping("/list")
    public Result list(InitiateActivitiesDTO dto) throws ActException {
        try {
            List<InitiateActivitiesVO> list = initiateActivitiesSer.list(dto);
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
            return ActResult.initialize(initiateActivitiesSer.find(id));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 活动发起
     */
    @PostMapping("/add")
    public Result add(@Validated(ADD.class) InitiateActivitiesTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            String userId =  UserUtil.currentUserID();
            List<File> files = FileUtil.save(request, getCommentPath(userId));
            initiateActivitiesSer.add(to, files);
            return ActResult.initialize("发起成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 活动信息修改
     */
    @PutMapping("/edit/{id}")
    public Result edit(@Validated(EDIT.class) InitiateActivitiesTO to, BindingResult bindingResult) throws ActException {
        try {
            initiateActivitiesSer.edit(to);
            return ActResult.initialize("edit success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 活动删除
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            initiateActivitiesSer.delete(id);
            return ActResult.initialize("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 公益活动点赞/取消点赞
     */
    @PutMapping("/like/{initiateactivitiesID}")
    public Result like(@PathVariable String initiateactivitiesID) throws ActException {
        try {
            initiateActivitiesSer.like(initiateactivitiesID);
            return ActResult.initialize("点赞成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 转发
     */
    @GetMapping("/forward/{initiateactivitiesID}")
    public Result forward(@PathVariable String initiateactivitiesID) throws ActException {
        try {
            initiateActivitiesSer.forward(initiateactivitiesID);
            return ActResult.initialize("转发成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上传
     *
     * @param initiateactivitiesID
     * @return
     * @throws Exception
     */
    @PostMapping("/upload/img/{initiateactivitiesID}")
    public Result uploadImg(@PathVariable String initiateactivitiesID, HttpServletRequest request) throws ActException {
        try {
            String userId = UserUtil.currentUserID();
            List<File> files = FileUtil.save(request, getCommentPath(userId));
            initiateActivitiesSer.uploadImg(initiateactivitiesID, files);
            return new ActResult("success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 图片保存路径
     *
     * @param userId
     * @return
     */
    private String getCommentPath(String userId) {
        return "/" + userId + "/charitableActivities/initiateactivities/" + DateUtil.dateToString(LocalDate.now()).replaceAll("-", "");
    }

    /**
     * 我参与的公益
     *
     * @return class InitiateActivitiesVO
     */
    @GetMapping("v1/partake")
    public Result partake() throws ActException {
        try {
            return ActResult.initialize(initiateActivitiesSer.partake());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 当前用户对该公益活动是否已点赞
     *
     * @param initiateactivitiesID
     */
    @GetMapping("/isPoint/{initiateactivitiesID}")
    public Result isPoint(@PathVariable String initiateactivitiesID) throws ActException {
        try {
            return ActResult.initialize(initiateActivitiesSer.isPoint(initiateactivitiesID));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 得到当前用户点赞的公益活动id
     */
    @GetMapping("v1/getInitiateActivitieses")
    public Result getInitiateActivitieses() throws ActException {
        try {
            List<String> list = initiateActivitiesSer.getInitiateActivitieses();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
