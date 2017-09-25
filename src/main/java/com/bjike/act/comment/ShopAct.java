package com.bjike.act.comment;

import com.bjike.common.exception.ActException;
import com.bjike.common.exception.SerException;
import com.bjike.common.interceptor.login.LoginAuth;
import com.bjike.common.restful.ActResult;
import com.bjike.common.restful.Result;
import com.bjike.dto.Restrict;
import com.bjike.dto.comment.ShopDTO;
import com.bjike.entity.comment.Shop;
import com.bjike.ser.comment.ShopSer;
import com.bjike.vo.comment.ShopVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 店铺
 *
 * @Author: [liguiqin]
 * @Date: [2017-06-28 14:07]
 * @Description: [店铺 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@LoginAuth
@RestController
@RequestMapping("shop")
public class ShopAct {
    @Autowired
    private ShopSer shopSer;

    /**
     * 附近店铺
     *
     * @return class ShopVO
     * @throws Exception
     * @version v1
     */
    @GetMapping("nearby")
    public Result nearby() throws ActException {
        try {
            ShopDTO dto = new ShopDTO();
            List<ShopVO> shopVOs = shopSer.nearby(dto);
            return ActResult.initialize(shopVOs);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 店铺删除
     *
     * @param pointId 店铺坐标id
     * @return {name:'data',type:'string',defaultValue:'',description:'delete success.'}
     * @version v1
     */
    @DeleteMapping("del/{pointId}")
    public Result del(@PathVariable String pointId) throws ActException {
        try {
            ShopDTO dto = new ShopDTO();
            dto.getConditions().add(Restrict.eq("pointId", pointId));
            Shop shop = shopSer.findOne(dto);
            if (null != shop) {
                shopSer.remove(shop);
            }
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
