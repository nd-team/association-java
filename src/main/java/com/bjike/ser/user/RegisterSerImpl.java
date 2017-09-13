package com.bjike.ser.user;

import com.bjike.common.exception.SerException;
import com.bjike.common.util.PasswordHash;
import com.bjike.common.util.SeqUtil;
import com.bjike.common.util.regex.Validator;
import com.bjike.entity.chat.Friend;
import com.bjike.entity.user.Recommend;
import com.bjike.entity.user.User;
import com.bjike.entity.user.UserInfo;
import com.bjike.ser.chat.FriendSer;
import com.bjike.session.AuthCodeSession;
import com.bjike.to.user.LoginTO;
import com.bjike.to.user.RegisterTO;
import com.bjike.type.chat.ApplyType;
import com.bjike.type.user.UserType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @Author: [liguiqin]
 * @Date: [2017-08-22 10:11]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class RegisterSerImpl implements RegisterSer {
    @Autowired
    private UserSer userSer;
    @Autowired
    private UserInfoSer userInfoSer;
    @Autowired
    private LoginSer loginSer;
    @Autowired
    private RecommendSer recommendSer;
    @Autowired
    private FriendSer friendSer;

    @Transactional
    @Override
    public String register(RegisterTO to) throws SerException {
        validRegister(to); //注册验证
        Recommend recommend = initRecommend(to);//验证邀请码并获得推荐详情信息,如无邀请码,则为普通注册情况
        String name = null != recommend ? recommend.getRealName() : to.getNickname();
        UserType userType = null != recommend ? UserType.PERSONAL_VIP : UserType.ORDINARY; //是否为vip
        User user = new User();
        user.setUsername(name);
        user.setNickname(name);
        user.setUserType(userType);
        user.setPhone(to.getPhone());
        user.setCreateTime(LocalDateTime.now());
        user.setNumber(SeqUtil.genNumber(userSer.findByMaxField("number", User.class)));
        try {
            user.setPassword(PasswordHash.createHash(to.getPassword()));
        } catch (Exception e) {
            throw new SerException(e.getMessage());
        }
        userSer.save(user);
        if (StringUtils.isNotBlank(to.getInviteCode())) { //邀请码注册情况
            initUserInfo(user, recommend);//完善用户信息
            addFriend(recommend, user);//添加好友
            recommend.setRecommended(user); //设置被推荐人
            recommendSer.update(recommend);
        }else { //简单的注册情况
            UserInfo userInfo = new UserInfo();
            userInfo.setUser(user);
            userInfo.setExperience(0.0);
            userInfo.setContribute(0.0);
            userInfo.setReputation(0.0);
            userInfoSer.save(userInfo);
        }
        String token = loginUser(to);//登录用户
        return token;
    }

    @Override
    public void handleAuthCode(String sid, String code) throws SerException {
        AuthCodeSession.put(sid, code);
    }

    /**
     * 注册成功并登录用户
     *
     * @param to
     * @throws SerException
     */
    private String loginUser(RegisterTO to) throws SerException {
        LoginTO loginTO = new LoginTO();
        loginTO.setLoginType(to.getLoginType());
        loginTO.setAccount(to.getPhone());
        loginTO.setPassword(to.getPassword());
        loginTO.setIp(to.getIp());
        return loginSer.login(loginTO);
    }

    /**
     * 验证邀请码并获得推荐详情信息
     *
     * @param to
     * @return
     * @throws SerException
     */
    private Recommend initRecommend(RegisterTO to) throws SerException {
        String inviteCode = to.getInviteCode();
        if (StringUtils.isNotBlank(inviteCode)) { //邀请码注册
            Recommend recommend = recommendSer.findByInviteCode(inviteCode);
            if (null != recommend) {
                return recommend; //获取推荐详细信息
            }
            throw new SerException("无效邀请码!");
        }
        return null;
    }

    /**
     * 注册验证
     *
     * @param to
     * @throws SerException
     */
    private void validRegister(RegisterTO to) throws SerException {
        if (!Validator.isPhone(to.getPhone())) {
            throw new SerException("手机号码格式错误!");
        } else if (false && !to.getAuthCode().equalsIgnoreCase(AuthCodeSession.get(to.getSid()))) {//验证码
            throw new SerException("验证码错误!");
        } else if (!to.getPassword().equals(to.getRePassword())) { //密码
            throw new SerException("密码不一致!");
        } else if (!Validator.isPassword(to.getPassword())) { //密码强度
            throw new SerException("密码过于简单！");
        }
    }

    /**
     * 注册完成添加好友
     *
     * @param recommend
     */
    private void addFriend(Recommend recommend, User user) throws SerException {
        Friend friend = new Friend();
        friend.setUser(recommend.getUser());
        friend.setFriend(user);
        friend.setApplyType(ApplyType.PASS);
        friendSer.save(friend);
    }

    /**
     * 注册完成,完善用户信息
     *
     * @param user
     * @param recommend
     */
    private void initUserInfo(User user, Recommend recommend) throws SerException {
        UserInfo userInfo = new UserInfo();
        if (null != recommend) {
            BeanUtils.copyProperties(recommend, userInfo, "user"); //从推荐信息表获得用户详情
        }
        userInfo.setUser(user);
        userInfoSer.save(userInfo);//初始化保存用户详情信息
    }

}
