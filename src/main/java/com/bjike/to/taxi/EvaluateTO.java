package com.bjike.to.taxi;

import com.bjike.common.aspect.ADD;
import com.bjike.common.aspect.EDIT;
import com.bjike.to.BaseTO;
import com.bjike.type.comment.ScoreType;
import org.hibernate.validator.constraints.NotBlank;


/**
 * 订单评价添加更新操作对象
 *
 * @Author: [liguiqin]
 * @Date: [2017-07-19 13:58]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class EvaluateTO extends BaseTO {


    /**
     * 订单id
     */
    @NotBlank(message = "请填写订单id", groups = {ADD.class, EDIT.class})
    private String orderId;
    /**
     * 评价内容
     */
    @NotBlank(message = "请填写评价内容", groups = {ADD.class, EDIT.class})
    private String content;
    /**
     * 评分
     */
    @NotBlank(message = "请填写评分", groups = {ADD.class, EDIT.class})
    private ScoreType scoreType;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ScoreType getScoreType() {
        return scoreType;
    }

    public void setScoreType(ScoreType scoreType) {
        this.scoreType = scoreType;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
