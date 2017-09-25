package com.bjike.act.user;

import com.bjike.common.exception.ActException;
import com.bjike.common.exception.SerException;
import com.bjike.common.interceptor.login.LoginAuth;
import com.bjike.common.restful.ActResult;
import com.bjike.common.util.QRCodeUtil;
import com.bjike.common.util.UserUtil;
import com.bjike.common.util.bean.BeanCopy;
import com.bjike.common.util.file.FileUtil;
import com.bjike.entity.user.User;
import com.bjike.ser.user.UserSer;
import com.bjike.to.user.UserInfoTO;
import com.bjike.vo.user.UserInfoVO;
import com.bjike.vo.user.UserVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

/**
 * 用户
 *
 * @Author: [liguiqin]
 * @Date: [2017-08-22 15:50]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("user")
public class UserAct {
    @Autowired
    private UserSer userSer;

    /**
     * 当前用户信息
     *
     * @return class UserInfoVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("info")
    public ActResult userInfo() throws ActException {
        try {
            User user = UserUtil.currentUser();
            UserInfoVO vo = userSer.userInfo(user.getId());
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 用户信息
     * @version v1
     */
    @LoginAuth
    @GetMapping("edit/info")
    public ActResult editInfo(UserInfoTO to) throws ActException {
        try {
            String userId = UserUtil.currentUserID();
            return ActResult.initialize(userSer.editInfo(userId, to));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * id查询个人信息
     *
     * @param userId 用户id
     * @return class UserInfoVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("find/{userId}")
    public ActResult findByUserId(@PathVariable String userId) throws ActException {
        try {
            User user = userSer.findById(userId);
            UserInfoVO vo = userSer.userInfo(user.getId());
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 二维码名片
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("card")
    public ActResult card(HttpServletResponse response) throws ActException {
        try {
            User user = UserUtil.currentUser();
            if (StringUtils.isNotBlank(user.getHeadPath())) {
                response.setContentType("image/jpeg");
                response.setDateHeader("expries", -1);
                response.setHeader("Cache-Control", "no-cache");
                try {
                    BufferedImage image = QRCodeUtil.encode(user.getId(), FileUtil.getRealPath(user.getHeadPath()), response.getOutputStream(), true);
                    ImageIO.write(image, "JPEG", response.getOutputStream());
                    image.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new ActException("生成二维码名片错误!");
                }

            } else {
                throw new ActException("请先上传头像!");
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
        return new ActResult("success");
    }

    /**
     * 头像上传
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("upload/headPath")
    public ActResult uploadHeadPath(HttpServletRequest request) throws ActException {
        try {
            User user = UserUtil.currentUser();
            List<File> files = FileUtil.save(request, "/" + user.getId() + "/head");
            String path = FileUtil.getRealPath(files.get(0).getPath());
            userSer.uploadHeadPath(path);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ActException("上传头像错误!");
        }
        return new ActResult("success");
    }


    /**
     * 查找用户
     *
     * @param account 手机号昵称用户编号
     * @return class UserVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("search/{account}")
    public ActResult search(@PathVariable String account) throws ActException {
        try {
            List<User> users = userSer.findByAccount(account);
            List<UserVO> userVOS = BeanCopy.copyProperties(users, UserVO.class);
            return ActResult.initialize(userVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}
