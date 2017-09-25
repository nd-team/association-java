package com.bjike.act.taxi;

import com.bjike.common.aspect.ADD;
import com.bjike.common.exception.ActException;
import com.bjike.common.exception.SerException;
import com.bjike.common.interceptor.login.LoginAuth;
import com.bjike.common.restful.ActResult;
import com.bjike.common.restful.Result;
import com.bjike.entity.taxi.AreaCost;
import com.bjike.ser.taxi.AreaCostSer;
import com.bjike.to.taxi.AreaCostTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 打车地区费用
 *
 * @Author: [liguiqin]
 * @Date: [2017-07-19 13:58]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RequestMapping("area/cost")
@RestController
public class AreaCostAct {
    /**
     * 添加
     *
     * @param to 费用信息
     * @throws ActException
     * @version v1
     */
    @Autowired
    private AreaCostSer areaCostSer;

    @PostMapping("add")
    public Result add(@Validated({ADD.class}) AreaCostTO to, BindingResult rs) throws ActException {
        try {
            areaCostSer.add(to);
            return ActResult.initialize("success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 地区打车费用id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("delete/{id}")
    public Result add(@PathVariable String id) throws ActException {
        try {
            areaCostSer.remove(id);
            return ActResult.initialize("success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 开启关闭
     *
     * @param id   地区打车费用id
     * @param open 是否打开
     * @version v1
     */
    @PutMapping("edit/{id}/{enable}")
    public Result enable(@PathVariable String id, @PathVariable Boolean open) throws ActException {
        try {
            AreaCost cost = areaCostSer.findById(id);
            cost.setEnable(open);
            areaCostSer.update(cost);
            return ActResult.initialize("success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}
