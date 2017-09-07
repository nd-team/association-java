package com.bjike.ser.user;

import com.alibaba.fastjson.JSON;
import com.bjike.common.constant.UserCommon;
import com.bjike.common.exception.SerException;
import com.bjike.common.util.UserUtil;
import com.bjike.common.util.bean.BeanCopy;
import com.bjike.dao.user.UserRep;
import com.bjike.dto.Restrict;
import com.bjike.dto.user.UserDTO;
import com.bjike.dto.user.UserInfoDTO;
import com.bjike.entity.user.User;
import com.bjike.entity.user.UserInfo;
import com.bjike.redis.client.RedisClient;
import com.bjike.ser.ServiceImpl;
import com.bjike.session.UserSession;
import com.bjike.to.user.UserInfoTO;
import com.bjike.to.user.VIPApplyTO;
import com.bjike.type.user.UserType;
import com.bjike.vo.user.UserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-07-06 14:11]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class UserImpl extends ServiceImpl<User, UserDTO> implements UserSer {
    @Autowired
    private UserRep userRep;
    @Autowired
    private UserInfoSer userInfoSer;
    @Autowired
    private RedisClient redis;

    @Transactional
    @Override
    public void update(User entity) throws SerException {
        String token = UserUtil.currentToken();
        UserSession.put(token, entity); //同步更新缓存
        redis.appendToMap(UserCommon.LOGIN_USER, token, JSON.toJSONString(entity), UserCommon.LOGIN_TIMEOUT);
        super.update(entity);
    }

    @Override
    public User findByUsername(String username) throws SerException {
        return userRep.findByUsername(username);
    }


    @Override
    public User findByPhone(String phone) throws SerException {
        return userRep.findByPhone(phone);
    }

    @Override
    public Boolean isLogin(String token) throws SerException {
        return null != UserUtil.currentUser(token);
    }

    @Override
    public UserInfoVO userInfo(String userId) throws SerException {
        UserInfoVO userInfoVO = new UserInfoVO();
        UserInfoDTO dto = new UserInfoDTO();
        dto.getConditions().add(Restrict.eq("user.id", userId));
        UserInfo info = userInfoSer.findOne(dto);
        User user = UserUtil.currentUser();
        BeanCopy.copyProperties(user, userInfoVO);
        BeanCopy.copyProperties(info, userInfoVO, "user");
        userInfoVO.setId(user.getId());
        return userInfoVO;
    }

    @Transactional
    @Override
    public Boolean editInfo(String userId, UserInfoTO to) throws SerException {
        User user = super.findById(userId);
        BeanCopy.copyProperties(to, user);
        super.update(user);
        UserInfoDTO dto = new UserInfoDTO();
        dto.getConditions().add(Restrict.eq("user.id", userId));
        UserInfo info = userInfoSer.findOne(dto);
        BeanCopy.copyProperties(to, info);
        userInfoSer.update(info);
        return true;
    }

    @Override
    public List<User> findByAccount(String account) throws SerException {
        UserDTO dto = new UserDTO();
        dto.getConditions().add(Restrict.eq("phone", account));
        dto.getConditions().add(Restrict.or("number", account));
        return super.findByCis(dto);
    }

    @Override
    public void uploadHeadPath(String path) throws SerException {
        User user = UserUtil.currentUser(false);
        user.setHeadPath(path);
        this.update(user);
    }

    @Override
    public Boolean vipApply(VIPApplyTO to) throws SerException {
        User user = UserUtil.currentUser(false);
        if (user.getUserType().equals(UserType.ORDINARY)) {
            if (user.getPhone().equals(to.getPhone())) {
                UserInfo userInfo = userInfoSer.findByUserId(user.getId());
                BeanCopy.copyProperties(to, userInfo);
                userInfoSer.update(userInfo);
                user.setUserType(UserType.PERSONAL_VIP);
                super.update(user);
            } else {
                throw new SerException("注册手机填写错误");
            }
        } else {
            throw new SerException("您已是vip用户");
        }
        return null;
    }
}


