package com.bjike.ser.CharitableActivities;

import com.bjike.common.exception.SerException;
import com.bjike.common.util.UserUtil;
import com.bjike.common.util.bean.BeanCopy;
import com.bjike.common.util.date.DateUtil;
import com.bjike.common.util.regex.Validator;
import com.bjike.dto.CharitableActivities.RegistrationDTO;
import com.bjike.dto.Restrict;
import com.bjike.dto.chat.GroupDTO;
import com.bjike.entity.CharitableActivities.InitiateActivities;
import com.bjike.entity.CharitableActivities.Registration;
import com.bjike.entity.chat.Group;
import com.bjike.entity.chat.GroupMember;
import com.bjike.entity.user.User;
import com.bjike.ser.ServiceImpl;
import com.bjike.ser.chat.GroupMemberSer;
import com.bjike.ser.chat.GroupSer;
import com.bjike.ser.user.UserSer;
import com.bjike.to.CharitableActivities.RegistrationTO;
import com.bjike.vo.CharitableActivities.RegistrationVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: [dengjunren]
 * @Date: [2017-08-31 11:39]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class RegistrationSerImpl extends ServiceImpl<Registration, RegistrationDTO> implements RegistrationSer {

    @Autowired
    private InitiateActivitiesSer initiateActivitiesSer;
    @Autowired
    private UserSer userSer;
    @Autowired
    private GroupSer groupSer;
    @Autowired
    private GroupMemberSer groupMemberSer;

    @Override
    public void add(RegistrationTO to) throws SerException {
        if (!Validator.isPhone(to.getTellphone())) {
            throw new SerException("手机号码格式不正确");
        }
        if (StringUtils.isBlank(to.getInitiateactivitiesID())) {
            throw new SerException("公益活动id不能为空");
        }
        InitiateActivities initiateActivities = initiateActivitiesSer.findById(to.getInitiateactivitiesID());
        if (null == initiateActivities) {
            throw new SerException("目标数据对象为空");
        }
        String deadline = DateUtil.dateToString(initiateActivities.getDeadline());
        String now = DateUtil.dateToString(LocalDateTime.now());
        if (!isOrder(deadline, now)) {
            throw new SerException("报名截止时间已过，当前不能报名");
        }
        //当前报名人数是否超限制
        if (!initiateActivities.getNumberLimit().equals("无限制") && StringUtils.isNotBlank(initiateActivities.getNumberLimit())) {
            String limit = initiateActivities.getNumberLimit().substring(0, initiateActivities.getNumberLimit().indexOf("人"));
            Long numberLimit = Long.valueOf(limit);
            if (initiateActivities.getNum() >= numberLimit) {
                throw new SerException("当前报名人数已达上限，无法报名");
            }
        }

        // TODO: 17-9-7 报名费

        User user = UserUtil.currentUser();
        to.setSignUpId(user.getId());
        to.setStatas(true);
        Registration entity = BeanCopy.copyProperties(to, Registration.class);
        super.save(entity);
        //公益活动报名数加1
        initiateActivities.setNum(initiateActivities.getNum() != null ? initiateActivities.getNum() + 1 : 1);
        initiateActivitiesSer.save(initiateActivities);

        //加入群聊
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.getConditions().add(Restrict.eq("name", initiateActivities.getActivitySubject()));
        groupDTO.getConditions().add(Restrict.eq("userId", initiateActivities.getSponsor()));
        List<Group> groups = groupSer.findByCis(groupDTO);
        if (null != groups && groups.size() > 0) {
            Group group = groups.get(0);
            GroupMember groupMember = new GroupMember();
 //todo 修改liguiqin           groupMember.setUserId(user.getId());
            groupMember.setGroup(group);
            groupMemberSer.save(groupMember);
        }
    }

    @Override
    public void edit(RegistrationTO to) throws SerException {
        if (StringUtils.isBlank(to.getId())) {
            throw new SerException("id不能为空");
        }
        Registration temp = super.findById(to.getId());
        if (null == temp) {
            throw new SerException("目标不对象不能为空");
        }
        User user = UserUtil.currentUser();
        if (!user.getId().equals(temp.getSignUpId())) {
            throw new SerException("不能修改他人的报名数据");
        }
        BeanCopy.copyProperties(to, temp, "signUpId");
        temp.setModifyTime(LocalDateTime.now());
        super.update(temp);
    }

    @Override
    public Boolean isApply(String id) throws SerException {
        Registration entity = super.findById(id);
        if (null == entity) {
            throw new SerException("目标对象不能为空");
        }
        Boolean tar = entity.getStatas();
        return tar;
    }

    @Override
    public List<RegistrationVO> list(RegistrationDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<Registration> registrations = super.findByPage(dto);
        List<RegistrationVO> registrationVOs = new ArrayList<>(0);
        if (!CollectionUtils.isEmpty(registrations)) {
            for (Registration registration : registrations) {
                RegistrationVO vo = transForm(registration);
                registrationVOs.add(vo);
            }
        }
        return registrationVOs;
    }

    @Override
    public RegistrationVO find(String id) throws SerException {
        Registration entity = super.findById(id);
        if (null == entity) {
            throw new SerException("目标数据对象不存在");
        }
        return transForm(entity);
    }

    public Boolean isOrder(String date1, String date2) throws SerException {
        Boolean tar = false;
        try {
            //a1报名截止时间，b1,当前时间
            Date a1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(date1);
            Date b1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(date2);
            int result = a1.compareTo(b1);
            if (result <= 0) {
                //能报名
                tar = true;
            }
            return tar;
        } catch (Exception e) {
            throw new SerException(e.getMessage());
        }
    }

    private RegistrationVO transForm(Registration entity) throws SerException {
//        UserInfoVO userInfoVO = userSer.userInfo(entity.getSignUpId());
        User userInfoVO = userSer.findById(entity.getSignUpId());
        RegistrationVO registrationVO = BeanCopy.copyProperties(entity, RegistrationVO.class, false, "name");
        registrationVO.setName(userInfoVO.getNickname());
        registrationVO.setSignUpPathHead(userInfoVO.getHeadPath());
        return registrationVO;
    }

}
