package com.bjike.ser.CharitableActivities;

import com.bjike.common.exception.SerException;
import com.bjike.dto.CharitableActivities.RegistrationDTO;
import com.bjike.entity.CharitableActivities.Registration;
import com.bjike.ser.Ser;
import com.bjike.to.CharitableActivities.RegistrationTO;
import com.bjike.vo.CharitableActivities.RegistrationVO;

import java.util.List;

/**
 * @Author: [dengjunren]
 * @Date: [2017-08-31 11:37]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface RegistrationSer extends Ser<Registration, RegistrationDTO> {

    /**
     * 活动报名
     *
     * @param to
     * @throws SerException
     */
    default void add(RegistrationTO to) throws SerException {
        return;
    }

    /**
     * 报名信息修改
     *
     * @param to
     * @throws SerException
     */
    default void edit(RegistrationTO to) throws SerException {
        return;
    }

    /**
     * 是否已报名
     *
     * @return
     * @throws SerException
     */
    default Boolean isApply(String id) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default List<RegistrationVO> list(RegistrationDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据id获取对象
     *
     * @param id
     * @return
     * @throws SerException
     */
    default RegistrationVO find(String id) throws SerException {
        return null;
    }
}
