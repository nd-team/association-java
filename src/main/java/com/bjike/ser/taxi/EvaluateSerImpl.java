package com.bjike.ser.taxi;

import com.bjike.common.exception.SerException;
import com.bjike.common.util.UserUtil;
import com.bjike.common.util.bean.BeanCopy;
import com.bjike.common.util.file.FileUtil;
import com.bjike.dto.taxi.EvaluateDTO;
import com.bjike.entity.taxi.Evaluate;
import com.bjike.entity.taxi.EvaluatePicture;
import com.bjike.entity.taxi.TaxiOrder;
import com.bjike.ser.ServiceImpl;
import com.bjike.to.taxi.EvaluateTO;
import com.bjike.type.taxi.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单评价业务操作实现
 *
 * @Author: [ liguiqin ]
 * @Date: [  2017-09-06 14:50:50 ]
 * @Description: [ 订单评价 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [com.bjike]
 */
@Service
public class EvaluateSerImpl extends ServiceImpl<Evaluate, EvaluateDTO> implements EvaluateSer {

    @Autowired
    private TaxiOrderSer taxiOrderSer;
    @Autowired
    private EvaluatePictureSer evaluatePictureSer;


    @Transactional
    @Override
    public Boolean add(EvaluateTO to, List<File> files) throws SerException {
        Evaluate evaluate = BeanCopy.copyProperties(to, Evaluate.class);
        TaxiOrder taxiOrder = taxiOrderSer.findById(to.getOrderId());
        if (null != taxiOrder) {
            evaluate.setReviewer(taxiOrder.getUser());
            evaluate.setUser(UserUtil.currentUser(false));
            evaluate.setTaxiOrder(taxiOrder);
            super.save(evaluate);
            List<EvaluatePicture> evaluatePictures = new ArrayList<>(files.size());
            for (File file : files) {
                String path = FileUtil.getDbPath(file.getPath());
                EvaluatePicture picture = new EvaluatePicture();
                picture.setEvaluate(evaluate);
                picture.setImage(path);
                evaluatePictures.add(picture);
            }
            evaluatePictureSer.save(evaluatePictures); //保存图片
        } else {
            for (File file : files) {
                if (file.exists()) {
                    file.delete();
                }
            }
            throw new SerException("该订单不存在");
        }

        return true;
    }

    @Override
    public Boolean unfinished(String orderId, String content) throws SerException {
       TaxiOrder taxiOrder =  taxiOrderSer.findById(orderId);
       if(null!=taxiOrder){
           Evaluate evaluate =new Evaluate();
           evaluate.setReviewer(taxiOrder.getUser());
           evaluate.setUser(UserUtil.currentUser(false));
           evaluate.setContent(content);
           evaluate.setTaxiOrder(taxiOrder);
           super.save(evaluate);
           taxiOrder.setStatus(OrderStatus.UNFINISHED);
           taxiOrderSer.update(taxiOrder);
       }else {
           throw new SerException("该订单不存在");
       }
        return true;
    }
}


