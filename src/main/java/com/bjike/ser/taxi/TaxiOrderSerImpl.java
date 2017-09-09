package com.bjike.ser.taxi;

import com.bjike.common.exception.SerException;
import com.bjike.common.util.UserUtil;
import com.bjike.common.util.bean.BeanCopy;
import com.bjike.dto.Restrict;
import com.bjike.dto.taxi.AreaCostDTO;
import com.bjike.dto.taxi.TaxiOrderDTO;
import com.bjike.entity.taxi.AreaCost;
import com.bjike.entity.taxi.TaxiOrder;
import com.bjike.entity.user.User;
import com.bjike.ser.ServiceImpl;
import com.bjike.to.taxi.TaxiOrderTO;
import com.bjike.type.taxi.OrderStatus;
import com.bjike.type.user.SexType;
import com.bjike.vo.taxi.NearbyVO;
import com.bjike.vo.taxi.TaxiOrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 叫车订单 业务操作实现
 *
 * @Author: [ liguiqin ]
 * @Date: [  2017-09-02 17:50:09 ]
 * @Description: [ 叫车订单 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [com.bjike]
 */
@Service
public class TaxiOrderSerImpl extends ServiceImpl<TaxiOrder, TaxiOrderDTO> implements TaxiOrderSer {

    @Autowired
    private AreaCostSer areaCostSer;

    @Transactional
    @Override
    public Boolean publish(TaxiOrderTO to) throws SerException {
        User user = UserUtil.currentUser(false);
        TaxiOrder taxiOrder = BeanCopy.copyProperties(to, TaxiOrder.class);
        taxiOrder.setUser(user);
        taxiOrder.setStatus(OrderStatus.PENDING);
        super.save(taxiOrder);
        return true;
    }

    @Override
    public Boolean taking(String orderId) throws SerException {
        TaxiOrderDTO dto = new TaxiOrderDTO();
        dto.getConditions().add(Restrict.eq("id", orderId));
        dto.getConditions().add(Restrict.eq("status", OrderStatus.PENDING));
        TaxiOrder taxiOrder = super.findOne(dto);
        if (null != taxiOrder) {
            taxiOrder.setStatus(OrderStatus.RECEIVED);
        } else {
            throw new SerException("订单不存在,或已被抢单了");
        }
        return true;
    }

    @Override
    public List<NearbyVO> nearby(Double longitude, Double latitude, Integer range) throws SerException {
        double r = 6371;//地球半径千米
        double dis = (range != null ? range / 1000 : 0.2);//0.2千米距离
        double dlng = 2 * Math.asin(Math.sin(dis / (2 * r)) / Math.cos(latitude * Math.PI / 180));
        dlng = dlng * 180 / Math.PI;//角度转为弧度
        double dlat = dis / r;
        dlat = dlat * 180 / Math.PI;
        double minlat = latitude - dlat;
        double maxlat = latitude + dlat;
        double minlng = longitude - dlng;
        double maxlng = longitude + dlng;

        String sql = " select a.id,a.start_point as startPoint,a.destination ,b.nickname,b.head_path as headPath ,b.sex_type as sexType, " +
                " a.longitude,a.latitude from taxi_order a" +
                " ,user b where a.user_id=b.id and longitude>='%s' and longitude <='%s' and latitude>='%s' and latitude<='%s' and a.status=0";
        sql = String.format(sql, minlng, maxlng, minlat, maxlat);
        String[] fields = new String[]{"id", "startPoint", "destination", "nickname", "headPath", "sexType", "longitude", "latitude",};
        List<NearbyVO> nearbyVOS = super.findBySql(sql, TaxiOrderVO.class, fields);
        nearbyVOS.forEach(vo -> {
            double d1 = Double.parseDouble(vo.getLongitude());
            double d2 = Double.parseDouble(vo.getLatitude());
            vo.setDistance(getDistance(longitude, latitude, d1, d2));
            vo.setNickname(vo.getNickname().substring(0, 1) + (vo.getSexType().equals(SexType.MAN) ? "先生" : "女士"));
            vo.setSexType(null);
        });
        return nearbyVOS;
    }

    @Override
    public Double cost(String area, Double distance, Integer minutes) throws SerException {
        AreaCostDTO dto = new AreaCostDTO();
        dto.getConditions().add(Restrict.eq("area", area));
        AreaCost cost = areaCostSer.findOne(dto);
        Double fee = null;
        if (null != cost && cost.getEnable()) {
            fee = distance * cost.getPerMinute() + minutes * cost.getPerMinute();
        }
        return fee;
    }

    //地球平均半径
    private static final double EARTH_RADIUS = 6378137;

    //把经纬度转为度（°）
    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 根据两点间经纬度坐标（double值），计算两点间距离，单位为米
     *
     * @param lng1
     * @param lat1
     * @param lng2
     * @param lat2
     * @return
     */
    public static double getDistance(double lng1, double lat1, double lng2, double lat2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(
                Math.sqrt(
                        Math.pow(Math.sin(a / 2), 2)
                                + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)
                )
        );
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }
}


