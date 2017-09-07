package com.bjike.ser.user;

import com.alibaba.fastjson.JSON;
import com.bjike.common.constant.UserCommon;
import com.bjike.common.exception.SerException;
import com.bjike.common.util.UserUtil;
import com.bjike.dto.Restrict;
import com.bjike.dto.user.UserInfoDTO;
import com.bjike.entity.user.Exp;
import com.bjike.entity.user.UserInfo;
import com.bjike.redis.client.RedisClient;
import com.bjike.ser.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

/**
 * @Author: [liguiqin]
 * @Date: [2017-08-23 09:49]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class UserInfoSerImpl extends ServiceImpl<UserInfo, UserInfoDTO> implements UserInfoSer {
    private static  final  double MAX_EXP =15;
    @Autowired
    private RedisClient redis;

    @Override
    public UserInfo findByUserId(String userId) throws SerException {
        UserInfoDTO dto = new UserInfoDTO();
        dto.getConditions().add(Restrict.eq("user.id", userId));
        UserInfo userInfo = super.findOne(dto);
        if (null != userInfo) {
            return userInfo;
        } else {
            throw new SerException("获取不到该用户详细信息!");
        }
    }

    @Transactional
    @Override
    public Boolean addExperience(double experience) throws SerException {
        String userId = UserUtil.currentUserID();
        String now = LocalDate.now().toString();
        UserInfo info = findByUserId(userId);
        String str_exp = redis.getMap(UserCommon.EXPERIENCE, userId);
        Exp exp = null;
        if (StringUtils.isNotBlank(str_exp)) {
            exp = JSON.parseObject(str_exp, Exp.class);
            if (exp.getToday().equals(now)) {
                if (exp.getExp() <= MAX_EXP) { //今天还没有到15
                    double new_exp = (exp.getExp() + experience) >= MAX_EXP ? MAX_EXP : (exp.getExp() + experience); //今天已满15
                    info.setExperience(new_exp + info.getExperience());
                    update(info);
                    exp.setExp(new_exp);
                    redis.appendToMap(UserCommon.EXPERIENCE, userId, JSON.toJSONString(exp), UserCommon.EXP_TIMEOUT);
                }
            } else { //非今天,添加新的
                expToRedis(userId);
            }
        } else { //不存在,添加新的
            expToRedis(userId);
        }
        return true;
    }

    private void expToRedis(String userId) throws SerException {
        Exp exp = new Exp();
        exp.setExp(0);
        exp.setToday(LocalDate.now().toString());
        redis.appendToMap(UserCommon.EXPERIENCE, userId, JSON.toJSONString(exp), UserCommon.EXP_TIMEOUT);
    }

}
