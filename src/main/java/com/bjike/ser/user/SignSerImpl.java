package com.bjike.ser.user;

import com.bjike.common.exception.SerException;
import com.bjike.common.util.UserUtil;
import com.bjike.common.util.date.DateUtil;
import com.bjike.dto.Restrict;
import com.bjike.dto.user.SignDTO;
import com.bjike.entity.user.Sign;
import com.bjike.entity.user.User;
import com.bjike.entity.user.UserInfo;
import com.bjike.ser.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-08-29 17:29]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class SignSerImpl extends ServiceImpl<Sign, SignDTO> implements SignSer {
    @Autowired
    private UserSer userSer;
    @Autowired
    private UserInfoSer userInfoSer;

    @Transactional
    @Override
    public Integer sign() throws SerException {
        LocalDate today = LocalDate.now();
        String date = DateUtil.dateToString(today);
        SignDTO dto = new SignDTO();
        String userId = UserUtil.currentUserID();
        dto.getConditions().add(Restrict.eq("signDate", date));
        dto.getConditions().add(Restrict.eq("user.id", userId));
        Sign todaySign = super.findOne(dto);
        if (null != todaySign) {
            throw new SerException("您今天已签到过了!");
        } else {
            LocalDate yesterday = today.minusDays(1);
            dto.getConditions().add(Restrict.eq("signDate", DateUtil.dateToString(yesterday)));
            dto.getConditions().add(Restrict.eq("user.id", userId));
            Sign yesterdaySign = super.findOne(dto);
            int signCount = 0;
            if (null != yesterdaySign) { //昨天已签到
                signCount = yesterdaySign.getSignCount() + 1;
            } else {//昨天没有签到
                signCount = 1;
            }
            User user = userSer.findById(userId);
            Sign sign = new Sign();
            sign.setSignCount(signCount);
            sign.setSignDate(today);
            sign.setUser(user);
            super.save(sign);
            setExperience(userId, signCount);
            return signCount; //返回签到经验
        }
    }

    @Override
    public List<Sign> signList(String startDate, String endDate) throws SerException {
        try {
            DateUtil.parseDate(startDate);
            DateUtil.parseDate(endDate);
        } catch (Exception e) {
            throw new SerException("日期格式不正确!");
        }
        SignDTO dto = new SignDTO();
        String userId = UserUtil.currentUserID();
        dto.getConditions().add(Restrict.between("signDate", new String[]{startDate, endDate}));
        dto.getConditions().add(Restrict.eq("user.id", userId));
        dto.getSorts().add("signDate=asc");
        return super.findByCis(dto);
    }

    /**
     * 更新经验
     *
     * @param userId
     * @throws SerException
     */
    private void setExperience(String userId, int signCount) throws SerException {
        UserInfo userInfo = userInfoSer.findByUserId(userId);
        userInfo.setExperience(userInfo.getExperience()!=null?userInfo.getExperience():0 + signCount);
        userInfoSer.save(userInfo);
    }
}
