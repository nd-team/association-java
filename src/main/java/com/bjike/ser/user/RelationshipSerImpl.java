package com.bjike.ser.user;

import com.bjike.common.exception.SerException;
import com.bjike.common.util.UserUtil;
import com.bjike.common.util.bean.BeanCopy;
import com.bjike.dto.Restrict;
import com.bjike.dto.user.RelationshipDTO;
import com.bjike.dto.user.UserDTO;
import com.bjike.entity.user.Relationship;
import com.bjike.entity.user.User;
import com.bjike.entity.user.other.FriendChain;
import com.bjike.entity.user.other.UserChain;
import com.bjike.ser.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author: [liguiqin]
 * @Date: [2017-07-10 14:31]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class RelationshipSerImpl extends ServiceImpl<Relationship, RelationshipDTO> implements RelationshipSer {
    @Autowired
    private UserSer userSer;

    @Override
    public Relationship search(String name) throws SerException {
        Relationship relationship = new Relationship();
        User user = UserUtil.currentUser();
        UserDTO dto = new UserDTO();
        dto.getConditions().add(Restrict.or("nickname", name));
        dto.getConditions().add(Restrict.or("id", user.getId()));
        List<User> users = userSer.findByCis(dto);
        User seek = null;
        if (null != users && users.size() > 0) {
            seek = users.get(0);
            initRelationship(seek, user, relationship);
        }
        return relationship;
    }

    private void initRelationship(User seek, User user, Relationship relationship) throws SerException {
        String sql = "select a.friend_id  from friends_relationship a where a.user_id='%s'";
        String[] himFriends = null; //他的朋友
        String[] myFriends = null;//我的朋友

        String exSql = String.format(sql, user.getId());
        List<Object> objects = super.findBySql(exSql);
        if (null != objects) {
            myFriends = objects.toArray(new String[objects.size()]);
            for (String fid : myFriends) {
                if (fid.equals(seek.getId())) {
                    relationship.setYourFriend(true);
                    return;
                }
            }
        }
        exSql = String.format(sql, seek.getId());
        objects = super.findBySql(exSql);
        if (null != objects) {
            himFriends = objects.toArray(new String[objects.size()]);
            for (String mid : myFriends) {
                for (String hid : himFriends) {
                    if (hid.equals(mid)) { //第一条,第一层朋友链
                        if (relationship.getFriendChains().size() == 5) {
                            return;
                        }
                        User _user = userSer.findById(mid);
                        UserChain myFriend = BeanCopy.copyProperties(_user, UserChain.class);
                        UserChain u = new UserChain();
                        u.setNickname(seek.getNickname());
                        u.setHeadPath(seek.getHeadPath());
                        u.setId(seek.getId());
                        myFriend.setChain(u);
                        FriendChain chain = new FriendChain();
                        chain.setFriendChain(myFriend);
                        List<FriendChain> chains = new ArrayList<>();
                        chains.add(chain);
                        relationship.getFriendChains().add(chains);
                    }
                }
            }
        }
        Map<String, String[]> friendsMap = new HashMap<>();
        if (relationship.getFriendChains().size() <= 5) {
            for (String my_fid : myFriends) {
                sql = "select a.friend_id  from friends_relationship a where a.id='%s'";
                //通过朋友的朋友查询
                exSql = String.format(sql, my_fid);
                objects = super.findBySql(exSql);
                if (null != objects) {
                    String[] friend_friends = objects.toArray(new String[objects.size()]);
                    friendsMap.put(my_fid, friend_friends);
                    for (String friend_friend : friend_friends) {
                        for (String hid : himFriends) {
                            if (friend_friend.equals(hid)) { //第二层朋友链
                                if (relationship.getFriendChains().size() == 5) {
                                    return;
                                }
                                List<String> nameList = new ArrayList<>();
                                UserChain friend_Friend = BeanCopy.copyProperties(userSer.findById(friend_friend), UserChain.class);
                                UserChain second_chain = BeanCopy.copyProperties(userSer.findById(my_fid), UserChain.class);
                                UserChain u = new UserChain();
                                u.setNickname(seek.getNickname());
                                u.setHeadPath(seek.getHeadPath());
                                u.setId(seek.getId());
                                friend_Friend.setChain(second_chain);
                                friend_Friend.getChain().setChain(u);
                                nameList.add(friend_Friend.getNickname());
                                nameList.add(second_chain.getNickname());
                                nameList.add(seek.getNickname());
                                if (!isRepeat(nameList)) {
                                    FriendChain chain = new FriendChain();
                                    chain.setFriendChain(friend_Friend);
                                    List<FriendChain> chains = new ArrayList<>();
                                    chains.add(chain);
                                    relationship.getFriendChains().add(chains);
                                }


                            }
                        }
                    }
                }
            }

        }

        if (relationship.getFriendChains().size() <= 5) {
            for (Map.Entry<String, String[]> entry : friendsMap.entrySet()) {
                String friend = entry.getKey();
                String[] friend_friends = entry.getValue();
                for (String fid : friend_friends) {
                    sql = "select a.friend_id  from friends_relationship a where a.id='%s'";
                    //通过朋友的朋友查询
                    exSql = String.format(sql, fid);
                    objects = super.findBySql(exSql);
                    if (null != objects) {
                        String[] friend_friend_friends = objects.toArray(new String[objects.size()]);
                        for (String friend_friend : friend_friend_friends) {
                            for (String hid : himFriends) {
                                if (friend_friend.equals(hid)) { //第三层朋友链
                                    if (relationship.getFriendChains().size() == 5) {
                                        return;
                                    }
                                    List<String> nameList = new ArrayList<>();
                                    UserChain last_chain = BeanCopy.copyProperties(userSer.findById(friend_friend), UserChain.class);
                                    UserChain u = new UserChain();
                                    u.setNickname(seek.getNickname());
                                    u.setHeadPath(seek.getHeadPath());
                                    u.setId(seek.getId());
                                    last_chain.setChain(u);
                                    UserChain second_chain = BeanCopy.copyProperties(userSer.findById(fid), UserChain.class);
                                    UserChain first_chain = BeanCopy.copyProperties(userSer.findById(friend), UserChain.class);
                                    nameList.add(last_chain.getNickname());
                                    nameList.add(u.getNickname());
                                    nameList.add(second_chain.getNickname());
                                    nameList.add(first_chain.getNickname());
                                    if (!isRepeat(nameList)) {
                                        second_chain.setChain(last_chain);
                                        first_chain.setChain(second_chain);
                                        FriendChain chain = new FriendChain();
                                        chain.setFriendChain(first_chain);
                                        List<FriendChain> chains = new ArrayList<>();
                                        chains.add(chain);
                                        relationship.getFriendChains().add(chains);
                                    }

                                }
                            }
                        }
                    }
                }

            }
        }

    }


    private boolean isRepeat(List<String> nameList) {
        HashSet<String> nameSet = new HashSet<>();
        nameSet.addAll(nameList);
        return nameList.size() != nameSet.size();
    }


}
