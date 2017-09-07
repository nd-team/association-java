package com.bjike.ser.user;

import com.bjike.common.exception.SerException;
import com.bjike.dto.user.UserInfoDTO;
import com.bjike.entity.user.UserInfo;
import com.bjike.ser.Ser;

/**
 * 用户详细信息业务
 *
 * @Author: [liguiqin]
 * @Date: [2017-08-23 09:49]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface UserInfoSer extends Ser<UserInfo, UserInfoDTO> {
    /**
     * 是否已登录
     *
     * @param userId
     * @return
     * @throws SerException
     */
    default UserInfo findByUserId(String userId) throws SerException {
        return null;
    }

    /**
     * 添加经验值
     * @param experience 经验值
     * @return
     * @throws SerException
     */
    default Boolean addExperience(double experience) throws SerException {
        return null;
    }

}
