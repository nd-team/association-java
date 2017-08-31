package com.bjike.act.chat;

import com.bjike.common.exception.ActException;
import com.bjike.common.exception.SerException;
import com.bjike.common.interceptor.login.LoginAuth;
import com.bjike.common.restful.ActResult;
import com.bjike.common.restful.Result;
import com.bjike.common.util.UserUtil;
import com.bjike.entity.chat.Friend;
import com.bjike.ser.chat.FriendSer;
import com.bjike.ser.chat.GroupSer;
import com.bjike.to.chat.GroupTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 群
 *
 * @Author: [liguiqin]
 * @Date: [2017-07-19 13:58]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@LoginAuth
@RestController
@RequestMapping("chat/group")
public class GroupAct {

    @Autowired
    private GroupSer groupSer;

    @Autowired
    private FriendSer friendSer;
    /**
     * 群成员
     *
     * @param groupId 群id
     * @return class FriendVO
     * @throws ActException
     */
    @GetMapping("member/{groupId}")
    public Result groupMember(@PathVariable String groupId) throws ActException {
        try {
            return ActResult.initialize(friendSer.groupMember(groupId));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 用户所拥有的群
     * @return class Group
     * @throws ActException
     */
    @GetMapping("user/list")
    public Result listByUser() throws ActException {
        try {
            String userId = UserUtil.currentUserID();
            groupSer.listByUser(userId);
            return new ActResult("add success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 添加群
     * @param to 群信息
     * @throws ActException
     */
    @PostMapping("add")
    public Result add(GroupTO to) throws ActException {
        try {
            groupSer.add(to);
            return new ActResult("add success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 解散群
     * @param id 群id
     * @throws ActException
     */
    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            groupSer.remove(id);
            return new ActResult("delete sueecss");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑群信息
     * @param to 群信息
     * @return
     * @throws ActException
     */
    @PutMapping("edit")
    public Result edit(GroupTO to) throws ActException {
        try {
            groupSer.edit(to);
            return new ActResult("edit success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}
