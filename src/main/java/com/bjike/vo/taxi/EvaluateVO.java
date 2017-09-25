package com.bjike.vo.taxi;

import com.bjike.type.comment.ScoreType;
import com.bjike.vo.BaseVO;


/**
 * 订单评价值对象
 *
 * @Author: [liguiqin]
 * @Date: [2017-07-19 13:58]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class EvaluateVO extends BaseVO {

    /**
     * 评价人
     */
 	private String reviewer;
    /**
     * 被评价人
     */
 	private String username;
    /**
     * 评价内容
     */
 	private String content;
    /**
     * 评分
     */
 	private ScoreType scoreType;

	public String getReviewer() {
		return reviewer;
	}

	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getContent () {
		return content;
	}
	public void setContent (String content) {
		this.content =content;
	}

 	public ScoreType getScoreType () {
		return scoreType;
	}
	public void setScoreType (ScoreType scoreType) {
		this.scoreType =scoreType;
	}



}
