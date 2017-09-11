package com.bjike.ser.CharitableActivities;

import com.bjike.common.exception.SerException;
import com.bjike.common.util.UserUtil;
import com.bjike.common.util.bean.BeanCopy;
import com.bjike.dto.CharitableActivities.CommentsDTO;
import com.bjike.dto.CharitableActivities.NewsDTO;
import com.bjike.dto.CharitableActivities.PointDTO;
import com.bjike.dto.Restrict;
import com.bjike.entity.CharitableActivities.*;
import com.bjike.entity.user.User;
import com.bjike.ser.ServiceImpl;
import com.bjike.ser.user.UserSer;
import com.bjike.to.CharitableActivities.CommentsTO;
import com.bjike.vo.CharitableActivities.CommentsVO;
import com.bjike.vo.user.UserInfoVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: [dengjunren]
 * @Date: [2017-08-31 14:54]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class CommentsSerImpl extends ServiceImpl<Comments, CommentsDTO> implements CommentsSer {

    @Autowired
    private PointSer pointSer;
    @Autowired
    private PicturesSer pictureSer;
    @Autowired
    private InitiateActivitiesSer initiateActivitiesSer;
    @Autowired
    private NewsSer newsSer;
    @Autowired
    private UserSer userSer;

    @Override
    public void add(CommentsTO to) throws SerException {
        User user = UserUtil.currentUser();
        to.setCommentatorid(user.getId());
        to.setOtherCommer(false);
        Comments entity = BeanCopy.copyProperties(to, Comments.class);
        InitiateActivities initiateActivities = initiateActivitiesSer.findById(to.getInitiateactivitiesID());
        if (null == initiateActivities) {
            throw new SerException("目标对象为空");
        }
        initiateActivities.setCommentsNumber(initiateActivities.getCommentsNumber() != null ? initiateActivities.getCommentsNumber() + 1 : 1);
        initiateActivities.setModifyTime(LocalDateTime.now());
        initiateActivitiesSer.update(initiateActivities);
        super.save(entity);

        //增加消息
        News news = new News();
        news.setMessageID(initiateActivities.getSponsor());
        news.setRead(false);
        news.setSponsorID(user.getId());
        news.setContent(user.getNickname() + "评论了您的\"" + initiateActivities.getActivitySubject() + "\"公益活动");
        news.setObjID(initiateActivities.getId());
        newsSer.save(news);

    }

    @Override
    public List<CommentsVO> list(CommentsDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<Comments> list = super.findByPage(dto);
        if (null != list && list.size() > 0) {
            List<CommentsVO> commentsVOs = BeanCopy.copyProperties(list, CommentsVO.class, "commentator", "initiateactivities", "commented");
            for (CommentsVO vo : commentsVOs) {
                transForm(vo);
            }
            return commentsVOs;
        }
        return null;
    }

    @Override
    public Long getTotal(String initiateActivitiesID) throws SerException {
        CommentsDTO dto = new CommentsDTO();
        dto.getConditions().add(Restrict.eq("initiateactivitiesID", initiateActivitiesID));
        return super.count(dto);
    }

    @Transactional
    @Override
    public void reply(CommentsTO to) throws SerException {
//        if (StringUtils.isBlank(to.getId())) {
//            throw new SerException("被评论人的id不能为空");
//        }
//        to.setCommentedID(to.getId());

        if (StringUtils.isBlank(to.getId())) {
            throw new SerException("回复的评论id不能为空");
        }
        CommentsDTO commentsDTO = new CommentsDTO();
        commentsDTO.getConditions().add(Restrict.eq("id", to.getId()));
        Comments comments = super.findOne(commentsDTO);
        if (null == comments) {
            throw new SerException("目标对象为空");
        }
        to.setCommentedID(comments.getCommentatorid());
        to.setInitiateactivitiesID(comments.getInitiateactivitiesID());

        User user = UserUtil.currentUser();
        to.setCommentatorid(user.getId());
        to.setOtherCommer(true);
        Comments entity = BeanCopy.copyProperties(to, Comments.class, "id");
        super.save(entity);

        //增加消息
        News news = new News();
        news.setMessageID(entity.getCommentedID());
        news.setRead(false);
        news.setSponsorID(entity.getCommentatorid());
//        UserInfoVO userInfoVO = userSer.userInfo(entity.getCommentatorid());
        User userInfoVO = userSer.findById(entity.getCommentatorid());
        news.setContent(userInfoVO.getNickname() + "回复了您的评论\"" + entity.getComments() + "\"");
        news.setObjID(entity.getId());
        newsSer.save(news);
    }

    @Transactional
    @Override
    public void like(String commentID) throws SerException {
        User user = UserUtil.currentUser();
        Comments entity = super.findById(commentID);
        if (null == entity) {
            throw new SerException("该评论不存在或已被删除");
        }
        PointDTO pointDTO = new PointDTO();
        pointDTO.getConditions().add(Restrict.eq("praiseID", user.getId()));
        pointDTO.getConditions().add(Restrict.eq("commentID", commentID));
        List<Point> points = pointSer.findByCis(pointDTO);
        if (null != points && points.size() > 0) {
            //该用户对该条评论已点赞，现取消点赞
            entity.setApproved(entity.getApproved() != null ? entity.getApproved() - 1 : 0);
            entity.setModifyTime(LocalDateTime.now());

            //取消点赞消息
            NewsDTO newsDTO = new NewsDTO();
            newsDTO.getConditions().add(Restrict.eq("messageID", entity.getCommentatorid()));
            newsDTO.getConditions().add(Restrict.eq("sponsorID", user.getId()));
            newsDTO.getConditions().add(Restrict.eq("objID", entity.getId()));
            newsDTO.getConditions().add(Restrict.eq("content", user.getNickname() + "点赞了您的评论\"" + entity.getComments() + "\""));
            News news = newsSer.findOne(newsDTO);
            if (news != null) {
                newsSer.remove(news);
            }

            super.update(entity);
            pointSer.remove(points);
        } else {
            entity.setApproved(entity.getApproved() != null ? entity.getApproved() + 1 : 1);
            entity.setModifyTime(LocalDateTime.now());
            super.update(entity);
            Point point = new Point();
            point.setPraiseID(user.getId());
            point.setCommentID(commentID);
            pointSer.save(point);

            //增加消息
            News news = new News();
            news.setMessageID(entity.getCommentatorid());
            news.setRead(false);
            news.setSponsorID(point.getPraiseID());
//            UserInfoVO userInfoVO = userSer.userInfo(point.getPraiseID());
            User userInfoVO = userSer.findById(point.getPraiseID());
            news.setContent(userInfoVO.getNickname() + "点赞了您的评论\"" + entity.getComments() + "\"");
            news.setObjID(entity.getId());
            newsSer.save(news);
        }
    }

    @Transactional
    @Override
    public void uploadImg(String commentId, List<File> files) throws SerException {
        Comments comment = super.findById(commentId);
        List<Pictures> pictures = new ArrayList<>(files.size());
        for (File file : files) {
            String path = StringUtils.substringAfter(file.getPath(), "/root/storage/comment");
            Pictures picture = new Pictures();
            picture.setCommentID(commentId);
            picture.setPath(path);
            pictures.add(picture);
        }
        pictureSer.save(pictures);
    }

    @Override
    public Boolean isPoint(String commentID) throws SerException {
        Boolean tar = false;
        User user = UserUtil.currentUser();
        PointDTO dto = new PointDTO();
        dto.getConditions().add(Restrict.eq("commentID", commentID));
        dto.getConditions().add(Restrict.eq("praiseID", user.getId()));
        List<Point> points = pointSer.findByCis(dto);
        if (null != points && points.size() > 0) {
            tar = true;
        }
        return tar;
    }

    @Override
    public List<String> getComments() throws SerException {
        User user = UserUtil.currentUser();
        List<String> list = new ArrayList<>(0);
        PointDTO dto = new PointDTO();
        dto.getConditions().add(Restrict.eq("praiseID", user.getId()));
        List<Point> points = pointSer.findByCis(dto);
        if (null != points && points.size() > 0) {
            list = points.stream().map(Point::getCommentID).distinct().collect(Collectors.toList());
        }
        return list;
    }

    @Override
    public void delete(String commentID) throws SerException {
        Comments entity = super.findById(commentID);
        if (null == entity) {
            throw new SerException("目标数据对象为空");
        }
        InitiateActivities initiateActivities = initiateActivitiesSer.findById(entity.getInitiateactivitiesID());
        if (null == initiateActivities) {
            throw new SerException("该公益活动不存在");
        }
        initiateActivities.setCommentsNumber(initiateActivities.getCommentsNumber() != null ? initiateActivities.getCommentsNumber() - 1 : 0);
        initiateActivities.setModifyTime(LocalDateTime.now());
        initiateActivitiesSer.update(initiateActivities);

        //评论关联的消息删除
        NewsDTO newsDTO = new NewsDTO();
        newsDTO.getConditions().add(Restrict.eq("objID", commentID));
        List<News> newsList = newsSer.findByCis(newsDTO);
        newsSer.remove(newsList);

        //评论关联的点赞删除
        PointDTO pointDTO = new PointDTO();
        pointDTO.getConditions().add(Restrict.eq("commentID", commentID));
        List<Point> points = pointSer.findByCis(pointDTO);
        if (null != points && points.size() > 0) {
            pointSer.remove(points);
        }
        super.remove(commentID);

    }

    @Override
    public CommentsVO find(String id) throws SerException {
        Comments entity = super.findById(id);
        if (null == entity) {
            throw new SerException("目标对象为空");
        }
        CommentsVO vo = BeanCopy.copyProperties(entity, CommentsVO.class, "commentator", "initiateactivities", "commented");
        return transForm(vo);
    }


    private CommentsVO transForm(CommentsVO vo) throws SerException {
//        UserInfoVO userInfoVO1 = userSer.userInfo(vo.getCommentatorid());
        User userInfoVO1 = userSer.findById(vo.getCommentatorid());
        InitiateActivities initiateActivities = initiateActivitiesSer.findById(vo.getInitiateactivitiesID());
        if (StringUtils.isBlank(vo.getCommentedID())) {
            vo.setCommentedID(initiateActivities.getSponsor());
        }
//        UserInfoVO userInfoVO2 = userSer.userInfo(vo.getCommentedID());
        User userInfoVO2 = userSer.findById(vo.getCommentedID());
        vo.setCommentator(userInfoVO1.getNickname());
        vo.setCommentatorPathHead(userInfoVO1.getHeadPath());
        vo.setCommented(userInfoVO2.getNickname());
        vo.setCommentedPathHead(userInfoVO2.getHeadPath());
        if (null != initiateActivities) {
            vo.setInitiateactivities(initiateActivities.getActivitySubject());
        }
        return vo;
    }
}
