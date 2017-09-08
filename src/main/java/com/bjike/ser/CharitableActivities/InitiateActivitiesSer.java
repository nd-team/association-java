package com.bjike.ser.CharitableActivities;

import com.bjike.common.exception.SerException;
import com.bjike.dto.CharitableActivities.InitiateActivitiesDTO;
import com.bjike.entity.CharitableActivities.InitiateActivities;
import com.bjike.ser.Ser;
import com.bjike.to.CharitableActivities.InitiateActivitiesTO;
import com.bjike.vo.CharitableActivities.InitiateActivitiesVO;

import java.io.File;
import java.util.List;

/**
 * @Author: [dengjunren]
 * @Date: [2017-08-31 08:39]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface InitiateActivitiesSer extends Ser<InitiateActivities, InitiateActivitiesDTO> {
    /**
     * 公益活动列表
     */
    default List<InitiateActivitiesVO> list(InitiateActivitiesDTO dto) throws SerException {
        return null;
    }

    /**
     * 活动发起
     *
     * @param to
     * @throws SerException
     */
    default void add(InitiateActivitiesTO to, List<File> files) throws SerException {
        return;
    }

    /**
     * 编辑
     *
     * @param to
     * @throws SerException
     */
    default void edit(InitiateActivitiesTO to) throws SerException {
        return;
    }

    /**
     * 删除
     *
     * @param id
     * @throws SerException
     */
    default void delete(String id) throws SerException {
        return;
    }

    /**
     * 公益活动点赞/取消点赞
     *
     * @param initiateactivitiesID
     * @throws SerException
     */
    default void like(String initiateactivitiesID) throws SerException {
        return;
    }

    /**
     * 转发
     *
     * @param initiateactivitiesID
     * @throws SerException
     */
    default void forward(String initiateactivitiesID) throws SerException {
        return;
    }

    /**
     * 上传图片
     *
     * @param initiateactivitiesID
     * @param files
     * @throws SerException
     */
    default void uploadImg(String initiateactivitiesID, List<File> files) throws SerException {
    }

    /**
     * 根据id获取对象
     *
     * @param id
     * @return
     * @throws SerException
     */
    default InitiateActivitiesVO find(String id) throws SerException {
        return null;
    }

    /**
     * 我参与的公益
     *
     * @return
     * @throws SerException
     */
    default List<InitiateActivitiesVO> partake() throws SerException {
        return null;
    }

    /**
     * 当前用户对该公益活动是否已点赞
     *
     * @param initiateactivitiesID
     * @return
     * @throws SerException
     */
    default Boolean isPoint(String initiateactivitiesID) throws SerException {
        return null;
    }

    /**
     * 得到当前用户点赞的公益活动id
     *
     * @return
     * @throws SerException
     */
    default List<String> getInitiateActivitieses() throws SerException {
        return null;
    }
}
