package com.bjike.ser.taxi;

import com.bjike.common.exception.SerException;
import com.bjike.common.util.UserUtil;
import com.bjike.common.util.bean.BeanCopy;
import com.bjike.common.util.file.FileUtil;
import com.bjike.dto.Restrict;
import com.bjike.dto.taxi.DriverDTO;
import com.bjike.dto.taxi.DrivingLicenceDTO;
import com.bjike.entity.taxi.Driver;
import com.bjike.entity.taxi.DrivingLicence;
import com.bjike.entity.user.User;
import com.bjike.ser.ServiceImpl;
import com.bjike.to.taxi.DriverTO;
import com.bjike.type.taxi.VerifyType;
import com.bjike.vo.taxi.DriverVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 司机信息操作
 *
 * @Author: [liguiqin]
 * @Date: [2017-09-02 09:32]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class DriverSerImpl extends ServiceImpl<Driver, DriverDTO> implements DriverSer {
    @Autowired
    private DrivingLicenceSer drivingLicenceSer;

    @Transactional
    @Override
    public Boolean apply(DriverTO to, List<File> files) throws SerException {
        try {
            DriverDTO dto = new DriverDTO();
            User user = UserUtil.currentUser(false);
            dto.getConditions().add(Restrict.eq("user.id", user.getId()));
            if (null == super.findOne(dto)) {
                Driver driver = BeanCopy.copyProperties(to, Driver.class,true);
                driver.setVerifyType(VerifyType.PENDING);
                driver.setUser(user);
                super.save(driver);
                if (null != files) {
                    List<DrivingLicence> drivingLicences = new ArrayList<>(files.size());
                    for (File file : files) {
                        DrivingLicence drivingLicence = new DrivingLicence();
                        drivingLicence.setDriver(driver);
                        drivingLicence.setImage(FileUtil.getDbPath(file.getPath()));
                        drivingLicences.add(drivingLicence);
                    }
                    drivingLicenceSer.save(drivingLicences);
                }

                return true;
            } else {
                throw new SerException("对不起,你已经申请过了");
            }

        } catch (Exception e) {
            if(null!=files){
                for (File file : files) {
                    file.delete();
                }
            }
            throw  new SerException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public Boolean imgUpload(List<File> files) throws SerException {
        DriverDTO dto = new DriverDTO();
        User user = UserUtil.currentUser(false);
        dto.getConditions().add(Restrict.eq("user.id", user.getId()));
        Driver driver = super.findOne(dto);
        if (null != driver) {
            DrivingLicenceDTO licenceDTO = new DrivingLicenceDTO();
            licenceDTO.getConditions().add(Restrict.eq("driver.id", driver.getId()));
            List<DrivingLicence> drivingLicences = drivingLicenceSer.findByCis(licenceDTO);
            if (null != drivingLicences && drivingLicences.size() > 0) { //删除之前上传的照片,如果存在

                for (DrivingLicence licence : drivingLicences) {
                    File f = new File(FileUtil.getRealPath(licence.getImage()));
                    if (f.exists()) {
                        f.delete();
                    }
                }
                drivingLicenceSer.remove(drivingLicences);
            }
            for (File file : files) {//上传照片
                DrivingLicence drivingLicence = new DrivingLicence();
                drivingLicence.setImage(FileUtil.getDbPath(file.getPath()));
                drivingLicence.setDriver(driver);
                drivingLicences.add(drivingLicence);
            }
            drivingLicenceSer.save(drivingLicences);

        } else {
            for (File file : files) {
                file.delete();
            }
            throw new SerException("找不到该用户司机申请单");

        }
        return true;
    }

    @Override
    public Boolean agree(String id) throws SerException {
        Driver driver = super.findById(id);
        driver.setVerifyType(VerifyType.PASS);
        super.update(driver);
        return true;
    }

    @Override
    public DriverVO findByUserId(String userId) throws SerException {
        DriverDTO dto = new DriverDTO();
        dto.getConditions().add(Restrict.eq("user.id", userId));
        Driver driver = super.findOne(dto);
        if (null != driver) {
            return getDriverVO(driver);

        } else {
            throw new SerException("没有查询到司机申请信息");
        }

    }

    @Override
    public List<DriverVO> list(VerifyType verifyType, DriverDTO dto) throws SerException {
        if (null != verifyType) {
            dto.getConditions().add(Restrict.eq("applyType", verifyType));
        }
        List<Driver> drivers = super.findByCis(dto);
        List<DriverVO> driverVOS = new ArrayList<>();
        for (Driver driver : drivers) {
            DriverVO driverVO = getDriverVO(driver);
            driverVOS.add(driverVO);
        }
        return driverVOS;
    }

    private DriverVO getDriverVO(Driver driver) throws SerException {
        if (null != driver) {
            DriverVO driverVO = BeanCopy.copyProperties(driver, DriverVO.class);
            DrivingLicenceDTO licenceDTO = new DrivingLicenceDTO();
            licenceDTO.getConditions().add(Restrict.eq("driver.id", driver.getId()));
            List<DrivingLicence> licences = drivingLicenceSer.findByCis(licenceDTO);
            String[] images = new String[licences.size()];
            int i = 0;
            for (DrivingLicence licence : licences) {
                images[i++] = licence.getImage();
            }
            driverVO.setImages(images);
            return driverVO;
        }
        return null;
    }
}
