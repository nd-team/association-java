package com.bjike.act.CharitableActivities;

import com.bjike.common.aspect.ADD;
import com.bjike.common.exception.ActException;
import com.bjike.common.exception.SerException;
import com.bjike.common.interceptor.login.LoginAuth;
import com.bjike.common.restful.ActResult;
import com.bjike.common.restful.Result;
import com.bjike.dto.CharitableActivities.RegistrationDTO;
import com.bjike.ser.CharitableActivities.RegistrationSer;
import com.bjike.to.CharitableActivities.RegistrationTO;
import com.bjike.vo.CharitableActivities.RegistrationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 公益活动报名
 *
 * @Author: [dengjunren]
 * @Date: [2017-08-31 11:36]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@LoginAuth
@RestController
@RequestMapping("registrationAct")
public class RegistrationAct {
    @Autowired
    private RegistrationSer registrationSer;

    /**
     * 活动报名
     */
    @PostMapping("/add")
    public Result add(@Validated(ADD.class) RegistrationTO to, BindingResult bindingResult) throws ActException {
        try {
            registrationSer.add(to);
            return ActResult.initialize("报名成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

//    /**
//     * 报名信息修改
//     */
//    @PutMapping("/edit/{id}")
//    public Result edit(RegistrationTO to) throws ActException {
//        try {
//            registrationSer.edit(to);
//            return ActResult.initialize("edit success");
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }

    /**
     * 列表
     */
    @GetMapping("/list")
    public Result list(RegistrationDTO dto) throws ActException {
        try {
            List<RegistrationVO> registrationVOs = registrationSer.list(dto);
            return ActResult.initialize(registrationVOs);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取对象
     */
    @GetMapping("/findById/{id}")
    public Result findById(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(registrationSer.find(id));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 是否已报名
     */
    @GetMapping("/isApply/{id}")
    public Result isApply(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(registrationSer.isApply(id));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
