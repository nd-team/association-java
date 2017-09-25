package com.bjike.act.addressBook;

import com.bjike.common.aspect.ADD;
import com.bjike.common.aspect.EDIT;
import com.bjike.common.exception.ActException;
import com.bjike.common.exception.SerException;
import com.bjike.common.interceptor.login.LoginAuth;
import com.bjike.common.restful.ActResult;
import com.bjike.common.restful.Result;
import com.bjike.ser.addressBook.InterestAllianceSer;
import com.bjike.to.addressBook.InterestAllianceTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 兴趣联盟
 *
 * @Author: [chenjunhao]
 * @Date: [2017-08-31 11:13]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@LoginAuth //登录验证注解,header必须携带token
@RestController
@RequestMapping("addressBook/interest/alliance")
public class InterestAllianceAct {
    @Autowired
    private InterestAllianceSer interestAllianceSer;

    /**
     * 创建联盟
     *
     * @param to to
     * @throws ActException
     */
    @PostMapping("/add")
    public Result add(@Validated(ADD.class) InterestAllianceTO to, BindingResult result) throws ActException {
        try {
            interestAllianceSer.add(to);
            return new ActResult("创建成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑联盟
     *
     * @param to to
     * @throws ActException
     */
    @PutMapping("/edit")
    public Result edit(@Validated(EDIT.class) InterestAllianceTO to,BindingResult result) throws ActException {
        try {
            interestAllianceSer.edit(to);
            return new ActResult("编辑成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @throws ActException
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            interestAllianceSer.delete(id);
            return new ActResult("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加成员
     *
     * @param to to
     * @throws ActException
     */
    @PostMapping("/addMember")
    public Result addMember(@Validated(InterestAllianceTO.ADDMEMBER.class) InterestAllianceTO to,BindingResult result) throws ActException {
        try {
            interestAllianceSer.addMember(to);
            return new ActResult("添加成员成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除成员
     *
     * @param to to
     * @throws ActException
     */
    @DeleteMapping("/delMemeber")
    public Result delMemeber(@Validated(InterestAllianceTO.ADDMEMBER.class) InterestAllianceTO to,BindingResult result) throws ActException {
        try {
            interestAllianceSer.delMemeber(to);
            return new ActResult("删除成员成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 申请加入联盟
     *
     * @param id id
     * @throws ActException
     */
    @PostMapping("/Apply/{id}")
    public Result Apply(@PathVariable String id) throws ActException {
        try {
            interestAllianceSer.Apply(id);
            return new ActResult("发送申请成功，等待审核");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 同意加入联盟
     *
     * @param id     id
     * @param userId userId
     * @throws ActException
     */
    @PostMapping("/agree/{id}/{userId}")
    public Result agree(@PathVariable String id, @PathVariable String userId) throws ActException {
        try {
            interestAllianceSer.agree(id, userId);
            return new ActResult("同意成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 拒绝加入联盟
     *
     * @param id     id
     * @param userId userId
     * @throws ActException
     */
    @PutMapping("/refuse/{id}/{userId}")
    public Result refuse(@PathVariable String id, @PathVariable String userId) throws ActException {
        try {
            interestAllianceSer.refuse(id, userId);
            return new ActResult("拒绝成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过爱好标签查找兴趣联盟
     *
     * @param tag 爱好标签
     * @return class InterestAllianceVO
     * @throws ActException
     */
    @GetMapping("/findByTag/{tag}")
    public Result findByTag(@PathVariable String tag) throws ActException {
        try {
            return ActResult.initialize(interestAllianceSer.findByTag(tag));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找当前用户的所有兴趣联盟
     *
     * @return class InterestAllianceVO
     * @throws ActException
     */
    @GetMapping("/userAll")
    public Result userAll() throws ActException {
        try {
            return ActResult.initialize(interestAllianceSer.userAll());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找某联盟的所有成员
     *
     * @param id id
     * @return class UserVO
     * @throws ActException
     */
    @GetMapping("/findMembers/{id}")
    public Result findMembers(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(interestAllianceSer.findMembers(id));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}
