package com.wifi32767.domain.system.adapter.repository;

import com.wifi32767.domain.portal.model.DeviceVO;

import java.util.List;

public interface DeviceRepository {
    List<DeviceVO> getDeviceList();
    List<DeviceVO> getDeviceList(int page, int size);
    void modifyDevice(DeviceVO deviceVO);
    void deleteDevice(Integer deviceId);
}
