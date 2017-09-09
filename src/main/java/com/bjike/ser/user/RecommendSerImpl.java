package com.bjike.ser.user;

import com.bjike.common.exception.SerException;
import com.bjike.common.util.UserUtil;
import com.bjike.common.util.bean.BeanCopy;
import com.bjike.dto.Restrict;
import com.bjike.dto.user.RecommendDTO;
import com.bjike.entity.user.Recommend;
import com.bjike.entity.user.User;
import com.bjike.ser.ServiceImpl;
import com.bjike.to.user.RecommendTO;
import com.bjike.vo.recommend.RecommendVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author: [liguiqin]
 * @Date: [2017-08-23 14:20]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class RecommendSerImpl extends ServiceImpl<Recommend, RecommendDTO> implements RecommendSer {

    @Autowired
    private UserSer userSer;

    @Override
    public String add(RecommendTO to) throws SerException {
        User recommended = userSer.findById(to.getUserId());
        if(null!=recommended){
            RecommendDTO dto = new RecommendDTO();
            dto.getConditions().add(Restrict.eq("recommended.id",to.getUserId()));
            dto.getConditions().add(Restrict.eq("succeed",true));
            if(null == super.findOne(dto)){
                Recommend recommend = BeanCopy.copyProperties(to, Recommend.class);
                User currentUser = UserUtil.currentUser();
                recommend.setUser(currentUser);
                recommend.setSucceed(false);
                recommend.setRecommended(recommended);
                String code = "QR-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
                recommend.setInviteCode(code);
                super.save(recommend);
                return code;
            }else {
                throw  new SerException("该用户已经被推荐过了");
            }
        }else {
            throw  new SerException("该推荐用户不存在");
        }



    }

    @Override
    public List<RecommendVO> myRecommends(RecommendDTO dto) throws SerException {
        String userId = UserUtil.currentUserID();
        dto.getConditions().add(Restrict.eq("user.id", userId));
        List<Recommend> recommends = super.findByCis(dto);
        List<RecommendVO> recommendVOS = new ArrayList<>();
        for (Recommend recommend : recommends) {
            RecommendVO vo = new RecommendVO();
            vo.setNickname(recommend.getRealName());
            vo.setInviteCode(recommend.getInviteCode());
            recommendVOS.add(vo);
        }
        return recommendVOS;
    }

    @Override
    public Boolean validate(String code) throws SerException {
        return null != findByInviteCode(code);
    }

    @Override
    public Recommend findByInviteCode(String code) throws SerException {
        RecommendDTO dto = new RecommendDTO();
        dto.getConditions().add(Restrict.eq("inviteCode", code));
        return super.findOne(dto);
    }

    @Override
    public Recommend findByUserId(String userId) throws SerException {
        RecommendDTO dto = new RecommendDTO();
        dto.getConditions().add(Restrict.eq("recommended.id", userId));
        Recommend recommend = super.findOne(dto);
        if (null != recommend) {
            return recommend;
        } else {
            throw new SerException("找不到该用户推荐人!");
        }
    }
}
