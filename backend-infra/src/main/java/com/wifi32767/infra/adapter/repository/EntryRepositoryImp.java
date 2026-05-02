package com.wifi32767.infra.adapter.repository;

import com.wifi32767.domain.entry.adapter.repository.EntryRepository;
import com.wifi32767.domain.portal.model.DeviceVO;
import com.wifi32767.infra.adapter.converter.DeviceConverter;
import com.wifi32767.infra.dao.DeviceDao;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class EntryRepositoryImp implements EntryRepository {
    @Resource
    private DeviceDao deviceDao;

    @Resource
    private DeviceConverter deviceConverter;

    @Override
    public void single(DeviceVO deviceVO) {
        deviceVO.setDeviceInsqlTime(LocalDateTime.now());
        deviceVO.setDeviceChangesqlTime(LocalDateTime.now());
        deviceDao.insert(deviceConverter.DeviceVO2Device(deviceVO));
    }

    @Override
    public void batch(List<DeviceVO> devices) {
        devices.forEach(
                deviceVO -> {
                    deviceVO.setDeviceInsqlTime(LocalDateTime.now());
                    deviceVO.setDeviceChangesqlTime(LocalDateTime.now());
                    deviceDao.insert(deviceConverter.DeviceVO2Device(deviceVO));
                }
        );
    }
}
