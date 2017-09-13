package com.bjike.ser.user;

import com.bjike.common.exception.SerException;
import com.bjike.common.util.bean.BeanCopy;
import com.bjike.common.util.date.DateUtil;
import com.bjike.dto.Restrict;
import com.bjike.dto.user.LoginLogDTO;
import com.bjike.entity.user.LoginLog;
import com.bjike.ser.ServiceImpl;
import com.bjike.to.user.LoginLogTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-08-22 14:03]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class LoginLogSerImpl extends ServiceImpl<LoginLog, LoginLogDTO> implements LoginLogSer {
    @Autowired
    private UserSer userSer;

    /**
     * 每个用户仅保存最近的五条登录记录
     *
     * @param loginLogTO
     * @return
     * @throws SerException
     */
    @Transactional
    @Override
    public void saveLoginLog(LoginLogTO loginLogTO) throws SerException {
        LoginLogDTO dto = new LoginLogDTO();
        dto.getConditions().add(Restrict.eq("user.id", loginLogTO.getUser().getId()));
        dto.getSorts().add("loginTime=ASC");
        List<LoginLog> loginLogs = findByCis(dto);
        if (null != loginLogs && loginLogs.size() >= 10) {
            LoginLog old_log = loginLogs.get(0); //更新最旧的数据为最新的
            old_log.setLoginTime(DateUtil.parseDateTime(loginLogTO.getLoginTime()));
            old_log.setLoginIp(loginLogTO.getLoginIp());
            old_log.setLoginType(loginLogTO.getLoginType());
            old_log.setLoginAddress(loginLogTO.getLoginAddress());
            super.update(old_log);
        } else {
            LoginLog loginLog = BeanCopy.copyProperties(loginLogTO, LoginLog.class, true);
            loginLog.setUser(userSer.findById(loginLog.getUser().getId()));
            super.save(loginLog);
        }
    }

    @Transactional
    @Override
    public void save(Collection<LoginLog> loginLogs) throws SerException {
        for (LoginLog log : loginLogs) { //批量添加
            this.save(log);
        }
    }

    @Override
    public List<LoginLog> findByUserId(String userId) throws SerException {
        LoginLogDTO dto = new LoginLogDTO();
        dto.getConditions().add(Restrict.eq("user.id", userId));
        dto.getSorts().add("loginTime=DESC");
        return super.findByCis(dto);

    }


}
