package com.bjike.ser.chat;

import com.bjike.common.exception.SerException;
import com.bjike.dto.chat.GroupDTO;
import com.bjike.entity.chat.Group;
import com.bjike.ser.Ser;
import com.bjike.to.chat.GroupTO;
import com.bjike.vo.chat.FriendVO;
import com.bjike.vo.chat.GroupVO;

import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-07-20 10:55]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface GroupSer extends Ser<Group, GroupDTO> {

    /**
     * 创建群
     * @param to
     * @throws SerException
     */
    default void add(GroupTO to) throws SerException {

    }

    /**
     * 编辑群
     * @param to
     * @throws SerException
     */
    default void edit(GroupTO to) throws SerException {

    }

    /**
     * 查询某用户所有群
     * @param userId
     * @return
     * @throws SerException
     */
    default List<GroupVO> listByUser(String userId) throws SerException {
        return null;
    }


}
