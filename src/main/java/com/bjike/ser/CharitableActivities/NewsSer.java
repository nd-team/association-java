package com.bjike.ser.CharitableActivities;

import com.bjike.common.exception.SerException;
import com.bjike.dto.CharitableActivities.NewsDTO;
import com.bjike.entity.CharitableActivities.News;
import com.bjike.ser.Ser;
import com.bjike.vo.CharitableActivities.NewsVO;

import java.util.List;

/**
 * @Author: [dengjunren]
 * @Date: [2017-09-01 15:09]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface NewsSer extends Ser<News, NewsDTO> {

    /**
     * 读取未读消息
     *
     * @return
     */
    default List<NewsVO> getNews() throws SerException {
        return null;
    }

    /**
     * 得到当前用户的未读数据的条数
     *
     * @return
     * @throws SerException
     */
    default Long getTotal() throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default List<NewsVO> list(NewsDTO dto) throws SerException {
        return null;
    }

    /**
     * 将当前用户的未读消息设为已读
     *
     * @param id
     * @throws SerException
     */
    default void read(String id) throws SerException {
        return;
    }
}
