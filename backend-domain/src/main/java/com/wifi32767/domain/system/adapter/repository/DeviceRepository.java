package com.wifi32767.domain.system.adapter.repository;

import com.wifi32767.common.PageData;
import com.wifi32767.domain.portal.model.DeviceVO;

import java.util.List;

public interface DeviceRepository {
    List<DeviceVO> getDeviceList();

    PageData<DeviceVO> getDeviceList(int pageNum, int pageSize);

    void modifyDevice(DeviceVO deviceVO);

    void deleteDevice(Integer deviceId);
}
