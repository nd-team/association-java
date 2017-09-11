package com.bjike.ser.CharitableActivities;

import com.bjike.common.exception.SerException;
import com.bjike.common.util.UserUtil;
import com.bjike.common.util.bean.BeanCopy;
import com.bjike.common.util.date.DateUtil;
import com.bjike.common.util.regex.Validator;
import com.bjike.dto.CharitableActivities.*;
import com.bjike.dto.Restrict;
import com.bjike.dto.chat.GroupDTO;
import com.bjike.entity.CharitableActivities.*;
import com.bjike.entity.chat.Group;
import com.bjike.entity.user.User;
import com.bjike.ser.ServiceImpl;
import com.bjike.ser.chat.GroupSer;
import com.bjike.ser.user.UserSer;
import com.bjike.to.CharitableActivities.InitiateActivitiesTO;
import com.bjike.vo.CharitableActivities.InitiateActivitiesVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: [dengjunren]
 * @Date: [2017-08-31 08:49]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class InitiateActivitiesSerImpl extends ServiceImpl<InitiateActivities, InitiateActivitiesDTO> implements InitiateActivitiesSer {

    @Autowired
    private CommentsSer commentSer;
    @Autowired
    private PointSer pointSer;
    @Autowired
    private ForWardSer forWardSer;
    @Autowired
    private NewsSer newsSer;
    @Autowired
    private PicturesSer picturesSer;
    @Autowired
    private UserSer userSer;
    @Autowired
    private RegistrationSer registrationSer;
    @Autowired
    private GroupSer groupSer;

    @Override
    public List<InitiateActivitiesVO> list(InitiateActivitiesDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<InitiateActivitiesVO> initiateActivitiesVOs = new ArrayList<>(0);
        List<InitiateActivities> initiateActivitiesList = super.findByPage(dto);
        if (null != initiateActivitiesList && initiateActivitiesList.size() > 0) {
            for (InitiateActivities initiateActivities : initiateActivitiesList) {
//                //判断该活动是否已结束
//                String now = DateUtil.dateToString(LocalDateTime.now());
//                String endTime = DateUtil.dateToString(initiateActivities.getActivityEndTime());
//                if (!isOrder(endTime, now)) {
//                    initiateActivities.setStatus(false);
//                }
                InitiateActivitiesVO vo = transForm(initiateActivities);
                initiateActivitiesVOs.add(vo);
            }
        }

        return initiateActivitiesVOs;
    }

    @Override
    public void add(InitiateActivitiesTO to, List<File> files) throws SerException {
        validatorNumLimit(to);
        try {
            if (!Validator.isPhone(to.getPhone())) {
                throw new SerException("手机号码格式不正确");
            }
            User user = UserUtil.currentUser();
            InitiateActivities entity = BeanCopy.copyProperties(to, InitiateActivities.class, true, "sponsor");
            entity.setSponsor(user.getId());
            //活动状态－＞进行中
            entity.setStatus(true);
            super.save(entity);
            uploadImg(entity.getId(), files);

            //创建群聊
            Group group = new Group();
            group.setUserId(user.getId());
            group.setName(to.getActivitySubject());
            group.setHeadPath(user.getHeadPath());
            groupSer.save(group);

        } catch (SerException e) {
            for (File file : files) {
                file.delete();
            }
            throw new SerException(e.getMessage());
        }
    }

    @Override
    public void edit(InitiateActivitiesTO to) throws SerException {
        validatorNumLimit(to);
        if (!Validator.isPhone(to.getPhone())) {
            throw new SerException("手机号码格式不正确");
        }
        if (StringUtils.isBlank(to.getId())) {
            throw new SerException("id不能为空");
        }
        InitiateActivities temp = super.findById(to.getId());
        if (null == temp) {
            throw new SerException("目标对象为空");
        }
        User user = UserUtil.currentUser();
        if (!user.getUsername().equals(temp.getSponsor())) {
            throw new SerException("您不是发起人,不能修改活动内容");
        }

        GroupDTO groupDTO = new GroupDTO();
        groupDTO.getConditions().add(Restrict.eq("name", temp.getActivitySubject()));
        groupDTO.getConditions().add(Restrict.eq("userId", temp.getSponsor()));
        List<Group> groups = groupSer.findByCis(groupDTO);
        if (null != groups && groups.size() > 0) {
            Group group = groups.get(0);
            group.setName(to.getActivitySubject());
            groupSer.update(group);
        }

        BeanCopy.copyProperties(to, temp, true);
        temp.setModifyTime(LocalDateTime.now());
        super.update(temp);
    }

    @Override
    public void delete(String id) throws SerException {
        //删除评论了该公益活动的评论
        CommentsDTO dto = new CommentsDTO();
        dto.getConditions().add(Restrict.eq("InitiateActivitiesID", id));
        List<Comments> comments = commentSer.findByCis(dto);
        if (null != comments && comments.size() > 0) {
            for (Comments comment : comments) {
                //删除点赞了该评论的数据
                PointDTO pointDTO = new PointDTO();
                pointDTO.getConditions().add(Restrict.eq("commentID", comment.getId()));
                List<Point> points = pointSer.findByCis(pointDTO);
                if (null != points && points.size() > 0) {
                    pointSer.remove(points);
                }
                //删除评论所对应的消息
                NewsDTO newsDTO = new NewsDTO();
                newsDTO.getConditions().add(Restrict.eq("objID", comment.getId()));
                List<News> newsList = newsSer.findByCis(newsDTO);
                if (null != newsList && newsList.size() > 0) {
                    newsSer.remove(newsList);
                }
                commentSer.remove(comment);
            }
        }
        //删除公益活动所对应的消息
        NewsDTO newsDTO = new NewsDTO();
        newsDTO.getConditions().add(Restrict.eq("objID", id));
        List<News> newsList = newsSer.findByCis(newsDTO);
        if (null != newsList && newsList.size() > 0) {
            newsSer.remove(newsList);
        }
        super.remove(id);
    }

    @Override
    public void like(String initiateactivitiesID) throws SerException {
        User user = UserUtil.currentUser();
        InitiateActivities entity = super.findById(initiateactivitiesID);
        if (null == entity) {
            throw new SerException("该公益活动不存在或已被删除");
        }
        PointDTO pointDTO = new PointDTO();
        pointDTO.getConditions().add(Restrict.eq("praiseID", user.getId()));
        pointDTO.getConditions().add(Restrict.eq("initiateactivitiesID", initiateactivitiesID));
        List<Point> points = pointSer.findByCis(pointDTO);
        if (null != points && points.size() > 0) {
            //该用户对该条评论已点赞，现取消点赞
            entity.setPraiseNumber(entity.getPraiseNumber() != null ? entity.getPraiseNumber() - 1 : 0);
            entity.setModifyTime(LocalDateTime.now());
            super.update(entity);
            pointSer.remove(points);
        } else {
            entity.setPraiseNumber(entity.getPraiseNumber() != null ? entity.getPraiseNumber() + 1 : 1);
            entity.setModifyTime(LocalDateTime.now());
            super.update(entity);
            Point point = new Point();
            point.setPraiseID(user.getId());
            point.setInitiateactivitiesID(initiateactivitiesID);
            pointSer.save(point);
        }
    }

    @Transactional
    @Override
    public void forward(String initiateactivitiesID) throws SerException {
        User user = UserUtil.currentUser();
        InitiateActivities entity = super.findById(initiateactivitiesID);
        if (null == entity) {
            throw new SerException("该公益活动不存在或已删除");
        }
        entity.setForwardingNumber(entity.getForwardingNumber() != null ? entity.getForwardingNumber() + 1 : 1);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        ForWard forWard = new ForWard();
        forWard.setForwarders(user.getId());
        forWard.setInitiateactivitiesID(initiateactivitiesID);
        forWardSer.save(forWard);
    }

    @Transactional
    @Override
    public void uploadImg(String initiateactivitiesID, List<File> files) throws SerException {
        InitiateActivities entity = super.findById(initiateactivitiesID);
        List<Pictures> pictures = new ArrayList<>(files.size());
        for (File file : files) {
            String path = StringUtils.substringAfter(file.getPath(), "/root/storage/");
            Pictures picture = new Pictures();
            picture.setCommentID(initiateactivitiesID);
            picture.setPath(path);
            pictures.add(picture);
        }
        picturesSer.save(pictures);
    }

    @Override
    public InitiateActivitiesVO find(String id) throws SerException {
        InitiateActivities entity = super.findById(id);
        InitiateActivitiesVO initiateActivitiesVO = new InitiateActivitiesVO();
        if (null != entity) {
            initiateActivitiesVO = transForm(entity);
        }
        return initiateActivitiesVO;
    }

    @Override
    public List<InitiateActivitiesVO> partake() throws SerException {
        User user = UserUtil.currentUser();
        InitiateActivitiesDTO dto = new InitiateActivitiesDTO();
        dto.getConditions().add(Restrict.eq("sponsor", user.getId()));
        List<InitiateActivities> initiateActivitiesList = super.findByCis(dto);
        Set<String> ids = new HashSet<>(0);
        if (null != initiateActivitiesList && initiateActivitiesList.size() > 0) {
            List<String> initiateactivitiesIDs = initiateActivitiesList.stream().map(InitiateActivities::getId).distinct().collect(Collectors.toList());
            ids.addAll(initiateactivitiesIDs);
        }

        RegistrationDTO registrationDTO = new RegistrationDTO();
        registrationDTO.getConditions().add(Restrict.eq("signUpId", user.getId()));
        List<Registration> registrationList = registrationSer.findByCis(registrationDTO);
        if (null != registrationList && registrationList.size() > 0) {
            List<String> initiateactivitiesIds = registrationList.stream().map(Registration::getInitiateactivitiesID).distinct().collect(Collectors.toList());
            ids.addAll(initiateactivitiesIds);
        }

        List<InitiateActivitiesVO> vos = new ArrayList<>(0);
        if (null != ids && ids.size() > 0) {
            for (String id : ids) {
                InitiateActivities entity = super.findById(id);
                InitiateActivitiesVO initiateActivitiesVO = transForm(entity);
                vos.add(initiateActivitiesVO);
            }
        }
        return vos;
    }

    @Override
    public Boolean isPoint(String initiateactivitiesID) throws SerException {
        Boolean tar = false;
        User user = UserUtil.currentUser();
        PointDTO pointDTO = new PointDTO();
        pointDTO.getConditions().add(Restrict.eq("initiateactivitiesID", initiateactivitiesID));
        pointDTO.getConditions().add(Restrict.eq("praiseID", user.getId()));
        Point point = pointSer.findOne(pointDTO);
        if (null != point) {
            tar = true;
        }
        return tar;
    }

    @Override
    public List<String> getInitiateActivitieses() throws SerException {
        User user = UserUtil.currentUser();
        PointDTO pointDTO = new PointDTO();
        pointDTO.getConditions().add(Restrict.eq("praiseID", user.getId()));
        List<Point> points = pointSer.findByCis(pointDTO);
        if (null != points && points.size() > 0) {
            List<String> ids = points.stream().filter(point -> StringUtils.isNotBlank(point.getInitiateactivitiesID())).map(Point::getInitiateactivitiesID).distinct().collect(Collectors.toList());
            return ids;
        }
        return null;
    }

    private InitiateActivitiesVO transForm(InitiateActivities entity) throws SerException {
        //判断该活动是否已结束
        String now = DateUtil.dateToString(LocalDateTime.now());
        String endTime = DateUtil.dateToString(entity.getActivityEndTime());
        if (!isOrder(endTime, now)) {
            entity.setStatus(false);
        }
//        UserInfoVO userInfoVO = userSer.userInfo(entity.getSponsor());
        User userInfoVO = userSer.findById(entity.getSponsor());
        InitiateActivitiesVO initiateActivitiesVO = BeanCopy.copyProperties(entity, InitiateActivitiesVO.class, false, "name");
        initiateActivitiesVO.setName(userInfoVO.getNickname());
        initiateActivitiesVO.setSponsorPathHead(userInfoVO.getHeadPath());
        return initiateActivitiesVO;
    }

    public Boolean isOrder(String date1, String date2) throws SerException {
        Boolean tar = false;
        try {
            //a1活动截止时间，b1,当前时间
            Date a1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(date1);
            Date b1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(date2);
            int result = a1.compareTo(b1);
            if (result <= 0) {
                //活动进行中
                tar = true;
            }
            return tar;
        } catch (Exception e) {
            throw new SerException(e.getMessage());
        }
    }

    //人数限制校验
    private void validatorNumLimit(InitiateActivitiesTO to) throws SerException{
        if(-1 == to.getNumberLimit().indexOf("人") && !"无限制".equals(to.getNumberLimit())){
            throw new SerException("人数限制应为数字＋人，如：300人或者无限制 ");
        }
        if(-1 != to.getNumberLimit().indexOf("人")){
            try {
                String numStr = to.getNumberLimit().substring(0,to.getNumberLimit().indexOf("人"));
                Long num = Long.valueOf(numStr);
            }catch (Exception e){
                throw new SerException("人数限制应为数字＋人");
            }
        }
    }
}
