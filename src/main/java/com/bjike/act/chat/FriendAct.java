package com.bjike.act.chat;

import com.bjike.common.aspect.ADD;
import com.bjike.common.exception.ActException;
import com.bjike.common.exception.SerException;
import com.bjike.common.interceptor.login.LoginAuth;
import com.bjike.common.restful.ActResult;
import com.bjike.common.restful.Result;
import com.bjike.common.util.UserUtil;
import com.bjike.ser.chat.FriendSer;
import com.bjike.to.chat.FriendTO;
import com.bjike.type.chat.ApplyType;
import com.bjike.vo.chat.FriendGroupVO;
import com.bjike.vo.chat.FriendVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 我的好友
 *
 * @Author: [liguiqin]
 * @Date: [2017-07-21 11:18]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@LoginAuth
@RestController
@RequestMapping("chat/friend")
public class FriendAct {
    @Autowired
    private FriendSer friendSer;

    /**
     * 所有好友成员
     *
     * @return class FriendVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("list")
    public Result list() throws ActException {
        try {
            String userId = UserUtil.currentUserID();
            return ActResult.initialize(friendSer.list(userId));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 好友列表
     *
     * @param type 申请类型
     * @return class FriendVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("type/list")
    public Result findByApplyType(ApplyType type) throws ActException {
        try {
            String userId = UserUtil.currentUserID();
            return ActResult.initialize(friendSer.findByApplyType(type, userId));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 好友分组成员
     *
     * @param id 好友组id
     * @return class FriendVO
     * @version v1
     */
    @GetMapping("friendGroup/{id}")
    public Result friendGroup(@PathVariable String id) throws ActException {
        try {
            List<FriendVO> friendVOS = friendSer.friendGroup(id);
            return ActResult.initialize(friendVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 好友分组信息
     *
     * @return class FriendGroupVO
     * @version v1
     */
    @GetMapping("friendGroup/info")
    public Result groupInfo() throws ActException {
        try {
            String userId = UserUtil.currentUserID();
            List<FriendGroupVO> groupVOS = friendSer.groupInfo(userId);
            return ActResult.initialize(groupVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 好友申请
     *
     * @param to 好友申请传输
     * @throws ActException
     * @version v1
     */
    @PostMapping("apply")
    public Result apply(@Validated(ADD.class) FriendTO to, BindingResult rs) throws ActException {
        try {
            friendSer.add(to);
            return new ActResult("apply success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 同意好友申请
     *
     * @param friendId 好友id
     * @throws ActException
     * @version v1
     */
    @PutMapping("agree/{friendId}")
    public Result agree(@PathVariable String friendId) throws ActException {
        try {
            friendSer.agree(friendId);
            return new ActResult("agree success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 拒绝好友申请
     *
     * @param friendId 好友id
     * @version v1
     */
    @PutMapping("refuse/{friendId}")
    public Result refuse(@PathVariable String friendId) throws ActException {
        try {
            friendSer.refuse(friendId);
            return new ActResult("refuse success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }


    /**
     * 删除好友
     *
     * @param friendId 好友id
     * @version v1
     */
    @DeleteMapping("delete/{friendId}")
    public Result delete(@PathVariable String friendId) throws ActException {
        try {
            friendSer.delete(friendId);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 备注好友
     *
     * @param friendId 好友id
     * @throws ActException
     * @version v1
     */
    @PutMapping("remark")
    public Result editNickname(String nickname, String friendId, HttpServletRequest request) throws ActException {
        try {
            friendSer.editRemark(friendId, nickname);
            return new ActResult("remark success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}
