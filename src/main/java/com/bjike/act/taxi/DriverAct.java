package com.bjike.act.taxi;

import com.bjike.common.exception.ActException;
import com.bjike.common.exception.SerException;
import com.bjike.common.interceptor.login.LoginAuth;
import com.bjike.common.restful.ActResult;
import com.bjike.common.restful.Result;
import com.bjike.common.util.UserUtil;
import com.bjike.common.util.date.DateUtil;
import com.bjike.common.util.file.FileUtil;
import com.bjike.dto.taxi.DriverDTO;
import com.bjike.ser.taxi.DriverSer;
import com.bjike.to.taxi.DriverTO;
import com.bjike.type.taxi.VerifyType;
import com.bjike.vo.taxi.DriverVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.time.LocalDate;
import java.util.List;

/**
 * 司机信息
 *
 * @Author: [liguiqin]
 * @Date: [2017-09-02 14:53]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@LoginAuth
@RequestMapping("driver")
@RestController
public class DriverAct {

    @Autowired
    private DriverSer driverSer;

    /**
     * 申请司机
     *
     * @param to 申请信息
     * @return {name:'data',type:'boolean',defaultValue:'',description:'true/false.'}
     * @version v1
     */
    @PostMapping("apply")
    public Result apply(DriverTO to, HttpServletRequest request) throws ActException {
        try {
            String path =FileUtil.getModulePath("driver",true);
            List<File> files = FileUtil.save(request, path);
            Boolean rs = driverSer.apply(to, files);
            return ActResult.initialize(rs);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 上传申请图片
     *
     * @return {name:'data',type:'boolean',defaultValue:'',description:'true/false.'}
     * @throws ActException
     * @version v1
     */
    @PostMapping("img/upload")
    public Result imgUpload(HttpServletRequest request) throws ActException {
        try {
            String path =FileUtil.getModulePath("driver",true);
            List<File> files = FileUtil.save(request, path);
            Boolean rs = driverSer.imgUpload(files);
            return ActResult.initialize(rs);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 同意
     *
     * @param id 申请id
     * @return {name:'data',type:'boolean',defaultValue:'',description:'true/false.'}
     * @version v1
     */
    @PutMapping("agree/{id}")
    public Result agree(@PathVariable String id) throws ActException {
        try {
            Boolean rs = driverSer.agree(id);
            return ActResult.initialize(rs);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 我的申请信息
     *
     * @return class DriverVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("myApply")
    public Result myApply() throws ActException {
        try {
            DriverVO vo = driverSer.findByUserId(UserUtil.currentUserID());
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 司机申请列表
     *
     * @param verifyType 审核状态
     * @return class DriverVO
     * @throws ActException
     * @des verify为空时查询所有
     * @version v1
     */
    @GetMapping("list")
    public Result list(VerifyType verifyType, DriverDTO dto) throws ActException {
        try {
            List<DriverVO> vos = driverSer.list(verifyType, dto);
            return ActResult.initialize(vos);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}
