package com.bjike.act.recommend;

import com.bjike.common.aspect.ADD;
import com.bjike.common.exception.ActException;
import com.bjike.common.exception.SerException;
import com.bjike.common.interceptor.login.LoginAuth;
import com.bjike.common.restful.ActResult;
import com.bjike.common.util.QRCodeUtil;
import com.bjike.dto.user.RecommendDTO;
import com.bjike.ser.user.RecommendSer;
import com.bjike.to.user.RecommendTO;
import com.bjike.vo.recommend.RecommendVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * 推荐
 *
 * @Author: [liguiqin]
 * @Date: [2017-08-23 17:24]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("recommend")
public class RecommendAct {
    @Autowired
    private RecommendSer recommendSer;

    /**
     * 我要推荐
     *
     * @return {name:'data',type:'string',defaultValue:'',description:'推荐码.'}
     * @version v1
     */
    @LoginAuth
    @PostMapping("add")
    public ActResult add(@Validated({ADD.class}) RecommendTO to, BindingResult result) throws ActException {
        try {
            String code = recommendSer.add(to);
            return new ActResult(code);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 我推荐的人
     *
     * @return class RecommendVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("list")
    public ActResult list(RecommendDTO dto) throws ActException {
        try {
            List<RecommendVO> recommendList = recommendSer.myRecommends(dto);
            return ActResult.initialize(recommendList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 获取二维码
     *
     * @param code 推荐码
     * @return {name:'data',type:'bytes',defaultValue:'',description:'图片流.'}
     * @throws ActException
     * @version v1
     */
    @GetMapping("qr/{code}")
    public ActResult QRCode(@PathVariable String code, HttpServletResponse response) throws ActException {
        try {
            response.setContentType("image/jpeg");
            response.setDateHeader("expries", -1);
            response.setHeader("Cache-Control", "no-cache");
            BufferedImage image = QRCodeUtil.encode(code, response.getOutputStream());
            ImageIO.write(image, "JPEG", response.getOutputStream());
            image.flush();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ActException("生成二维码错误!");
        }
        return new ActResult("success");
    }

    /**
     * 验证二维码
     *
     * @param code 推荐码
     * @return {name:'data',type:'int',defaultValue:'',description:'推荐码.'}
     * @throws ActException
     * @version v1
     */
    @GetMapping("validate/{code}")
    public ActResult validate(@PathVariable String code) throws ActException {
        try {
            return ActResult.initialize(recommendSer.validate(code));
        } catch (Exception e) {
            throw new ActException("推荐码已失效!");
        }
    }
}
