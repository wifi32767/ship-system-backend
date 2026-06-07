package com.wifi32767.domain.system.service;

import com.wifi32767.common.PageData;
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
        if (auditFlag < 0 || auditFlag > 2) {
            return 0;
        }
        return portalRepository.queryDeviceCountByAuditFlag(auditFlag);
    }

    @Override
    public List<DeviceVO> getDeviceList() {
        return deviceRepository.getDeviceList();
    }

    @Override
    public PageData<DeviceVO> getDeviceList(int pageNum, int pageSize) {
        if (pageNum <= 0 || pageSize <= 0) {
            return null;
        }
        return deviceRepository.getDeviceList(pageNum, pageSize);
    }

    @Override
    public void modifyDevice(DeviceVO deviceVO) {
        if (deviceVO == null || deviceVO.getId() == null) {
            return;
        }
        deviceRepository.modifyDevice(deviceVO);
    }

    @Override
    public void deleteDevice(Integer deviceId) {
        if (deviceId == null) {
            return;
        }
        deviceRepository.deleteDevice(deviceId);
    }
}
