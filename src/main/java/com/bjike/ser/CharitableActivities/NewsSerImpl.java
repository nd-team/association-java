package com.bjike.ser.CharitableActivities;

import com.bjike.common.exception.SerException;
import com.bjike.common.util.UserUtil;
import com.bjike.common.util.bean.BeanCopy;
import com.bjike.common.util.date.DateUtil;
import com.bjike.dto.CharitableActivities.NewsDTO;
import com.bjike.dto.Restrict;
import com.bjike.entity.CharitableActivities.News;
import com.bjike.entity.user.User;
import com.bjike.ser.ServiceImpl;
import com.bjike.ser.user.UserSer;
import com.bjike.vo.CharitableActivities.NewsVO;
import com.bjike.vo.user.UserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: [dengjunren]
 * @Date: [2017-09-01 15:10]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class NewsSerImpl extends ServiceImpl<News, NewsDTO> implements NewsSer {
    @Autowired
    private UserSer userSer;

    @Override
    public List<NewsVO> getNews() throws SerException {
        User user = UserUtil.currentUser();
        List<NewsVO> list = new ArrayList<>(0);
        NewsDTO newsDTO = new NewsDTO();
        newsDTO.getConditions().add(Restrict.eq("messageID", user.getId()));
        newsDTO.getConditions().add(Restrict.eq("isRead", 0));
        List<News> newsList = super.findByCis(newsDTO);
        if (null != newsList && newsList.size() > 0) {
            for (News news : newsList) {
                list.add(transForm(news));
            }
        }
        return list;
    }

    @Override
    public Long getTotal() throws SerException {
        User user = UserUtil.currentUser();
        NewsDTO dto = new NewsDTO();
        dto.getConditions().add(Restrict.eq("isRead", 0));
        dto.getConditions().add(Restrict.eq("messageID", user.getId()));
        return super.count(dto);
    }

    @Override
    public List<NewsVO> list(NewsDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<NewsVO> list = new ArrayList<>(0);
        List<News> newsList = super.findByPage(dto);
        if (null != newsList && newsList.size() > 0) {
            for (News news : newsList) {
                list.add(transForm(news));
            }
        }
        return list;
    }

    @Override
    public void read(String id) throws SerException {
        News entity = super.findById(id);
        if (null == entity) {
            throw new SerException("目标数据对象不存在");
        }
        entity.setRead(true);
        super.update(entity);
    }

    private NewsVO transForm(News news) throws SerException {
//        UserInfoVO userInfoVO = userSer.userInfo(news.getMessageID());
        User userInfoVO = userSer.findById(news.getMessageID());
//        UserInfoVO userInfoVO1 = userSer.userInfo(news.getSponsorID());
        User userInfoVO1 = userSer.findById(news.getSponsorID());
        NewsVO vo = BeanCopy.copyProperties(news, NewsVO.class, "message", "sponsor");
        vo.setMessage(userInfoVO.getNickname());
        vo.setSponsor(userInfoVO1.getNickname());
        vo.setCreateTime(DateUtil.dateToString(news.getCreateTime()));
        return vo;
    }
}
