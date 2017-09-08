package com.bjike.ser.CharitableActivities;

import com.bjike.common.exception.SerException;
import com.bjike.dto.CharitableActivities.CommentsDTO;
import com.bjike.entity.CharitableActivities.Comments;
import com.bjike.ser.Ser;
import com.bjike.to.CharitableActivities.CommentsTO;
import com.bjike.vo.CharitableActivities.CommentsVO;

import java.io.File;
import java.util.List;

/**
 * @Author: [dengjunren]
 * @Date: [2017-08-31 14:53]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface CommentsSer extends Ser<Comments, CommentsDTO> {

    /**
     * 新增评论
     *
     * @param to
     * @throws SerException
     */
    default void add(CommentsTO to) throws SerException {
        return;
    }

    /**
     * 获取评论列表
     *
     * @return
     * @throws SerException
     */
    default List<CommentsVO> list(CommentsDTO dto) throws SerException {
        return null;
    }

    /**
     * 评论数
     *
     * @param initiateactivitiesID
     * @return
     * @throws SerException
     */
    default Long getTotal(String initiateactivitiesID) throws SerException {
        return null;
    }

    /**
     * 评论回复
     *
     * @param to
     * @throws SerException
     */
    default void reply(CommentsTO to) throws SerException {
        return;
    }

    /**
     * 评论点赞/取消
     *
     * @param commentID
     * @throws SerException
     */
    default void like(String commentID) throws SerException {
        return;
    }

    /**
     * 上传图片
     *
     * @param commentId
     * @param files
     * @throws SerException
     */
    default void uploadImg(String commentId, List<File> files) throws SerException {
    }

    /**
     * 当前用户对该条评论是否已点赞
     *
     * @return
     * @throws SerException
     */
    default Boolean isPoint(String commentID) throws SerException {
        return null;
    }

    /**
     * 得到当前用户点赞的评论id
     *
     * @return
     * @throws SerException
     */
    default List<String> getComments() throws SerException {
        return null;
    }

    /**
     * 评论删除
     *
     * @param commentID
     * @throws SerException
     */
    default void delete(String commentID) throws SerException {
        return;
    }

    /**
     * 根据ｉｄ获取对象
     *
     * @param id
     * @return
     * @throws SerException
     */
    default CommentsVO find(String id) throws SerException {
        return null;
    }
}
