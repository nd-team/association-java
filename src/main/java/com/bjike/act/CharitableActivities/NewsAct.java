package com.bjike.act.CharitableActivities;

import com.bjike.common.exception.ActException;
import com.bjike.common.exception.SerException;
import com.bjike.common.interceptor.login.LoginAuth;
import com.bjike.common.restful.ActResult;
import com.bjike.common.restful.Result;
import com.bjike.dto.CharitableActivities.NewsDTO;
import com.bjike.ser.CharitableActivities.NewsSer;
import com.bjike.vo.CharitableActivities.NewsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 公益活动信息
 *
 * @Author: [dengjunren]
 * @Date: [2017-09-01 15:06]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@LoginAuth
@RestController
@RequestMapping("newsAct")
public class NewsAct {

    @Autowired
    private NewsSer newsSer;

    /**
     * 读取当前用户的未读消息的内容
     */
    @GetMapping("/getNews")
    public Result getNews() throws ActException {
        try {
            List<NewsVO> list = newsSer.getNews();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     */
    @GetMapping("/list")
    public Result list(NewsDTO dto) throws ActException {
        try {
            List<NewsVO> newsList = newsSer.list(dto);
            return ActResult.initialize(newsList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取对象
     */
    @GetMapping("/findById/{id}")
    public Result findByID(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(newsSer.findById(id));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 得到当前用户的未读数据的条数
     */
    @GetMapping("/getTotal")
    public Result getTotal() throws ActException {
        try {
            return ActResult.initialize(newsSer.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 将当前用户的未读消息设为已读
     */
    @PutMapping("v1/read/{id}")
    public Result read(@PathVariable String id) throws ActException {
        try {
            newsSer.read(id);
            return ActResult.initialize("消息已读");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}
