package com.bjike.ser.chat;

import com.bjike.common.exception.SerException;
import com.bjike.common.util.UserUtil;
import com.bjike.dto.Restrict;
import com.bjike.dto.chat.GroupMemberDTO;
import com.bjike.entity.chat.Group;
import com.bjike.entity.chat.GroupMember;
import com.bjike.ser.ServiceImpl;
import com.bjike.ser.user.UserSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-07-20 10:09]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class GroupMemberSerImpl extends ServiceImpl<GroupMember, GroupMemberDTO> implements GroupMemberSer {
    @Autowired
    private GroupSer groupSer;
    @Autowired
    private UserSer userSer;

    @Transactional
    @Override
    public void add(String groupId, String[] userId) throws SerException {
        Group group = groupSer.findById(groupId);
        if (null != group) {
            if (group.getUser().getId().equals(UserUtil.currentUserID())) {
                List<GroupMember> members = new ArrayList<>();
                for (String id : userId) {
                    GroupMember member = new GroupMember();
                    member.setGroup(group);
                    member.setUser(userSer.findById(id));
                    members.add(member);
                }
                super.save(members);
            } else {
                throw new SerException("您不是群主,没有权限添加");
            }
        } else {
            throw new SerException("该群不存在");
        }


    }

    @Transactional
    @Override
    public void del(String groupId, String[] userId) throws SerException {
        Group group = groupSer.findById(groupId);
        if (null != group) {
            if (group.getUser().getId().equals(UserUtil.currentUserID())) {
                GroupMemberDTO dto = new GroupMemberDTO();
                dto.getConditions().add(Restrict.eq("group.id", groupId));
                dto.getConditions().add(Restrict.in("user.id", userId));
                List<GroupMember> members = super.findByCis(dto);
                if (null != members && members.size() > 0) {
                    super.remove(members);
                } else {
                    throw new SerException("找不到该成员");
                }
            } else {
                throw new SerException("您不是群主,没有权限删除");
            }
        } else {
            throw new SerException("该群不存在");
        }
    }


}
