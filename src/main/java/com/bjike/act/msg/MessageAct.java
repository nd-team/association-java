package com.bjike.act.msg;


import com.bjike.common.aspect.EDIT;
import com.bjike.common.exception.ActException;
import com.bjike.common.exception.SerException;
import com.bjike.common.interceptor.login.LoginAuth;
import com.bjike.common.restful.ActResult;
import com.bjike.common.restful.Result;
import com.bjike.common.util.UserUtil;
import com.bjike.common.util.bean.BeanCopy;
import com.bjike.dto.msg.MessageDTO;
import com.bjike.entity.msg.Message;
import com.bjike.ser.msg.MessageSer;
import com.bjike.to.msg.MessageTO;
import com.bjike.type.msg.MsgType;
import com.bjike.vo.msg.MessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 消息功能操作
 *
 * @Author: [liguiqin]
 * @Date: [2017-03-15 14:26]
 * @Description: [消息推送]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("msg")
@LoginAuth
public class MessageAct {
    @Autowired
    private MessageSer messageSer;


    /**
     * 读取消息
     *
     * @param id 消息id
     * @throws ActException
     * @version v1
     */
    @GetMapping("read/{id}")
    public Result read(@PathVariable String id) throws ActException {
        try {
            messageSer.read(id);
            return ActResult.initialize("read success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 读取消息列表
     *
     * @param msgType 消息类型
     * @throws ActException
     * @version v1
     */
    @GetMapping("list")
    public Result messages(MsgType msgType, HttpServletRequest request) throws ActException {
        try {
            MessageDTO dto = new MessageDTO();
            dto.setMsgType(msgType);
            List<Message> messages = messageSer.list(dto);
            List<MessageVO> vos = BeanCopy.copyProperties(messages, MessageVO.class, request);
            return ActResult.initialize(vos);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 未读消息
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("list/unread")
    public Result unreadMessages(MsgType msgType, HttpServletRequest request) throws ActException {
        try {
            String userId = UserUtil.currentUserID();
            List<Message> messages = messageSer.unreadList(userId, msgType);
            List<MessageVO> vos = BeanCopy.copyProperties(messages, MessageVO.class, request);
            return ActResult.initialize(vos);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 消息修改
     *
     * @param messageTO 消息体
     * @throws ActException
     * @version v1
     */
    @PutMapping("edit")
    public Result edit(@Validated(EDIT.class) MessageTO messageTO, BindingResult result) throws ActException {
        try {
            messageSer.edit(messageTO);
            return new ActResult("edit success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 消息删除
     *
     * @param id 消息id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            messageSer.remove(id);
            return new ActResult("delete  success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}
