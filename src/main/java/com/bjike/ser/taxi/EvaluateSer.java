package com.bjike.ser.taxi;

import com.bjike.common.exception.SerException;
import com.bjike.dto.taxi.EvaluateDTO;
import com.bjike.entity.taxi.Evaluate;
import com.bjike.ser.Ser;
import com.bjike.to.taxi.EvaluateTO;

import java.io.File;
import java.util.List;

/**
 * 订单评价业务操作接口
 *
 * @Author: [ liguiqin ]
 * @Date: [  2017-09-06 14:50:50 ]
 * @Description: [ 订单评价 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [com.bjike]
 */
public interface EvaluateSer extends Ser<Evaluate, EvaluateDTO> {
    /**
     * 司机对用户评价
     *
     * @param to 评价信息
     * @return
     * @throws SerException
     */
    default Boolean add(EvaluateTO to, List<File>files) throws SerException {
        return null;

    }

    /**
     *
     * @param orderId 订单id
     * @param content 未完成原因
     * @return
     * @throws SerException
     */
    default Boolean unfinished(String orderId, String content) throws SerException {
        return null;

    }
}
