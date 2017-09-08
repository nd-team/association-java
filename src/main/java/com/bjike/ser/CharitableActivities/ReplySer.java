package com.bjike.ser.CharitableActivities;

import com.bjike.common.exception.SerException;
import com.bjike.dto.CharitableActivities.ReplyDTO;
import com.bjike.entity.CharitableActivities.Reply;
import com.bjike.ser.Ser;
import com.bjike.to.CharitableActivities.ReplyTO;

/**
 * @Author: [dengjunren]
 * @Date: [2017-08-31 17:46]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface ReplySer extends Ser<Reply, ReplyDTO> {

    /**
     * 回复评论
     *
     * @param to
     * @throws SerException
     */
    default void reply(ReplyTO to) throws SerException {
        return;
    }
}
