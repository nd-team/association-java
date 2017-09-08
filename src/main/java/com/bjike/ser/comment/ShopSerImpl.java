package com.bjike.ser.comment;

import com.bjike.common.exception.SerException;
import com.bjike.dto.comment.ShopDTO;
import com.bjike.entity.comment.Shop;
import com.bjike.ser.ServiceImpl;
import com.bjike.vo.comment.ShopVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-06-27 08:51]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class ShopSerImpl extends ServiceImpl<Shop, ShopDTO> implements ShopSer {
    @Transactional
    @Override
    public Shop add(Shop shop) throws SerException {
        Long seq = super.count(new ShopDTO());
        shop.setSeq(seq==null?0:seq.intValue() + 1);
        super.save(shop);
        return shop;
    }

    @Override
    public List<ShopVO> nearby(ShopDTO dto) throws SerException {
        double latitude = dto.getLatitude();
        double longitude = dto.getLongitude();
        double r = 6371;//地球半径千米
        double dis = 0.5;//0.5千米距离
        double dlng =  2*Math.asin(Math.sin(dis/(2*r))/Math.cos(latitude*Math.PI/180));
        dlng = dlng*180/Math.PI;//角度转为弧度
        double dlat = dis/r;
        dlat = dlat*180/Math.PI;
        double minlat =latitude-dlat;
        double maxlat = latitude+dlat;
        double minlng = longitude -dlng;
        double maxlng = longitude + dlng;

        String sql = "select id,name,seq,point_id,longitude,latitude,address from shop where longitude>="+minlat+" and longitude <="+maxlat+" and latitude<="+minlng+" and latitude>="+maxlng;
        List<ShopVO> vos = super.findBySql(sql,ShopVO.class,new String[]{"id","name","seq","pointId","longitude","latitude","address"});
        for(ShopVO shop: vos){
            sql = "select  b.headPath from comment a,user b where a.shop_id='"+shop.getId()+"' and a.user_id=b.id  desc  LIMIT 0,3";
            List<Object> ob_images =  super.findBySql(sql);
            if(null!=ob_images && ob_images.size()>0){
                String[] images = ob_images.toArray(new String[ob_images.size()]);
                shop.setImages(images);
            }
        }
        return vos;
    }
}
