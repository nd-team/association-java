package com.bjike.common.util;

import com.bjike.common.exception.SerException;
import org.apache.commons.lang3.StringUtils;

/**
 * 序列工具
 *
 * @Author: [liguiqin]
 * @Date: [2017-05-18 10:19]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class SeqUtil {

    private static final String EMP_NUMBER = "IKE"; // 非内部员工编号格式
    private static final String EMP_ZERO = "100000"; // 员工编号开始与位数

    /**
     * 生成下一个编号
     *
     * @param employeeNumber 最大员工编号
     */
    public static synchronized String genNumber(String employeeNumber) throws SerException {
        if (StringUtils.isNotBlank(employeeNumber)) {
            int empLength = EMP_NUMBER.length() + EMP_ZERO.length();
            Integer number = Integer.parseInt(StringUtils.substringAfter(employeeNumber, EMP_NUMBER)) + 1;
            Integer length = empLength - (String.valueOf(number).length());
            if (length > 0) {
                employeeNumber = EMP_NUMBER + EMP_ZERO.substring(0, length - EMP_NUMBER.length());
            } else if (0 == length) {
                employeeNumber = EMP_NUMBER + number;
            } else {
                throw new SerException("员工编号超出长度:" + length);
            }
            return employeeNumber + number;
        } else {
            return genNumber(EMP_NUMBER + EMP_ZERO); //假如为空,则从第一个开始IKE000001
        }

    }

}
