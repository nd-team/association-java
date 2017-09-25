package com.bjike.act.user.relation;

import com.bjike.common.exception.ActException;
import com.bjike.common.exception.SerException;
import com.bjike.common.interceptor.login.LoginAuth;
import com.bjike.common.restful.ActResult;
import com.bjike.common.restful.Result;
import com.bjike.ser.user.RelationshipSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 关系
 *
 * @Author: [liguiqin]
 * @Date: [2017-06-27 16:29]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RequestMapping("relation")
@RestController
@LoginAuth
public class RelationshipAct {
    @Autowired
    private RelationshipSer relationshipSer;

    /**
     * 后台找人
     *
     * @param name 昵称
     * @version v1
     * @desc 五种途径可找到该人
     */
    @RequestMapping("search/{name}")
    public Result search(@PathVariable String name) throws ActException {
        try {
            return ActResult.initialize(relationshipSer.search(name));

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}
