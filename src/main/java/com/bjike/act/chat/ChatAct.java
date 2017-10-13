package com.bjike.act.chat;

import com.bjike.common.exception.ActException;
import com.bjike.common.exception.SerException;
import com.bjike.common.interceptor.login.LoginAuth;
import com.bjike.common.restful.ActResult;
import com.bjike.common.restful.Result;
import com.bjike.common.util.UserUtil;
import com.bjike.common.util.file.FileUtil;
import com.bjike.entity.chat.AudioClient;
import com.bjike.entity.chat.Msg;
import com.bjike.ser.chat.ChatSer;
import com.bjike.session.AudioClientSession;
import com.bjike.session.AudioSession;
import com.bjike.session.ChatSession;
import com.bjike.type.msg.MsgType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 聊天
 *
 * @Author: [liguiqin]
 * @Date: [2017-07-19 13:58]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@LoginAuth
@RestController
@RequestMapping("chat")
public class ChatAct {
    @Autowired
    private ChatSer chatSer;

    /**
     * 退出聊天
     *
     * @param userId 用户id
     * @throws ActException
     * @version v1
     */
    @GetMapping("quit/{userId}")
    public Result quit(@PathVariable String userId) throws ActException {
        try {
            Msg msg = new Msg();
            msg.setMsgType(MsgType.OFFLINE);
            msg.setContent("下线通知");
            msg.setUserId(userId);
            chatSer.broadcast(msg);
            ChatSession.remove(userId);
            AudioSession.remove(userId);
            return new ActResult("quit success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 加入语音聊天
     *
     * @param sender  发送人
     * @param reviver 接收人
     * @throws ActException
     * @version v1
     */
    @GetMapping("join/audio/{sender}/{reviver}")
    public Result join(@PathVariable String sender, @PathVariable String reviver) throws ActException {
        AudioClient client = AudioClientSession.get(sender);
        if (null != client) {
            Map<String, Boolean> map = client.getRequester();
            for (Map.Entry<String, Boolean> entry : map.entrySet()) {
                if (entry.getValue().equals(reviver)) {
                    entry.setValue(true);
                    client.setRequester(map);
                    return ActResult.initialize("join success");
                }
            }
        }
        return new ActResult("audio is over");
    }

    /**退出語音聊天
     *  @version v1
     * @param userId 退出語音聊天
     * @throws ActException
     */
    @GetMapping("quit/audio/{userId}")
    public Result quitAudio(@PathVariable String userId) throws ActException {
        AudioClientSession.remove(userId);
        AudioSession.remove(userId);
        return new ActResult("audio is over");
    }

    /**
     * 文件发送
     *
     * @throws ActException
     * @version v1
     */
    @PostMapping("file/upload")
    public Result upload(HttpServletRequest request) throws ActException {
        try {
            String userId = UserUtil.currentUserID();
            List<File> files = FileUtil.save(request, "/" + userId + "/chat/file");
            String[] paths = new String[files.size()];
            int i = 0;
            for (File file : files) {
                paths[i++] = FileUtil.getDbPath(file.getPath());
            }
            return ActResult.initialize(paths);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

}
