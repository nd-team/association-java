package com.bjike.act.chat;

import com.bjike.common.aspect.ADD;
import com.bjike.common.aspect.EDIT;
import com.bjike.common.exception.ActException;
import com.bjike.common.exception.SerException;
import com.bjike.common.interceptor.login.LoginAuth;
import com.bjike.common.restful.ActResult;
import com.bjike.common.restful.Result;
import com.bjike.common.util.UserUtil;
import com.bjike.entity.chat.Group;
import com.bjike.ser.chat.FriendSer;
import com.bjike.ser.chat.GroupSer;
import com.bjike.to.chat.GroupTO;
import com.bjike.vo.chat.FriendVO;
import com.bjike.vo.chat.GroupVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * @version v1
     */
    @GetMapping("member/{groupId}")
    public Result groupMember(@PathVariable String groupId) throws ActException {
        try {
            List<FriendVO> friendVOS = friendSer.groupMember(groupId);
            return ActResult.initialize(friendVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 我的群列表
     *
     * @return class GroupVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("user/list")
    public Result listByUser() throws ActException {
        try {
            String userId = UserUtil.currentUserID();
            List<GroupVO> groups = groupSer.listByUser(userId);
            return ActResult.initialize(groups);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 添加群
     *
     * @param to 群信息
     * @throws ActException
     * @version v1
     */
    @PostMapping("add")
    public Result add(@Validated(ADD.class) GroupTO to, BindingResult rs) throws ActException {
        try {
            groupSer.add(to);
            return new ActResult("add success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 解散群
     *
     * @param id 群id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            groupSer.remove(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑群信息
     *
     * @param to 群信息
     * @throws ActException
     * @version v1
     */
    @PutMapping("edit")
    public Result edit(@Validated(EDIT.class) GroupTO to,BindingResult rs) throws ActException {
        try {
            groupSer.edit(to);
            return new ActResult("edit success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}
