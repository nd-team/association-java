package com.bjike.ser.shake;

import com.bjike.common.exception.SerException;
import com.bjike.entity.user.User;

/**
 * @Author: [liguiqin]
 * @Date: [2017-07-06 13:53]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface ShakeSer {
    /**
     * 摇一摇
     *
     * @param longitude
     * @param latitude
     * @return
     * @throws SerException
     */
    default User shake( String longitude, String latitude) throws SerException {
        return null;
    }
}
