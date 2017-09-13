package com.bjike.to.CharitableActivities;

import com.bjike.common.aspect.ADD;
import com.bjike.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Author: [dengjunren]
 * @Date: [2017-08-31 17:53]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ReplyTO extends BaseTO {

    /**
     * 回复人
     */
    @NotBlank(message = "回复人不能为空", groups = {ADD.class})
    private String replyID;

    /**
     * 回复内容
     */
    @NotBlank(message = "回复内容不能为空", groups = {ADD.class})
    private String content;

    /**
     * 回复的评论id
     */
    @NotBlank(message = "回复的评论id不能为空", groups = {ADD.class})
    private String commentID;

    public String getReplyID() {
        return replyID;
    }

    public void setReplyID(String replyID) {
        this.replyID = replyID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCommentID() {
        return commentID;
    }

    public void setCommentID(String commentID) {
        this.commentID = commentID;
    }
}
