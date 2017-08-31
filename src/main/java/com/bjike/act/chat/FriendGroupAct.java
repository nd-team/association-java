package com.bjike.act.chat;

import com.bjike.common.exception.ActException;
import com.bjike.common.exception.SerException;
import com.bjike.common.interceptor.login.LoginAuth;
import com.bjike.common.restful.ActResult;
import com.bjike.common.restful.Result;
import com.bjike.ser.chat.FriendGroupSer;
import com.bjike.to.chat.FriendGroupTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 朋友分组
 *
 * @Author: [liguiqin]
 * @Date: [2017-07-21 10:47]
 * @Description: [ 好友分组]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@LoginAuth
@RestController
@RequestMapping("chat/friend/group")
public class FriendGroupAct {

    @Autowired
    private FriendGroupSer friendGroupSer;

    /**
     * 添加分组
     *
     * @param to 好友分组数据传输
     * @throws ActException
     */
    @PostMapping("add")
    public Result add(FriendGroupTO to) throws ActException {
        try {
            friendGroupSer.add(to);
            return new ActResult("add success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 删除分组
     *
     * @param id 分组id
     * @throws ActException
     */
    @DeleteMapping("delete")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            friendGroupSer.remove(id);
            return new ActResult("delete sueecss");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑分组
     *
     * @param to 好友分组数据传输
     * @throws ActException
     */
    @PutMapping("edit")
    public Result edit(FriendGroupTO to) throws ActException {
        try {
            friendGroupSer.edit(to);
            return new ActResult("edit success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}
