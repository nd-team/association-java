package com.bjike.entity.user.other;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-08 17:19]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class UserChain {

    private String id;
    private String nickname;
    private String headPath;
    private UserChain chain;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadPath() {
        return headPath;
    }

    public void setHeadPath(String headPath) {
        this.headPath = headPath;
    }

    public UserChain getChain() {
        return chain;
    }

    public void setChain(UserChain chain) {
        this.chain = chain;
    }
}
