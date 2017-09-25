package com.bjike.act.addressBook;

import com.bjike.common.aspect.ADD;
import com.bjike.common.aspect.EDIT;
import com.bjike.common.exception.ActException;
import com.bjike.common.exception.SerException;
import com.bjike.common.interceptor.login.LoginAuth;
import com.bjike.common.restful.ActResult;
import com.bjike.common.restful.Result;
import com.bjike.ser.addressBook.AllianceActivitySer;
import com.bjike.to.addressBook.ActivityMemberTO;
import com.bjike.to.addressBook.AllianceActivityTO;
import com.bjike.vo.activity.ActivityDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 联盟活动
 *
 * @Author: [chenjunhao]
 * @Date: [2017-08-31 11:10]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@LoginAuth //登录验证注解,header必须携带token
@RestController
@RequestMapping("addressBook/alliance/activity")
public class AllianceActivityAct {
    @Autowired
    private AllianceActivitySer allianceActivitySer;

    /**
     * 发布活动
     *
     * @param interestAllianceId 联盟id
     * @param to                 to
     * @throws ActException
     */
    @PostMapping("/pulbish/{interestAllianceId}")
    public Result pulbish(@PathVariable String interestAllianceId, @Validated(ADD.class) AllianceActivityTO to, BindingResult result) throws ActException {
        try {
            allianceActivitySer.pulbish(interestAllianceId, to);
            return new ActResult("发布活动成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑活动
     *
     * @param to to
     * @throws ActException
     */
    @PutMapping("/edit")
    public Result edit(@Validated(EDIT.class) AllianceActivityTO to, BindingResult result) throws ActException {
        try {
            allianceActivitySer.edit(to);
            return new ActResult("编辑活动成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除活动
     *
     * @param id id
     * @throws ActException
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            allianceActivitySer.delete(id);
            return new ActResult("删除活动成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查看某联盟的所有活动
     *
     * @param interestAllianceId 联盟id
     * @return class AllianceActivityVO
     * @throws ActException
     */
    @GetMapping("/list/{interestAllianceId}")
    public Result list(@PathVariable String interestAllianceId) throws ActException {
        try {
            return ActResult.initialize(allianceActivitySer.list(interestAllianceId));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 我要报名
     *
     * @param id               id
     * @param activityMemberTO activityMemberTO
     * @throws ActException
     */
    @PostMapping("/attend/{id}")
    public Result attend(@PathVariable String id, @Validated(ADD.class) ActivityMemberTO activityMemberTO, BindingResult result) throws ActException {
        try {
            allianceActivitySer.attend(id, activityMemberTO);
            return new ActResult("报名成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找某活动的所有成员
     *
     * @param id id
     * @return class ActivityMemberVO
     * @throws ActException
     */
    @GetMapping("/findMembers/{id}")
    public Result findMembers(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(allianceActivitySer.findMembers(id));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 活动详情
     *
     * @param id id
     * @return class ActivityDetailVO
     * @throws ActException
     */
    @GetMapping("/detail/{id}")
    public Result detail(@PathVariable String id) throws ActException {
        try {
            ActivityDetailVO vo = allianceActivitySer.detail(id);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}
