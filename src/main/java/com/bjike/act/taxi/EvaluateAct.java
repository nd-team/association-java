package com.bjike.act.taxi;

import com.bjike.common.aspect.ADD;
import com.bjike.common.exception.ActException;
import com.bjike.common.exception.SerException;
import com.bjike.common.restful.ActResult;
import com.bjike.common.restful.Result;
import com.bjike.common.util.file.FileUtil;
import com.bjike.entity.taxi.Evaluate;
import com.bjike.ser.taxi.EvaluateSer;
import com.bjike.to.taxi.EvaluateTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

/**
 * 订单评价
 *
 * @Author: [ liguiqin ]
 * @Date: [  2017-09-06 14:50:50 ]
 * @Description: [ 订单评价 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [com.bjike]
 */

@RestController
@RequestMapping("evaluate")
public class EvaluateAct {
    @Autowired
    private EvaluateSer evaluateSer;

    /**
     * 司机订单评价
     *
     * @param to 评价信息
     * @return {name:'data',type:'boolean',defaultValue:'',description:'true/false.'}
     * @version v1
     */
    @PostMapping("add/{orderId}")
    public Result add(@Validated({ADD.class}) EvaluateTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            String path = FileUtil.getModulePath("evaluate");
            List<File> files = FileUtil.save(request,path);
            Boolean rs = evaluateSer.add(to,files);
            return ActResult.initialize(rs);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    @PostMapping("unfinished/{orderId}")
    public Result unfinished(@PathVariable String orderId,String content) throws ActException {
        try {
            Boolean rs = evaluateSer.unfinished(orderId,content);
            return ActResult.initialize(rs);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
