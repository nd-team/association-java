package com.bjike.ser.chat;

import com.bjike.common.exception.SerException;
import com.bjike.common.util.UserUtil;
import com.bjike.common.util.bean.BeanCopy;
import com.bjike.dto.chat.GroupDTO;
import com.bjike.entity.chat.Group;
import com.bjike.entity.chat.GroupMember;
import com.bjike.ser.ServiceImpl;
import com.bjike.to.chat.GroupTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-07-20 10:55]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class GroupSerIMpl extends ServiceImpl<Group, GroupDTO> implements GroupSer {
    @Override
    public void add(GroupTO to) throws SerException {
        Group group = BeanCopy.copyProperties(to, Group.class);
        group.setUser(UserUtil.currentUser(false));
        super.save(group);
    }

    @Override
    public void edit(GroupTO to) throws SerException {
        Group group = super.findById(to.getId());
        if (null != group) {
            BeanCopy.copyProperties(to, group);
            super.update(group);
        }
    }

    @Override
    public List<Group> listByUser(String userId) throws SerException {
        return null;
    }

    @Override
    public void remove(String id) throws SerException {
        super.executeSql("delete from "+getTableName(GroupMember.class)+" where group_id='"+id+"'");
        super.remove(id);
    }
}
