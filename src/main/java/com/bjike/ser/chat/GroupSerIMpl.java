package com.bjike.ser.chat;

import com.bjike.common.exception.SerException;
import com.bjike.common.util.UserUtil;
import com.bjike.common.util.bean.BeanCopy;
import com.bjike.common.util.file.FileUtil;
import com.bjike.dto.chat.GroupDTO;
import com.bjike.entity.chat.Group;
import com.bjike.entity.chat.GroupMember;
import com.bjike.ser.ServiceImpl;
import com.bjike.to.chat.GroupTO;
import com.bjike.vo.chat.GroupVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
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

    @Transactional
    @Override
    public void uploadHeadPath(String path, Group group) throws SerException {
        group.setHeadPath(path);
        super.update(group);
    }

    @Override
    public List<GroupVO> listByUser(String userId) throws SerException {
        String sql = " select a.id,a.create_time as createTime,a.description,a.head_path as headPath " +
                " ,a.name ,if(a.user_id='%s','true','false') as own " +
                " from chat_group a,(" +
                "select group_id as groupId from chat_group_member   where user_id='%s'" +
                " union" +
                " select id as groupId  from chat_group where user_id='%s') b" +
                " where a.id = b.groupId";
        sql = String.format(sql, userId, userId, userId);
        String[] fields = new String[]{"id", "createTime", "description", "headPath", "name", "own"};
        return super.findBySql(sql, GroupVO.class, fields);
    }

    @Override
    public void remove(String id) throws SerException {
        super.executeSql("delete from " + getTableName(GroupMember.class) + " where group_id='" + id + "'");
        Group group = super.findById(id);
        if (StringUtils.isNotBlank(group.getHeadPath())) {
            File file = new File(FileUtil.getRealPath(group.getHeadPath()));
            if (file.exists()) {
                file.delete();
            }
        }
        super.remove(group);
    }
}
