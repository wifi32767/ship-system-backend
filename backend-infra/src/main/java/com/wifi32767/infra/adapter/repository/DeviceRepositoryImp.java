package com.wifi32767.infra.adapter.repository;

import com.wifi32767.domain.portal.model.DeviceVO;
import com.wifi32767.domain.system.adapter.repository.DeviceRepository;
import com.wifi32767.infra.adapter.converter.DeviceConverter;
import com.wifi32767.infra.dao.DeviceDao;
import com.wifi32767.infra.dao.po.Device;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DeviceRepositoryImp implements DeviceRepository {

    @Resource
    private DeviceDao deviceDao;

    @Resource
    private DeviceConverter deviceConverter;

    @Override
    public List<DeviceVO> getDeviceList() {
        return deviceConverter.LDevice2LDeviceVO(deviceDao.queryAll());
    }

    @Override
    public List<DeviceVO> getDeviceList(int page, int size) {
        return deviceConverter.LDevice2LDeviceVO(deviceDao.queryAllWithPages((page - 1) * size, size));
    }

    @Override
    public void modifyDevice(DeviceVO deviceVO) {
        Device device = deviceConverter.DeviceVO2Device(deviceVO);
        device.setDeviceChangesqlTime(LocalDateTime.now());
        deviceDao.update(device);
    }

    @Override
    public void deleteDevice(Integer deviceId) {
        deviceDao.deleteById(deviceId);
    }
}
