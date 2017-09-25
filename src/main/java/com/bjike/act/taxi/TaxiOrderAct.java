package com.bjike.act.taxi;

import com.bjike.common.exception.ActException;
import com.bjike.common.exception.SerException;
import com.bjike.common.restful.ActResult;
import com.bjike.common.restful.Result;
import com.bjike.entity.taxi.TaxiOrder;
import com.bjike.ser.taxi.TaxiOrderSer;
import com.bjike.to.taxi.TaxiOrderTO;
import com.bjike.type.taxi.OrderStatus;
import com.bjike.vo.taxi.NearbyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 叫车订单
 *
 * @Author: [liguiqin]
 * @Date: [2017-07-19 13:58]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RequestMapping("order")
@RestController
public class TaxiOrderAct {

    @Autowired
    private TaxiOrderSer taxiOrderSer;

    /**
     * 发布用车
     *
     * @param to 发布用车信息
     * @return {name:'data',type:'boolean',defaultValue:'',description:'true/false.'}
     * @version v1
     */
    @PostMapping("publish")
    public Result publish(@Validated({TaxiOrderTO.PUBLISH.class}) TaxiOrderTO to, BindingResult result) throws ActException {
        try {
            Boolean rs = taxiOrderSer.publish(to);
            return ActResult.initialize(rs);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 取消订单
     *
     * @param id 订单id
     * @return {name:'data',type:'boolean',defaultValue:'',description:'true/false.'}
     * @version v1
     */
    @PutMapping("cancel/{id}")
    public Result cancel(@PathVariable String id) throws ActException {
        try {
            TaxiOrder taxiOrder = taxiOrderSer.findById(id);
            taxiOrder.setStatus(OrderStatus.CANCEL);
            taxiOrderSer.update(taxiOrder);
            return ActResult.initialize(true);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 发布用车
     *
     * @param id 订单id
     * @return {name:'data',type:'boolean',defaultValue:'',description:'true/false.'}
     * @version v1
     */
    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            TaxiOrder taxiOrder = taxiOrderSer.findById(id);
            taxiOrderSer.remove(taxiOrder);
            return ActResult.initialize(true);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 接单
     *
     * @return {name:'data',type:'boolean',defaultValue:'',description:'true/false.'}
     * @version v1
     */
    @PutMapping("taking/{orderId}")
    public Result taking(@PathVariable String orderId) throws ActException {
        try {
            Boolean rs = taxiOrderSer.taking(orderId);
            return ActResult.initialize(rs);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 附近的訂單
     *
     * @param longitude 经度
     * @param latitude  纬度
     * @param range     范围(米)默认200米
     * @return class NearbyVO
     * @version v1
     * @throws ActException
     */
    @GetMapping("nearby")
    public Result taking(@RequestParam Double longitude, @RequestParam Double latitude, Integer range) throws ActException {
        try {
            List<NearbyVO> nearbyVOS = taxiOrderSer.nearby(longitude, latitude, range);
            return ActResult.initialize(nearbyVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    ;

    /**
     * 预测费用
     *
     * @param area     地区
     * @param distance 距离公里
     * @return {name:'data',type:'double',defaultValue:'',description:'费用.'}
     * @version v1
     */
    @GetMapping("cost/{area}")
    public Result cost(@PathVariable String area, @RequestParam Double distance, @RequestParam Integer minutes) throws ActException {
        try {
            Double rs = taxiOrderSer.cost(area, distance, minutes);
            return ActResult.initialize(rs);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}
