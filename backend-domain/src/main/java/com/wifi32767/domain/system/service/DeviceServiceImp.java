package com.wifi32767.domain.system.service;

import com.wifi32767.domain.portal.adapter.repository.PortalRepository;
import com.wifi32767.domain.portal.model.DeviceVO;
import com.wifi32767.domain.system.adapter.repository.DeviceRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DeviceServiceImp implements DeviceService {

    @Resource
    private DeviceRepository deviceRepository;

    @Resource
    private PortalRepository portalRepository;

    @Override
    public int deviceCount() {
        return portalRepository.queryDeviceCount();
    }

    @Override
    public int auditDeviceCount(int auditFlag) {
        return portalRepository.queryDeviceCountByAuditFlag(auditFlag);
    }

    @Override
    public List<DeviceVO> getDeviceList() {
        return deviceRepository.getDeviceList();
    }

    @Override
    public List<DeviceVO> getDeviceList(int page, int size) {
        return deviceRepository.getDeviceList(page, size);
    }

    @Override
    public void modifyDevice(DeviceVO deviceVO) {
        deviceRepository.modifyDevice(deviceVO);
    }

    @Override
    public void deleteDevice(Integer deviceId) {
        deviceRepository.deleteDevice(deviceId);
    }
}
