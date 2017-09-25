package com.bjike.act.chat;

import com.bjike.common.exception.ActException;
import com.bjike.common.exception.SerException;
import com.bjike.common.interceptor.login.LoginAuth;
import com.bjike.common.restful.ActResult;
import com.bjike.common.restful.Result;
import com.bjike.ser.chat.GroupMemberSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 群成员管理
 *
 * @Author: [liguiqin]
 * @Date: [2017-09-12 13:58]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@LoginAuth
@RestController
@RequestMapping("chat/group/member")
public class GroupMemberAct {

    @Autowired
    private GroupMemberSer groupMemberSer;

    /**
     * 添加群成员
     *
     * @param userId  新增群员id
     * @param groupId 群id
     * @throws ActException
     * @version v1
     */
    @PostMapping("add/to/{groupId}")
    public Result add(String[] userId, @PathVariable String groupId) throws ActException {
        try {
            groupMemberSer.add(groupId, userId);
            return ActResult.initialize("add success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除群成员
     *
     * @param userId  群员id
     * @param groupId 群id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("delete/by/{groupId}")
    public Result del(String[] userId, @PathVariable String groupId) throws ActException {
        try {
            groupMemberSer.del(groupId, userId);
            return ActResult.initialize("del success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 退出群
     *
     * @param groupId 群id
     * @throws ActException
     * @version v1
     */
    @PutMapping("quit/{groupId}")
    public Result quit(@PathVariable String groupId) throws ActException {
        try {
            groupMemberSer.quit(groupId);
            return ActResult.initialize("quit success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
