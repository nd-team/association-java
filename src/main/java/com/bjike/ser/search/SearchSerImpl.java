package com.bjike.ser.search;

import com.bjike.common.exception.SerException;
import com.bjike.common.util.UserUtil;
import com.bjike.common.util.bean.BeanCopy;
import com.bjike.dto.Restrict;
import com.bjike.dto.chat.FriendDTO;
import com.bjike.dto.search.SearchDTO;
import com.bjike.dto.user.UserDTO;
import com.bjike.dto.user.UserInfoDTO;
import com.bjike.entity.chat.Friend;
import com.bjike.entity.search.Search;
import com.bjike.entity.user.User;
import com.bjike.entity.user.UserInfo;
import com.bjike.ser.ServiceImpl;
import com.bjike.ser.chat.FriendSer;
import com.bjike.ser.user.UserInfoSer;
import com.bjike.ser.user.UserSer;
import com.bjike.type.search.DealStatus;
import com.bjike.vo.search.FindVO;
import com.bjike.vo.search.SearchUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 后台找人
 *
 * @Author: [chenjunhao]
 * @Date: [2017-09-05 13:57]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class SearchSerImpl extends ServiceImpl<Search, SearchDTO> implements SearchSer {
    @Autowired
    private UserSer userSer;
    @Autowired
    private UserInfoSer userInfoSer;
    @Autowired
    private FriendSer friendSer;

    @Override
    public List<FindVO> search(String name) throws SerException {
        String currentId = UserUtil.currentUserID();
        Set<String> ids = userIds(name);
        List<FindVO> vos = new ArrayList<>();
        for (String id : ids) {
            FriendDTO dto = new FriendDTO();
            dto.getConditions().add(Restrict.eq("user.id", currentId));
            dto.getConditions().add(Restrict.eq("friend.id", id));
            Friend friend = friendSer.findOne(dto);
            User user = userSer.findById(id);
            FindVO vo = BeanCopy.copyProperties(user, FindVO.class);
            vo.setUserId(user.getId());
            if ((null != friend) || (currentId.equals(id))) {
                vo.setFriend(true);
            } else {
                vo.setFriend(false);
            }
            vos.add(vo);
        }
        return vos;
    }

    private Set<String> userIds(String name) throws SerException {
        UserDTO userDTO1 = new UserDTO();
        userDTO1.getConditions().add(Restrict.like("username", name));
        List<User> list1 = userSer.findByCis(userDTO1);
        UserDTO userDTO2 = new UserDTO();
        userDTO2.getConditions().add(Restrict.like("nickname", name));
        List<User> list2 = userSer.findByCis(userDTO2);
        UserInfoDTO userInfoDTO1 = new UserInfoDTO();
        userInfoDTO1.getConditions().add(Restrict.like("realName", name));
        List<UserInfo> infos1 = userInfoSer.findByCis(userInfoDTO1);
        UserInfoDTO userInfoDTO2 = new UserInfoDTO();
        userInfoDTO2.getConditions().add(Restrict.like("company", name));
        List<UserInfo> infos2 = userInfoSer.findByCis(userInfoDTO2);
        UserInfoDTO userInfoDTO3 = new UserInfoDTO();
        userInfoDTO3.getConditions().add(Restrict.like("job", name));
        List<UserInfo> infos3 = userInfoSer.findByCis(userInfoDTO3);
        Set<String> s1 = list1.stream().map(user -> user.getId()).collect(Collectors.toSet());
        Set<String> s2 = list2.stream().map(user -> user.getId()).collect(Collectors.toSet());
        Set<String> set1 = infos1.stream().map(userInfo -> userInfo.getUser().getId()).collect(Collectors.toSet());
        Set<String> set2 = infos2.stream().map(userInfo -> userInfo.getUser().getId()).collect(Collectors.toSet());
        Set<String> set3 = infos3.stream().map(userInfo -> userInfo.getUser().getId()).collect(Collectors.toSet());
        s1.addAll(s2);
        s1.addAll(set1);
        s1.addAll(set2);
        s1.addAll(set3);
        return s1;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public SearchUserVO agree(String id) throws SerException {
        Search entity=super.findById(id);
        if (!DealStatus.WAITDEAL.equals(entity.getDealStatus())){
            throw new SerException("该记录已被处理了，不能操作");
        }
        entity.setDealStatus(DealStatus.AGREE);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        User user = userSer.findById(entity.getUserId());
        SearchUserVO vo = BeanCopy.copyProperties(user, SearchUserVO.class);
        UserInfoDTO dto = new UserInfoDTO();
        dto.getConditions().add(Restrict.eq("user.id", user.getId()));
        UserInfo info = userInfoSer.findOne(dto);
        if (null != info) {
            vo.setAddress(info.getAddress());
            vo.setContribute(info.getContribute());
            vo.setReputation(info.getReputation());
            vo.setCompany(info.getCompany());
            vo.setJob(info.getJob());
        }
        //todo:推送消息给想认识人的那个人,告知已同意
        return vo;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public FindVO know(String id) throws SerException {
        String name = UserUtil.currentUser().getUsername();
        User user = userSer.findById(id);
        Search search = new Search();
        search.setUserId(id);
        search.setName(name);
        search.setDealStatus(DealStatus.WAITDEAL);
        super.save(search);
        FindVO findVO = BeanCopy.copyProperties(user, FindVO.class,"id");
        findVO.setName(name);
        findVO.setUserId(user.getId());
        findVO.setId(search.getId());
        findVO.setDealStatus(search.getDealStatus());
        //todo:推送消息给维护人员
        return findVO;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void reject(String id) throws SerException {
        Search entity=super.findById(id);
        if (!DealStatus.WAITDEAL.equals(entity.getDealStatus())){
            throw new SerException("该记录已被处理了，不能操作");
        }
        entity.setDealStatus(DealStatus.REJECT);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        //todo:推送消息给想认识人的那个人,告知他被拒绝了
    }

    @Override
    public List<FindVO> list(SearchDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<Search> list = super.findByCis(dto);
        List<FindVO> vos = new ArrayList<>();
        for (Search s : list) {
            String id=s.getId();
            String userId = s.getUserId();
            String name = s.getName();
            User user = userSer.findById(userId);
            FindVO vo = BeanCopy.copyProperties(user, FindVO.class,"id");
            vo.setUserId(userId);
            vo.setName(name);
            vo.setId(id);
            vo.setDealStatus(s.getDealStatus());
            vos.add(vo);
        }
        return vos;
    }
}
