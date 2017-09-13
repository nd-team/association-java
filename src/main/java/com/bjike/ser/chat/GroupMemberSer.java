package com.bjike.ser.chat;

import com.bjike.common.exception.SerException;
import com.bjike.dto.BaseDTO;
import com.bjike.dto.chat.GroupMemberDTO;
import com.bjike.entity.chat.GroupMember;
import com.bjike.ser.Ser;

/**
 * @Author: [liguiqin]
 * @Date: [2017-07-20 10:08]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface GroupMemberSer extends Ser<GroupMember, GroupMemberDTO> {
    /**
     * 添加群成员
     * @param groupId 群id
     * @param userId 新增群员id
     * @throws SerException
     */
    default void add(String groupId ,String[] userId) throws SerException {

    }

    /**
     * 删除群成员
     * @param groupId 群id
     * @param userId 新增群员id
     * @throws SerException
     */
    default void del(String groupId ,String[] userId) throws SerException {

    }
}
