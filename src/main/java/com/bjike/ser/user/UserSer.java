package com.bjike.ser.user;

import com.bjike.common.exception.SerException;
import com.bjike.dto.user.UserDTO;
import com.bjike.entity.user.User;
import com.bjike.ser.Ser;
import com.bjike.to.user.UserInfoTO;
import com.bjike.to.user.VIPApplyTO;
import com.bjike.vo.user.UserInfoVO;

import java.util.List;

/**
 * 用户业务
 *
 * @Author: [liguiqin]
 * @Date: [2017-07-06 14:11]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface UserSer extends Ser<User, UserDTO> {
    /**
     * 是否已登录
     *
     * @param token
     * @return
     * @throws SerException
     */
    default Boolean isLogin(String token) throws SerException {
        return null;
    }

    /**
     * 通过用户名查询用户
     *
     * @param username
     * @return
     * @throws SerException
     */
    default User findByUsername(String username) throws SerException {
        return null;
    }

    /**
     * 通过手机号查询用户
     *
     * @param phone
     * @return
     * @throws SerException
     */
    default User findByPhone(String phone) throws SerException {
        return null;
    }

    /**
     * 通过邮件获取id查询
     *
     * @param cond 条件
     * @return
     * @throws SerException
     */
    default User findByIdOrMail(String cond) throws SerException {
        return null;
    }
    /**
     * 通过邮件获取id查询
     *
     * @param mails 条件 ,空查询全部
     * @return
     * @throws SerException
     */
    default String[] findIdByMail(String[] mails) throws SerException {
        return null;
    }
    /**
     * 通过邮件获取id查询
     *
     * @param mails 条件,空查询全部
     * @return
     * @throws SerException
     */
    default String[] findMailById(String[] mails) throws SerException {
        return null;
    }


    /**
     * 获取用户详情
     *
     * @param userId
     * @return
     * @throws SerException
     */
    default UserInfoVO userInfo(String userId) throws SerException {
        return null;
    }

    /**
     *
     * @param userId 用户id
     * @param to 用户信息
     * @return
     * @throws SerException
     */
    default Boolean editInfo(String userId, UserInfoTO to) throws SerException {
        return null;
    }


    /**
     * 手机号,昵称,用户编号找人
     *
     * @param account
     * @return
     * @throws SerException
     */
    default List<User> findByAccount(String account) throws SerException {
        return null;
    }

    /**
     * 上传头像
     *
     * @param path
     * @throws SerException
     */
    default void uploadHeadPath(String path) throws SerException {

    }

    /**
     * vip申请
     *
     * @param to
     * @throws SerException
     */
    default Boolean vipApply(VIPApplyTO to) throws SerException {
        return null;

    }

}
